package com.neu.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.EmailSender.EmailSender;
import com.neu.JSONPojo.HotelJSON;
import com.neu.JSONPojo.JSONDetails;
import com.neu.Util.Connect_URL;
import com.neu.pojo.Booking;
import com.neu.pojo.Customer;
import com.neu.pojo.Room;

@Controller
@RequestMapping("")
public class UserProfileController {

	Connect_URL urlconn = new Connect_URL();

	@RequestMapping(value = "/bookhotel.htm", method = RequestMethod.GET)
	public String viewhotel( Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String hotelid = request.getParameter("hotelID");
		if (null != session.getAttribute("customer")) {

			int id = Integer.parseInt(hotelid);
			if (null != session.getAttribute("hoteljson")) {
				JSONDetails hoteljson = (JSONDetails) session.getAttribute("hoteljson");
				ArrayList<HotelJSON> hotellist = hoteljson.getHotelList();
				for (HotelJSON hotel : hotellist) {
					if (hotel.getHotelID() == id) {
						System.out.println(hotel.getHotelID());
						model.addAttribute("staydates", hoteljson.getStayDates());
						model.addAttribute("hotelinfo", hotel);
						return "hello";
					}
					
				}
				return "error";
			}
			return "error";
		} else {
			session.setAttribute("hotelid", hotelid);
			return "Login";
		}
	}

	@RequestMapping(value = "/confirmbooking.htm", method = RequestMethod.GET)
	public String openPayment(HttpServletRequest request) throws IOException {
		String id = "3456";
		String hotelname = "ShivSagar";
		String desc = "abaaklsd";
		String detailsurl = "sdjv";
		String thumburl = "sjdv";
		String rating = "4.5";
		String streetaddr = "sjkfe";
		String city = "Boston";
		String province = "abc";
		String country = "USA";
		String price = "567.8";
		HttpSession session = request.getSession();
		if (null != session.getAttribute("hotelid")) {
			int hotelid = Integer.parseInt(session.getAttribute("hotelid").toString());
			if (null != session.getAttribute("hoteljson")) {
				JSONDetails det = (JSONDetails) session.getAttribute("hoteljson");
				ArrayList<HotelJSON> hotellist = det.getHotelList();

				for (HotelJSON hotel : hotellist) {
					if (hotel.getHotelID() == hotelid) {
						id = String.valueOf(hotel.getHotelID());
						hotelname = hotel.getHotelName().replaceAll(" ", "%20");
						desc = hotel.getDescription().replaceAll(" ", "%20");
						detailsurl = hotel.getDetailsurl().replaceAll(" ", "%20");
						thumburl = hotel.getThumbnailUrl().replaceAll(" ", "%20");
						rating = hotel.getRating().replaceAll(" ", "%20");
						streetaddr = hotel.getLocation().getStreetAddress().replaceAll(" ", "%20");
						city = hotel.getLocation().getCity().replaceAll(" ", "%20");
						province = hotel.getLocation().getProvince().replaceAll(" ", "%20");
						country = hotel.getLocation().getCountry().replaceAll(" ", "%20");
						if(hotel.getPrice()!=null&&hotel.getPrice().getValue()!=null&&hotel.getPrice().getValue().getValue()!=null){
						price = hotel.getPrice().getValue().getValue();}
					}
				}
				urlconn.callSaveHotel("http://localhost:8094/saveHotel?hotelid=" + id + "&hotelname=" + hotelname
						+ "&description=" + desc + "&detailsurl=" + detailsurl + "&thumbnailurl=" + thumburl
						+ "&rating=" + rating + "&streetaddress=" + streetaddr + "&city=" + city + "&province="
						+ province + "&country=" + country + "&price=" + price, "POST");
				double pricedouble =Double.parseDouble(price);
				request.setAttribute("price", pricedouble);
				return "Payment";
			}
		}
		return "error";
	}
	@RequestMapping(value = "/payment.htm", method = RequestMethod.POST)
	public String confirmPayment(HttpServletRequest request) throws IOException {
		
		
		HttpSession session = request.getSession();
		if(null!=session.getAttribute("hotelid"))
		{
			
			if(null!=session.getAttribute("customer"))
			{
				String hotelid = session.getAttribute("hotelid").toString();
				String roomtype= request.getParameter("roomtype");
				String noofrooms="2";
				Customer c=(Customer)session.getAttribute("customer");
				String emailid= c.getEmailId();
				String price= request.getParameter("price");
				String cardholder = request.getParameter("cardholdername");
				String cardno = request.getParameter("cardnumber");
				String ccv= request.getParameter("ccv");
				String expmm=request.getParameter("expireMM");
				String expyy= request.getParameter("expireYY");
				String expdate = expmm+expyy;
				String cardtype = request.getParameter("cardtype");
				JSONDetails json=(JSONDetails)session.getAttribute("hoteljson");
				String checkin = json.getStayDates().getCheckInDate();
				String checkout=json.getStayDates().getCheckOutDate();
				String checkindate = request.getParameter(checkin);
				String checkoutdate = request.getParameter(checkout);
				if(cardno.equals("")||ccv.equals("")||cardholder.equals("")||cardno==null||ccv==null||cardholder==null)
				{
					session.setAttribute("message","Card details cannot be null");
					return "error";
				}
				String urlstr= "http://localhost:8094/confirmbooking?hotelid="+hotelid+"&roomtype="+roomtype+
						"&noofrooms="+noofrooms+"&customerid="+emailid+"&totalprice="+price+"&cardnumber="+cardno+
						"&cardholder="+cardholder+"&cardtype="+cardtype+
						"&ccv="+ccv+"&expirydate="+expdate+"&checkindate="+checkin+"&checkoutdate="+checkout;
				urlconn.callSaveHotel(urlstr, "POST");
				return "PaymentSuccess";
			}
		}
		
		return "error";
		
	}
	
	@RequestMapping(value="/getBookings.htm",method= RequestMethod.GET)
	public String getBooking(HttpServletRequest request) throws IOException
	{
		HttpSession session = request.getSession();
		if(null!=session.getAttribute("customer"))
		{
			Customer c = (Customer) session.getAttribute("customer");
		 ArrayList<Room> bookedrooms= urlconn.getBookings("http://localhost:8094/getuserbooking?id="+c.getPersonId(), "POST");
		if(bookedrooms!=null)
		{
			session.setAttribute("bookinglist", bookedrooms);
			if(null!=session.getAttribute("bookinglist")){
			return "ViewBooking";}
		}
		}
		return "error";
	}
	
	@RequestMapping(value="/cancelbooking.htm",method= RequestMethod.GET)
	public String cancelBookingRequest(HttpServletRequest request) throws IOException
	{
		String bid = request.getParameter("bookingid");
		int result = urlconn.cancelBooking("http://localhost:8094/cancelrequest?bookingid="+bid, "GET");
		if(result>0)
		{ 
			HttpSession session = request.getSession();
			if(null!=session.getAttribute("customer")){
			Customer c =(Customer)session.getAttribute("customer");
			
			
			StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~").append("\n");

        stringBuffer.append("Web Tools Travel Site Update").append("\n");
        stringBuffer.append("Your booking was cancelled successfully").append("\n");
        stringBuffer.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~").append("\n");

        String[] to = {c.getEmailId()};
        EmailSender.EmailSend("webtools.travel@gmail.com", "travel999", stringBuffer.toString(), to);
			return "CancelRequest";
			}
		}
		return "error";
		
	}
	
}

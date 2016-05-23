package service;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.neu.exception.AdException;

import service.Util.RequestConnection;

@RestController
public class ServiceController {
	CustomerDAO dao= new CustomerDAO();
	RequestConnection conn= new RequestConnection();
    @RequestMapping(value="/user",method= RequestMethod.POST)
  
    public Customer signUp(@RequestParam(value="firstName") String firstName,
    		@RequestParam(value="lastName") String lastName,
    		@RequestParam(value="emailId") String emailId,
    		@RequestParam(value="password") String password) throws AdException {
    	Customer c=null;
boolean value = dao.isValidUserName(emailId);
		if(value)
		{
			c = dao.create(emailId, password, firstName, lastName);
			if(c!=null)
			{
			return c;
			}
	    	
		}

    	return null;
        
    }
    @RequestMapping(value="/login",method= RequestMethod.POST)
    
    public Customer login(
    		@RequestParam(value="emailId") String emailId,
    		@RequestParam(value="password") String password) throws AdException {
    	Customer c=dao.isValidUser(emailId, password);
    	if(c!=null)
    	{
        return c;
    	}
    	return null;
    }
    
@RequestMapping(value="/gethotels",method= RequestMethod.GET)
    
    public String getHotels(	@RequestParam(value="address") String latlong,
    		@RequestParam(value="checkindate") String checkindate,
    		@RequestParam(value="checkoutdate") String checkoutdate,
    		@RequestParam(value="adults") String adults
    		) throws AdException, IOException {
	
	
	String url= "http://terminal2.expedia.com:80/x/hotels?maxhotels=100&location="+latlong+"&radius=5km&checkInDate="+checkindate+""
			+ "&checkOutDate="+checkoutdate+"&adults="+adults+"&apikey=zR1tAnWmhd7JriaAFAVRBpseHIFxyE9W";
    String str= conn.callService(url, "GET");
	return str;
	
    }
    
	@RequestMapping(value="/saveHotel",method= RequestMethod.POST)
	  public Hotel saveHotel(@RequestParam(value="hotelid")String hotelid,
			  @RequestParam(value="hotelname")String hotelname,
			  @RequestParam(value="description")String description,
			  @RequestParam(value="detailsurl")String detailsurl,
			  @RequestParam(value="thumbnailurl")String thumbnailurl,
			  @RequestParam(value="rating")String rating,
			  @RequestParam(value="streetaddress")String streetaddress,
			  @RequestParam(value="city")String city,
			  @RequestParam(value="province")String province,
			  @RequestParam(value="country")String country,
			  @RequestParam(value="price")String price
	    		) throws AdException, IOException {
	
		Location location= new Location();
		location.setStreetAddress(streetaddress);
		location.setCity(city);
		location.setProvince(province);
		location.setCountry(country);
		double dprice=Double.parseDouble(price);
		int id= Integer.parseInt(hotelid);
		HotelDAO dao = new HotelDAO();
		Hotel hotel = dao.insert(id, hotelname, description, detailsurl, rating, thumbnailurl, location, dprice);
		return hotel;
	    }
	
	@RequestMapping(value="/confirmbooking" ,method=RequestMethod.POST)
	public Booking confirmBooking(
			@RequestParam(value="hotelid")String hid,
			@RequestParam(value="roomtype")String roomType,
			@RequestParam(value="noofrooms")String noofrooms,
			@RequestParam(value="customerid") String custemailid,
			@RequestParam(value="totalprice")String totalprice,
			@RequestParam(value="cardnumber")String cardno,
			@RequestParam(value="cardholder")String cardname,
			@RequestParam(value="cardtype")String cardtype,
			@RequestParam(value="ccv")String ccv,
			@RequestParam(value="expirydate")String expdate,
			@RequestParam(value="checkindate") String checkindate,
			@RequestParam(value="checkoutdate")String checkoutdate
			
			) throws AdException
	{
		Card card = new Card();
		card.setCardHolderName(cardname);
		card.setCardnumber(Long.parseLong(cardno));
		card.setCardType(cardtype);
		card.setCcv(Integer.parseInt(ccv));
		card.setValidTo(expdate);
		
		Payment payment = new Payment();
		Date date = new Date();
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy-hh:mm:ss");
		payment.setPaymentDate(dt.format(date));
		payment.setPaidstatus("Paid");
		payment.setTotalprice(Double.parseDouble(totalprice));
		
		CustomerDAO cdao = new CustomerDAO();
		Customer customer = cdao.getCustomerById(custemailid);
		
		
		BookingDAO dao = new BookingDAO();
		Booking bookingdetails= new Booking();
		Set<Room> roomset =dao.allotRooms(Integer.parseInt(noofrooms), roomType, Integer.parseInt(hid));
		bookingdetails.setRoomBookingList(roomset);
		Booking booking= dao.insertBooking
				(bookingdetails, payment, card, customer);
	
	if(booking!=null&&dao.updateRooms(roomset, "booked", checkindate, checkoutdate,booking.getBookingId()))
	{
		return booking;
	}
	
	return null;
	}
	
	@RequestMapping(value="/getuserbooking" , method= RequestMethod.POST)
	public ArrayList<Room> getBooking(@RequestParam (value="id")String id) throws AdException
	{
		CustomerDAO dao = new CustomerDAO();
		
			BookingDAO bdao= new BookingDAO();
			ArrayList<Room> list =bdao.getBooking(Integer.parseInt(id));
			return list;
	}
	
	@RequestMapping(value="/getAllUsers",method=RequestMethod.GET)
	public ArrayList<Customer> getAllUsers() throws AdException
	{
		AdminDAO dao = new AdminDAO();
		ArrayList<Customer> customerList = dao.getAllCustomers();
		return customerList;
	}
	@RequestMapping(value="/cancelrequest",method=RequestMethod.GET)
	public int cancelRequest(@RequestParam(value="bookingid")String bookingid) throws AdException
	{
		BookingDAO dao = new BookingDAO();
		int value = dao.updateCancelrequest(Integer.parseInt(bookingid));
		if(value >0)
		{
			return value;
		}
		return value;
	}
	
}

package com.neu.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.velocity.context.Context;
import org.apache.velocity.servlet.VelocityServlet;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.neu.JSONPojo.HotelJSON;
import com.neu.JSONPojo.JSONDetails;
import com.neu.Util.Connect_URL;
import com.neu.Util.Parse;
import com.neu.pojo.Customer;

@Controller
@RequestMapping("")

public class IndexController {
	Connect_URL conn = new Connect_URL();
	Parse parse = new Parse();

	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
	public String openHome() throws IOException {

		return "home";
	}

	@RequestMapping(value = "/gethotels.htm", method = RequestMethod.POST)
	public void viewHotels(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String latlong = request.getParameter("address");
		String checkin = request.getParameter("checkindate");
		String checkout = request.getParameter("checkoutdate");
		String adults = request.getParameter("adults");

		String connurl = "http://localhost:8094/gethotels?address=" + latlong + "&checkindate=" + checkin
				+ "&checkoutdate=" + checkout + "&adults=" + adults;

		String json = conn.callHotelService(connurl, "GET");
		JSONDetails jsonDetails = parse.getJSONData(json);
		HttpSession session = request.getSession();
		session.setAttribute("hoteljson", jsonDetails);
		if (jsonDetails != null) {
			ArrayList<HotelJSON> hotels = jsonDetails.getHotelList();
			if (hotels != null) {
				JSONObject object = new JSONObject();
				object.put("hotels", hotels);
				PrintWriter out = response.getWriter();
				out.println(object);
			}
		}

	}


}

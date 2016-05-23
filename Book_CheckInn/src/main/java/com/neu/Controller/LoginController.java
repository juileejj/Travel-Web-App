package com.neu.Controller;

import java.awt.SystemColor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.tools.JavaFileObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.remoting.support.UrlBasedRemoteAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;

import com.google.gson.Gson;
import com.neu.EmailSender.EmailSender;
import com.neu.Encryption.Encryption;
import com.neu.JSONPojo.HotelJSON;
import com.neu.JSONPojo.JSONDetails;
import com.neu.Util.Connect_URL;
import com.neu.Validator.CustomerValidator;
import com.neu.exception.AdException;
import com.neu.pojo.Customer;

@Controller
@RequestMapping("")

public class LoginController {
	
	Connect_URL urlconn = new Connect_URL();
	Encryption hash = new Encryption();

	@RequestMapping(value = "/login.htm", method = RequestMethod.POST)
	protected String login(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception {
		String url = null;
		
		String hashpwd = hash.encrypt(request.getParameter("password"));
		Customer c = urlconn.getUserService(
				"http://localhost:8094/login?emailId=" + request.getParameter("emailId") + "&password=" + hashpwd, "POST");
		if (c != null) {
			HttpSession session = request.getSession();
			session.setAttribute("customer", c);
			Cookie cookie = new Cookie("username", c.getFirstName());
			response.addCookie(cookie);
			if (null != session.getAttribute("hotelid")) {
				int id=Integer.parseInt(session.getAttribute("hotelid").toString());
				JSONDetails hoteljson = (JSONDetails) session.getAttribute("hoteljson");
				if(hoteljson!=null){
				ArrayList<HotelJSON> hotellist = hoteljson.getHotelList();
				for (HotelJSON hotel : hotellist) {
					if (hotel.getHotelID() == id) {
						model.addAttribute("user", c);
						model.addAttribute("hotelinfo", hotel);
						model.addAttribute("staydates",hoteljson.getStayDates());
						return "hello";
					}
					
				}
				}
			}
			return "userProfile";
		}
		request.setAttribute("message","Login was not successful");
		return "error";
	}

	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
	public String initializeLoginForm(HttpServletRequest request) {

		return "Login";
	}

	

	@RequestMapping(value = "/logout.htm")
	public String logout(@ModelAttribute("customer") Customer customer, HttpServletRequest request,
			BindingResult result) {

		HttpSession session = request.getSession();
		if (null != session.getAttribute("customer")) {
			session.invalidate();
			return "Login";
		}
		return "Login";
	}
}

package com.neu.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.el.ArrayELResolver;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.Util.Connect_URL;
import com.neu.pojo.Customer;

@Controller
public class AdminController {

	Connect_URL urlconn = new Connect_URL();
	
	@RequestMapping(value="/admin.htm",method=RequestMethod.GET)
	public String getAdmin()
	{
		return "AdminHome";
	}
	@RequestMapping(value="/logoutadmin.htm",method=RequestMethod.GET)
	public String logoutAdmin()
	{
		return "home";
	}
	@RequestMapping(value="/viewall.htm",method=RequestMethod.GET)
	public String viewAll(HttpServletRequest request) throws IOException
	{
		ArrayList<Customer> custList= urlconn.getAllUsers("http://localhost:8094/getAllUsers", "GET");
		HttpSession session = request.getSession();
		session.setAttribute("alluserlist",custList);
		if(null!=session.getAttribute("alluserlist"))
		{
			return "AdminViewAll";
		}
		return "error";
	}
	
	
	
}

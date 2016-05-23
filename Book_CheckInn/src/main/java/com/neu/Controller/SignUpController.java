package com.neu.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.EmailSender.EmailSender;
import com.neu.Encryption.Encryption;
import com.neu.Util.Connect_URL;
import com.neu.Validator.CustomerValidator;
import com.neu.pojo.Customer;

@Controller
public class SignUpController {
	
	Connect_URL urlconn = new Connect_URL();
	Encryption hash = new Encryption();
	
	@Autowired
	@Qualifier("customerValidator")
	CustomerValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	@RequestMapping(value = "/signup.htm", method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("customer") Customer customer, BindingResult result,HttpServletRequest request)
			throws Exception {
		String url = null;
		validator.validate(customer, result);
		if (result.hasErrors()) {
			return "SignUp";
		}
		
		String hashpwd = hash.encrypt(customer.getPassword());
		Customer c = urlconn.getUserService(
				"http://localhost:8094/user?emailId=" + customer.getEmailId() + "&firstName=" + customer.getFirstName()
						+ "&lastName=" + customer.getLastName() + "&password=" + hashpwd,
				"POST");
		if (c != null) {

            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~").append("\n");

            stringBuffer.append("Welcome to Web Tools Travel Site").append("\n");
            stringBuffer.append("Customer name:").append(c.getFirstName()+" "+c.getLastName()).append("\n");
            stringBuffer.append("Your login details:").append("\n").append("Email:"+c.getEmailId()).append("\n");
            stringBuffer.append("Password:").append("Password:"+customer.getPassword()).append("\n");
     
            stringBuffer.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~").append("\n");

            String[] to = {c.getEmailId()};
            EmailSender.EmailSend("webtools.travel@gmail.com", "travel999", stringBuffer.toString(), to);
            return "Login";
		}
		
		request.setAttribute("message","User with the same email id already exists!");
		return "error";
	}
	
	@RequestMapping(value = "/signup.htm", method = RequestMethod.GET)
	public String initializeSignUpForm(@ModelAttribute("customer") Customer customer, HttpServletRequest request,
			BindingResult result) {

		return "SignUp";
	}
	
	
}

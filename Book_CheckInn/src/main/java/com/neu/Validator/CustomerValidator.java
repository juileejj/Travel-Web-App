package com.neu.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.pojo.Customer;


public class CustomerValidator implements Validator {

	 private Pattern pattern;  
	 private Matcher matcher;  
	  
	 private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"  
	   + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	 private static final  String STRING_PATTERN = "[a-zA-Z]+";  
	@Override
	public boolean supports(Class aClass) {
		 return aClass.equals(Customer.class);
	}
	// email validation in spring  
	 
	@Override
	public void validate(Object obj, Errors errors) {
	Customer customer= (Customer)obj;
	 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.user", "First Name Required");
     ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.user", "Last Name Required");
     ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
     ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId", "error.invalid.emailId", "Email Required");
	
     if (!(customer.getEmailId() != null && customer.getEmailId().isEmpty())) {  
    	   pattern = Pattern.compile(EMAIL_PATTERN);  
    	   matcher = pattern.matcher(customer.getEmailId());  
    	   if (!matcher.matches()) {  
    	    errors.rejectValue("email", "email.incorrect",  
    	      "Enter a correct email");  
    	   }  
    	  }  
     if (!(customer.getFirstName() != null && customer.getFirstName().isEmpty())) {  
    	   pattern = Pattern.compile(STRING_PATTERN);  
    	   matcher = pattern.matcher(customer.getFirstName());  
    	   if (!matcher.matches()) {  
    	    errors.rejectValue("name", "name.containNonChar",  
    	      "Enter a valid first name");  
    	   }  
    	  }  
     if (!(customer.getLastName() != null && customer.getLastName().isEmpty())) {  
  	   pattern = Pattern.compile(STRING_PATTERN);  
  	   matcher = pattern.matcher(customer.getLastName());  
  	   if (!matcher.matches()) {  
  	    errors.rejectValue("name", "name.containNonChar",  
  	      "Enter a valid last name");  
  	   }  
  	  }  
	
	}

}

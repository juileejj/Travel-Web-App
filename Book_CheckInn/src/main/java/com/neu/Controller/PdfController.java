package com.neu.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;


@Controller
public class PdfController {

	@RequestMapping(value = "/booking.pdf", method = RequestMethod.GET)
	public ModelAndView createReport()
	{
		View view = new PdfReportView();
		
		return new ModelAndView(view);
	}
}

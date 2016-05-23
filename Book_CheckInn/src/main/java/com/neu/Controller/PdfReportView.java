package com.neu.Controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;
import com.neu.pojo.Customer;
import com.neu.pojo.Room;


public class PdfReportView extends AbstractPdfView
{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document pdfdoc, PdfWriter pdfwriter, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Font font_helvetica_16_normal_green = new Font(Font.HELVETICA, 16, Font.NORMAL, Color.GREEN); 
		Font font_courier_16_italic_black = new Font(Font.TIMES_ROMAN, 16, Font.NORMAL, Color.BLACK);
		Font font_times_36_bold_black = new Font(Font.TIMES_ROMAN, 30, Font.BOLD, Color.BLACK);
		
		HttpSession session = request.getSession();
		Room booking = null;
		int id = Integer.parseInt(request.getParameter("bookingid"));
		if(null!=session.getAttribute("bookinglist"))
		{
			ArrayList<Room> roomList= (ArrayList<Room>)session.getAttribute("bookinglist");
			for(Room room :roomList)
			{
				if(room.getRoomId()==id)
				{
					Chunk title = new Chunk("Your Booking Information", font_times_36_bold_black);
					Customer customer=(Customer)session.getAttribute("customer");
					Paragraph custname= new Paragraph("Customer Name:-"+customer.getFirstName()+" "+customer.getLastName(),font_courier_16_italic_black);
					Paragraph hotelname = new Paragraph("Hotel Name:-"+room.getHotel().getHotelName(),font_courier_16_italic_black);
					Paragraph status = new Paragraph(room.getBookingStatus(),font_helvetica_16_normal_green);
					Phrase bookingdates= new Phrase("From:-"+room.getBookedFrom()+"To:"+room.getBookedTo(),font_courier_16_italic_black);
					pdfdoc.add(title);
					pdfdoc.add(custname);
					pdfdoc.add(hotelname);
					pdfdoc.add(status);
					pdfdoc.add(bookingdates);
				}
			}
		}
		
	}

	
}

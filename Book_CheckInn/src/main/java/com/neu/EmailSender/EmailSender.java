package com.neu.EmailSender;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class EmailSender {
	   public static boolean EmailSend(String from,String password, String message, String to[])
	    {
	        String host="smtp.gmail.com";
	        Properties properties=System.getProperties();
	        properties.put("mail.smtp.starttls.enable", "true");
	        properties.put("mail.smtp.host", host);
	        properties.put("mail.smtp.user", from);
	        properties.put("mail.smtp.password",password);
//	        properties.put("mail.smtp.port", "465");
	        properties.put("mail.smtp.port","587");
	        properties.put("mail.smtp.auth", "true");
	        
	        Session session =Session.getDefaultInstance(properties,null);
	        MimeMessage mimeMessage= new MimeMessage(session);
	        try
	        {
	            mimeMessage.setFrom(new InternetAddress(from));
	            InternetAddress[] toAddress= new InternetAddress[to.length];
	            for(int i=0;i<to.length;i++)
	            {
	                toAddress[i]=new InternetAddress(to[i]);
	            }
	            for(int i=0;i<toAddress.length;i++)
	            {
	                mimeMessage.addRecipient(RecipientType.TO, toAddress[i]);
	            }
	            mimeMessage.setSubject("Web Tools Travel Site");
	            mimeMessage.setText(message);
	              BodyPart messageBodyPart = new MimeBodyPart();

	         // Now set the actual message
	         messageBodyPart.setText("This is message body");

	        

	        

	            Transport transport = session.getTransport("smtp");
	            transport.connect(host,from,password);
	            transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
	            transport.close();
	            return true;
	        }
	        catch(MessagingException ex)
	        {
	            ex.printStackTrace();
	        }
	        return false;
	    }
}

package com.neu.Util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.neu.pojo.Booking;
import com.neu.pojo.Customer;
import com.neu.pojo.Room;

public class Connect_URL {
	public Customer getUserService(String urlstr, String method) throws IOException {
		URL url;
		url = new URL(urlstr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod(method);
		conn.setRequestProperty("Content-Type", "application/json");
		InputStreamReader in = new InputStreamReader(conn.getInputStream(), Charset.defaultCharset());
		StringBuilder sb = new StringBuilder();
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	    int c;
	    for (c = in.read(); c != '\n' && c != -1 ; c = in.read()) {
	        byteArrayOutputStream.write(c);
	    }
	    if (c == -1 && byteArrayOutputStream.size() == 0) {
	        
	    }
	    String line = byteArrayOutputStream.toString("UTF-8");
//		BufferedReader bufferedReader = new BufferedReader(in);
//		if (bufferedReader != null) {
//			int cp;
//			while ((cp = bufferedReader.read()) != -1) {
//				sb.append((char) cp);
//			}
//			bufferedReader.close();
//		}
//		in.close();
//		String data = sb.toString();
		Customer customer = new Gson().fromJson(line, Customer.class);
		System.out.println(customer);
		conn.disconnect();
		return customer;
	}

	public String callHotelService(String urlstr, String method) throws IOException {
		URL url;
		url = new URL(urlstr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod(method);
		conn.setRequestProperty("Content-Type", "application/json");

		InputStreamReader in = new InputStreamReader(conn.getInputStream(), Charset.defaultCharset());
		StringBuilder sb = new StringBuilder();
		BufferedReader bufferedReader = new BufferedReader(in);
		if (bufferedReader != null) {
			int cp;
			while ((cp = bufferedReader.read()) != -1) {
				sb.append((char) cp);
			}
			bufferedReader.close();
		}
		in.close();
		String data = sb.toString();

		// Customer customer = new Gson().fromJson(data,Customer.class);

		conn.disconnect();
		return data;
	}

	public void callSaveHotel(String urlstr, String method) throws IOException {
		try {
			URL url;
			url = new URL(urlstr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod(method);
			conn.setRequestProperty("Content-Type", "application/json");
			

			InputStreamReader in = new InputStreamReader(conn.getInputStream(), Charset.defaultCharset());
			StringBuilder sb = new StringBuilder();
			BufferedReader bufferedReader = new BufferedReader(in);
			if (bufferedReader != null) {
//				int cp;
//				while ((cp = bufferedReader.read()) != -1) {
//					sb.append((char) cp);
//				}
//				bufferedReader.close();
			}
			in.close();
			String data = sb.toString();

			// Customer customer = new Gson().fromJson(data,Customer.class);

			conn.disconnect();
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		
	}
public String getConnection(String urlstr, String method) throws IOException {
	URL url;
	url = new URL(urlstr);
	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	conn.setDoOutput(true);
	conn.setRequestMethod(method);
	conn.setRequestProperty("Content-Type", "application/json");
	InputStreamReader in = new InputStreamReader(conn.getInputStream(), Charset.defaultCharset());
	StringBuilder sb = new StringBuilder();
	BufferedReader bufferedReader = new BufferedReader(in);
	if (bufferedReader != null) {
		int cp;
		while ((cp = bufferedReader.read()) != -1) {
			sb.append((char) cp);
		}
		bufferedReader.close();
	}
	in.close();
	String data = sb.toString();
	return data;
}

public ArrayList<Room> getBookings(String urlstr,String method) throws IOException
{
	Set<Booking> bookingset = new HashSet<Booking>();
	String data = getConnection(urlstr, method);
	Type type = new TypeToken<ArrayList<Room>>() {
	}.getType();
	Gson gson = new Gson();
	ArrayList<Room> roomlist = gson.fromJson(data.trim(), type);
	return roomlist;
} 

public ArrayList<Customer> getAllUsers(String urlstr,String method) throws IOException
{
	String data = getConnection(urlstr, method);
	Type type = new TypeToken<ArrayList<Customer>>() {
	}.getType();
	Gson gson = new Gson();
	ArrayList<Customer> customers = gson.fromJson(data.trim(), type);
	return customers;
}

public int cancelBooking(String urlstr,String method) throws IOException
{
	String data = getConnection(urlstr, method);
	int result = Integer.parseInt(data);
	return result;
}
}

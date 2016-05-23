package service.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.neu.JSONPojo.HotelJSON;
import com.neu.JSONPojo.JSONDetails;
import com.neu.JSONPojo.LocationJSON;
import com.neu.JSONPojo.PriceJSON;
import com.neu.JSONPojo.StayDatesJSON;
import com.neu.JSONPojo.ValueJSON;

public class RequestConnection {

	public String callService(String urlstr, String method) throws IOException {
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

}

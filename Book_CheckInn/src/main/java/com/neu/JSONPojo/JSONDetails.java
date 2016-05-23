package com.neu.JSONPojo;

import java.util.ArrayList;

public class JSONDetails {

	private int matchingHotelCount;
	private StayDatesJSON stayDates;
	private ArrayList<HotelJSON> hotelList;
	public int getMatchingHotelCount() {
		return matchingHotelCount;
	}
	public void setMatchingHotelCount(int matchingHotelCount) {
		this.matchingHotelCount = matchingHotelCount;
	}
	public StayDatesJSON getStayDates() {
		return stayDates;
	}
	public void setStayDates(StayDatesJSON stayDates) {
		this.stayDates = stayDates;
	}
	public ArrayList<HotelJSON> getHotelList() {
		return hotelList;
	}
	public void setHotelList(ArrayList<HotelJSON> hotelList) {
		this.hotelList = hotelList;
	}
	
}

package com.neu.JSONPojo;

public class HotelJSON {

	private int hotelID;
	private String hotelName;
	private LocationJSON location;
	private String description;
	private PriceJSON price;
	private String detailsurl;
	private String rating;
	private String thumbnailUrl;
	public int getHotelID() {
		return hotelID;
	}
	public void setHotelID(int hotelID) {
		this.hotelID = hotelID;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public LocationJSON getLocation() {
		return location;
	}
	public void setLocation(LocationJSON location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public PriceJSON getPrice() {
		return price;
	}
	public void setPrice(PriceJSON price) {
		this.price = price;
	}
	public String getDetailsurl() {
		return detailsurl;
	}
	public void setDetailsurl(String detailsurl) {
		this.detailsurl = detailsurl;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	
}

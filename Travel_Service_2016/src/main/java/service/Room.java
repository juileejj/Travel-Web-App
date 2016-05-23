package service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="roomtable")
public class Room {

	@Id
	@Column(name="roomid")
	private int roomId;
	@Column(name="bookedfrom")
	private String bookedFrom;
	@Column(name="bookedto")
	private String bookedTo;
	@Column(name="roomtype")
	private String roomType;
	@Column(name="bookstatus")
	private String bookingStatus;
	@Column(name="price")
	private double price;
	 @ManyToOne(fetch=FetchType.EAGER,optional=true)
	    @JoinColumn(name="hotelid")
	private Hotel hotel;
	 
	 @ManyToOne(fetch=FetchType.EAGER,optional=true)
	    @JoinColumn(name="bookingid")
	 private Booking booking;
	 
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getBookedFrom() {
		return bookedFrom;
	}
	public void setBookedFrom(String bookedFrom) {
		this.bookedFrom = bookedFrom;
	}
	public String getBookedTo() {
		return bookedTo;
	}
	public void setBookedTo(String bookedTo) {
		this.bookedTo = bookedTo;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
}

package com.neu.pojo;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="bookingtable")

public class Booking {
	@Id
	@GeneratedValue
	@Column(name="bookingid")
	private int bookingId;
	
	 @OneToMany(fetch=FetchType.LAZY,mappedBy="booking")
	private Set<Room> roomBookingList;
	 
	@OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="paymentid")
	private Payment payment;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="personid",nullable=false)
	private Customer customer;
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public Set<Room> getRoomBookingList() {
		return roomBookingList;
	}
	public void setRoomBookingList(Set<Room> roomBookingList) {
		this.roomBookingList = roomBookingList;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}

package com.neu.pojo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;



@Entity
@Table(name="customertable")
@PrimaryKeyJoinColumn
public class Customer extends Person {


	private Card creditcard;
	
	
	private Set<Booking> bookingList;
	 
	 
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cardnumber",nullable=true)
	public Card getCreditcard() {
		return creditcard;
	}

	public void setCreditcard(Card creditcard) {
		this.creditcard = creditcard;
	}


	public Customer() {
	bookingList = new HashSet<Booking>();
	}

	 @OneToMany(fetch=FetchType.EAGER,mappedBy="customer")
	public Set<Booking> getBookingList() {
		return bookingList;
	}

	public void setBookingList(Set<Booking> bookingList) {
		this.bookingList = bookingList;
	}
	


}

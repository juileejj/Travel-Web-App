package service;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.neu.JSONPojo.PriceJSON;
@Entity
@Table(name="hoteltable")
public class Hotel {

	@Id
	@Column(name="hotelid",unique=true,nullable=false)
	private int hotelId;
	@Column(name="hotelname")
	private String hotelName;
	@Column(name="description")
	private String description;
	@Column(name="detailsurl")
	private String detailsurl;
	@Column(name="rating")
	private String rating;
	@Column(name="thumbnailurl")
	private String thumbnailUrl;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="locationid")
	private Location location;
	@JsonIgnore
	 @OneToMany(fetch=FetchType.LAZY,mappedBy="hotel")
	private Set<Room> roomSet;

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Set<Room> getRoomSet() {
		return roomSet;
	}

	public void setRoomSet(Set<Room> roomSet) {
		this.roomSet = roomSet;
	}


	 
}

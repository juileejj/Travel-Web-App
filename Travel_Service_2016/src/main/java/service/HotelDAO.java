package service;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;

import com.neu.exception.AdException;

public class HotelDAO extends DAO {

	public Hotel insert(int hotelid, String hotelName, String description, String detailsurl, String rating,
			String thumbnailUrl, Location location, double price) throws AdException {
		try {
			begin();
			Hotel hotel = new Hotel();
			hotel.setHotelId(hotelid);
			hotel.setHotelName(hotelName);
			hotel.setDescription(description);
			hotel.setDetailsurl(detailsurl);
			hotel.setRating(rating);
			hotel.setThumbnailUrl(thumbnailUrl);
			hotel.setLocation(location);
			Set<Room> rooms = new HashSet();
			for (int i = 0; i < 5; i++) {
				Room room = new Room();
				room.setRoomId(hotelid + i);
				room.setBookingStatus("No");
				room.setRoomType("Deluxe");
				room.setPrice(price);
				room.setHotel(hotel);
				rooms.add(room);
				getSession().save(room);
			}
			for (int i = 5; i < 10; i++) {
				Room room = new Room();
				room.setRoomId(hotelid + i);
				room.setBookingStatus("No");
				room.setRoomType("Suite");
				room.setPrice(price);
				room.setHotel(hotel);
				rooms.add(room);
				getSession().save(room);
			}
			hotel.setRoomSet(rooms);
			getSession().save(hotel);
			commit();
			return hotel;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new AdException("Exception while creating user: " + e.getMessage());
		}
	}
}

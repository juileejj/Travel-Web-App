package service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.exception.AdException;

public class BookingDAO extends DAO {

	public Booking insertBooking(Booking bookingdetails, Payment paymentdet, Card det, Customer custdet)
			throws AdException {
		try {
			begin();
			paymentdet.setCard(det);
			bookingdetails.setCustomer(custdet);
			bookingdetails.setPayment(paymentdet);
			custdet.getBookingList().add(bookingdetails);

			getSession().save(paymentdet);
			getSession().save(bookingdetails);
			getSession().save(custdet);
			commit();
			return bookingdetails;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new AdException("Exception while creating user: " + e.getMessage());
		}
	}

	public Set<Room> allotRooms(int noofrooms, String roomType, int hotelid) throws AdException {
		Set<Room> roomsAlloted = new HashSet<Room>();
		try {
			begin();
			for (int i = 0; i < noofrooms; i++) {
				Query q = getSession().createQuery(
						"from Room where bookingStatus=:status and roomType = :roomtype and hotelid =:hotelid");
				q.setString("roomtype", roomType);
				q.setString("status", "No");
				q.setInteger("hotelid", hotelid);
				q.setMaxResults(1);
				Room room = (Room) q.uniqueResult();
				if (room != null) {
					roomsAlloted.add(room);
				}
				commit();
				return roomsAlloted;
			}
		} catch (HibernateException ex) {
			rollback();
			throw new AdException("Exception while creating user: " + ex.getMessage());
		}
		return null;
	}

	public boolean updateRooms(Set<Room> roomset, String status, String checkin, String checkout, int bid)
			throws AdException {
		try {
			int result = 0;
			begin();
			for (Room room : roomset) {
				int roomid = room.getRoomId();

				Query q = getSession().createQuery(
						"update Room set bookingStatus =:status,bookedFrom =:checkin,bookedTo=:checkout,bookingid=:bid where roomId=:rid");

				q.setString("status", status);
				q.setString("checkin", checkin);
				q.setString("checkout", checkout);
				q.setInteger("rid", roomid);
				q.setInteger("bid", bid);
				getSession().update(room);
				result = q.executeUpdate();

			}
			commit();
			if (result > 0) {
				return true;
			}
			return false;

		} catch (HibernateException ex) {
			rollback();
			throw new AdException("Exception while creating user: " + ex.getMessage());
		}
	}

	public ArrayList<Room> getBooking(int personid) throws AdException {
		try {
			begin();
			ArrayList<Room> roomlist = new ArrayList<Room>();
			Query q = getSession().createQuery("from Booking where personid = :pid");
			q.setInteger("pid", personid);
			ArrayList<Booking> list = (ArrayList<Booking>) q.list();
			for (Booking booking : list) {
				int id = booking.getBookingId();
				Query query = getSession().createQuery("from Room where bookingid =:bid");
				query.setInteger("bid", id);
				
				Room room = (Room) query.uniqueResult();
				roomlist.add(room);
			}
			commit();
			return roomlist;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new AdException("Exception while creating user: " + e.getMessage());
		}
	}

	public int updateCancelrequest(int bookingid) {
		try {
			begin();
			int result = 0;
			Query q = getSession().createQuery("update Room set bookingStatus =:status,bookedFrom=:bookedfrom,bookedTo=:bookedto where bookingId =:bid");
			q.setString("status","No");
			q.setString("bookedto", null);
			q.setString("bookedfrom", null);
			q.setInteger("bid", bookingid);
			result = q.executeUpdate();
			commit();
			return result;

		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			return 0;
		}
	}

}

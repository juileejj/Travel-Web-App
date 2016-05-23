package service;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.exception.AdException;

public class AdminDAO extends DAO {

	public ArrayList<Customer> getAllCustomers() throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("from Customer");
			ArrayList<Customer> custList = (ArrayList<Customer>) q.list();
			commit();
			return custList;
		} catch (HibernateException e) {
			rollback();
			return null;
		}
	}
}

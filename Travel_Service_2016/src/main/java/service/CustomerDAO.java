package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.context.annotation.Bean;

import com.neu.exception.AdException;


public class CustomerDAO extends DAO {
	
	 public Customer create(String emailId, String password, String firstName, String lastName)
	            throws AdException {
	        try {
	            begin();
	            Customer customer=null;
	           
	            customer= new Customer();
	            customer.setFirstName(firstName);
	            customer.setLastName(lastName);
	            customer.setEmailId(emailId);
	            customer.setPassword(password);
	            getSession().save(customer);	            
	            commit();
	            
	            return customer;
	        } catch (HibernateException e) {
	            rollback();
	            //throw new AdException("Could not create user " + username, e);
	            throw new AdException("Exception while creating user: " + e.getMessage());
	        }
	    }
	 public Customer isValidUser(String emailId, String password)
	            throws AdException {
	        try {
	            begin();
	            Query q = getSession().createQuery("from Customer where emailId = :emailid and password =:password");
	            q.setString("emailid", emailId);
	            q.setString("password", password);
	            Customer customer= (Customer)q.uniqueResult();	            
	            commit();
	            return customer;
	        } catch (HibernateException e) {
	            rollback();
	            //throw new AdException("Could not create user " + username, e);
	            throw new AdException("Exception while creating user: " + e.getMessage());
	        }
	    }
	 public  boolean isValidUserName(String emailId) throws AdException {
		 
		 try {
	            begin();
	            Query q = getSession().createQuery("select emailId from Customer");
	            ArrayList<String> usernames  = (ArrayList<String>)q.list();	            
	            commit();
	            if(usernames!=null)
	            {
	            	for(String username : usernames)
	            	{
	            		if(username.equals(emailId))
	            		{
	            			return false;
	            		}
	            	}
	            	return true;
	            }
	            else
	            {
	            	return true;
	            }
	        } catch (HibernateException e) {
	            rollback();
	            //throw new AdException("Could not create user " + username, e);
	            throw new AdException("Exception while creating user: " + e.getMessage());
	        }
		 
	}
	 public Customer getCustomerById(String custid) throws AdException {
		 try {
	            begin();
//	            String sql = "Select * from customertable where personid = :pid";
//	            SQLQuery q  =getSession().createSQLQuery(sql);
//	            q.addEntity(Customer.class);
//	            q.setLong("pid", id);  
//	            Customer customer= (Customer)q.uniqueResult();	
	            
	            
	            Query q = getSession().createQuery("from Customer where emailid = :pid");
	            q.setString("pid",custid);
	            Customer customer= (Customer)q.uniqueResult();	            
	            commit();
	            return customer;
	        } catch (HibernateException e) {
	            rollback();
	            //throw new AdException("Could not create user " + username, e);
	            throw new AdException("Exception while creating user: " + e.getMessage());
	        }
	}
}

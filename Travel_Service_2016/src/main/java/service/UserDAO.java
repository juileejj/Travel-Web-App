package service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
	 @PersistenceContext
	  EntityManager entityManager;

	   protected Session getCurrentSession()  {
	      return entityManager.unwrap(Session.class);
	   }

	   public Customer test() {
	      Session session = getCurrentSession();

          Customer customer= new Customer();
          customer.setFirstName("edch");
          customer.setLastName("scse");
          customer.setEmailId("efaca");
          customer.setPassword("ddf");
	      session.save(customer);
	      System.out.println(customer);
	      return customer;
	   }
}

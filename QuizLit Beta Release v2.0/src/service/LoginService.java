package service;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import beans.User;
import hibernate.util.HibernateUtility;

public class LoginService {
	public boolean authenticateUser(String email, String password) {
		User user = getUserByEmail(email);
		if(user != null && user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) 
			return true;
		else
			return false;
	}
	
	public User getUserByEmail(String email) {
		Session session = HibernateUtility.openSession();
		Transaction tx = null;
		User user = null;
		
		try {
			tx = session.getTransaction();
			tx.begin();
			@SuppressWarnings("deprecation")
			Criteria cr = session.createCriteria(User.class);
			cr.add(Restrictions.eq("user_email", email));
			tx.commit();
		} catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}
	
	public List<User> getUserList(){
		List<User> list = new ArrayList<User>();
		Session session = HibernateUtility.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			list = session.createQuery("FROM User").list();
			tx.commit();
		} catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
}

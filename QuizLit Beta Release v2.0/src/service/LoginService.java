package service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import beans.User;
import hibernate.util.HibernateUtility;



public class LoginService {
	
	
	public LoginService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean authenticateUser(String email, String password) {
		User user = getUserByEmail(email);
		boolean result  = false;
		try {
			if(user != null && user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) 
				result = true;
		} catch (Exception e) {
			System.out.println("LoginService.authenticateUser Failed: ");
			e.printStackTrace();
		}
		return result;
	}
	
	public User getUserByEmail(String email) {
		Session session = HibernateUtility.openSession();
		Transaction tx = null;
		User user = null;
		
		try {
			tx = session.getTransaction();
			tx.begin();
			
			List<User> users = getUserList();
			Iterator<User> iterator = users.iterator();
			
			while(iterator.hasNext()) {
				User dbUser = iterator.next();
				if(dbUser.getEmail().equals(email)) {
					user = dbUser;
					break;
				}
			}
			
			tx.commit();
		} catch(Exception e) {
			System.out.println("LoginService.getUserByEmail Failed: User not Found Matching that Email.");
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
		List dbUsers = null;
		Session session = HibernateUtility.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			dbUsers = session.createQuery("FROM User").list();
			tx.commit();
		} catch(Exception e) {
			System.out.println("LoginService.getUserList Failed: ");
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return dbUsers;
	}
	
	public boolean isUserAdmin(User user) {
		if(user.getRole().equals("admin"))
			return true;
		else
			return false;
	}
}

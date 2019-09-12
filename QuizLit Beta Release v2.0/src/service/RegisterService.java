package service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.User;
import hibernate.util.HibernateUtility;

public class RegisterService {
	
	//Approach #1 - Store all User objects and check if the new User's email matches another existing User.
	public boolean userExists(User user) {
		List<User> users = new LoginService().getUserList();
		
		Iterator<User> it = users.iterator();
		while(it.hasNext()) {
			User dbUser = it.next();
			if(dbUser.getEmail().equalsIgnoreCase(user.getEmail())) {
				return true;
			}
		}
		return false;
	}
	
	public void registerUser(User user) {
		Session session = HibernateUtility.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(user);
		} catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			System.err.println("RegisterService.registerUser Failed: ");
			e.printStackTrace();
		} finally {
			tx.commit();
			session.close();
		}
	}
}

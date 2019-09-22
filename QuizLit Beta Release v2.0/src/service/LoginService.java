package service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.User;
import hibernate.util.HibernateUtility;

import javax.persistence.*;



public class LoginService {
	
	
	public LoginService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//Hibernate-JPQL search for user from email
	public User getUserFromEmail(String email) {
		
		Session session = HibernateUtility.openSession();
		
		EntityManagerFactory emFac = session.getEntityManagerFactory();
		EntityManager em = emFac.createEntityManager();
		
		Transaction tx = null;
		
		List<User> users = new ArrayList<User>();
		
		try {
			tx = session.beginTransaction();
			Query query = em.createQuery("SELECT u FROM User u WHERE u.email LIKE '" + email + "'");
			users = (List<User>)query.getResultList();
		}catch(Exception e) {
			 System.out.println("LoginService.getUserFromEmail failed:");
			 if(tx != null)
				 tx.rollback();
			 e.printStackTrace();
		}
		finally{
			session.close();
		}
		if(users.isEmpty()) {
			System.out.println("There was no User that matched " + email);
			return null;
		}
		return users.get(0);
	}
	
	//Returns the entire User Table. Use of this method is discouraged.
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
	
	public boolean isAdmin(User user) {
		if(user.getRole().equals("admin"))
			return true;
		else
			return false;
	}
	
}

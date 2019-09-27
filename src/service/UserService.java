package service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.User;
import hibernate.util.HibernateUtility;

public class UserService {

	/**
	 * @author Alex Perez
	 * Given a single User Bean Object, register them into the DB
	 * @param user
	 */
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
	/**
	 * @author Alex Perez
	 * Given a List of Users, register them all into the DB
	 * @param users
	 */
	public void registerUsers(List<User> users) {
		Session session = HibernateUtility.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			for (User user : users) {
				registerUser(user);
			}
		} catch(Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			System.err.println("UserService.registerUsers Failed:");
			e.printStackTrace();
		} finally {
			tx.commit();
			session.close();
		}
	}
	/**
	 * @author Alex Perez
	 * @param user
	 * @return Returns true if the user exists in the DB, false if the User does not exist.
	 */
	public boolean userExists(User user) {
		if(getUserFromEmail(user.getEmail()) != null)
			return true;
		else
			return false;
	}
	/**
	 * @author Alex Perez
	 * Given an email String, 
	 * @param email
	 * @return Returns the User object that has that matching email. If there is no User matching with
	 * that email, returns Null;
	 */
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
	/**
	 * @author Alex Perez
	 * @param id
	 * @return Returns the User object that has that matching id. If there is no User matching with
	 * that id, returns Null;
	 */
	public User getUserFromId(int id) {

		Session session = HibernateUtility.openSession();

		EntityManagerFactory emFac = session.getEntityManagerFactory();
		EntityManager em = emFac.createEntityManager();
		
		Transaction tx = null;

		List<User> users = new ArrayList<User>();

		try {
			tx = session.beginTransaction();
			Query query = em.createQuery("SELECT u FROM User u WHERE u.id = " + id);
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
			System.out.println("There was no User that matched " + id);
			return null;
		}
		return users.get(0);
	}
	/**
	 * @author Alex Perez
	 * 	
	 * @return Returns the a List of Users containing every User within the DB. Use of this method
	 *  is highly discouraged.
	 */
	public List<User> getUserList(){
		List<User> dbUsers = null;
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
	/**
	 * @author Alex Perez
	 * @param user
	 * @return Given a User object, return a boolean true if the User is an admin, false if they are not an admin.
	 */
	public boolean isAdmin(User user) {
		if(user.getRole().equals("admin"))
			return true;
		else
			return false;
	}
}

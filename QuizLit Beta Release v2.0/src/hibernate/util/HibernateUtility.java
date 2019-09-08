package hibernate.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtility {
	private static SessionFactory factory;
	
	static {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		}catch(HibernateException e) {
			System.err.println("Session Factory initialization Failed");
			e.printStackTrace();
		}catch(Exception e) {
			System.err.println("Session Factory initialization Failed");
			e.printStackTrace();
		}
	}
	
	public static Session openSession() {
		return factory.openSession();
	}
}

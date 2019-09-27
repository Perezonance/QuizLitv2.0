package hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtility {
	private static HibernateUtility hui;
	private static SessionFactory factory;
	
	
	private HibernateUtility(){
		try {
			factory = new Configuration().configure().buildSessionFactory();
		}catch(Exception e) {
			System.err.println("Session Factory initialization Failed");
			e.printStackTrace();
		}
	}
	
	public static Session openSession() {
		if(hui == null){
			System.out.println("Creating new HibernateUtility Singleton...");
			hui = new HibernateUtility();
		}
		return factory.openSession();
	}
}

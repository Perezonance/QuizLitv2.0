package service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.Category;
import beans.User;
import hibernate.util.HibernateUtility;

public class CategoryService {
	
	public CategoryService() {
		super();
	}
	
	public List<Category> getCategoryList() {
		List dbCategories = null;
		Session session = HibernateUtility.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			dbCategories = session.createQuery("FROM Category").list();
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
		return dbCategories;
	}
}

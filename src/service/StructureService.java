package service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import beans.QuizStructure;
import hibernate.util.HibernateUtility;

public class StructureService {
	public List<QuizStructure> getStructureList() {
		List<QuizStructure> dbStructures = null;
		Session session = HibernateUtility.openSession();
		Transaction tx = null;
		try {
			tx = session.getTransaction();
			tx.begin();
			dbStructures = session.createQuery("FROM QuizStructure").list();
			tx.commit();
		} catch(Exception e) {
			System.out.println("StructureService.getStructureList Failed: ");
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return dbStructures;
	}
	public QuizStructure getStructureFromName(String structName) {
		Session session = HibernateUtility.openSession();
		
		EntityManagerFactory emFac = session.getEntityManagerFactory();
		EntityManager em = emFac.createEntityManager();
		
		Transaction tx = null;
		
		List<QuizStructure> quizzes = new ArrayList<QuizStructure>();
		try {
			tx = session.beginTransaction();
			Query query = em.createQuery("SELECT q FROM QuizStructure WHERE q.name LIKE '" + structName + "'");
		} catch(Exception e) {
			System.err.println("StructureService.getStructureFromName Failed:");
			if(tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			tx.commit();
			session.close();
		}
		return quizzes.get(0);
	}
	/**
	 * @author Alex Perez
	 * @param String code
	 * @return Returns the QuizStructure object that has that matching quiz access code. If there is no Quiz Structure matching with
	 * that code, returns Null;
	 */
	public QuizStructure getStructureFromCode(String code) {
		
		Session session = HibernateUtility.openSession();
		
		EntityManagerFactory emFac = session.getEntityManagerFactory();
		EntityManager em = emFac.createEntityManager();
		
		Transaction tx = null;
		
		List<QuizStructure> quizzes = new ArrayList<QuizStructure>();
		
		try {
			tx = session.beginTransaction();
			Query query = em.createQuery("SELECT q FROM QuizStructure q WHERE q.accessCode LIKE '" + code  + "';");
			quizzes = (List<QuizStructure>)query.getResultList();
		} catch (Exception e) {
			System.err.println("StructureService.getStructureFromCode Failed:");
			if(tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			tx.commit();
			session.close();
		}
		return quizzes.get(0);
	}
}

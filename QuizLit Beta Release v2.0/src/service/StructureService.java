package service;

import java.util.List;

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
	
	public QuizStructure getStructure(String struct) {
		QuizStructure structure = null;
		List<QuizStructure> structs = getStructureList();
		for (QuizStructure s : structs) {
			if (s.getName().equals(struct))
				structure = s;
		}
		return structure;
	}
	public QuizStructure getStructureFromCode(String code) {
		QuizStructure structure = null;
		List<QuizStructure> structs = getStructureList();
		for (QuizStructure s : structs) {
			if (s.getAccessCode().equalsIgnoreCase(code)) {
				structure = s;
			}
				
		}
		return structure;
	}
}

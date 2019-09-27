package service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import beans.Category;
import beans.Question;
import beans.Quiz;
import beans.QuizStructure;
import beans.User;
import hibernate.util.HibernateUtility;

public class QuizService {
	
	
	public Quiz generateQuiz(User user) {
		return null;
		
	}
	
	public List<Question> genQuestions(QuizStructure struct){
		Session session = (Session) HibernateUtility.openSession();
		List<Question> tempBank = new ArrayList<Question>();
		List<Question> qBank = new ArrayList<Question>();
		List<Question> dbQuestions = (List<Question>)session.createQuery("FROM Question").list();
		
		for (Question question : dbQuestions) {
			if(question.getQuestionCategory().getName().equals(struct.getStructureCategory().getName())) 
				tempBank.add(question);
		}
		
		int questionCount = struct.getQuestionCount();
		int size = tempBank.size();
		int i = 0;
		while(i < questionCount) {
			int num = (int)(Math.random() * size);
			if(!qBank.contains(tempBank.get(num))) {
				qBank.add(tempBank.get(num));
				i++;
			}
		}
		session.close();
		return qBank;
	}
	
	public void createQuiz(Category category ) {
		QuizStructure struct = new QuizStructure();
		struct.setAccessCode("PVQ29Z");
		struct.setQuestionCount(10);
		struct.setStructureCategory(category);
		struct.setTime(10.0);
		
	}
	public void commitQuiz(Quiz quiz) {
		Session session = (Session)HibernateUtility.openSession();
		session.save(quiz);
		session.close();
	}
}

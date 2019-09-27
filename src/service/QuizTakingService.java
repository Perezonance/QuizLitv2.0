package service;

import java.util.List;

import beans.*;

public class QuizTakingService {
	
	static List<Question> questions;
	static int counter = 1;
	
	public QuizTakingService(Quiz quiz) {
		questions = quiz.getQuizQuestions();
	}
	
	public Question next() {
		counter++;
		return questions.get(counter);
	}
	
	public Question back() {
		counter--;
		return questions.get(counter);
	}
	public void endQuiz() {
		
	}
}

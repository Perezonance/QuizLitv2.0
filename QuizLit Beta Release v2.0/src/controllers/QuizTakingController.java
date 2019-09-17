package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.*;

/**
 * Servlet implementation class QuizTakingController
 */
@WebServlet("/QuizTakingController")
public class QuizTakingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int qCounter = 0;
	private boolean[] answers;
	private static int correctAns = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizTakingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		Quiz quiz = (Quiz)session.getAttribute("Quiz");
		
		if(quiz == null) {
			System.out.println("Quiz is Null");
		}
		
		List<Question> qBank = quiz.getQuizQuestions();
		
		if(qCounter == 0) {
			answers = new boolean[qBank.size()];
			correctAns = 0;
		}else {
			//Grade the previous response if it's not the first question
			int selectedAns = Integer.parseInt(request.getParameter("question"));
			
			List<Response> res = (List<Response>)session.getAttribute("Responses");
			if(res.get(selectedAns).isCorrect()) {
				correctAns++;
				System.out.println(correctAns + " Marks Right");
			}
		}
	
		
		
		
		if(qCounter == qBank.size()) {
			qCounter = 0;
			
			session.setAttribute("CorrectAnswers", correctAns);
			
			RequestDispatcher rd = request.getRequestDispatcher("QuizResultsController");
			rd.forward(request, response);
		}else {
			Question question = qBank.get(qCounter);
			
			
			List<Response> responses = new ArrayList<Response>();
			responses.add(new Response(question.getCorrectAnswer(), true));
			responses.add(new Response(question.getWrongAnswer1(), false));
			responses.add(new Response(question.getWrongAnswer2(), false));
			responses.add(new Response(question.getWrongAnswer3(), false));
			
			Collections.shuffle(responses);
			
			session.setAttribute("Question", question);
			session.setAttribute("Responses", responses);
			session.setAttribute("qCount", (qCounter + 1));
			
			qCounter++;
			
			RequestDispatcher rd = request.getRequestDispatcher("QuizPage.jsp");
			rd.forward(request, response);
		}
		
		
		
	}

}

package controllers;

import java.io.IOException;
import java.util.ArrayList;
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
		
		List<Response> responses = new ArrayList<Response>();
		
	
		
		
		if(qCounter == qBank.size()) {
			qCounter = 0;
			
			RequestDispatcher rd = request.getRequestDispatcher("QuizResultsPage.jsp");
			rd.forward(request, response);
		}else {
			Question question = qBank.get(qCounter);
			qCounter++;
			
			session.setAttribute("Question", question);
			
			RequestDispatcher rd = request.getRequestDispatcher("QuizPage.jsp");
			rd.forward(request, response);
		}
		
		
		
	}

}

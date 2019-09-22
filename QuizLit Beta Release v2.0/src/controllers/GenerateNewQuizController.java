package controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.*;
import service.QuizService;

/**
 * Servlet implementation class GenerateNewQuizController
 */
@WebServlet("/GenerateNewQuizController")
public class GenerateNewQuizController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private int questionCounter = 0;
    private static int grade = 0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateNewQuizController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		HttpSession session = request.getSession();
		
		QuizStructure structure = (QuizStructure)session.getAttribute("Structure");
		User user = (User)session.getAttribute("User");
		
		Quiz quiz = new Quiz();
		quiz.setUser(user);
		quiz.setQuizStructure(structure);
		
		List<Question> questions = (List<Question>) new QuizService().genQuestions(structure);
		quiz.setQuizQuestions(questions);
		
		new QuizService().commitQuiz(quiz);
		session.setAttribute("Quiz", quiz);
		
		RequestDispatcher rd = request.getRequestDispatcher("QuizTakingController");
		rd.forward(request, response);
		
	}

}

package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.QuizStructure;
import service.StructureService;

/**
 * Servlet implementation class QuizRowController
 */
@WebServlet("/QuizRowController")
public class QuizRowController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuizRowController() {
        super();
        // TODO Auto-generated constructor stub
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
		
		StructureService structServ = new StructureService();
		List<QuizStructure> structList = structServ.getStructureList();
		Iterator<QuizStructure> it = structList.iterator();
		
		PrintWriter out = response.getWriter();
		
		while(it.hasNext()) {
			QuizStructure stru = (QuizStructure)it.next();
			out.println("<div class='row'>");
				out.println("<div class='cell' data-title='quizName'>" + stru.getName() + "</div>");
				out.println("<div class='cell' data-title='desc'>" + stru.getStructureCategory().getDescription() + "</div>");
				out.println("<div class='cell' data-title='button'><form action='QuizSelectionController' method='post'><input type='submit' name = 'struct' value='" + stru.getName() + "'></form></div>");
			out.println("</div>");
		}
	}

}

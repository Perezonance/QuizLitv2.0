package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.*;
import service.LoginService;
import service.StructureService;

/**
 * Servlet implementation class GuestLoginController
 */
@WebServlet("/GuestLoginController")
public class GuestLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestLoginController() {
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
		response.setContentType("text/html");
		
		HttpSession session = request.getSession();
		
        String accessCode = request.getParameter("code");
        String email = "guest@guest.com";

        LoginService ls = new LoginService();
        StructureService ss = new StructureService();
        
        User user = ls.getUserByEmail(email);
        QuizStructure structure = ss.getStructureFromCode(accessCode);
        
        

        if(ss.getStructureFromCode(accessCode) != null) {
            
        	session.setAttribute("Structure", structure);
        	session.setAttribute("User", user);
        	
        	System.out.println("Test: " + user);
        	System.out.println("Test3: " + structure);
        	
            RequestDispatcher rd = request.getRequestDispatcher("QuizLandingPage.jsp");
            rd.forward(request, response);
        }else {
        	
        	
        	RequestDispatcher rd = request.getRequestDispatcher("QuizLandingPage.jsp");
            rd.forward(request, response);
        }
		
	}

}

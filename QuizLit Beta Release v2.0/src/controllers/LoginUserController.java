package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import service.LoginService;

/**
 * Servlet implementation class LoginUserController
 */
@WebServlet("/LoginUserController")
public class LoginUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginUserController() {
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
		
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		
		LoginService ls = new LoginService();

		if(ls.authenticateUser(email, pass)) {
			User user = ls.getUserByEmail(email);
			if(ls.isUserAdmin(user)) {
				HttpSession session = request.getSession();
				session.setAttribute("LoggedIn", true);
				session.setAttribute("User", user);
				RequestDispatcher rd = request.getRequestDispatcher("AdminDashboard.jsp");
				rd.forward(request, response);
			}else {
				HttpSession session = request.getSession();
				session.setAttribute("LoggedIn", true);
				session.setAttribute("User", user);
				RequestDispatcher rd = request.getRequestDispatcher("UserDashboard.jsp");
				rd.forward(request, response);
			}
		}else {
			request.setAttribute("invalidLogin", true);
			RequestDispatcher rd = request.getRequestDispatcher("LoginPage.jsp");
			rd.forward(request, response);
		}
	}

}

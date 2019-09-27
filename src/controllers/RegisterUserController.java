package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import service.RegisterService;

/**
 * Servlet implementation class RegisterUserController
 */
@WebServlet("/RegisterUserController")
public class RegisterUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUserController() {
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
		System.out.println("Controller Accessed");
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();

        String userFirstName=request.getParameter("user_first_name");
        String userLastName=request.getParameter("user_last_name");
        String userEmail=request.getParameter("user_email");
        String userPassword=request.getParameter("user_password");
        String userPasswordConfirm=request.getParameter("password2");

        User user = new User();

        user.setfName(userFirstName);
        user.setlName(userLastName);
        user.setEmail(userEmail);
        user.setPassword(userPassword);
        user.setRole("student");
        user.setLoginAttempts(5);
        
        RegisterService rs = new RegisterService();
        
        if (!userPassword.equals(userPasswordConfirm)) {
            request.setAttribute("passMatch", "false");
            RequestDispatcher rd = request.getRequestDispatcher("RegisterPage.jsp");
            rd.forward(request, response);
        }
        if(rs.userExists(user) == true){
        	request.setAttribute("emailExists", "true");
            RequestDispatcher rd = request.getRequestDispatcher("RegisterPage.jsp");
            rd.forward(request, response);
        }
        else {
            rs.registerUser(user);
            HttpSession session = request.getSession();
            session.setAttribute("User", user);
            RequestDispatcher rd = request.getRequestDispatcher("UserDashboard.jsp");
            rd.forward(request, response);
        }
	}

}

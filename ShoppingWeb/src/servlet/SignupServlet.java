package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsersDAO;
import model.User;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
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

		request.setCharacterEncoding("utf-8");

		User user = new User();
		String name=null;
		String password1=null;
		String passowrd2=null;	
		
		name = request.getParameter("name");
		password1=request.getParameter("password1");
		passowrd2=request.getParameter("password2");
		
		
		user.setName(name);
		if(password1.equals(passowrd2)){
			user.setPassword(password1);
		}else{
			response.sendRedirect("../LoginPage.jsp");
		}
		
		List<Object> names = UsersDAO.selectName();
		for (Object object : names) {
			if (object.equals(name)) {
				response.sendRedirect("../LoginPage.jsp");
			}
		}
		
		UsersDAO.add(user);
		request.getSession().setAttribute("user", user);
		request.getRequestDispatcher("/GoodsList.jsp").forward(request, response);
	}

}

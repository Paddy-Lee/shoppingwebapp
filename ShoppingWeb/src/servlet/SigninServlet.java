package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsersDAO;
import model.User;

/**
 * Servlet implementation class SigninServlet
 */
@WebServlet("/SigninServlet")
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SigninServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);	
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		User user = new User();
		String name=null;
		String password=null;

		name = request.getParameter("name");
		password=request.getParameter("password");
		
		List<Object []> passwords = UsersDAO.selectPassword();
		for (Object[] object : passwords) {
			if (object[0].equals(name)) {
				if(object[1].equals(password)){
					user.setName(name);
					user.setPassword(password);

					if(request.getParameter("whetherCookie")!=null)
					{
						
						Cookie usernameCookie=new Cookie("name",name);
						Cookie passwordCookie=new Cookie("password",password);
						usernameCookie.setMaxAge(1000);
						passwordCookie.setMaxAge(1000);
						response.addCookie(usernameCookie);
						response.addCookie(passwordCookie);
					}
					else
					{
						Cookie [] cookies = request.getCookies();
						if(cookies!=null&&cookies.length>0)
						{
							for(Cookie c:cookies)
							{
								if(c.getName().equals("name")||c.getName().equals("password"))
								{
									c.setMaxAge(0);
									response.addCookie(c);
								}
							}
						}
					};
					
					request.getSession().setAttribute("user", user);
					request.getRequestDispatcher("/GoodsList.jsp").forward(request, response);
				
				}
			}
		}
		response.sendRedirect("../LoginPage.jsp");
	}

}

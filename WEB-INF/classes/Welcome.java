import javax.servlet.*;
import java.io.*;
import java.util.Enumeration;
import javax.servlet.http.*;

public class Welcome extends HttpServlet{
	
	PrintWriter out;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		out = response.getWriter();
        HttpSession session = request.getSession(false); 
        if (session == null) 
            response.sendRedirect("login"); 
            else {
                String loggedIn = (String) session.getAttribute("loggedIn");
                if (!loggedIn.equals("true")) 
                    response.sendRedirect ("login");
            }
		out.println("<h1>Logged in successfully. Welcome!</h1>");
	}
	

}






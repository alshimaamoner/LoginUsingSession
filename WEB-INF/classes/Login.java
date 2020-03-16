import javax.servlet.*;
import java.io.*;
import java.sql.*;
import java.util.Enumeration;
import javax.servlet.http.*;

public class Login extends HttpServlet{
	
	boolean flag=true;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out;
		response.setContentType("text/html");
		out = response.getWriter();
		out.println("<FORM method='POST'>");
		out.println("<BR> Username: <INPUT TYPE='TEXT' NAME='userName'>");
		out.println("<BR> Password: <INPUT TYPE='PASSWORD' NAME='password'>");
		out.println("<BR> <INPUT TYPE='SUBMIT' VALUE='Submit'>");
		out.println("</FORM>");
        if(!flag){
            out.println("<label color='red'>Invalid username or password</label>");

        }
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
        	PrintWriter out;
         HttpSession session = request.getSession(true);
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");	
         try{
         String myDriver = "com.mysql.jdbc.Driver";
         String myUrl = "jdbc:mysql://localhost:3306/employee";
      Class.forName(myDriver);
      Connection conn = DriverManager.getConnection(myUrl, "root", "password");
      PreparedStatement pstmt;
      ResultSet rs;
    pstmt = conn.prepareStatement("SELECT * FROM user WHERE name=? and pass=?");
    pstmt.setString(1, userName);
    pstmt.setString(2, password);                            
    rs = pstmt.executeQuery();       
   if (rs.next()) {   
       flag=true;
       session.setAttribute("loggedIn", new String("true")); 

    response.sendRedirect("welcome");
    
    }else{
        response.sendRedirect("login");
           flag=false;
   }
    rs.close();                                     
   pstmt.close();                   
	}catch(Exception e){

            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
             
         }

}
                                 }





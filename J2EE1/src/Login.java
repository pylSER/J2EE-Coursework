import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/Login")
public class Login extends HttpServlet{
	String loginfilepath="";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		String wrongpsw=request.getParameter("wrongpsw");
		
		if(wrongpsw==null){
			loginfilepath=getServletContext().getRealPath("/")+"login.html";
		}else {
			loginfilepath=getServletContext().getRealPath("/")+"loginfalse.html";
		}
		
		response.setCharacterEncoding("UTF-8");
		try {
			
			PrintWriter out= response.getWriter();

			
			 File file = new File(loginfilepath);  
		     BufferedReader reader = null;  
		     
		     InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8"); 
		     
		     reader = new BufferedReader(isr);  
		     String tempString = null;  
		     while ((tempString = reader.readLine()) != null) { 
	    	
	            out.println(tempString);
	                      
	          }  
		     reader.close();

			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		if(request.getParameter("wrongpsw")!=null){
			doGet(request, response);
			return;
		}
		
		
		
		
		ServletContext application =this.getServletContext();  
		RequestDispatcher rd = application.getRequestDispatcher("/Info");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	

}

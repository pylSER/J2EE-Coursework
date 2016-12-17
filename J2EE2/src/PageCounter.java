import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class PageCounter implements ServletContextListener,ServletContextAttributeListener{
	String counterfilepath="counter.txt";
	
	
	@Override
	public void attributeAdded(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {  //write counter back whenever server shutdown
		counterfilepath=arg0.getServletContext().getRealPath("/")+"counter.txt";
		try{
		      int data = (int) arg0.getServletContext().getAttribute("totalCount");

		      String datastr=""+data;
		      
		      File file =new File(counterfilepath);

		      //true = append file
		      FileWriter fileWritter = new FileWriter(counterfilepath,false);
		             BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		             bufferWritter.write(datastr);
		             
		             bufferWritter.flush();
		             bufferWritter.close();

		         System.out.println("Done");

		     }catch(IOException e){
		      e.printStackTrace();
		     }
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			
			counterfilepath=sce.getServletContext().getRealPath("/")+"counter.txt";
			 File file = new File(counterfilepath);  
		     BufferedReader reader = null;  
		     
		     InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8"); 
		     
		     reader = new BufferedReader(isr);  
		     String tempString = null;  
		     tempString = reader.readLine();
		     reader.close();
		     
		     tempString=tempString.trim();
		     
		     int count=Integer.parseInt(tempString);
		     
		     ServletContext context=sce.getServletContext();
		     context.setAttribute("totalCount", count);
		     context.setAttribute("visitorCount", 0);
		     context.setAttribute("userCount", 0);
		     
			System.out.println("counter loaded.count is "+count+" app inited.");
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     

		
	}

}

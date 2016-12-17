import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CountGetter")
public class CounterGetter extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		int total=(int) getServletContext().getAttribute("totalCount");
		int user=(int) getServletContext().getAttribute("userCount");
		int visitor=(int) getServletContext().getAttribute("visitorCount");
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		
		String json="{\"total\":"+total+",\"user\":"+user+",\"visitor\":"+visitor+"}";
		
		PrintWriter out = null;
		try {
		    out = response.getWriter();
		    out.write(json);
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    if (out != null) {
		        out.close();
		    }
		}
		
		
		
	}
	
	
}

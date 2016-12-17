import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class WarpperedRequest extends HttpServletRequestWrapper{

	public WarpperedRequest(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
		
		if(request.getParameter("email")==null){
			return;
		}
		
		String name=request.getParameter("email");
	
		
		// add a new attr to save name in utf8 format
		
		try {
			name=new String(name.getBytes("iso-8859-1"),"utf-8"); 
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		
		request.setAttribute("realname", name);
		
		
		
	}



}

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter("/Login")
public class ChineseFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}   

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		
		if(request instanceof HttpServletRequest && response instanceof HttpServletResponse){
			HttpServletRequest request2=(HttpServletRequest) request;
			
			WarpperedRequest wRequest=new WarpperedRequest(request2);
			System.out.println(wRequest.getAttribute("realname"));
			
			chain.doFilter(wRequest, response);
			
		}else{
			chain.doFilter(request, response);
		}
		
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
@WebListener
public class LogoutListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("adding a session");
		
		addLoginNum(arg0);
	}

	@Override// minus user num when session is invalidated or expired
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("destroying a session");
		minusUserNum(arg0);
	}
	
	
	private synchronized void addLoginNum(HttpSessionEvent se){// user +1
		int usercount=(int) se.getSession(). getServletContext().getAttribute("userCount");
		int vistorcount=(int)  se.getSession().getServletContext().getAttribute("visitorCount");
		int historycount=(int) se.getSession(). getServletContext().getAttribute("totalCount");
		
		usercount++;
		
		
		 se.getSession().getServletContext().setAttribute("userCount", usercount);
		
		System.out.println("total"+historycount+",visitor"+vistorcount+",user"+usercount);
	}

	
	
	
	private synchronized void minusUserNum(HttpSessionEvent se){//user -1
		int usercount=(int) se.getSession().getServletContext().getAttribute("userCount");
		int vistorcount=(int) se.getSession().getServletContext().getAttribute("visitorCount");
		int historycount=(int) se.getSession().getServletContext().getAttribute("totalCount");
		usercount--;
	
		
		
		se.getSession().getServletContext().setAttribute("userCount", usercount);
	
		System.out.println("total"+historycount+",visitor"+vistorcount+",user"+usercount);

		
	}

}

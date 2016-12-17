import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.sql.DataSource;


@WebServlet("/Info")
public class Info extends HttpServlet{
	Properties properties =null;
	Context context=null;
	DataSource dSource=null;
	Connection connection=null;
	
	String infofilepath="";
	
	
	
	@Override
	public void init() throws ServletException {
		properties = new Properties();
		properties.put(javax.naming.Context.PROVIDER_URL, "jnp:///");
		properties.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY,
			"org.apache.naming.java.javaURLContextFactory");
		try {
			context=new InitialContext(properties);
			dSource=(DataSource) context.lookup("java:comp/env/jdbc/J2EE1");
			connection=dSource.getConnection();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		System.out.println("info get:");
		
		
		
		if(request.getSession().getAttribute("id")==null){
			//back to login
			try {
				response.sendRedirect("/J2EE2/Login");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			//show info
			
			
			response.setCharacterEncoding("UTF-8");
			infofilepath=getServletContext().getRealPath("/")+"info.html";
			
			String userid=(String) request.getSession().getAttribute("id");
			
//			String userid=(String) request.getAttribute("realname");
			
			System.out.println("ssssid"+userid);
			
			try {
				PreparedStatement pStatement=connection.prepareStatement("select people,avgscore,examname,year,info,score "
						+ "from Student s,Exam e,ExtraInfo exi,(select examid,count(*) as people,avg(score) as avgscore "
						+ "from ExtraInfo group by examid) as Stat "
						+ "where s.id=exi.studentid and exi.examid=e.id and Stat.examid=e.id and (s.id=? or s.name=?)  order by year desc ;");
				pStatement.setString(1, userid);
				pStatement.setString(2, userid);
				
				
				ResultSet resultSet=pStatement.executeQuery();
				

				
				
				PrintWriter out= response.getWriter();

				
				 File file = new File(infofilepath);  
			     BufferedReader reader = null;  
			     
			     InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8"); 
			     
			     reader = new BufferedReader(isr);  
			     String tempString = null;  
			     
			     int i=0;
			     while ((tempString = reader.readLine()) != null) { 
			    	 
			    	 if(i==54){
			    		 i++;
			    		 out.println(userid);
		            	 continue;
		             }
			    	 
			    	 if(i==71){
			    		 break;
			    	 }
		            out.println(tempString);
		             i++;     
		          }  
			     reader.close();
			     
			     
				while(resultSet.next()){
					out.println(generateCard(resultSet.getString("examname"), resultSet.getInt("year"), resultSet.getInt("score"), resultSet.getString("info"),resultSet.getInt("people"),resultSet.getDouble("avgscore")));
					
				}
			     
			     
				String footer="</div>  <div class=\"column\"> <p></p> <p></p> </div> </div> "
						+ "<div class=\"ui small modal\" id=\"modal\"> <div class=\"header\">在线人数</div>   <div class=\"content\">"
						+ "<p >历史总访问人数：<span id=\"total\"></span></p> <p >目前已登录人数：<span id=\"user\"></span></p>  <p >目前游客人数：<span id=\"visitor\"></span></p>"
						+ "</div>  <div class=\"actions\"><div class=\"ui positive right icon button small\" style=\"background-color: #0076d9;\">关闭 </div> </div> </div>"
						+ "   </body> <script src=\"js/exit.js\"></script></html>";
				
				out.println(footer);
				
// if close
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			
		}
		
		
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
			
		String id=(String) request.getAttribute("realname");
		String psw=request.getParameter("password");
		
		

			
			if(checkUser(id, psw)){
				//add session
				HttpSession session=request.getSession(true);
				session.setAttribute("id", id);
				session.setMaxInactiveInterval(900);// destroy after 15 min 
				
				
				try {
					minusVisitorNum();
					response.sendRedirect("/J2EE2/Info");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}else {
				request.setAttribute("wrongpsw", "true");
				
				ServletContext application =this.getServletContext();  
				RequestDispatcher rd = application.getRequestDispatcher("/Login?wrongpsw=true");
				
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
	
	
	private String generateCard(String exam,int year,int score,String info,int people,double avg){
		String examname=exam+"("+year+")";
		String icon="";
		
		if(score>=60){
			icon="<i style=\"float: right;color: #90ee90\" class=\"checkmark icon\"></i>";
		}else{
			icon="<i style=\"float: right;color: #ff0000\" class=\"warning circle icon\"></i>";
		}
		
		
		
		String reString=" <div class=\"ui card\"  style=\"display: inline-block;height:270px;vertical-align: top;margin-left: 40px\">";
		reString+="  <div class=\"content\">";
		reString+="<div class=\"header\">";
		reString+=examname;
		reString+=icon;
		reString+="</i></div></div><div class=\"content\"><div class=\"ui sub \">成绩: ";
		reString+=score;
		reString+="</div><div class=\"ui sub \">平均分: ";
		reString+=avg;
		reString+="</div><div class=\"ui sub \">考试人数: ";
		reString+=people;
		reString+="</div><br/><div class=\"ui small feed\">";
		reString+=generateEvent(info);
		reString+="</div></div></div>";
		
		
		
		
		return reString;
	}
	
	private String generateEvent(String info){
		String anString="";
		
		String[] reStrings=info.split(";");
		
		for (int i = 0; i < reStrings.length; i++) {
			String temp="<div class=\"event\"> <div class=\"content\"> <div class=\"summary\">";
			
			String string = reStrings[i];
			
			temp+=string;
			
			temp+="</div></div></div>";
			
			anString+=temp;
		}
		
		
		return anString;
	}
	
	private boolean checkUser(String id,String psw){
		try {
			
			
			PreparedStatement pStatement=connection.prepareStatement("select count(*) from Student where (id=? and password=?) or (name=? and password=?) ");
			pStatement.setString(1, id);
			pStatement.setString(2, psw);
			pStatement.setString(3, id);
			pStatement.setString(4, psw);
			
			ResultSet resultSet=pStatement.executeQuery();
			
			while(resultSet.next()){
		
				if(resultSet.getInt("count(*)")==0){
					return false;
				}else {
					return true;
				}
				

			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	
	
	
	
	
	private synchronized void minusLoginNum(){// user-1
		int usercount=(int) getServletContext().getAttribute("userCount");
		int vistorcount=(int) getServletContext().getAttribute("visitorCount");
		int historycount=(int) getServletContext().getAttribute("totalCount");
		
		usercount--;
		
		
		getServletContext().setAttribute("userCount", usercount);
		
		System.out.println("total"+historycount+",visitor"+vistorcount+",user"+usercount);
	}
	
	private synchronized void minusVisitorNum(){//vistor -1 
		int usercount=(int)getServletContext().getAttribute("userCount");
		int vistorcount=(int) getServletContext().getAttribute("visitorCount");
		int historycount=(int)  getServletContext().getAttribute("totalCount");
		
		vistorcount--;

		 getServletContext().setAttribute("visitorCount", vistorcount);
		
		System.out.println("total"+historycount+",visitor"+vistorcount+",user"+usercount);
	}
	

}

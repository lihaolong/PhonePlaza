package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import service.DologinService;
@WebServlet("/DologinServlet")
public class DologinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DologinServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(userName+password);
		
		DologinService dologin = new DologinService();
		//System.out.println(dologin.isUser(userName, password));
		/*if(dologin.isUser(userName, password)){
			response.sendRedirect("index.html");
		}else{
			response.getWriter().write(userName);
		}*/
		JSONObject json = new JSONObject();
		if(dologin.isUser(userName, password)){
			try {
				json.put("username", userName);
				json.put("islogin", true);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		response.getWriter().write(json.toString());
		}
		else{
			try {
				json.put("islogin", false);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			response.getWriter().write(json.toString());
		}
	}
}

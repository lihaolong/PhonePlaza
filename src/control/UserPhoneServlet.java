package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import service.UserPhoneService;
@WebServlet("/UserPhoneServlet")
public class UserPhoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UserPhoneServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String userName = request.getParameter("username");
			String phoneName = request.getParameter("phonename");
			System.out.println(userName+phoneName);
			
			UserPhoneService userPhoneService = new UserPhoneService();
			System.out.println(userPhoneService.isExit(userName, phoneName));
			
		    if(!userPhoneService.isExit(userName, phoneName)){
		    	JSONObject json = new JSONObject();
		    	try {
					userPhoneService.add(userName, phoneName);
					json.put("isexit", false);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				}
		    	response.setCharacterEncoding("utf-8");
		    	response.getWriter().write(json.toString());
		    }
		    else{
		    	JSONObject json = new JSONObject();
		    	try {
					json.put("isexit", true);
					json.put("username", userName);
				} catch (JSONException e) {
					e.printStackTrace();
				}
		    	System.out.println(json.toString());
		    	response.getWriter().write(json.toString());
		    }
	}

}

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

import DAO.UserPhoneDAO;
@WebServlet("/DeleteUserPhoneServlet")
public class DeleteUserPhoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DeleteUserPhoneServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phonename = request.getParameter("phonename");
		String username = request.getParameter("username");
		
		UserPhoneDAO userPhoneDAO = new UserPhoneDAO();
		JSONObject json = new JSONObject();
		try {
			if(!userPhoneDAO.delete(username, phonename)){
				json.put("del", true);
			}
			else{
				json.put("del", false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		response.getWriter().write(json.toString());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;

import DAO.UrlDAO;
import DAO.UserPhoneDAO;
@WebServlet("/GetCollectionURLServlet")
public class GetCollectionURLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public GetCollectionURLServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		System.out.println("collection:"+username);
		String phonebrand = null;
		UserPhoneDAO userPhoneDAO = new UserPhoneDAO();
		UrlDAO urlDAO = new UrlDAO();
		JSONArray arr = new JSONArray();
		
		try {
			phonebrand = userPhoneDAO.queryMaxPhonebrand(username);
			arr = urlDAO.queryByPhone(phonebrand, 8);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(arr.toString());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

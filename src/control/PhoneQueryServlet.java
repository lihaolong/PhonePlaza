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

import DAO.PhoneInfoDAO;
@WebServlet("/PhoneQueryServlet")
public class PhoneQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PhoneQueryServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String info1 = request.getParameter("info1");
		String info2 = request.getParameter("info2");
		String info3 = request.getParameter("info3");
		PhoneInfoDAO phoneInfoDAO = new PhoneInfoDAO();
		
		
		
		JSONArray json = new JSONArray();
		try {
			json = phoneInfoDAO.querylist(info1,info2,info3);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		System.out.println(info1+info2+info3);
		response.getWriter().write(json.toString());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

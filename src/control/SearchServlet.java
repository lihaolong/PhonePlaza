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
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SearchServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String text = request.getParameter("text");
		System.out.println("text:"+text);
		PhoneInfoDAO phoneInfoDAO = new PhoneInfoDAO();
		JSONArray jsonArr = new JSONArray();
		try {
			jsonArr = phoneInfoDAO.querySearch(text, text);
		} catch (SQLException | JSONException e) {
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonArr.toString());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

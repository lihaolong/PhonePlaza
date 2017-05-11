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
@WebServlet("/GetUrlInfoServlet")
public class GetUrlInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public GetUrlInfoServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String phonename = request.getParameter("phonename");
		String phonebrand = request.getParameter("phonebrand");
		
		
		UrlDAO urlDAO = new UrlDAO();
		JSONArray jsonArr = new JSONArray();
		try {
			if(urlDAO.countUrl(phonename)<3){
				jsonArr = urlDAO.queryByPhone(phonebrand);
			}else{
				jsonArr =	urlDAO.queryByPhone(phonename);
			}
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

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
@WebServlet("/URLServlet")
public class URLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public URLServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获得top4 URL信息
		UrlDAO urlDao = new UrlDAO();
		JSONArray jsonArray = new JSONArray();
		
		//处理首页URL请求
		try {
			jsonArray = urlDao.queryTop();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println(jsonArray.toString());
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonArray.toString());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

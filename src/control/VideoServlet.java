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

import DAO.FviewUrlDAO;
import DAO.NaYanUrlDAO;
import DAO.VideoUrlDAO;
import DAO.ZealerUrlDAO;
@WebServlet("/VideoServlet")
public class VideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public VideoServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VideoUrlDAO videoUrlDAO = new VideoUrlDAO();
		ZealerUrlDAO zealerUrlDAO = new ZealerUrlDAO();
		NaYanUrlDAO nayanUrlDAO = new NaYanUrlDAO();
		FviewUrlDAO fviewUrlDAO = new FviewUrlDAO();
		JSONArray jsonArray = new JSONArray();
		try {
			
			jsonArray.put(videoUrlDAO.queryNewDami());
			jsonArray.put(zealerUrlDAO.queryNewZealer());
			jsonArray.put(nayanUrlDAO.queryNewNayan());
			jsonArray.put(fviewUrlDAO.queryNewFview());
		} catch (SQLException | JSONException e) {
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		System.out.println(jsonArray.toString());
		response.getWriter().write(jsonArray.toString());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

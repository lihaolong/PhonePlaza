package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import DAO.PhoneInfoDAO;
import model.PhoneInfo;

@WebServlet("/PhoneServlet")
public class PhoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PhoneServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String phoneName = request.getParameter("phoneName");

		PhoneInfoDAO phoneInfoDAO = new PhoneInfoDAO();
		PhoneInfo phoneInfo = new PhoneInfo();
		JSONObject phoneJson = new JSONObject();

		try {
			phoneInfo = phoneInfoDAO.query(phoneName);
			phoneJson = creatJson(phoneInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		System.out.println(phoneName);
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.write(phoneJson.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public JSONObject creatJson(PhoneInfo phoneInfo) throws JSONException {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("phonebrand", phoneInfo.getPhoneBrand());
		jsonObj.put("selltime", phoneInfo.getSellTime());
		jsonObj.put("screensize", String.valueOf(phoneInfo.getScreenSize()));
		jsonObj.put("touchid", phoneInfo.isTouchID());
		return jsonObj;
	}

}

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
import model.CameraInfo;
import model.CpuInfo;
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
		CpuInfo cpuInfo = new CpuInfo();
		CameraInfo cameraInfo = new CameraInfo();
		JSONObject phoneJson = new JSONObject();

		try {
			phoneInfo = phoneInfoDAO.query(phoneName);
			cpuInfo = phoneInfoDAO.queryCpu(phoneName);
			cameraInfo = phoneInfoDAO.queryCamera(phoneName);
			phoneJson = creatJson(phoneInfo,cpuInfo,cameraInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		System.out.println(phoneName);
		System.out.println(cpuInfo.getCpuBrand());
		System.out.println(cameraInfo.getCameraName());
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.write(phoneJson.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public JSONObject creatJson(PhoneInfo phoneInfo,CpuInfo cpuInfo,CameraInfo cameraInfo) throws JSONException {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("phonename", phoneInfo.getPhoneName());
		jsonObj.put("phonebrand", phoneInfo.getPhoneBrand());
		jsonObj.put("selltime", phoneInfo.getSellTime());
		jsonObj.put("weight", phoneInfo.getWeight());
		jsonObj.put("thickness", phoneInfo.getThickness());
		jsonObj.put("price", phoneInfo.getPrice());
		jsonObj.put("ram", phoneInfo.getRam());
		jsonObj.put("rom", phoneInfo.getRom());
		jsonObj.put("touchid", phoneInfo.isTouchID());
		jsonObj.put("baterry", phoneInfo.getBattery());
		jsonObj.put("nettype", phoneInfo.getNettype());
		jsonObj.put("screensize", String.valueOf(phoneInfo.getScreenSize()));
		jsonObj.put("screenmaterial", phoneInfo.getScreenMaterial());
		jsonObj.put("screenpx", phoneInfo.getScreenPx());
		jsonObj.put("cpuname", cpuInfo.getCpuName());
		jsonObj.put("cpubrand", cpuInfo.getCpuBrand());
		jsonObj.put("cpucorenum", cpuInfo.getCpuCoreNum());
		jsonObj.put("cpuhz", cpuInfo.getCpuHz());
		jsonObj.put("gpu", cpuInfo.getGpu());
		jsonObj.put("cpuprocess", cpuInfo.getCpuProcess());
		jsonObj.put("cameraname", cameraInfo.getCameraName());
		jsonObj.put("camerapx", cameraInfo.getCameraPx());
		jsonObj.put("supportois", cameraInfo.isSupportois());
		jsonObj.put("sperture", cameraInfo.getSperture());
		return jsonObj;
	}

}

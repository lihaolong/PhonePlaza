package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
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
		String phoneNameother = request.getParameter("phoneNameother");

		PhoneInfoDAO phoneInfoDAO = new PhoneInfoDAO();
		PhoneInfo phoneInfo = new PhoneInfo();
		CpuInfo cpuInfo = new CpuInfo();
		CameraInfo cameraInfo = new CameraInfo();

		PhoneInfo otherphoneInfo = new PhoneInfo();
		CpuInfo othercpuInfo = new CpuInfo();
		CameraInfo othercameraInfo = new CameraInfo();
		JSONObject phoneJson = new JSONObject();
		JSONObject otherphoneJson = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		try {
			phoneInfo = phoneInfoDAO.query(phoneName);
			cpuInfo = phoneInfoDAO.queryCpu(phoneName);
			cameraInfo = phoneInfoDAO.queryCamera(phoneName);
			phoneJson = creatJson(phoneInfo,cpuInfo,cameraInfo);
			jsonArray.put(phoneJson);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		System.out.println(phoneName);
		System.out.println(phoneNameother);
		System.out.println(phoneJson);
		if(phoneNameother==null){
		out.write(phoneJson.toString());
		}else{
			try {
				otherphoneInfo = phoneInfoDAO.query(phoneNameother);
				othercpuInfo = phoneInfoDAO.queryCpu(phoneNameother);
				othercameraInfo = phoneInfoDAO.queryCamera(phoneNameother);
				otherphoneJson = creatJson(otherphoneInfo,othercpuInfo,othercameraInfo);
				jsonArray.put(otherphoneJson);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			out.write(jsonArray.toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
//根据Javabean创建json对象
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

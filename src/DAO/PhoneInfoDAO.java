package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mysql.jdbc.PreparedStatement;

import model.CameraInfo;
import model.CpuInfo;
import model.PhoneInfo;

public class PhoneInfoDAO {

	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/phoneplaza";
	public static String sqlByphonebrand = "select phonename,selltime,price from phoneinfo where phonebrand = ?";

	private static Connection getCon() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "root", "123456");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public PhoneInfo query(String phoneName) throws SQLException {
		PhoneInfo phoneInfo = new PhoneInfo();
		PreparedStatement pst = (PreparedStatement) getCon()
				.prepareStatement("select * from phoneinfo where phonename = ?");
		pst.setString(1, phoneName);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			phoneInfo.setPhoneName(rs.getString("phonename"));
			phoneInfo.setPhoneBrand(rs.getString("phonebrand"));
			;
			phoneInfo.setPrice(rs.getInt("price"));
			phoneInfo.setWeight(rs.getString("weight"));
			phoneInfo.setScreenSize(rs.getFloat("screensize"));
			phoneInfo.setScreenPx(rs.getString("screenpx"));
			phoneInfo.setScreenMaterial(rs.getString("screenmaterial"));
			phoneInfo.setSellTime(rs.getDate("selltime"));
			phoneInfo.setThickness(rs.getString("thickness"));
			phoneInfo.setTouchID(rs.getBoolean("touchid"));
			phoneInfo.setRam(rs.getString("ram"));
			phoneInfo.setRom(rs.getString("rom"));
			phoneInfo.setBattery(rs.getString("battery"));
			phoneInfo.setNettype(rs.getString("nettype"));
		}
		return phoneInfo;
	}

	public CpuInfo queryCpu(String phoneName) throws SQLException {
		CpuInfo cpuInfo = new CpuInfo();
		PreparedStatement pst = (PreparedStatement) getCon().prepareStatement(
				"select * from cpuinfo where cpuinfo.cpuname in (select cpuinfo from phonecpucamera where phonename = ?)");
		pst.setString(1, phoneName);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			cpuInfo.setCpuName(rs.getString("cpuname"));
			cpuInfo.setCpuCoreNum(rs.getInt("cpucorenum"));
			cpuInfo.setCpuHz(rs.getString("cpuhz"));
			cpuInfo.setGpu(rs.getString("gpu"));
			cpuInfo.setCpuProcess(rs.getString("cpuprocess"));
			cpuInfo.setCpuBrand(rs.getString("cpubrand"));
		}
		System.out.println(cpuInfo.getGpu());
		return cpuInfo;
	}

	public CameraInfo queryCamera(String phoneName) throws SQLException {
		CameraInfo cameraInfo = new CameraInfo();
		PreparedStatement pst = (PreparedStatement) getCon().prepareStatement(
				"select * from  camerainfo where camerainfo.cameraname in (select camerainfo from phonecpucamera where phonename = ?)");
		pst.setString(1, phoneName);
		ResultSet rs = pst.executeQuery();
		while(rs.next()){
			cameraInfo.setCameraName(rs.getString("cameraname"));
			cameraInfo.setCameraPx(rs.getString("camerapx"));
			cameraInfo.setSperture(rs.getString("sperture"));
			cameraInfo.setSupportois(rs.getBoolean("supportois"));
		}
		System.out.println(cameraInfo.getSperture());
		return cameraInfo;
	}
	
	public JSONArray querylist(String info) throws SQLException, JSONException{
		JSONArray jsonArray = new JSONArray();
		PreparedStatement pst = (PreparedStatement) getCon().prepareStatement(sqlByphonebrand);
		pst.setString(1, info);
		ResultSet rs = pst.executeQuery();
		//System.out.println(rs.next());
		while(rs.next()){
			JSONObject jsonLan = new JSONObject();
			jsonLan.put("phonename", rs.getString("phonename"));
			jsonLan.put("selltime", rs.getDate("selltime"));
			jsonLan.put("price", rs.getInt("price"));
			jsonArray.put(jsonLan);
		}
		return jsonArray;
	}
}

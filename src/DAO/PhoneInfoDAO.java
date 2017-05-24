package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mysql.jdbc.PreparedStatement;

import model.CameraInfo;
import model.CpuInfo;
import model.PhoneInfo;

public class PhoneInfoDAO {
	private static String sqlByphonebrand = "select phonename,selltime,price from phoneinfo where phonebrand = ?";
	private static String sqlAll = "select phonename,selltime,price from phoneinfo";
	private static String sqlPrice = "select phonename,selltime,price from phoneinfo where price>=? and price<=?";
	private static String sqlCpu = "select phonename,price,selltime from phoneinfo where phonename in (select phonename from phonecpucamera where cpuinfo in (select cpuname from cpuinfo where cpubrand = ?))";
	private static String sqlScreenSize = "select phonename,selltime,price from phoneinfo where screensize>=? and screensize<=?";
	private static String sqlCollection = "SELECT p.phonename,p.selltime,p.price FROM phoneinfo p LEFT JOIN userphone u ON p.phonename = u.phonename WHERE u.username=?";
	//模糊匹配手机型号和手机品牌
	private static String sqlSearch = "select phonename,selltime,price from phoneinfo where phonename like concat('%',?,'%') or phonebrand like concat ('%',?,'%')";
	//根据手机型号查询品牌
	private static String sqlByPhonename = "select phonebrand from phoneinfo where phonename = ?";
	
	public PhoneInfo query(String phoneName) throws SQLException {
		PhoneInfo phoneInfo = new PhoneInfo();
		PreparedStatement pst = (PreparedStatement) ClientDB.getCon()
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
		PreparedStatement pst = (PreparedStatement) ClientDB.getCon().prepareStatement(
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
		PreparedStatement pst = (PreparedStatement) ClientDB.getCon().prepareStatement(
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
	
	public JSONArray querylist(String info1,String info2,String info3) throws SQLException, JSONException{
		JSONArray jsonArray = new JSONArray();
		PreparedStatement pst = null;
		//根据标志位查询对应数据
		int tagi = Integer.parseInt(info2);
		switch (tagi) {
		case 1:
			pst = (PreparedStatement) ClientDB.getCon().prepareStatement(sqlByphonebrand);
			pst.setString(1, info1);
			break;
		case 2:
			pst = (PreparedStatement) ClientDB.getCon().prepareStatement(sqlAll);
			break;
		case 3:
			pst = (PreparedStatement) ClientDB.getCon().prepareStatement(sqlPrice);
			pst.setString(1, info1);
			pst.setString(2, info3);
			break;
		case 4:
			pst = (PreparedStatement) ClientDB.getCon().prepareStatement(sqlCpu);
			pst.setString(1, info1);
			break;
		case 5:
			pst = (PreparedStatement) ClientDB.getCon().prepareStatement(sqlScreenSize);
			pst.setString(1, info1);
			pst.setString(2, info3);
			break;
		case 6:
			pst = (PreparedStatement) ClientDB.getCon().prepareStatement(sqlCollection);
			pst.setString(1, info1);
			break;
		}
		
		ResultSet rs = pst.executeQuery();
		while(rs.next()){
			JSONObject jsonLan = new JSONObject();
			jsonLan.put("phonename", rs.getString("phonename"));
			jsonLan.put("selltime", rs.getDate("selltime"));
			jsonLan.put("price", rs.getInt("price"));
			jsonArray.put(jsonLan);
		}
		System.out.println(jsonArray);
		return jsonArray;
	}
	//根据手机名称或品牌查询手机
	public JSONArray querySearch(String phonename,String phonebrand) throws SQLException, JSONException{
		JSONArray json = new JSONArray();
		PreparedStatement pst = (PreparedStatement) ClientDB.getCon().prepareStatement(sqlSearch);
		pst.setString(1, phonename);
		pst.setString(2, phonebrand);
		ResultSet rs = pst.executeQuery();
		while(rs.next()){
			JSONObject jsonLan = new JSONObject();
			jsonLan.put("phonename", rs.getString("phonename"));
			jsonLan.put("selltime", rs.getDate("selltime"));
			jsonLan.put("price", rs.getInt("price"));
			json.put(jsonLan);
		}
		return json;
	}
	//根据手机型号查询品牌
	public String queryPhonebrand(String phonename) throws SQLException{
		String phonebrand = null;
		PreparedStatement pst = (PreparedStatement) ClientDB.getCon().prepareStatement(sqlByPhonename);
		pst.setString(1, phonename);
		ResultSet rs = pst.executeQuery();
		while(rs.next()){
		phonebrand = rs.getString("phonebrand");
		}
		return phonebrand;
	}
}

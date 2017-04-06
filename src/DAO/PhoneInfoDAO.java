package DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.mysql.jdbc.PreparedStatement;

import model.PhoneInfo;

public class PhoneInfoDAO {

	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/phoneplaza";
	
	private static Connection getCon(){
		Connection con = null;
	try
	{
		System.out.println(driver);
		Class.forName(driver);
		con = DriverManager.getConnection(url,"root","123456");
	}catch(ClassNotFoundException|
	SQLException e)
	{
		e.printStackTrace();
	}
	return con;
	}
	public PhoneInfo query(String phoneName) throws SQLException{
		PhoneInfo phoneInfo = new PhoneInfo();
		PreparedStatement pst = (PreparedStatement) getCon().prepareStatement("select * from phoneinfo where phonename = ?");
		pst.setString(1, phoneName);
		ResultSet rs = pst.executeQuery();
		while(rs.next()){
			phoneInfo.setPhoneBrand(rs.getString("phonebrand"));;
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
}

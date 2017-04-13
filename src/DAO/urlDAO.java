package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import com.mysql.jdbc.PreparedStatement;

public class urlDAO {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/phoneplaza";
	private static String insertSql = "insert into urlinfo(url,title,para,time) values (?,?,?,?)";
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
	public void insertURL(String url,String title,String para,Date time) throws SQLException{
		PreparedStatement pst = (PreparedStatement) getCon().prepareStatement(insertSql);
		pst.setString(1, url);
		pst.setString(2, title);
		pst.setString(3, para);
		Timestamp sqldate = new Timestamp(time.getTime());
		System.out.println("sqldate:"+sqldate);
		pst.setTimestamp(4, sqldate);
		pst.execute();
	}
}

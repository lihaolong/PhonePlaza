package DAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class VideoUrlDAO {
	private static String insertsql = "insert into videourl (url,title,time) values (?,?,?)";
	private static String queryMaxTime = "select time from videourl order by time desc limit 1";
	
	public void insert(String url,String title,Date time) throws SQLException{
		PreparedStatement pst = (PreparedStatement) ClientDB.getCon().prepareStatement(insertsql);
		pst.setString(1, url);
		pst.setString(2, title);
		pst.setDate(3, time);;
		pst.execute();
	}
	
	public Date queryMax () throws SQLException{
		PreparedStatement pst = (PreparedStatement) ClientDB.getCon().prepareStatement(queryMaxTime);
		ResultSet rs = pst.executeQuery();
		Date maxTime = null;
		while(rs.next()){
			maxTime = rs.getDate("time");
		}
		return maxTime;
	}
}

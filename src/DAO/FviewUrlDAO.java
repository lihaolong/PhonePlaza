package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.mysql.jdbc.PreparedStatement;

public class FviewUrlDAO {
	private static String insertsql = "insert into fviewurl (url,title,time) values(?,?,?)";
	private static String queryMax = "select time from fviewurl order by time desc limit 0,1";
	
	public void insert(String url,String title,Timestamp time) throws SQLException{
		PreparedStatement pst = (PreparedStatement) ClientDB.getCon().prepareStatement(insertsql);
		pst.setString(1, url);
		pst.setString(2, title);
		pst.setTimestamp(3, time);
		pst.execute();
	}
	
	public Timestamp queryMax() throws SQLException{
		Timestamp timeMax = null;
		PreparedStatement pst = (PreparedStatement) ClientDB.getCon().prepareStatement(queryMax);
		ResultSet rs = pst.executeQuery();
		while(rs.next()){
			timeMax = rs.getTimestamp("time");
		}
		return timeMax;
	}
}

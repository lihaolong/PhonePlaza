package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mysql.jdbc.PreparedStatement;

public class urlDAO {
	private static String insertSql = "insert into urlinfo(url,title,para,time) values (?,?,?,?)";
	private static String queryMaxSql = "select max(time) from urlinfo";
	private static String queryTop = "select * from urlinfo order by time desc limit 0,4";
	
	public void insertURL(String url,String title,String para,Date time) throws SQLException{
		PreparedStatement pst = (PreparedStatement) ClientDB.getCon().prepareStatement(insertSql);
		pst.setString(1, url);
		pst.setString(2, title);
		pst.setString(3, para);
		Timestamp sqldate = new Timestamp(time.getTime());
		System.out.println("sqldate:"+sqldate);
		pst.setTimestamp(4, sqldate);
		pst.execute();
	}
	
	public Timestamp queryMaxTime() throws SQLException{
		PreparedStatement pst = (PreparedStatement) ClientDB.getCon().prepareStatement(queryMaxSql);
		ResultSet rs = pst.executeQuery();
		Timestamp timeMax = null;
		while(rs.next()){
		timeMax = rs.getTimestamp("max(time)");
		}
		if(timeMax==null){
			timeMax = Timestamp.valueOf("2017-4-1 00:00:00");
		}
		return timeMax;
	}
	
	public JSONArray queryTop() throws JSONException, SQLException{
		PreparedStatement pst = (PreparedStatement) ClientDB.getCon().prepareStatement(queryTop);
		ResultSet rs = pst.executeQuery();
		JSONArray jsonArray = new JSONArray();
		while(rs.next()){
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("url", rs.getString("url"));
			jsonObj.put("title", rs.getString("title"));
			jsonObj.put("time", rs.getTimestamp("time"));
			jsonObj.put("para", rs.getString("para"));
			jsonArray.put(jsonObj);
		}
		return jsonArray;
	}
}

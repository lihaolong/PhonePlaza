package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mysql.jdbc.PreparedStatement;

public class UrlDAO {
	private static String insertSql = "insert into urlinfo(url,title,para,time) values (?,?,?,?)";
	private static String queryTop = "select distinct * from urlinfo order by time desc limit 0,4";
	//根据网站查询最大时间
	private static String maxTime = "SELECT time FROM urlinfo WHERE url LIKE CONCAT('%',?,'%') ORDER BY time DESC LIMIT 1";
	private static String queryByPhone = "select distinct url,title,time,para from urlinfo where title like concat('%',?,'%') order by time desc limit ?";
	private static String countUrl = "select count(url) as count from urlinfo where title like concat('%',?,'%')";
	
	//插入url
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
	//查询最大时间
	public Timestamp queryMaxTime(String url) throws SQLException{
		PreparedStatement pst = (PreparedStatement) ClientDB.getCon().prepareStatement(maxTime);
		pst.setString(1, url);
		ResultSet rs = pst.executeQuery();
		Timestamp timeMax = null;
		while(rs.next()){
		timeMax = rs.getTimestamp("time");
		}
		if(timeMax==null){
			timeMax = Timestamp.valueOf("2017-4-1 00:00:00");
		}
		return timeMax;
	}
	//查询最新URL信息
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
	//根据手机查询url
	public JSONArray queryByPhone(String phonename,int num) throws SQLException, JSONException{
		JSONArray jsonArray = new JSONArray();
		PreparedStatement pst = (PreparedStatement) ClientDB.getCon().prepareStatement(queryByPhone);
		pst.setString(1, phonename);
		pst.setInt(2, num);
		ResultSet rs = pst.executeQuery();
		while(rs.next()){
			JSONObject json = new JSONObject();
			json.put("url", rs.getString("url"));
			json.put("title", rs.getString("title"));
			json.put("time", rs.getTimestamp("time"));
			json.put("para", rs.getString("para"));
			jsonArray.put(json);
		}
		System.out.println(jsonArray.toString());
		return jsonArray;
	}
	//查询得到的url数目
	public int countUrl(String phonename) throws SQLException{
		int count = 0;
		PreparedStatement pst = (PreparedStatement) ClientDB.getCon().prepareStatement(countUrl);
		pst.setString(1, phonename);
		ResultSet rs = pst.executeQuery();
		while(rs.next()){
			count = rs.getInt("count");
		}
		return count;
	}
}

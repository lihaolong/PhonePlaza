package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.mysql.jdbc.PreparedStatement;

public class NaYanUrlDAO {
	private static String insertsql = "insert into nayanurl values(?,?,?)";
	private static String queryMax = "select postid from nayanurl order by postid desc limit 0,1";
	private static String queryNew = "select url,title from nayanurl order by postid desc limit 1";
	
	public void insert(int postId,String url,String title) throws SQLException{
		PreparedStatement pst = (PreparedStatement) ClientDB.getCon().prepareStatement(insertsql);
		pst.setInt(1, postId);
		pst.setString(2, url);
		pst.setString(3, title);
		pst.execute();
	}
	
	public int queryMaxId() throws SQLException{
		int postId = 0;
		PreparedStatement pst = (PreparedStatement) ClientDB.getCon().prepareStatement(queryMax);
		ResultSet rs = pst.executeQuery();
		while(rs.next()){
			postId = rs.getInt("postid");
		}
		return postId;
	}
	
	public JSONObject queryNewNayan() throws SQLException, JSONException{
		JSONObject json = new JSONObject();
		PreparedStatement pst = (PreparedStatement) ClientDB.getCon().prepareStatement(queryNew);
		ResultSet rs = pst.executeQuery();
		while(rs.next()){
			json.put("info", "2");
			json.put("url", rs.getString("url"));
			json.put("title", rs.getString("title"));
		}
		return json;
	}
}

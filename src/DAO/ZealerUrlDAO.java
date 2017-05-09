package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class ZealerUrlDAO {
	private static String insertsql = "insert into zealerurl values(?,?,?)";
	private static String queryMax = "select postid from zealerurl order by postid desc limit 0,1";
	
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
}

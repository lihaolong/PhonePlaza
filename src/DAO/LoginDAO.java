package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import model.UserInfo;

public class LoginDAO {
	private static String insertUser = "insert into userinfo (username,`password`) values (?,?)";
	private static String queryUser = "select username,`password` from userinfo";
	
	public void insertUser(String userName,String password) throws SQLException{
		PreparedStatement pst = (PreparedStatement) ClientDB.getCon().prepareStatement(insertUser);
		pst.setString(1, userName);
		pst.setString(2, password);
		pst.execute();
	}
	
	public List<UserInfo> queryUser(){
		List<UserInfo> uList = new ArrayList<UserInfo>();
		UserInfo userInfo = new UserInfo();
		PreparedStatement pst;
		try {
			pst = (PreparedStatement) ClientDB.getCon().prepareStatement(queryUser);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				userInfo.setUserName(rs.getString("username"));
				userInfo.setPassword(rs.getString("password"));
				uList.add(userInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return uList;
	}
}

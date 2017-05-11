package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import model.UserPhone;

public class UserPhoneDAO {
	private static String insert = "insert into userphone (username,phonename) values (?,?)";
	private static String query = "select username,phonename from userphone";
	private static String delete = "delete from userphone where username=? and phonename=?";
	
	public void insert(String userName,String phoneName) throws SQLException{
		PreparedStatement pst = (PreparedStatement) ClientDB.getCon().prepareStatement(insert);
		pst.setString(1, userName);
		pst.setString(2, phoneName);
		pst.execute();
	}
	public List<UserPhone> query(){
		List<UserPhone> uList = new ArrayList<>();
		PreparedStatement pst;
		try {
			pst = (PreparedStatement) ClientDB.getCon().prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while(rs.next()){
				UserPhone userPhone = new UserPhone();
				userPhone.setPhoneName(rs.getString("phonename"));
				userPhone.setUserName(rs.getString("username"));
				uList.add(userPhone);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uList;
	}
	
	public boolean delete(String username,String phonename) throws SQLException{
		PreparedStatement pst = (PreparedStatement) ClientDB.getCon().prepareStatement(delete);
		pst.setString(1, username);
		pst.setString(2, phonename);
		return pst.execute();
	}
}

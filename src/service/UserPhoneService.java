package service;

import java.sql.SQLException;
import java.util.List;

import DAO.UserPhoneDAO;
import model.UserPhone;

public class UserPhoneService {
	UserPhoneDAO userPhoneDAO = new UserPhoneDAO();
	List<UserPhone> uList = userPhoneDAO.query();
	public boolean isExit(String userName,String phoneName){
	boolean isExit = false;
	for(UserPhone u : uList){
		if(u.getUserName().equals(userName)&&u.getPhoneName().equals(phoneName)){
			isExit = true;
		}
	}
	return isExit;
	}
	public void add(String userName,String phoneName) throws SQLException{
		userPhoneDAO.insert(userName, phoneName);
	}
}

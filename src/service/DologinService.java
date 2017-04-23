package service;

import java.util.List;

import DAO.LoginDAO;
import model.UserInfo;

public class DologinService {

	LoginDAO logindao = new LoginDAO();
	List<UserInfo> uList = logindao.queryUser();
	
	public boolean isUser(String userName,String password){
		boolean isUser = false;
		for(UserInfo u : uList){
			if(u.getUserName()==userName&&u.getPassword()==password){
				isUser = true;
			}
		}
		return isUser;
	}
}


package service;

import java.util.List;

import DAO.LoginDAO;
import model.UserInfo;

public class DologinService {

	LoginDAO logindao = new LoginDAO();
	//判断user是否存在数据库
	public boolean isUser(String userName,String password){
		List<UserInfo> uList = logindao.queryUser();
		boolean isUser = false;
		for(UserInfo u : uList){
			if(u.getUserName().equals(userName)&&u.getPassword().equals(password)){
				isUser = true;
				System.out.println("isuser");
			}
			//System.out.println(u.getUserName());
		}
		return isUser;
	}
}


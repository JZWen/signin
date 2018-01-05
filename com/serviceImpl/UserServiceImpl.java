package com.serviceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.entite.User;
import com.service.UserService;
import com.userdao.Userdao;
import com.userdaoImpl.UserdaoImpl;

public class UserServiceImpl implements UserService{

	Userdao userdao = new UserdaoImpl();
	
	@Override
	public boolean login(String id, String password) {
		return userdao.login(id, password);
	}
	
	@Override
	public boolean register(User user) {
		return userdao.register(user);
	}
	/*
	 * ͨ��id��ѯ�û���Ϣ���жϸ��û��Ƿ����(non-Javadoc)
	 * @see com.service.UserService#byIdUser(java.lang.String)
	 */
	@Override
	public boolean byIdUser(String id) {
		return userdao.byIdUser(id);
	}
	
	
	@Override
	public List<User> byIdUserInfo(String id) {
		//List<User> userList = new ArrayList<User>();
		return userdao.byIdUserInfo(id);
	}

	@Override
	public boolean signIn(User user) {
		return userdao.signIn(user);
	}

	@Override
	public boolean byIdSignIn(String id) {
		return userdao.byIdSignIn(id);
	}

	@Override
	public Timestamp byIdSignTime(String id) {
		return userdao.byIdSignTime(id);
	}

	@Override
	public boolean delByIdSignin(String id) {
		return userdao.delByIdSign(id);
	}

	@Override
	public boolean insertRecond(User user) {
		return userdao.InsertRecond(user);
	}

	@Override
	public boolean alterUserInfo(User user) {
		return userdao.alterUserInfo(user);
	}

	@Override
	public boolean alterPassword(User user) {
		return userdao.alterPassword(user);
	}

	@Override
	public boolean idPwd(User user) {
		return userdao.idPwd(user);
	}
	

}

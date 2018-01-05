package com.service;

import java.sql.Timestamp;
import java.util.List;

import com.entite.User;

public interface UserService {

	public boolean login(String id,String password);
	
	public boolean register(User user);
	
	public boolean byIdUser(String id);
	
	public List<User> byIdUserInfo(String id);
	
	public boolean signIn(User user);
	
	public boolean byIdSignIn(String id);
	
	public Timestamp byIdSignTime(String id);
	
	public boolean delByIdSignin(String id);
	
	public boolean insertRecond(User user);
	
	public boolean alterUserInfo(User user);
	
	public boolean alterPassword(User user);
	
	public boolean idPwd(User user);
}

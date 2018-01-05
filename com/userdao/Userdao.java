package com.userdao;

import java.sql.Timestamp;
import java.util.List;

import com.entite.User;

public interface Userdao {
	public boolean login(String id,String password);
	
	public boolean byIdUser(String id);

	public boolean register(User user);
	
	public List<User> byIdUserInfo(String id);
	
	public boolean signIn(User user);
	
	public boolean byIdSignIn(String id);
	
	public Timestamp byIdSignTime(String id);
	
	public boolean delByIdSign(String id);
	
	public boolean InsertRecond(User user);
	
	public boolean alterUserInfo(User user);
	
	public boolean alterPassword(User user);
	
	public boolean idPwd(User user);
}

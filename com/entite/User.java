package com.entite;

//import java.sql.Date;
import java.sql.Timestamp;

public class User {

	private String id;
	private String password;
	private String grade;
	private String name;
	//private Date signin;
	//private Date signout;
	//
	private Timestamp signin;
	private Timestamp signout;
	private float time;
	public String getId() {
		return id;
	}
	public float getTime() {
		return time;
	}
	public void setTime(float time) {
		this.time = time;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getSignin() {
		return signin;
	}
	public void setSignin(Timestamp signin) {
		this.signin = signin;
	}
	public Timestamp getSignout() {
		return signout;
	}
	public void setSignout(Timestamp signout) {
		this.signout = signout;
	}
	
}

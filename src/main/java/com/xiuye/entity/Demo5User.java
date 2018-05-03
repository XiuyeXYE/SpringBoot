package com.xiuye.entity;

public class Demo5User {
	private Long id;
	private String userName;
	private String password;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return password;
	}
	public void setPassWord(String passWord) {
		this.password = passWord;
	}
	@Override
	public String toString() {
		return "Demo5User [id=" + id + ", userName=" + userName + ", passWord=" + password + "]";
	}

}

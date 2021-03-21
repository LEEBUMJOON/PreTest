package com.KakaopayPreTest.insurance.response.dto;

import java.util.ArrayList;

import com.KakaopayPreTest.insurance.domain.User;

public class UserListDto {

	private ArrayList<User> userList = new ArrayList<User>();

	public ArrayList<User> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}
}

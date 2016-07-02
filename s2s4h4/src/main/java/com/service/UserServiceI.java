package com.service;

import java.io.Serializable;

import com.drivenModel.UserModel;
import com.model.DbUser;

public interface UserServiceI {

	
	public void test();
	public Serializable saveUser(UserModel u);
	public DbUser findUser(String name);
	public void delUser(String name);
}

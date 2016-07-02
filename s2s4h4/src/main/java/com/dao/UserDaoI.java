package com.dao;

import java.util.List;

import com.model.DbUser;

public interface UserDaoI extends BaseDaoI<DbUser>{
	public List<DbUser> find(Object values);
}

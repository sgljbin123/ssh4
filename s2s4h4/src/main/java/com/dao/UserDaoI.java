package com.dao;

import java.util.List;

import com.model.UtUser;

public interface UserDaoI extends BaseDaoI<UtUser>{
	public List<UtUser> find(Object values);
}

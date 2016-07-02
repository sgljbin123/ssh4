package com.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dao.UserDaoI;
import com.model.DbUser;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<DbUser> implements UserDaoI {
	public List<DbUser> find(Object values){
		String sql = "from DbUser where name = ?";
		return super.find(sql, values);
	}
}

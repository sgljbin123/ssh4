package com.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dao.UserDaoI;
import com.model.UtUser;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<UtUser> implements UserDaoI {
	public List<UtUser> find(Object values){
		String sql = "from DbUser where name = ?";
		return super.find(sql, values);
	}
}

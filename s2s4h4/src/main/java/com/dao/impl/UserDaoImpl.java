package com.dao.impl;

import org.springframework.stereotype.Repository;

import com.dao.UserDaoI;
import com.model.UtUser;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<UtUser> implements UserDaoI {
	
}

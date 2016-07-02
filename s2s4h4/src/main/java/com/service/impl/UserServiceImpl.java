package com.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDaoI;
import com.model.DbUser;
import com.service.UserServiceI;
import com.utils.EncryptAndDecrypt;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserServiceI {

	@Autowired
	private UserDaoI userDao;
	
	private static final Logger logger = Logger
			.getLogger(UserServiceImpl.class);
	

	@Override
	public void test() {
		// TODO Auto-generated method stub
		logger.info("hello Service");
	}

	@Override
	public Serializable saveUser(DbUser u) {
		// TODO Auto-generated method stub
		logger.info(u.getName());
		u.setPassword(EncryptAndDecrypt.encrypt(u.getPassword()));
		return userDao.save(u);
	}

	@Override
	public DbUser findUser(String name) {
		List<DbUser> DbuserList = userDao.find(name);
		return  (DbuserList == null) || (DbuserList.size() == 0)?null:DbuserList.get(0);
	}

	@Override
	public void delUser(String name) {
		String querySql = "from DbUser where name = ?";
		List<DbUser> DbuserList = userDao.find(querySql, name);
		userDao.deleteAll(DbuserList);
	}

}

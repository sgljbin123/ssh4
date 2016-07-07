package com.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDaoI;
import com.drivenModel.UserModel;
import com.model.UtUser;
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
	public Serializable saveUser(UserModel userModel) {
		
		// TODO Auto-generated method stub
		UtUser u = new UtUser();
		u.setName(userModel.getIndex_reg_name());
		logger.info(u.getName());
		u.setPassword(EncryptAndDecrypt.encrypt(userModel.getIndex_reg_password()));
		u.setCreatetime(new Date());
		return userDao.save(u);
	}

	@Override
	public UtUser findUser(String name,String password) {
		logger.info("find user service");
		String queryString = "from UtUser where name=? and password=?";
		String[] paramMap = {name,EncryptAndDecrypt.encrypt(password)}; 
		List<UtUser> DbuserList = userDao.findList(queryString,paramMap);
		return  (DbuserList == null) || (DbuserList.size() == 0)?null:DbuserList.get(0);
	}

	@Override
	public void delUser(String name) {
		String queryString = "from UtUser where name =:name";
		String[] paramMap={name};
		List<UtUser> DbuserList = userDao.findList(queryString,paramMap);
		userDao.deleteAll(DbuserList);
	}

}

package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.MenuDaoI;
import com.dao.UserDaoI;
import com.model.UtMenu;
import com.model.UtUser;
import com.service.RepareServiceI;
import com.utils.EncryptAndDecrypt;

@Service("repareService")
@Transactional
public class RepareServiceImpl implements RepareServiceI {

	private MenuDaoI menuDao;

	public MenuDaoI getMenuDao() {
		return menuDao;
	}

	@Autowired
	public void setMenuDao(MenuDaoI menuDao) {
		this.menuDao = menuDao;
	}

	private UserDaoI userDao;

	public UserDaoI getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(UserDaoI userDao) {
		this.userDao = userDao;
	}

	@Override
	public void repare() {
		// TODO Auto-generated method stub
		repareMenu();
		repareUser();
	}

	public void repareMenu() {
		List<UtMenu> menuList = menuDao.query("from UtMenu");
		if (menuList != null && menuList.size() > 0) {
			menuDao.deleteAll(menuList);
		}
		UtMenu root = new UtMenu();
		root.setText("首页");
		UtMenu menu = new UtMenu();
		menu.setText("系统管理");
		menu.setUtMenu(root);
		menuDao.saveOrUpdate(root);
		menuDao.saveOrUpdate(menu);
		UtMenu menu1 = new UtMenu();
		menu1.setText("用户管理");
		menu1.setUtMenu(menu);
		menuDao.saveOrUpdate(menu1);

	}

	public void repareUser() {
		UtUser user = new UtUser();
		user.setName("linjb");
		user.setPassword(EncryptAndDecrypt.encrypt("sgljbin123"));
		List<UtUser> useList = userDao.findByExample(user);
		if (useList != null && useList.size() > 0) {
			userDao.delete(user);
		} else {
			userDao.save(user);
		}
	}

}

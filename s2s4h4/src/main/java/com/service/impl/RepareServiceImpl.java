package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.MenuDaoI;
import com.model.UtMenu;
import com.service.RepareServiceI;

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

	@Override
	public void repare() {
		// TODO Auto-generated method stub
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

	
	
}

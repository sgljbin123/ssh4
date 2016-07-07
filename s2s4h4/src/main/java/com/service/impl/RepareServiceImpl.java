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
		menu.setText("菜单功能");
		menu.setUtMemu(root);
		menuDao.saveOrUpdate(root);
		menuDao.saveOrUpdate(menu);
	}

	
	
}

package com.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.MenuDaoI;
import com.drivenModel.MenuModel;
import com.model.UtMenu;
import com.service.MenuServiceI;

@Service("menuService")
@Transactional
public class MenuServiceImpl implements MenuServiceI {

	private MenuDaoI menuDao;

	public MenuDaoI getMenuDao() {
		return menuDao;
	}

	@Autowired
	public void setMenuDao(MenuDaoI menuDao) {
		this.menuDao = menuDao;
	}

	@Override
	public void addMenu() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<MenuModel> loadMenu() {
		// TODO Auto-generated method stub
		String queryString = "from UtMenu t where t.utMenu is null";
		List<UtMenu> menuList = menuDao.query(queryString);
		List<MenuModel> menuModelList = new ArrayList<MenuModel>();
		BeanUtils.copyProperties(menuList, menuModelList);
		return menuModelList;
	}

	@Override
	public List<UtMenu> loadChildMenu(int pid) {
		// TODO Auto-generated method stub
		String queryString = "from UtMenu t where t.utMenu = ?";
		String value = Integer.toString(pid);
		String[] values = new String[] { value };
		return menuDao.findList(queryString, values);
	}

}

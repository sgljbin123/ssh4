package com.service.impl;

import java.util.ArrayList;
import java.util.List;

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
	public List<MenuModel> loadMenu(int pid) {
		// TODO Auto-generated method stub
		String queryString = null;
		List<UtMenu> menuList = new ArrayList<UtMenu>();
		if(pid == 0){
			queryString = "from UtMenu t where t.utMenu is null";
			menuList = menuDao.query(queryString);
		}else{
			queryString = "from UtMenu t where t.utMenu.id = :id";
			menuList = menuDao.findByParam(queryString,new String[]{"id"}, new Integer[]{pid});
		}
		
		
		List<MenuModel> menuModelList = new ArrayList<MenuModel>();
		if (menuList != null && menuList.size() > 0) {
			for (UtMenu menu : menuList) {
				MenuModel model = new MenuModel();
				BeanUtils.copyProperties(menu, model);
				if(menu.getUtMenus()!=null&&!menu.getUtMenus().isEmpty()){
				model.setState("closed");
				}else{
					model.setState("open");
				}
				menuModelList.add(model);
			}
		}
		return menuModelList;
	}

}

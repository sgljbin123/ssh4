package com.service.impl;

import java.awt.Menu;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	public List<UtMenu> loadMenu() {
		// TODO Auto-generated method stub
		String queryString = "from UtMenu t where t.utMenu is null";
		return menuDao.query(queryString);
	}

	@Override
	public List<UtMenu> loadChildMenu(int pid) {
		// TODO Auto-generated method stub
		String queryString = "from UtMenu t where t.utMenu = ?";
		String value = Integer.toString(pid);
		String[] values = new String[] { value };
		return menuDao.findList(queryString, values);
	}

	public List<MenuModel> getTreeNode(Integer id) {
		List<MenuModel> nl = new ArrayList<MenuModel>();
		String[] map = null;
		String hql = null;
		String pid = Integer.toString(id);
		if (pid == null || pid.equals("")) { // 显示根节点
			hql = "from UtMenu t where t.utMenu is null ";
		} else { // 查询当前id下的子节点
			hql = "from UtMenu t where t.utMenu.id = ? ";
			map=new String[]{pid};
		}
		List<UtMenu> list = menuDao.findList(hql, map);
		if (list != null && list.size() > 0) {
			for (UtMenu t : list) {
				MenuModel m = new MenuModel();
				BeanUtils.copyProperties(t, m);
				Set<UtMenu> set = t.getUtMenus();
				// 判断该节点是否有子节点
				if (set != null && !set.isEmpty()) {
					m.setState("closed");
				} else {
					m.setState("open");
				}
				nl.add(m);
			}
		}
		return nl;
	}

}

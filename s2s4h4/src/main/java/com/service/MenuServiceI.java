package com.service;

import java.util.List;

import com.drivenModel.MenuModel;
import com.model.UtMenu;

public interface MenuServiceI {

	public void addMenu();
	public List<UtMenu> loadMenu();
	public List<UtMenu> loadChildMenu(int pid);
	public List<MenuModel> getTreeNode(Integer id);
}

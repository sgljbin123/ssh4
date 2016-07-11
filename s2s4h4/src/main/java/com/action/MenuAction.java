package com.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.action.base.BaseAction;
import com.drivenModel.MenuModel;
import com.opensymphony.xwork2.ModelDriven;
import com.service.MenuServiceI;

public class MenuAction extends BaseAction implements ModelDriven<MenuModel> {
	
	private MenuServiceI menuService;

	public MenuServiceI getMenuService() {
		return menuService;
	}

	@Autowired
	public void setMenuService(MenuServiceI menuService) {
		this.menuService = menuService;
	}

	
	private Logger logger = Logger.getLogger(MenuAction.class);
	
	private MenuModel menuModel = new MenuModel();
	

	@Override
	public MenuModel getModel() {
		return menuModel;
	}

	@Action(value = "getMenu")
	public void addMenu() {
//		logger.info(menuModel.getId());
		
		writeToJSON(menuService.loadMenu());
	}
	public void loadChildMenu(){
		
	}
	

}

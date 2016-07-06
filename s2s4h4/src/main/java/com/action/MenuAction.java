package com.action;

import org.apache.struts2.convention.annotation.Action;

import com.action.base.BaseAction;
import com.drivenModel.MenuModel;
import com.opensymphony.xwork2.ModelDriven;


public class MenuAction extends BaseAction implements ModelDriven<MenuModel>{

	private MenuModel menuModel;

	@Override
	public MenuModel getModel() {
		// TODO Auto-generated method stub
		return menuModel;
	}
	
	@Action(value="getMenu")
	public void addMenu(){
		
	}
}

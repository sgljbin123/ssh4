package com.action;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.action.base.BaseAction;
import com.service.RepareServiceI;

public class RepareAction extends BaseAction {

	private RepareServiceI repareService;
	
	public RepareServiceI getRepareService() {
		return repareService;
	}
	
	@Autowired
	public void setRepareService(RepareServiceI repareService) {
		this.repareService = repareService;
	}

	@Action("repareAction")
	public void repareAction(){
		repareService.repare();
	}
}

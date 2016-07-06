package com.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.action.base.BaseAction;
import com.drivenModel.UserModel;
import com.model.UtUser;
import com.opensymphony.xwork2.ModelDriven;
import com.service.UserServiceI;




public class UserAction extends BaseAction implements ModelDriven<UserModel>{


	@Autowired
	private UserServiceI userService;



	private UserModel userModel;
	
	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	

	public UserServiceI getUserService() {
		return userService;
	}

	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}

	@Override
	public UserModel getModel() {
		// TODO Auto-generated method stub
		if(userModel == null){
			userModel = new UserModel();
		}
		return userModel;
	}

	

	private final static Logger logger = Logger.getLogger(UserAction.class);

	@Action("saveUser")
	public void saveUser() {
		UtUser u = new UtUser();
		Map<String , Object> userMap = new HashMap<>();
		u = userService.findUser(userModel.getIndex_reg_name(),userModel.getIndex_reg_password());
		logger.info(userModel.getIndex_reg_name());
		if (u != null) {
			userMap.put("success", false);
			userMap.put("message", "�˺��Ѵ��ڣ�ע��ʧ��");
		} else {
			try {
				userService.saveUser(userModel);
				userMap.put("success", true);
				userMap.put("message", "ע��ɹ�");

			} catch (Exception e) {
				e.printStackTrace();
				userMap.put("success", false);
				userMap.put("message", "ϵͳ�쳣��ע��ʧ�ܣ����Ժ�����");
			}
		}
		writeToJSON(userMap);
	}

	@Action("userLoggin")
	public void userLoggin() {
		UtUser u = new UtUser();
		u = userService.findUser(userModel.getIndex_loggin_name(),userModel.getIndex_loggin_password());
		Map<String , Object> userMap = new HashMap<>();
		if (u == null) {
			userMap.put("success", false);
			userMap.put("message", "�˺Ż����벻��ȷ������������");
		} else {
				userMap.put("success", true);
				userMap.put("message", "��½�ɹ���������תҳ��");
		}
		writeToJSON(userMap);
	}

	public void test() {
		logger.info("Hello struts2");
	}

}

package com.dao.impl;

import org.springframework.stereotype.Repository;

import com.dao.MenuDaoI;
import com.model.UtMenu;

@Repository("menuDao")
public class MenuDaoImpl extends BaseDaoImpl<UtMenu> implements MenuDaoI {

}

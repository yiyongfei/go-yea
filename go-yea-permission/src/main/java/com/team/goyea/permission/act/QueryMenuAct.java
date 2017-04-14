package com.team.goyea.permission.act;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.goyea.permission.model.MenuInfo;
import com.team.goyea.permission.service.PermissionService;
import com.yea.core.base.act.AbstractAct;


/**
 * 
 * @author yiyongfei
 */
@Service
public class QueryMenuAct extends AbstractAct<List<MenuInfo>> {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PermissionService permissionService;
   
	@Override
	protected List<MenuInfo> perform(Object[] messages) throws Throwable {
		// TODO Auto-generated method stub
		MenuInfo menuInfo = null;
		if(messages.length > 0) {
			menuInfo = (MenuInfo) messages[0];
		}
		return permissionService.queryMenu(menuInfo);
	}
	
}

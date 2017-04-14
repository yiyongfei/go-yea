package com.team.goyea.permission.act;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.goyea.permission.model.MenuInfo;
import com.team.goyea.permission.model.pk.MenuInfoPK;
import com.team.goyea.permission.service.PermissionService;
import com.yea.core.base.act.AbstractAct;


/**
 * 
 * @author yiyongfei
 */
@Service
public class LoadMenuAct extends AbstractAct<MenuInfo> {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PermissionService permissionService;
    
	@Override
	protected MenuInfo perform(Object[] messages) throws Throwable {
		// TODO Auto-generated method stub
		return permissionService.loadMenu((MenuInfoPK) messages[0]);
	}
	
}

package com.team.goyea.permission.act;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.goyea.permission.model.ResourceInfo;
import com.team.goyea.permission.model.pk.ResourceInfoPK;
import com.team.goyea.permission.service.PermissionService;
import com.yea.core.base.act.AbstractAct;


/**
 * 
 * @author yiyongfei
 */
@Service
public class LoadResourceAct extends AbstractAct<ResourceInfo> {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PermissionService permissionService;
    
	@Override
	protected ResourceInfo perform(Object[] messages) throws Throwable {
		// TODO Auto-generated method stub
		return permissionService.loadResource((ResourceInfoPK) messages[0]);
	}
	
}

package com.team.goyea.permission.act;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.goyea.permission.model.ResourceInfo;
import com.team.goyea.permission.model.pk.ResourceInfoPK;
import com.team.goyea.permission.service.PermissionService;
import com.yea.core.base.act.AbstractTransactionAct;


/**
 * 
 * @author yiyongfei
 */
@Service
public class SaveResourceAct extends AbstractTransactionAct<ResourceInfoPK> {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PermissionService permissionService;
    
	@Override
	protected ResourceInfoPK perform(Object[] messages) throws Throwable {
		// TODO Auto-generated method stub
		return permissionService.addResource((ResourceInfo)messages[0]);
	}
	
}

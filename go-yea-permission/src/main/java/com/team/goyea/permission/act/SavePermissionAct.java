package com.team.goyea.permission.act;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.goyea.permission.model.PermissionInfo;
import com.team.goyea.permission.model.pk.PermissionInfoPK;
import com.team.goyea.permission.service.PermissionService;
import com.yea.core.base.act.AbstractTransactionAct;


/**
 * 
 * @author yiyongfei
 */
@Service
public class SavePermissionAct extends AbstractTransactionAct<PermissionInfoPK> {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PermissionService permissionService;
    
	@Override
	protected PermissionInfoPK perform(Object[] messages) throws Throwable {
		// TODO Auto-generated method stub
		return permissionService.addPermission((PermissionInfo)messages[0]);
	}
	
}

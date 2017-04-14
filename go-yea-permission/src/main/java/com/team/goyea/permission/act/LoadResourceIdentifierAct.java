package com.team.goyea.permission.act;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.goyea.permission.model.ResourceIdentifier;
import com.team.goyea.permission.model.pk.ResourceIdentifierPK;
import com.team.goyea.permission.service.PermissionService;
import com.yea.core.base.act.AbstractAct;


/**
 * 
 * @author yiyongfei
 */
@Service
public class LoadResourceIdentifierAct extends AbstractAct<ResourceIdentifier> {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PermissionService permissionService;
    
	@Override
	protected ResourceIdentifier perform(Object[] messages) throws Throwable {
		// TODO Auto-generated method stub
		return permissionService.loadResourceIdentifier((ResourceIdentifierPK) messages[0]);
	}
	
}

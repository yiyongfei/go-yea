package com.team.goyea.permission.act;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.goyea.permission.model.ResourceIdentifier;
import com.team.goyea.permission.model.pk.ResourceIdentifierPK;
import com.team.goyea.permission.service.PermissionService;
import com.yea.core.base.act.AbstractTransactionAct;


/**
 * 
 * @author yiyongfei
 */
@Service
public class SaveResourceIdentifierAct extends AbstractTransactionAct<ResourceIdentifierPK> {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PermissionService permissionService;
    
	@Override
	protected ResourceIdentifierPK perform(Object[] messages) throws Throwable {
		// TODO Auto-generated method stub
		return permissionService.saveResourceIdentifier((ResourceIdentifier) messages[0], (Long) messages[1], (Long) messages[2]);
	}
	
}

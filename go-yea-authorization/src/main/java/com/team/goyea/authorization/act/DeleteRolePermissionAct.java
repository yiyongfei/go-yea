package com.team.goyea.authorization.act;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.goyea.authorization.model.pk.RolePermissionRelaPK;
import com.team.goyea.authorization.service.AuthorizationService;
import com.yea.core.base.act.AbstractTransactionAct;


/**
 * 
 * @author yiyongfei
 */
@Service
public class DeleteRolePermissionAct extends AbstractTransactionAct<Object> {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AuthorizationService authorizationService;
    
	@Override
	protected Object perform(Object[] messages) throws Throwable {
		// TODO Auto-generated method stub
		authorizationService.delRolePermission((RolePermissionRelaPK)messages[0]);
		return null;
	}
	
}

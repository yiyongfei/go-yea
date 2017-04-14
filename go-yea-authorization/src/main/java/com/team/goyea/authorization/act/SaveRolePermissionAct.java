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
public class SaveRolePermissionAct extends AbstractTransactionAct<RolePermissionRelaPK> {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AuthorizationService authorizationService;
    
	@Override
	protected RolePermissionRelaPK perform(Object[] messages) throws Throwable {
		// TODO Auto-generated method stub
		return authorizationService.addRolePermission((Long)messages[0], (Long)messages[1], (Long)messages[2]);
	}
	
}

package com.team.goyea.permission.act;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.goyea.permission.model.OperationInfo;
import com.team.goyea.permission.model.pk.OperationInfoPK;
import com.team.goyea.permission.service.PermissionService;
import com.yea.core.base.act.AbstractTransactionAct;


/**
 * 
 * @author yiyongfei
 */
@Service
public class SaveOperationAct extends AbstractTransactionAct<OperationInfoPK> {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PermissionService permissionService;
    
	@Override
	protected OperationInfoPK perform(Object[] messages) throws Throwable {
		// TODO Auto-generated method stub
		return permissionService.addOperation((OperationInfo)messages[0]);
	}
	
}

package com.team.goyea.permission.act;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.goyea.permission.model.OperationInfo;
import com.team.goyea.permission.service.PermissionService;
import com.yea.core.base.act.AbstractAct;


/**
 * 
 * @author yiyongfei
 */
@Service
public class QueryOperationAct extends AbstractAct<List<OperationInfo>> {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PermissionService permissionService;
    
	@Override
	protected List<OperationInfo> perform(Object[] messages) throws Throwable {
		// TODO Auto-generated method stub
		return permissionService.queryOperation();
	}
	
}

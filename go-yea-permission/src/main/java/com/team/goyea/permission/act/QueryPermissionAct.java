package com.team.goyea.permission.act;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.goyea.permission.model.PermissionInfo;
import com.team.goyea.permission.service.PermissionService;
import com.yea.core.base.act.AbstractAct;


/**
 * 
 * @author yiyongfei
 */
@Service
public class QueryPermissionAct extends AbstractAct<List<PermissionInfo>> {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PermissionService permissionService;
    
	@Override
	protected List<PermissionInfo> perform(Object[] messages) throws Throwable {
		// TODO Auto-generated method stub
		return permissionService.queryPermission((PermissionInfo)messages[0]);
	}
	
}

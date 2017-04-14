package com.team.goyea.permission.act;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.goyea.permission.model.ResourceInfo;
import com.team.goyea.permission.service.PermissionService;
import com.yea.core.base.act.AbstractAct;


/**
 * 
 * @author yiyongfei
 */
@Service
public class QueryParentResourceAct extends AbstractAct<List<ResourceInfo>> {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PermissionService permissionService;
    
	@Override
	protected List<ResourceInfo> perform(Object[] messages) throws Throwable {
		// TODO Auto-generated method stub
		ResourceInfo resourceInfo = null;
		if(messages.length > 0) {
			resourceInfo = (ResourceInfo) messages[0];
		}
		return permissionService.queryResourceByPanent(resourceInfo);
	}
	
}

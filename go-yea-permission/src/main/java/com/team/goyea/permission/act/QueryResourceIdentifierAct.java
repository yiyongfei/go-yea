package com.team.goyea.permission.act;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.goyea.permission.model.ResourceIdentifier;
import com.team.goyea.permission.service.PermissionService;
import com.yea.core.base.act.AbstractAct;


/**
 * 
 * @author yiyongfei
 */
@Service
public class QueryResourceIdentifierAct extends AbstractAct<List<ResourceIdentifier>> {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PermissionService permissionService;
    
	@Override
	protected List<ResourceIdentifier> perform(Object[] messages) throws Throwable {
		// TODO Auto-generated method stub
		ResourceIdentifier resourceIdentifier = null;
		if(messages.length > 0) {
			resourceIdentifier = (ResourceIdentifier) messages[0];
		}
		return permissionService.queryResourceIdentifier(resourceIdentifier);
	}
	
}

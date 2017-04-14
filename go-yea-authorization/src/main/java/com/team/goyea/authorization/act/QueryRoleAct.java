package com.team.goyea.authorization.act;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.goyea.authorization.model.RoleInfo;
import com.team.goyea.authorization.service.AuthorizationService;
import com.yea.core.base.act.AbstractAct;


/**
 * 
 * @author yiyongfei
 */
@Service
public class QueryRoleAct extends AbstractAct<List<RoleInfo>> {
	private static final long serialVersionUID = 1L;

	@Autowired
	private AuthorizationService authorizationService;

	@Override
	protected List<RoleInfo> perform(Object[] messages) throws Throwable {
		// TODO Auto-generated method stub
		return authorizationService.queryRole();
	}

}

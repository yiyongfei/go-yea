package com.team.goyea.authorization.facade;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.goyea.authorization.model.RoleInfo;
import com.team.goyea.authorization.service.AuthorizationService;
import com.yea.core.base.facade.AbstractFacade;


/**
 * 
 * @author lenovo
 * @version $Id: V1.0 2015年9月6日 上午9:18:43 Exp $
 */
@Service
public class QueryRoleFacade extends AbstractFacade<List<RoleInfo>> {
	private static final long serialVersionUID = 1L;

	@Autowired
	private AuthorizationService authorizationService;

	/**
	 * @throws Exception
	 * @see com.AbstractFacade.remote.facade.AbstractFacade#perform(java.lang.Object[])
	 */

	@Override
	protected List<RoleInfo> perform(Object[] messages) throws Throwable {
		// TODO Auto-generated method stub
		return authorizationService.queryRole();
	}

}

package com.team.goyea.authorization.facade;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.goyea.authorization.model.UserInfo;
import com.team.goyea.authorization.model.pk.UserInfoPK;
import com.team.goyea.authorization.service.AuthorizationService;
import com.yea.core.base.facade.AbstractFacade;


/**
 * 
 * @author lenovo
 * @version $Id: V1.0 2015年9月6日 上午9:18:43 Exp $
 */
@Service
public class LoadUserRoleFacade extends AbstractFacade<UserInfo> {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AuthorizationService authorizationService;
    /** 
     * @throws Exception 
     * @see com.AbstractFacade.remote.facade.AbstractFacade#perform(java.lang.Object[])
     */
    
	@Override
	protected UserInfo perform(Object[] messages) throws Throwable {
		// TODO Auto-generated method stub
		return authorizationService.loadUserRole((UserInfoPK)messages[0]);
	}
	
}

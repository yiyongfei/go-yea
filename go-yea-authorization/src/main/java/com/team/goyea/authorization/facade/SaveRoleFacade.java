package com.team.goyea.authorization.facade;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.goyea.authorization.model.RoleInfo;
import com.team.goyea.authorization.service.AuthorizationService;
import com.yea.core.base.facade.AbstractTransactionFacade;


/**
 * 
 * @author lenovo
 * @version $Id: V1.0 2015年9月6日 上午9:18:43 Exp $
 */
@Service
public class SaveRoleFacade extends AbstractTransactionFacade<Object> {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AuthorizationService authorizationService;
    /** 
     * @throws Exception 
     * @see com.AbstractFacade.remote.facade.AbstractFacade#perform(java.lang.Object[])
     */
    
	@Override
	protected Object perform(Object[] messages) throws Throwable {
		// TODO Auto-generated method stub
		authorizationService.saveRole((RoleInfo)messages[0]);
		return null;
	}
	
}

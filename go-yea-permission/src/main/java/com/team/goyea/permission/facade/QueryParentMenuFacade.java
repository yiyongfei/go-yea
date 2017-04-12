package com.team.goyea.permission.facade;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.goyea.permission.model.MenuInfo;
import com.team.goyea.permission.service.PermissionService;
import com.yea.core.base.facade.AbstractFacade;


/**
 * 
 * @author lenovo
 * @version $Id: V1.0 2015年9月6日 上午9:18:43 Exp $
 */
@Service
public class QueryParentMenuFacade extends AbstractFacade<List<MenuInfo>> {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PermissionService permissionService;
    /** 
     * @throws Exception 
     * @see com.AbstractFacade.remote.facade.AbstractFacade#perform(java.lang.Object[])
     */
    
	@Override
	protected List<MenuInfo> perform(Object[] messages) throws Throwable {
		// TODO Auto-generated method stub
		MenuInfo menuInfo = null;
		if(messages.length > 0) {
			menuInfo = (MenuInfo) messages[0];
		}
		return permissionService.queryParentOfMenu(menuInfo);
	}
	
}

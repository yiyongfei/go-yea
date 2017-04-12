package com.team.goyea.permission.service;

import java.util.List;

import com.team.goyea.permission.model.MenuInfo;
import com.team.goyea.permission.model.OperationInfo;
import com.team.goyea.permission.model.PermissionInfo;
import com.team.goyea.permission.model.ResourceIdentifier;
import com.team.goyea.permission.model.ResourceInfo;
import com.team.goyea.permission.model.pk.MenuInfoPK;
import com.team.goyea.permission.model.pk.OperationInfoPK;
import com.team.goyea.permission.model.pk.PermissionInfoPK;
import com.team.goyea.permission.model.pk.ResourceIdentifierPK;
import com.team.goyea.permission.model.pk.ResourceInfoPK;

public interface PermissionService {

	public OperationInfoPK addOperation(OperationInfo operationInfo) throws Exception;
	public List<OperationInfo> queryOperation() throws Exception;
	
	public ResourceInfoPK addResource(ResourceInfo resourceInfo) throws Exception;
	public ResourceInfo loadResource(ResourceInfoPK resourcePk) throws Exception;
	public List<ResourceInfo> queryResource(ResourceInfo resourceInfo) throws Exception;
	public List<ResourceInfo> queryResourceByPanent(ResourceInfo resourceInfo) throws Exception;
	
	public PermissionInfoPK addPermission(PermissionInfo permissionInfo) throws Exception;
	public List<PermissionInfo> queryPermission(PermissionInfo permissionInfo) throws Exception;
	
	public ResourceIdentifierPK saveResourceIdentifier(ResourceIdentifier resourceIdentifier, Long resourceId, Long operationId) throws Exception;
	public ResourceIdentifier loadResourceIdentifier(ResourceIdentifierPK resourceIdentifierPK) throws Exception;
	public List<ResourceIdentifier> queryResourceIdentifier(ResourceIdentifier resourceIdentifier) throws Exception;

	public MenuInfoPK saveMenu(MenuInfo menuInfo) throws Exception;
	public MenuInfo loadMenu(MenuInfoPK menuId) throws Exception;
	public List<MenuInfo> queryMenu(MenuInfo menuInfo) throws Exception;
	public List<MenuInfo> queryParentOfMenu(MenuInfo menuInfo) throws Exception;
	
}

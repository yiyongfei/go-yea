package com.team.goyea.permission.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.team.goyea.permission.repository.MenuInfoRepository;
import com.team.goyea.permission.repository.OperationInfoRepository;
import com.team.goyea.permission.repository.PermissionInfoRepository;
import com.team.goyea.permission.repository.ResourceIdentifierRepository;
import com.team.goyea.permission.repository.ResourceInfoRepository;
import com.team.goyea.permission.service.PermissionService;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	private OperationInfoRepository operationInfoRepository;
	@Autowired
	private ResourceInfoRepository resourceInfoRepository;
	@Autowired
	private PermissionInfoRepository permissionInfoRepository;
	@Autowired
	private ResourceIdentifierRepository resourceIdentifierRepository;
	@Autowired
	private MenuInfoRepository menuInfoRepository;

	@Override
	public OperationInfoPK addOperation(OperationInfo operationInfo) throws Exception {
		return operationInfoRepository.saveOperation(operationInfo);
	}

	@Override
	public List<OperationInfo> queryOperation() throws Exception {
		// TODO Auto-generated method stub
		return operationInfoRepository.queryOperation();
	}

	@Override
	public ResourceInfoPK addResource(ResourceInfo resourceInfo) throws Exception {
		// TODO Auto-generated method stub
		if(resourceInfo.getResourceInfoEntity().getParentResourceId() != null) {
			ResourceInfo parentResource = resourceInfoRepository.loadResource(new ResourceInfoPK(resourceInfo.getResourceInfoEntity().getParentResourceId()));
			resourceInfo.getResourceInfoEntity().setResourceContent(parentResource.getResourceInfoEntity().getResourceContent() + ":" +resourceInfo.getResourceInfoEntity().getResourceName());
		} else {
			resourceInfo.getResourceInfoEntity().setResourceContent(resourceInfo.getResourceInfoEntity().getResourceName());
		}
		return resourceInfoRepository.saveResource(resourceInfo);
	}

	@Override
	public ResourceInfo loadResource(ResourceInfoPK resourcePk) throws Exception {
		// TODO Auto-generated method stub
		return resourceInfoRepository.loadResource(resourcePk);
	}
	
	@Override
	public List<ResourceInfo> queryResource(ResourceInfo resourceInfo) throws Exception {
		// TODO Auto-generated method stub
		return resourceInfoRepository.queryResource(resourceInfo);
	}
	
	@Override
	public List<ResourceInfo> queryResourceByPanent(ResourceInfo resourceInfo) throws Exception {
		// TODO Auto-generated method stub
		return resourceInfoRepository.queryResourceByPanent(resourceInfo);
	}

	@Override
	public PermissionInfoPK addPermission(PermissionInfo permissionInfo) throws Exception {
		// TODO Auto-generated method stub
		return permissionInfoRepository.savePermission(permissionInfo);
	}

	@Override
	public List<PermissionInfo> queryPermission(PermissionInfo permissionInfo) throws Exception {
		// TODO Auto-generated method stub
		return permissionInfoRepository.queryPermission(permissionInfo);
	}

	@Override
	public ResourceIdentifierPK saveResourceIdentifier(ResourceIdentifier resourceIdentifier, Long resourceId, Long operationId) throws Exception {
		// TODO Auto-generated method stub
		if(resourceId != null && operationId != null) {
			PermissionInfo permissionInfo = new PermissionInfo();
			permissionInfo.getPermissionInfoEntity().setResourceId(resourceId);
			permissionInfo.getPermissionInfoEntity().setOperationId(operationId);
			permissionInfo = permissionInfoRepository.queryPermission(permissionInfo).get(0);
			resourceIdentifier.getResourceIdentifierEntity().setPermissionId(permissionInfo.getPermissionInfoPK().getPermissionId());
		}
		
		return resourceIdentifierRepository.saveResourceIdentifier(resourceIdentifier);
	}

	@Override
	public ResourceIdentifier loadResourceIdentifier(ResourceIdentifierPK resourceIdentifierPK) throws Exception {
		// TODO Auto-generated method stub
		return resourceIdentifierRepository.loadResourceIdentifier(resourceIdentifierPK);
	}
	
	@Override
	public List<ResourceIdentifier> queryResourceIdentifier(ResourceIdentifier resourceIdentifier) throws Exception {
		// TODO Auto-generated method stub
		return resourceIdentifierRepository.queryResourceIdentifier(resourceIdentifier);
	}

	@Override
	public MenuInfoPK saveMenu(MenuInfo menuInfo) throws Exception {
		// TODO Auto-generated method stub
		return menuInfoRepository.saveMenu(menuInfo);
	}

	@Override
	public MenuInfo loadMenu(MenuInfoPK menuId) throws Exception {
		// TODO Auto-generated method stub
		return menuInfoRepository.loadMenu(menuId);
	}
	
	@Override
	public List<MenuInfo> queryMenu(MenuInfo menuInfo) throws Exception {
		// TODO Auto-generated method stub
		return menuInfoRepository.queryMenu(menuInfo);
	}
	
	@Override
	public List<MenuInfo> queryParentOfMenu(MenuInfo menuInfo) throws Exception {
		return menuInfoRepository.queryPanentOfMenu(menuInfo);
	}
	
}

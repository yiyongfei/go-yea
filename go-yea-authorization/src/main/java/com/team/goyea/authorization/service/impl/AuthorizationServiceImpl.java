package com.team.goyea.authorization.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.goyea.authorization.model.RoleInfo;
import com.team.goyea.authorization.model.RolePermissionRela;
import com.team.goyea.authorization.model.UserInfo;
import com.team.goyea.authorization.model.entity.PartyRoleRelaEntity;
import com.team.goyea.authorization.model.pk.RoleInfoPK;
import com.team.goyea.authorization.model.pk.RolePermissionRelaPK;
import com.team.goyea.authorization.model.pk.UserInfoPK;
import com.team.goyea.authorization.repository.RoleInfoRepository;
import com.team.goyea.authorization.repository.RolePermissionRelaRepository;
import com.team.goyea.authorization.repository.UserInfoRepository;
import com.team.goyea.authorization.service.AuthorizationService;
import com.team.goyea.permission.model.PermissionInfo;
import com.team.goyea.permission.model.ResourceInfo;
import com.team.goyea.permission.model.pk.ResourceInfoPK;

@Service("authorizationService")
public class AuthorizationServiceImpl implements AuthorizationService {
	@Autowired
	private RoleInfoRepository roleInfoRepository;
	@Autowired
	private RolePermissionRelaRepository rolePermissionRelaRepository;
	@Autowired
	private UserInfoRepository userInfoRepository;

	@Override
	public void saveRole(RoleInfo roleInfo) throws Exception {
		roleInfoRepository.saveRoleInfo(roleInfo);
	}

	@Override
	public RoleInfo loadRole(RoleInfoPK roleId) throws Exception {
		return roleInfoRepository.loadRole(roleId);
	}

	@Override
	public List<RoleInfo> queryRole() throws Exception {
		return roleInfoRepository.queryRole();
	}

	@Override
	public RolePermissionRelaPK addRolePermission(Long roleId, Long resourceId, Long operationId) throws Exception {
		// TODO Auto-generated method stub
		RolePermissionRela rolePermissionRela = new RolePermissionRela();
		rolePermissionRela.getRolePermissionRelaEntity().setRoleId(roleId);
		if(operationId != null) {
			PermissionInfo permissionInfo = new PermissionInfo();
			permissionInfo.getPermissionInfoEntity().setResourceId(resourceId);
			permissionInfo.getPermissionInfoEntity().setOperationId(operationId);
			permissionInfo = rolePermissionRelaRepository.queryPermissionInfo(permissionInfo).get(0);
			rolePermissionRela.getRolePermissionRelaEntity().setPermissionId(permissionInfo.getPermissionInfoPK().getPermissionId());
		} else {
			ResourceInfo ResourceInfo = rolePermissionRelaRepository.loadResource(new ResourceInfoPK(resourceId));
			rolePermissionRela.getRolePermissionRelaEntity().setPermissionWildcards(ResourceInfo.getResourceInfoEntity().getResourceContent() + ":*");
		}
		return rolePermissionRelaRepository.saveRolePermissionRela(rolePermissionRela);
	}

	@Override
	public void delRolePermission(RolePermissionRelaPK rolePermissionId) throws Exception {
		// TODO Auto-generated method stub
		rolePermissionRelaRepository.deleteRolePermissionRela(rolePermissionId);
	}

	@Override
	public RoleInfo queryRolePermissionWildcards(RoleInfoPK roleId) throws Exception {
		// TODO Auto-generated method stub
		return roleInfoRepository.loadRolePermission(roleId);
	}
	
	@Override
	public void saveUser(UserInfo userInfo) throws Exception {
		userInfoRepository.saveUserInfo(userInfo);
	}

	@Override
	public UserInfo loadUser(UserInfoPK userId) throws Exception {
		return userInfoRepository.loadUser(userId);
	}

	@Override
	public List<UserInfo> queryUser() throws Exception {
		return userInfoRepository.queryUser();
	}
	
	@Override
	public void modifyPassword(UserInfo userInfo) throws Exception {
		userInfoRepository.modifyUserPassword(userInfo);
	}
	
	@Override
	public UserInfo loadUserRole(UserInfoPK userId) throws Exception {
		return userInfoRepository.loadUserRole(userId);
	}
	@Override
	public void addUserRole(Long partyId, Long[] roleIds) throws Exception {
		UserInfo userInfo = new UserInfo(partyId);
		if (roleIds != null && roleIds.length > 0) {
			for(Long roleId : roleIds) {
				PartyRoleRelaEntity partyRoleRela = new PartyRoleRelaEntity();
				partyRoleRela.generatePK();
				partyRoleRela.setPartyId(partyId);
				partyRoleRela.setRoleId(roleId);
				userInfo.getListPartyRoleRelaEntity().add(partyRoleRela);
			}
		}
		userInfoRepository.addUserRole(userInfo);
	}
}

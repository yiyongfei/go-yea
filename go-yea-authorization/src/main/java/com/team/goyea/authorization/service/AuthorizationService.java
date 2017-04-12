package com.team.goyea.authorization.service;

import java.util.List;

import com.team.goyea.authorization.model.RoleInfo;
import com.team.goyea.authorization.model.UserInfo;
import com.team.goyea.authorization.model.pk.RoleInfoPK;
import com.team.goyea.authorization.model.pk.RolePermissionRelaPK;
import com.team.goyea.authorization.model.pk.UserInfoPK;

public interface AuthorizationService {
	
	public void saveRole(RoleInfo roleInfo) throws Exception;
	public RoleInfo loadRole(RoleInfoPK roleId) throws Exception;
	public List<RoleInfo> queryRole() throws Exception;
	
	public RolePermissionRelaPK addRolePermission(Long roleId, Long resourceId, Long operationId) throws Exception;
	public void delRolePermission(RolePermissionRelaPK rolePermissionId) throws Exception;
	public RoleInfo queryRolePermissionWildcards(RoleInfoPK roleId) throws Exception;
	
	public void saveUser(UserInfo userInfo) throws Exception;
	public UserInfo loadUser(UserInfoPK userId) throws Exception;
	public List<UserInfo> queryUser() throws Exception;
	public void modifyPassword(UserInfo userInfo) throws Exception;
	
	public UserInfo loadUserRole(UserInfoPK userId) throws Exception;
	public void addUserRole(Long partyId, Long[] roleIds) throws Exception;
}

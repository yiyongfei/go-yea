package com.team.goyea.authorization.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team.goyea.authorization.model.RolePermissionRela;
import com.team.goyea.authorization.model.pk.RolePermissionRelaPK;
import com.team.goyea.common.dao.CommonDao;
import com.team.goyea.permission.model.PermissionInfo;
import com.team.goyea.permission.model.ResourceInfo;
import com.team.goyea.permission.model.pk.ResourceInfoPK;
import com.yea.core.base.model.BaseModel;
import com.yea.core.remote.AbstractEndpoint;
import com.yea.core.remote.promise.Promise;
import com.yea.core.remote.struct.CallAct;

@Repository
public class RolePermissionRelaRepository {
	@Autowired
	private CommonDao<BaseModel> commonDao;
	@Autowired
	private AbstractEndpoint launcherClient;
	
	public RolePermissionRelaPK saveRolePermissionRela(RolePermissionRela rolePermissionRela) throws Exception {
		rolePermissionRela.generatePK();
		commonDao.insert(RolePermissionRela.Sqlid.ROLEPERMISSIONRELA_INSERT.value(), rolePermissionRela.getRolePermissionRelaEntity());
		return rolePermissionRela.getRolePermissionRelaPK();
	}
	
	public void deleteRolePermissionRela(RolePermissionRelaPK rolePermissionRelaPK) throws Exception {
		commonDao.queryOne(RolePermissionRela.Sqlid.ROLEPERMISSIONRELA_DELETE.value(), rolePermissionRelaPK);
	}
	
	public List<PermissionInfo> queryPermissionInfo(PermissionInfo permissionInfo) throws Exception {
		CallAct act = new CallAct();
		act.setActName("queryPermissionAct");
		Promise<List<PermissionInfo>> promise;
		try {
			promise = launcherClient.send(act, permissionInfo);
			return promise.awaitObject(10000);
		} catch (Throwable e) {
			throw new Exception(e);
		}
	}
	
	public ResourceInfo loadResource(ResourceInfoPK resourcePk) throws Exception {
		CallAct act = new CallAct();
		act.setActName("loadResourceAct");
		Promise<ResourceInfo> promise;
		try {
			promise = launcherClient.send(act, resourcePk);
			return promise.awaitObject(10000);
		} catch (Throwable e) {
			throw new Exception(e);
		}
	}
}

package com.team.goyea.permission.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team.goyea.common.dao.CommonDao;
import com.team.goyea.permission.model.PermissionInfo;
import com.team.goyea.permission.model.pk.PermissionInfoPK;
import com.yea.core.base.model.BaseModel;

@Repository
public class PermissionInfoRepository {
	@Autowired
	private CommonDao<BaseModel> commonDao;
	
	public PermissionInfoPK savePermission(PermissionInfo permissionInfo) throws Exception {
		if(permissionInfo.getPermissionInfoPK() != null && permissionInfo.getPermissionInfoPK().getPermissionId() != null) {
			permissionInfo.getPermissionInfoEntity().setPk(permissionInfo.getPermissionInfoPK());
			commonDao.update(PermissionInfo.Sqlid.UPDATE.value(), permissionInfo.getPermissionInfoEntity());
		} else {
			permissionInfo.generatePK();
			commonDao.insert(PermissionInfo.Sqlid.INSERT.value(), permissionInfo.getPermissionInfoEntity());
		}
		return permissionInfo.getPermissionInfoPK();
	}
	public List<PermissionInfo> queryPermission(PermissionInfo permissionInfo) throws Exception {
		List<PermissionInfo> listReturn = new ArrayList<PermissionInfo>();
		List<BaseModel> list = commonDao.queryMany(PermissionInfo.Sqlid.SELECT_PERMISSION.value(), permissionInfo);
		for(BaseModel model : list) {
			listReturn.add((PermissionInfo)model);
		}
		return listReturn;
	}
}

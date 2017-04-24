package com.team.goyea.authorization.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team.goyea.authorization.model.RoleInfo;
import com.team.goyea.authorization.model.pk.RoleInfoPK;
import com.team.goyea.common.dao.CommonDao;
import com.yea.core.base.model.BaseModel;

@Repository
public class RoleInfoRepository {
	@Autowired
	private CommonDao<BaseModel> commonDao;
	
	public RoleInfoPK saveRoleInfo(RoleInfo roleInfo) throws Exception {
		RoleInfo queryDto = new RoleInfo();
		queryDto.getRoleInfoEntity().setRoleName(roleInfo.getRoleInfoEntity().getRoleName());
		List<BaseModel> listRole = commonDao.queryMany(RoleInfo.Sqlid.ROLEINFO_SELECT_SELECTIVE.value(), queryDto);
		if(listRole.size() > 0) {
			throw new Exception("角色名["+roleInfo.getRoleInfoEntity().getRoleName()+"]已存在，请设置角色名");
		}
		
		if(roleInfo.getRoleInfoPK() != null && roleInfo.getRoleInfoPK().getRoleId() != null) {
			roleInfo.getRoleInfoEntity().setPk(roleInfo.getRoleInfoPK());
			commonDao.update(RoleInfo.Sqlid.ROLEINFO_UPDATE.value(), roleInfo.getRoleInfoEntity());
		} else {
			roleInfo.generatePK();
			commonDao.insert(RoleInfo.Sqlid.ROLEINFO_INSERT.value(), roleInfo.getRoleInfoEntity());
		}
		return roleInfo.getRoleInfoPK();
	}
	
	public RoleInfo loadRole(RoleInfoPK roleInfoPk) throws Exception {
		return (RoleInfo) commonDao.queryOne(RoleInfo.Sqlid.ROLEINFO_LOAD.value(), roleInfoPk);
	}
	
	public List<RoleInfo> queryRole() throws Exception {
		List<RoleInfo> listReturn = new ArrayList<RoleInfo>();
		List<BaseModel> list = commonDao.queryMany(RoleInfo.Sqlid.ROLEINFO_SELECT_SELECTIVE.value());
		for(BaseModel model : list) {
			listReturn.add((RoleInfo)model);
		}
		return listReturn;
	}
	
	public RoleInfo loadRolePermission(RoleInfoPK roleInfoPk) throws Exception {
		return (RoleInfo) commonDao.queryOne(RoleInfo.Sqlid.ROLEPERMISSION_LOAD.value(), roleInfoPk);
	}
}

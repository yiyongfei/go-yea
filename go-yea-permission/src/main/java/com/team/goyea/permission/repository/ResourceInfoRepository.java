package com.team.goyea.permission.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team.goyea.common.dao.CommonDao;
import com.team.goyea.permission.model.ResourceInfo;
import com.team.goyea.permission.model.pk.ResourceInfoPK;
import com.yea.core.base.model.BaseModel;

@Repository
public class ResourceInfoRepository {
	@Autowired
	private CommonDao<BaseModel> commonDao;
	
	public ResourceInfoPK saveResource(ResourceInfo resourceInfo) throws Exception {
		if(resourceInfo.getResourceInfoPK() != null && resourceInfo.getResourceInfoPK().getResourceId() != null) {
			resourceInfo.getResourceInfoEntity().setPk(resourceInfo.getResourceInfoPK());
			commonDao.update(ResourceInfo.Sqlid.UPDATE.value(), resourceInfo.getResourceInfoEntity());
		} else {
			resourceInfo.generatePK();
			commonDao.insert(ResourceInfo.Sqlid.INSERT.value(), resourceInfo.getResourceInfoEntity());
		}
		return resourceInfo.getResourceInfoPK();
	}
	
	public ResourceInfo loadResource(ResourceInfoPK resourcePk) throws Exception {
		return (ResourceInfo) commonDao.queryOne(ResourceInfo.Sqlid.LOAD.value(), resourcePk);
	}

	public List<ResourceInfo> queryResource(ResourceInfo resourceInfo) throws Exception {
		List<ResourceInfo> listReturn = new ArrayList<ResourceInfo>();
		List<BaseModel> list = commonDao.queryMany(ResourceInfo.Sqlid.SELECT_SELECTIVE.value(), resourceInfo);
		for(BaseModel model : list) {
			listReturn.add((ResourceInfo)model);
		}
		return listReturn;
	}
	
	public List<ResourceInfo> queryResourceByPanent(ResourceInfo resourceInfo) throws Exception {
		List<ResourceInfo> listReturn = new ArrayList<ResourceInfo>();
		List<BaseModel> list = commonDao.queryMany(ResourceInfo.Sqlid.SELECT_PARENT.value(), resourceInfo);
		for(BaseModel model : list) {
			listReturn.add((ResourceInfo)model);
		}
		return listReturn;
	}
}

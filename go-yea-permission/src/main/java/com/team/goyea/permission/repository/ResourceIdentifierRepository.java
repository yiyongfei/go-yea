package com.team.goyea.permission.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team.goyea.common.dao.CommonDao;
import com.team.goyea.permission.model.ResourceIdentifier;
import com.team.goyea.permission.model.pk.ResourceIdentifierPK;
import com.yea.core.base.model.BaseModel;

@Repository
public class ResourceIdentifierRepository {
	@Autowired
	private CommonDao<BaseModel> commonDao;
	
	public ResourceIdentifierPK saveResourceIdentifier(ResourceIdentifier resourceIdentifier) throws Exception {
		if(resourceIdentifier.getResourceIdentifierPK() != null && resourceIdentifier.getResourceIdentifierPK().getIdentifierId() != null) {
			resourceIdentifier.getResourceIdentifierEntity().setPk(resourceIdentifier.getResourceIdentifierPK());
			commonDao.insert(ResourceIdentifier.Sqlid.UPDATE.value(), resourceIdentifier.getResourceIdentifierEntity());
		} else {
			resourceIdentifier.generatePK();
			commonDao.insert(ResourceIdentifier.Sqlid.INSERT.value(), resourceIdentifier.getResourceIdentifierEntity());
		}
		return resourceIdentifier.getResourceIdentifierPK();
	}
	public ResourceIdentifier loadResourceIdentifier(ResourceIdentifierPK resourceIdentifierPK) throws Exception {
		return (ResourceIdentifier) commonDao.queryOne(ResourceIdentifier.Sqlid.LOAD.value(), resourceIdentifierPK);
	}
	public List<ResourceIdentifier> queryResourceIdentifier(ResourceIdentifier resourceIdentifier) throws Exception {
		List<ResourceIdentifier> listReturn = new ArrayList<ResourceIdentifier>();
		List<BaseModel> list = commonDao.queryMany(ResourceIdentifier.Sqlid.SELECT_PERM_CONTENT.value(), resourceIdentifier);
		for(BaseModel model : list) {
			listReturn.add((ResourceIdentifier)model);
		}
		return listReturn;
	}
}

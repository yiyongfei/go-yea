package com.team.goyea.permission.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team.goyea.common.dao.CommonDao;
import com.team.goyea.permission.model.OperationInfo;
import com.team.goyea.permission.model.pk.OperationInfoPK;
import com.yea.core.base.model.BaseModel;

@Repository
public class OperationInfoRepository {
	@Autowired
	private CommonDao<BaseModel> commonDao;
	
	public OperationInfoPK saveOperation(OperationInfo operationInfo) throws Exception {
		if(operationInfo.getOperationInfoPK() != null && operationInfo.getOperationInfoPK().getOperationId() != null) {
			operationInfo.getOperationInfoEntity().setPk(operationInfo.getOperationInfoPK());
			commonDao.update(OperationInfo.Sqlid.UPDATE.value(), operationInfo.getOperationInfoEntity());
		} else {
			operationInfo.generatePK();
			commonDao.insert(OperationInfo.Sqlid.INSERT.value(), operationInfo.getOperationInfoEntity());
		}
		return operationInfo.getOperationInfoPK();
	}
	
	public List<OperationInfo> queryOperation() throws Exception {
		List<OperationInfo> listReturn = new ArrayList<OperationInfo>();
		List<BaseModel> list = commonDao.queryMany(OperationInfo.Sqlid.SELECT_SELECTIVE.value());
		for(BaseModel model : list) {
			listReturn.add((OperationInfo)model);
		}
		return listReturn;
	}
}

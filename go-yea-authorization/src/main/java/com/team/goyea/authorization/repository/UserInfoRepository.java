package com.team.goyea.authorization.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team.goyea.authorization.model.LoginInfo;
import com.team.goyea.authorization.model.PartyInfo;
import com.team.goyea.authorization.model.PartyRoleRela;
import com.team.goyea.authorization.model.PersonInfo;
import com.team.goyea.authorization.model.UserInfo;
import com.team.goyea.authorization.model.entity.LoginInfoEntity;
import com.team.goyea.authorization.model.entity.PartyRoleRelaEntity;
import com.team.goyea.authorization.model.pk.PersonInfoPK;
import com.team.goyea.authorization.model.pk.UserInfoPK;
import com.team.goyea.common.dao.CommonDao;
import com.yea.core.base.model.BaseModel;
import com.yea.orm.handle.ORMConstants;
import com.yea.shiro.password.EncrytPassword;

@Repository
public class UserInfoRepository {
	@Autowired
	private CommonDao<BaseModel> commonDao;
	
	public UserInfoPK saveUserInfo(UserInfo userInfo) throws Exception {
		if(userInfo.getUserInfoPK() != null && userInfo.getUserInfoPK().getPartyId() != null) {
			userInfo.getPersonInfoEntity().setPk(new PersonInfoPK(userInfo.getUserInfoPK().getPartyId()));
			commonDao.update(PersonInfo.Sqlid.PERSONINFO_UPDATE.value(), userInfo.getPersonInfoEntity());
		} else {
			LoginInfo queryDto = new LoginInfo();
			queryDto.getLoginInfoEntity().setLoginName(userInfo.getUsername());
			List<BaseModel> listLogin = commonDao.queryMany(LoginInfo.Sqlid.LOGININFO_SELECT_SELECTIVE.value(), queryDto);
			if(listLogin.size() > 0) {
				throw new Exception("用户["+userInfo.getUsername()+"]已存在，请重新注册");
			}
			
			userInfo.generatePK();
			commonDao.insert(PartyInfo.Sqlid.PARTYINFO_INSERT.value(), userInfo.getPartyInfoEntity());
			commonDao.insert(PersonInfo.Sqlid.PERSONINFO_INSERT.value(), userInfo.getPersonInfoEntity());
			
			EncrytPassword encryt = new EncrytPassword(userInfo.getPassword());
			userInfo.getUserInfoEntity().setLoginPassword(encryt.getEncrytPassword());
			userInfo.getUserInfoEntity().setLoginSalt(encryt.getSalt());
			commonDao.insert(UserInfo.Sqlid.USERINFO_INSERT.value(), userInfo.getUserInfoEntity());
			
			LoginInfoEntity loginInfoEntity = new LoginInfoEntity();
			loginInfoEntity.generatePK();
			loginInfoEntity.setPartyId(userInfo.getUserInfoPK().getPartyId());
			loginInfoEntity.setLoginName(userInfo.getUsername());
			loginInfoEntity.setIsPrimary("01");
			commonDao.insert(LoginInfo.Sqlid.LOGININFO_INSERT.value(), loginInfoEntity);
		}
		return userInfo.getUserInfoPK();
	}
	
	public UserInfo loadUser(UserInfoPK userInfoId) throws Exception {
		return (UserInfo) commonDao.queryOne(UserInfo.Sqlid.USERINFO_LOAD.value(), userInfoId);
	}
	
	public List<UserInfo> queryUser() throws Exception {
		List<UserInfo> listReturn = new ArrayList<UserInfo>();
		List<BaseModel> list = commonDao.queryMany(UserInfo.Sqlid.USERINFO_SELECT_SELECTIVE.value());
		for(BaseModel model : list) {
			listReturn.add((UserInfo)model);
		}
		return listReturn;
	}
	
	public void modifyUserPassword(UserInfo userInfo) throws Exception {
		EncrytPassword encryt = new EncrytPassword(userInfo.getPassword());
		userInfo.getUserInfoEntity().setPk(userInfo.getUserInfoPK());
		userInfo.getUserInfoEntity().setLoginPassword(encryt.getEncrytPassword());
		userInfo.getUserInfoEntity().setLoginSalt(encryt.getSalt());
		commonDao.insert(UserInfo.Sqlid.USERINFO_UPDATE.value(), userInfo.getUserInfoEntity());
	}
	
	public UserInfo loadUserRole(UserInfoPK userInfoId) throws Exception {
		return (UserInfo) commonDao.queryOne(UserInfo.Sqlid.USERROLE_LOAD.value(), userInfoId);
	}
	
	public void addUserRole(UserInfo userInfo) throws Exception {
		PartyRoleRelaEntity entity = new PartyRoleRelaEntity();
		entity.setPartyId(userInfo.getUserInfoPK().getPartyId());
		commonDao.delete(PartyRoleRela.Sqlid.PARTYROLERELA_DELETE_SELECTIVE.value(), entity);
		entity = null;
		
		List<BaseModel> list = new ArrayList<BaseModel>();
		list.addAll(userInfo.getListPartyRoleRelaEntity());
		commonDao.executeBatch(ORMConstants.ORM_LEVEL.M_INSERT, PartyRoleRela.Sqlid.PARTYROLERELA_INSERT_BATCH.value(), list);
		list = null;
	}
	
}

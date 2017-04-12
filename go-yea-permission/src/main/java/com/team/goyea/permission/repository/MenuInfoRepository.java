package com.team.goyea.permission.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team.goyea.common.dao.CommonDao;
import com.team.goyea.permission.model.MenuInfo;
import com.team.goyea.permission.model.pk.MenuInfoPK;
import com.yea.core.base.model.BaseModel;

@Repository
public class MenuInfoRepository {
	@Autowired
	private CommonDao<BaseModel> commonDao;
	
	public MenuInfoPK saveMenu(MenuInfo menuInfo) throws Exception {
		if(menuInfo.getMenuInfoPK() != null && menuInfo.getMenuInfoPK().getMenuId() != null) {
			menuInfo.getMenuInfoEntity().setPk(menuInfo.getMenuInfoPK());
			commonDao.insert(MenuInfo.Sqlid.MENUINFO_UPDATE.value(), menuInfo.getMenuInfoEntity());
		} else {
			menuInfo.generatePK();
			commonDao.insert(MenuInfo.Sqlid.MENUINFO_INSERT.value(), menuInfo.getMenuInfoEntity());
		}
		return menuInfo.getMenuInfoPK();
	}
	
	public MenuInfo loadMenu(MenuInfoPK menuId) throws Exception {
		return (MenuInfo) commonDao.queryOne(MenuInfo.Sqlid.MENUINFO_LOAD.value(), menuId);
	}
	
	public List<MenuInfo> queryMenu(MenuInfo menuInfo) throws Exception {
		List<MenuInfo> listReturn = new ArrayList<MenuInfo>();
		List<BaseModel> list = commonDao.queryMany(MenuInfo.Sqlid.MENUFULL_SELECT_SELECTIVE.value(), menuInfo);
		for(BaseModel model : list) {
			listReturn.add((MenuInfo)model);
		}
		return listReturn;
	}
	
	public List<MenuInfo> queryPanentOfMenu(MenuInfo menuInfo) throws Exception {
		List<MenuInfo> listReturn = new ArrayList<MenuInfo>();
		List<BaseModel> list = commonDao.queryMany(MenuInfo.Sqlid.MENUINFO_SELECT_PARENT.value(), menuInfo);
		for(BaseModel model : list) {
			listReturn.add((MenuInfo)model);
		}
		return listReturn;
	}
}

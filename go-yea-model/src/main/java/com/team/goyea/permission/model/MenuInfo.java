/**
 * Copyright 2017 伊永飞
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.team.goyea.permission.model;


import com.yea.core.base.entity.BaseEntity;
import com.team.goyea.permission.model.entity.MenuInfoEntity;
import com.team.goyea.permission.model.pk.MenuInfoPK;

public class MenuInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    private MenuInfoPK menuInfoPK;
	private MenuInfoEntity menuInfoEntity;
	private MenuInfoEntity parentMenuInfoEntity;
	private String urlPath;
	
	
	public MenuInfo() {
		this.menuInfoPK = new MenuInfoPK();
		this.menuInfoEntity = new MenuInfoEntity();
		this.parentMenuInfoEntity = new MenuInfoEntity();
	}
	
	public MenuInfo(Long menuId) {
		this.menuInfoPK = new MenuInfoPK(menuId);
		this.menuInfoEntity = new MenuInfoEntity();
		this.menuInfoEntity.setPk(this.menuInfoPK);
		this.parentMenuInfoEntity = new MenuInfoEntity();
	}
	
	public MenuInfo(MenuInfoEntity menuInfoEntity) {
		this.menuInfoEntity = menuInfoEntity;
		this.menuInfoPK = (MenuInfoPK) this.menuInfoEntity.getPk();
		this.parentMenuInfoEntity = new MenuInfoEntity();
	}
	
	public MenuInfoPK getMenuInfoPK() {
		return menuInfoPK;
	}
	
	public MenuInfoEntity getMenuInfoEntity() {
		return menuInfoEntity;
	}
	
	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

	public MenuInfoEntity getParentMenuInfoEntity() {
		return parentMenuInfoEntity;
	}

	@Override
	public void generatePK() {
		this.menuInfoPK = menuInfoPK.generatePK();
		this.menuInfoEntity.setPk(this.menuInfoPK);
	}
	
    public enum Sqlid {
        MENUINFO_INSERT("menuInfo_insert"),
        MENUINFO_INSERT_SELECTIVE("menuInfo_insertSelective"),
        MENUINFO_UPDATE("menuInfo_update"),
        MENUINFO_UPDATE_SELECTIVE("menuInfo_updateSelective"),
        MENUINFO_DELETE("menuInfo_delete"),
        MENUINFO_DELETE_SELECTIVE("menuInfo_deleteBySelective"),
        MENUINFO_LOAD("menuInfo_load"),
        MENUINFO_SELECT_SELECTIVE("menuInfo_selectBySelective"),
        MENUINFO_SELECT_PARENT("menuInfo_selectByParent"),
        MENUINFO_COUNT_SELECTIVE("menuInfo_selectBySelectiveCount"),
        MENUFULL_SELECT_SELECTIVE("menuFull_selectBySelective");
        
        private String value;
        private Sqlid(String value) {
            this.value = value;
        }
        public String value() {
            return this.value;
        }
    }
}

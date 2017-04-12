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
package com.team.goyea.permission.model.entity;


import com.team.goyea.permission.model.pk.MenuInfoPK;
import com.yea.core.base.entity.BaseEntity;

public class MenuInfoEntity extends BaseEntity {

    private static final long serialVersionUID = 2L;
    
    private Long parentMenuId;
    
    private String menuName;
    
    private Long identifierId;
    
    private Long menuSequence;
    
    private String isValid = "01";
    
    private String isDelete = "01";
    
    public MenuInfoEntity() {
        super(new MenuInfoPK());
    }
    
    public MenuInfoEntity(MenuInfoPK pk) {
        super(pk);
    }

    public void setParentMenuId(Long parentMenuId) {
        this.parentMenuId = parentMenuId;
    }
    public Long getParentMenuId() {
        return this.parentMenuId;
    }
    
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public String getMenuName() {
        return this.menuName;
    }
    
    public void setIdentifierId(Long identifierId) {
        this.identifierId = identifierId;
    }
    public Long getIdentifierId() {
        return this.identifierId;
    }
    
    public Long getMenuSequence() {
		return menuSequence;
	}
	public void setMenuSequence(Long menuSequence) {
		this.menuSequence = menuSequence;
	}

	public void setIsValid(String isValid) {
        this.isValid = isValid;
    }
    public String getIsValid() {
        return this.isValid;
    }
    
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }
    public String getIsDelete() {
        return this.isDelete;
    }
    
}

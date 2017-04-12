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
package com.team.goyea.authorization.model;


import java.util.ArrayList;
import java.util.List;

import com.team.goyea.authorization.model.entity.RoleInfoEntity;
import com.team.goyea.authorization.model.entity.RolePermissionRelaEntity;
import com.team.goyea.authorization.model.pk.RoleInfoPK;
import com.yea.core.base.entity.BaseEntity;

public class RoleInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    private RoleInfoPK roleInfoPK;
	private RoleInfoEntity roleInfoEntity;
	private List<RolePermissionRelaEntity> listRolePermissionRelaEntity;
	
	public RoleInfo() {
		this.roleInfoPK = new RoleInfoPK();
		this.roleInfoEntity = new RoleInfoEntity();
		this.listRolePermissionRelaEntity = new ArrayList<RolePermissionRelaEntity>();
	}
	
	public RoleInfo(Long roleId) {
		this.roleInfoPK = new RoleInfoPK(roleId);
		this.roleInfoEntity = new RoleInfoEntity();
		this.roleInfoEntity.setPk(this.roleInfoPK);
		this.listRolePermissionRelaEntity = new ArrayList<RolePermissionRelaEntity>();
	}
	
	public RoleInfo(RoleInfoEntity roleInfoEntity) {
		this.roleInfoEntity = roleInfoEntity;
		this.roleInfoPK = (RoleInfoPK) this.roleInfoEntity.getPk();
		this.listRolePermissionRelaEntity = new ArrayList<RolePermissionRelaEntity>();
	}
	
	public RoleInfoPK getRoleInfoPK() {
		return roleInfoPK;
	}
	
	public RoleInfoEntity getRoleInfoEntity() {
		return roleInfoEntity;
	}
	
	public List<RolePermissionRelaEntity> getListRolePermissionRelaEntity() {
		return listRolePermissionRelaEntity;
	}

	@Override
	public void generatePK() {
		this.roleInfoPK = roleInfoPK.generatePK();
		this.roleInfoEntity.setPk(this.roleInfoPK);
	}
	
    public enum Sqlid {
        ROLEINFO_INSERT("roleInfo_insert"),
        ROLEINFO_INSERT_SELECTIVE("roleInfo_insertSelective"),
        ROLEINFO_UPDATE("roleInfo_update"),
        ROLEINFO_UPDATE_SELECTIVE("roleInfo_updateSelective"),
        ROLEINFO_DELETE("roleInfo_delete"),
        ROLEINFO_DELETE_SELECTIVE("roleInfo_deleteBySelective"),
        ROLEINFO_LOAD("roleInfo_load"),
        ROLEINFO_SELECT_SELECTIVE("roleInfo_selectBySelective"),
        ROLEINFO_COUNT_SELECTIVE("roleInfo_selectBySelectiveCount"),
        ROLEPERMISSION_LOAD("rolePermission_load");
        
        private String value;
        private Sqlid(String value) {
            this.value = value;
        }
        public String value() {
            return this.value;
        }
    }
}

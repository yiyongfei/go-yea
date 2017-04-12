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


import com.team.goyea.authorization.model.entity.RolePermissionRelaEntity;
import com.team.goyea.authorization.model.pk.RolePermissionRelaPK;
import com.yea.core.base.entity.BaseEntity;

public class RolePermissionRela extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    private RolePermissionRelaPK rolePermissionRelaPK;
	private RolePermissionRelaEntity rolePermissionRelaEntity;
	
	public RolePermissionRela() {
		this.rolePermissionRelaPK = new RolePermissionRelaPK();
		this.rolePermissionRelaEntity = new RolePermissionRelaEntity();
	}
	
	public RolePermissionRela(Long rolePermissionId) {
		this.rolePermissionRelaPK = new RolePermissionRelaPK(rolePermissionId);
		this.rolePermissionRelaEntity = new RolePermissionRelaEntity();
		this.rolePermissionRelaEntity.setPk(this.rolePermissionRelaPK);
	}
	
	public RolePermissionRela(RolePermissionRelaEntity rolePermissionRelaEntity) {
		this.rolePermissionRelaEntity = rolePermissionRelaEntity;
		this.rolePermissionRelaPK = (RolePermissionRelaPK) this.rolePermissionRelaEntity.getPk();
	}
	
	public RolePermissionRelaPK getRolePermissionRelaPK() {
		return rolePermissionRelaPK;
	}
	
	public RolePermissionRelaEntity getRolePermissionRelaEntity() {
		return rolePermissionRelaEntity;
	}
	
	@Override
	public void generatePK() {
		this.rolePermissionRelaPK = rolePermissionRelaPK.generatePK();
		this.rolePermissionRelaEntity.setPk(this.rolePermissionRelaPK);
	}
	
    public enum Sqlid {
        ROLEPERMISSIONRELA_INSERT("rolePermissionRela_insert"),
        ROLEPERMISSIONRELA_INSERT_SELECTIVE("rolePermissionRela_insertSelective"),
        ROLEPERMISSIONRELA_UPDATE("rolePermissionRela_update"),
        ROLEPERMISSIONRELA_UPDATE_SELECTIVE("rolePermissionRela_updateSelective"),
        ROLEPERMISSIONRELA_DELETE("rolePermissionRela_delete"),
        ROLEPERMISSIONRELA_DELETE_SELECTIVE("rolePermissionRela_deleteBySelective"),
        ROLEPERMISSIONRELA_LOAD("rolePermissionRela_load"),
        ROLEPERMISSIONRELA_SELECT_SELECTIVE("rolePermissionRela_selectBySelective"),
        ROLEPERMISSIONRELA_COUNT_SELECTIVE("rolePermissionRela_selectBySelectiveCount");
        
        private String value;
        private Sqlid(String value) {
            this.value = value;
        }
        public String value() {
            return this.value;
        }
    }
}

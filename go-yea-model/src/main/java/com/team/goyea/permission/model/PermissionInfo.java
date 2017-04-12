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


import com.team.goyea.permission.model.entity.PermissionInfoEntity;
import com.team.goyea.permission.model.pk.PermissionInfoPK;
import com.yea.core.base.entity.BaseEntity;

public class PermissionInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    private PermissionInfoPK permissionInfoPK;
	private PermissionInfoEntity permissionInfoEntity;
    
    private String resourceName;
    private String resourceContent;
    private String operationName;
	
	public PermissionInfo() {
		this.permissionInfoPK = new PermissionInfoPK();
		this.permissionInfoEntity = new PermissionInfoEntity();
	}
	
	public PermissionInfo(Long permissionId) {
		this.permissionInfoPK = new PermissionInfoPK(permissionId);
		this.permissionInfoEntity = new PermissionInfoEntity();
		this.permissionInfoEntity.setPk(permissionInfoPK);
	}
	
	public PermissionInfo(PermissionInfoEntity permissionInfoEntity) {
		this.permissionInfoEntity = permissionInfoEntity;
		this.permissionInfoPK = (PermissionInfoPK) this.permissionInfoEntity.getPk();
	}
	
	public PermissionInfoPK getPermissionInfoPK() {
		return permissionInfoPK;
	}
	
	public PermissionInfoEntity getPermissionInfoEntity() {
		return permissionInfoEntity;
	}
	
    public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceContent() {
		return resourceContent;
	}

	public void setResourceContent(String resourceContent) {
		this.resourceContent = resourceContent;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	@Override
	public void generatePK() {
		this.permissionInfoPK = permissionInfoPK.generatePK();
		this.permissionInfoEntity.setPk(this.permissionInfoPK);
	}
	
	public enum Sqlid {
        INSERT("permissionInfo_insert"),
        INSERT_SELECTIVE("permissionInfo_insertSelective"),
        UPDATE("permissionInfo_update"),
        UPDATE_SELECTIVE("permissionInfo_updateSelective"),
        DELETE("permissionInfo_delete"),
        DELETE_SELECTIVE("permissionInfo_deleteBySelective"),
        LOAD("permissionInfo_load"),
        SELECT_SELECTIVE("permissionInfo_selectBySelective"),
        COUNT_SELECTIVE("permissionInfo_selectBySelectiveCount"),
        SELECT_PERMISSION("permissionInfo_selectPermission");
        
        private String value;
        private Sqlid(String value) {
            this.value = value;
        }
        public String value() {
            return this.value;
        }
    }
}

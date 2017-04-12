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


import com.team.goyea.permission.model.entity.ResourceIdentifierEntity;
import com.team.goyea.permission.model.pk.ResourceIdentifierPK;
import com.yea.core.base.entity.BaseEntity;

public class ResourceIdentifier extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    private ResourceIdentifierPK resourceIdentifierPK;
	private ResourceIdentifierEntity resourceIdentifierEntity;
	private String permissionName;
	private String permissionContent;
	
	public ResourceIdentifier() {
		this.resourceIdentifierPK = new ResourceIdentifierPK();
		this.resourceIdentifierEntity = new ResourceIdentifierEntity();
	}
	
	public ResourceIdentifier(Long identifierId) {
		this.resourceIdentifierPK = new ResourceIdentifierPK(identifierId);
		this.resourceIdentifierEntity = new ResourceIdentifierEntity();
	}
	
	public ResourceIdentifier(ResourceIdentifierEntity resourceIdentifierEntity) {
		this.resourceIdentifierEntity = resourceIdentifierEntity;
		this.resourceIdentifierPK = (ResourceIdentifierPK) this.resourceIdentifierEntity.getPk();
	}
	
	public ResourceIdentifierPK getResourceIdentifierPK() {
		return resourceIdentifierPK;
	}
	
	public ResourceIdentifierEntity getResourceIdentifierEntity() {
		return resourceIdentifierEntity;
	}
	
	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getPermissionContent() {
		return permissionContent;
	}

	public void setPermissionContent(String permissionContent) {
		this.permissionContent = permissionContent;
	}

	@Override
	public void generatePK() {
		this.resourceIdentifierPK = resourceIdentifierPK.generatePK();
		this.resourceIdentifierEntity.setPk(this.resourceIdentifierPK);
	}
	
    public enum Sqlid {
        INSERT("resourceIdentifier_insert"),
        INSERT_SELECTIVE("resourceIdentifier_insertSelective"),
        UPDATE("resourceIdentifier_update"),
        UPDATE_SELECTIVE("resourceIdentifier_updateSelective"),
        DELETE("resourceIdentifier_delete"),
        DELETE_SELECTIVE("resourceIdentifier_deleteBySelective"),
        LOAD("resourceIdentifier_load"),
        SELECT_SELECTIVE("resourceIdentifier_selectBySelective"),
        COUNT_SELECTIVE("resourceIdentifier_selectBySelectiveCount"),
        SELECT_PERM_CONTENT("resourceIdentifier_selectPermissionContent");
        
        private String value;
        private Sqlid(String value) {
            this.value = value;
        }
        public String value() {
            return this.value;
        }
    }
}

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


import com.team.goyea.permission.model.pk.ResourceIdentifierPK;
import com.yea.core.base.entity.BaseEntity;

public class ResourceIdentifierEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    private Long permissionId;
    
    private String identifierName;
    
    private String identifierPath;
    
    private String identifierType;
    
    private String accessType;
    
    public ResourceIdentifierEntity() {
        super(new ResourceIdentifierPK());
    }
    
    public ResourceIdentifierEntity(ResourceIdentifierPK pk) {
        super(pk);
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
    public Long getPermissionId() {
        return this.permissionId;
    }
    
    public void setIdentifierName(String identifierName) {
        this.identifierName = identifierName;
    }
    public String getIdentifierName() {
        return this.identifierName;
    }
    
    public void setIdentifierPath(String identifierPath) {
        this.identifierPath = identifierPath;
    }
    public String getIdentifierPath() {
        return this.identifierPath;
    }
    
    public void setIdentifierType(String identifierType) {
        this.identifierType = identifierType;
    }
    public String getIdentifierType() {
        return this.identifierType;
    }

	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
    
}

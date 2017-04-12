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


import com.team.goyea.permission.model.pk.PermissionInfoPK;
import com.yea.core.base.entity.BaseEntity;

public class PermissionInfoEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    private Long resourceId;
    
    private Long operationId;
    
    private String permissionDesc;
    
    private String isValid = "01";
    
    private String isDelete = "01";
    
    private String permissionName;
    
    private String accessType;
    
    public PermissionInfoEntity() {
        super(new PermissionInfoPK());
    }
    
    public PermissionInfoEntity(PermissionInfoPK pk) {
        super(pk);
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }
    public Long getResourceId() {
        return this.resourceId;
    }
    
    public void setOperationId(Long operationId) {
        this.operationId = operationId;
    }
    public Long getOperationId() {
        return this.operationId;
    }
    
    public void setPermissionDesc(String permissionDesc) {
        this.permissionDesc = permissionDesc;
    }
    public String getPermissionDesc() {
        return this.permissionDesc;
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
    
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
    public String getPermissionName() {
        return this.permissionName;
    }
    
    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }
    public String getAccessType() {
        return this.accessType;
    }
    
}

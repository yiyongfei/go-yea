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
package com.team.goyea.authorization.model.entity;


import com.team.goyea.authorization.model.pk.RolePermissionRelaPK;
import com.yea.core.base.entity.BaseEntity;

public class RolePermissionRelaEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    private Long roleId;
    
    private Long permissionId;
    
    private String permissionWildcards;
    
    public RolePermissionRelaEntity() {
        super(new RolePermissionRelaPK());
    }
    
    public RolePermissionRelaEntity(RolePermissionRelaPK pk) {
        super(pk);
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    public Long getRoleId() {
        return this.roleId;
    }
    
    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
    public Long getPermissionId() {
        return this.permissionId;
    }
    
    public void setPermissionWildcards(String permissionWildcards) {
        this.permissionWildcards = permissionWildcards;
    }
    public String getPermissionWildcards() {
        return this.permissionWildcards;
    }
    
}

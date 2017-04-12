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


import com.team.goyea.authorization.model.pk.RoleInfoPK;
import com.yea.core.base.entity.BaseEntity;

public class RoleInfoEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    /**
     * 角色类型
     */
    private String roleType;
    
    /**
     * 角色名称
     */
    private String roleName;
    
    /**
     * 角色描述
     */
    private String roleDesc;
    
    /**
     * 是否有效：01、有效，09、无效
     */
    private String isValid = "01";
    
    private String isDelete = "01";
    
    public RoleInfoEntity() {
        super(new RoleInfoPK());
    }
    
    public RoleInfoEntity(RoleInfoPK pk) {
        super(pk);
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }
    public String getRoleType() {
        return this.roleType;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }
    public String getRoleDesc() {
        return this.roleDesc;
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

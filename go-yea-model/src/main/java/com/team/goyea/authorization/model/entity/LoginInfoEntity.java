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


import com.team.goyea.authorization.model.pk.LoginInfoPK;
import com.yea.core.base.entity.BaseEntity;

public class LoginInfoEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    private Long partyId;
    
    private String loginName;
    
    private String isPrimary;
    
    public LoginInfoEntity() {
        super(new LoginInfoPK());
    }
    
    public LoginInfoEntity(LoginInfoPK pk) {
        super(pk);
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }
    public Long getPartyId() {
        return this.partyId;
    }
    
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    public String getLoginName() {
        return this.loginName;
    }
    
    public void setIsPrimary(String isPrimary) {
        this.isPrimary = isPrimary;
    }
    public String getIsPrimary() {
        return this.isPrimary;
    }
    
}

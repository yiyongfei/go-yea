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


import com.team.goyea.authorization.model.pk.UserInfoPK;
import com.yea.core.base.entity.BaseEntity;

public class UserInfoEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    private String loginPassword;
    
    private String loginSalt;
    
    private String isLock = "01";
    
    public UserInfoEntity() {
        super(new UserInfoPK());
    }
    
    public UserInfoEntity(UserInfoPK pk) {
        super(pk);
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
    public String getLoginPassword() {
        return this.loginPassword;
    }
    
    public void setLoginSalt(String loginSalt) {
        this.loginSalt = loginSalt;
    }
    public String getLoginSalt() {
        return this.loginSalt;
    }
    
    public void setIsLock(String isLock) {
        this.isLock = isLock;
    }
    public String getIsLock() {
        return this.isLock;
    }
    
}

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


import com.team.goyea.authorization.model.entity.LoginInfoEntity;
import com.team.goyea.authorization.model.pk.LoginInfoPK;
import com.yea.core.base.entity.BaseEntity;

public class LoginInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    private LoginInfoPK loginInfoPK;
	private LoginInfoEntity loginInfoEntity;
	
	public LoginInfo() {
		this.loginInfoPK = new LoginInfoPK();
		this.loginInfoEntity = new LoginInfoEntity();
	}
	
	public LoginInfo(Long loginId) {
		this.loginInfoPK = new LoginInfoPK(loginId);
		this.loginInfoEntity = new LoginInfoEntity();
		this.loginInfoEntity.setPk(this.loginInfoPK);
	}
	
	public LoginInfo(LoginInfoEntity loginInfoEntity) {
		this.loginInfoEntity = loginInfoEntity;
		this.loginInfoPK = (LoginInfoPK) this.loginInfoEntity.getPk();
	}
	
	public LoginInfoPK getLoginInfoPK() {
		return loginInfoPK;
	}
	
	public LoginInfoEntity getLoginInfoEntity() {
		return loginInfoEntity;
	}
	
	@Override
	public void generatePK() {
		this.loginInfoPK = loginInfoPK.generatePK();
		this.loginInfoEntity.setPk(this.loginInfoPK);
	}
	
    public enum Sqlid {
        LOGININFO_INSERT("loginInfo_insert"),
        LOGININFO_INSERT_SELECTIVE("loginInfo_insertSelective"),
        LOGININFO_UPDATE("loginInfo_update"),
        LOGININFO_UPDATE_SELECTIVE("loginInfo_updateSelective"),
        LOGININFO_DELETE("loginInfo_delete"),
        LOGININFO_DELETE_SELECTIVE("loginInfo_deleteBySelective"),
        LOGININFO_LOAD("loginInfo_load"),
        LOGININFO_SELECT_SELECTIVE("loginInfo_selectBySelective"),
        LOGININFO_COUNT_SELECTIVE("loginInfo_selectBySelectiveCount");
        
        private String value;
        private Sqlid(String value) {
            this.value = value;
        }
        public String value() {
            return this.value;
        }
    }
}

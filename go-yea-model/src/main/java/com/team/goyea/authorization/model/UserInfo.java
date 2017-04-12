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


import java.util.ArrayList;
import java.util.List;

import com.team.goyea.authorization.model.entity.PartyInfoEntity;
import com.team.goyea.authorization.model.entity.PartyRoleRelaEntity;
import com.team.goyea.authorization.model.entity.PersonInfoEntity;
import com.team.goyea.authorization.model.entity.UserInfoEntity;
import com.team.goyea.authorization.model.pk.PartyInfoPK;
import com.team.goyea.authorization.model.pk.PersonInfoPK;
import com.team.goyea.authorization.model.pk.UserInfoPK;
import com.team.goyea.common.ModelConstants;
import com.yea.core.base.entity.BaseEntity;

public class UserInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    private UserInfoPK userInfoPK;
	private UserInfoEntity userInfoEntity;
	private PartyInfoEntity partyInfoEntity;
	private PersonInfoEntity personInfoEntity;
	private String username;
	private String password;
	private List<PartyRoleRelaEntity> listPartyRoleRelaEntity;
	
	public UserInfo() {
		this.userInfoPK = new UserInfoPK();
		this.userInfoEntity = new UserInfoEntity();
		this.personInfoEntity = new PersonInfoEntity();
		this.partyInfoEntity = new PartyInfoEntity();
		this.partyInfoEntity.setPartyType(ModelConstants.PARTY_TYPE.PERSON.value());
		this.listPartyRoleRelaEntity = new ArrayList<PartyRoleRelaEntity>();
	}
	
	public UserInfo(Long partyId) {
		this.userInfoPK = new UserInfoPK(partyId);
		this.userInfoEntity = new UserInfoEntity();
		this.userInfoEntity.setPk(this.userInfoPK);
		this.personInfoEntity = new PersonInfoEntity();
		this.personInfoEntity.setPk(new PersonInfoPK(partyId));
		this.partyInfoEntity = new PartyInfoEntity();
		this.partyInfoEntity.setPartyType(ModelConstants.PARTY_TYPE.PERSON.value());
		this.partyInfoEntity.setPk(new PartyInfoPK(partyId));
		this.listPartyRoleRelaEntity = new ArrayList<PartyRoleRelaEntity>();
	}
	
	public UserInfo(UserInfoEntity userInfoEntity) {
		this.userInfoEntity = userInfoEntity;
		this.userInfoPK = (UserInfoPK) this.userInfoEntity.getPk();
		this.personInfoEntity = new PersonInfoEntity();
		this.personInfoEntity.setPk(new PersonInfoPK(this.userInfoPK.getPartyId()));
		this.partyInfoEntity = new PartyInfoEntity();
		this.partyInfoEntity.setPartyType(ModelConstants.PARTY_TYPE.PERSON.value());
		this.partyInfoEntity.setPk(new PartyInfoPK(this.userInfoPK.getPartyId()));
		this.listPartyRoleRelaEntity = new ArrayList<PartyRoleRelaEntity>();
	}
	
	public UserInfoPK getUserInfoPK() {
		return userInfoPK;
	}
	
	public UserInfoEntity getUserInfoEntity() {
		return userInfoEntity;
	}
	
	public PartyInfoEntity getPartyInfoEntity() {
		return partyInfoEntity;
	}

	public PersonInfoEntity getPersonInfoEntity() {
		return personInfoEntity;
	}

	public List<PartyRoleRelaEntity> getListPartyRoleRelaEntity() {
		return listPartyRoleRelaEntity;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void generatePK() {
		this.userInfoPK = userInfoPK.generatePK();
		this.userInfoEntity.setPk(this.userInfoPK);
		this.partyInfoEntity.setPk(new PartyInfoPK(this.userInfoPK.getPartyId()));
		this.personInfoEntity.setPk(new PersonInfoPK(this.userInfoPK.getPartyId()));
	}
	
    public enum Sqlid {
        USERINFO_INSERT("userInfo_insert"),
        USERINFO_INSERT_SELECTIVE("userInfo_insertSelective"),
        USERINFO_UPDATE("userInfo_update"),
        USERINFO_UPDATE_SELECTIVE("userInfo_updateSelective"),
        USERINFO_DELETE("userInfo_delete"),
        USERINFO_DELETE_SELECTIVE("userInfo_deleteBySelective"),
        USERINFO_LOAD("userInfo_load"),
        USERINFO_SELECT_SELECTIVE("userInfo_selectBySelective"),
        USERINFO_COUNT_SELECTIVE("userInfo_selectBySelectiveCount"),
        USERROLE_LOAD("userRole_load");
        
        private String value;
        private Sqlid(String value) {
            this.value = value;
        }
        public String value() {
            return this.value;
        }
    }
}

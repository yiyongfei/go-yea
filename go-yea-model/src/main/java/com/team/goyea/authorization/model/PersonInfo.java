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


import com.yea.core.base.entity.BaseEntity;
import com.team.goyea.authorization.model.entity.PersonInfoEntity;
import com.team.goyea.authorization.model.pk.PersonInfoPK;

public class PersonInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    private PersonInfoPK personInfoPK;
	private PersonInfoEntity personInfoEntity;
	
	public PersonInfo() {
		this.personInfoPK = new PersonInfoPK();
		this.personInfoEntity = new PersonInfoEntity();
	}
	
	public PersonInfo(Long partyId) {
		this.personInfoPK = new PersonInfoPK(partyId);
		this.personInfoEntity = new PersonInfoEntity();
		this.personInfoEntity.setPk(this.personInfoPK);
	}
	
	public PersonInfo(PersonInfoEntity personInfoEntity) {
		this.personInfoEntity = personInfoEntity;
		this.personInfoPK = (PersonInfoPK) this.personInfoEntity.getPk();
	}
	
	public PersonInfoPK getPersonInfoPK() {
		return personInfoPK;
	}
	
	public PersonInfoEntity getPersonInfoEntity() {
		return personInfoEntity;
	}
	
	@Override
	public void generatePK() {
		this.personInfoPK = personInfoPK.generatePK();
		this.personInfoEntity.setPk(this.personInfoPK);
	}
	
    public enum Sqlid {
        PERSONINFO_INSERT("personInfo_insert"),
        PERSONINFO_INSERT_SELECTIVE("personInfo_insertSelective"),
        PERSONINFO_UPDATE("personInfo_update"),
        PERSONINFO_UPDATE_SELECTIVE("personInfo_updateSelective"),
        PERSONINFO_DELETE("personInfo_delete"),
        PERSONINFO_DELETE_SELECTIVE("personInfo_deleteBySelective"),
        PERSONINFO_LOAD("personInfo_load"),
        PERSONINFO_SELECT_SELECTIVE("personInfo_selectBySelective"),
        PERSONINFO_COUNT_SELECTIVE("personInfo_selectBySelectiveCount");
        
        private String value;
        private Sqlid(String value) {
            this.value = value;
        }
        public String value() {
            return this.value;
        }
    }
}

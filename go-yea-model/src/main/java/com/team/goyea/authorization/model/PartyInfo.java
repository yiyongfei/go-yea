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


import com.team.goyea.authorization.model.entity.PartyInfoEntity;
import com.team.goyea.authorization.model.pk.PartyInfoPK;
import com.yea.core.base.entity.BaseEntity;

public class PartyInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    private PartyInfoPK partyInfoPK;
	private PartyInfoEntity partyInfoEntity;
	
	public PartyInfo() {
		this.partyInfoPK = new PartyInfoPK();
		this.partyInfoEntity = new PartyInfoEntity();
	}
	
	public PartyInfo(Long partyId) {
		this.partyInfoPK = new PartyInfoPK(partyId);
		this.partyInfoEntity = new PartyInfoEntity();
		this.partyInfoEntity.setPk(this.partyInfoPK);
	}
	
	public PartyInfo(PartyInfoEntity partyInfoEntity) {
		this.partyInfoEntity = partyInfoEntity;
		this.partyInfoPK = (PartyInfoPK) this.partyInfoEntity.getPk();
	}
	
	public PartyInfoPK getPartyInfoPK() {
		return partyInfoPK;
	}
	
	public PartyInfoEntity getPartyInfoEntity() {
		return partyInfoEntity;
	}
	
	@Override
	public void generatePK() {
		this.partyInfoPK = partyInfoPK.generatePK();
		this.partyInfoEntity.setPk(this.partyInfoPK);
	}
	
    public enum Sqlid {
        PARTYINFO_INSERT("partyInfo_insert"),
        PARTYINFO_INSERT_SELECTIVE("partyInfo_insertSelective"),
        PARTYINFO_UPDATE("partyInfo_update"),
        PARTYINFO_UPDATE_SELECTIVE("partyInfo_updateSelective"),
        PARTYINFO_DELETE("partyInfo_delete"),
        PARTYINFO_DELETE_SELECTIVE("partyInfo_deleteBySelective"),
        PARTYINFO_LOAD("partyInfo_load"),
        PARTYINFO_SELECT_SELECTIVE("partyInfo_selectBySelective"),
        PARTYINFO_COUNT_SELECTIVE("partyInfo_selectBySelectiveCount");
        
        private String value;
        private Sqlid(String value) {
            this.value = value;
        }
        public String value() {
            return this.value;
        }
    }
}

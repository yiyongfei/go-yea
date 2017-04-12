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


import com.team.goyea.authorization.model.entity.PartyRoleRelaEntity;
import com.team.goyea.authorization.model.pk.PartyRoleRelaPK;
import com.yea.core.base.entity.BaseEntity;

public class PartyRoleRela extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    private PartyRoleRelaPK partyRoleRelaPK;
	private PartyRoleRelaEntity partyRoleRelaEntity;
	
	public PartyRoleRela() {
		this.partyRoleRelaPK = new PartyRoleRelaPK();
		this.partyRoleRelaEntity = new PartyRoleRelaEntity();
	}
	
	public PartyRoleRela(Long partyRoleId) {
		this.partyRoleRelaPK = new PartyRoleRelaPK(partyRoleId);
		this.partyRoleRelaEntity = new PartyRoleRelaEntity();
		this.partyRoleRelaEntity.setPk(this.partyRoleRelaPK);
	}
	
	public PartyRoleRela(PartyRoleRelaEntity partyRoleRelaEntity) {
		this.partyRoleRelaEntity = partyRoleRelaEntity;
		this.partyRoleRelaPK = (PartyRoleRelaPK) this.partyRoleRelaEntity.getPk();
	}
	
	public PartyRoleRelaPK getPartyRoleRelaPK() {
		return partyRoleRelaPK;
	}
	
	public PartyRoleRelaEntity getPartyRoleRelaEntity() {
		return partyRoleRelaEntity;
	}
	
	@Override
	public void generatePK() {
		this.partyRoleRelaPK = partyRoleRelaPK.generatePK();
		this.partyRoleRelaEntity.setPk(this.partyRoleRelaPK);
	}
	
    public enum Sqlid {
        PARTYROLERELA_INSERT("partyRoleRela_insert"),
        PARTYROLERELA_INSERT_SELECTIVE("partyRoleRela_insertSelective"),
        PARTYROLERELA_UPDATE("partyRoleRela_update"),
        PARTYROLERELA_UPDATE_SELECTIVE("partyRoleRela_updateSelective"),
        PARTYROLERELA_DELETE("partyRoleRela_delete"),
        PARTYROLERELA_DELETE_SELECTIVE("partyRoleRela_deleteBySelective"),
        PARTYROLERELA_LOAD("partyRoleRela_load"),
        PARTYROLERELA_SELECT_SELECTIVE("partyRoleRela_selectBySelective"),
        PARTYROLERELA_COUNT_SELECTIVE("partyRoleRela_selectBySelectiveCount"),
        PARTYROLERELA_INSERT_BATCH("partyRoleRela_insertBatch");
        
        private String value;
        private Sqlid(String value) {
            this.value = value;
        }
        public String value() {
            return this.value;
        }
    }
}

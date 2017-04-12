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


import com.team.goyea.authorization.model.entity.PartyRelationshipEntity;
import com.team.goyea.authorization.model.pk.PartyRelationshipPK;
import com.yea.core.base.entity.BaseEntity;

public class PartyRelationship extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    private PartyRelationshipPK partyRelationshipPK;
	private PartyRelationshipEntity partyRelationshipEntity;
	
	public PartyRelationship() {
		this.partyRelationshipPK = new PartyRelationshipPK();
		this.partyRelationshipEntity = new PartyRelationshipEntity();
	}
	
	public PartyRelationship(Long relationshipId) {
		this.partyRelationshipPK = new PartyRelationshipPK(relationshipId);
		this.partyRelationshipEntity = new PartyRelationshipEntity();
		this.partyRelationshipEntity.setPk(this.partyRelationshipPK);
	}
	
	public PartyRelationship(PartyRelationshipEntity partyRelationshipEntity) {
		this.partyRelationshipEntity = partyRelationshipEntity;
		this.partyRelationshipPK = (PartyRelationshipPK) this.partyRelationshipEntity.getPk();
	}
	
	public PartyRelationshipPK getPartyRelationshipPK() {
		return partyRelationshipPK;
	}
	
	public PartyRelationshipEntity getPartyRelationshipEntity() {
		return partyRelationshipEntity;
	}
	
	@Override
	public void generatePK() {
		this.partyRelationshipPK = partyRelationshipPK.generatePK();
		this.partyRelationshipEntity.setPk(this.partyRelationshipPK);
	}
	
    public enum Sqlid {
        PARTYRELATIONSHIP_INSERT("partyRelationship_insert"),
        PARTYRELATIONSHIP_INSERT_SELECTIVE("partyRelationship_insertSelective"),
        PARTYRELATIONSHIP_UPDATE("partyRelationship_update"),
        PARTYRELATIONSHIP_UPDATE_SELECTIVE("partyRelationship_updateSelective"),
        PARTYRELATIONSHIP_DELETE("partyRelationship_delete"),
        PARTYRELATIONSHIP_DELETE_SELECTIVE("partyRelationship_deleteBySelective"),
        PARTYRELATIONSHIP_LOAD("partyRelationship_load"),
        PARTYRELATIONSHIP_SELECT_SELECTIVE("partyRelationship_selectBySelective"),
        PARTYRELATIONSHIP_COUNT_SELECTIVE("partyRelationship_selectBySelectiveCount");
        
        private String value;
        private Sqlid(String value) {
            this.value = value;
        }
        public String value() {
            return this.value;
        }
    }
}

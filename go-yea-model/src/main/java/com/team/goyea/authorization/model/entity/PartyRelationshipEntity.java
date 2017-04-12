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

import java.util.Date;

import com.team.goyea.authorization.model.pk.PartyRelationshipPK;
import com.yea.core.base.entity.BaseEntity;

public class PartyRelationshipEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    private String relationshipTypeCode;
    
    private Long srcPartyRoleId;
    
    private Long tagPartyRoleId;
    
    private Date startDate;
    
    private Date endDate;
    
    public PartyRelationshipEntity() {
        super(new PartyRelationshipPK());
    }
    
    public PartyRelationshipEntity(PartyRelationshipPK pk) {
        super(pk);
    }

    public void setRelationshipTypeCode(String relationshipTypeCode) {
        this.relationshipTypeCode = relationshipTypeCode;
    }
    public String getRelationshipTypeCode() {
        return this.relationshipTypeCode;
    }
    
    public void setSrcPartyRoleId(Long srcPartyRoleId) {
        this.srcPartyRoleId = srcPartyRoleId;
    }
    public Long getSrcPartyRoleId() {
        return this.srcPartyRoleId;
    }
    
    public void setTagPartyRoleId(Long tagPartyRoleId) {
        this.tagPartyRoleId = tagPartyRoleId;
    }
    public Long getTagPartyRoleId() {
        return this.tagPartyRoleId;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getStartDate() {
        return this.startDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public Date getEndDate() {
        return this.endDate;
    }
    
}

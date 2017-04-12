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
package com.team.goyea.authorization.model.pk;


import com.yea.core.base.entity.BasePK;
import com.yea.core.base.id.RandomIDGennerator;

public class PartyRoleRelaPK extends BasePK {

    private static final long serialVersionUID = 1L;
    
    private Long partyRoleId;
    
    public PartyRoleRelaPK() {
    }
    
    public PartyRoleRelaPK(Long partyRoleId) {
	    this.partyRoleId = partyRoleId;
    }

    public void setPartyRoleId(Long partyRoleId) {
        this.partyRoleId = partyRoleId;
    }
    public Long getPartyRoleId() {
        return this.partyRoleId;
    }
    
    public boolean isEmptyPK() {
        return
	          (partyRoleId == null)
	       
               ? true : false;
    }
    
    public PartyRoleRelaPK generatePK() {
        return new PartyRoleRelaPK(
	            RandomIDGennerator.get().generate()
	       
                );
    }
}

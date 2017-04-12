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
package com.team.goyea.permission.model.entity;


import com.team.goyea.permission.model.pk.OperationInfoPK;
import com.yea.core.base.entity.BaseEntity;

public class OperationInfoEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    private String operationName;
    
    private String operationDesc;
    
    public OperationInfoEntity() {
        super(new OperationInfoPK());
    }
    
    public OperationInfoEntity(OperationInfoPK pk) {
        super(pk);
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }
    public String getOperationName() {
        return this.operationName;
    }
    
    public void setOperationDesc(String operationDesc) {
        this.operationDesc = operationDesc;
    }
    public String getOperationDesc() {
        return this.operationDesc;
    }
    
}

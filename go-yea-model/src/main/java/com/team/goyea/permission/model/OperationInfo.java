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
package com.team.goyea.permission.model;


import com.team.goyea.permission.model.entity.OperationInfoEntity;
import com.team.goyea.permission.model.pk.OperationInfoPK;
import com.yea.core.base.entity.BaseEntity;

public class OperationInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    private OperationInfoPK operationInfoPK;
	private OperationInfoEntity operationInfoEntity;
	
	public OperationInfo() {
		this.operationInfoPK = new OperationInfoPK();
		this.operationInfoEntity = new OperationInfoEntity();
	}
	
	public OperationInfo(Long operationId) {
		this.operationInfoPK = new OperationInfoPK(operationId);
		this.operationInfoEntity = new OperationInfoEntity();
		this.operationInfoEntity.setPk(this.operationInfoPK);
	}
	
	public OperationInfo(OperationInfoEntity operationInfoEntity) {
		this.operationInfoEntity = operationInfoEntity;
		this.operationInfoPK = (OperationInfoPK) this.operationInfoEntity.getPk();
	}
	
	public OperationInfoPK getOperationInfoPK() {
		return operationInfoPK;
	}
	
	public OperationInfoEntity getOperationInfoEntity() {
		return operationInfoEntity;
	}
	
	@Override
	public void generatePK() {
		this.operationInfoPK = operationInfoPK.generatePK();
		this.operationInfoEntity.setPk(this.operationInfoPK);
	}
	
    public enum Sqlid {
        INSERT("operationInfo_insert"),
        INSERT_SELECTIVE("operationInfo_insertSelective"),
        UPDATE("operationInfo_update"),
        UPDATE_SELECTIVE("operationInfo_updateSelective"),
        DELETE("operationInfo_delete"),
        DELETE_SELECTIVE("operationInfo_deleteBySelective"),
        LOAD("operationInfo_load"),
        SELECT_SELECTIVE("operationInfo_selectBySelective"),
        COUNT_SELECTIVE("operationInfo_selectBySelectiveCount");
        
        private String value;
        private Sqlid(String value) {
            this.value = value;
        }
        public String value() {
            return this.value;
        }
    }
}

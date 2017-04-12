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


import com.team.goyea.permission.model.entity.ResourceInfoEntity;
import com.team.goyea.permission.model.pk.ResourceInfoPK;
import com.yea.core.base.entity.BaseEntity;

public class ResourceInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    private ResourceInfoPK resourceInfoPK;
	private ResourceInfoEntity resourceInfoEntity;
	
	public ResourceInfo() {
		this.resourceInfoPK = new ResourceInfoPK();
		this.resourceInfoEntity = new ResourceInfoEntity();
	}
	
	public ResourceInfo(Long resourceId) {
		this.resourceInfoPK = new ResourceInfoPK(resourceId);
		this.resourceInfoEntity = new ResourceInfoEntity();
		this.resourceInfoEntity.setPk(this.resourceInfoPK);
	}
	
	public ResourceInfo(ResourceInfoEntity resourceInfoEntity) {
		this.resourceInfoEntity = resourceInfoEntity;
		this.resourceInfoPK = (ResourceInfoPK) this.resourceInfoEntity.getPk();
	}
	
	public ResourceInfoPK getResourceInfoPK() {
		return resourceInfoPK;
	}
	
	public ResourceInfoEntity getResourceInfoEntity() {
		return resourceInfoEntity;
	}
	
	@Override
	public void generatePK() {
		this.resourceInfoPK = resourceInfoPK.generatePK();
		this.resourceInfoEntity.setPk(this.resourceInfoPK);
	}
	
    public enum Sqlid {
        INSERT("resourceInfo_insert"),
        INSERT_SELECTIVE("resourceInfo_insertSelective"),
        UPDATE("resourceInfo_update"),
        UPDATE_SELECTIVE("resourceInfo_updateSelective"),
        DELETE("resourceInfo_delete"),
        DELETE_SELECTIVE("resourceInfo_deleteBySelective"),
        LOAD("resourceInfo_load"),
        SELECT_SELECTIVE("resourceInfo_selectBySelective"),
        COUNT_SELECTIVE("resourceInfo_selectBySelectiveCount"),
        SELECT_PARENT("resourceInfo_selectByParent");
        
        private String value;
        private Sqlid(String value) {
            this.value = value;
        }
        public String value() {
            return this.value;
        }
    }
}

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


import com.team.goyea.permission.model.pk.ResourceInfoPK;
import com.yea.core.base.entity.BaseEntity;

public class ResourceInfoEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    private Long parentResourceId;
    
    private String resourceName;
    
    private String resourceContent;
    
    private String resourceDesc;
    
    public ResourceInfoEntity() {
        super(new ResourceInfoPK());
    }
    
    public ResourceInfoEntity(ResourceInfoPK pk) {
        super(pk);
    }

    public void setParentResourceId(Long parentResourceId) {
        this.parentResourceId = parentResourceId;
    }
    public Long getParentResourceId() {
        return this.parentResourceId;
    }
    
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
    public String getResourceName() {
        return this.resourceName;
    }
    
    public void setResourceContent(String resourceContent) {
        this.resourceContent = resourceContent;
    }
    public String getResourceContent() {
        return this.resourceContent;
    }
    
    public void setResourceDesc(String resourceDesc) {
        this.resourceDesc = resourceDesc;
    }
    public String getResourceDesc() {
        return this.resourceDesc;
    }
    
}

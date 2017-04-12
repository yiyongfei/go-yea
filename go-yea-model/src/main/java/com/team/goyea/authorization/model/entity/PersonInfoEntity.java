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
import org.springframework.format.annotation.DateTimeFormat;

import com.team.goyea.authorization.model.pk.PersonInfoPK;
import com.yea.core.base.entity.BaseEntity;

public class PersonInfoEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    private String personName;
    
    private String sexTypeCode;
    
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private Date birthDate;
    
    private String maritalTypeCode;
    
    private String personMemo;
    
    public PersonInfoEntity() {
        super(new PersonInfoPK());
    }
    
    public PersonInfoEntity(PersonInfoPK pk) {
        super(pk);
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
    public String getPersonName() {
        return this.personName;
    }
    
    public void setSexTypeCode(String sexTypeCode) {
        this.sexTypeCode = sexTypeCode;
    }
    public String getSexTypeCode() {
        return this.sexTypeCode;
    }
    
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    public Date getBirthDate() {
        return this.birthDate;
    }
    
    public void setMaritalTypeCode(String maritalTypeCode) {
        this.maritalTypeCode = maritalTypeCode;
    }
    public String getMaritalTypeCode() {
        return this.maritalTypeCode;
    }
    
    public void setPersonMemo(String personMemo) {
        this.personMemo = personMemo;
    }
    public String getPersonMemo() {
        return this.personMemo;
    }
    
}

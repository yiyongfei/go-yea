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
package com.team.goyea.permission.model.pk;


import com.yea.core.base.entity.BasePK;
import com.yea.core.base.id.RandomIDGennerator;

public class MenuInfoPK extends BasePK {

    private static final long serialVersionUID = 1L;
    
    private Long menuId;
    
    public MenuInfoPK() {
    }
    
    public MenuInfoPK(Long menuId) {
	    this.menuId = menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
    public Long getMenuId() {
        return this.menuId;
    }
    
    public boolean isEmptyPK() {
        return
	          (menuId == null)
	       
               ? true : false;
    }
    
    public MenuInfoPK generatePK() {
        return new MenuInfoPK(
	            RandomIDGennerator.get().generate()
	       
                );
    }
}

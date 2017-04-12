
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav metismenu" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">${loginuser.personName}</strong>
                             </span> </span> </a>
                            
                        </div>
                        <div class="logo-element">
                            Y+
                        </div>
                    </li>
                    
                    <#list systemMenu.menus?if_exists as menu>
			        <li <#if (systemMenu.currMenu?? && menu.menuPath?contains(systemMenu.currMenu)) >class="active"</#if>>
                    <#if (menu.childMenu?size > 0) >
					    <a href="${menu.menuPath}"><i class="fa fa-sitemap"></i> <span class="nav-label">${menu.menuName}</span> <span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <#list menu.childMenu?if_exists as childMenu>
                            <li <#if (systemMenu.currMenu?? && childMenu.menuPath?contains(systemMenu.currMenu)) >class="active"</#if> ><a href="${childMenu.menuPath}">${childMenu.menuName}</a></li>
                            </#list>
                        </ul>
					<#else>
					    <a href="${menu.menuPath}"><i class="fa fa-diamond"></i> <span class="nav-label">${menu.menuName}</span></a>
					</#if>
                    </li>
			      	</#list>
                    
                </ul>

            </div>
        </nav>
   
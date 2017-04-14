	<div class="row border-bottom">
	    <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
	        <div class="navbar-header">
	            <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
	            
	        </div>
	        <ul class="nav navbar-top-links navbar-right">
	            <li>
	                <span class="m-r-sm text-muted welcome-message">欢迎您来到管理平台</span>
	            </li>
	            <li>
	              <#if (loginuser.partyId)??>
	                <a href="${basepath}/logout.html">
	                    <i class="fa fa-sign-out"></i> Log out
	                </a>
	              <#else>
	                <a href="${basepath}/login.html">
	                    <i class="fa fa-sign-out"></i> Log in
	                </a>
				  </#if>
	                
	            </li>
	        </ul>
	    </nav>
	</div>
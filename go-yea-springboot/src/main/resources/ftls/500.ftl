<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>管理平台</title>
    
    <#include "/common/css.ftl"/>
</head>

<body>
	<div id="wrapper">
	
        <div class="row border-bottom">
		    <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
		        <ul class="nav navbar-top-links navbar-right">
		            <li>
		                <span class="m-r-sm text-muted welcome-message"></span>
		            </li>
		            <li>
		                <a href="${basepath}/index.html">
		                    <i class="fa fa-sign-out"></i> 首页
		                </a>  
		            </li>
		        </ul>
		    </nav>
		</div>
        <div class="middle-box text-center animated fadeInDown">
	        <h1>500</h1>
	        <h3 class="font-bold">Internal Server Error</h3>
	
	        <div class="error-desc">
	            ${error}
	        </div>
	    </div>
    	<#include "/common/footer.ftl"/> 
	</div>
	
</body>
</html>

<#include "/common/script.ftl"/>

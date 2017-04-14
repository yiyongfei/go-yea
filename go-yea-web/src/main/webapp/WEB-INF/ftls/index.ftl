<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>YEA首页</title>
    
    <#include "/common/css.ftl"/>
</head>

<body>
	<div id="wrapper">
	    <#include "/common/menu.ftl"/>
	        
	    <div id="page-wrapper" class="gray-bg dashbard-1">
	    
	        <#include "/common/header.ftl"/>
	        
	        <div class="wrapper wrapper-content animated fadeInRight">
	            <div class="row">
	                <div class="col-lg-12">
                    <div class="text-center m-t-lg">
                        <h1>
                            Welcome in YEA
                        </h1>
                        <small>
                            It is an application skeleton for a typical web app. You can use it to quickly bootstrap your webapp projects and dev environment for these projects.
                        </small>
                    </div>
                </div>
	            </div>
	        </div>
	        
	    	<#include "/common/footer.ftl"/>
	
	    </div>
	</div>
	
</body>
</html>

<#include "/common/script.ftl"/>

<!-- Custom and plugin javascript -->
<script src="${basepath}/js/plugins/pace/pace.min.js"></script>

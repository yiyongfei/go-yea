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
	    <#include "/common/menu.ftl"/>
	        
	    <div id="page-wrapper" class="gray-bg dashbard-1">
	    
	        <#include "/common/header.ftl"/>
	        
	        <div class="wrapper wrapper-content animated fadeInRight">
	            <div class="row">
	                <div class="col-lg-12">
	                    <div class="ibox float-e-margins">
	                        <div class="ibox float-e-margins">
			                    <div class="ibox-title">
			                        <h5>生成结果 <small class="m-l-sm">   ${message}</small></h5>
			                        <div class="ibox-tools">
			                            <a class="collapse-link">
			                                <i class="fa fa-chevron-up"></i>
			                            </a>
			                        </div>
			                    </div>
			                    <div class="ibox-content">
			                        <p>
			                            <strong>生成结果：</strong><br/>
			                           <#list tablenames?if_exists as tablename>
							            ${tablename}<br/>
	                                   </#list>
			                        </p>
			                    </div>
			                    <form action="/developer/generator/load.html" method="post" class="form-horizontal" >
	                                <div class="hr-line-dashed"></div>
	                                <div class="form-group">
	                                    <div class="col-sm-12 col-sm-offset-10">
	                                        <button class="btn btn-primary" type="submit">返回</button>
	                                    </div>
	                                </div>
	                            </form>
			                </div>
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
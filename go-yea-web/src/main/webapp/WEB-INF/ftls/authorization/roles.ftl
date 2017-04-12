<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>管理平台</title>
    
    <#include "/common/css.ftl"/>
    <!-- FooTable（与表格相关） -->
    <link href="${basepath}/css/plugins/footable/footable.core.css" rel="stylesheet">
    
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
	                        <div class="ibox-title">
	                            <h5>角色设置</h5>
	                            <div class="ibox-tools">
	                                <a class="collapse-link">
	                                    <i class="fa fa-chevron-up"></i>
	                                </a>
	                            </div>
	                        </div>
	                        <div class="ibox-content">
	                            <table class="footable table table-stripped toggle-arrow-tiny">
	                                <thead>
	                                <tr>
	                                    <th data-toggle="true">角色名</th>
	                                    <th>角色类型</th>
	                                    <th data-hide="all">角色描述</th>
	                                    <th>操作</th>
	                                </tr>
	                                </thead>
	                                <tbody>
	                                <#list roles?if_exists as role>
							        <tr>
	                                    <td>${role.roleInfoEntity.roleName}</td>
	                                    <td>${role.roleInfoEntity.roleTypeName}</td>
	                                    <td>${role.roleInfoEntity.roleDesc}</td>
	                                    <td><a onclick="updateRole('${role.roleInfoPK.roleId}')" >更新</a> | <a onclick="loadRolePermission('${role.roleInfoPK.roleId}')" >赋权</a></td>
	                                </tr>
							      	</#list>
	                                </tbody>
	                                <tfoot>
	                                <tr>
	                                    <td colspan="5">
	                                        <ul class="pagination pull-right"></ul>
	                                    </td>
	                                </tr>
	                                </tfoot>
	                            </table>
	                        </div>
	                        <div class="hr-line-dashed"></div>
                            <div class="form-group">
                            	<form id="roleForm" method="get" class="form-horizontal" >
	                                <input name="roleId" type="hidden" class="form-control" value="">
	                                <div class="col-sm-12 col-sm-offset-10">
	                                    <button class="btn btn-primary" id="addRoldButton" >新增</button>
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

<!-- Custom and plugin javascript -->
<!-- FooTable -->
<script src="${basepath}/js/plugins/footable/footable.all.min.js"></script>

<!-- Chosen -->
<script src="${basepath}/js/plugins/chosen/chosen.jquery.js"></script>
        
<!-- Page-Level Scripts -->
    <script>
        
        $(document).ready(function() {
            $('.footable').footable();
            $('.footable2').footable();
            
            $("#addRoldButton").bind("click",function(){
	            $("[name='roleId']").val("");
	            $("#roleForm").attr("action","/authorization/role/load.html");
	            $("#roleForm").submit();
			});
        });
        
        function updateRole(roleId) {
        	$("[name='roleId']").val(roleId);
        	$("#roleForm").attr("action","/authorization/role/load.html");
	        $("#roleForm").submit();
        }
        
        function loadRolePermission(roleId){
       	 	$("[name='roleId']").val(roleId);
        	$("#roleForm").attr("action","/authorization/role/permission/load.html");
	        $("#roleForm").submit();
        }
    </script>
    
    
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
	                        <div class="ibox-title">
	                            <h5>角色设置</h5>
	                            <div class="ibox-tools">
	                                <a class="collapse-link">
	                                    <i class="fa fa-chevron-up"></i>
	                                </a>
	                            </div>
	                        </div>
	                        <div class="ibox-content">
	                            <form id="roleForm" method="post" class="form-horizontal" >
	                                <input name="roleInfoPK.roleId" type="hidden" class="form-control" value="${role.roleInfoPK.roleId}">
	                                <input name="roleInfoEntity.roleType" type="hidden" class="form-control" value="0100">
	                                <input name="roleInfoEntity.isValid" type="hidden" class="form-control" value="${role.roleInfoEntity.isValid}">
	                                <input name="roleInfoEntity.isDelete" type="hidden" class="form-control" value="${role.roleInfoEntity.isDelete}">
	                                <div class="form-group">
	                                    <label class="col-sm-2 control-label">角色名</label>
	                                    <div class="col-sm-3"><input name="roleInfoEntity.roleName" type="text" class="form-control" value="${role.roleInfoEntity.roleName}"></div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="col-sm-2 control-label">角色描述</label>
	                                    <div class="col-sm-8">
	                                        <textarea class="form-control" name="roleInfoEntity.roleDesc" rows="3" placeholder="请输入角色描述 ...">${role.roleInfoEntity.roleDesc}</textarea>
	                                    </div>
	                                    
	                                </div>
	                                
	                                <div class="hr-line-dashed"></div>
	                                <div class="form-group">
	                                    <div class="col-sm-12 col-sm-offset-10">
	                                        <button class="btn btn-primary" id="saveButton">保存</button>
	                                        <button class="btn btn-primary" id="returnButton">返回</button>
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

<!-- Custom and plugin javascript -->
	<script>
        
        $(document).ready(function() {
            
            $("#saveButton").bind("click",function(){
	            $("#roleForm").attr("action","${basepath}/authorization/role/save.html");
	            $("#roleForm").submit();
			});
			
			$("#returnButton").bind("click",function(){
	            $("#roleForm").attr("action","${basepath}/authorization/role/query.html");
	            $("#roleForm").submit();
			});
        });
        
    </script>
    
    
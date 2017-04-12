<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>管理平台</title>
    
    <#include "/common/css.ftl"/>
    <!-- FooTable（与表格相关） -->
    <link href="${basepath}/css/plugins/footable/footable.core.css" rel="stylesheet">
    
    <!-- Chosen（与下拉框相关） -->
    <link href="${basepath}/css/plugins/chosen/bootstrap-chosen.css" rel="stylesheet">
    
    
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
	                            <h5>角色赋权</h5>
	                            <div class="ibox-tools">
	                                <a class="collapse-link">
	                                    <i class="fa fa-chevron-up"></i>
	                                </a>
	                            </div>
	                        </div>
	                        <div class="ibox-content">
	                            <form id="rolePermissionForm" method="post" class="form-horizontal" >
	                                <input name="roleId" type="hidden" class="form-control" value="${role.roleInfoPK.roleId}">
	                                <div class="form-group">
	                                    <label class="col-sm-2 control-label">角色名</label>
	                                    <div class="col-sm-3"><input type="text" disabled="" class="form-control" value="${role.roleInfoEntity.roleName}"></div>
	                                </div>
	                                
	                                <div class="form-group">
	                                    <label class="col-sm-2 control-label">角色描述</label>
	                                    <div class="col-sm-8">
	                                        <textarea disabled="" class="form-control" rows="3" placeholder="请输入角色描述 ...">${role.roleInfoEntity.roleDesc}</textarea>
	                                    </div>
	                                    
	                                </div>
	                                
	                                <div class="form-group" id="resource_select_area">
		                                <label class="col-sm-2 control-label">资源内容</label>
		                                <input name="resourceId" type="hidden" class="form-control" value="">
	                                </div>
	                                <div class="form-group" id="operation_select_area">
		                                <label class="col-sm-2 control-label">操作名</label>
		                                <div class="col-sm-2"><select name='operationId' class='chosen-select' tabindex='2'></select></div>
	                                </div>
	                                
	                                <div class="hr-line-dashed"></div>
	                                <div class="form-group">
	                                    <div class="col-sm-12 col-sm-offset-10">
	                                        <button class="btn btn-primary" id="saveButton" >保存</button>
	                                        <button class="btn btn-primary" id="returnButton">返回</button>
	                                    </div>
	                                </div>
	                            </form>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            <div class="row">
	                <div class="col-lg-12">
	                    <div class="ibox float-e-margins">
	                        <div class="ibox-content">
	                            <table class="footable table table-stripped toggle-arrow-tiny">
	                                <thead>
	                                <tr>
	                                    <th>权限内容</th>
	                                    <th width="150">操作</th>
	                                </tr>
	                                </thead>
	                                <tbody>
	                                <#list role.listRolePermissionRelaEntity?if_exists as permission>
							        <tr>
	                                    <td>${permission.permissionWildcards}</td>
	                                    <td><a onclick="deleteRolePermission('${permission.pk.rolePermissionId}')" >删除</a></td>
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
		
		function initResource(){
			getResource($("[name='resourceId']").get(0));
		}
		
		function selectResource(object){
		    tmp=$(object).parent().nextAll();
			tmp.remove();
		    $("[name='resourceId']").val(object.value);
			getResource(object);
		}
		
		function getResource(object) {
		    initOperation();
		    $.ajax({
			    url:'/permission/resource/query/parent',
			    type:'GET',
			    async:true,
			    data:{
			        parentResourceId:object.value
			    },
			    timeout:5000,    //超时时间
			    dataType:'json', //返回的数据格式：json/xml/html/script/jsonp/text
			    success:function(data, textStatus, jqXHR){
			        if(data.length > 0) {
			            $('#resource_select_area').append("<div class='col-sm-2'>");
				        $('#resource_select_area').find("div:last").append("<select name='_resourceid' class='chosen-select' tabindex='2'>");
				        
				        lastSelect = $('#resource_select_area').find("select:last");
				        lastSelect.append("<option value='*'>请选择</option>");
				        $.each(data, function(index, content){
				            lastSelect.append("<option value='"+content.resourceInfoPK.resourceId+"'>"+content.resourceInfoEntity.resourceName+"</option>");
						});
						lastSelect.on("change", function(e, params) {
						    selectResource(this);
						});
						$("[name='_resourceid']").chosen({width: "100%"});
						$("[name='_resourceid']").trigger("chosen:updated");
						
			        } else {
			            getOperation($("[name='resourceId']").val());
			        }
			    }
			})
		}
		
		function getOperation(resourceId) {
		    $.ajax({
			    url:'/permission/permission/operation/query',
			    type:'GET',
			    async:true,
			    data:{
			        resourceId:resourceId
			    },
			    timeout:5000,    //超时时间
			    dataType:'json', //返回的数据格式：json/xml/html/script/jsonp/text
			    success:function(data, textStatus, jqXHR){
			        if(data.length > 0) {
			            lastSelect.children().remove();
			            lastSelect = $("[name='operationId']");
			            lastSelect.append("<option value=''>请选择</option>");
				        $.each(data, function(index, content){
				            lastSelect.append("<option value='"+content.permissionInfoEntity.operationId+"'>"+content.operationName+"</option>");
						});
						lastSelect.chosen({width: "100%"});
						lastSelect.trigger("chosen:updated");
			        }
			    }
			})
		}
		
        function initOperation() {
		    lastSelect = $("[name='operationId']");
		    lastSelect.children().remove();
		    lastSelect.append("<option value=''>请选择</option>");
            lastSelect.chosen({width: "100%"});
            lastSelect.trigger("chosen:updated");
		}
        
        $(document).ready(function() {
            $('.footable').footable();
            $('.footable2').footable();
            initResource();
            initOperation();
            
            $("#saveButton").bind("click",function(){
	            if($("[name='resourceId']").val() == "") {
	                alert("为角色赋权时必须设置资源！");
	                return false;
	            }
	            $("#rolePermissionForm").attr("action","/authorization/role/permission/save.html");
	            $("#rolePermissionForm").submit();
			});
			
			$("#returnButton").bind("click",function(){
	            $("#rolePermissionForm").attr("action","/authorization/role/query.html");
	            $("#rolePermissionForm").submit();
			});
			
        });
        
        function deleteRolePermission(rolePermissionId) {
            $("#rolePermissionForm").attr("action","/authorization/role/permission/delete.html?rolePermissionId="+rolePermissionId);
            $("#rolePermissionForm").submit();
		};
    </script>
    
    
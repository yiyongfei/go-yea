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
	                            <h5>权限设置</h5>
	                            <div class="ibox-tools">
	                                <a class="collapse-link">
	                                    <i class="fa fa-chevron-up"></i>
	                                </a>
	                            </div>
	                        </div>
	                        <div class="ibox-content">
	                            <form id="permissionForm" action="/permission/permission/save.html" method="post" class="form-horizontal" >
	                                
	                                <div class="form-group">
	                                    <label class="col-sm-2 control-label">权限名</label>
	                                    <div class="col-sm-3"><input name="permissionInfoEntity.permissionName" type="text" class="form-control"></div>
	                                </div>
	                                <div class="form-group" id="resource_select_area">
		                                <label class="col-sm-2 control-label">资源内容</label>
		                                <input name="permissionInfoEntity.resourceId" type="hidden" class="form-control" value="">
	                                </div>
	                                <div class="form-group" id="operation_select_area">
		                                <label class="col-sm-2 control-label">操作名</label>
		                                <div class="col-sm-2"><select name='permissionInfoEntity.operationId' class='chosen-select' tabindex='2'></select></div>
		                                
	                                </div>
	                                <div class="form-group">
	                                    <label class="col-sm-2 control-label">权限描述</label>
	                                    <div class="col-sm-8">
	                                        <textarea class="form-control" name="permissionInfoEntity.permissionDesc" rows="3" placeholder="请输入权限描述 ..."></textarea>
	                                    </div>
	                                    
	                                </div>
	                                
	                                <div class="hr-line-dashed"></div>
	                                <div class="form-group">
	                                    <div class="col-sm-12 col-sm-offset-10">
	                                        <button class="btn btn-primary" id="savePermissionButton">新增</button>
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
	                                    <th data-toggle="true">权限名</th>
	                                    <th>资源内容</th>
	                                    <th>操作名</th>
	                                    <th data-hide="all">权限描述</th>
	                                </tr>
	                                </thead>
	                                <tbody>
	                                <#list permissions?if_exists as permission>
							        <tr>
	                                    <td>${permission.permissionInfoEntity.permissionName}</td>
	                                    <td>${permission.resourceContent}</td>
	                                    <td>${permission.operationName}</td>
	                                    <td>${permission.permissionInfoEntity.permissionDesc}</td>
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
			getResource($("[name='permissionInfoEntity.resourceId']").get(0));
		}
		
		function selectResource(object){
		    tmp=$(object).parent().nextAll();
			tmp.remove();
		    $("[name='permissionInfoEntity.resourceId']").val(object.value);
			getResource(object);
		}
		
		function getResource(object) {
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
				        lastSelect.append("<option value=''>请选择</option>");
				        $.each(data, function(index, content){
				            lastSelect.append("<option value='"+content.resourceInfoPK.resourceId+"'>"+content.resourceInfoEntity.resourceName+"</option>");
						});
						lastSelect.on("change", function(e, params) {
						    selectResource(this);
						});
						$("[name='_resourceid']").chosen({width: "100%"});
						$("[name='_resourceid']").trigger("chosen:updated");
			        }
			    }
			})
		}
		
		function initOperation() {
		    $.ajax({
			    url:'/permission/operation/query',
			    type:'GET',
			    async:true,
			    timeout:5000,    //超时时间
			    dataType:'json', //返回的数据格式：json/xml/html/script/jsonp/text
			    success:function(data, textStatus, jqXHR){
			        if(data.length > 0) {
			            lastSelect = $("[name='permissionInfoEntity.operationId']");
			            lastSelect.append("<option value=''>请选择</option>");
				        $.each(data, function(index, content){
				            lastSelect.append("<option value='"+content.operationInfoPK.operationId+"'>"+content.operationInfoEntity.operationName+"</option>");
						});
						lastSelect.chosen({width: "100%"});
						lastSelect.trigger("chosen:updated");
			        }
			    }
			})
		}
		
        $(document).ready(function() {
            $('.footable').footable();
            $('.footable2').footable();
            initResource();
            initOperation();
            
            $("#savePermissionButton").bind("click",function(){
	            permisstionName = $("[name='permissionInfoEntity.permissionName']");
	            if(permisstionName.val() == "") {
	                alert("权限名不允许为空！");
	                return false;
	            }
	            lastResourceSelect = $('#resource_select_area').find("select:last");
	            if(lastResourceSelect.val() == "") {
	                alert("资源只能选择叶子资源！");
	                return false;
	            }
	            lastOperationSelect = $("[name='permissionInfoEntity.operationId']");
	            if(lastOperationSelect.val() == "") {
	                alert("操作名不允许为空！");
	                return false;
	            }
	            $("#permissionForm").submit();
			});
        });
        
        
        
    </script>
    
    
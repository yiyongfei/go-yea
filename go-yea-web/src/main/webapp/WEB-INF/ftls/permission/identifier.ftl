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
	                            <h5>资源标识设置</h5>
	                            <div class="ibox-tools">
	                                <a class="collapse-link">
	                                    <i class="fa fa-chevron-up"></i>
	                                </a>
	                            </div>
	                        </div>
	                        <div class="ibox-content">
	                            <form id="resourceIdentifierForm" method="post" class="form-horizontal" >
	                                <input name="resourceIdentifierPK.identifierId" type="hidden" class="form-control" value="${identifier.resourceIdentifierPK.identifierId}">
	                                <div class="form-group">
	                                    <label class="col-sm-2 control-label">标识名称</label>
	                                    <div class="col-sm-3"><input type="text" class="form-control" name="resourceIdentifierEntity.identifierName" value="${identifier.resourceIdentifierEntity.identifierName}"></div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="col-sm-2 control-label">标识路径</label>
	                                    <div class="col-sm-8"><input type="text" class="form-control" name="resourceIdentifierEntity.identifierPath" value="${identifier.resourceIdentifierEntity.identifierPath}"></div>
	                                </div>
	                                <div class="form-group" id="operation_select_area">
		                                <label class="col-sm-2 control-label">标识类型</label>
		                                <div class="col-sm-2">
		                                    <select name='resourceIdentifierEntity.identifierType' class='chosen-select' tabindex='2'>
		                                    </select>
		                                </div>
		                                <div class="col-sm-8">
                                            <label class="col-sm-2 control-label"></label>
                                            <div class="radio radio-info radio-inline">
                                                <input type="radio" name="resourceIdentifierEntity.accessType" id="radio1" value="01" checked="">
                                                <label for="radio1">
                                                    匿名访问
                                                </label>
                                            </div>
                                            <div class="radio radio-info radio-inline">
                                                <input type="radio" name="resourceIdentifierEntity.accessType" id="radio2" value="02" >
                                                <label for="radio2">
                                                    基于用户
                                                </label>
                                            </div>
                                            <div class="radio radio-info radio-inline">
                                                <input type="radio" name="resourceIdentifierEntity.accessType" id="radio3" value="03" >
                                                <label for="radio3">
                                                    登录验证
                                                </label>
                                            </div>
                                            <div class="radio radio-info radio-inline">
                                                <input type="radio" name="resourceIdentifierEntity.accessType" id="radio4" value="04" >
                                                <label for="radio4">
                                                    授权访问
                                                </label>
                                            </div>
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
	                                    <th>资源标识名称</th>
	                                    <th>资源标识路径</th>
	                                    <th>权限内容</th>
	                                    <th width="150">操作</th>
	                                </tr>
	                                </thead>
	                                <tbody>
	                                <#list identifiers?if_exists as ridentifier>
							        <tr>
	                                    <td>${ridentifier.resourceIdentifierEntity.identifierName}</td>
	                                    <td>${ridentifier.resourceIdentifierEntity.identifierPath}</td>
	                                    <td>${ridentifier.permissionContent}</td>
	                                    <td><a onclick="loadResourceIdentifier('${ridentifier.resourceIdentifierPK.identifierId}')" >更新</a></td>
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
	                        <div class="col-sm-12 col-sm-offset-10">
                                <button class="btn btn-primary" id="effectButton" >配置生效</button>
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
		    $("[name='_resource_select_div']").remove();
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
			            $('#resource_select_area').append("<div class='col-sm-2' name='_resource_select_div'>");
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
		
        $(document).ready(function() {
            $('.footable').footable();
            $('.footable2').footable();
            initEmptyResource();
            initEmptyOperation();
            initIdentifierType();
        });
        
        function initOperation() {
		    lastSelect = $("[name='operationId']");
		    lastSelect.children().remove();
		    lastSelect.append("<option value=''>请选择</option>");
            lastSelect.chosen({width: "100%"});
            lastSelect.trigger("chosen:updated");
		}
		
		function initIdentifierType() {
		    lastSelect = $("[name='resourceIdentifierEntity.identifierType']");
		    lastSelect.children().remove();
		    lastSelect.append("<option value=''>请选择</option>");
		    lastSelect.append("<option value='01'>URI</option>");
		    lastSelect.append("<option value='02'>METHOD</option>");
            lastSelect.chosen({width: "100%"});
            lastSelect.trigger("chosen:updated");
		}
        
        function initEmptyResource(){
		    $("[name='_resource_select_div']").remove();
			$('#resource_select_area').append("<div class='col-sm-2' name='_resource_select_div'>");
	        $('#resource_select_area').find("div:last").append("<select name='_resourceid' class='chosen-select' tabindex='2'>");
	        lastSelect = $('#resource_select_area').find("select:last");
	        lastSelect.append("<option value='*'>请选择</option>");
	        $("[name='_resourceid']").chosen({width: "100%"});
	        $("[name='_resourceid']").trigger("chosen:updated");
		}
		
		function initEmptyOperation() {
		    initOperation();
		}
		
        function loadResourceIdentifier(identifierId) {
            $.ajax({
			    url:'/permission/identifier/load',
			    type:'GET',
			    async:true,
			    data:{
			        identifierId:identifierId
			    },
			    timeout:5000,    //超时时间
			    dataType:'json', //返回的数据格式：json/xml/html/script/jsonp/text
			    success:function(data, textStatus, jqXHR){
			        $("[name='resourceIdentifierPK.identifierId']").val(data.resourceIdentifierPK.identifierId);
		            $("[name='resourceIdentifierEntity.identifierName']").val(data.resourceIdentifierEntity.identifierName);
		            $("[name='resourceIdentifierEntity.identifierType']").val(data.resourceIdentifierEntity.identifierType);
		            $("[name='resourceIdentifierEntity.identifierPath']").val(data.resourceIdentifierEntity.identifierPath);
		            $("[name='resourceIdentifierEntity.accessType']:checked").prop('checked','false');
		            $("[name='resourceIdentifierEntity.accessType'][value='"+data.resourceIdentifierEntity.accessType+"']").prop('checked','true');
		            $("[name='resourceIdentifierEntity.identifierType']").trigger("chosen:updated");
		            
		            if($("[name='resourceIdentifierEntity.accessType']:checked").val() == "04") {
		                initResource();
		                initOperation();
		            } else {
		                $("[name='resourceId']").val("");
		                $("[name='operationId']").val("");
		                initEmptyResource();
		                initEmptyOperation();
		            }
			    }
			})
        }
        
        $(document).ready(function() {
            
            $("#saveButton").bind("click",function(){
	            if($("[name='resourceIdentifierEntity.identifierName']").val() == "") {
	                alert("设置资源标识时必须设置标识名称！");
	                return false;
	            }
	            if($("[name='resourceIdentifierEntity.identifierType']").val() == "") {
	                alert("设置资源标识时必须设置标识类型！");
	                return false;
	            }
	            if($("[name='resourceIdentifierEntity.identifierPath']").val() == "") {
	                alert("设置资源标识时必须设置标识路径！");
	                return false;
	            }
	            if($("[name='resourceIdentifierEntity.accessType']:checked").val() == "04") {
		            if($("[name='resourceId']").val() == "") {
		                alert("授权访问时必须设置权限资源！");
		                return false;
		            }
		            if($("[name='operationId']").val() == "") {
		                alert("授权访问时必须设置权限操作！");
		                return false;
		            }
	            }
	            
	            $("#resourceIdentifierForm").attr("action","/permission/identifier/save.html");
	            $("#resourceIdentifierForm").submit();
			});
			
			$("#effectButton").bind("click",function(){
	            $("#resourceIdentifierForm").attr("action","/permission/identifier/effect.html");
	            $("#resourceIdentifierForm").submit();
			});
			
			$("[name='resourceIdentifierEntity.accessType']").bind("click",function(){
			    if($("[name='resourceIdentifierEntity.accessType']:checked").val() == "04") {
	                initResource();
	                initOperation();
	            } else {
	                $("[name='resourceId']").val("");
	                $("[name='operationId']").val("");
	                initEmptyResource();
	                initEmptyOperation();
	            }
			});
			
        });
        
    </script>
    
    
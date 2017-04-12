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
	                            <h5>菜单设置</h5>
	                            <div class="ibox-tools">
	                                <a class="collapse-link">
	                                    <i class="fa fa-chevron-up"></i>
	                                </a>
	                            </div>
	                        </div>
	                        <div class="ibox-content">
	                            <form id="menuForm" method="post" class="form-horizontal" >
	                                <input name="menuInfoPK.menuId" type="hidden" class="form-control" value="${menu.menuInfoPK.menuId}">
	                                <div class="form-group" id="parent_select_area">
		                                <label class="col-sm-2 control-label">父菜单</label>
		                                <input name="menuInfoEntity.parentMenuId" type="hidden" class="form-control" value="">
	                                </div>
	                                <div class="form-group">
	                                    <label class="col-sm-2 control-label">菜单名</label>
	                                    <div class="col-sm-2"><input name="menuInfoEntity.menuName" type="text" class="form-control" value="${menu.menuInfoEntity.menuName}" ></div>
	                                    <label class="col-sm-2 control-label">菜单序号</label>
	                                    <div class="col-sm-2"><input name="menuInfoEntity.menuSequence" type="text" class="form-control" value="${menu.menuInfoEntity.menuSequence}" ></div>
	                                </div>
	                                <div class="form-group" id="operation_select_area">
		                                <label class="col-sm-2 control-label">URI路径</label>
		                                <div class="col-sm-8"><select name='menuInfoEntity.identifierId' class='chosen-select' tabindex='2'></select></div>
		                                
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

	<!-- Chosen -->
	<script src="${basepath}/js/plugins/chosen/chosen.jquery.js"></script>
        
    <script>
		
		function initIdentifier(identifierId) {
		    $.ajax({
			    url:'/permission/menu/identifier/query',
			    type:'GET',
			    async:true,
			    timeout:5000,    //超时时间
			    dataType:'json', //返回的数据格式：json/xml/html/script/jsonp/text
			    success:function(data, textStatus, jqXHR){
			        if(data.length > 0) {
			            lastSelect = $("[name='menuInfoEntity.identifierId']");
			            lastSelect.append("<option value=''>请选择</option>");
				        $.each(data, function(index, content){
				            lastSelect.append("<option value='"+content.resourceIdentifierPK.identifierId+"'>"+content.resourceIdentifierEntity.identifierPath+"</option>");
						});
						lastSelect.val(identifierId);
						lastSelect.chosen({width: "100%"});
						lastSelect.trigger("chosen:updated");
			        }
			    }
			})
		}
		
		function initParentMenu(){
			getParentMenu($("[name='menuInfoEntity.parentMenuId']").get(0));
		}
		
		function selectParentMenu(object){
		    tmp=$(object).parent().nextAll();
			tmp.remove();
		    if(object.value.endsWith('/')){
		    	$("[name='menuInfoEntity.parentMenuId']").val(object.value.substring(0, object.value.length-1));
		    } else {
			    $("[name='menuInfoEntity.parentMenuId']").val(object.value);
				getParentMenu(object);
		    }
		}
		
		function getParentMenu(object) {
		    $.ajax({
			    url:'/permission/menu/parent/query',
			    type:'GET',
			    async:true,
			    data:{
			        parentMenuId:object.value
			    },
			    timeout:5000,    //超时时间
			    dataType:'json', //返回的数据格式：json/xml/html/script/jsonp/text
			    success:function(data, textStatus, jqXHR){
			        if(data.length > 0) {
			            $('#parent_select_area').append("<div class='col-sm-2'>");
				        $('#parent_select_area').find("div:last").append("<select class='chosen-select' tabindex='2'>");
				        
				        lastSelect = $('#parent_select_area').find("select:last");
				        lastSelect.append("<option value='"+object.value+"/'>请选择</option>");
				        $.each(data, function(index, content){
				            lastSelect.append("<option value='"+content.menuInfoPK.menuId+"'>"+content.menuInfoEntity.menuName+"</option>");
						});
						lastSelect.on("change", function(e, params) {
						    selectParentMenu(this);
						});
						$('.chosen-select').chosen({width: "100%"});
						$('.chosen-select').trigger("chosen:updated");
			        }
			    }
			})
		}
		
        $(document).ready(function() {
            $("#saveButton").bind("click",function(){
	            $("#menuForm").attr("action","/permission/menu/save.html");
	            $("#menuForm").submit();
			});
			
			$("#returnButton").bind("click",function(){
	            $("#menuForm").attr("action","/permission/menu/query.html");
	            $("#menuForm").submit();
			});
            initParentMenu();
            initIdentifier('${menu.menuInfoEntity.identifierId}');
        });
        
    </script>
    
    
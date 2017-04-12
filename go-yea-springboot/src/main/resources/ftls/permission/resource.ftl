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
	                            <h5>资源设置</h5>
	                            <div class="ibox-tools">
	                                <a class="collapse-link">
	                                    <i class="fa fa-chevron-up"></i>
	                                </a>
	                            </div>
	                        </div>
	                        <div class="ibox-content">
	                            <form action="/permission/resource/save.html" method="post" class="form-horizontal" >
	                                <div class="form-group" id="parent_select_area">
		                                <label class="col-sm-2 control-label">父资源</label>
		                                <input name="resourceInfoEntity.parentResourceId" type="hidden" class="form-control" value="">
	                                </div>
	                                <div class="form-group">
	                                    <label class="col-sm-2 control-label">资源名</label>
	                                    <div class="col-sm-2"><input name="resourceInfoEntity.resourceName" type="text" class="form-control"></div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="col-sm-2 control-label">资源描述</label>
	                                    <div class="col-sm-8">
	                                        <textarea class="form-control" name="resourceInfoEntity.resourceDesc" rows="3" placeholder="请输入资源描述 ..."></textarea>
	                                    </div>
	                                    
	                                </div>
	                                
	                                <div class="hr-line-dashed"></div>
	                                <div class="form-group">
	                                    <div class="col-sm-12 col-sm-offset-10">
	                                        <button class="btn btn-primary" type="submit">新增</button>
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
	                                    <th data-toggle="true">资源名</th>
	                                    <th>资源内容</th>
	                                    <th data-hide="all">资源描述</th>
	                                </tr>
	                                </thead>
	                                <tbody>
	                                <#list resources?if_exists as resource>
							        <tr>
	                                    <td>${resource.resourceInfoEntity.resourceName}</td>
	                                    <td>${resource.resourceInfoEntity.resourceContent}</td>
	                                    <td>${resource.resourceInfoEntity.resourceDesc}</td>
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
		
		function initParentResource(){
			getParentResource($("[name='resourceInfoEntity.parentResourceId']").get(0));
		}
		
		function selectParentResource(object){
		    tmp=$(object).parent().nextAll();
			tmp.remove();
		    if(object.value.endsWith('/')){
		    	$("[name='resourceInfoEntity.parentResourceId']").val(object.value.substring(0, object.value.length-1));
		    } else {
			    $("[name='resourceInfoEntity.parentResourceId']").val(object.value);
				getParentResource(object);
		    }
		}
		
		function getParentResource(object) {
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
			            $('#parent_select_area').append("<div class='col-sm-2'>");
				        $('#parent_select_area').find("div:last").append("<select class='chosen-select' tabindex='2'>");
				        
				        lastSelect = $('#parent_select_area').find("select:last");
				        lastSelect.append("<option value='"+object.value+"/'>请选择</option>");
				        $.each(data, function(index, content){
				            lastSelect.append("<option value='"+content.resourceInfoPK.resourceId+"'>"+content.resourceInfoEntity.resourceName+"</option>");
						});
						lastSelect.on("change", function(e, params) {
						    selectParentResource(this);
						});
						$('.chosen-select').chosen({width: "100%"});
						$('.chosen-select').trigger("chosen:updated");
			        }
			    }
			})
		}
		
        $(document).ready(function() {
            $('.footable').footable();
            $('.footable2').footable();
            initParentResource();
        });
        
    </script>
    
    
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
	                            <h5>菜单设置</h5>
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
	                                    <th data-toggle="true">菜单名</th>
	                                    <th>菜单URL地址</th>
	                                    <th>父菜单</th>
	                                    <th>操作</th>
	                                </tr>
	                                </thead>
	                                <tbody>
	                                <#list menus?if_exists as menu>
							        <tr>
	                                    <td>${menu.menuInfoEntity.menuName}</td>
	                                    <td>${menu.urlPath}</td>
	                                    <td>${menu.parentMenuInfoEntity.menuName}</td>
	                                    <td><a onclick="updateMenu('${menu.menuInfoPK.menuId}')" >更新</a></td>
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
                            	<form id="menuForm" method="get" class="form-horizontal" >
	                                <input name="menuId" type="hidden" class="form-control" value="">
	                                <div class="col-sm-12 col-sm-offset-10">
	                                    <button class="btn btn-primary" id="addButton" >新增</button>
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

    <script>
		
		$(document).ready(function() {
            $('.footable').footable();
            $('.footable2').footable();
            
            $("#addButton").bind("click",function(){
	            $("[name='menuId']").val("");
	            $("#menuForm").attr("action","${basepath}/permission/menu/load.html");
	            $("#menuForm").submit();
			});
        });
        
        function updateMenu(menuId) {
        	$("[name='menuId']").val(menuId);
        	$("#menuForm").attr("action","${basepath}/permission/menu/load.html");
	        $("#menuForm").submit();
        }
        
    </script>
    
    
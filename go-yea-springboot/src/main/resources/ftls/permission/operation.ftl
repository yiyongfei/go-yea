<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>管理平台</title>
    
    <#include "/common/css.ftl"/>
    <!-- FooTable -->
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
	                            <h5>操作设置</h5>
	                            <div class="ibox-tools">
	                                <a class="collapse-link">
	                                    <i class="fa fa-chevron-up"></i>
	                                </a>
	                            </div>
	                        </div>
	                        <div class="ibox-content">
	                            <form action="${basepath}/permission/operation/save.html" method="post" class="form-horizontal" >
	                                <div class="form-group">
	                                    <label class="col-sm-2 control-label">操作名</label>
	                                    <div class="col-sm-4"><input name="operationInfoEntity.operationName" type="text" class="form-control"></div>
	                                
	                                    <label class="col-sm-2 control-label">操作描述</label>
	                                    <div class="col-sm-4"><input name="operationInfoEntity.operationDesc" type="text" class="form-control"></div>
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
	                                    <th>操作名</th>
	                                    <th>操作描述</th>
	                                </tr>
	                                </thead>
	                                <tbody>
	                                <#list operations?if_exists as operation>
							        <tr>
	                                    <td>${operation.operationInfoEntity.operationName}</td>
	                                    <td>${operation.operationInfoEntity.operationDesc}</td>
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
<!-- Page-Level Scripts -->
    <script>
        $(document).ready(function() {

            $('.footable').footable();
            $('.footable2').footable();

        });

    </script>
    
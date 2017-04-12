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
	                            <h5>人员设置</h5>
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
	                                    <th data-toggle="true">姓名</th>
	                                    <th>登录名</th>
	                                    <th>生日</th>
	                                    <th data-hide="all">人员描述</th>
	                                    <th>操作</th>
	                                </tr>
	                                </thead>
	                                <tbody>
	                                <#list users?if_exists as user>
							        <tr>
	                                    <td>${user.personInfoEntity.personName}</td>
	                                    <td>${user.username}</td>
	                                    <td>${(user.personInfoEntity.birthDate?string("yyyy-MM-dd"))!''}</td>
	                                    <td>${user.personInfoEntity.personMemo}</td>
	                                    <td><a onclick="updateUser('${user.userInfoPK.partyId}')" >更新</a> | <a onclick="loadUserRole('${user.userInfoPK.partyId}')" >授权</a></td>
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
                            	<form id="userForm" method="get" class="form-horizontal" >
	                                <input name="partyId" type="hidden" class="form-control" value="">
	                                <div class="col-sm-12 col-sm-offset-10">
	                                    <button class="btn btn-primary" id="addUserButton" >新增</button>
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
            
            $("#addUserButton").bind("click",function(){
	            $("[name='partyId']").val("");
	            $("#userForm").attr("action","/authorization/user/load.html");
	            $("#userForm").submit();
			});
        });
        
        function updateUser(partyId) {
        	$("[name='partyId']").val(partyId);
        	$("#userForm").attr("action","/authorization/user/load.html");
	        $("#userForm").submit();
        }
        
        function loadUserRole(partyId){
       	 	$("[name='partyId']").val(partyId);
        	$("#userForm").attr("action","/authorization/user/role/load.html");
	        $("#userForm").submit();
        }
    </script>
    
    
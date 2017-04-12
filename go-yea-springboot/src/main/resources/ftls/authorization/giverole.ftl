<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>管理平台</title>
    
    <#include "/common/css.ftl"/>
    <!-- Chosen（与下拉框相关） -->
    <link href="${basepath}/css/plugins/chosen/bootstrap-chosen.css" rel="stylesheet">
    <link href="${basepath}/css/plugins/dualListbox/bootstrap-duallistbox.min.css" rel="stylesheet">
    
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
	                            <h5>人员授权</h5>
	                            <div class="ibox-tools">
	                                <a class="collapse-link">
	                                    <i class="fa fa-chevron-up"></i>
	                                </a>
	                            </div>
	                        </div>
	                        <div class="ibox-content">
	                            <form id="userRoleForm" method="post" class="form-horizontal" >
	                                <input name="partyId" type="hidden" class="form-control" value="${user.userInfoPK.partyId}">
	                                <div class="form-group">
	                                    <label class="col-sm-2 control-label">姓名</label>
	                                    <div class="col-sm-3"><input disabled="" type="text" class="form-control" value="${user.personInfoEntity.personName}"></div>
	                                    <label class="col-sm-2 control-label">登录名</label>
	                                    <div class="col-sm-3"><input disabled="" type="text" class="form-control" value="${user.username}"></div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="col-sm-2 control-label">性别</label>
	                                    <div class="col-sm-3"><select disabled="" name='personInfoEntity.sexTypeCode' class='chosen-select' tabindex='2'></select></div>
	                                    <label class="col-sm-2 control-label">生日</label>
	                                    <div class="col-sm-3"><input disabled="" type="text" class="form-control" value="${(user.personInfoEntity.birthDate?string("yyyy-MM-dd"))!''}" ></div>
	                                </div>
	                                
	                                <div class="form-group">
	                                    <label class="col-sm-2 control-label">授予角色</label>
	                                    <div class="col-sm-8">
				                            <select name='roleIds' class="form-control dual_select" multiple>
				                            <#list unselects?if_exists as role>
									            <option value="${role.roleInfoPK.roleId}">${role.roleInfoEntity.roleName}</option>
									      	</#list>
									      	<#list selects?if_exists as role>
									            <option selected value="${role.roleInfoPK.roleId}">${role.roleInfoEntity.roleName}</option>
									      	</#list>
			                                </select>
		                                </div>
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
    <!-- Dual Listbox -->
    <script src="${basepath}/js/plugins/dualListbox/jquery.bootstrap-duallistbox.js"></script>
    
    <script>
		function initSex(sexType) {
            lastSelect = $("[name='personInfoEntity.sexTypeCode']");
            lastSelect.append("<option value=''>请选择</option>");
	        lastSelect.append("<option value='01'>男性</option>");
	        lastSelect.append("<option value='02'>女性</option>");
	        lastSelect.append("<option value='09'>未知</option>");
	        lastSelect.val(sexType);
			lastSelect.chosen({width: "100%"});
			lastSelect.trigger("chosen:updated");
		}
		
		
        $(document).ready(function() {
            $("#saveButton").bind("click",function(){
	            $("#userRoleForm").attr("action","/authorization/user/role/save.html");
	            $("#userRoleForm").submit();
			});
			
			$("#returnButton").bind("click",function(){
	            $("#userRoleForm").attr("action","/authorization/user/query.html");
	            $("#userRoleForm").submit();
			});
			
            initSex('${user.personInfoEntity.sexTypeCode}');
            
            $('.dual_select').bootstrapDualListbox({
                selectorMinimalHeight: 160
            });
        });
        
    </script>
    
    
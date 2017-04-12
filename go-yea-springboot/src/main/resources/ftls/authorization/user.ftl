<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>管理平台</title>
    
    <#include "/common/css.ftl"/>
    <!-- Data picker，与日期组件相关 -->
    <link href="${basepath}/css/plugins/datapicker/bootstrap-datepicker3.css" rel="stylesheet">
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
	                            <h5>人员设置</h5>
	                            <div class="ibox-tools">
	                                <a class="collapse-link">
	                                    <i class="fa fa-chevron-up"></i>
	                                </a>
	                            </div>
	                        </div>
	                        
	                        <div class="ibox-content">
	                            <form id="userForm" method="post" class="form-horizontal" >
	                                <input name="userInfoPK.partyId" type="hidden" class="form-control" value="${user.userInfoPK.partyId}">
	                                <div class="form-group">
	                                    <label class="col-sm-2 control-label">姓名</label>
	                                    <div class="col-sm-3"><input name="personInfoEntity.personName" type="text" class="form-control" value="${user.personInfoEntity.personName}"></div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="col-sm-2 control-label">登录名</label>
	                                    <div class="col-sm-3"><input name="username" type="text" class="form-control" value="${user.username}"></div>
	                                    <label class="col-sm-2 control-label">登录密码</label>
	                                    <div class="col-sm-3"><input name="password" type="password" class="form-control" value=""></div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="col-sm-2 control-label">性别</label>
	                                    <div class="col-sm-3"><select name='personInfoEntity.sexTypeCode' class='chosen-select' tabindex='2'></select></div>
	                                    <label class="col-sm-2 control-label">生日</label>
	                                    <div class="date col-sm-3">
		                                    <input name="personInfoEntity.birthDate" type="text" readonly="readonly" class="form-control" value="${(user.personInfoEntity.birthDate?string("yyyy-MM-dd"))!''}" >
		                                </div>
	                                </div>
	                                <div class="form-group">
	                                    <label class="col-sm-2 control-label">描述</label>
	                                    <div class="col-sm-8">
	                                        <textarea class="form-control" name="personInfoEntity.personMemo" rows="3" placeholder="请输入人员的描述信息 ...">${user.personInfoEntity.personMemo}</textarea>
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
	<!-- Data picker，与日期组件相关 -->
    <script src="${basepath}/js/plugins/datapicker/bootstrap-datepicker.js"></script>
    <script src="${basepath}/js/plugins/datapicker/bootstrap-datepicker.zh-CN.min.js"></script>
    <!-- Chosen -->
    <script src="${basepath}/js/plugins/chosen/chosen.jquery.js"></script>
   
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
	            $("#userForm").attr("action","/authorization/user/save.html");
	            $("#userForm").submit();
			});
			
			$("#returnButton").bind("click",function(){
	            $("#userForm").attr("action","/authorization/user/query.html");
	            $("#userForm").submit();
			});
			
			initSex('${user.personInfoEntity.sexTypeCode}');
			
			if($("[name='userInfoPK.partyId']").val() != "") {
			    $("[name='username']").prop("disabled", true);
			    $("[name='password']").prop("disabled", true);
			}
			
			$("[name='personInfoEntity.birthDate']").datepicker({
                format: "yyyy-mm-dd", //设置日期格式
                todayBtn: true,
                clearBtn: true,
				language: "zh-CN",
				todayHighlight: true,
                calendarWeeks: true,
                keyboardNavigation: false,
                forceParse: false,
                autoclose: true
            });
			
        });
        
    </script>
    
    
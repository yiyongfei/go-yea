<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>管理平台</title>
    
    <#include "/common/css.ftl"/>
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
	                            <h5>生成工具<small>  ${message}</small></h5>
	                            <div class="ibox-tools">
	                                <a class="collapse-link">
	                                    <i class="fa fa-chevron-up"></i>
	                                </a>
	                            </div>
	                        </div>
	                        <div class="ibox-content">
	                            <form action="/developer/generator/generator.html" method="post" class="form-horizontal" >
	                                <div class="form-group"><label class="col-sm-2 control-label">模块名</label>
	                                    <div class="col-sm-10"><input name="moduleName" type="text" class="form-control"></div>
	                                </div>
	                                <div class="hr-line-dashed"></div>
	                                <div class="form-group"><label class="col-sm-2 control-label">根包路径</label>
	                                    <div class="col-sm-10"><input name="basePackagePath" type="text" class="form-control"></div>
	                                </div>
	                                <div class="hr-line-dashed"></div>
	                                <div class="form-group"><label class="col-sm-2 control-label">表名</label>
	                                    <div class="col-sm-10"><input name="tableName" type="text" placeholder="以;分隔多张表,可使用能配符%做表匹配" class="form-control"></div>
	                                </div>
	                                <div class="hr-line-dashed"></div>
	                                <div class="form-group"><label class="col-sm-2 control-label">表前缀</label>
	                                    <div class="col-sm-10"><input name="tablePrefixOverrides" type="text" placeholder="生成的Java对象名将不包含前缀" class="form-control"></div>
	                                </div>
	                                
	                                <div class="hr-line-dashed"></div>
	                                <div class="form-group">
	                                    <label class="col-sm-2 control-label">公共DAO<br/><small class="text-navy"></small></label>
	                                    <div class="col-sm-10">
	                                        <div><label> <input name="daoGenerateable" type="checkbox" value="false" onclick="judgeDaoGenerate(this);" > 是否使用公共DAO </label></div>
	                                    </div>
	                                </div>
	                                
	                                <div class="hr-line-dashed"></div>
	                                <div class="form-group"><label class="col-sm-2 control-label">公共DAO包路径</label>
	                                    <div class="col-sm-10"><input name="commonDaoPackagePath" disabled="" value="com.team.goyea.common.dao" type="text" placeholder="" class="form-control"></div>
	                                </div>
	                                <div class="hr-line-dashed"></div>
	                                <div class="form-group"><label class="col-sm-2 control-label">公共DAO名</label>
	                                    <div class="col-sm-10"><input name="commonDaoName" disabled="" value="CommonDao" type="text" placeholder="" class="form-control"></div>
	                                </div>
	                                
	                                <div class="hr-line-dashed"></div>
	                                <div class="form-group">
	                                    <div class="col-sm-12 col-sm-offset-10">
	                                        <button class="btn btn-primary" type="submit">生成</button>
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
<script type="text/javascript">
	function judgeDaoGenerate(obj) {
	  if (${r'$'}(obj).is(':checked')) {
	      ${r'$'}("input[name='commonDaoPackagePath']").attr('disabled', false);
	      ${r'$'}("input[name='commonDaoName']").attr('disabled', false);
	  } else {
	      ${r'$'}("input[name='commonDaoPackagePath']").attr('disabled', true);
	      ${r'$'}("input[name='commonDaoName']").attr('disabled', true);
	  }
	}
</script>
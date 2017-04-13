<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>YEA | Login</title>
    <#include "/common/css.ftl"/>
</head>

<body class="gray-bg">
    <div class="middle-box text-center loginscreen animated fadeInDown">
        <div>
            <div>
                <h1 class="logo-name">YEA</h1>
            </div>
            <h3>欢迎您来到YEA的世界</h3>
            <p>
            </p>
            <form class="m-t" role="form" method="post" action="/authenticed.html">
                <#if login_message?? >
                <div class="form-group has-error">
                    <label class="control-label">${login_message}</label>
                </div>
                </#if>
                <div class="form-group">
                    <input type="text" class="form-control" name="username" placeholder="username" required="">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="password" placeholder="password" required="">
                </div>
                <div class="form-group">
                        <div class="checkbox i-checks"><label> <input name="rememberMe" type="checkbox"><i></i> 记住我 </label></div>
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b">登录</button>
                <a href="#"><small>忘记密码?</small></a>
            </form>
            <p class="m-t"> <small><strong>Copyright</strong> YEA Framework &copy; 2015-2017</small> </p>
        </div>
    </div>
</body>
</html>

<#include "/common/script.ftl"/>

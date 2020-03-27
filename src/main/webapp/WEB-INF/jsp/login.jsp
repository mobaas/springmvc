<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html><head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
    <title>后台登录</title>
    <link rel="stylesheet" type="text/css" href="/lib/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="/lib/css/login.css">
    <style>

        element.style {
        }
        img {
            display: inline-block;
            border: none;
            vertical-align: middle;
        }
        img[Attributes Style] {
            width: 116px;
            height: 36px;
        }
        .form_code .code {
            position: absolute;
            right: 80px;
            top: 195px;
            cursor: pointer;
        }
        .login_btn {
            width: 100%;
        }

    </style>
</head>
<body>
    <div class="m-login-bg">
        <div class="m-login">
            <h3>登录</h3>
            <div class="m-login-warp">
                <form class="layui-form"  action="/login_submit.do" method="post">
 					<input type="hidden" name="_csrf" value="<c:out value="${token}" />" />
                    <div class="layui-form-item">
                        <input type="text" name="username" lay-verify="required"  placeholder="用户名" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-form-item">

                        <input type="password" name="password"  lay-verify="required"  placeholder="密码" autocomplete="off" class="layui-input">

                    </div>
                    <div class="layui-form-item" style="margin-left:10px;">

                        <input type="checkbox" name="remember"  lay-skin="primary" title="记住我">

                    </div>
                    <div class="layui-form-item m-login-btn">
                        <div class="layui-inline">
                            <button class="layui-btn layui-btn-normal" lay-submit="login" lay-filter="login">登录</button>

                        </div>
                        <div class="layui-inline">
                            <button type="reset" class="layui-btn layui-btn-primary">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script src="/lib/layui/layui.js" type="text/javascript" charset="utf-8"></script>
    <script>
        layui.use(['form', 'layedit', 'laydate'], function() {
            var form = layui.form,
                layer = layui.layer;


        });

    </script>
</body>
</html>
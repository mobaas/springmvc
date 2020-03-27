<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>资讯</title>
    <script type="text/javascript" src="/lib/js/jquery.js"></script>
    <script type="text/javascript" src="/lib/layui/layui.js"></script>
    <link rel="stylesheet" href="/lib/layui/css/layui.css">
    <link rel="stylesheet" href="/lib/css/base.css">
    <link rel="stylesheet" href="/lib/css/langmu.css">
    <style>
        .layui-nav-tree .layui-nav-child .active,
        .layui-nav-tree .layui-nav-child dd:hover {
            background: #0695ff;
        }

        .layui-layer-content {
            text-align: center;
        }
    </style>
</head>

<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
      <jsp:include page="/WEB-INF/jsp/top.jsp" flush="true" />
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
			<jsp:include page="/WEB-INF/jsp/menu.jsp" flush="true" />
        </div>
    </div>

    <div class="layui-body">
        <div class="place">
            您所在的位置: 资讯管理 > 添加资讯
        </div>
        <!-- 内容主体区域 -->
     	 <div class="manage" style="padding: 15px;">
			 <form class="layui-form" action="/info/add_submit.do"  method="post">
			  <input type="hidden" name="_csrf" value="<c:out value="${token}" />" />
	            <div class="layui-form-item">
	                <label class="layui-form-label"><span style="color: red;">*</span>标题</label>
	                <div class="layui-input-block">
	                    <input type="text" name="title" required lay-verify="required" placeholder="标题"
	                           autocomplete="off" class="layui-input">
	                </div>
	            </div>
	
	            <div class="layui-form-item layui-form-text">
	                <label class="layui-form-label">内容</label>
	                <div class="layui-input-block">
	                    <textarea id="content" name="content" style="width:700px;height:300px;"></textarea>
	                </div>
	            </div>
	            
	            <div class="layui-form-item">
	                <div class="layui-input-block">
	                    <button class="layui-btn" lay-submit >保存</button>
	                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
	                </div>
	            </div>
	        </form>
        
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
		<jsp:include page="/bottom.html" flush="true" />
    </div>
</div>

<script type="text/javascript">
   
</script>
</body>

</html>
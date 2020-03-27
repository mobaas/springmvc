<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>后台管理</title>
<script type="text/javascript" src="/lib/js/jquery.js"></script>
<script type="text/javascript" src="/lib/layui/layui.js"></script>
<link rel="stylesheet" href="/lib/layui/css/layui.css">
<link rel="stylesheet" href="/lib/css/base.css">
<link rel="stylesheet" href="/lib/css/langmu.css">
<style>
.layui-nav-tree .layui-nav-child .active, .layui-nav-tree .layui-nav-child dd:hover
	{
	background: #0695ff;
}
</style>
</head>

<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header"> <jsp:include page="/WEB-INF/jsp/top.jsp" flush="true" /></div>

		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<jsp:include page="/WEB-INF/jsp/menu.jsp" flush="true" />
			</div>
		</div>

		<div class="layui-body">
			<div class="place">您所在的位置: 管理首页</div>
			<!-- 内容主体区域 -->
			<!--  <div class="manage" style="padding: 15px;"> -->
			<div
				style="margin: 230px auto; text-align: center; color: #333; font-weight: 600; font-size: 38px;">
				
			</div>


			<!-- </div> -->
		</div>

		<div class="layui-footer">
			<!-- 底部固定区域 -->
			<jsp:include page="/bottom.html" flush="true" />
		</div>
	</div>


</body>

</html>

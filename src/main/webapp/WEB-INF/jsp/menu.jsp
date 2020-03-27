<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<ul class="layui-nav layui-nav-tree" lay-filter="test">
    <sec:authorize access="hasRole('ROLE_INFO_VIEW')">
    <li class="layui-nav-item layui-nav-itemed">
        <a class="" href="javascript:;">资讯管理</a>
        <dl class="layui-nav-child">
            <dd>
                <a href="/info/list.do">资讯列表</a>
            </dd>
            <sec:authorize access="hasRole('ROLE_INFO_EDIT')">
            <dd>
            	<a href="/info/add.do">添加资讯</a>
            </dd>
            </sec:authorize>
        </dl>
    </li>
	</sec:authorize>
    <sec:authorize access="hasRole('ROLE_SYSTEM_VIEW')">
    <li class="layui-nav-item layui-nav-itemed">
        <a href="javascript:;">系统设置</a>
        <dl class="layui-nav-child">
            <dd>
                <a href="/admin/list.do">管理员列表</a>
            </dd>
            <dd>
            	<a href="/admin/loglist.do">管理日志</a>
            </dd>
        </dl>
    </li>
	</sec:authorize>    
</ul>
<script type="text/javascript" src="/lib/js/jquery.js"></script>
    <script type="text/javascript" src="/lib/layui/layui.js"></script>
 <script>
     //JavaScript代码区域
     layui.use('element', function () {
         var element = layui.element;
     });
     layui.use('form', function () {
         var form = layui.form;
         form.render();
     });
 </script>
<script type="text/javascript" src="/lib/js/my-layui-verify.js"></script>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="layui-logo">管理后台</div>
  <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
          <a href="javascript:;">
              <sec:authentication property="name" />
          </a>
          <dl class="layui-nav-child">
              <dd><a href="javascript:changepwd()">修改密码</a></dd>
          </dl>
      </li>
      <li class="layui-nav-item">
          <a href="/logout.do">退出</a>
      </li>
  </ul>

  <script type="text/javascript">
      function changepwd() {
          //iframe层
          layui.use('layer', function () {
              var layer = layui.layer;
              layer.open({
                  type: 2,
                  title: '修改密码',
                  shadeClose: false,
                  shade: 0.8,
                  area: ['400px', '300px'],
                  content: 'about:blank'
              });
          });
      }
  </script>
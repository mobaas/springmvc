<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>系统管理</title>
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
            您所在的位置: 系统管理 > 日志列表
        </div>
        <!-- 内容主体区域 -->
        <div class="manage" style="padding: 15px;">
            <form class="layui-form listForm" method="GET">

                <div class="layui-form-item">

                </div>
            </form>

            <table class="layui-table">
                <thead>
                <tr>
		            <th>
		                时间
		            </th>
		            <th>
		                操作人
		            </th>
		            <th>
		                操作类别
		            </th>
		            <th>
		            	操作名称
		            </th>
		            <th>
		                返回结果
		            </th>
		            <th>
		                执行时长(秒)
		            </th>
                </tr>
                </thead>
			        <c:forEach items="${loglist}" var="log">
						<tr>
                              <td>
		                        <div><c:out value="${log.logTimeStr}" /></div>
		                    </td>
		                    <td>
		                        <div>
		                            <c:out value="${log.loginName}" /></div>
		                    </td>
		                    <td>
		                        <div>
		                            <c:out value="${log.category}" /></div>
		                    </td>
		                    <td>
		                        <div>
		                            <c:out value="${log.opName}" /></div>
		                    </td>
		                     <td>
		                        <div>
		                            <c:out value="${log.result}" /></div>
		                    </td>
		                     <td>
		                        <div>
		                            <c:out value="${log.duration}" /></div>
		                    </td>  
                      </tr>
                  </c:forEach>
            </table>
            <div id="pager"></div>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
		<jsp:include page="/bottom.html" flush="true" />
    </div>
</div>

<script type="text/javascript">
    layui.use(['laypage'], function () {
        var laypage = layui.laypage

        var count = [[${pager.rowCount}]];
        var pageNo = [[${pager.pageNo}]];

        var pageSize = [[${pager.pageSize}]];
        //调用分页

        laypage.render({
            elem: 'pager'
            , theme: '#1E9FFF'
            , count: count
            , curr: pageNo
            , limit: pageSize //每页显示的条数
            , layout: ['count', 'prev', 'page', 'next', 'skip']
            , jump: function (obj, first) {
                //首次不执行
                if (!first) {
                    location.href = "/admin/loglist.do?page=" + obj.curr;
                }
            }
        });
    })

</script>
</body>

</html>
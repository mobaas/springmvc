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
            您所在的位置: 资讯管理 > 资讯列表
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
                	<th style="width:80px">ID</th>
			        <th>标题</th>
                    <th style="width:100px">操作</th>
                </tr>
                </thead>
					<c:forEach items="${infolist}" var="info">
						<tr>
                              <td>
		                        <div>
		                            <c:out value="${info.id}" /></div>
		                    </td>
		                    <td>
		                        <div>
		                            <c:out value="${info.title}" /></div>
		                    </td>
		                    <td>
		                    	<sec:authorize access="hasRole('ROLE_INFO_EDIT')">
		                        <div>
		                        	<a href="edit.do?id=<c:out value="${info.id}"/>">编辑</a></div>
		                        </sec:authorize>	
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
                //  console.log(obj)
                if (!first) {
                    // var str = $(".order-date").find("input").val();
                    location.href = "/info/list.do?page=" + obj.curr;
                }
            }
        });
    })

</script>
</body>

</html>
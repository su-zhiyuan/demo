<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<!-- jsp文件头和头部 -->
<%@ include file="../../system/index/top.jsp"%>
</head>
<style>
tr{
	height:30px
}
.top{
    height:100px 
}
.one{
	padding-left:50px
}
.max{
	width:100%;
	height:auto;
}
.min{
	width:100px;
	height:auto;
}
</style>
<body class="no-skin">
	<div class="main-container" id="main-container">
		<div class="page-content">
			<form action="#" name="Form" id="Form" method="post">
				<div id="zhongxin" style="padding-top: 13px;">
					<div id="container" style="width: 650px;">
						<table style="width: 650px;">
							<c:if test="${ not empty varList}">
								<c:forEach items="${varList}" var="var" varStatus="vs">
									<tr>
										<td class="center">${vs.index+1}</td>
										<td class="center"><a href="javascript:void(0);" onclick="preview('${var}')"><img id="${vs.index+1}" class="min" src="${var}" width="100px" height="100px" ></a></td>
									</tr>
									<tr>
										<td class="center"></td>
									</tr>
									
									
								</c:forEach>
							</c:if>
						</table>
					</div>
				</div>
			</form>
		</div>
	</div>

	<!-- 页面底部js¨ -->
	<%@ include file="../../system/index/foot.jsp"%>
	<script type="text/javascript">
		$(top.hangge());

		function preview(path){
		        window.open(path);
		}
	</script>
</body>
</html>
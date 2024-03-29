﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<!-- 下拉框 -->
<link rel="stylesheet" href="static/ace/css/chosen.css" />
<!-- jsp文件头和头部 -->
<%@ include file="../../system/index/top.jsp"%>
<!-- 日期框 -->
<link rel="stylesheet" href="static/ace/css/datepicker.css" />
</head>
<body class="no-skin">

	<!-- /section:basics/navbar.layout -->
	<div class="main-container" id="main-container">
		<!-- /section:basics/sidebar -->
		<div class="main-content">
			<div class="main-content-inner">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
					
						<table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">	
							<thead>
								<tr>
									<th class="center" style="width:50px;">序号</th>
									<th class="center">创建人</th>
									<th class="center">创建时间</th>
									<th class="center">事由</th>
									<th class="center">交通工具</th>
									<th class="center">单程往返</th>
									<th class="center">出发城市</th>
									<th class="center">目的城市</th>
									<th class="center">开始时间</th>
									<th class="center">结束时间</th>
									<th class="center">出差天数</th>
									<th class="center">同行人</th>
									<th class="center">备注</th>
<!-- 									<th class="center">状态</th> -->
									<!-- <th class="center">备注12</th>
									<th class="center">备注13</th>
									<th class="center">备注14</th>
									<th class="center">备注15</th>
									<th class="center">备注16</th>
									<th class="center">备注17</th>
									<th class="center">备注18</th>
									<th class="center">备注19</th>
									<th class="center">备注20</th> -->
<!-- 									<th class="center">操作</th> -->
								</tr>
							</thead>
													
							<tbody>
							<!-- 开始循环 -->	
							<c:choose>
								<c:when test="${not empty varList}">
									<c:if test="${QX.cha == 1 }">
									<c:forEach items="${varList}" var="var" varStatus="vs">
										<tr>
											<td class='center' style="width: 30px;">${vs.index+1}</td>
											<td class='center'>${var.uNAME}</td>
											<td class='center'>${var.yl4}</td>
											<td class='center'>${var.cause}</td>
											<td class='center'>${var.jtgj}</td>
											<td class='center'>${var.way}</td>
											<td class='center'>${var.citycf}</td>
											<td class='center'>${var.citydd}</td>
											<td class='center'>${var.starttime}</td>
											<td class='center'>${var.endtime}</td>
											<td class='center'>${var.days}</td>
											<td class='center'>${var.txr}</td>
											<td class='center'>${var.remark}</td>
<%-- 											<td class='center'>${var.yl1}</td> --%>
											<%-- <td class='center'>${var.YL1}</td>
											<td class='center'>${var.YL2}</td>
											<td class='center'>${var.YL3}</td>
											<td class='center'>${var.YL4}</td>
											<td class='center'>${var.YL5}</td>
											<td class='center'>${var.YL6}</td>
											<td class='center'>${var.YL7}</td>
											<td class='center'>${var.YL8}</td>
											<td class='center'>${var.YL9}</td> --%>
										</tr>
									</c:forEach>
									</c:if>
								</c:when>
							</c:choose>
							</tbody>
						</table>
						<div class="page-header position-relative">
						</div>
						</form>
					
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
		</div>
		<!-- /.main-content -->

		<!-- 返回顶部 -->
		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>

	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../../system/index/foot.jsp"%>
	<!-- 删除时确认窗口 -->
	<script src="static/ace/js/bootbox.js"></script>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript">
		
	</script>


</body>
</html>
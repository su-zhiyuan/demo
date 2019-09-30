<%@ page language="java" contentType="text/html; charset=UTF-8"
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
							
						<!-- 检索  -->
						<form action="#" method="post" name="Form" id="Form">
						
						<table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">	
							<thead>
								<tr>
									<th class="center" style="width:35px;">
									<label class="pos-rel"><input type="checkbox" class="ace" id="zcheckbox" /><span class="lbl"></span></label>
									</th>
									<th class="center" style="width:50px;">序号</th>
									<th class="center">日期</th>
									<th class="center">打卡时间</th>
									<th class="center">创建人</th>
									<th class="center">上班地址</th>
									<th class="center">下班地址</th>
									<!-- <th class="center">备注</th> -->
									<th class="center">打卡状态</th>
									<th class="center">考勤</th>
									<th class="center">异常状态</th>
									<!-- <th class="center">备注9</th>
									<th class="center">备注10</th>
									<th class="center">备注11</th>
									<th class="center">备注12</th>
									<th class="center">备注13</th>
									<th class="center">备注14</th>
									<th class="center">备注15</th> -->
								</tr>
							</thead>
													
							<tbody>
								<c:forEach items="${varList}" var="var" varStatus="vs">
								<tr>
									<td class='center'>
												<label class="pos-rel"><input type='checkbox' name='ids' value="${var.CARDCLOCK_ID}" class="ace" /><span class="lbl"></span></label>
											</td>
											<td class='center' style="width: 30px;">${vs.index+1}</td>
											<td class='center'>${var.date}</td>
											<td class='center'>${var.updatetime}</td>
											<td class='center'>${var.uNAME}</td>
											<td class='center'>${var.yl3}</td>
											<td class='center'>${var.yl4}</td>
											<%-- <td class='center'>${var.remark}</td> --%>
											<%-- <td class='center'>${var.YL1}</td> --%>
											<td class='center'>
											<c:if test="${var.yl1=='1'}">上班</c:if>
											<c:if test="${var.yl1=='2'}">下班</c:if>
											</td>
											<%-- <td class='center'>${var.YL2}</td> --%>
											<td class='center'>
											<c:if test="${var.yl2=='正常'}">正常</c:if>
											<c:if test="${var.yl2=='迟到'}">迟到</c:if>
											<c:if test="${var.yl2=='早退'}">早退</c:if>
											</td>
											<td class='center'>${var.yl7}
											<%-- <td class='center'>${var.YL3}</td>
											<td class='center'>${var.YL4}</td>
											<td class='center'>${var.YL5}</td>
											<td class='center'>${var.YL6}</td>
											<td class='center'>${var.YL7}</td>
											<td class='center'>${var.YL8}</td>
											<td class='center'>${var.YL9}</td> --%>
									</tr>
								</c:forEach>
							</tbody>
						</table>
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
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript">
		$(top.hangge());//关闭加载状态
		//检索
		function tosearch(){
			top.jzts();
			$("#Form").submit();
		}
		
	</script>


</body>
</html>
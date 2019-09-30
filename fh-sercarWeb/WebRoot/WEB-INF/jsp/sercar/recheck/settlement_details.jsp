<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
					
					<form action="recheck/save.do" name="Form" id="Form" method="post">
						<input type="hidden" name="RECEPTION_ID" id="RECEPTION_ID" value="${pd.RECEPTION_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<c:if test="${not empty pomxs }">
								<tr>
									<td colspan="2">配件账单</td>
								</tr>
								<c:forEach items="${pomxs }" var="pomx" varStatus="vs">
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">名称:</td>
										<td><input type="text" name="PART_NAME" id="PART_NAME" value="${pomx.PART_NAME}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">价格:</td>
										<td><input type="text" name="TOTAL" id="TOTAL" value="${pomx.TOTAL}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">数量:</td>
										<td><input type="text" name="YL8" id="YL8" value="${pomx.YL8}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${not empty dsmxs }">
								<tr>
									<td colspan="2">工时报价</td>
								</tr>
								<c:forEach items="${dsmxs }" var="dsmx" varStatus="vs">
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">名称:</td>
										<td><input type="text" name="WORK_NAME" id="WORK_NAME" value="${dsmx.WORK_NAME}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">价格:</td>
										<td><input type="text" name="TOTAL" id="TOTAL" value="${dsmx.TOTAL}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
									</tr>
								</c:forEach>
							</c:if>
							<tr>
								<td style="text-align: center;" colspan="10">
									<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
								</td>
							</tr>
						</table>
						</div>
						<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
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
</div>
<!-- /.main-container -->


	<!-- 页面底部js¨ -->
	<%@ include file="../../system/index/foot.jsp"%>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript">
		$(top.hangge());
		
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		});
		</script>
</body>
</html>
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
						<input type="hidden" name="DISPATCHING_ID" id="DISPATCHING_ID" value="${pd.DISPATCHING_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">公司id:</td>
								<td><input type="text" name="gsid" id="gsid" value="${gsid}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">用户名:</td>
								<td><input type="text" name="username" id="username" value="${username}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">复核人:</td>
								<td><input type="text" name="name" id="name" value="${name}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">复核内容:</td>
								<td>
									<textarea type="text" name="content" id="content" style="width:98%; resize:none;" ></textarea>
								</td>
							</tr>
							<c:choose>
								<c:when test="${pd.CREATE_BY eq username}">
									<tr>
										<td style="text-align: center;" colspan="10">
											<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
										</td>
									</tr>
								</c:when>
								<c:otherwise> 
									<tr>
										<td style="text-align: center;" colspan="10">
											<a class="btn btn-mini btn-primary" onclick="agree();">同意</a>
											<a class="btn btn-mini btn-primary" onclick="disagree();">不同意</a>
											<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
										</td>
									</tr>
								</c:otherwise>
							</c:choose>
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
		//同意
		function agree(){
			var DISPATCHING_ID = $("#DISPATCHING_ID").val();
			var gongsiId = $("#gsid").val();
			var username = $("#username").val();
			var name = $("#name").val();
			var content = $("#content").val();
			$.ajax({
				url: "<%=basePath%>recheck/agreeDispatching.do",
				data: {
					"DISPATCHING_ID": DISPATCHING_ID,
					"gongsiId": gongsiId,
					"username": username,
					"content": content,
					"name": name
				},
				type: "post",
				success:function(data) {
					document.getElementById('zhongxin').style.display = 'none';
					top.Dialog.close();
				},
				error: function(e) {
					top.Dialog.close();
				}
			});
		}
		//不同意
		function disagree(){
			var DISPATCHING_ID = $("#DISPATCHING_ID").val();
			var gongsiId = $("#gsid").val();
			var username = $("#username").val();
			var name = $("#name").val();
			var content = $("#content").val();
			$.ajax({
				url: "<%=basePath%>recheck/disagreeDispatching.do",
				data: {
					"DISPATCHING_ID": DISPATCHING_ID,
					"gongsiId": gongsiId,
					"username": username,
					"content": content,
					"name": name
				},
				type: "post",
				success:function(data) {
					document.getElementById('zhongxin').style.display = 'none';
					top.Dialog.close();
				},
				error: function(e) {
					top.Dialog.close();
				}
			});
		}
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		});
		</script>
</body>
</html>
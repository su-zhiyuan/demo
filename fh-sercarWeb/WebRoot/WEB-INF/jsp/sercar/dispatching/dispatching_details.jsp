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
					
					<form action="dispatching/${msg }.do" name="Form" id="Form_dsmxAdd" method="post">
						<div id="zhongxin" style="padding-top: 13px;">
							<c:if test="${not empty dsmxList }">
								<table id="table_one"  class="table table-striped table-bordered table-hover">
									<c:forEach items="${dsmxList }" var="dsmx" varStatus="vs">
										<tr>
											<td style="width:75px;">作业名称:</td>
											<td><input type="text" name="WORK_NAME1" id="WORK_NAME" value="${dsmx.WORK_NAME }" readonly="readonly" maxlength="50" style="width:98%;"/></td>
										</tr>
										<tr>
											<td style="width:75px;">作业价格:</td>
											<td><input type="text" name="YL6" id="YL6" value="${dsmx.YL6 }" maxlength="50" readonly="readonly" style="width:98%;"/></td>
										</tr>
										<tr>
											<td style="width:75px;">作业状态:</td>
											<td><input type="text" name="YL15" id="YL15" value="${dsmx.YL15 }" maxlength="50" readonly="readonly" style="width:98%;"/></td>
										</tr>
										<tr>
											<td style="width:75px;">备注:</td>
											<td><input type="text" name="REMARK1" id="REMARK" value="${dsmx.REMARK }" readonly="readonly" maxlength="50" style="width:98%;"/></td>
										</tr>
										<c:if test="${msg == 'finish'}">
											<c:if test="${dsmx.YL15 == '待完成'}">
												<tr>
													<td colspan="2" align="center">
														<a class="btn btn-mini btn-success" style="margin-right:3px; height:30px;line-height:30px; margin-bottom:5px;" onclick="finishProject('${dsmx.DSMX_ID}');">完工</a>
													</td>
												</tr>
											</c:if>
										</c:if>
										<c:if test="${msg == 'detection'}">
											<c:if test="${dsmx.YL15 == '待检测'}">
												<tr>
													<td style="width:75px;text-align: right;padding-top: 13px;">检测备注:</td>
													<td><textarea type="text" name="content" class="content" style="width:98%; resize:none;" ></textarea></td>
												</tr>
												<tr>
													<td colspan="2" align="center">
														<a class="btn btn-mini btn-success" style="margin-right:3px; height:30px;line-height:30px; margin-bottom:5px;" onclick="detectionProject('${dsmx.DSMX_ID}');">检测</a>
													</td>
												</tr>
											</c:if>
										</c:if>
										<tr>
											<td colspan="2"></td>
										</tr>
									</c:forEach>
								</table>
							
							</c:if>
						</div>
						<table>
							<tr>
								<td style="text-align: center;" colspan="10">
									<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
								</td>
							</tr>
						</table>
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
		var jname = 1;
		//增加表单
		
		function finishProject(Id){
			$.ajax({
				url: "<%=basePath%>dispatching/finishProject.do",
				data:{
					"DSMX_ID" : Id
				},
				type: "post",
				async: false,
				success:function(data) {
					document.getElementById('zhongxin').style.display = 'none';
					top.Dialog.close();
				},
				error: function(e) {
					alert(JSON.stringify(e));
				}
			}) 
		}
		
		function detectionProject(Id){
			var content = $("#content").val();
			$.ajax({
				url: "<%=basePath%>dispatching/detectionProject.do",
				data:{
					"DSMX_ID" : Id,
					"content" : content
				},
				type: "post",
				async: false,
				success:function(data) {
					document.getElementById('zhongxin').style.display = 'none';
					top.Dialog.close();
				},
				error: function(e) {
					alert(JSON.stringify(e));
				}
			}) 
		}
		
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		});
		</script>
</body>
</html>
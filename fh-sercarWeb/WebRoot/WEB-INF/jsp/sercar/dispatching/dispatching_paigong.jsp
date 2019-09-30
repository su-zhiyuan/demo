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
							<c:if test="${not empty dispatch }">
								<table id="table_one"  class="table table-striped table-bordered table-hover">
									<tr>
										<td style="width:75px;">派工单ID:</td>
										<td><input type="text" name="DISPATCHING_ID" id="DISPATCHING_ID" value="${dispatch.DISPATCHING_ID }" readonly="readonly" maxlength="50" style="width:98%;"/></td>
									</tr>
									<tr>
										<td style="width:75px;">维修技师:</td>
										<td><input type="text" name="YL2" id="YL2" value="${dispatch.YL2 }" maxlength="50" readonly="readonly" style="width:98%;"/></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">检查备注:</td>
										<td><textarea type="text" name="REMARK" id="REMARK" style="width:98%; resize:none;" ></textarea></td>
									</tr>
									<tr>
										<td colspan="2" align="center">
											<a class="btn btn-mini btn-success" style="margin-right:3px; height:30px;line-height:30px; margin-bottom:5px;" onclick="finishwork('${dispatch.DISPATCHING_ID}');">检测</a>
										</td>
									</tr>
									<tr>
										<td colspan="2"></td>
									</tr>
									
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
		
		function finishwork(Id){
			var remark = $("#REMARK").val();
			$.ajax({
				url: "<%=basePath%>dispatching/DetectionWork.do",
				data:{
					"DISPATCHING_ID" : Id,
					"remark" : remark
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
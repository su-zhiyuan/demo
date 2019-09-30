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
							<c:if test="${not empty pomxList }">
								<table id="table_one"  class="table table-striped table-bordered table-hover">
									<c:forEach items="${pomxList }" var="pomx" varStatus="vs">
										<tr>
											<td style="width:75px;">零件编号:</td>
											<td><input type="text" name="PART_NUM" id="PART_NUM" value="${pomx.PART_NUM }" readonly="readonly" maxlength="50" style="width:98%;"/></td>
										</tr>
										<tr>
											<td style="width:75px;">零件名称:</td>
											<td><input type="text" name="PART_NAME" id="PART_NAME" value="${pomx.PART_NAME }" readonly="readonly" maxlength="50" style="width:98%;"/></td>
										</tr>
										<tr>
											<td style="width:75px;">零件数量:</td>
											<td><input type="text" name="PART_COUNT" id="PART_COUNT" value="${pomx.PART_COUNT }" readonly="readonly" maxlength="50" style="width:98%;"/></td>
										</tr>
										<tr>
											<td style="width:75px;">零件使用状态:</td>
											<td><input type="text" name="YL15" id="YL15" value="${pomx.YL15 }" readonly="readonly" maxlength="50" style="width:98%;"/></td>
										</tr>
										<c:if test="${pomx.YL15 == '待使用' }">
											<tr>
												<td colspan="2" align="center">
													<a class="btn btn-mini btn-success" style="margin-right:3px; height:30px;line-height:30px; margin-bottom:5px;" onclick="useLingJian('${pomx.POMX_ID}');">使用</a>
												</td>
											</tr>
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
		
		function useLingJian(Id){
			$.ajax({
				url: "<%=basePath%>dispatching/useLingJian.do",
				data:{
					"POMX_ID" : Id
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
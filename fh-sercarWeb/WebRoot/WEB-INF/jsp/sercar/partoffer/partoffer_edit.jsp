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
					
					<form action="partoffer/${msg }.do" name="Form" id="Form" method="post">
						<div id="zhongxin" style="padding-top: 13px;">
							<table id="table_report" class="table table-striped table-bordered table-hover">
								<c:if test="${not empty pomxList }">
									<tr>
										<td colspan="2" align="center">配件报价</td>
									</tr>
									<c:forEach items="${pomxList }" var="pomx" varStatus="vs">
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">名称:</td>
											<td><input type="text" name="PART_NAME" id="PART_NAME" value="${pomx.PART_NAME}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">单价:</td>
											<td><input type="text" name="YL8" id="YL8" value="${pomx.YL8}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">数量:</td>
											<td><input type="text" name="PART_COUNT" id="PART_COUNT" value="${pomx.PART_COUNT}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">订金:</td>
											<td><input type="text" name="YL16" id="YL16" value="${pomx.YL16}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">状态:</td>
											<td><input type="text" name="YL15" id="YL15" value="${pomx.YL15}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">总价:</td>
											<td><input type="text" name="TOTAL" id="TOTAL" value="${pomx.TOTAL}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
										</tr>
										<c:if test="${pomx.YL15 == '待使用' }">
											<c:if test="${stutus == '已交付定金' }">
												<tr>
													<td colspan="2" align="center">
														<a class="btn btn-mini btn-success" style="margin-right:3px; height:30px;line-height:30px; margin-bottom:5px;" onclick="delno('${pomx.POMX_ID}');">取消(退定金)</a>
														<a class="btn btn-mini btn-success" style="margin-right:3px; height:30px;line-height:30px; margin-bottom:5px;" onclick="del('${pomx.POMX_ID}');">取消(不退定金)</a>
													</td>
												</tr>
											</c:if>
											<c:if test="${stutus != '已交付定金' }">
												<tr>
													<td colspan="2" align="center">
														<a class="btn btn-mini btn-success" style="margin-right:3px; height:30px;line-height:30px; margin-bottom:5px;" onclick="delzh('${pomx.POMX_ID}');">取消</a>
													</td>
												</tr>
											</c:if>
										</c:if>
										<tr>
											<td colspan="2">
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${not empty dsmxList }">
									<tr>
										<td colspan="2" align="center">工时报价</td>
									</tr>
									<c:forEach items="${dsmxList }" var="dsmx" varStatus="vs">
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">作业类型:</td>
											<td><input type="text" name="WORK_NAME" id="WORK_NAME" value="${dsmx.WORK_NAME}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">作业名称:</td>
											<td><input type="text" name="YL7" id="YL7" value="${dsmx.YL7}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">作业价格:</td>
											<td><input type="text" name="YL6" id="YL6" value="${dsmx.YL6}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
										</tr>
										<tr>
											<td colspan="2" align="center">
												<a class="btn btn-mini btn-success" style="margin-right:3px; height:30px;line-height:30px; margin-bottom:5px;" onclick="del('${dsmx.DSMX_ID}');">取消报价</a>
											</td>
										</tr>
										<tr>
											<td colspan="2">
										</tr>
									</c:forEach>
								</c:if>
							</table>
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
		
		//删除表单
		function del(Id){
			$.ajax({
				url: "<%=basePath%>partoffer/delPartoffer.do",
				data:{
					"id" : Id
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
		
		
		//删除报价退定金
		function delno(Id){
			$.ajax({
				url: "<%=basePath%>partoffer/delNoPartoffer.do",
				data:{
					"id" : Id
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
		
		//没有交付订金取消
		function delzh(Id){
			$.ajax({
				url: "<%=basePath%>partoffer/delZhPartoffer.do",
				data:{
					"id" : Id
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
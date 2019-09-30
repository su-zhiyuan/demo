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
					
					<form action="order/${msg }.do" name="Form" id="Form_" method="post">
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">订单ID:</td>
								<td><input type="text" name="ORDER_ID" id="ORDER_ID" value="${pd.ORDER_ID}" maxlength="255" placeholder="这里输入备注" title="维修技师" style="width:98%;" readonly="readonly"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">公司ID:</td>
								<td><input type="text" name="YL10" id="YL10" value="${pd.YL10}" maxlength="255" placeholder="这里输入公司ID" title="公司ID" style="width:98%;" readonly="readonly"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">创建人:</td>
								<td><input type="text" name="NAME" id="NAME" value="${name}" maxlength="255" placeholder="这里输入公司ID" title="公司ID" style="width:98%;" readonly="readonly"/></td>
							</tr>
							<tr>
								<td colspan="2">配件报价:</td>
							</tr>
							
							<c:if test="${not empty pomxlist}">
								<c:forEach items="${pomxlist}" var="pomx" varStatus="vs">
									<tr>
										<td colspan="2" >
											<input type="checkbox" name="pomx" value="${pomx.POMX_ID}">${pomx.PART_NAME } --- ${pomx.PART_COUNT } --- ${pomx.YL8 } --- ${pomx.TOTAL }<br>
										</td>
									</tr>
								</c:forEach>
							</c:if>
							<tr>
								<td colspan="2">工时报价:</td>
							</tr>
							<c:if test="${not empty dsmxlist}">
								<c:forEach items="${dsmxlist}" var="dsmx" varStatus="vs">
									<tr>
										<td colspan="2" >
											<input type="checkbox" name="dsmx" value="${dsmx.DSMX_ID}">${dsmx.WORK_NAME } --- ${dsmx.YL6 }<br>
										</td>
									</tr>
								</c:forEach>
							</c:if>
							<tr>
								<td style="text-align: center;" colspan="10">
									<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
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
		//保存
		function save(){
			var pomxs = [];
			var rp = document.getElementsByName("pomx");
			for(var i = 0; i < rp.length; i++) {
				if(rp[i].checked) {
					pomxs.push(rp[i].value);
				}
			}
			
			var dsmxs = [];
			var rd = document.getElementsByName("dsmx");
			for(var i = 0; i < rd.length; i++) {
				if(rd[i].checked) {
					dsmxs.push(rd[i].value);
				}
			}
			var orderId = $("#ORDER_ID").val();
			var gongsiId = $("#YL10").val();
			var name = $("#NAME").val();
			
			$.ajax({
				url: "<%=basePath%>order/addSettlement.do",
				data: {
					"orderId": orderId,
					"dsmxs": JSON.stringify(dsmxs),
					"pomxs": JSON.stringify(pomxs),
					"gongsiId": gongsiId,
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
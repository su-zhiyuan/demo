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
					<form action="settlement/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="SETTLEMENT_ID" id="SETTLEMENT_ID" value="${pd.SETTLEMENT_ID}"/>
						<input type="hidden" name="dsmxIn" id="dsmxIn" value=""/>
						<input type="hidden" name="pomxIn" id="pomxIn" value=""/>
						<div id="zhongxin" style="padding-top: 13px;">
							<table id="" class="table table-striped table-bordered table-hover">
								<tr>
									<td style="width:100px;text-align: left;padding-top: 13px;">订单ID:</td>
									<td><input type="text" name="ORDER_ID" id="ORDER_ID" value="${pd.ORDER_ID}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
								</tr>
								<tr>
									<td style="width:100px;text-align: left;padding-top: 13px;">实收:</td>
									<td><input type="text" name="TOTAL" id="TOTAL" value="${pd.TOTAL}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
								</tr>
								<tr>
									<td style="width:100px;text-align: left;padding-top: 13px;">应收:</td>
									<td><input type="text" name="YL2" id="YL2" value="${pd.YL2}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
								</tr>
								<tr>
									<td style="width:100px;text-align: left;padding-top: 13px;">实收订金:</td>
									<td><input type="text" name="YL9" id="YL9" value="${pd.YL9}" maxlength="50" placeholder="0" style="width:98%;" readonly="readonly"/></td>
								</tr>
								<tr>
									<td style="width:100px;text-align: left;padding-top: 13px;">需退订金:</td>
									<td><input type="text" name="YL13" id="YL13" value="${pd.YL13}" maxlength="50" placeholder="0" style="width:98%;" readonly="readonly"/></td>
								</tr>
								<tr>
									<td style="width:100px;text-align: left;padding-top: 13px;">结算状态:</td>
									<td><input type="text" name="STATUS" id="STATUS" value="${pd.STATUS}" maxlength="50" placeholder="0" style="width:98%;" readonly="readonly"/></td>
								</tr>
								<tr>
									<td style="width:100px;text-align: left;padding-top: 13px;">复核人:</td>
									<td><input type="text" name="YL11" id="YL11" value="${pd.YL11}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
								</tr>
								<tr>
									<td style="width:100px;text-align: left;padding-top: 13px;">复核内容:</td>
									<td><input type="text" name="YL1" id="YL1" value="${pd.YL1}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
								</tr>
								<tr>
									<td style="width:100px;text-align: left;padding-top: 13px;">结算备注:</td>
									<td><input type="text" name="SREMARK" id="SREMARK" value="${pd.SREMARK}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
								</tr>
								<tr>
									<td colspan="2" align="center">配件账单</td>
								</tr>
								<c:if test="${not empty pomxList }">
									<c:forEach items="${pomxList }" var="pomx" varStatus="vs">
										<tr>
											<td style="width:100px;text-align: left;padding-top: 13px;">配件名称:</td>
											<td><input type="text" name="PART_NAME" id="PART_NAME" value="${pomx.PART_NAME}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
										</tr>
										<tr>
											<td style="width:100px;text-align: left;padding-top: 13px;">数量:</td>
											<td><input type="text" name="PART_COUNT" id="PART_COUNT" value="${pomx.PART_COUNT}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
										</tr>
										<tr>
											<td style="width:100px;text-align: left;padding-top: 13px;">单价:</td>
											<td><input type="text" name="YL8" id="YL8" value="${pomx.YL8}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
										</tr>
										<tr>
											<td style="width:100px;text-align: left;padding-top: 13px;">小计:</td>
											<td><input type="text" name="TOTAL" id="TOTAL" value="${pomx.TOTAL}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
										</tr>
									</c:forEach>
								</c:if>
								<tr>
									<td colspan="2" align="center">工时账单</td>
								</tr>
								<c:if test="${not empty dsmxList }">
									<c:forEach items="${dsmxList }" var="dsmx" varStatus="vs">
										<tr>
											<td style="width:100px;text-align: left;padding-top: 13px;">服务类型:</td>
											<td><input type="text" name="YL7" id="YL7" value="${dsmx.YL7}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
										</tr>
										<tr>
											<td style="width:100px;text-align: left;padding-top: 13px;">作业名称:</td>
											<td><input type="text" name="WORK_NAME" id="WORK_NAME" value="${dsmx.WORK_NAME}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
										</tr>
										<tr>
											<td style="width:100px;text-align: left;padding-top: 13px;">工时费:</td>
											<td><input type="text" name="YL6" id="YL6" value="${dsmx.YL6}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
										</tr>
									</c:forEach>
								</c:if>
							</table>
						</div>
						<table>
							<td style="text-align: center;" colspan="10">
									<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
							</td>
						</table>
						</div>
						<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
					</form>
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
		
			
		
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		});
		</script>
</body>
</html>
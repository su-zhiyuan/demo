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
						
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">创建人:</td>
								<td><input type="text" name="CREATE_BY" id="CREATE_BY" value="${pd.YL1}" maxlength="50" placeholder="创建人" title="创建人" style="width:98%;" readonly="readonly"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">创建时间:</td>
								<td><input type="text" name="CREATE_TIME" id="CREATE_TIME" value="${pd.YL2}" maxlength="50" placeholder="创建时间" title="创建时间" style="width:98%;" readonly="readonly"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">名称:</td>
								<td><input type="text" name="CHECKED_TIME" id="CHECKED_TIME" value="${pd.YL4}" maxlength="50" placeholder="名称" title="名称" style="width:98%;" readonly="readonly"/></td>
							</tr>	
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">类型:</td>
								<td><input type="text" name="ORDER_ID" id="ORDER_ID" value="${pd.TYPE}" maxlength="50" placeholder="出入库类型" title="类型" style="width:98%;" readonly="readonly"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">数量:</td>
								<td><input type="text" name="CHECKED_BY" id="CHECKED_BY" value="${pd.COUNT}" maxlength="50" placeholder="出入库数量" title="数量" style="width:98%;" readonly="readonly"/></td>
							</tr>
							
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">领物人:</td>
								<td><input type="text" name="DELIVERY_TIME" id="DELIVERY_TIME" value="${pd.YL7}" maxlength="50" placeholder="订单出库领物人" title="订单出库领物人" style="width:98%;" readonly="readonly"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">出库时间:</td>
								<td><input type="text" name="CHECKED_TIME" id="CHECKED_TIME" value="${pd.YL5}" maxlength="50" placeholder="出库时间" title="出库时间" style="width:98%;" readonly="readonly"/></td>
							</tr>	
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">车牌:</td>
								<td><input type="text" name="CHECKED_TIME" id="CHECKED_TIME" value="${pd.YL8}" maxlength="50" placeholder="车牌" title="车牌" style="width:98%;" readonly="readonly"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">出库类型:</td>
								<c:if test="${pd.YL9 == 1}">
									<td><input type="text" name="CHECKED_TIME" id="CHECKED_TIME" value="日常出库" maxlength="50" placeholder="出库类型" title="出库类型" style="width:98%;" readonly="readonly"/></td>
								</c:if>
								<c:if test="${pd.YL9 == 2 }">
									<td><input type="text" name="CHECKED_TIME" id="CHECKED_TIME" value="订单出库" maxlength="50" placeholder="出库类型" title="出库类型" style="width:98%;" readonly="readonly"/></td>
								</c:if>
								<c:if test="${empty pd.YL9}">
									<td><input type="text" name="CHECKED_TIME" id="CHECKED_TIME" maxlength="50" placeholder="出库类型" title="出库类型" style="width:98%;" readonly="readonly"/></td>
								</c:if>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">用途:</td>
								<td><input type="text" name="CHECKED_TIME" id="CHECKED_TIME" value="${pd.YL6}" maxlength="50" placeholder="用途" title="用途" style="width:98%;" readonly="readonly"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注:</td>
								<td><input type="text" name="REMARK" id="REMARK" value="${pd.REMARK}" maxlength="255" placeholder="备注" title="备注" style="width:98%;" readonly="readonly"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">公司id:</td>
								<td><input type="text" name="YL10" id="YL10" value="${pd.YL10}" maxlength="255" placeholder="这里输入备注" title="备注" style="width:98%;" readonly="readonly"/></td>
							</tr>
							<tr>
								<td style="text-align: center;" colspan="10">
									<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
								</td>
							</tr>
						</table>
						</div>
						
					
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
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		});
		</script>
</body>
</html>
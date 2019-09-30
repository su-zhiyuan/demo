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
					
					<form action="order/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="ORDER_ID" id="ORDER_ID" value="${pd.ORDER_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td colspan="2" align="center">订单基本信息 </td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">订单编号:</td>
								<td><input type="text" name="ORDER_ID" id="ORDER_ID" value="${order.ORDER_ID}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">订单状态:</td>
								<td><input type="text" name="ORDER_STATUS" id="ORDER_STATUS" value="${order.STATUS}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">创建人:</td>
								<td><input type="text" name="ORDER_YL11" id="ORDER_YL11" value="${order.YL11}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">创建时间:</td>
								<td><input type="text" name="ORDER_CREATE_TIME" id="ORDER_CREATE_TIME" value="${order.CREATE_TIME}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">客户姓名:</td>
								<td><input type="text" name="contacts_CONTACT" id="contacts_CONTACT" value="${contacts.CONTACT}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">客户电话:</td>
								<td><input type="text" name="contacts_CONTACT_TEL" id="contacts_CONTACT_TEL" value="${contacts.CONTACT_TEL}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
							</tr>
							<tr>
								<td colspan="2" align="center">车辆信息 </td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">创建人:</td>
								<td><input type="text" name="carInfo_YL11" id="carInfo_YL11" value="${carInfo.YL11}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">车牌号:</td>
								<td><input type="text" name="carInfo_CAR_NUM1" id="carInfo_CAR_NUM1" value="${carInfo.CAR_NUM1}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">车架号:</td>
								<td><input type="text" name="carInfo_CAR_NUM2" id="carInfo_CAR_NUM2" value="${carInfo.CAR_NUM2}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">发动机号:</td>
								<td><input type="text" name="carInfo_CAR_NUM3" id="carInfo_CAR_NUM3" value="${carInfo.CAR_NUM3}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">汽车品牌:</td>
								<td><input type="text" name="carInfo_BRAND" id="carInfo_BRAND" value="${carInfo.CAR_BRANK} - ${carInfo.YL1}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
							</tr>
							<tr>
								<td colspan="2" align="center">接车单信息 </td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">接车单ID:</td>
								<td><input type="text" name="reception_RECEPTION_ID" id="reception_RECEPTION_ID" value="${reception.RECEPTION_ID}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">服务顾问:</td>
								<td><input type="text" name="reception_YL12" id="reception_YL12" value="${reception.YL12}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">创建人:</td>
								<td><input type="text" name="reception_YL122" id="reception_YL122" value="${reception.YL12}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">入厂时间:</td>
								<td><input type="text" name="order_IN_TIME" id="order_IN_TIME" value="${order.IN_TIME}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">复核时间:</td>
								<td><input type="text" name="reception_CHECKED_TIME" id="reception_CHECKED_TIME" value="${reception.CHECKED_TIME}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">复核人:</td>
								<td><input type="text" name="reception_YL11" id="reception_YL11" value="${reception.YL11}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">复核内容:</td>
								<td><input type="text" name="reception_YL3" id="reception_YL3" value="${reception.YL3}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">状态:</td>
								<td><input type="text" name="reception_STATUS" id="reception_STATUS" value="${reception.STATUS}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">油量:</td>
								<td><input type="text" name="reception_YL6" id="reception_YL6" value="${reception.YL6}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">油量图片:</td>
								<td><img src="${reception.YL7}" name="reception_YL7" id="reception_YL7" maxlength="50"  style="height: 200px " /></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">里程:</td>
								<td><input type="text" name="reception_MILEAGE" id="reception_MILEAGE" value="${reception.MILEAGE}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">里程图片:</td>
								<td ><img src="${reception.YL5}" name="reception_YL5" id="reception_YL5" maxlength="50"  style="height: 200px " /></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">外观图片:</td>
								<td >
									<c:if test="${not empty photo}">
										<c:forEach items="${photo}" var="var" varStatus="vs" >
											<img src="${var}" maxlength="50"  style="height: 100px" />
										</c:forEach>
									</c:if>
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">是否路测:</td>
								<td><input type="text" name="reception_MILEAGE" id="reception_MILEAGE" value="${reception.IS_AGREE}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
							</tr>
							<tr>
								<td colspan="2" align="center">信息反馈单 </td>
							</tr>
							<c:if test="${not empty list_inforelay}">
								<c:forEach items="${list_inforelay }" var="inforelay" varStatus="vs">
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">反馈单号:</td>
										<td><input type="text" name="inforelay_INFORELAY_ID" id="inforelay_INFORELAY_ID" value="${inforelay.INFORELAY_ID}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">状态:</td>
										<td><input type="text" name="inforelay_STATUS" id="inforelay_STATUS" value="${inforelay.STATUS}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">复核人:</td>
										<td><input type="text" name="inforelay_YL11" id="inforelay_YL11" value="${inforelay.YL11}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">复核内容:</td>
										<td><input type="text" name="inforelay_YL2" id="inforelay_YL2" value="${inforelay.YL2}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">反馈内容:</td>
										<td><input type="text" name="inforelay_RELAY_CONTEND" id="inforelay_RELAY_CONTEND" value="${inforelay.RELAY_CONTEND}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">诊断人:</td>
										<td><input type="text" name="inforelay_YL12" id="inforelay_YL12" value="${inforelay.YL12}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">诊断人:</td>
										<td><input type="text" name="inforelay_DIAGNOSTIC_RESULT" id="inforelay_DIAGNOSTIC_RESULT" value="${inforelay.DIAGNOSTIC_RESULT}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">录音:</td>
										<td>
										<c:if test="${not empty inforelay.luyin}"></c:if>
											<c:forEach items="${inforelay.luyin }"  var="var" varStatus="vs" >
												<audio src="${var }" controls="controls"></audio>
											</c:forEach>
										</td>
									</tr>
									<tr>
										<td colspan="2"></td>
									</tr>
								</c:forEach>
								
							</c:if>
							<tr>
								<td colspan="2" align="center">报价单信息 </td>
							</tr>
							<c:if test="${not empty list_partoffer}">
								<c:forEach items="${list_partoffer }" var="partoffer" varStatus="vs">
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">报价单号:</td>
										<td><input type="text" name="partoffer_PARTOFFER_ID" id="partoffer_PARTOFFER_ID" value="${partoffer.PARTOFFER_ID}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">报价人:</td>
										<td><input type="text" name="partoffer_YL12" id="partoffer_YL12" value="${partoffer.YL12}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">复核人:</td>
										<td><input type="text" name="partoffer_YL12" id="partoffer_YL12" value="${partoffer.YL11}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">复核内容:</td>
										<td><input type="text" name="partoffer_YL1" id="partoffer_YL1" value="${partoffer.YL1}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">合计:</td>
										<td><input type="text" name="partoffer_TOTAL" id="partoffer_TOTAL" value="${partoffer.TOTAL}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">报价状态:</td>
										<td><input type="text" name="partoffer_STATUS" id="partoffer_STATUS" value="${partoffer.STATUS}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">订金合计:</td>
										<td><input type="text" name="partoffer_YL8" id="partoffer_YL8" value="${partoffer.YL8}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">备注:</td>
										<td><textarea type="text" name="partoffer_REMARK" id="partoffer_REMARK" value="${partoffer.REMARK}" readonly="readonly" maxlength="50"  style="width:98%;"></textarea></td>
									</tr>
									<c:if test="${not empty partoffer.list_pomx}">
										<tr>
											<td colspan="2" >具体配件报价单信息 </td>
										</tr>
										<c:forEach items="${partoffer.list_pomx }" var="pomx" varStatus="vs">
											<tr>
												<td style="width:75px;text-align: right;padding-top: 13px;">名称:</td>
												<td><input type="text" name="pomx_PART_NAME" id="pomx_PART_NAME" value="${pomx.PART_NAME}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
											</tr>
											<tr>
												<td style="width:75px;text-align: right;padding-top: 13px;">价格:</td>
												<td><input type="text" name="pomx_YL8" id="pomx_YL8" value="${pomx.YL8}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
											</tr>
											<tr>
												<td style="width:75px;text-align: right;padding-top: 13px;">数量:</td>
												<td><input type="text" name="pomx_PART_COUNT" id="pomx_PART_COUNT" value="${pomx.PART_COUNT}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
											</tr>
											<tr>
												<td style="width:75px;text-align: right;padding-top: 13px;">订金:</td>
												<td><input type="text" name="pomx_YL16" id="pomx_YL16" value="${pomx.YL16}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
											</tr>
											<tr>
												<td colspan="2"></td>
											</tr>
										</c:forEach>
									</c:if>
									<c:if test="${not empty partoffer.list_partdsmx}">
										<tr>
											<td colspan="2">具体工时报价单信息 </td>
										</tr>
										<c:forEach items="${partoffer.list_partdsmx }" var="partdsmx" varStatus="vs">
											<tr>
												<td style="width:75px;text-align: right;padding-top: 13px;">名称:</td>
												<td><input type="text" name="partdsmx_WORK_NAME" id="partdsmxPART_WORK_NAME" value="${partdsmx.WORK_NAME}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
											</tr>
											<tr>
												<td style="width:75px;text-align: right;padding-top: 13px;">价格:</td>
												<td><input type="text" name="partdsmx_YL6" id="partdsmx_YL6" value="${partdsmx.YL6}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
											</tr>
											<tr>
												<td colspan="2"></td>
											</tr>
										</c:forEach>
									</c:if>
								</c:forEach>
							</c:if>
							<tr>
								<td colspan="2" align="center">派工单信息 </td>
							</tr>
							<c:if test="${not empty list_dispatching}">
								<c:forEach items="${list_dispatching }" var="dispatching" varStatus="vs">
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">工作时间:</td>
										<td><input type="text" name="dispatching_YL5" id="dispatching_YL5" value="${dispatching.YL5}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">创建人:</td>
										<td><input type="text" name="dispatching_YL12" id="dispatching_YL12" value="${dispatching.YL12}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">创建时间:</td>
										<td><input type="text" name="dispatching_CREATE_TIME" id="dispatching_CREATE_TIME" value="${dispatching.CREATE_TIME}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">派工状态:</td>
										<td><input type="text" name="dispatching_STATUS" id="dispatching_STATUS" value="${dispatching.STATUS}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">维修技师:</td>
										<td><input type="text" name="dispatching_YL2" id="dispatching_YL2" value="${dispatching.YL2}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">复核人:</td>
										<td><input type="text" name="dispatching_YL11" id="dispatching_YL11" value="${dispatching.YL11}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">复核内容:</td>
										<td><input type="text" name="dispatching_YL1" id="dispatching_YL1" value="${dispatching.YL1}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">预交车日:</td>
										<td><input type="text" name="dispatching_EXPECTED_COMPLETION_DATE" id="dispatching_EXPECTED_COMPLETION_DATE" value="${dispatching.EXPECTED_COMPLETION_DATE}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">完工日期:</td>
										<td><input type="text" name="dispatching_COMPLETION_TIME" id="dispatching_COMPLETION_TIME" value="${dispatching.COMPLETION_TIME}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">完工备注:</td>
										<td><input type="text" name="dispatching_REMARK" id="dispatching_REMARK" value="${dispatching.REMARK}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">总检查员:</td>
										<td><input type="text" name="dispatching_INSPECT" id="dispatching_INSPECT" value="${dispatching.INSPECT}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">总检查备注:</td>
										<td><input type="text" name="dispatching_INSPECT_REMARK" id="dispatching_INSPECT_REMARK" value="${dispatching.INSPECT_REMARK}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
								</c:forEach>
							</c:if>
							<tr>
								<td colspan="2" align="center">具体派工单信息 </td>
							</tr>
							<c:if test="${not empty list_dsmx}">
								<c:forEach items="${list_dsmx }" var="dsmx" varStatus="vs">
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">作业类型:</td>
										<td><input type="text" name="dsmx_YL7" id="dsmx_YL7" value="${dsmx.YL7}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">作业名称:</td>
										<td><input type="text" name="dsmx_WORK_NAME" id="dsmx_WORK_NAME" value="${dsmx.WORK_NAME}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">作业状态:</td>
										<td><input type="text" name="dsmx_YL15" id="dsmx_YL15" value="${dsmx.YL15}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">检查备注:</td>
										<td><input type="text" name="dsmx_YL16" id="dsmx_YL16" value="${dsmx.YL16}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td colspan="2"></td>
									</tr>
								</c:forEach>
							</c:if>
							<tr>
								<td colspan="2" align="center">结算单信息 </td>
							</tr>
							<c:if test="${not empty settlement}">
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">实收:</td>
									<td><input type="text" name="settlement_TOTAL" id="settlement_TOTAL" value="${settlement.TOTAL}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">应收:</td>
									<td><input type="text" name="settlement_YL2" id="settlement_YL2" value="${settlement.YL2}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">实收定金:</td>
									<td><input type="text" name="order_YL9" id="order_YL9" value="${order.YL9}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">需退定金:</td>
									<td><input type="text" name="order_YL13" id="order_YL13" value="${order.YL13}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">结算状态:</td>
									<td><input type="text" name="settlement_STATUS" id="settlement_STATUS" value="${settlement.STATUS}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">复核人:</td>
									<td><input type="text" name="settlement_YL11" id="settlement_YL11" value="${settlement.YL11}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">复核内容:</td>
									<td><input type="text" name="settlement_YL1" id="settlement_YL1" value="${settlement.YL1}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">结算备注:</td>
									<td><input type="text" name="settlement_REMARK" id="settlement_REMARK" value="${settlement.REMARK}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
								</tr>
							</c:if>
							<tr>
								<td colspan="2" align="center">结算单配件账单详情 </td>
							</tr>
							<c:if test="${not empty list_settPomx}">
								<c:forEach items="${list_settPomx }" var="settPomx" varStatus="vs">
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">配件名称:</td>
										<td><input type="text" name="settPomx_PART_NAME" id="settPomx_PART_NAME" value="${settPomx.PART_NAME}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">数量:</td>
										<td><input type="text" name="settPomx_PART_COUNT" id="settPomx_PART_COUNT" value="${settPomx.PART_COUNT}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">单价:</td>
										<td><input type="text" name="settPomx_YL8" id="settPomx_YL8" value="${settPomx.YL8}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">小计:</td>
										<td><input type="text" name="settPomx_TOTAL" id="dsmx_TOTAL" value="${settPomx.TOTAL}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td colspan="2"></td>
									</tr>
								</c:forEach>
							</c:if>
							<tr>
								<td colspan="2" align="center">结算单工时账单详情 </td>
							</tr>
							<c:if test="${not empty list_settDsmx}">
								<c:forEach items="${list_settDsmx }" var="settDsmx" varStatus="vs">
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">服务类型:</td>
										<td><input type="text" name="YL7" id="YL7" value="${settDsmx.YL7}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">作业名称:</td>
										<td><input type="text" name="settDsmx_WORK_NAME" id="settDsmx_WORK_NAME" value="${settDsmx.WORK_NAME}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">工时费:</td>
										<td><input type="text" name="settDsmx_YL6" id="settDsmx_YL6" value="${settDsmx.YL6}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
									</tr>
									<tr>
										<td colspan="2"></td>
									</tr>
								</c:forEach>
							</c:if>
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

		</script>
</body>
</html>
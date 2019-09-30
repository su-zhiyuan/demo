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
								<td colspan="2"> 订单基本信息 </td>
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
								<td colspan="2"> 车辆信息 </td>
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
								<td colspan="2"> 接车单信息 </td>
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
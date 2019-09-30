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
					
					<form action="pomx/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="POMX_ID" id="POMX_ID" value="${pd.POMX_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">配件报价ID:</td>
								<td><input type="text" name="PART_OFFER_ID" id="PART_OFFER_ID" value="${pd.PART_OFFER_ID}" maxlength="50" placeholder="这里输入配件报价ID" title="配件报价ID" style="width:98%;" <c:if test="${msg == 'edit'}"> readonly="readonly"</c:if>/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">结算单ID:</td>
								<td><input type="text" name="SETT_ID" id="SETT_ID" value="${pd.SETT_ID}" maxlength="50" placeholder="这里输入结算单ID" title="结算单ID" style="width:98%;" <c:if test="${msg == 'edit'}"> readonly="readonly"</c:if>/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">配件名称:</td>
								<td><input type="text" name="PART_NAME" id="PART_NAME" value="${pd.PART_NAME}" maxlength="50" placeholder="这里输入配件名称" title="配件名称" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">配件数量:</td>
								<td><input type="text" name="PART_COUNT" id="PART_COUNT" value="${pd.PART_COUNT}" maxlength="50" placeholder="这里输入配件数量" title="配件数量" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">配件编号:</td>
								<td><input type="text" name="PART_NUM" id="PART_NUM" value="${pd.PART_NUM}" maxlength="50" placeholder="这里输入配件编号" title="配件编号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">单价:</td>
								<td><input type="text" name="PRICE" id="PRICE" value="${pd.PRICE}" maxlength="50" placeholder="这里输入单价" title="单价" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">小计:</td>
								<td><input type="text" name="TOTAL" id="TOTAL" value="${pd.TOTAL}" maxlength="50" placeholder="这里输入小计" title="小计" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">货期:</td>
								<td><input type="text" name="DELIVERY" id="DELIVERY" value="${pd.DELIVERY}" maxlength="50" placeholder="这里输入货期" title="货期" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注:</td>
								<td><input type="text" name="REMARK" id="REMARK" value="${pd.REMARK}" maxlength="255" placeholder="这里输入备注" title="备注" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">采购单ID:</td>
								<td><input type="text" name="YL1" id="YL1" value="${pd.YL1}" maxlength="255" placeholder="这里输入 采购单ID" title=" 预留" style="width:98%;" <c:if test="${msg == 'edit'}"> readonly="readonly"</c:if>/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">订单ID:</td>
								<td><input type="text" name="YL2" id="YL2" value="${pd.YL2}" maxlength="255" placeholder="这里输入订单ID" title="预留" style="width:98%;" <c:if test="${msg == 'edit'}"> readonly="readonly"</c:if>/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">是否采购:</td>
								<td><input type="text" name="IS_PUR" id="IS_PUR" value="${pd.IS_PUR}" maxlength="50" placeholder="这里输入是否采购" title="是否采购" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">采购数量:</td>
								<td><input type="text" name="PUR_COUNT" id="PUR_COUNT" value="${pd.PUR_COUNT}" maxlength="50" placeholder="这里输入采购数量" title="采购数量" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">公司id:</td>
								<td><input type="text" name="YL10" id="YL10" value="${pd.YL10}" maxlength="255" placeholder="这里输入备注" title="备注" style="width:98%;" readonly="readonly"/></td>
							</tr>
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
			/* if($("#PART_OFFER_ID").val()==""){
				$("#PART_OFFER_ID").tips({
					side:3,
		            msg:'请输入配件报价ID',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PART_OFFER_ID").focus();
			return false;
			}
			if($("#SETT_ID").val()==""){
				$("#SETT_ID").tips({
					side:3,
		            msg:'请输入结算单ID',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#SETT_ID").focus();
			return false;
			}
			if($("#PART_NAME").val()==""){
				$("#PART_NAME").tips({
					side:3,
		            msg:'请输入配件名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PART_NAME").focus();
			return false;
			}
			if($("#PART_COUNT").val()==""){
				$("#PART_COUNT").tips({
					side:3,
		            msg:'请输入配件数量',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PART_COUNT").focus();
			return false;
			}
			if($("#PART_NUM").val()==""){
				$("#PART_NUM").tips({
					side:3,
		            msg:'请输入配件编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PART_NUM").focus();
			return false;
			}
			if($("#PRICE").val()==""){
				$("#PRICE").tips({
					side:3,
		            msg:'请输入单价',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PRICE").focus();
			return false;
			}
			if($("#TOTAL").val()==""){
				$("#TOTAL").tips({
					side:3,
		            msg:'请输入小计',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TOTAL").focus();
			return false;
			}
			if($("#DELIVERY").val()==""){
				$("#DELIVERY").tips({
					side:3,
		            msg:'请输入货期',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#DELIVERY").focus();
			return false;
			}
			if($("#REMARK").val()==""){
				$("#REMARK").tips({
					side:3,
		            msg:'请输入备注',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#REMARK").focus();
			return false;
			}
			if($("#YL1").val()==""){
				$("#YL1").tips({
					side:3,
		            msg:'请输入 预留',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL1").focus();
			return false;
			}
			if($("#YL2").val()==""){
				$("#YL2").tips({
					side:3,
		            msg:'请输入预留',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL2").focus();
			return false;
			}
			if($("#IS_PUR").val()==""){
				$("#IS_PUR").tips({
					side:3,
		            msg:'请输入是否采购',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#IS_PUR").focus();
			return false;
			}
			if($("#PUR_COUNT").val()==""){
				$("#PUR_COUNT").tips({
					side:3,
		            msg:'请输入采购数量',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PUR_COUNT").focus();
			return false;
			} */
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}
		
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		});
		</script>
</body>
</html>
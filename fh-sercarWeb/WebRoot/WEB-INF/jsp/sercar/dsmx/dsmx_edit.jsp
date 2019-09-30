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
					
					<form action="dsmx/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="DSMX_ID" id="DSMX_ID" value="${pd.DSMX_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">派工单ID:</td>
								<td><input type="text" name="DISPATCHING_ID" id="DISPATCHING_ID" value="${pd.DISPATCHING_ID}" maxlength="50" placeholder="这里输入派工单ID" title="派工单ID" style="width:98%;" <c:if test="${msg == 'edit'}"> readonly="readonly"</c:if>/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">结算单ID:</td>
								<td><input type="text" name="SETTLEMENT_ID" id="SETTLEMENT_ID" value="${pd.SETTLEMENT_ID}" maxlength="50" placeholder="这里输入结算单ID" title="结算单ID" style="width:98%;" <c:if test="${msg == 'edit'}"> readonly="readonly"</c:if>/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">作业名称:</td>
								<td><input type="text" name="WORK_NAME" id="WORK_NAME" value="${pd.WORK_NAME}" maxlength="50" placeholder="这里输入作业名称" title="作业名称" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">检查员:</td>
								<td><input type="text" name="INSPECT" id="INSPECT" value="${pd.INSPECT}" maxlength="50" placeholder="这里输入检查员" title="检查员" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">工时:</td>
								<td><input type="text" name="WORK_HOURS" id="WORK_HOURS" value="${pd.WORK_HOURS}" maxlength="50" placeholder="这里输入工时" title="工时" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">金额:</td>
								<td><input type="text" name="TOTAL" id="TOTAL" value="${pd.TOTAL}" maxlength="50" placeholder="这里输入金额" title="金额" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">应收:</td>
								<td><input type="text" name="RECEIVABLE" id="RECEIVABLE" value="${pd.RECEIVABLE}" maxlength="50" placeholder="这里输入应收" title="应收" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">实收:</td>
								<td><input type="text" name="RECEIPTS" id="RECEIPTS" value="${pd.RECEIPTS}" maxlength="50" placeholder="这里输入实收" title="实收" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">折扣率:</td>
								<td><input type="text" name="DISCOUNT_RATE" id="DISCOUNT_RATE" value="${pd.DISCOUNT_RATE}" maxlength="50" placeholder="这里输入折扣率" title="折扣率" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注:</td>
								<td><input type="text" name="REMARK" id="REMARK" value="${pd.REMARK}" maxlength="50" placeholder="这里输入备注" title="备注" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">报价单ID:</td>
								<td><input type="text" name="YL1" id="YL1" value="${pd.YL1}" maxlength="255" placeholder="这里输入报价单ID" title="预留" style="width:98%;" <c:if test="${msg == 'edit'}"> readonly="readonly"</c:if>/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">订单ID:</td>
								<td><input type="text" name="YL2" id="YL2" value="${pd.YL2}" maxlength="255" placeholder="这里输入订单ID" title="预留" style="width:98%;" <c:if test="${msg == 'edit'}"> readonly="readonly"</c:if>/></td>
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
			/* if($("#DISPATCHING_ID").val()==""){
				$("#DISPATCHING_ID").tips({
					side:3,
		            msg:'请输入派工单ID',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#DISPATCHING_ID").focus();
			return false;
			}
			if($("#SETTLEMENT_ID").val()==""){
				$("#SETTLEMENT_ID").tips({
					side:3,
		            msg:'请输入结算单ID',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#SETTLEMENT_ID").focus();
			return false;
			}
			if($("#WORK_NAME").val()==""){
				$("#WORK_NAME").tips({
					side:3,
		            msg:'请输入作业名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#WORK_NAME").focus();
			return false;
			}
			if($("#INSPECT").val()==""){
				$("#INSPECT").tips({
					side:3,
		            msg:'请输入检查员',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#INSPECT").focus();
			return false;
			}
			if($("#WORK_HOURS").val()==""){
				$("#WORK_HOURS").tips({
					side:3,
		            msg:'请输入工时',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#WORK_HOURS").focus();
			return false;
			}
			if($("#TOTAL").val()==""){
				$("#TOTAL").tips({
					side:3,
		            msg:'请输入金额',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TOTAL").focus();
			return false;
			}
			if($("#RECEIVABLE").val()==""){
				$("#RECEIVABLE").tips({
					side:3,
		            msg:'请输入应收',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#RECEIVABLE").focus();
			return false;
			}
			if($("#RECEIPTS").val()==""){
				$("#RECEIPTS").tips({
					side:3,
		            msg:'请输入实收',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#RECEIPTS").focus();
			return false;
			}
			if($("#DISCOUNT_RATE").val()==""){
				$("#DISCOUNT_RATE").tips({
					side:3,
		            msg:'请输入折扣率',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#DISCOUNT_RATE").focus();
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
		            msg:'请输入预留',
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
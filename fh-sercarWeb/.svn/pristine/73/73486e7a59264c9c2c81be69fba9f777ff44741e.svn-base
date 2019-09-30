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
					
					<form action="inforelay/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="INFORELAY_ID" id="INFORELAY_ID" value="${pd.INFORELAY_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<c:if test="${msg == 'edit'}">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">创建人:</td>
								<td><input type="text" name="CREATE_BY" id="CREATE_BY" value="${pd.CREATE_BY}" maxlength="50" placeholder="这里输入创建人" title="创建人" style="width:98%;" readonly="readonly"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">创建时间:</td>
								<td><input type="text" name="CREATE_TIME" id="CREATE_TIME" value="${pd.CREATE_TIME}" maxlength="50" placeholder="这里输入创建时间" title="创建时间" style="width:98%;" readonly="readonly"/></td>
							</tr>
							</c:if>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">订单ID:</td>
								<td><input type="text" name="ORDER_ID" id="ORDER_ID" value="${pd.ORDER_ID}" maxlength="50" placeholder="这里输入订单ID" title="订单ID" style="width:98%;" <c:if test="${msg == 'edit'}"> readonly="readonly"</c:if>/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">复核人:</td>
								<td><input type="text" name="CHECKED_BY" id="CHECKED_BY" value="${pd.CHECKED_BY}" maxlength="50" placeholder="这里输入复核人" title="复核人" style="width:98%;" readonly="readonly"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">复核时间:</td>
								<td><input type="text" name="CHECKED_TIME" id="CHECKED_TIME" value="${pd.CHECKED_TIME}" maxlength="50" placeholder="这里输入复核时间" title="复核时间" style="width:98%;" readonly="readonly"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">复核内容:</td>
								<td><input type="text" name="YL2" id="YL2" value="${pd.YL2}" maxlength="50" placeholder="这里输入复核时间" title="复核时间" style="width:98%;" readonly="readonly"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">反馈内容:</td>
								<td><input type="text" name="RELAY_CONTEND" id="RELAY_CONTEND" value="${pd.RELAY_CONTEND}" maxlength="2550" placeholder="这里输入反馈内容" title="反馈内容" style="width:98%;"/></td>
							</tr>
							<%-- <tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">反馈文件:</td>
								<td><input type="text" name="RELAY_FILE" id="RELAY_FILE" value="${pd.RELAY_FILE}" maxlength="255" placeholder="这里输入反馈文件" title="反馈文件" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">诊断人:</td>
								<td><input type="text" name="DIAGNOSTIC_BY" id="DIAGNOSTIC_BY" value="${pd.DIAGNOSTIC_BY}" maxlength="50" placeholder="这里输入诊断人" title="诊断人" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">诊断时间:</td>
								<td><input type="text" name="DIAGNOSTIC_TIME" id="DIAGNOSTIC_TIME" value="${pd.DIAGNOSTIC_TIME}" maxlength="50" placeholder="这里输入诊断时间" title="诊断时间" style="width:98%;"/></td>
								<td style="padding-left:2px;"><input class="span10 date-picker" name="DIAGNOSTIC_TIME" id="DIAGNOSTIC_TIME"  value="${pd.DIAGNOSTIC_TIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="诊断时间" title="诊断时间"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">诊断结果:</td>
								<td><input type="text" name="DIAGNOSTIC_RESULT" id="DIAGNOSTIC_RESULT" value="${pd.DIAGNOSTIC_RESULT}" maxlength="2550" placeholder="这里输入诊断结果" title="诊断结果" style="width:98%;"/></td>
							</tr> --%>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注:</td>
								<td><input type="text" name="REMARK" id="REMARK" value="${pd.REMARK}" maxlength="255" placeholder="这里输入备注" title="备注" style="width:98%;"/></td>
							</tr>
							<%-- <tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">状态:</td>
								<td><input type="text" name="STATUS" id="STATUS" value="${pd.STATUS}" maxlength="50" placeholder="这里输入状态" title="状态" style="width:98%;" readonly="readonly"/></td>
							</tr>  --%>
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
			/* if($("#ORDER_ID").val()==""){
				$("#ORDER_ID").tips({
					side:3,
		            msg:'请输入订单ID',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ORDER_ID").focus();
			return false;
			}
			if($("#CREATE_BY").val()==""){
				$("#CREATE_BY").tips({
					side:3,
		            msg:'请输入创建人',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CREATE_BY").focus();
			return false;
			}
			if($("#CREATE_TIME").val()==""){
				$("#CREATE_TIME").tips({
					side:3,
		            msg:'请输入创建时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CREATE_TIME").focus();
			return false;
			}
			if($("#CHECKED_BY").val()==""){
				$("#CHECKED_BY").tips({
					side:3,
		            msg:'请输入复核人',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CHECKED_BY").focus();
			return false;
			}
			if($("#CHECKED_TIME").val()==""){
				$("#CHECKED_TIME").tips({
					side:3,
		            msg:'请输入复核时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CHECKED_TIME").focus();
			return false;
			}
			if($("#RELAY_CONTEND").val()==""){
				$("#RELAY_CONTEND").tips({
					side:3,
		            msg:'请输入反馈内容',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#RELAY_CONTEND").focus();
			return false;
			}
			if($("#RELAY_FILE").val()==""){
				$("#RELAY_FILE").tips({
					side:3,
		            msg:'请输入反馈文件',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#RELAY_FILE").focus();
			return false;
			}
			if($("#DIAGNOSTIC_BY").val()==""){
				$("#DIAGNOSTIC_BY").tips({
					side:3,
		            msg:'请输入诊断人',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#DIAGNOSTIC_BY").focus();
			return false;
			}
			if($("#DIAGNOSTIC_TIME").val()==""){
				$("#DIAGNOSTIC_TIME").tips({
					side:3,
		            msg:'请输入诊断时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#DIAGNOSTIC_TIME").focus();
			return false;
			}
			if($("#DIAGNOSTIC_RESULT").val()==""){
				$("#DIAGNOSTIC_RESULT").tips({
					side:3,
		            msg:'请输入诊断结果',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#DIAGNOSTIC_RESULT").focus();
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
			if($("#STATUS").val()==""){
				$("#STATUS").tips({
					side:3,
		            msg:'请输入状态',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#STATUS").focus();
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
			}
 */			$("#Form").submit();
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
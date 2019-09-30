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
					
					<form action="sname/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="SNAME_ID" id="SNAME_ID" value="${pd.SNAME_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<c:if test="${msg == 'save'}">
								<tr>
									<c:if test="${not empty list}">
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">项目类型:</td>
											<td>
												<select class="chosen-select form-control" name="PID" id="PID"  data-placeholder="请选择状态" style="vertical-align:top;width: 120px;">									
													<c:forEach items="${list}" var="var" varStatus="vs">
						                                <option value="${var.STYPE_ID}" <c:if test="${var.STYPE_ID==pd.PID}">selected</c:if> > ${var.TYPE_NAME}</option>
						                            </c:forEach>
											  	</select>
											</td>
										</tr>
									</c:if>
									<c:if test="${empty list }">
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">项目类型ID:</td>
											<td><input type="text" name="PID" id="PID" value="${pd.PID}" maxlength="255" placeholder="这里输入项目类型ID" title="上级ID" style="width:98%;"/></td>
										</tr>
									</c:if>
								</tr>
							</c:if>
							<c:if test="${msg == 'edit'}">
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">项目类型:</td>
									<td>
										<select class="chosen-select form-control" name="PID" id="PID"  data-placeholder="请选择状态" style="vertical-align:top;width: 120px;">									
											<c:forEach items="${list}" var="var" varStatus="vs">
				                                <option value="${var.STYPE_ID}" <c:if test="${var.STYPE_ID==pd.PID}">selected</c:if> > ${var.TYPE_NAME}</option>
				                            </c:forEach>
									  	</select>
									</td>
								</tr>
							</c:if>
							<tr>
							<td style="width:75px;text-align: right;padding-top: 13px;">项目名称:</td>
								<td><input type="text" name="S_NAME" id="S_NAME" value="${pd.S_NAME}" maxlength="255" placeholder="这里输入项目名称" title="项目名称" style="width:98%;"/></td>
							</tr>
							<tr>
							<td style="width:75px;text-align: right;padding-top: 13px;">项目费用:</td>
								<td><input type="text" name="YL1" id="YL1" value="${pd.YL1}" maxlength="255" placeholder="这里输入项目金额" title="项目费用" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注:</td>
								<td><input type="text" name="REMARK" id="REMARK" value="${pd.REMARK}" maxlength="255" placeholder="这里输入备注" title="备注" style="width:98%;"/></td>
							</tr>
							<c:if test="${msg == 'edit'}">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">公司id:</td>
								<td><input type="text" name="YL10" id="YL10" value="${pd.YL10}" maxlength="255" placeholder="这里输入备注" title="备注" style="width:98%;" readonly="readonly"/></td>
							</tr>
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
			/* if($("#PID").val()==""){
				$("#PID").tips({
					side:3,
		            msg:'请输入上级ID',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PID").focus();
			return false;
			}
			if($("#S_NAME").val()==""){
				$("#S_NAME").tips({
					side:3,
		            msg:'请输入项目名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#S_NAME").focus();
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
			}
			if($("#YL3").val()==""){
				$("#YL3").tips({
					side:3,
		            msg:'请输入预留',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL3").focus();
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
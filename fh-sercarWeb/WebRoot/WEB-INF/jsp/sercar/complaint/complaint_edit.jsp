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
					
					<form action="complaint/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="COMPLAINT_ID" id="COMPLAINT_ID" value="${pd.COMPLAINT_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
						
							<tr style="display:none">
								<td style="width:75px;text-align: right;padding-top: 13px;">创建人:</td>
								<td><input type="text" name="CREATER_BY" id="CREATER_BY" value="${pd.CREATER_BY}" maxlength="100" placeholder="创建人" title="创建人	" style="width:98%;" readonly="readonly"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">投诉人:</td>
								<td><input type="text" name="COMPLAINT_NAME" id="COMPLAINT_NAME" value="${pd.COMPLAINT_NAME}" maxlength="100" placeholder="投诉人" title="投诉人" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">投诉人电话:</td>
								<td><input type="text" name="COMPLAINT_TEL" id="COMPLAINT_TEL" value="${pd.COMPLAINT_TEL}" maxlength="100" placeholder="投诉人电话" title="投诉人电话" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">投诉内容:</td>
								<td><input type="text" name="COMPLAINT_CONTENT" id="COMPLAINT_CONTENT" value="${pd.COMPLAINT_CONTENT}" maxlength="1000" placeholder="投诉内容" title="投诉内容" style="width:98%;"/></td>
							</tr>
							<tr style="display:none">
								<td style="width:75px;text-align: right;padding-top: 13px;">投诉时间:</td>
								<td><input type="text" name="COMPLAINT_TIME" id="COMPLAINT_TIME" value="${pd.COMPLAINT_TIME}" maxlength="100" placeholder="投诉时间" title="投诉时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">状态:</td>
								<%-- <td><input type="text" name="STATE" id="STATE" value="${pd.STATE}" maxlength="100" placeholder="状态" title="状态" style="width:98%;"/></td> --%>
								<td style="vertical-align:top;padding-left:2px;">								
										<select name="STATE" id="STATE" >
											<option value="">--请选择--</option>
				                            <c:forEach items="${list1}" var="var" varStatus="vs">
				                                <option value="${var.NAME}" <c:if test="${var.NAME==pd.STATE}">selected</c:if> > ${var.NAME}</option>
				                            </c:forEach>
	                        			</select>						
								</td>
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
			/* if($("#CREATER_BY").val()==""){
				$("#CREATER_BY").tips({
					side:3,
		            msg:'请输入备注2',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CREATER_BY").focus();
			return false;
			}
			if($("#COMPLAINT_NAME").val()==""){
				$("#COMPLAINT_NAME").tips({
					side:3,
		            msg:'请输入备注3',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#COMPLAINT_NAME").focus();
			return false;
			}
			if($("#COMPLAINT_TEL").val()==""){
				$("#COMPLAINT_TEL").tips({
					side:3,
		            msg:'请输入备注4',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#COMPLAINT_TEL").focus();
			return false;
			}
			if($("#COMPLAINT_CONTENT").val()==""){
				$("#COMPLAINT_CONTENT").tips({
					side:3,
		            msg:'请输入备注5',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#COMPLAINT_CONTENT").focus();
			return false;
			}
			if($("#COMPLAINT_TIME").val()==""){
				$("#COMPLAINT_TIME").tips({
					side:3,
		            msg:'请输入备注6',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#COMPLAINT_TIME").focus();
			return false;
			}
			if($("#STATE").val()==""){
				$("#STATE").tips({
					side:3,
		            msg:'请输入备注7',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#STATE").focus();
			return false;
			}
			if($("#YL1").val()==""){
				$("#YL1").tips({
					side:3,
		            msg:'请输入备注8',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL1").focus();
			return false;
			}
			if($("#YL2").val()==""){
				$("#YL2").tips({
					side:3,
		            msg:'请输入备注9',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL2").focus();
			return false;
			}
			if($("#YL3").val()==""){
				$("#YL3").tips({
					side:3,
		            msg:'请输入备注10',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL3").focus();
			return false;
			}
			if($("#YL4").val()==""){
				$("#YL4").tips({
					side:3,
		            msg:'请输入备注11',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL4").focus();
			return false;
			}
			if($("#YL5").val()==""){
				$("#YL5").tips({
					side:3,
		            msg:'请输入备注12',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL5").focus();
			return false;
			}
			if($("#YL6").val()==""){
				$("#YL6").tips({
					side:3,
		            msg:'请输入备注13',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL6").focus();
			return false;
			}
			if($("#YL7").val()==""){
				$("#YL7").tips({
					side:3,
		            msg:'请输入备注14',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL7").focus();
			return false;
			}
			if($("#YL8").val()==""){
				$("#YL8").tips({
					side:3,
		            msg:'请输入备注15',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL8").focus();
			return false;
			}
			if($("#YL9").val()==""){
				$("#YL9").tips({
					side:3,
		            msg:'请输入备注16',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL9").focus();
			return false;
			}
			if($("#YL10").val()==""){
				$("#YL10").tips({
					side:3,
		            msg:'请输入备注17',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL10").focus();
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
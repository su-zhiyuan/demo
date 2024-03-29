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
					
					<form action="mycollection/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="MYCOLLECTION_ID" id="MYCOLLECTION_ID" value="${pd.MYCOLLECTION_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<c:if test="${msg == 'edit'}">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">用户名:</td>
								<td><input type="text" name="USERNAME" id="USERNAME" value="${pd.USERNAME}" maxlength="50" placeholder="这里输入用户名" title="用户名" style="width:98%;"readonly="readonly"/></td>
							</tr>
							</c:if>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">公告ID:</td>
								<td><input type="text" name="BULLETIN_ID" id="BULLETIN_ID" value="${pd.BULLETIN_ID}" maxlength="50" placeholder="这里输入公告ID" title="公告ID" style="width:98%;"  <c:if test="${msg == 'edit'}"> readonly="readonly"</c:if>/></td>
							</tr>
							<%-- <tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留:</td>
								<td><input type="text" name="YL1" id="YL1" value="${pd.YL1}" maxlength="255" placeholder="这里输入预留" title="预留" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留:</td>
								<td><input type="text" name="YL2" id="YL2" value="${pd.YL2}" maxlength="255" placeholder="这里输入预留" title="预留" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留:</td>
								<td><input type="text" name="YL3" id="YL3" value="${pd.YL3}" maxlength="255" placeholder="这里输入预留" title="预留" style="width:98%;"/></td>
							</tr> --%>
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
			/* if($("#USERNAME").val()==""){
				$("#USERNAME").tips({
					side:3,
		            msg:'请输入用户名',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#USERNAME").focus();
			return false;
			}
			if($("#BULLETIN_ID").val()==""){
				$("#BULLETIN_ID").tips({
					side:3,
		            msg:'请输入公告ID',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#BULLETIN_ID").focus();
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
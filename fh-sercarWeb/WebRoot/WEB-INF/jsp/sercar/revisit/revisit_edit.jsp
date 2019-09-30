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
					
					<form action="revisit/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="REVISIT_ID" id="REVISIT_ID" value="${pd.REVISIT_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">订单id:</td>
								<td><input type="text" name="ORDER_ID" id="ORDER_ID" value="${pd.ORDER_ID}" maxlength="255" placeholder="这里输入订单id" title="订单id" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">创建人:</td>
								<td><input type="text" name="CREATER" id="CREATER" value="${pd.CREATER}" maxlength="255" placeholder="这里输入创建人" title="创建人" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">创建时间:</td>
								<td><input type="text" name="CREATE_TIME" id="CREATE_TIME" value="${pd.CREATE_TIME}" maxlength="255" placeholder="这里输入创建时间" title="创建时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">客户名:</td>
								<td><input type="text" name="CLIENT" id="CLIENT" value="${pd.CLIENT}" maxlength="255" placeholder="这里输入客户名" title="客户名" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">客户电话:</td>
								<td><input type="text" name="CLIENT_PHONE" id="CLIENT_PHONE" value="${pd.CLIENT_PHONE}" maxlength="255" placeholder="这里输入客户电话" title="客户电话" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">回访内容:</td>
								<td><input type="text" name="CONTENT" id="CONTENT" value="${pd.CONTENT}" maxlength="255" placeholder="这里输入回访内容" title="回访内容" style="width:98%;"/></td>
							</tr>
							<%-- <tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留1:</td>
								<td><input type="text" name="YL1" id="YL1" value="${pd.YL1}" maxlength="255" placeholder="这里输入预留1" title="预留1" style="width:98%;"/></td>
							</tr>
							 <tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留2:</td>
								<td><input type="text" name="YL2" id="YL2" value="${pd.YL2}" maxlength="255" placeholder="这里输入预留2" title="预留2" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留3:</td>
								<td><input type="text" name="YL3" id="YL3" value="${pd.YL3}" maxlength="255" placeholder="这里输入预留3" title="预留3" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留4:</td>
								<td><input type="text" name="YL4" id="YL4" value="${pd.YL4}" maxlength="255" placeholder="这里输入预留4" title="预留4" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留5:</td>
								<td><input type="text" name="YL5" id="YL5" value="${pd.YL5}" maxlength="255" placeholder="这里输入预留5" title="预留5" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留6:</td>
								<td><input type="text" name="YL6" id="YL6" value="${pd.YL6}" maxlength="255" placeholder="这里输入预留6" title="预留6" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留7:</td>
								<td><input type="text" name="YL7" id="YL7" value="${pd.YL7}" maxlength="255" placeholder="这里输入预留7" title="预留7" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留8:</td>
								<td><input type="text" name="YL8" id="YL8" value="${pd.YL8}" maxlength="255" placeholder="这里输入预留8" title="预留8" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留9:</td>
								<td><input type="text" name="YL9" id="YL9" value="${pd.YL9}" maxlength="255" placeholder="这里输入预留9" title="预留9" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留10:</td>
								<td><input type="text" name="YL10" id="YL10" value="${pd.YL10}" maxlength="255" placeholder="这里输入预留10" title="预留10" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留11:</td>
								<td><input type="text" name="YL11" id="YL11" value="${pd.YL11}" maxlength="255" placeholder="这里输入预留11" title="预留11" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留12:</td>
								<td><input type="text" name="YL12" id="YL12" value="${pd.YL12}" maxlength="255" placeholder="这里输入预留12" title="预留12" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留13:</td>
								<td><input type="text" name="YL13" id="YL13" value="${pd.YL13}" maxlength="255" placeholder="这里输入预留13" title="预留13" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留14:</td>
								<td><input type="text" name="YL14" id="YL14" value="${pd.YL14}" maxlength="255" placeholder="这里输入预留14" title="预留14" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留15:</td>
								<td><input type="text" name="YL15" id="YL15" value="${pd.YL15}" maxlength="255" placeholder="这里输入预留15" title="预留15" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留16:</td>
								<td><input type="text" name="YL16" id="YL16" value="${pd.YL16}" maxlength="255" placeholder="这里输入预留16" title="预留16" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留17:</td>
								<td><input type="text" name="YL17" id="YL17" value="${pd.YL17}" maxlength="255" placeholder="这里输入预留17" title="预留17" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留18:</td>
								<td><input type="text" name="YL18" id="YL18" value="${pd.YL18}" maxlength="255" placeholder="这里输入预留18" title="预留18" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留19:</td>
								<td><input type="text" name="YL19" id="YL19" value="${pd.YL19}" maxlength="255" placeholder="这里输入预留19" title="预留19" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">预留20:</td>
								<td><input type="text" name="YL20" id="YL20" value="${pd.YL20}" maxlength="255" placeholder="这里输入预留20" title="预留20" style="width:98%;"/></td>
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
			if($("#ORDER_ID").val()==""){
				$("#ORDER_ID").tips({
					side:3,
		            msg:'请输入订单id',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ORDER_ID").focus();
			return false;
			}
			if($("#CREATER").val()==""){
				$("#CREATER").tips({
					side:3,
		            msg:'请输入创建人',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CREATER").focus();
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
			if($("#CLIENT").val()==""){
				$("#CLIENT").tips({
					side:3,
		            msg:'请输入客户名',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CLIENT").focus();
			return false;
			}
			if($("#CLIENT_PHONE").val()==""){
				$("#CLIENT_PHONE").tips({
					side:3,
		            msg:'请输入客户电话',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CLIENT_PHONE").focus();
			return false;
			}
			if($("#CONTENT").val()==""){
				$("#CONTENT").tips({
					side:3,
		            msg:'请输入回访内容',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CONTENT").focus();
			return false;
			}
			if($("#YL1").val()==""){
				$("#YL1").tips({
					side:3,
		            msg:'请输入预留1',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL1").focus();
			return false;
			}
			if($("#YL2").val()==""){
				$("#YL2").tips({
					side:3,
		            msg:'请输入预留2',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL2").focus();
			return false;
			}
			if($("#YL3").val()==""){
				$("#YL3").tips({
					side:3,
		            msg:'请输入预留3',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL3").focus();
			return false;
			}
			if($("#YL4").val()==""){
				$("#YL4").tips({
					side:3,
		            msg:'请输入预留4',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL4").focus();
			return false;
			}
			if($("#YL5").val()==""){
				$("#YL5").tips({
					side:3,
		            msg:'请输入预留5',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL5").focus();
			return false;
			}
			if($("#YL6").val()==""){
				$("#YL6").tips({
					side:3,
		            msg:'请输入预留6',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL6").focus();
			return false;
			}
			if($("#YL7").val()==""){
				$("#YL7").tips({
					side:3,
		            msg:'请输入预留7',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL7").focus();
			return false;
			}
			if($("#YL8").val()==""){
				$("#YL8").tips({
					side:3,
		            msg:'请输入预留8',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL8").focus();
			return false;
			}
			if($("#YL9").val()==""){
				$("#YL9").tips({
					side:3,
		            msg:'请输入预留9',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL9").focus();
			return false;
			}
			if($("#YL10").val()==""){
				$("#YL10").tips({
					side:3,
		            msg:'请输入预留10',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL10").focus();
			return false;
			}
			if($("#YL11").val()==""){
				$("#YL11").tips({
					side:3,
		            msg:'请输入预留11',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL11").focus();
			return false;
			}
			if($("#YL12").val()==""){
				$("#YL12").tips({
					side:3,
		            msg:'请输入预留12',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL12").focus();
			return false;
			}
			if($("#YL13").val()==""){
				$("#YL13").tips({
					side:3,
		            msg:'请输入预留13',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL13").focus();
			return false;
			}
			if($("#YL14").val()==""){
				$("#YL14").tips({
					side:3,
		            msg:'请输入预留14',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL14").focus();
			return false;
			}
			if($("#YL15").val()==""){
				$("#YL15").tips({
					side:3,
		            msg:'请输入预留15',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL15").focus();
			return false;
			}
			if($("#YL16").val()==""){
				$("#YL16").tips({
					side:3,
		            msg:'请输入预留16',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL16").focus();
			return false;
			}
			if($("#YL17").val()==""){
				$("#YL17").tips({
					side:3,
		            msg:'请输入预留17',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL17").focus();
			return false;
			}
			if($("#YL18").val()==""){
				$("#YL18").tips({
					side:3,
		            msg:'请输入预留18',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL18").focus();
			return false;
			}
			if($("#YL19").val()==""){
				$("#YL19").tips({
					side:3,
		            msg:'请输入预留19',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL19").focus();
			return false;
			}
			if($("#YL20").val()==""){
				$("#YL20").tips({
					side:3,
		            msg:'请输入预留20',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL20").focus();
			return false;
			}
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
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
					
					<form action="companyinfo/${msg }.do" name="Form" id="Form" method="post" enctype="multipart/form-data">
						<input type="hidden" name="COMPANYINFO_ID" id="COMPANYINFO_ID" value="${pd.COMPANYINFO_ID}"/>
						<c:if test="${msg eq 'edit'}">
							<input type="hidden" name="COMPANY_LOGO" id="COMPANY_LOGO" value="${pd.COMPANY_LOGO}"/>
						</c:if>
						
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">公司名称:</td>
								<td><input type="text" name="COMPANY_NAME" id="COMPANY_NAME" value="${pd.COMPANY_NAME}" maxlength="50" placeholder="这里输入公司名称" title="公司名称" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">公司地址:</td>
								<td><input type="text" name="COMPANY_ADDR" id="COMPANY_ADDR" value="${pd.COMPANY_ADDR}" maxlength="50" placeholder="这里输入公司地址" title="公司地址" style="width:98%;"/></td>
							</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">公司LOGO:</td>
									<td><input type="file" name="COMPANY_LOGO" id="COMPANY_LOGO" value="${pd.COMPANY_LOGO}"/></td>
								</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">公司简介:</td>
								<td><textarea type="text" name="COMPANY_JJ" id="COMPANY_JJ" value="${pd.COMPANY_JJ}" maxlength="2550" placeholder="这里输入公司简介" title="公司简介" style="width:98%;">${pd.COMPANY_JJ}</textarea></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">员工手册:</td>
								<td><textarea type="text" name="YL1" id="YL1" value="${pd.YL1}" maxlength="2550" placeholder="这里输入员工手册" title="员工手册" style="width:98%;">${pd.YL1}</textarea></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">上班时间:</td>
								<%-- <td><input type="text" name="yl4" id="yl4" value="${pd.yl4}" maxlength="50" placeholder="这里输入上班时间" title="上班时间" style="width:98%;"/></td> --%>
								<td>
									<select class="chosen-select form-control" name="yl4" id="yl4" data-placeholder="请选择" style="vertical-align:top;width: 120px;">
									<option value="">-上班时间-</option>
										<option value="7:00" <c:if test="${'7:00'==pd.yl4}">selected</c:if> >7:00</option>
										<option value="7:30" <c:if test="${'7:30'==pd.yl4}">selected</c:if> >7:30</option>
										<option value="8:00" <c:if test="${'8:00'==pd.yl4}">selected</c:if> >8:00</option>
										<option value="8:30" <c:if test="${'8:30'==pd.yl4}">selected</c:if> >8:30</option>
										<option value="9:00" <c:if test="${'9:00'==pd.yl4}">selected</c:if> >9:00</option>
										<option value="9:30" <c:if test="${'9:30'==pd.yl4}">selected</c:if> >9:30</option>
										<option value="10:00" <c:if test="${'10:00'==pd.yl4}">selected</c:if> >10:00</option>
										<option value="10:30" <c:if test="${'10:30'==pd.yl4}">selected</c:if> >10:30</option>
										<option value="11:00" <c:if test="${'11:00'==pd.yl4}">selected</c:if> >11:00</option>
										<option value="11:30" <c:if test="${'11:30'==pd.yl4}">selected</c:if> >11:30</option>
										<option value="12:00" <c:if test="${'12:00'==pd.yl4}">selected</c:if> >12:00</option>
										<option value="12:30" <c:if test="${'12:30'==pd.yl4}">selected</c:if> >12:30</option>
										<option value="13:00" <c:if test="${'13:00'==pd.yl4}">selected</c:if> >13:00</option>
										<option value="13:30" <c:if test="${'13:30'==pd.yl4}">selected</c:if> >13:30</option>
										<option value="14:00" <c:if test="${'14:00'==pd.yl4}">selected</c:if> >14:00</option>
										<option value="14:30" <c:if test="${'14:30'==pd.yl4}">selected</c:if> >14:30</option>
										<option value="15:00" <c:if test="${'15:00'==pd.yl4}">selected</c:if> >15:00</option>
										<option value="15:30" <c:if test="${'15:30'==pd.yl4}">selected</c:if> >15:30</option>
										<option value="16:00" <c:if test="${'16:00'==pd.yl4}">selected</c:if> >16:00</option>
										<option value="16:30" <c:if test="${'16:30'==pd.yl4}">selected</c:if> >16:30</option>
										<option value="17:00" <c:if test="${'17:00'==pd.yl4}">selected</c:if> >17:00</option>
										<option value="17:30" <c:if test="${'17:30'==pd.yl4}">selected</c:if> >17:30</option>
										<option value="18:00" <c:if test="${'18:00'==pd.yl4}">selected</c:if> >18:00</option>
										<option value="18:30" <c:if test="${'18:30'==pd.yl4}">selected</c:if> >18:30</option>
										<option value="19:00" <c:if test="${'19:00'==pd.yl4}">selected</c:if> >19:00</option>
										<option value="19:30" <c:if test="${'19:30'==pd.yl4}">selected</c:if> >19:30</option>
										<option value="20:00" <c:if test="${'20:00'==pd.yl4}">selected</c:if> >20:00</option>
										<option value="20:30" <c:if test="${'20:30'==pd.yl4}">selected</c:if> >20:30</option>
										<option value="21:00" <c:if test="${'21:00'==pd.yl4}">selected</c:if> >21:00</option>
										<option value="21:30" <c:if test="${'21:30'==pd.yl4}">selected</c:if> >21:30</option>
										<option value="22:00" <c:if test="${'22:00'==pd.yl4}">selected</c:if> >22:00</option>
										<option value="22:30" <c:if test="${'22:30'==pd.yl4}">selected</c:if> >22:30</option>
										<option value="23:00" <c:if test="${'23:00'==pd.yl4}">selected</c:if> >23:00</option>
										<option value="23:30" <c:if test="${'23:30'==pd.yl4}">selected</c:if> >23:30</option>
										<option value="00:00" <c:if test="${'00:00'==pd.yl4}">selected</c:if> >00:00</option>
										<option value="00:30" <c:if test="${'00:30'==pd.yl4}">selected</c:if> >00:30</option>
										<option value="1:00" <c:if test="${'1:00'==pd.yl4}">selected</c:if> >1:00</option>
										<option value="1:30" <c:if test="${'1:30'==pd.yl4}">selected</c:if> >1:30</option>
										<option value="2:00" <c:if test="${'2:00'==pd.yl4}">selected</c:if> >2:00</option>
										<option value="2:30" <c:if test="${'2:30'==pd.yl4}">selected</c:if> >2:30</option>
										<option value="3:00" <c:if test="${'3:00'==pd.yl4}">selected</c:if> >3:00</option>
										<option value="3:30" <c:if test="${'3:30'==pd.yl4}">selected</c:if> >3:30</option>
										<option value="4:00" <c:if test="${'4:00'==pd.yl4}">selected</c:if> >4:00</option>
										<option value="4:30" <c:if test="${'4:30'==pd.yl4}">selected</c:if> >4:30</option>
										<option value="5:00" <c:if test="${'5:00'==pd.yl4}">selected</c:if> >5:00</option>
										<option value="5:30" <c:if test="${'5:30'==pd.yl4}">selected</c:if> >5:30</option>
										<option value="6:00" <c:if test="${'6:00'==pd.yl4}">selected</c:if> >6:00</option>
										<option value="6:30" <c:if test="${'6:30'==pd.yl4}">selected</c:if> >6:30</option>
								  	</select>
								</td>			
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">下班时间:</td>
<%-- 								<td><input type="text" name="yl5" id="yl5" value="${pd.yl5}" maxlength="50" placeholder="这里输入下班时间" title="下班时间" style="width:98%;"/></td> --%>
								<td>
									<select class="chosen-select form-control" name="yl5" id="yl5" data-placeholder="请选择" style="vertical-align:top;width: 120px;">
									<option value="">-下班时间-</option>										
										<option value="16:00" <c:if test="${'16:00'==pd.yl5}">selected</c:if> >16:00</option>
										<option value="16:30" <c:if test="${'16:30'==pd.yl5}">selected</c:if> >16:30</option>
										<option value="17:00" <c:if test="${'17:00'==pd.yl5}">selected</c:if> >17:00</option>
										<option value="17:30" <c:if test="${'17:30'==pd.yl5}">selected</c:if> >17:30</option>
										<option value="18:00" <c:if test="${'18:00'==pd.yl5}">selected</c:if> >18:00</option>
										<option value="18:30" <c:if test="${'18:30'==pd.yl5}">selected</c:if> >18:30</option>
										<option value="19:00" <c:if test="${'19:00'==pd.yl5}">selected</c:if> >19:00</option>
										<option value="19:30" <c:if test="${'19:30'==pd.yl5}">selected</c:if> >19:30</option>
										<option value="20:00" <c:if test="${'20:00'==pd.yl5}">selected</c:if> >20:00</option>
										<option value="20:30" <c:if test="${'20:30'==pd.yl5}">selected</c:if> >20:30</option>
										<option value="21:00" <c:if test="${'21:00'==pd.yl5}">selected</c:if> >21:00</option>
										<option value="21:30" <c:if test="${'21:30'==pd.yl5}">selected</c:if> >21:30</option>
										<option value="22:00" <c:if test="${'22:00'==pd.yl5}">selected</c:if> >22:00</option>
										<option value="22:30" <c:if test="${'22:30'==pd.yl5}">selected</c:if> >22:30</option>
										<option value="23:00" <c:if test="${'23:00'==pd.yl5}">selected</c:if> >23:00</option>
										<option value="23:30" <c:if test="${'23:30'==pd.yl5}">selected</c:if> >23:30</option>
										<option value="00:00" <c:if test="${'00:00'==pd.yl5}">selected</c:if> >00:00</option>
										<option value="00:30" <c:if test="${'00:30'==pd.yl5}">selected</c:if> >00:30</option>
										<option value="1:00" <c:if test="${'1:00'==pd.yl5}">selected</c:if> >1:00</option>
										<option value="1:30" <c:if test="${'1:30'==pd.yl5}">selected</c:if> >1:30</option>
										<option value="2:00" <c:if test="${'2:00'==pd.yl5}">selected</c:if> >2:00</option>
										<option value="2:30" <c:if test="${'2:30'==pd.yl5}">selected</c:if> >2:30</option>
										<option value="3:00" <c:if test="${'3:00'==pd.yl5}">selected</c:if> >3:00</option>
										<option value="3:30" <c:if test="${'3:30'==pd.yl5}">selected</c:if> >3:30</option>
										<option value="4:00" <c:if test="${'4:00'==pd.yl5}">selected</c:if> >4:00</option>
										<option value="4:30" <c:if test="${'4:30'==pd.yl5}">selected</c:if> >4:30</option>
										<option value="5:00" <c:if test="${'5:00'==pd.yl5}">selected</c:if> >5:00</option>
										<option value="5:30" <c:if test="${'5:30'==pd.yl5}">selected</c:if> >5:30</option>
										<option value="6:00" <c:if test="${'6:00'==pd.yl5}">selected</c:if> >6:00</option>
										<option value="6:30" <c:if test="${'6:30'==pd.yl5}">selected</c:if> >6:30</option>
										<option value="7:00" <c:if test="${'7:00'==pd.yl5}">selected</c:if> >7:00</option>
										<option value="7:30" <c:if test="${'7:30'==pd.yl5}">selected</c:if> >7:30</option>
										<option value="8:00" <c:if test="${'8:00'==pd.yl5}">selected</c:if> >8:00</option>
										<option value="8:30" <c:if test="${'8:30'==pd.yl5}">selected</c:if> >8:30</option>
										<option value="9:00" <c:if test="${'9:00'==pd.yl5}">selected</c:if> >9:00</option>
										<option value="9:30" <c:if test="${'9:30'==pd.yl5}">selected</c:if> >9:30</option>
										<option value="10:00" <c:if test="${'10:00'==pd.yl5}">selected</c:if> >10:00</option>
										<option value="10:30" <c:if test="${'10:30'==pd.yl5}">selected</c:if> >10:30</option>
										<option value="11:00" <c:if test="${'11:00'==pd.yl5}">selected</c:if> >11:00</option>
										<option value="11:30" <c:if test="${'11:30'==pd.yl5}">selected</c:if> >11:30</option>
										<option value="12:00" <c:if test="${'12:00'==pd.yl5}">selected</c:if> >12:00</option>
										<option value="12:30" <c:if test="${'12:30'==pd.yl5}">selected</c:if> >12:30</option>
										<option value="13:00" <c:if test="${'13:00'==pd.yl5}">selected</c:if> >13:00</option>
										<option value="13:30" <c:if test="${'13:30'==pd.yl5}">selected</c:if> >13:30</option>
										<option value="14:00" <c:if test="${'14:00'==pd.yl5}">selected</c:if> >14:00</option>
										<option value="14:30" <c:if test="${'14:30'==pd.yl5}">selected</c:if> >14:30</option>
										<option value="15:00" <c:if test="${'15:00'==pd.yl5}">selected</c:if> >15:00</option>
										<option value="15:30" <c:if test="${'15:30'==pd.yl5}">selected</c:if> >15:30</option>
								  	</select>
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">公司电话:</td>
								<td><input type="text" name="yl11" id="yl11" value="${pd.yl11}" maxlength="50" placeholder="这里输入公司电话" title="公司电话" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">使用状态:</td>
								<td>
									<select  name="yl10" id="yl10">
											<option value="">请选择</option>
									  		<option value="使用" <c:if test="${pd.yl10=='使用'}">selected</c:if>>使用</option>
									 		<option value="停用" <c:if test="${pd.yl10=='停用'}">selected</c:if>>停用</option>
									</select>
								</td>
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
			/* if($("#COMPANY_NAME").val()==""){
				$("#COMPANY_NAME").tips({
					side:3,
		            msg:'请输入公司名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#COMPANY_NAME").focus();
			return false;
			}
			if($("#COMPANY_ADDR").val()==""){
				$("#COMPANY_ADDR").tips({
					side:3,
		            msg:'请输入公司地址',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#COMPANY_ADDR").focus();
			return false;
			}
			if($("#COMPANY_LOGO").val()==""){
				$("#COMPANY_LOGO").tips({
					side:3,
		            msg:'请输入公司LOGO',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#COMPANY_LOGO").focus();
			return false;
			}
			if($("#COMPANY_JJ").val()==""){
				$("#COMPANY_JJ").tips({
					side:3,
		            msg:'请输入公司简介',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#COMPANY_JJ").focus();
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
		            msg:'请输入员工手册',
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
		
		//选择图片
		function xuanTp(ID){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="选择图片";
			 diag.URL = '<%=basePath%>pictures/listfortc.do';
			 diag.Width = 860;
			 diag.Height = 680;
			 diag.CancelEvent = function(){ //关闭事件
				 $("#"+ID).val('<%=basePath%>'+diag.innerFrame.contentWindow.document.getElementById('xzvalue').value);
				 diag.close();
			 };
			 diag.show();
		}
		
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		});
		</script>
</body>
</html>
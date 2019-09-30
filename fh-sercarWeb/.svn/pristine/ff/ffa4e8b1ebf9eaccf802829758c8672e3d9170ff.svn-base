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
					
					<form action="leave/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="LEAVE_ID" id="LEAVE_ID" value="${pd.LEAVE_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<c:if test="${msg == 'edit'}">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">创建人:</td>
								<td style="padding-left:2px;"><input class="text" name="YL3" id="YL3"  value="${pd.YL3}" type="text"  readonly="readonly" style="width:88px;" placeholder=创建人 title="创建人" readonly="readonly"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">创建时间:</td>
								<td style="padding-left:2px;"><input class="text" name="YL4" id="YL4"  value="${pd.YL4}" type="text"  readonly="readonly" style="width:88px;" placeholder="创建时间" title="创建时间" readonly="readonly"/></td>
							</tr>
							</c:if>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">类型:</td>
								<td style="padding-left:2px;"><input class="text" name="TYPE" id="TYPE"  value="${pd.TYPE}" type="text"  readonly="readonly" style="width:88px;" </td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">开始时间:</td>
								<td><input type="text" name="STARTTIME" id="STARTTIME" value="${pd.STARTTIME}" maxlength="30" placeholder="这里输入开始时间" title="备注2" style="width:98%;"/></td>
<%-- 								<td style="padding-left:2px;"><input class="span10 date-picker" name="STARTTIME" id="STARTTIME"  value="${pd.STARTTIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="开始时间" title="开始时间"/></td> --%>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">结束时间:</td>
								<td><input type="text" name="ENDTIME" id="ENDTIME" value="${pd.ENDTIME}" maxlength="30" placeholder="这里输入结束时间" title="备注3" style="width:98%;"/></td>
<%-- 								<td style="padding-left:2px;"><input class="span10 date-picker" name="ENDTIME" id="ENDTIME"  value="${pd.ENDTIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="结束时间" title="结束时间"/></td> --%>
							</tr>
<!-- 							<tr> -->
<!-- 								<td style="width:75px;text-align: right;padding-top: 13px;">类型:</td> -->
<%-- 								<td><input type="text" name="TYPE" id="TYPE" value="${pd.TYPE}" maxlength="50" placeholder="这里输入备注2" title="备注2" style="width:98%;"/></td> --%>
<!-- 								<td style="vertical-align:top;padding-left:2px;">								 -->
<!-- 										<select name="TYPE" id="TYPE" > -->
<!-- 											<option value="">--请选择--</option> -->
<%-- 				                            <c:forEach items="${list1}" var="var" varStatus="vs"> --%>
<%-- 				                                <option value="${var.NAME}" <c:if test="${var.NAME==pd.TYPE}">selected</c:if> > ${var.NAME}</option> --%>
<%-- 				                            </c:forEach> --%>
<!-- 	                        			</select>						 -->
<!-- 								</td> -->
<!-- 							</tr> -->
							
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">事由:</td>
								<td><input type="text" name="CAUSE" id="CAUSE" value="${pd.CAUSE}" maxlength="255" readonly="readonly" placeholder="这里输入事由" title="备注5" style="width:98%;"/></td>
							</tr>
							
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">状态:</td>
								<td style="vertical-align:top;padding-left:2px;">								
										<select name="YL1" id="YL1" >
											<option value="">--请选择--</option>
				                            <c:forEach items="${list2}" var="var2" varStatus="vs2">
				                                <option value="${var2.NAME}" <c:if test="${var2.NAME==pd.YL1}">selected</c:if> > ${var2.NAME}</option>
				                            </c:forEach>
	                        			</select>						
								</td>
							</tr>
							
							<%-- <tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注:</td>
								<td><input type="text" name="REMARK" id="REMARK" value="${pd.REMARK}" maxlength="255" readonly="readonly" placeholder="请输入备注" title="备注" style="width:98%;"/></td>
							</tr> --%>
							<tr style="display:none">
								<td style="width:75px;text-align: right;padding-top: 13px;">月份：</td>
								<td><input type="text" name="YL5" id="YL5" value="${pd.YL5}" maxlength="255" readonly="readonly" placeholder="" title="月份" style="width:98%;"/></td>
							</tr>
							<tr style="display:none">
								<td style="width:75px;text-align: right;padding-top: 13px;">请假天数:</td>
								<td><input type="text" name="YL6" id="YL6" value="${pd.YL6}" maxlength="255" readonly="readonly" placeholder="" title="请假天数" style="width:98%;"/></td>
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
			/* if($("#TYPE").val()==""){
				$("#TYPE").tips({
					side:3,
		            msg:'请输入备注2',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TYPE").focus();
			return false;
			}
			if($("#STARTTIME").val()==""){
				$("#STARTTIME").tips({
					side:3,
		            msg:'请输入备注3',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#STARTTIME").focus();
			return false;
			}
			if($("#ENDTIME").val()==""){
				$("#ENDTIME").tips({
					side:3,
		            msg:'请输入备注4',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ENDTIME").focus();
			return false;
			}
			if($("#CAUSE").val()==""){
				$("#CAUSE").tips({
					side:3,
		            msg:'请输入备注5',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CAUSE").focus();
			return false;
			}
			if($("#REMARK").val()==""){
				$("#REMARK").tips({
					side:3,
		            msg:'请输入备注6',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#REMARK").focus();
			return false;
			}
			if($("#YL1").val()==""){
				$("#YL1").tips({
					side:3,
		            msg:'请输入备注7',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL1").focus();
			return false;
			}
			if($("#YL2").val()==""){
				$("#YL2").tips({
					side:3,
		            msg:'请输入备注8',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL2").focus();
			return false;
			}
			if($("#YL3").val()==""){
				$("#YL3").tips({
					side:3,
		            msg:'请输入备注9',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL3").focus();
			return false;
			}
			if($("#YL4").val()==""){
				$("#YL4").tips({
					side:3,
		            msg:'请输入备注10',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL4").focus();
			return false;
			}
			if($("#YL5").val()==""){
				$("#YL5").tips({
					side:3,
		            msg:'请输入备注11',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL5").focus();
			return false;
			}
			if($("#YL6").val()==""){
				$("#YL6").tips({
					side:3,
		            msg:'请输入备注12',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL6").focus();
			return false;
			}
			if($("#YL7").val()==""){
				$("#YL7").tips({
					side:3,
		            msg:'请输入备注13',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL7").focus();
			return false;
			}
			if($("#YL8").val()==""){
				$("#YL8").tips({
					side:3,
		            msg:'请输入备注14',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL8").focus();
			return false;
			}
			if($("#YL9").val()==""){
				$("#YL9").tips({
					side:3,
		            msg:'请输入备注15',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL9").focus();
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
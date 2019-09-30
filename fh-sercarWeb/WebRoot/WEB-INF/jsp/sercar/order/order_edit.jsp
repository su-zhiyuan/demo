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
								<td style="width:75px;text-align: right;padding-top: 13px;">车辆ID:</td>
								<td><input type="text" name="CAR_ID" id="CAR_ID" value="${pd.CAR_ID}" maxlength="50" placeholder="这里输入车辆ID" title="车辆ID" style="width:98%;" <c:if test="${msg == 'edit'}"> readonly="readonly"</c:if>/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">车牌号:</td>
								<td><input type="text" name="CAR_NUM1" id="CAR_NUM1" value="${pd.CAR_NUM1}" maxlength="50" placeholder="这里输入车牌号" title="车牌号" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">车架号:</td>
								<td><input type="text" name="CAR_NUM2" id="CAR_NUM2" value="${pd.CAR_NUM2}" maxlength="50" placeholder="这里输入车架号" title="车架号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">发动机号:</td>
								<td><input type="text" name="CAR_NUM3" id="CAR_NUM3" value="${pd.CAR_NUM3}" maxlength="50" placeholder="这里输入发动机号" title="发动机号" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">状态:</td>
										<%-- <td><input type="text" name="STATUS" id="STATUS" value="${pd.STATUS}" maxlength="50" placeholder="这里输入状态" title="状态" style="width:98%;"/></td> --%>
								<td style="vertical-align:top;padding-left:2px;">								
									<select name="STATUS" id="STATUS" >
										<option value="">--请选择--</option>
			                            <c:forEach items="${list1}" var="var" varStatus="vs">
			                                <option value="${var.NAME}" <c:if test="${var.NAME==pd.STATUS}">selected</c:if> > ${var.NAME}</option>
			                            </c:forEach>
                        			</select>						
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">服务类型:</td>
								<%-- <td><input type="text" name="SERVICE_TYPE" id="SERVICE_TYPE" value="${pd.SERVICE_TYPE}" maxlength="50" placeholder="这里输入服务类型" title="服务类型" style="width:98%;"/></td> --%>
								<td style="vertical-align:top;padding-left:2px;">								
									<select name="SERVICE_TYPE" id="SERVICE_TYPE" >
										<option value="">--请选择--</option>
			                            <c:forEach items="${list2}" var="var" varStatus="vs">
			                                <option value="${var.TYPE_NAME}" <c:if test="${var.TYPE_NAME==pd.SERVICE_TYPE}">selected</c:if> > ${var.TYPE_NAME}</option>
			                            </c:forEach>
                        			</select>						
								</td>
							</tr>
							
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">联系人ID:</td>
								<td><input type="text" name="CONTACT_ID" id="CONTACT_ID" value="${pd.CONTACT_ID}" maxlength="50" placeholder="这里输入联系人ID" title="联系人ID" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">服务顾问:</td>
								<td><input type="text" name="SERVICE_CONSULTANT" id="SERVICE_CONSULTANT" value="${pd.SERVICE_CONSULTANT}" maxlength="50" placeholder="这里输入服务顾问" title="服务顾问" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">总检查员:</td>
								<td><input type="text" name="INSPECTOR" id="INSPECTOR" value="${pd.INSPECTOR}" maxlength="50" placeholder="这里输入总检查员" title="总检查员" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">配件合计:</td>
								<td><input type="text" name="PART_TOTAL" id="PART_TOTAL" value="${pd.PART_TOTAL}" maxlength="50" placeholder="这里输入配件合计" title="配件合计" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">工时合计:</td>
								<td><input type="text" name="HOURS_TOTAL" id="HOURS_TOTAL" value="${pd.HOURS_TOTAL}" maxlength="50" placeholder="这里输入工时合计" title="工时合计" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">合计:</td>
								<td><input type="text" name="TOTAL" id="TOTAL" value="${pd.TOTAL}" maxlength="50" placeholder="这里输入合计" title="合计" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">入厂时间:</td>
								 <td><input type="text" name="IN_TIME" id="IN_TIME" value="${pd.IN_TIME}" maxlength="50" placeholder="这里输入入厂时间" title="入厂时间" style="width:98%;" /></td>
								<%--<td style="padding-left:2px;"><input class="span10 date-picker" name="IN_TIME" id="IN_TIME"  value="${pd.IN_TIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="入厂时间" title="入厂时间"/></td> --%>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">出厂时间:</td>
								<td><input type="text" name="OUT_TIME" id="OUT_TIME" value="${pd.OUT_TIME}" maxlength="50" placeholder="这里输入出厂时间" title="出厂时间" style="width:98%;" /></td>
								<%-- <td style="padding-left:2px;"><input class="span10 date-picker" name="OUT_TIME" id="OUT_TIME"  value="${pd.OUT_TIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="出厂时间" title="出厂时间"/></td>--%>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注:</td>
								<td><input type="text" name="REMARK" id="REMARK" value="${pd.REMARK}" maxlength="255" placeholder="这里输入备注" title="备注" style="width:98%;" /></td>
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
			/* if($("#CREATE_BY").val()==""){
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
			if($("#CAR_ID").val()==""){
				$("#CAR_ID").tips({
					side:3,
		            msg:'请输入车辆ID',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CAR_ID").focus();
			return false;
			}
			if($("#CAR_NUM1").val()==""){
				$("#CAR_NUM1").tips({
					side:3,
		            msg:'请输入车牌号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CAR_NUM1").focus();
			return false;
			}
			if($("#CAR_NUM2").val()==""){
				$("#CAR_NUM2").tips({
					side:3,
		            msg:'请输入车架号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CAR_NUM2").focus();
			return false;
			}
			if($("#CAR_NUM3").val()==""){
				$("#CAR_NUM3").tips({
					side:3,
		            msg:'请输入发动机号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CAR_NUM3").focus();
			return false;
			}
			if($("#SERVICE_TYPE").val()==""){
				$("#SERVICE_TYPE").tips({
					side:3,
		            msg:'请输入服务类型',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#SERVICE_TYPE").focus();
			return false;
			}
			if($("#CONTACT_ID").val()==""){
				$("#CONTACT_ID").tips({
					side:3,
		            msg:'请输入联系人ID',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CONTACT_ID").focus();
			return false;
			}
			if($("#SERVICE_CONSULTANT").val()==""){
				$("#SERVICE_CONSULTANT").tips({
					side:3,
		            msg:'请输入服务顾问',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#SERVICE_CONSULTANT").focus();
			return false;
			}
			if($("#INSPECTOR").val()==""){
				$("#INSPECTOR").tips({
					side:3,
		            msg:'请输入总检查员',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#INSPECTOR").focus();
			return false;
			}
			if($("#PART_TOTAL").val()==""){
				$("#PART_TOTAL").tips({
					side:3,
		            msg:'请输入配件合计',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PART_TOTAL").focus();
			return false;
			}
			if($("#HOURS_TOTAL").val()==""){
				$("#HOURS_TOTAL").tips({
					side:3,
		            msg:'请输入工时合计',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#HOURS_TOTAL").focus();
			return false;
			}
			if($("#TOTAL").val()==""){
				$("#TOTAL").tips({
					side:3,
		            msg:'请输入合计',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TOTAL").focus();
			return false;
			} 
			if($("#IN_TIME").val()==""){
				$("#IN_TIME").tips({
					side:3,
		            msg:'请输入入厂时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#IN_TIME").focus();
			return false;
			}
			if($("#OUT_TIME").val()==""){
				$("#OUT_TIME").tips({
					side:3,
		            msg:'请输入出厂时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#OUT_TIME").focus();
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
			}
			if($("#YL4").val()==""){
				$("#YL4").tips({
					side:3,
		            msg:'请输入预留',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL4").focus();
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
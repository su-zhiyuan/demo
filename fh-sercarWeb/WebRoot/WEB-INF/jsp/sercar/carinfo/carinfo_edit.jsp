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
					
					<form action="carinfo/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="CARINFO_ID" id="CARINFO_ID" value="${pd.CARINFO_ID}"/>
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
								<td style="width:75px;text-align: right;padding-top: 13px;">车牌号:</td>
								<td><input type="text" name="CAR_NUM1" id="CAR_NUM1" value="${pd.CAR_NUM1}" maxlength="50" placeholder="这里输入车牌号" title="车牌号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">车架号:</td>
								<td><input type="text" name="CAR_NUM2" id="CAR_NUM2" value="${pd.CAR_NUM2}" maxlength="50" placeholder="这里输入车架号" title="车架号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">发动机号:</td>
								<td><input type="text" name="CAR_NUM3" id="CAR_NUM3" value="${pd.CAR_NUM3}" maxlength="50" placeholder="这里输入发动机号" title="发动机号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">汽车品牌:</td>
								<td><input type="text" name="CAR_BRANK" id="CAR_BRANK" value="${pd.CAR_BRANK}" maxlength="50" placeholder="这里输入汽车品牌" title="汽车品牌" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">汽车系列:</td>
								<%-- <td><input type="text" name="CAR_XL" id="CAR_XL" value="${pd.CAR_XL}" maxlength="50" placeholder="这里输入汽车系列" title="汽车系列" style="width:98%;"/></td> --%>
								<td style="vertical-align:top;padding-left:2px;">								
										<select name="CAR_XL" id="CAR_XL" >
											<option value="">--请选择--</option>
				                            <c:forEach items="${list1}" var="var" varStatus="vs">
				                                <option value="${var.NAME}" <c:if test="${var.NAME==pd.CAR_XL}">selected</c:if> > ${var.NAME}</option>
				                            </c:forEach>
	                        			</select>						
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">汽车年款:</td>
								<td><input type="text" name="CAR_YEAR" id="CAR_YEAR" value="${pd.CAR_YEAR}" maxlength="50" placeholder="这里输入汽车年款" title="汽车年款" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">外车颜色:</td>
								<td><input type="text" name="COLOR_OUT" id="COLOR_OUT" value="${pd.COLOR_OUT}" maxlength="10" placeholder="这里输入外车颜色" title="外车颜色" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">内车颜色:</td>
								<td><input type="text" name="COLOR_IN" id="COLOR_IN" value="${pd.COLOR_IN}" maxlength="10" placeholder="这里输入内车颜色" title="内车颜色" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">所属用户，可多个追加:</td>
								<td><input type="text" name="USERNAME" id="USERNAME" value="${pd.USERNAME}" maxlength="255" placeholder="这里输入所属用户，可多个追加" title="所属用户，可多个追加" style="width:98%;"/></td>
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
			if($("#CAR_BRANK").val()==""){
				$("#CAR_BRANK").tips({
					side:3,
		            msg:'请输入汽车品牌',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CAR_BRANK").focus();
			return false;
			}
			if($("#CAR_XL").val()==""){
				$("#CAR_XL").tips({
					side:3,
		            msg:'请输入汽车系列',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CAR_XL").focus();
			return false;
			}
			if($("#CAR_YEAR").val()==""){
				$("#CAR_YEAR").tips({
					side:3,
		            msg:'请输入汽车年款',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#CAR_YEAR").focus();
			return false;
			}
			if($("#COLOR_OUT").val()==""){
				$("#COLOR_OUT").tips({
					side:3,
		            msg:'请输入外车颜色',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#COLOR_OUT").focus();
			return false;
			}
			if($("#COLOR_IN").val()==""){
				$("#COLOR_IN").tips({
					side:3,
		            msg:'请输入内车颜色',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#COLOR_IN").focus();
			return false;
			}
			if($("#USERNAME").val()==""){
				$("#USERNAME").tips({
					side:3,
		            msg:'请输入所属用户，可多个追加',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#USERNAME").focus();
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
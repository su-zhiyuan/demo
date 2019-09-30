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
					
					<form action="repoinfo/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="REPOINFO_ID" id="REPOINFO_ID" value="${pd.REPOINFO_ID}"/>
						<div id="zhongxin" style="padding-top: 13px;">
						<!-- 入库信息  -->
						<c:if test="${pd.ifInfo == 'repoIn'}">
							<table id="table_report" class="table table-striped table-bordered table-hover">
								<c:if test="${msg == 'edit'}">
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">创建人:</td>
										<td><input type="text" name="YL1" id="YL1" value="${pd.YL1}" maxlength="50" placeholder="这里输入创建人" title="创建人" style="width:98%;" disabled = "disabled" readonly="readonly"/></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">创建时间:</td>
										<td><input type="text" name="YL2" id="YL2" value="${pd.YL2}" maxlength="50" placeholder="这里输入创建时间" title="创建时间" style="width:98%;" disabled = "disabled" readonly="readonly"/></td>
									</tr>
								</c:if>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">仓库类别:</td>
									<td><input type="text" name="TYPE" id="TYPE" value="${pd.TYPE}" maxlength="255" title="类型" disabled = "disabled" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">编号</td>
									<td><input type="text" name="BIANHAO" id="BIANHAO" value="${pd.BIANHAO}" maxlength="255" placeholder="这里输入编号" title="编号" disabled = "disabled" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">名称:</td>
									<td><input type="text" name="NAME" id="NAME" value="${pd.NAME}" maxlength="255" placeholder="这里输入名称" title="名称" disabled = "disabled" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">规格型号:</td>
									<td><input type="text" name="XINGHAO" id="XINGHAO" value="${pd.XINGHAO}" maxlength="255" placeholder="这里输入规格型号" title="规格型号" disabled = "disabled" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">单位:</td>
									<td><input type="text" name="DANWEI" id="DANWEI" value="${pd.DANWEI}" maxlength="255" placeholder="这里输入单位" title="单位" disabled = "disabled" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">数量:</td>
									<td><input type="text" name="NUMBER" id="NUMBER" value="${0}" maxlength="50" placeholder="这里输入数量" title="数量" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">单价:</td>
									<td><input type="text" name="PRICE" id="PRICE" value="${pd.PRICE}" maxlength="255" placeholder="这里输入单价" title="单价" disabled = "disabled" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">存放地址:</td>
									<td><input type="text" name="ADDRESS" id="ADDRESS" value="${pd.ADDRESS}" maxlength="255" placeholder="这里输入存放地址" title="存放地址" disabled = "disabled" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">公司ID:</td>
									<td><input type="text" name="YL10" id="" value="${pd.YL10}" maxlength="255" placeholder="这里输入公司ID" title="公司ID" style="width:98%;" readonly="readonly"/></td>
								</tr> 
								<tr>
									<td style="text-align: center;" colspan="10">
										<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
										<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
									</td>
								</tr>
							</table>
						</c:if>
							
						<!-- 出库信息  -->
						<c:if test="${pd.ifInfo == 'repoInChu'}">
							<table id="table_report" class="table table-striped table-bordered table-hover">
								<c:if test="${msg == 'edit'}">
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">创建人:</td>
										<td><input type="text" name="YL1" id="YL1" value="${pd.YL1}" maxlength="50" placeholder="这里输入创建人" title="创建人" style="width:98%;" disabled = "disabled" readonly="readonly"/></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">创建时间:</td>
										<td><input type="text" name="YL2" id="YL2" value="${pd.YL2}" maxlength="50" placeholder="这里输入创建时间" title="创建时间" style="width:98%;" disabled = "disabled" readonly="readonly"/></td>
									</tr>
								</c:if>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">仓库类别:</td>
									<td><input type="text" name="TYPE" id="TYPE" value="${pd.TYPE}" maxlength="255" title="类型" disabled = "disabled" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">编号</td>
									<td><input type="text" name="BIANHAO" id="BIANHAO" value="${pd.BIANHAO}" maxlength="255" placeholder="这里输入编号" title="编号" disabled = "disabled" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">名称:</td>
									<td><input type="text" name="NAME" id="NAME" value="${pd.NAME}" maxlength="255" placeholder="这里输入名称" title="名称" disabled = "disabled" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">规格型号:</td>
									<td><input type="text" name="XINGHAO" id="XINGHAO" value="${pd.XINGHAO}" maxlength="255" placeholder="这里输入规格型号" title="规格型号" disabled = "disabled" style="width:98%;"/></td>
								</tr>
								<%-- 
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">单位:</td>
									<td><input type="text" name="DANWEI" id="DANWEI" value="${pd.DANWEI}" maxlength="255" placeholder="这里输入单位" title="单位" disabled = "disabled" style="width:98%;"/></td>
								</tr> 
								--%>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">单价:</td>
									<td><input type="text" name="PRICE" id="PRICE" value="${pd.PRICE}" maxlength="255" placeholder="这里输入单价" title="单价" disabled = "disabled" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">存放地址:</td>
									<td><input type="text" name="ADDRESS" id="ADDRESS" value="${pd.ADDRESS}" maxlength="255" placeholder="这里输入存放地址" title="存放地址" disabled = "disabled" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">领料人:</td>
									<td><input type="text" name="YL7" id="YL7" maxlength="50" placeholder="这里输入姓名" title="姓名" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">数量:</td>
									<td><input type="text" name="NUMBER" id="NUMBER" value="${0}" maxlength="50" placeholder="这里输入数量" title="数量" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">类别:</td>
									<td>								
										<select name="YL9" id="YL9" >
											<option value="1">日常出库</option>
				                            <option value="2">订单出库</option>
	                        			</select>						
									</td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">出库时间:</td>
									<td>
										<input class="span10 date-picker" name="YL5" id="YL5" type="text" data-date-format="yyyy-mm-dd" style="width:88px;" placeholder="选择时间" title="出库时间"/>
									</td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">车牌号:</td>
									<td><input type="text" name="YL8" id="YL8" maxlength="50" placeholder="这里输入车牌" title="车牌" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">用途:</td>
									<td>
										<textarea name="YL6" id="YL6" rows="3" style="width:98%; resize:none" maxlength="255" ></textarea>
									</td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">公司ID:</td>
									<td><input type="text" name="YL10" id="" value="${pd.YL10}" maxlength="255" placeholder="这里输入公司ID" title="公司ID" style="width:98%;" readonly="readonly"/></td>
								</tr> 
								<tr>
									<td style="text-align: center;" colspan="10">
										<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
										<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
									</td>
								</tr>
							</table>
						</c:if>	
							
						<!-- 修改仓库信息  -->
						<c:if test="${pd.ifInfo == 'edite'}">
							<table id="table_report" class="table table-striped table-bordered table-hover">
								<c:if test="${msg == 'edit'}">
								<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">创建人:</td>
										<td><input type="text" name="YL1" id="YL1" value="${pd.YL1}" maxlength="255" placeholder="这里输入创建人" title="创建人" style="width:98%;" readonly="readonly"/></td>
									</tr>
									<tr>
										<td style="width:75px;text-align: right;padding-top: 13px;">创建时间:</td>
										<td><input type="text" name="YL2" id="YL2" value="${pd.YL2}" maxlength="255" placeholder="这里输入创建时间" title="创建时间" style="width:98%;" readonly="readonly"/></td>
									</tr>
								</c:if>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">类别:</td>
									<%-- <td><input type="text" name="TYPE" id="TYPE" value="${pd.TYPE}" maxlength="255" placeholder="这里输入类别" title="类别" style="width:98%;"/></td> --%>
									<td>								
										<select name="TYPE" id="TYPE" >
											<option value="">--请选择--</option>
				                            <c:forEach items="${list1}" var="var" varStatus="vs">
				                                <option value="${var.NAME}" <c:if test="${var.NAME==pd.TYPE}">selected</c:if> > ${var.NAME}</option>
				                            </c:forEach>
	                        			</select>						
									</td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">编号:</td>
									<td><input type="text" name="BIANHAO" id="BIANHAO" value="${pd.BIANHAO}" maxlength="255" placeholder="这里输入编号" title="编号" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">名称:</td>
									<td><input type="text" name="NAME" id="NAME" value="${pd.NAME}" maxlength="255" placeholder="这里输入名称" title="名称" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">规格型号:</td>
									<td><input type="text" name="XINGHAO" id="XINGHAO" value="${pd.XINGHAO}" maxlength="255" placeholder="这里输入规格型号" title="规格型号" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">单位:</td>
									<td><input type="text" name="DANWEI" id="DANWEI" value="${pd.DANWEI}" maxlength="255" placeholder="这里输入单位" title="单位" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">数量:</td>
									<td><input type="text" name="NUMBER" id="NUMBER" value="${pd.NUMBER}" maxlength="255" placeholder="这里输入数量" title="数量" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">单价:</td>
									<td><input type="text" name="PRICE" id="PRICE" value="${pd.PRICE}" maxlength="255" placeholder="这里输入单价" title="单价" style="width:98%;"/></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">存放地址:</td>
									<td><input type="text" name="ADDRESS" id="ADDRESS" value="${pd.ADDRESS}" maxlength="255" placeholder="这里输入存放地址" title="存放地址" style="width:98%;"/></td>
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
						</c:if>
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
			if($("#TYPE").val()==""){
				$("#TYPE").tips({
					side:3,
		            msg:'请输入类别',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#TYPE").focus();
			return false;
			}
			if($("#BIANHAO").val()==""){
				$("#BIANHAO").tips({
					side:3,
		            msg:'请输入编号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#BIANHAO").focus();
			return false;
			}
			if($("#NAME").val()==""){
				$("#NAME").tips({
					side:3,
		            msg:'请输入名称',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#NAME").focus();
			return false;
			}
			if($("#XINGHAO").val()==""){
				$("#XINGHAO").tips({
					side:3,
		            msg:'请输入规格型号',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#XINGHAO").focus();
			return false;
			}
			if($("#DANWEI").val()==""){
				$("#DANWEI").tips({
					side:3,
		            msg:'请输入单位',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#DANWEI").focus();
			return false;
			}
			if($("#NUMBER").val()==""){
				$("#NUMBER").tips({
					side:3,
		            msg:'请输入数量',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#NUMBER").focus();
			return false;
			}
			if($("#PRICE").val()==""){
				$("#PRICE").tips({
					side:3,
		            msg:'请输入单价',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PRICE").focus();
			return false;
			}
			if($("#ADDRESS").val()==""){
				$("#ADDRESS").tips({
					side:3,
		            msg:'请输入存放地址',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ADDRESS").focus();
			return false;
			}
			if($("#YL1").val()==""){
				$("#YL1").tips({
					side:3,
		            msg:'请输入创建人',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL1").focus();
			return false;
			}
			if($("#YL2").val()==""){
				$("#YL2").tips({
					side:3,
		            msg:'请输入创建时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL2").focus();
			return false;
			}
			if($("#YL3").val()==""){
				$("#YL3").tips({
					side:3,
		            msg:'请输入序号',
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
			}
			if($("#YL5").val()==""){
				$("#YL5").tips({
					side:3,
		            msg:'请输入预留',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL5").focus();
			return false;
			}
			if($("#YL6").val()==""){
				$("#YL6").tips({
					side:3,
		            msg:'请输入预留',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL6").focus();
			return false;
			}
			if($("#YL7").val()==""){
				$("#YL7").tips({
					side:3,
		            msg:'请输入预留',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL7").focus();
			return false;
			}
			/* if($("#YL8").val()==""){
				$("#YL8").tips({
					side:3,
		            msg:'请输入预留',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL8").focus();
			return false; 
			}*/
			if($("#YL9").val()==""){
				$("#YL9").tips({
					side:3,
		            msg:'请输入预留',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL9").focus();
			return false;
			}
			if($("#YL10").val()==""){
				$("#YL10").tips({
					side:3,
		            msg:'请输入预留',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL10").focus();
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
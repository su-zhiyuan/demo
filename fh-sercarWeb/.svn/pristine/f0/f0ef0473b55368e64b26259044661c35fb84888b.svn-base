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
					
					<form action="caigourc/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="CAIGOURC_ID" id="CAIGOURC_ID" value="${pd.CAIGOURC_ID}"/>
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
								<td style="width:75px;text-align: right;padding-top: 13px;">状态:</td>
								<%-- <td><input type="text" name="STATUS" id="STATUS" value="${pd.STATUS}" maxlength="255" placeholder="这里输入状态" title="状态" style="width:98%;"/></td> --%>
								<td style="vertical-align:top;padding-left:2px;">								
										<select name="STATUS" id="STATUS" >
											<option value="">--请选择--</option>
				                            <c:forEach items="${list2}" var="var" varStatus="vs">
				                                <option value="${var.NAME}" <c:if test="${var.NAME==pd.STATUS}">selected</c:if> > ${var.NAME}</option>
				                            </c:forEach>
	                        			</select>						
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">商品id:</td>
								<td><input type="text" name="ZONGJI" id="ZONGJI" value="${pd.ZONGJI}" maxlength="255" placeholder="这里输入商品id" title="商品id" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注:</td>
								<td><input type="text" name="BEIZHU" id="BEIZHU" value="${pd.BEIZHU}" maxlength="255" placeholder="这里输入备注" title="备注" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">审核人:</td>
								<td><input type="text" name="SHENHE" id="SHENHE" value="${pd.SHENHE}" maxlength="255" placeholder="这里输入审核人" title="审核人" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">审核时间:</td>
								<td><input type="text" name="SH_TIME" id="SH_TIME" value="${pd.SH_TIME}" maxlength="255" placeholder="这里输入审核时间" title="审核时间" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">完成时间:</td>
								<td><input type="text" name="FINISH_TIME" id="FINISH_TIME" value="${pd.FINISH_TIME}" maxlength="255" placeholder="这里输入完成时间" title="完成时间" style="width:98%;"/></td>
							</tr>
							<c:if test="${msg == 'edit'}">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">公司Id:</td>
								<td><input type="text" name="YL10" id="YL10" value="${pd.YL10}" maxlength="255" placeholder="这里输入公司Id" title="公司Id" style="width:98%;"/></td>
							</tr>
							</c:if>
							<%-- <tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注9:</td>
								<td><input type="text" name="YL1" id="YL1" value="${pd.YL1}" maxlength="255" placeholder="这里输入备注9" title="备注9" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注10:</td>
								<td><input type="text" name="YL2" id="YL2" value="${pd.YL2}" maxlength="255" placeholder="这里输入备注10" title="备注10" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注11:</td>
								<td><input type="text" name="YL3" id="YL3" value="${pd.YL3}" maxlength="255" placeholder="这里输入备注11" title="备注11" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注12:</td>
								<td><input type="text" name="YL4" id="YL4" value="${pd.YL4}" maxlength="255" placeholder="这里输入备注12" title="备注12" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注13:</td>
								<td><input type="text" name="YL5" id="YL5" value="${pd.YL5}" maxlength="255" placeholder="这里输入备注13" title="备注13" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注14:</td>
								<td><input type="text" name="YL6" id="YL6" value="${pd.YL6}" maxlength="255" placeholder="这里输入备注14" title="备注14" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注15:</td>
								<td><input type="text" name="YL7" id="YL7" value="${pd.YL7}" maxlength="255" placeholder="这里输入备注15" title="备注15" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注16:</td>
								<td><input type="text" name="YL8" id="YL8" value="${pd.YL8}" maxlength="255" placeholder="这里输入备注16" title="备注16" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注17:</td>
								<td><input type="text" name="YL9" id="YL9" value="${pd.YL9}" maxlength="255" placeholder="这里输入备注17" title="备注17" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注19:</td>
								<td><input type="text" name="YL11" id="YL11" value="${pd.YL11}" maxlength="255" placeholder="这里输入备注19" title="备注19" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注20:</td>
								<td><input type="text" name="YL12" id="YL12" value="${pd.YL12}" maxlength="255" placeholder="这里输入备注20" title="备注20" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注21:</td>
								<td><input type="text" name="YL13" id="YL13" value="${pd.YL13}" maxlength="255" placeholder="这里输入备注21" title="备注21" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注22:</td>
								<td><input type="text" name="YL14" id="YL14" value="${pd.YL14}" maxlength="255" placeholder="这里输入备注22" title="备注22" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注23:</td>
								<td><input type="text" name="YL15" id="YL15" value="${pd.YL15}" maxlength="255" placeholder="这里输入备注23" title="备注23" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注24:</td>
								<td><input type="text" name="YL16" id="YL16" value="${pd.YL16}" maxlength="255" placeholder="这里输入备注24" title="备注24" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注25:</td>
								<td><input type="text" name="YL17" id="YL17" value="${pd.YL17}" maxlength="255" placeholder="这里输入备注25" title="备注25" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注26:</td>
								<td><input type="text" name="YL18" id="YL18" value="${pd.YL18}" maxlength="255" placeholder="这里输入备注26" title="备注26" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注27:</td>
								<td><input type="text" name="YL19" id="YL19" value="${pd.YL19}" maxlength="255" placeholder="这里输入备注27" title="备注27" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注28:</td>
								<td><input type="text" name="YL20" id="YL20" value="${pd.YL20}" maxlength="255" placeholder="这里输入备注28" title="备注28" style="width:98%;"/></td>
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
			/* if($("#STATUS").val()==""){
				$("#STATUS").tips({
					side:3,
		            msg:'请输入状态',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#STATUS").focus();
			return false;
			}
			if($("#ZONGJI").val()==""){
				$("#ZONGJI").tips({
					side:3,
		            msg:'请输入商品id',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#ZONGJI").focus();
			return false;
			}
			if($("#BEIZHU").val()==""){
				$("#BEIZHU").tips({
					side:3,
		            msg:'请输入备注',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#BEIZHU").focus();
			return false;
			}
			if($("#SHENHE").val()==""){
				$("#SHENHE").tips({
					side:3,
		            msg:'请输入审核人',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#SHENHE").focus();
			return false;
			}
			if($("#SH_TIME").val()==""){
				$("#SH_TIME").tips({
					side:3,
		            msg:'请输入审核时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#SH_TIME").focus();
			return false;
			}
			if($("#YL1").val()==""){
				$("#YL1").tips({
					side:3,
		            msg:'请输入备注9',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL1").focus();
			return false;
			}
			if($("#YL2").val()==""){
				$("#YL2").tips({
					side:3,
		            msg:'请输入备注10',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL2").focus();
			return false;
			}
			if($("#YL3").val()==""){
				$("#YL3").tips({
					side:3,
		            msg:'请输入备注11',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL3").focus();
			return false;
			}
			if($("#YL4").val()==""){
				$("#YL4").tips({
					side:3,
		            msg:'请输入备注12',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL4").focus();
			return false;
			}
			if($("#YL5").val()==""){
				$("#YL5").tips({
					side:3,
		            msg:'请输入备注13',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL5").focus();
			return false;
			}
			if($("#YL6").val()==""){
				$("#YL6").tips({
					side:3,
		            msg:'请输入备注14',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL6").focus();
			return false;
			}
			if($("#YL7").val()==""){
				$("#YL7").tips({
					side:3,
		            msg:'请输入备注15',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL7").focus();
			return false;
			}
			if($("#YL8").val()==""){
				$("#YL8").tips({
					side:3,
		            msg:'请输入备注16',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL8").focus();
			return false;
			}
			if($("#YL9").val()==""){
				$("#YL9").tips({
					side:3,
		            msg:'请输入备注17',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL9").focus();
			return false;
			}
			if($("#YL10").val()==""){
				$("#YL10").tips({
					side:3,
		            msg:'请输入公司Id',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL10").focus();
			return false;
			}
			if($("#YL11").val()==""){
				$("#YL11").tips({
					side:3,
		            msg:'请输入备注19',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL11").focus();
			return false;
			}
			if($("#YL12").val()==""){
				$("#YL12").tips({
					side:3,
		            msg:'请输入备注20',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL12").focus();
			return false;
			}
			if($("#YL13").val()==""){
				$("#YL13").tips({
					side:3,
		            msg:'请输入备注21',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL13").focus();
			return false;
			}
			if($("#YL14").val()==""){
				$("#YL14").tips({
					side:3,
		            msg:'请输入备注22',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL14").focus();
			return false;
			}
			if($("#YL15").val()==""){
				$("#YL15").tips({
					side:3,
		            msg:'请输入备注23',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL15").focus();
			return false;
			}
			if($("#YL16").val()==""){
				$("#YL16").tips({
					side:3,
		            msg:'请输入备注24',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL16").focus();
			return false;
			}
			if($("#YL17").val()==""){
				$("#YL17").tips({
					side:3,
		            msg:'请输入备注25',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL17").focus();
			return false;
			}
			if($("#YL18").val()==""){
				$("#YL18").tips({
					side:3,
		            msg:'请输入备注26',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL18").focus();
			return false;
			}
			if($("#YL19").val()==""){
				$("#YL19").tips({
					side:3,
		            msg:'请输入备注27',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL19").focus();
			return false;
			}
			if($("#YL20").val()==""){
				$("#YL20").tips({
					side:3,
		            msg:'请输入备注28',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL20").focus();
			return false;
			}
			if($("#FINISH_TIME").val()==""){
				$("#FINISH_TIME").tips({
					side:3,
		            msg:'请输入完成时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#FINISH_TIME").focus();
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
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
					
					<form action="partoffer/${msg }.do" name="Form" id="Form" method="post">
						<div id="zhongxin" style="padding-top: 13px;">
							<table id="table_report" class="table table-striped table-bordered table-hover">
								<c:if test="${not empty varList }">
									<c:forEach items="${varList }" var="goods">
										<tr>
											<tr>
												<td style="width:75px;text-align: right;padding-top: 13px;">商品名称:</td>
												<td><input type="text" name="NAME" id="NAME" value="${goods.NAME}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
											</tr>
											<tr>
												<td style="width:75px;text-align: right;padding-top: 13px;">商品数量:</td>
												<td><input type="text" name="NUMBER" id="NUMBER" value="${goods.NUMBER}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
											</tr>
											<tr>
												<td style="width:75px;text-align: right;padding-top: 13px;">商品价格:</td>
												<td><input type="text" name="PRICE" id="PRICE" value="${goods.PRICE}" maxlength="50" style="width:98%;" readonly="readonly"/></td>
											</tr>
											<tr>
												<td colspan="2"></td>
											</tr>
										</tr>
									</c:forEach>
								</c:if>
							</table>
						</div>
						<table>
							<tr>
								<td style="text-align: center;" colspan="10">
									<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
								</td>
							</tr>
						</table>
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
		var jname = 1;
		//增加表单
		function addForm(){
		jname++;
		var trHTML = 
							"<tr>"
							+"<td></td>"
							+"<td><input type='text' maxlength='50' style='width:98%; visibility: hidden;'/></td>"
							+"</tr>"+
							+"<tr>"
								+"<td>配件名称:</td>"
								+"<td><input type='text' name='PART_NAME"+jname+"' id='PART_NAME"+jname+"' value='' maxlength='50' placeholder='这里输入配件名称' title='配件名称' style='width:98%;'/></td>"
							+"</tr>"
							+"<tr>"
								+"<td>配件数量:</td>"
								+"<td><input type='text' name='PART_COUNT"+jname+"' id='PART_COUNT"+jname+"' value='' maxlength='50' placeholder='这里输入配件数量' title='配件数量' style='width:98%;'/></td>"
							+"</tr>"
							+"<tr>"
								+"<td>配件编号:</td>"
								+"<td><input type='text' name='PART_NUM"+jname+"' id='PART_NUM"+jname+"' value='' maxlength='50' placeholder='这里输入配件编号' title='配件编号' style='width:98%;'/></td>"
							+"</tr>"
							+"<tr>"
								+"<td>配件单价:</td>"
								+"<td><input type='text' name='PRICE"+jname+"' id='PRICE"+jname+"' value='' maxlength='50' placeholder='这里输入配件单价' title='配件单价' style='width:98%;'/></td>"
							+"</tr>"
							;
		$("#table_report").append(trHTML);
		}
		
		//保存
		function save(){
			$("input[name='jname']").val(jname);
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
			
		}
		//删除表单
		function delForm(){
			jname--;
			if(5<$("tbody>tr").length){
				$("tbody>tr").eq(-2).remove();
				$("tbody>tr").eq(-2).remove();
				$("tbody>tr").eq(-2).remove();
				$("tbody>tr").eq(-2).remove();
				$("tbody>tr").eq(-2).remove();
			}else{
				alert("没有可以删除的表单~");
			}
		}
		
		
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		});
		</script>
</body>
</html>
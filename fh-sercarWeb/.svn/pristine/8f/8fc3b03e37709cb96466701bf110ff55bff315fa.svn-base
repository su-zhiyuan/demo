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
					
					<form action="purchase/${msg }.do" name="Form" id="Form" method="post">
						<input type="hidden" name="PURCHASE_ID" id="PURCHASE_ID" value="${pd.PURCHASE_ID}"/>
						<input type="hidden" name="lineIn" id="lineIn" value=""/>
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:100px;text-align: left;padding-top: 13px;">订单ID:</td>
								<td><input type="text" name="ORDER_ID" id="ORDER_ID" value="${pd.ORDER_ID}" maxlength="50" placeholder="这里输入订单ID" title="订单ID" style="width:98%;" readonly="readonly"/></td>
							</tr>
							<tr>
								<td style="width:100px;text-align: left;padding-top: 13px;">预期交付日期:</td>
								<td><input type="text" name="DELIVERY_TIME" id="DELIVERY_TIME" value="${pd.DELIVERY_TIME}" maxlength="50" placeholder="这里输入预期交付日期" title="预期交付日期" style="width:98%;" readonly="readonly"/></td>
							</tr>
							</table>
							<hr />
						<table id="" class="table table-striped table-bordered table-hover">
							<c:forEach items="${varList}" var="var" varStatus="vs">
							<tr>
								<td style="width:15px;text-align: right;padding-top: 13px;"><label class="pos-rel"><input type="checkbox" class="ace" name="ids" /><span class="lbl"></span></label></td>
								<td style="width:90px;text-align: right;padding-top: 13px;">配件明细ID:</td>
								<td><input type="text" name="POMX_ID${vs.index}" id="POMX_ID" value="${var.POMX_ID}" maxlength="50" placeholder="这里输入配件明细ID" title="配件明细ID" style="width:98%;" readonly="readonly"/></td>
								<td style="width:75px;text-align: right;padding-top: 13px;">采购数量</td>
								<td><input type="text" name="PUR_COUNT${vs.index}" id="PUR_COUNT" value="0" maxlength="50" placeholder="这里输入采购数量" title="采购数量" style="width:98%;"/></td>
							</tr>
						
							</c:forEach>
						</table>
						<table>
							<td style="text-align: center;" colspan="10">
									<a class="btn btn-mini btn-primary" onclick="save('确定选中的数据吗?');">保存</a>
									<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
							</td>
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
		function save(msg){
			var checkStr = '';
			var lineIn = 0;
			for(var i=0; i<document.getElementsByName('ids').length; i++){
				if(document.getElementsByName('ids')[i].checked){
					checkStr +=document.getElementsByName('ids')[i].value ;
				}
				lineIn = i;
			}
			if(''==checkStr){
				alert("你没有选择任何内容！");
				return;
			}else{
				
				if(confirm(msg) == true){
					var checked = document.getElementsByName('ids');
					for(var i=0; i<checked.length; i++){
						var str1 = "POMX_ID" +i;
						var str2 = "PUR_COUNT" +i;
						if(checked[i].checked == false){
							$("input[name="+str1+"]").val('');
							$("input[name="+str2+"]").val('0');
						}
						if(checked[i].checked == true){
							if('' == $("input[name="+str2+"]").val()){
								alert("采购数量未填值");
								return;
							}
							if(isNaN(parseInt($("input[name="+str2+"]").val()))){
								alert("采购数量请填数字");
								return;
							}
							
						}
					}
					
						$("#lineIn").val(lineIn);
						$("#Form").submit();
						$("#zhongxin").hide();
						$("#zhongxin2").show();
				}else{
					return;
				}
			}
		
		}
			
		
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		});
		</script>
</body>
</html>
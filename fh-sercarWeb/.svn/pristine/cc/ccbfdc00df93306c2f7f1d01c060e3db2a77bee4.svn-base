<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
							
						<!-- 检索  -->
						<form action="pomx/list.do" method="post" name="Form" id="Form">
						<div id="zhongxin" style="padding-top: 13px;">
							<table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">	
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">订单ID:</td>
									<td><input type="text" name="ORDER_ID" id="ORDER_ID" value="${order.ORDER_ID}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">车牌:</td>
									<td><input type="text" name="CAR_NUM1" id="CAR_NUM1" value="${order.CAR_NUM1}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">联系人:</td>
									<td><input type="text" name="YL18" id="YL18" value="${order.YL18}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">联系电话:</td>
									<td><input type="text" name="YL17" id="YL17" value="${order.YL17}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
								</tr>
								<c:if test="${not empty list}">
									<c:forEach items="${list }" var="var" varStatus="vs">
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">配件ID:</td>
											<td><input type="text" name="POMX_ID" id="POMX_ID" value="${var.POMX_ID}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">配件名称:</td>
											<td><input type="text" name="PART_NAME" id="PART_NAME" value="${var.PART_NAME}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">配件数量:</td>
											<td><input type="text" name="PART_COUNT" id="PART_COUNT" value="${var.PART_COUNT}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">配件编号:</td>
											<td><input type="text" name="PART_NUM" id="PART_NUM" value="${var.PART_NUM}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">单价:</td>
											<td><input type="text" name="YL8" id="YL8" value="${var.YL8}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">小计:</td>
											<td><input type="text" name="TOTAL" id="TOTAL" value="${var.TOTAL}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">订金:</td>
											<td><input type="text" name="YL16" id="YL16" value="${var.YL16}" readonly="readonly" maxlength="50"  style="width:98%;" /></td>
										</tr>
										<tr>
											<td style="width:75px;text-align: right;padding-top: 13px;">备注:</td>
											<td><textarea type="text" name="REMARK" id="REMARK" value="${var.REMARK}" readonly="readonly" maxlength="50"  style="width:98%;"></textarea></td>
										</tr>
									</c:forEach>
									<c:if test="${part.YL15 != '已交付定金' }">
										<tr>
											<td class="center" colspan="2">
												<a class="btn btn-mini btn-success" style="margin-right:3px; height:30px;line-height:30px; margin-bottom:5px;" onclick="getDingjin('${part.PARTOFFER_ID}');">收取定金</a>
											</td>
										</tr>
									</c:if>
									
								</c:if>
							</table>
						</div>
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

		<!-- 返回顶部 -->
		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>

	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->
	<!-- 页面底部js¨ -->
	<%@ include file="../../system/index/foot.jsp"%>
	<!-- 删除时确认窗口 -->
	<script src="static/ace/js/bootbox.js"></script>
	<!-- ace scripts -->
	<script src="static/ace/js/ace/ace.js"></script>
	<!-- 下拉框 -->
	<script src="static/ace/js/chosen.jquery.js"></script>
	<!-- 日期框 -->
	<script src="static/ace/js/date-time/bootstrap-datepicker.js"></script>
	<!--提示框-->
	<script type="text/javascript" src="static/js/jquery.tips.js"></script>
	<script type="text/javascript">
		$(top.hangge());//关闭加载状态
		//检索
		function tosearch(){
			top.jzts();
			$("#Form").submit();
		}
		$(function() {
		
			//日期框
			$('.date-picker').datepicker({
				autoclose: true,
				todayHighlight: true
			});
			
			//下拉框
			if(!ace.vars['touch']) {
				$('.chosen-select').chosen({allow_single_deselect:true}); 
				$(window)
				.off('resize.chosen')
				.on('resize.chosen', function() {
					$('.chosen-select').each(function() {
						 var $this = $(this);
						 $this.next().css({'width': $this.parent().width()});
					});
				}).trigger('resize.chosen');
				$(document).on('settings.ace.chosen', function(e, event_name, event_val) {
					if(event_name != 'sidebar_collapsed') return;
					$('.chosen-select').each(function() {
						 var $this = $(this);
						 $this.next().css({'width': $this.parent().width()});
					});
				});
				$('#chosen-multiple-style .btn').on('click', function(e){
					var target = $(this).find('input[type=radio]');
					var which = parseInt(target.val());
					if(which == 2) $('#form-field-select-4').addClass('tag-input-style');
					 else $('#form-field-select-4').removeClass('tag-input-style');
				});
			}
			
			
			//复选框全选控制
			var active_class = 'active';
			$('#simple-table > thead > tr > th input[type=checkbox]').eq(0).on('click', function(){
				var th_checked = this.checked;//checkbox inside "TH" table header
				$(this).closest('table').find('tbody > tr').each(function(){
					var row = this;
					if(th_checked) $(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
					else $(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
				});
			});
		});
		
		function getDingjin(Id){
			$.ajax({
				url: "<%=basePath%>dingjin/getDingjin.do",
				data:{
					"PARTOFFER_ID" : Id
				},
				type: "post",
				async: false,
				success:function(data) {
					document.getElementById('zhongxin').style.display = 'none';
					top.Dialog.close();
				},
				error: function(e) {
					alert(JSON.stringify(e));
					top.Dialog.close();
				}
			}) 
		}
		
		//导出excel
		function toExcel(){
			window.location.href='<%=basePath%>pomx/excel.do';
		}
	</script>


</body>
</html>
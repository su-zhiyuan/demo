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
						<form action="cardclock/yuetongji.do" method="post" name="Form" id="Form">
						<table style="margin-top:5px;">
							<tr>
								<td style="vertical-align:top;padding-left:2px;">
								 	<select class="chosen-select form-control" name="YL6" id="YL6" data-placeholder="请选择" style="vertical-align:top;width: 120px;">
									<option value="">月份</option>
										<option value="01" <c:if test="${'01'==pd.YL6}">selected</c:if> >1月</option>
										<option value="02" <c:if test="${'02'==pd.YL6}">selected</c:if> >2月</option>
										<option value="03" <c:if test="${'03'==pd.YL6}">selected</c:if> >3月</option>
										<option value="04" <c:if test="${'04'==pd.YL6}">selected</c:if> >4月</option>
										<option value="05" <c:if test="${'05'==pd.YL6}">selected</c:if> >5月</option>
										<option value="06" <c:if test="${'06'==pd.YL6}">selected</c:if> >6月</option>
										<option value="07" <c:if test="${'07'==pd.YL6}">selected</c:if> >7月</option>
										<option value="08" <c:if test="${'08'==pd.YL6}">selected</c:if> >8月</option>
										<option value="09" <c:if test="${'09'==pd.YL6}">selected</c:if> >9月</option>
										<option value="10" <c:if test="${'10'==pd.YL6}">selected</c:if> >10月</option>
										<option value="11" <c:if test="${'11'==pd.YL6}">selected</c:if> >11月</option>
										<option value="12" <c:if test="${'12'==pd.YL6}">selected</c:if> >12月</option>
								  	</select>
								</td>
								<td style="vertical-align:top;padding-left:2px"><a class="btn btn-light btn-xs" onclick="tosearch();"  title="检索"><i id="nav-search-icon" class="ace-icon fa fa-search bigger-110 nav-search-icon blue"></i></a></td>
								</tr>
						</table> 
					
						<table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">	
							<thead>
								<tr>
									<th class="center" style="width:50px;">序号</th>
									<th class="center">姓名</th>
									<th class="center">出勤天数</th>
									<th class="center">迟到次数</th>
									<th class="center">早退次数</th>
									<th class="center">异常次数</th>
									<!-- <th class="center">月份</th> -->
								</tr>
							</thead>
													
							<tbody>
								<c:forEach items="${dktongjilist}" var="var" varStatus="vs">
									<tr>
										<td class='center' style="width: 30px;">${vs.index+1}</td>
										<td class="center"><a href="cardclock/detaillist.do?Name=${var.name}&YL6=${pd.YL6}">${var.name}</a></td>
										<td class="center">${var.chuqin}</td>
										<td class="center">${var.chidao}</td>
										<td class="center">${var.zaotui}</td>
										<td class="center">${var.yichang}</td>
										<%-- <td class="center">${var.yue}</td> --%>										
									</tr>
								</c:forEach>
							</tbody>
						</table>
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
		});
	</script>


</body>
</html>
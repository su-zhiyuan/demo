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
						<form action="dispatching/list.do" method="post" name="Form" id="Form">
						<table style="margin-top:5px;">
							<tr>
								<td>
									<div class="nav-search">
										<span class="input-icon">
											<input type="text" placeholder="这里输入关键词" class="nav-search-input" id="nav-search-input" autocomplete="off" name="keywords" value="${pd.keywords }" placeholder="这里输入关键词"/>
											<i class="ace-icon fa fa-search nav-search-icon"></i>
										</span>
									</div>
								</td>
								<td style="padding-left:2px;"><input class="span10 date-picker" name="lastStart" id="lastStart"  value="${pd.lastStart }" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="创建时间始" title="创建时间始"/></td>
								<td style="padding-left:2px;"><input class="span10 date-picker" name="lastEnd" name="lastEnd"  value="${pd.lastEnd }" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="创建时间止" title="创建时间止"/></td>
								
								<td style="vertical-align:top;padding-left:2px;">
								 	<select class="chosen-select form-control" name="name" id="id"  data-placeholder="请选择状态" style="vertical-align:top;width: 120px;">									
										<option value=""> --状态--</option>
										<c:forEach items="${list1}" var="var" varStatus="vs">
			                                <option value="${var.NAME}" <c:if test="${var.NAME==pd.name}">selected</c:if> > ${var.NAME}</option>
			                            </c:forEach>
								  	</select>
								</td>
								
								<c:if test="${QX.cha == 1 }">
								<td style="vertical-align:top;padding-left:2px"><a class="btn btn-light btn-xs" onclick="tosearch();"  title="检索"><i id="nav-search-icon" class="ace-icon fa fa-search bigger-110 nav-search-icon blue"></i></a></td>
								</c:if>
								<c:if test="${QX.toExcel == 1 }"><td style="vertical-align:top;padding-left:2px;"><a class="btn btn-light btn-xs" onclick="toExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="ace-icon fa fa-download bigger-110 nav-search-icon blue"></i></a></td></c:if>
							</tr>
						</table>
						<!-- 检索  -->
					
						<table id="simple-table" class="table table-striped table-bordered table-hover" style="margin-top:5px;">	
							<thead>
								<tr>
									<th class="center" style="width:35px;">
									<label class="pos-rel"><input type="checkbox" class="ace" id="zcheckbox" /><span class="lbl"></span></label>
									</th>
									<th class="center" style="width:50px;">序号</th>
									<th class="center">订单ID</th>
									<th class="center">创建人</th>
									<th class="center">创建时间</th>
									<th class="center">复核人</th>
									<th class="center">复核时间</th>
									<th class="center">维修技师</th>
									<th class="center">完工时间</th>
									<th class="center">预交车日</th>
									<th class="center">总检查员</th>
									<th class="center">总检查备注</th>
									<th class="center">备注</th>
									<th class="center">状态</th>
									<th class="center">操作</th>
								</tr>
							</thead>
													
							<tbody>
							<!-- 开始循环 -->	
							<c:choose>
								<c:when test="${not empty varList}">
									<c:if test="${QX.cha == 1 }">
									<c:forEach items="${varList}" var="var" varStatus="vs">
										<tr>
											<td class='center'>
												<label class="pos-rel"><input type='checkbox' name='ids' value="${var.DISPATCHING_ID}" class="ace" /><span class="lbl"></span></label>
											</td>
											<td class='center' style="width: 30px;">${vs.index+1}</td>
											<td class='center'>${var.ORDER_ID}</td>
											<%-- <td class='center'>${var.CREATE_BY}</td> --%>
											<td class='center'>${var.uName}</td>
											<td class='center'>${var.CREATE_TIME}</td>
											<%-- <td class='center'>${var.CHECKED_BY}</td> --%>
											<td class='center'>${var.cName}</td>
											<td class='center'>${var.CEHCKED_TIME}</td>
											<td class='center'>${var.mName}</td>
											<td class='center'>${var.COMPLETION_TIME}</td>
											<td class='center'>${var.EXPECTED_COMPLETION_DATE}</td>
											<td class='center'>${var.INSPECT}</td>
											<td class='center'>${var.INSPECT_REMARK}</td>
											<td class='center'>${var.REMARK}</td>
											<td class='center'>${var.STATUS}</td>
											<td class="center">
												<div class="hidden-sm hidden-xs btn-group">
													<a class="btn btn-xs btn-success" onclick="getDispatchingDetails('${var.DISPATCHING_ID}');">派工明细</a>
													<c:if test="${flag == 'ld' }">
														<c:if test="${var.STATUS == '待提交' || var.STATUS == '复核未通过'}">
															<a class="btn btn-xs btn-success"  onclick="edit('${var.DISPATCHING_ID}');">
																编辑
															</a>
															<a class="btn btn-xs btn-success" onclick="submit('${var.DISPATCHING_ID}');">
																提交
															</a>
															<a class="btn btn-xs btn-danger" onclick="del('${var.DISPATCHING_ID}');">
																删除
															</a>
															
														</c:if>
														<c:if test="${var.STATUS == '待开工'}">
															<a class="btn btn-xs btn-success" onclick="startwork('${var.DISPATCHING_ID}');">开工</a>
														</c:if>
														<c:if test="${var.STATUS == '待完工'}">
															<a class="btn btn-xs btn-success" onclick="projectwork('${var.DISPATCHING_ID}');">项目完工</a>
															<a class="btn btn-xs btn-success" onclick="projectdetection('${var.DISPATCHING_ID}');">项目检测</a>
															<a class="btn btn-xs btn-success" onclick="paigongWancheng('${var.DISPATCHING_ID}');">派工完工</a>
														</c:if>
														<c:if test="${var.STATUS == '待检测'}">
															<a class="btn btn-xs btn-success" onclick="finishwork('${var.DISPATCHING_ID}');">派工检测</a>
														</c:if>
														<a class="btn btn-xs btn-success" onclick="userlingjian('${var.DISPATCHING_ID}');">零件使用</a>
													</c:if>
													
													<c:if test="${flag == 'js' }">
														<c:if test="${var.STATUS == '待开工'}">
															<a class="btn btn-xs btn-success" onclick="startwork('${var.DISPATCHING_ID}');">开工</a>
														</c:if>
														<c:if test="${var.STATUS == '待完工'}">
															<a class="btn btn-xs btn-success" onclick="projectwork('${var.DISPATCHING_ID}');">项目完工</a>
															<a class="btn btn-xs btn-success" onclick="projectdetection('${var.DISPATCHING_ID}');">项目检测</a>
															<a class="btn btn-xs btn-success" onclick="paigongWancheng('${var.DISPATCHING_ID}');">派工完工</a>
														</c:if>
														<c:if test="${var.STATUS == '待检测'}">
															<a class="btn btn-xs btn-success" onclick="finishwork('${var.DISPATCHING_ID}');">派工检测</a>
														</c:if>
														<a class="btn btn-xs btn-success" onclick="userlingjian('${var.DISPATCHING_ID}');">零件使用</a>
													</c:if>
													<c:if test="${flag == 'fwgw' }">
														<c:if test="${var.STATUS == '待提交' || var.STATUS == '复核未通过'}">
															<a class="btn btn-xs btn-success" title="编辑" onclick="edit('${var.DISPATCHING_ID}');">
																编辑
															</a>
															<a class="btn btn-xs btn-success" onclick="submit('${var.DISPATCHING_ID}');">
																提交
															</a>
															<a class="btn btn-xs btn-danger" onclick="del('${var.DISPATCHING_ID}');">
																删除
															</a>
														</c:if>
													</c:if>
													
												</div>
											</td>
										</tr>
									
									</c:forEach>
									</c:if>
								</c:when>
								<c:otherwise>
									<tr class="main_info">
										<td colspan="100" class="center" >没有相关数据</td>
									</tr>
								</c:otherwise>
							</c:choose>
							</tbody>
						</table>
						<%-- <div class="page-header position-relative">
						<table style="width:100%;">
							<tr>
								<td style="vertical-align:top;">
									<c:if test="${QX.add == 1 }">
									<a class="btn btn-mini btn-success" onclick="add();">新增</a>
									</c:if>
									<c:if test="${QX.del == 1 }">
									<a class="btn btn-mini btn-danger" onclick="makeAll('确定要删除选中的数据吗?');" title="批量删除" ><i class='ace-icon fa fa-trash-o bigger-120'></i></a>
									</c:if>
									<!-- <a class="btn btn-mini btn-success" onclick="goWork();">工作统计</a>
									<a class="btn btn-mini btn-success" onclick="goOutput();">产量统计</a> -->
								</td>
								<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
							</tr>
						</table>
						</div> --%>
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
		
		//新增
		function add(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>dispatching/goAdd.do';
			 diag.Width = 800;
			 diag.Height = 740;
			 diag.Modal = true;				//有无遮罩窗口
			 diag. ShowMaxButton = true;	//最大化按钮
		     diag.ShowMinButton = true;		//最小化按钮
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 top.jzts();
						 setTimeout("self.location=self.location",100);
					 }else{
						 nextPage(${page.currentPage});
					 }
				}
				diag.close();
			 };
			 
			 diag.show();
		}
		
		//工作统计
		function goWork(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="工作统计";
			 diag.URL = '<%=basePath%>dispatching/goWork.do';
			 diag.Width = 800;
			 diag.Height = 740;
			 diag.Modal = true;				//有无遮罩窗口
			 diag. ShowMaxButton = true;	//最大化按钮
		     diag.ShowMinButton = true;		//最小化按钮
			 diag.CancelEvent = function(){ //关闭事件
				diag.close();
			 };
			 diag.show();
		}
		
		//产量统计
		function goOutput(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="工作统计";
			 diag.URL = '<%=basePath%>dispatching/goOutput.do';
			 diag.Width = 800;
			 diag.Height = 740;
			 diag.Modal = true;				//有无遮罩窗口
			 diag. ShowMaxButton = true;	//最大化按钮
		     diag.ShowMinButton = true;		//最小化按钮
			 diag.CancelEvent = function(){ //关闭事件
				diag.close();
			 };
			 diag.show();
		}
		
		//删除
		function del(Id){
			bootbox.confirm("确定要删除吗?", function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>dispatching/delete.do?DISPATCHING_ID="+Id+"&tm="+new Date().getTime();
					$.get(url,function(data){
						javascript:location.reload();
					});
				}
			});
		}
		
		//修改
		function edit(Id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>dispatching/goEdit.do?DISPATCHING_ID='+Id;
			 diag.Width = 450;
			 diag.Height = 355;
			 diag.Modal = true;				//有无遮罩窗口
			 diag. ShowMaxButton = true;	//最大化按钮
		     diag.ShowMinButton = true;		//最小化按钮 
		     diag.CancelEvent = function(){ //关闭事件
		    	window.location.reload();
				diag.close();
			 };
			 diag.show();
		}
		
		//新增派工明细
		function getDispatchingDetails(Id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增派工明细";
			 diag.URL = '<%=basePath%>dispatching/getDispatchingDetails.do?DISPATCHING_ID='+Id;
			 diag.Width = 800;
			 diag.Height = 700;
			 diag.Modal = true;				//有无遮罩窗口
			 diag. ShowMaxButton = true;	//最大化按钮
		     diag.ShowMinButton = true;		//最小化按钮 
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 nextPage(${page.currentPage});
			 	}
				 diag.close();
			 };
			 diag.show();
		}
		
		//开工
		function startwork(Id) {
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="开工页面";
			 diag.URL = '<%=basePath%>dispatching/goStartWork.do?DISPATCHING_ID='+Id;
			 diag.Width = 800;
			 diag.Height = 700;
			 diag.Modal = true;				//有无遮罩窗口
			 diag. ShowMaxButton = true;	//最大化按钮
		     diag.ShowMinButton = true;		//最小化按钮 
			 diag.CancelEvent = function(){ //关闭事件
				window.location.reload();
				diag.close();
			 };
			 diag.show();
		}
		
		//项目完工
		function projectwork(Id) {
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="项目完工页面";
			 diag.URL = '<%=basePath%>dispatching/goProjectWork.do?DISPATCHING_ID='+Id;
			 diag.Width = 800;
			 diag.Height = 700;
			 diag.Modal = true;				//有无遮罩窗口
			 diag. ShowMaxButton = true;	//最大化按钮
		     diag.ShowMinButton = true;		//最小化按钮 
			 diag.CancelEvent = function(){ //关闭事件
				window.location.reload();
				diag.close();
			 };
			 diag.show();
		}
		
		//派工完工
		function paigongWancheng(Id) {
			$.ajax({
				url: "<%=basePath%>dispatching/paigongWancheng.do",
				data:{
					"DISPATCHING_ID" : Id
				},
				type: "post",
				async: false,
				success:function(data) {
					history.go(0);
				},
				error: function(e) {
					alert(JSON.stringify(e));
				}
			}) 
		}
		
		//项目检测
		function projectdetection(Id) {
			top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="项目检测页面";
			 diag.URL = '<%=basePath%>dispatching/goProjectDetection.do?DISPATCHING_ID='+Id;
			 diag.Width = 800;
			 diag.Height = 700;
			 diag.Modal = true;				//有无遮罩窗口
			 diag. ShowMaxButton = true;	//最大化按钮
		     diag.ShowMinButton = true;		//最小化按钮 
			 diag.CancelEvent = function(){ //关闭事件
				window.location.reload();
				diag.close();
			 };
			 diag.show();
		}
		
		//整个派工单的检测
		function finishwork(Id) {
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="项目检测页面";
			 diag.URL = '<%=basePath%>dispatching/goDetectionWork.do?DISPATCHING_ID='+Id;
			 diag.Width = 800;
			 diag.Height = 700;
			 diag.Modal = true;				//有无遮罩窗口
			 diag. ShowMaxButton = true;	//最大化按钮
		     diag.ShowMinButton = true;		//最小化按钮 
			 diag.CancelEvent = function(){ //关闭事件
				window.location.reload();
				diag.close();
			 };
			 diag.show();
		}
		
		//零件使用
		function userlingjian(Id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="开工页面";
			 diag.URL = '<%=basePath%>dispatching/goUserLingJian.do?DISPATCHING_ID='+Id;
			 diag.Width = 800;
			 diag.Height = 700;
			 diag.Modal = true;				//有无遮罩窗口
			 diag. ShowMaxButton = true;	//最大化按钮
		     diag.ShowMinButton = true;		//最小化按钮 
			 diag.CancelEvent = function(){ //关闭事件
				window.location.reload();
				diag.close();
			 };
			 diag.show();
		}
		
		//提交
		function submit(Id) {
			bootbox.confirm("确定要提交吗?", function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>order/subdispatching.do?DISPATCHING_ID="+Id;
					$.get(url,function(data){
						history.go(0);
						nextPage(${page.currentPage});
					});
				}
			});
		}
		
		//导出excel
		function toExcel(){
			window.location.href='<%=basePath%>dispatching/excel.do';
		}
	</script>


</body>
</html>
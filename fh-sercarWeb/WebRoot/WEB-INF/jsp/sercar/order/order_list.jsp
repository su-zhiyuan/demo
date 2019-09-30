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
						<form action="order/list.do" method="post" name="Form" id="Form">
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
								<td style="padding-left:2px;"><input class="span10 date-picker" name="lastEnd" name="lastEnd"  value="${pd.lastEnd }" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" style="width:88px;" placeholder="创建时间止" title="创建时间止" /></td>
								<td style="vertical-align:top;padding-left:2px;">
								 	<select class="chosen-select form-control" name="name" id="id"  data-placeholder="服务类型" style="vertical-align:top;width: 120px;">									
										 <option value="">--服务类型--</option>
										<c:forEach items="${list2}" var="var" varStatus="vs">
			                                <option value="${var.TYPE_NAME}" <c:if test="${var.TYPE_NAME==pd.name}">selected</c:if>> ${var.TYPE_NAME}</option>
			                            </c:forEach>
								  	</select>
								</td>
								<td style="vertical-align:top;padding-left:2px;">
								 	<select class="chosen-select form-control" name="status" id="id"  data-placeholder="订单状态" style="vertical-align:top;width: 120px;">									
										<option value="">--订单状态--</option>
										<c:forEach items="${list1}" var="var" varStatus="vs">
			                                <option value="${var.NAME}" <c:if test="${var.NAME==pd.status}">selected</c:if> > ${var.NAME}</option>
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
									<th class="center">创建人</th>
									<th class="center">创建时间</th>
									<th class="center">状态</th>
									<!-- <th class="center">车辆ID</th> -->
									<th class="center">车牌号</th>
									<th class="center">车架号</th>
									<th class="center">发动机号</th>
									<!-- <th class="center">服务类型</th> -->
									<!-- <th class="center">联系人ID</th> -->
									<th class="center">服务顾问</th>
									<!-- <th class="center">总检查员</th> -->
									<!-- <th class="center">配件合计</th>
									<th class="center">工时合计</th> -->
									<th class="center">合计</th>
									<th class="center" style="width:100px;">入厂时间</th>
									<th class="center" style="width:100px;">出厂时间</th>
									<th class="center">备注</th>
									<!-- <th class="center">公司id</th> -->
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
												<label class="pos-rel"><input type='checkbox' name='ids' value="${var.ORDER_ID}" class="ace" /><span class="lbl"></span></label>
											</td>
											<td class='center' style="width: 30px;">${vs.index+1}</td>
											<%-- <td class='center'>${var.CREATE_BY}</td> --%>
											<td class='center'>${var.uName}</td>
											<td class='center'>${var.CREATE_TIME}</td>
											<td class='center'>${var.STATUS}</td>
											<%-- <td class='center'>${var.CAR_ID}</td> --%>
											<td class='center'>${var.CAR_NUM1}</td>
											<td class='center'>${var.CAR_NUM2}</td>
											<td class='center'>${var.CAR_NUM3}</td>
											<%-- <td class='center'>${var.SERVICE_TYPE}</td> --%>
											<%-- <td class='center'>${var.CONTACT_ID}</td> --%>
											<td class='center'>${var.sName}</td>
											<%-- <td class='center'>${var.SERVICE_CONSULTANT}</td> 
											<td class='center'>${var.INSPECTOR}</td> --%>
											<%-- <td class='center'>${var.iName}</td> --%>
											<%-- <td class='center'>${var.PART_TOTAL}</td>
											<td class='center'>${var.HOURS_TOTAL}</td> --%>
											<c:if test="${not empty var.sett.TOTAL}">
												<td class='center'>${var.sett.TOTAL}</td>
											</c:if>
											<c:if test="${empty var.sett.TOTAL}">
												<td class='center'>0</td>
											</c:if>
											<td class='center' style="width:100px;">${var.IN_TIME}</td>
											<td class='center' style="width:100px;">${var.OUT_TIME}</td>
											<td class='center'>${var.REMARK}</td>
											<%-- <td class='center'>${var.YL10}</td> --%>
											<td class="center">
												<c:if test="${QX.edit != 1 && QX.del != 1 }">
												<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="ace-icon fa fa-lock" title="无权限"></i></span>
												</c:if>
												<div class="hidden-sm hidden-xs btn-group">
													<a class="btn btn-mini btn-success" style="margin-right:2px; height:30px;line-height:30px; margin-bottom:5px;" onclick="getOrder('${var.ORDER_ID}');">订单详情</a>
													<c:if test="${var.STATUS!='接车中'&&var.STATUS!='已创建接车单待提交复核'&&var.STATUS!='接车单已复核待客户确认'&&var.STATUS!='结算单已复核待客户付款'&&var.STATUS!='结算单未提交复核'&&var.STATUS!='已创建结算单待客户付款'&&var.STATUS!='结算单已提交待复核'&&var.STATUS!='客户已付款待财务确认'&&var.STATUS!='已审批待财务确认'&&var.STATUS!='财务确认待申请人确认'&&var.STATUS!='申请交车中'&&var.STATUS!='财务已确认'&&var.STATUS!='拒绝放行'&&var.STATUS!='待担保人确认'&&var.STATUS!='同意放行---已付款'&&var.STATUS!='同意放行---未付款'&&var.STATUS!='接车单已提交待复核'&&var.STATUS!='已创建结算单待提交复核'}"> 
														<a class="btn btn-mini btn-success" style="margin-right:2px; height:30px;line-height:30px; margin-bottom:5px;" onclick="addInforelay('${var.ORDER_ID}');">+信息反馈单</a>
														<a class="btn btn-mini btn-success"  style="margin-right:2px; height:30px;line-height:30px; margin-bottom:5px;" onclick="addPartofferPomx('${var.ORDER_ID}');">+配件报价单</a>
														<a class="btn btn-mini btn-success"  style="margin-right:2px; height:30px;line-height:30px; margin-bottom:5px;" onclick="addPartofferDsmx('${var.ORDER_ID}');">+工时报价单</a>
														<a class="btn btn-mini btn-success" style="margin-right:2px; height:30px;line-height:30px; margin-bottom:5px;" onclick="addDispatch('${var.ORDER_ID}');">+派工单</a>
														<a class="btn btn-mini btn-success"  style="margin-right:2px; height:30px;line-height:30px; margin-bottom:5px;" onclick="addSettlement('${var.ORDER_ID}');">+结算单</a>
													</c:if>
													<c:if test="${var.STATUS!='申请交车中'&&var.STATUS!='已交车'&&var.STATUS!='订单已取消'&&var.STATUS!='同意放行---已付款'&&var.STATUS!='同意放行---未付款'&&var.STATUS!='待担保人确认'}"> 
														<a class="btn btn-mini btn-success" style="margin-right:2px; height:30px;line-height:30px; margin-bottom:5px;" onclick="applyGiveCar('${var.ORDER_ID}');">申请交车</a>
													</c:if>
													<%-- <c:if test="${var.STATUS == '已创建结算单待客户付款' }"> --%>
													<c:if test="${var.STATUS == '结算单已复核待客户付款' }">
														<a class="btn btn-mini btn-success" style="margin-right:3px; height:30px;line-height:30px; margin-bottom:5px;" onclick="pay('${var.ORDER_ID}');">客户付款</a>
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
						<div class="page-header position-relative">
						<table style="width:100%;">
							<tr>
								<td style="vertical-align:top;">
									<a class="btn btn-mini btn-success" onclick="add();">新增</a>
								</td>
								<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
							</tr>
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
		
		//新增
		function add(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>order/goAdd.do';
			 diag.Width = 450;
			 diag.Height = 355;
			 diag.Modal = true;				//有无遮罩窗口
			 diag. ShowMaxButton = true;	//最大化按钮
		     diag.ShowMinButton = true;		//最小化按钮
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 top.jzts();
						 setTimeout("self.location=self.location",10000);
					 }else{
						 nextPage(${page.currentPage});
					 }
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//删除
		function del(Id){
			bootbox.confirm("确定要删除吗?", function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>order/delete.do?ORDER_ID="+Id+"&tm="+new Date().getTime();
					$.get(url,function(data){
						nextPage(${page.currentPage});
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
			 diag.URL = '<%=basePath%>order/goEdit.do?ORDER_ID='+Id;
			 diag.Width = 450;
			 diag.Height = 355;
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
		
		//新增接车单明细
		function getOrder(Id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="订单详情";
			 diag.URL = '<%=basePath%>order/getOrder.do?ORDER_ID='+Id;
			 diag.Width = 500;
			 diag.Height = 450;
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
		
		//新增信息反馈单明细
		function addInforelay(Id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增信息反馈单明细";
			 diag.URL = '<%=basePath%>order/goAddInforelay.do?ORDER_ID='+Id;
			 diag.Width = 500;
			 diag.Height = 450;
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
		
		//新增派工单明细
		function addDispatch(Id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增派工单明细";
			 diag.URL = '<%=basePath%>order/goAddDispatch.do?ORDER_ID='+Id;
			 diag.Width = 500;
			 diag.Height = 450;
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
		
		//新增配件报价单明细
		function addPartofferPomx(Id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增配件报价单明细";
			 diag.URL = '<%=basePath%>order/goAddPartofferPomx.do?ORDER_ID='+Id;
			 diag.Width = 500;
			 diag.Height = 450;
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
		
		//新增配件采购单明细
		function addPartofferDsmx(Id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增配件采购单明细";
			 diag.URL = '<%=basePath%>order/goAddPartofferDsmx.do?ORDER_ID='+Id;
			 diag.Width = 500;
			 diag.Height = 450;
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
		
		//新增结算单明细
		function addSettlement(Id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增结算单明细";
			 diag.URL = '<%=basePath%>order/goAddSettlement.do?ORDER_ID='+Id;
			 diag.Width = 500;
			 diag.Height = 450;
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
		
		function applyGiveCar(Id){
			$.ajax({
				url: "<%=basePath%>order/applyGiveCar.do",
				data:{
					"ORDER_ID" : Id
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
		
		//批量操作
		function makeAll(msg){
			bootbox.confirm(msg, function(result) {
				if(result) {
					var str = '';
					for(var i=0;i < document.getElementsByName('ids').length;i++){
					  if(document.getElementsByName('ids')[i].checked){
					  	if(str=='') str += document.getElementsByName('ids')[i].value;
					  	else str += ',' + document.getElementsByName('ids')[i].value;
					  }
					}
					if(str==''){
						bootbox.dialog({
							message: "<span class='bigger-110'>您没有选择任何内容!</span>",
							buttons: 			
							{ "button":{ "label":"确定", "className":"btn-sm btn-success"}}
						});
						$("#zcheckbox").tips({
							side:1,
				            msg:'点这里全选',
				            bg:'#AE81FF',
				            time:8
				        });
						return;
					}else{
						if(msg == '确定要删除选中的数据吗?'){
							top.jzts();
							$.ajax({
								type: "POST",
								url: '<%=basePath%>order/deleteAll.do?tm='+new Date().getTime(),
						    	data: {DATA_IDS:str},
								dataType:'json',
								//beforeSend: validateData,
								cache: false,
								success: function(data){
									 $.each(data.list, function(i, list){
											nextPage(${page.currentPage});
									 });
								}
							});
						}
					}
				}
			});
		};
		
		function pay(Id) {
			$.ajax({
				url: "<%=basePath%>settlement/paySettlement.do",
				data:{
					"ORDER_ID" : Id
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
		
		//导出excel
		function toExcel(){
			window.location.href='<%=basePath%>order/excel.do';
		}
	</script>


</body>
</html>
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
	<style>
		.chakanbox-centen {
			    width: 100%;
			    max-height: 60%;
			    overflow-y: auto;
			}
			.chakanbox {
			    width: 100%;
			   	height: auto;
			    background-color: rgba(55,69,84,0.6);
			    filter: Alpha(opacity=60);
			    position: absolute;
			    left: 0px;
			    top: 10px;			  
			    z-index: 96;
			}
			.chakanone {
			    width: 100%;
			    height: 40px;
			    font-size: 12px;
			    display: flex;
			    justify-content: flex-start;
			    align-items: center;
			    color: #222222;
			    background: #FFFFFF;
			    text-indent:10px;
			    border-bottom: 1px solid #EEEEEE;
			}
			.qx {
			    width: 100px;
			    color: red;
			    text-align: center;
			    line-height: 50px;
			    background-color: #EEEEEE;
			    font-size: 0.3rem;
			    height: 50px;
			    margin: 20px auto;
			    border-radius: 5px;
			}
		  .minxi-box{
		  		position: relative;
		  }
	</style>
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
					
					<form action="order/${msg }.do" name="Form" id="Form_" method="post">
						<div id="zhongxin" style="padding-top: 13px;">
						
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">订单ID:</td>
								<td><input type="text" name="ORDER_ID" id="ORDER_ID" value="${pd.ORDER_ID}" maxlength="255" placeholder="这里输入备注" title="订单ID" style="width:98%;" readonly="readonly"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">公司ID:</td>
								<td><input type="text" name="YL10" id="YL10" value="${pd.YL10}" maxlength="255" placeholder="这里输入公司ID" title="公司ID" style="width:98%;" readonly="readonly"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">开工时间:</td>
								<td><input class="span10 date-picker" name="workStart" id="workStart"  value="" type="text" data-date-format="yyyy-mm-dd"  style="width:98%;" placeholder="开始工作时间" title="开始工作时间"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">完工时间:</td>
								<td><input class="span10 date-picker" name="workEnd" id="workEnd"  value="" type="text" data-date-format="yyyy-mm-dd" style="width:98%;" placeholder="结束工作时间" title="结束工作时间"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">服务类型</td>
								<td>
									<select class="chosen-select form-control type"  id="type" onchange="getWorkName(this)" data-placeholder="服务类型" style="vertical-align:top;width: 120px;">									
										<c:forEach items="${list}" var="var" varStatus="vs">
			                                <option value="${var.STYPE_ID}">${var.TYPE_NAME}</option>
			                            </c:forEach>
								  	</select>
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">服务技师</td>
								<td>
									<select class="chosen-select form-control jishi" id="jishi"  data-placeholder="服务技师" style="vertical-align:top;width: 120px;">									
										
								  	</select>
								</td>
							</tr>
						</table>
						
						<table  class="table table-striped table-bordered table-hover mingxi-box mingxi-one" warehousepartid="sky">
							<!-- 第一个 -->	
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">项目选择:</td>
									<td>
										<select class="chosen-select form-control chakan"  onchange="tiancong(this)"  style="width:98%;"  data-placeholder="显示搜索" >									
									  	</select>
									</td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">项目搜索:</td>
									<td>
										<input type="text" name="PART_NAME" oninput="OnInput(event)" onpropertychange="OnPropChanged(event)" class="PART_NAME mease" value="" maxlength="255" style="width:98%;" />
									</td>
								</tr>
								<tr style="display:none">
									<td colspan="2">
										<input type="text" name="DSMX_ID" class="DSMX_ID" value="" style="display:none"/>
									 </td>
								</tr>
								<tr>
									<td style="width:75px;text-align: right;padding-top: 13px;">工时费:</td>
									<td><input type="text" name="DSMX_PRICE" class="DSMX_PRICE" value="" maxlength="255" style="width:98%;" /></td>
								</tr>
						</table>
						<!-- 第一个end -->
						<table id="table_button" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">备注:</td>
								<td><textarea type="text" name="REMARK" class="REMARK" style="width:98%; resize:none;" ></textarea></td>
							</tr>
							<tr>
								<td style="text-align: center;" colspan="10">
									<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
									<a class="btn btn-mini btn-primary" onclick="addPomx();">添加派工明细</a>
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
		function addPomx(){
			var items = '<table  class="table table-striped table-bordered table-hover mingxi-box mingxi-two" warehousepartid="sky"><tr><td style="width:75px;text-align: right;padding-top: 13px;">项目选择:</td><td><select class="chosen-select form-control chakan"  onchange="tiancong(this)"  style="width:98%;"  data-placeholder="显示搜索" >	<option value="">待选择</option></select></td></tr><tr><td style="width:75px;text-align: right;padding-top: 13px;">项目搜索:</td><td><input type="text" name="PART_NAME" oninput="OnInput(event)" onpropertychange="OnPropChanged(event)" class="PART_NAME mease" value="" maxlength="255" style="width:98%;" /></td></tr><tr><td colspan="2"><input type="text" name="DSMX_ID" class="DSMX_ID" value="" style="display:none"/></td></tr><tr><td style="width:75px;text-align: right;padding-top: 13px;">工时费:</td><td><input type="text" name="DSMX_PRICE" class="DSMX_PRICE" value="" maxlength="255" placeholder="这里输入配件编号" style="width:98%;" /></td></tr></table>';
			$("#table_button").before(items);
		}
		//保存
		function save(){
			if($("#workStart").val()==""){
				$("#workStart").tips({
					side:3,
		            msg:'请选择开工时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#workStart").focus();
			return false;
			}
			if($("#workEnd").val()==""){
				$("#workEnd").tips({
					side:3,
		            msg:'请选择完工时间',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#workEnd").focus();
			return false;
			}
			
			var orderId = $("#ORDER_ID").val();
			var gongsiId = $("#YL10").val();
			var workstart = $("#workStart").val();
			var workend = $("#workEnd").val();
			var type = $('#type').val();
			var jishi = $("#jishi").val();
			var remark = $("#REMARK").val();
			
			var partName = $("input[name='PART_NAME']");
			var DSMX_PRICE = $("input[name='DSMX_PRICE']");
			var DSMX_ID = $("input[name='DSMX_ID']");
			
			var mingxibox=$('.mingxi-box');
			var dsmxs = new Array();
			for(var i = 0; i < mingxibox.length; i++) {
				var obj = {
						partName: partName[i].value,
						DSMX_PRICE: DSMX_PRICE[i].value,
						DSMX_ID: DSMX_ID[i].value
					};
				dsmxs.push(obj)
			}
			$.ajax({
				url: "<%=basePath%>order/saveDispatching.do",
				data: {
					"orderId": orderId,
					"dsmxs": JSON.stringify(dsmxs),
					"gongsiId": gongsiId,
					"type": type,
					"jishi": jishi,
					"workstart": workstart,
					"workend": workend,
					"remark": remark
				},
				type: "post",
				success:function(data) {
					document.getElementById('zhongxin').style.display = 'none';
					top.Dialog.close();
				},
				error: function(e) {
					top.Dialog.close();
				}
			});
		}
		/* 搜索 */
		var box;
		var sn;
		function OnInput (event) {			
			var dd=event.srcElement;
			box=$(dd).parents(".mingxi-box");
			sn=event.srcElement.value;
			ss();
        }
    // Internet Explorer
        function OnPropChanged (event) {
            if (event.propertyName.toLowerCase () == "value") {
            	var dd=event.srcElement;
    			box=$(dd).parents(".mingxi-box");
    			
    			sn=event.srcElement.value;
    			ss();
            }
        }
		function ss() {
			/*  box = $(this).parents(".mingxi-box");
			 var sn=$(this).val(); */	
			 var gongsiId = $("#YL10").val();
			 var type = $("#type").val();
			 $.ajax({
				url: "<%=basePath%>order/getSName.do",
				data: {
					"keywords" : sn,
					"YL10" : gongsiId,
					"type" : type
				},
				dataType: "json",
				type: "post",
				success: function(result) {
					box.find(".chakan").empty();
					var data = result;
					if (data) {	
						box.find(".chakan").append("<option>...</option>");
						$.each(data, function(index, item) {
							
							var sname = $("<option></option>").append(item.S_NAME).attr({"name":item.S_NAME,"price":item.YL1,"id":item.SNAME_ID});						
							box.find('.chakan').append(sname);
						})
					} else{
						
					}
				},
				error: function(e) {
					alert(JSON.stringify(e));
				}
			});
		};
		

		
		//点击搜索的自动填充
		function tiancong(tc){
			var mingcheng=$(tc).find('option:selected').attr("name");
			var jg=$(tc).find('option:selected').attr("price");
			var id=$(tc).find('option:selected').attr("id");
			box.find("input[name='PART_NAME']").val(mingcheng);
			box.find("input[name='DSMX_PRICE']").val(jg);
			box.find("input[name='DSMX_ID']").val(id);
			$(tc).empty();
		}	
		
		

		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
			win();
		});
		//选择技师
		function win(){
			//清空服务技师
			$('.jishi').empty();
			var type=$('.type option:selected').text();
			var gongsiId = $("#YL10").val();
			$.ajax({
				url: "<%=basePath%>order/getJiShi.do",
				data: {
					"roleType": type,
					"gsId": gongsiId
				},
				dataType: "json",
				type: "post",
				success: function(result) {
					
						$.each(result, function(index, item) {
							var name_box = $("<option value=" + item.USERNAME + "></option>").append(item.NAME);
							$('.jishi').append(name_box);
						});
				},
				error: function(e) {
					alert(JSON.stringify(e));
				}
			});
		}

		//选择服务类型显示相应的服务技师
		function getWorkName(key333) {
			$("#Form_").find(".mingxi-two").remove();
			$(".mingxi-one").find(".PART_NAME").val("");
			$(".mingxi-one").find(".DSMX_PRICE").val("");
			win();
		}

		</script>
</body>
</html>
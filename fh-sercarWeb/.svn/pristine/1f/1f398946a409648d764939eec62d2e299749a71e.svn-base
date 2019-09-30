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
	<style type="text/css">
			
			.upload-content {
				max-width: 95%;
				min-width: 90%;
				margin: 0 auto;
				font-size: 12px;
				color: #666;
				padding: 20px;
				background: #fff;
				overflow-x: hidden;
			}
			
			.upload-content .content-img {
				max-width: 95%;
				min-width: 90%;
				overflow-x: hidden;
			}
			
			.upload-content .content-img-list {
				/*display: inline;*/
				max-width: 90%;
				min-width: 90%;
				height: auto;
				display: flex;
				justify-content: flex-start;
				align-items: center;
				
			}
			#waiguan{
				overflow-x: scroll;	
				overflow-y: hidden;
				padding: 0;
			}
			
			.upload-content .content-img .ico-plus {
				display: inline-block;
				vertical-align: middle;
				margin-top: -4px;
				margin-left: 2px;
				width: 28px;
				height: 28px;
				background: url('https://caiyunupload.b0.upaiyun.com/newweb/imgs/icon-plus.png') center/28px 28px;
			}
			
			.upload-content .content-img-list-item {
				position: relative;
				display: inline-block;
				width: 165px;
				height: 96px;
				margin-top: 20px;
				margin-left: 7px;
			}
			
			.upload-content .content-img-list-item:first-child {
				margin-left: 0;
			}
			
			.upload-content .content-img-list-item .hide {
				display: none;
			}
			
			.upload-content .content-img-list-item .delete-btn {
				position: absolute;
				left: 0;
				bottom: 0;
				text-align: center;
				width: 100%;
				background: rgba(0, 0, 0, 0.6);
				height: 28px;
				line-height: 28px;
				color: #fff;
				cursor: pointer;
			}
			
			.upload-content .content-img-list-item .ico-delete {
				display: inline-block;
				vertical-align: middle;
				width: 12px;
				height: 13px;
				background: url('https://caiyunupload.b0.upaiyun.com/newweb/imgs/icon-delete.png') center/12px 13px;
			}
			
			.upload-content .content-img-list-item img {
				width: 165px;
				height: 96px;
				border: 1px solid #EEEEEE;
			}
			
			.upload-content .upload-tips {
				padding-top: 10px;
				text-align: right;
				width: 100%;
			}
			/*图片上传按钮*/
			
			.upload-content .file {
				position: relative;
				/*display: inline-block;*/
				border: 1px dashed #DEDEDE;
				border-radius: 4px;
				color: #999999;
				text-decoration: none;
				text-indent: 0;
				width: 165px;
				height: 96px;
				line-height: 96px;
				margin: 0 auto;
			}
			
			.upload-content .file input {
				position: absolute;
				font-size: 0px;
				right: 0;
				top: 0;
				opacity: 0;
				cursor: pointer;
				width: 165px;
				height: 96px;
			}
			
			.upload-content .file:hover {
				color: #999999;
			}
			
			.upload-submit {
				text-align: center;
				margin-top: 50px;
			}
			
			.upload-submit .btn-submit-upload {
				display: inline-block;
				width: 170px;
				height: 40px;
				text-align: center;
				line-height: 38px;
				font-size: 14px;
				box-sizing: border-box;
				background: #ff8819;
				color: #fff;
				border: 1px solid #ff8819;
				border-radius: 2px;
				text-decoration: none;
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
					
					<form action="order/save.do" name="Form" id="Form" method="post" enctype="multipart/form-data">
						
						<div id="zhongxin" style="padding-top: 13px;">
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">车牌号:</td>
								<td><input type="text" name="CAR_NUM1" id="CAR_NUM1" value="${pd.CAR_NUM1}" maxlength="50" placeholder="这里输入车牌号" title="车牌号" style="width:98%;" maxlength="7" /></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">车架号:</td>
								<td><input type="text" name="CAR_NUM2" id="CAR_NUM2" value="${pd.CAR_NUM2}" maxlength="50" placeholder="这里输入车架号" title="车架号" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">发动机号:</td>
								<td><input type="text" name="CAR_NUM3" id="CAR_NUM3" value="${pd.CAR_NUM3}" maxlength="50" placeholder="这里输入发动机号" title="发动机号" style="width:98%;" /></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">汽车品牌:</td>
								<td style="vertical-align:top;padding-left:2px;">								
									<select name="YL20" id="YL20" onchange="getCheXi()">
										<option value=""> --请选择--</option>
			                            <c:forEach items="${list}" var="var" varStatus="vs">
			                                <option value="${var}"> ${var}</option>
			                            </c:forEach>
                        			</select>						
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">汽车车系:</td> 
								<td style="vertical-align:top;padding-left:2px;">								
									<select name="YL19" id="YL19" >
                        			</select>						
								</td>
							</tr>
							
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">联系人:</td>
								<td><input type="text" name="YL18" id="YL18" maxlength="50" value="${pd.YL18}" placeholder="这里输入联系人" title="联系人" style="width:98%;"/></td>
							</tr>
							
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">称谓:</td>
								<td style="vertical-align:top;padding-left:2px;">								
									<select name="wc" id="wc" >
										<option value="先生">先生</option>
			                            <option value="女士">女士</option>
                        			</select>						
								</td>
							</tr>
							
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">联系电话：</td>
								<td><input type="text" name="YL17" id="YL17" value="${pd.YL17}" maxlength="50" placeholder="这里输入联系电话" title="联系电话" style="width:98%;"/></td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">油量：</td>
								<td style="vertical-align:top;padding-left:2px;">								
									<select name="RECEPTION_YL6" id="RECEPTION_YL6" >
										<option value="1/2">1/2</option>
										<option value="1/3">1/3</option>
										<option value="1/4">1/4</option>
										<option value="满">满</option>
										<option value="空">空</option>
										<option value="无法获取">无法获取</option>
                        			</select>						
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">油量图片：</td>
								<td>
									<div class="upload-content">
										<div class="content-img">
											<ul class="content-img-list" id="youliang">
												<!-- <li class="content-img-list-item"><img src="https://www.baidu.com/img/bd_logo1.png" alt=""><a class="delete-btn"><i class="ico-delete"></i></a></li> -->
											</ul>
											<div class="file" id="ylup">
												<i class="ico-plus"></i>油量，支持jpg/png<input type="file" name="file" accept="image/*" id="uploadyl">					
											</div>
										</div>
									</div>	
								</td>
							</tr>
							
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">里程数：</td>
								<td><input type="text" name="RECEPTION_MILEAGE" id="RECEPTION_MILEAGE" maxlength="50" placeholder="这里输入里程数" title="里程数" style="width:98%;"/></td>
							</tr>
							
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">里程图片：</td>
								<td>
									<div class="upload-content">
										<div class="content-img">
											<!--里程-->
											<ul class="content-img-list" id="licheng">
												<!-- <li class="content-img-list-item"><img src="https://www.baidu.com/img/bd_logo1.png" alt=""><a class="delete-btn"><i class="ico-delete"></i></a></li> -->
											</ul>
											<div class="file" id="lcup">
												<i class="ico-plus"></i>里程，支持jpg/png<input type="file" name="file" accept="image/*" id="uploadlc">					
											</div>
										</div>
									</div>	
								</td>
							</tr>
							
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">外观状态：</td>
								<td style="vertical-align:top;padding-left:2px;">								
									<select name="YL1" id="YL1" >
										<option value="外观无损">外观无损</option>
										<option value="外观有损">外观有损</option>
                        			</select>						
								</td>
							</tr>
							
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">外观图片：</td>
								<td>
									<div class="upload-content">
										<div class="content-img">
											<!-- 外观图片 -->
											<ul class="content-img-list" id="waiguan">
												<!-- <li class="content-img-list-item"><img src="https://www.baidu.com/img/bd_logo1.png" alt=""><a class="delete-btn"><i class="ico-delete"></i></a></li> -->
											</ul>
											<div class="file" id="wgup">
												<i class="ico-plus"></i>外观，支持jpg/png<input type="file" name="file[]" multiple id="upload">
											</div>
										</div>
									</div>	
								</td>
							</tr>
							<tr>
								<td style="width:75px;text-align: right;padding-top: 13px;">是否路测：</td>
								<td style="vertical-align:top;padding-left:2px;">								
									<select name="RECEPTION_AGREE" id="RECEPTION_AGREE" >
										<option value="是">是</option>
										<option value="否">否</option>
                        			</select>						
								</td>
							</tr>
							
							<tr>
								<td style="text-align: center;" colspan="10">
									<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
									<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
								</td>
								<img class="fileimg" />
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
		
		var imgFile = []; //文件流
		var imgSrc = []; //图片路径
		$(function() {
			// 鼠标经过显示删除按钮
			$('.content-img-list').on('mouseover', '.content-img-list-item', function() {
				$(this).children('.delete-btn').removeClass('hide');
			});
			// 鼠标离开隐藏删除按钮
			$('.content-img-list').on('mouseleave', '.content-img-list-item', function() {
				$(this).children('.delete-btn').addClass('hide');
			});
			

			//外观图片上传
			$('#upload').on('change', function() {

				if($("#waiguan .content-img-list-item").length >= 30) {
					return alert("最多只能上传4张图片");
				}
				/* var imgSize = this.files[0].size; //b
				if(imgSize > 1024 * 1024 * 3) { //1M
					return alert("上传图片不能超过3M");
				}
				console.log(this.files[0].type) */
				if(this.files[0].type != 'image/png' && this.files[0].type != 'image/jpeg' && this.files[0].type != 'image/gif') {
					return alert("图片上传格式不正确");
				}

				var fileList = this.files;
				for(var i = 0; i < fileList.length; i++) {
					var imgSrcI = getObjectURL(fileList[i]);
					var type="wg";
					appendFileyh(imgSrcI,type);
				}

				
				this.value = null; //解决无法上传相同图片的问题
			})
			
			//里程图片上传
			$('#uploadlc').on('change', function() {

				if($("#licheng .content-img-list-item").length >= 1) {
					return alert("最多只能上传1张图片");
				}
				/* var imgSize = this.files[0].size; //b
				if(imgSize > 1024 * 1024 * 3) { //1M
					return alert("上传图片不能超过3M");
				}
				console.log(this.files[0].type) */
				if(this.files[0].type != 'image/png' && this.files[0].type != 'image/jpeg' && this.files[0].type != 'image/gif') {
					return alert("图片上传格式不正确");
				}

				var fileList = this.files;
				for(var i = 0; i < fileList.length; i++) {
					var imgSrcI = getObjectURL(fileList[i]);
					var type="lc";
					appendFileyh(imgSrcI,type);
				}
			})
			
			//油量图片上传
			$('#uploadyl').on('change', function() {

				if($("#youliang .content-img-list-item").length >= 1) {
					return alert("最多只能上传1张图片");
				}
				/* var imgSize = this.files[0].size; //b
				if(imgSize > 1024 * 1024 * 3) { //1M
					return alert("上传图片不能超过3M");
				} 
				console.log(this.files[0].type)*/
				if(this.files[0].type != 'image/png' && this.files[0].type != 'image/jpeg' && this.files[0].type != 'image/gif') {
					return alert("图片上传格式不正确");
				}

				var fileList = this.files;
				for(var i = 0; i < fileList.length; i++) {
					var imgSrcI = getObjectURL(fileList[i]);
					var type="yl";
					appendFileyh(imgSrcI,type);
				}
			})
		});
		
		//建立一個可存取到該file的url
		function getObjectURL(file) {
			var url = null;
			if(window.createObjectURL != undefined) { // basic
				url = window.createObjectURL(file);
			} else if(window.URL != undefined) { // mozilla(firefox)
				url = window.URL.createObjectURL(file);
			} else if(window.webkitURL != undefined) { // webkit or chrome
				url = window.webkitURL.createObjectURL(file);
			}
			return url;
		}

		var f5 = null;

		function appendFileyh(path,tp) {
			
			var img = new Image();
			img.src = path; // 传过来的图片路径在这里用。
			img.onload = function() {
				var that = this;
				//生成比例 
				var w = that.width,
					h = that.height,
					scale = w / h;
				w = 600 || w; //480  你想压缩到多大，改这里
				h = w / scale;

				//生成canvas
				var canvas = document.createElement('canvas');

				var ctx = canvas.getContext('2d');
				
				$(canvas).attr({
					width: w,
					height: h
				});
		        var imageData = ctx.getImageData(0, 0, canvas.width, canvas.height);
		        for(var i = 0; i < imageData.data.length; i += 4) {
		            // 当该像素是透明的，则设置成白色
		            if(imageData.data[i + 3] == 0) {
		                imageData.data[i] = 255;
		                imageData.data[i + 1] = 255;
		                imageData.data[i + 2] = 255;
		                imageData.data[i + 3] = 255; 
		            }
		        }
		        ctx.putImageData(imageData, 0, 0);
				
				

				ctx.drawImage(that, 0, 0, w, h);

				var base64 = canvas.toDataURL('image/jpeg', 1 || 1); //1最清晰，越低越模糊。有一点不清楚这里明明设置的是jpeg。弹出 base64 开头的一段 data：image/png;却是png。哎开心就好，开心就好
				//              alert(base64);      

				f5 = base64; // 把base64数据丢过去，上传要用。
				//		$('#youhou').empty();
				//					    console.log(base64)

				if(tp =="wg"){
					$("#waiguan").append('<li class="content-img-list-item"><img class="fileimg" src="' + base64 + '" alt=""><div onclick="wgsc(this)" class="hide delete-btn"><i class="ico-delete"></i></div></li>')
					if($("#waiguan .content-img-list-item").length == 30) { //隐藏上传按钮
						$('#wgup').hide();
					}
				}else if(tp=="lc"){
					$("#licheng").append('<li class="content-img-list-item"><img class="fileimg" src="' + base64 + '" alt=""><div onclick="lcsc(this)" class="hide delete-btn"><i class="ico-delete"></i></div></li>')
					if($("#licheng .content-img-list-item").length == 1) { //隐藏上传按钮
						$('#lcup').hide();
					}
				}else if(tp=="yl"){
					$("#youliang").append('<li class="content-img-list-item"><img class="fileimg" src="' + base64 + '" alt=""><div onclick="ylsc(this)" class="hide delete-btn"><i class="ico-delete"></i></div></li>')
					if($("#youliang .content-img-list-item").length == 1) { //隐藏上传按钮
						$('#ylup').hide();
					}
				
				}
				
			}
		}
		// 单个图片删除
		function wgsc(obj){				
			 $(obj).parents(".content-img-list-item").remove();  
			 if($("#waiguan .content-img-list-item").length < 30) { //显示上传按钮
				$('#wgup').show();
			 }
		}
		// 单个图片删除
		function lcsc(obj){				
			 $(obj).parents(".content-img-list-item").remove();  
			$('#lcup').show();
		}
		// 单个图片删除
		function ylsc(obj){				
			 $(obj).parents(".content-img-list-item").remove();  
			 $('#ylup').show();
		}
		
		//选择品牌获取车系
		function getCheXi(){
			
			var brand=$("#YL20").val();
			 $.ajax({
				url: "<%=basePath%>order/getCheXi.do",
				data:{
					"brand" : brand
				},
				type: "post",
				async: false,
				success:function(data) {
					$("#YL19").empty();
					$.each(data, function(index, item) {
						var option = $("<option value="+item+"></p>").append(item);
						$('#YL19').append(option);
					});
				},
				error: function(e) {
					alert(JSON.stringify(e));
				}
			}) 
		}
		
		//保存
		function save(){
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
			if($("#YL20").val()==""){
				$("#YL20").tips({
					side:3,
		            msg:'请选择品牌',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL20").focus();
				return false;
			}
			if($("#YL19").val()==""){
				$("#YL19").tips({
					side:3,
		            msg:'请选择车系',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL19").focus();
				return false;
			}
			if($("#YL18").val()==""){
				$("#YL18").tips({
					side:3,
		            msg:'请输入联系人',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL18").focus();
				return false;
			}
			if($("#YL17").val()==""){
				$("#YL17").tips({
					side:3,
		            msg:'请输入联系电话',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#YL17").focus();
				return false;
			}
			
			//外观图片
	    	var waiguantpList = "";
	    	var tupianbox = $("#waiguan").find('.fileimg');
	    	for(var i = 0; i < $("#waiguan").find(".fileimg").length; i++) {
	    		waiguantpList = waiguantpList + "@" + tupianbox.eq(i).attr("src");
			} 
	    	
	    	/*var waiguantpList = [];
	    	var tupianbox = $("#waiguan").find('.fileimg');
	    	for(var i = 0; i < $("#waiguan").find(".fileimg").length; i++) {
	    		var obj = tupianbox.eq(i).attr("src");
	    		waiguantpList.push(obj);
			}
	    	
	    	for(var i = 0; i < waiguantpList.length; i++) {
	    		alert(JSON.stringify(waiguantpList[i]));
			}*/
	    	
	    	
	    	
	    	//里程图片
	    	var lcimg=$("#licheng").find(".fileimg").attr("src");
	    	//油量图片
	    	var ylimg=$("#youliang").find(".fileimg").attr("src");
	    	
	    	var CAR_NUM1 = $("#CAR_NUM1").val();
	    	var CAR_NUM2 = $("#CAR_NUM2").val();
	    	var CAR_NUM3 = $("#CAR_NUM3").val();
	    	var RECEPTION_AGREE = $("#RECEPTION_AGREE").val();
	    	var YL20 = $("#YL20").val();
	    	var YL19 = $("#YL19").val();
	    	var YL18 = $("#YL18").val();
	    	var YL17 = $("#YL17").val();
	    	var YL1 = $("#YL1").val();
	    	var RECEPTION_MILEAGE = $("#RECEPTION_MILEAGE").val();
	    	var RECEPTION_YL6 = $("#RECEPTION_YL6").val();
	    	var wc = $("#wc").val();
	    	
	    	if(CAR_NUM3.length == 17 || CAR_NUM3.length == 12 || CAR_NUM3 == '无法获取'){
	    		$.ajax({
					url: "<%=basePath%>order/save.do",
					contentType: 'application/json',
					dataType: "json",
					data: JSON.stringify({
						"waiguantpList" : waiguantpList,
						"lcimg" : lcimg,
						"ylimg" : ylimg,
						"CAR_NUM1" : CAR_NUM1,
						"CAR_NUM2" : CAR_NUM2,
						"CAR_NUM3" : CAR_NUM3,
						"YL20" : YL20,
						"YL19" : YL19,
						"YL18" : YL18,
						"YL17" : YL17,
						"YL1" : YL1,
						"RECEPTION_MILEAGE" : RECEPTION_MILEAGE,
						"RECEPTION_YL6" : RECEPTION_YL6,
						"RECEPTION_AGREE" : RECEPTION_AGREE,
						"wc" : wc
					}),
					type: "post",
					success:function(data) {
						document.getElementById('zhongxin').style.display = 'none';
						top.Dialog.close();
					},
					error: function(e) {
						document.getElementById('zhongxin').style.display = 'none';
						top.Dialog.close();
					}
				});
	    	}else{
	    		alert("车架号不得等于17位数或12位数");
	    	}
		}
		
		$('input[name="CAR_NUM1"]').blur(function() {
			var str=$('input[name="CAR_NUM1"]').val();
			if(str.length<7){
				alert("车牌号不得低于7位数");
				$('input[name="CAR_NUM1"]').focus();
			}else if(str.length == 7){	
				cardid(str);
			}else if(str.length>7){
								
			}
		})
		
		function cardid(str){
			$.ajax({
				url: "<%=basePath%>order/getCarInfo.do",
				data:{
					"CAR_NUM1" : str
				},
				type: "post",
				async: false,
				success:function(data) {
				/* 	alert(JSON.stringify(data)); */
					if(data != null){
						$('input[name="CAR_NUM2"]').val(data.CAR_NUM2);
						$('input[name="CAR_NUM3"]').val(data.CAR_NUM3);
						$("#YL20").find("option[value="+data.CAR_BRANK+"]").attr("selected",true);
						$("#YL19").append("<option>"+data.YL1+"</option>");
					}
				},
				error: function(e) {
					alert(JSON.stringify(e));
				}
			}) 
		}
		
		$('input[name="YL18"]').blur(function() {
			var str=$('input[name="YL18"]').val();
			contants(str);
		})
		
		function contants(str){
			$.ajax({
				url: "<%=basePath%>order/getContants.do",
				data:{
					"CONTACT" : str
				},
				type: "post",
				async: false,
				success:function(data) {
					if(data != null){
						$('input[name="YL17"]').val(data.CONTACT_TEL);
						$("#wc").find("option[value="+data.YL4+"]").attr("selected",true);
					}
				},
				error: function(e) {
					alert(JSON.stringify(e));
				}
			}) 
		}
		
		$(function() {
			//日期框
			$('.date-picker').datepicker({autoclose: true,todayHighlight: true});
		});

		</script>
</body>
</html>
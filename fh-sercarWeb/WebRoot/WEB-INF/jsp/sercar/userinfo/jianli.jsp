<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<!-- jsp文件头和头部 -->
<%@ include file="../../system/index/top.jsp"%>
</head>
<style>
tr{
	height:30px
}
.top{
    height:100px 
}
.one{
	padding-left:50px
}
</style>
<body class="no-skin">
	<div class="main-container" id="main-container">
		<div class="page-content">
			<form action="#" name="Form" id="Form" method="post">
				<div id="zhongxin" style="padding-top: 13px;">
					<div id="container" style="width: 650px;">
						<table style="width: 650px;">
							<tr class=top>
								<td colspan="3"><img src="static/jianli_img/jianli01.png" /></td>
							</tr>
							<tr>
								<td colspan="3"></td>
							</tr>
							<tr>
								<td colspan="3"><img src="static/jianli_img/jianli02.png" style="margin:0 0 0 30px;"/></td>
							</tr>
							<tr>
								<td class=one>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：${pd.NAME }</td>
								<td>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：${pd.YL1 }</td>
								<td rowspan="6"><div style="width: 100px; height: 120px;background-color:#C8C8C8;">
										<img src="${pd.PHOTO}" width="100%" height="100%"/>
									</div></td>
								<!-- border-radius:125px; -->
							</tr>
							<tr>
								<td class=one>出生年月：${pd.BIRTH }</td>
								<td>民&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;族：${pd.MINGZU }</td>
							</tr>
							<tr>
								<td class=one>户&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;籍：${pd.HUJI}</td>
								<td>政治面貌：${pd.ZHENZHIMIANMAO}</td>
							</tr>
							<tr>
								<td class=one>家庭住址：${pd.ADDRESS}</td>
								<td>身份证号：${pd.CARDNUMBER}</td>
							</tr>
							<tr>
								<td class=one>电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：${pd.PHONE}</td>
								<td>Q&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Q：${pd.QQ}</td>
							</tr>
							<tr>
								<td class=one>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：${pd.EMAIL}</td>
							</tr>
							<tr>
								<td colspan="3" height="20px"></td>
							</tr>
							<tr>
								<td colspan="3"><img src="static/jianli_img/jianli03.png" style="margin:0 0 0 30px;"/></td>
							</tr>
							<tr>
								<td class=one>毕业院校：${pd.SCHOOL }</td>
								<td>学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;历：${pd.XUELI }</td>
								<td>专&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业：${pd.ZHUANYE }</td>
							</tr>
							<tr>
								<td colspan="3" height="20px"></td>
							</tr>
							<tr>
								<td colspan="3"><img src="static/jianli_img/jianli04.png" style="margin:0 0 0 30px;"/></td>
							</tr>
							<tr>
								<td colspan="3" class=one>${pd.YL2 }</td>
							</tr>
							<tr>
								<td colspan="3" height="20px"></td>
							</tr>
							<tr>
								<td colspan="3"><img src="static/jianli_img/jianli05.png" style="margin:0 0 0 30px;"/></td>
							</tr>
								<c:forEach items="${resumeexperList}" var="var" varStatus="vs">
								<tr>
									<td class=one>起止时间：${var.TIME}</td>
									<td colspan="2">单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：${var.UNIT }</td>
								</tr>
								<tr>
									<td colspan="3" class=one>经&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;历：${var.EXPERIENCE }</td>
								</tr>
								</c:forEach>
							<tr>
								<td colspan="3"><img src="static/jianli_img/jianli06.png" style="margin:0 0 0 30px;"/></td>
							</tr>
							<tr>
								<td colspan="3" class=one>${pd.ZIWOPINGJIA }</td>
							</tr>
							<tr>
								<td colspan="3" style="height:100px"></td>
							</tr>
						</table>
					</div>
				</div>
			</form>


		</div>
	</div>

	<!-- 页面底部js¨ -->
	<%@ include file="../../system/index/foot.jsp"%>
	<script type="text/javascript">
		$(top.hangge());
	</script>
</body>
</html>
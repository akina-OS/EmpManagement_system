<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>员工修改界面</title>
<%@include file="/static/commonjsp/head.jsp"%>
<style type="text/css">
/* label color */
.input-field label {
	color: #e0e0e0;
}
/* label focus color */
.input-field input[type=text]:focus+label {
	color: #80d8ff;
}
/* label underline focus color */
.input-field input[type=text]:focus {
	border-bottom: 1px solid #e0e0e0;
	box-shadow: 0 1px 0 0 #80d8ff;
}
/* valid color */
.input-field input[type=text].valid {
	border-bottom: 1px solid #80d8ff;
	box-shadow: 0 1px 0 0 #80d8ff;
}
/* invalid color */
.input-field input[type=text].invalid {
	border-bottom: 1px solid #e0e0e0;
	box-shadow: 0 1px 0 0 #e0e0e0;
}
/* icon prefix focus color */
.input-field .prefix.active {
	color: #80d8ff;
}
</style>
</head>
<body>
	<header> <nav
		class="nav-extended  #40c4ff light-blue accent-2">
	<div class="nav-wrapper">
		<!-- 手机浏览器影藏该图片 -->
		<img class="responsive-img brand-logo  hide-on-small-only"
			src="/hnzs_voteSys/static/image/logo.png" width="10%">
		<!-- PC浏览器影藏该图片 -->
		<img class="responsive-img brand-logo  hide-on-med-and-up"
			src="/hnzs_voteSys/static/image/logo.png" width="30%"> <a
			href="#" data-activates="mobile-demo" class="button-collapse"> <i
			class="material-icons">menu</i>
		</a>
		<!-- PC端菜单开始 -->
		<ul id="nav-mobile" class="right hide-on-med-and-down">
			<li class="active"><a href="/hnzs_voteSys/adm/gotoEmpistr">员工管理</a></li>
			<li><a href="/hnzs_voteSys/adm/gotoVStat">投票统计</a></li>
			<li><a href="/hnzs_voteSys/adm/gotoOpinion">意见管理</a></li>
			<li><a href="/hnzs_voteSys/adm/gotoUpdatePwd">修改密码</a></li>
		</ul>
		<!-- PC端菜单结束 -->
		<!-- 手机端菜单开始 -->
		<ul class="side-nav" id="mobile-demo">
			<li class="active"><a href="/hnzs_voteSys/adm/gotoEmpistr">员工管理</a></li>
			<li><a href="/hnzs_voteSys/adm/gotoVStat">投票统计</a></li>
			<li><a href="/hnzs_voteSys/adm/gotoOpinion">意见管理</a></li>
			<li><a href="/hnzs_voteSys/adm/gotoUpdatePwd">修改密码</a></li>
		</ul>
		<!-- 手机端菜单结束 -->

	</div>
	</nav> </header>
	<main>

	<div class="container">
		<div class="row" style="margin-top: 5%">
			<form onsubmit="return open_loading()" id="emp_date" class="col s12"
				method="POST" enctype="multipart/form-data"
				action="/hnzs_voteSys/adm/updateEmp">
				<input hidden value="${updateEmp.id}" name="emp_id">
				<div class="row">
					<div class="input-field col s12 m6 l6">
						<input placeholder="请键入修改的姓名" value="${updateEmp.empName}"
							id="emp_name" name="emp_name" type="text" class="validate">
						<label for="first_name">姓名</label>
					</div>
				</div>
				<div class="row">
					<div class="col s12 m6 l6">
						<div class=" file-field input-field">
							<div class="btn #00b0ff light-blue accent-3">
								<span>文件</span> <input placeholder="请上传png或jpg格式的图片"
									name="img_file" onchange="changeImgUrl()" id="img_file"
									type="file">
							</div>
							<div class="file-path-wrapper">
								<input class="file-path validate" type="text">
							</div>
						</div>
					</div>
					<div style="margin-top: -10%"
						class="input-field col s3  offset-s1 hide-on-small-only">
						<img id="image_head_pc" class="responsive-img materialboxed"
							src="${updateEmp.empImg}">
					</div>
				</div>
				<div class="row">
					<div class="input-field col s5  hide-on-med-and-up">
						<img id="image_head_model" class="responsive-img materialboxed"
							src="${updateEmp.empImg}">
					</div>
				</div>
				<br> <br>
				<div class="row">
					<div class="col s5">
						<!-- <a  onclick="ajax_form()" class="waves-effect waves-light btn-large #00b0ff light-blue accent-3">
							 <i class="material-icons right">send</i>
							
						</a> -->
						<button
							class=" btn waves-effect waves-light #00b0ff light-blue accent-3"
							type="submit" name="action">
							修改员工信息 <i class="material-icons right">send</i>
						</button>

					</div>
				</div>


			</form>
		</div>

	</div>


	</main>
	<div id="loading" class="modal">
		<div class="progress">
			<div class="indeterminate"></div>
		</div>
	</div>
	<%@include file="/static/commonjsp/body.jsp"%>
	<script type="text/javascript"
		src="/hnzs_voteSys/static/js/adm/empUpdate.js"></script>
	<script type="text/javascript">
		$(function() {
			Materialize.toast("${upLoadImagemsg}", 3000);
		});
	</script>
</body>
</html>
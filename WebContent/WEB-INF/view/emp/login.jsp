<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>员工登陆</title>
<%@include file="/static/commonjsp/head.jsp"%>

</head>
<body>
	<header></header>
	<main>
	<div class="container">
		<!-- 页面内容 -->
		<div class="row " style="margin-top: 20%">
			<form onsubmit="return checkIn()" method="POST"
				action="/hnzs_voteSys/emp/lar" class="  col  s12 ">
				<div class="row">
					<div class="input-field col s10  ">
						<input id="emp_name" name="emp_name" type="text" class="validate ">
						<label for="emp_name">姓名</label>
					</div>
				</div>
				<div class="row">
					<div class="input-field col s10">
						<input id="emp_pwd" name="emp_pwd" type="password"  
							class="validate"> <label for="emp_pwd">密码</label>
					</div>
				</div>
				<div class="row">
					<div class="input-field col s10">
						<button
							class="btn waves-effect waves-light #40c4ff light-blue accent-1"
							type="submit" name="action">
							登陆 <i class="material-icons right">send</i>
						</button>

					</div>
				</div>
			</form>
		</div>
	</div>
	</main>
	<%@include file="/static/commonjsp/body.jsp"%>
	<script type="text/javascript"
		src="/hnzs_voteSys/static/js/emp/login.js"></script>
	<script type="text/javascript">
		$(function() {
			Materialize.toast("${errormsg}", 4000);
		});
	</script>
</body>
</html>
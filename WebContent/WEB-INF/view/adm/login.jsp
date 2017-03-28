<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>管理员登陆</title>
<%@include file="/static/commonjsp/head.jsp"%>

</head>
<body>
	<header></header>
	<main>
	<div class="container">
		<!-- 页面内容 -->
		<div class="row " style="margin-top: 20%">
			<form 
				onsubmit="return checkIn()" 
				method="POST" 
				action="/hnzs_voteSys/adm/admLog" 
				class="  col  s12 ">
				<div class="row">
					<div class="input-field col s10  ">
						<input id="adm_name" name="adm_name" type="text" class="validate ">
						<label for="adm_name">管理员账号</label>
					</div>
				</div>
				<div class="row">
					<div class="input-field col s10">
						<input id="adm_pwd" name="adm_pwd" type="password" class="validate"> 
						<label for="adm_pwd">密码</label>
					</div>
				</div>
				<div class="row">
					<div class="input-field col s10">
						<button
							class="btn waves-effect waves-light #40c4ff light-blue accent-1"
							type="submit" name="action">
							登陆 
							<i class="material-icons right">send</i>
						</button>

					</div>
				</div>
			</form>
		</div>
	</div>
	</main>
	<%@include file="/static/commonjsp/body.jsp"%>
	<script type="text/javascript" src="/hnzs_voteSys/static/js/adm/login.js"></script>
	<script type="text/javascript">
		$(function() {
			Materialize.toast("${errormsg}", 4000);
		});
	</script>
</body>
</html>
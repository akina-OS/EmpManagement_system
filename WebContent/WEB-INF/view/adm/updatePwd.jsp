<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>管理员密码修改</title>
<%@include file="/static/commonjsp/head.jsp"%>
<style type="text/css">
img.responsive-img, video.responsive-video {
	max-width: 30%;
}

nav.nav-extended .nav-wrapper {
	min-height: 118px;
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
			<li><a href="/hnzs_voteSys/adm/gotoEmpistr">员工管理</a></li>
			<li><a href="/hnzs_voteSys/adm/gotoVStat">投票统计</a></li>
			<li><a href="/hnzs_voteSys/adm/gotoOpinion">意见管理</a></li>
			<li class="active"><a href="/hnzs_voteSys/adm/gotoUpdatePwd">修改密码</a></li>
		</ul>
		<!-- PC端菜单结束 -->
		<!-- 手机端菜单开始 -->
		<ul class="side-nav" id="mobile-demo">
			<li><a href="/hnzs_voteSys/adm/gotoEmpistr">员工管理</a></li>
			<li><a href="/hnzs_voteSys/adm/gotoVStat">投票统计</a></li>
			<li><a href="/hnzs_voteSys/adm/gotoOpinion">意见管理</a></li>
			<li class="active"><a href="/hnzs_voteSys/adm/gotoUpdatePwd">修改密码</a></li>
		</ul>
		<!-- 手机端菜单结束 -->
	</div></header>
	<main>
	<div class="container">
		<form style="margin-top: 10%" onsubmit="return checkIn()"
			method="POST" action="/hnzs_voteSys/adm/updatePwd" class="  col  s12 ">
			<div class="row">
				<div class="input-field col s12 m8 l6">
					<input id="new_pwd" name="new_pwd" type="password" class="validate">
					<label for="new_pwd">请输入密码</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s12 m8 l6">
					<input id="new_pwd_ed" name="new_pwd_ed" type="password" class="validate">
					<label for="new_pwd_ed">请重新密码</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s12 m8 l6">
					<button
						class="btn waves-effect waves-light #40c4ff light-blue accent-1"
						type="submit" name="action">
						修改密码 <i class="material-icons right">send</i>
					</button>

				</div>
			</div>
		</form>

	</div>
	</main>
	<%@include file="/static/commonjsp/body.jsp"%>
	<script type="text/javascript"
		src="/hnzs_voteSys/static/js/adm/updatePwd.js"></script>
</body>
</html>
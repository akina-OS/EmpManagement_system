<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>员工意见管理</title>
<%@include file="/static/commonjsp/head.jsp"%>
<style type="text/css">
img.responsive-img, video.responsive-video {
	max-width: 30%;
}

nav.nav-extended .nav-wrapper {
	min-height: 118px;
}

blockquote {
	border-left: 5px solid #00b0ff;
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
			<li class="active"><a href="/hnzs_voteSys/adm/gotoOpinion">意见管理</a></li>
			<li><a href="/hnzs_voteSys/adm/gotoUpdatePwd">修改密码</a></li>
		</ul>
		<!-- PC端菜单结束 -->
		<!-- 手机端菜单开始 -->
		<ul class="side-nav" id="mobile-demo">
			<li><a href="/hnzs_voteSys/adm/gotoEmpistr">员工管理</a></li>
			<li><a href="/hnzs_voteSys/adm/gotoVStat">投票统计</a></li>
			<li class="active"><a href="/hnzs_voteSys/adm/gotoOpinion">意见管理</a></li>
			<li><a href="/hnzs_voteSys/adm/gotoUpdatePwd">修改密码</a></li>
		</ul>
		<!-- 手机端菜单结束 -->
	</div></header>
	<main> <br>
	<br>
	<div class="container">
		<!-- 页面内容放在这里 	-->
		<table class="striped">
			<thead>
				<tr>
					<th data-field="id">提交员工</th>
					<th data-field="name">内容</th>
					<th data-field="price">删除</th>
				</tr>
			</thead>

			<tbody id="opinionDate">

			</tbody>
		</table>
		<br> <br>
		<blockquote id="count_num"></blockquote>
		<br>
		<div id="page"></div>
	</div>
	<div id="loading" class="modal">
		<div class="progress">
			<div class="indeterminate"></div>
		</div>
	</div>
	<div id="showOpinion" class="modal">
		<div class="modal-content">
			<h4>意见内容</h4>
			<p id="showedOpin"></p>
		</div>
		<div class="modal-footer">
			<a href="#!"
				class=" modal-action modal-close waves-effect waves-green btn-flat">关闭</a>
		</div>
	</div>
	</main>
	<%@include file="/static/commonjsp/body.jsp"%>
	<script type="text/javascript"
		src="/hnzs_voteSys/static/js/adm/opinionManag.js"></script>
</body>
</html>
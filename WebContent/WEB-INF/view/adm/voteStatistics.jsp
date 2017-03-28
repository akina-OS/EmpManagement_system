<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>投票统计</title>
<%@include file="/static/commonjsp/head.jsp"%>
<style type="text/css">
.switch label input[type=checkbox]:checked+.lever:after {
	background-color: #52b6d2;
}

.switch label input[type=checkbox]:checked+.lever {
	background-color: #47dfff;
}

.dropdown-content li>a, .dropdown-content li>span {
	color: #00b0ff;
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
			<li class="active"><a href="/hnzs_voteSys/adm/gotoVStat">投票统计</a></li>
			<li><a href="/hnzs_voteSys/adm/gotoOpinion">意见管理</a></li>
			<li><a href="/hnzs_voteSys/adm/gotoUpdatePwd">修改密码</a></li>
		</ul>
		<!-- PC端菜单结束 -->
		<!-- 手机端菜单开始 -->
		<ul class="side-nav" id="mobile-demo">
			<li><a href="/hnzs_voteSys/adm/gotoEmpistr">员工管理</a></li>
			<li class="active"><a href="/hnzs_voteSys/adm/gotoVStat">投票统计</a></li>
			<li><a href="/hnzs_voteSys/adm/gotoOpinion">意见管理</a></li>
			<li><a href="/hnzs_voteSys/adm/gotoUpdatePwd">修改密码</a></li>
		</ul>
		<!-- 手机端菜单结束 -->
	</div>
	<div class="nav-content">
		<ul class="tabs tabs-transparent">
			<!-- <li class="tab disabled"><a href="#test3">禁用标签</a></li> -->
			<li class="tab"><a href="#vote_month">本月投票统计</a></li>
			<li class="tab"><a href="#vote_year">本年度投票统计</a></li>
		</ul>
	</div>
	</nav>
	<div id="loading" class="modal">
		<div class="progress">
			<div class="indeterminate"></div>
		</div>
	</div>
	</header>
	<main>
	<div class="container">
		<!-- 显示主体 -->
		<div id="vote_month">
			<div class="row">
				<br>
				<div class="switch">
					<label> 柱状图 <input id="changeInput"
						onclick="onchangedfun()" type="checkbox"> <span
						class="lever"></span> 南丁格尔图
					</label>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col s12">
					<div style="height: 900px;" id="main_tu"></div>
				</div>
			</div>
		</div>

		<div id="vote_year">

			<div class="row">
				<br>
				<div class="  col s6">
					<a
						class="waves-effect waves-light btn dropdown-button #00b0ff light-blue accent-3"
						data-activates='dropdown1'> <i class="material-icons right">web</i>
						选择年份
					</a>
					<!-- Dropdown Structure -->
					<ul id='dropdown1' class='dropdown-content'>
					</ul>

				</div> 
			</div>
			<div class="row">
				<div class="col s12">
					<div style="height: 900px;" id="main_tu_year"></div>
				</div>
			</div>

		</div>

	</div>
	</main>
	<%@include file="/static/commonjsp/body.jsp"%>
	<!-- 引入 ECharts 文件 -->
	<script src="/hnzs_voteSys/static/js/Echrts/echarts.min.js"></script>
	<script type="text/javascript"
		src="/hnzs_voteSys/static/js/adm/voteStatistics.js"></script>


</body>
</html>
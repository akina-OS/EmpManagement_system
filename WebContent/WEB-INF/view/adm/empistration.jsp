<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>管理员界面</title>
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
	border-bottom: 1px solid #e0e0e0;
	box-shadow: 0 1px 0 0 #e0e0e0;
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

blockquote {
	border-left: 5px solid #00b0ff;
}

ul {
	overflow-x: hidden;
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
	<div class="nav-content">
		<ul class="tabs tabs-transparent">
			<li class="tab"><a class="active" href="#empAdm">员工管理</a></li>
			<li class="tab"><a href="#addEmp">员工添加</a></li>
		</ul>
	</div>
	</nav> </header>
	<main> <!-- 员工管理开始 -->
	<div class="container" id="empAdm">
		<!-- 页面内容放在这里 -->
		<br>
		<div class="row">
			<div class="input-field col s6">
				<i class="material-icons prefix">search</i> <input id="query_str"
					type="text" class="validate"> <label for="query_str">请输入需要检索的姓名</label>
			</div>
			<a onclick="getEmpByPage(1)"
				class=" col s3 offset-s1 waves-effect waves-light btn-large #00b0ff light-blue accent-3">
				搜索 </a>

		</div>
		<br>
		<div class="row"> 
			<a  onclick="getVoteEmp_no()"
				 class=" col s3 offset-s1 waves-effect waves-light btn-large #00b0ff light-blue accent-3">
				本次投票未确认员工 </a>
			<a href="#restSys"
				class=" col s3 offset-s1 waves-effect waves-light btn-large #00b0ff light-blue accent-3">
				重置系统 </a>

		</div>


		<table class="striped ">
			<thead>
				<tr>
					<th data-field="id">姓名</th>
					<th data-field="vote">员工本月投票记录</th>
					<th data-field="vote">员工本月得票</th>
					<th data-field="name" style="text-align: center;" colspan="2">操作</th>
				</tr>
			</thead>

			<tbody id="emp_date">

			</tbody>
		</table>
		<br> <br>
		<blockquote id="count_num"></blockquote>
		<br>
		<div id="page"></div>
	</div>
	<!-- 员工管理结束 --> <!-- 员工添加开始 -->

	<div class="container" id="addEmp">
		<br> <br> <br> <br>
		<form onsubmit="return open_loading()" id="emp_date" class="col s12"
			method="POST" enctype="multipart/form-data"
			action="/hnzs_voteSys/adm/addEmp">
			<div class="row">
				<div class="input-field col s12 m6 l6">
					<input placeholder="请键入新员工的 " id="emp_name" name="emp_name"
						type="text" class="validate"> <label for="first_name">姓名</label>
				</div>
			</div>
			<div class="row">
				<div class="col s12 m6 l6">
					<div class=" file-field input-field">
						<div class="btn #00b0ff light-blue accent-3">
							<span>文件</span> <input value="${emp_name}" name="img_file"
								onchange="changeImgUrl()" id="img_file" type="file">
						</div>
						<div class="file-path-wrapper">
							<input placeholder="请上传png或jpg格式的图片" class="file-path validate"
								type="text">
						</div>
					</div>
				</div>
				<div style="margin-top: -10%"
					class="input-field col s3  offset-s1 hide-on-small-only">
					<img id="image_head_pc" class="responsive-img materialboxed">
				</div>
			</div>
			<div class="row">
				<div class="input-field col s5  hide-on-med-and-up">
					<img id="image_head_model" class="responsive-img materialboxed">
				</div>
			</div>
			<br> <br>
			<div class="row">
				<div class="col s5">
					<button
						class=" btn waves-effect waves-light #00b0ff light-blue accent-3"
						type="submit" name="action">
						添加员工 <i class="material-icons right">send</i>
					</button>

				</div>
			</div>
		</form>
	</div>
	<!-- 员工添加结束 -->
	<div id="confirm" class="modal">
		<div class="modal-content">
			<h4>删除提醒</h4>
			<p id="confirm_str">确认要删除吗？</p>
		</div>
		<div class="modal-footer">
			<a href="#!"
				class=" modal-action modal-close waves-effect waves-green btn-flat">取消</a>
			<a onclick="delEmp()" href="#!" class="  btn-flat">确认</a>
		</div>
	</div>
	<div id="loading" class="modal">
		<div class="progress">
			<div class="indeterminate"></div>
		</div>
	</div>
	<!-- 重置系统提醒 -->
	<div id="restSys" class="modal">
		<div class="modal-content">
			<h4>确认要重置系统吗？</h4>
			<h5 id="confirm_str">系统重置后，本次投票信息会被移送至年度结算</h5>
		</div>
		<div class="modal-footer">
			<a href="#!"
				class=" modal-action modal-close waves-effect waves-green btn-flat">取消</a>
			<a  href="/hnzs_voteSys/admVote/restartSysDate" class="  btn-flat">确认</a>
		</div>
	</div>
	
	<!-- 本次投票未投员工-->
	<div id="voteEmp_no" class="modal">
		<div class="modal-content">
			<h6>本次投票还没有点击确认的员工</h6>
			<br><br>
			<p id="novote_emp">
			</p>
		</div>
		<div class="modal-footer">
			<a href="#!"
				class=" modal-action modal-close waves-effect waves-green btn-flat">关闭</a>
		</div>
	</div>
	</main>
	<%@include file="/static/commonjsp/body.jsp"%>
	<script type="text/javascript"
		src="/hnzs_voteSys/static/js/adm/empinistration.js"></script>
	<script type="text/javascript">
		$(function() {
			Materialize.toast("${adderrormsg}", 3000);
		});
	</script>
</body>
</html>
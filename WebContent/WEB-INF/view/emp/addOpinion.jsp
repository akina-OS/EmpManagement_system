<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" >
<head>
<title>提交建议</title>
<%@include file="/static/commonjsp/head.jsp"%>
</head>
<body>
	<input hidden value="${loginEmp.id}" id="loginEmpID" />
	<header> <%@include file="/static/commonjsp/empHeader.jsp"%></header>
	<main>
	<div class="container">
		<a href="/hnzs_voteSys/emp/gotoEmpVote"> 投票界面 </a> >> <a href="#!">提交建议</a>
		<br /> <br />
		<div class="row">
			<form action="/hnzs_voteSys/emp/addOpini" method="post"
				class="col s12" onsubmit="return checkIn()">
				<div class="row">
					<div class="row">
						<div class="input-field col s12">
							<textarea onkeyup="showl()" id="textarea1" name="textarea1"
								class="materialize-textarea "></textarea>
							<label for="textarea1">文本域</label> <span
								class="teal-text text-darken-2" id="showl">0</span>

						</div>
					</div>
				</div>
				<button
					class="btn waves-effect waves-light #00b0ff light-blue accent-3"
					type="submit" name="action">
					提交 <i class="material-icons right">send</i>
				</button>
			</form>
		</div>
	</div>








	</main>
	<%@include file="/static/commonjsp/body.jsp"%>
	<script type="text/javascript"
		src="/hnzs_voteSys/static/js/emp/addOpinion.js"></script>
</body>
</html>
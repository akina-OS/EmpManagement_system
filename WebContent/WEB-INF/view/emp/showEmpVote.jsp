<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" >
<head>
<title>投票界面</title>
<%@include file="/static/commonjsp/head.jsp"%>
<style type="text/css">
.card-panel {
	padding: 0px;
}

[type="checkbox"]+label {
	height: 50px;
	line-height: 50px
}

[type="checkbox"]:checked+label:before {
	left: 5px;
	top: 9px;
}

*, *:before, *:after {
	box-sizing: inherit;
}

[type="checkbox"]+label:before, [type="checkbox"]:not(.filled-in)+label:after {
    content: '';
    position: absolute;
    top: 13px;
    left: 10px;
    width: 18px;
    height: 18px;
    z-index: 0;
    border: 2px solid #5a5a5a;
    border-radius: 1px;
    margin-top: 2px;
    transition: .2s;
}


 
</style>


</head>
<body>
	<input hidden value="${loginEmp.id}" id="loginEmpID" />
	<header> <%@include file="/static/commonjsp/empHeader.jsp"%></header>
	<main>
		<br>
		<div class="container" id="mianBody">
				<!-- 页面内容 -->
		</div>
		  <div id="loading" class="modal">
			     <div class="progress">
			      	<div class="indeterminate"></div>
			     </div>
		  </div>
	</main>
	<div id="loading" class="modal">
		<div class="progress">
			<div class="indeterminate"></div>
		</div>
	</div>
	<%@include file="/static/commonjsp/body.jsp"%>
	<script type="text/javascript" src="/hnzs_voteSys/static/js/emp/showEmpVote.js"></script>
	<script type="text/javascript">
		$(function() {
			//Materialize.toast("${logmsg}", 4000);
		});
	</script>
</body>
</html>
<%@page import="com.hnzs.util.StaticStr"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<footer class="page-footer #40c4ff light-blue accent-2">
	<div class="container">
		<div class="row">
			<div class="col l6 s12">
				<h5 class="white-text">海纳展示 EVS</h5>
				<p class="grey-text text-lighten-4">员工投票系统</p>
			</div>
			<div class="col l4 offset-l2 s12">
				<!-- <h5 class="white-text">有建议吗？</h5> -->
				<ul>
					<%
						if (session.getAttribute(StaticStr.SESSION_EMP) != null) {
					%>
					 <li>
						<a class="grey-text text-lighten-3" href="/hnzs_voteSys/emp/gotoAddopinion">向公司提议....</a>
					</li>
					<%
						}
					%>
				</ul>
			</div>
		</div>
	</div>
	<div class="footer-copyright">
		<div class="container">
			© 2017 Akina
			<!--  <a class="grey-text text-lighten-4 right" href="#!">更多链接</a> -->
		</div>
	</div>
</footer>





<!-- 在jquery之后导入js文件 -->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript"
	src="/hnzs_voteSys/static/materialize/js/materialize.min.js"></script>

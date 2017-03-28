$(function() {
	// 初始化
	$(".button-collapse").sideNav();
});

/**
 * 表单验证方法
 * 
 * @returns
 */
function checkIn() {

	var new_pwd = $("#new_pwd").val();
	var new_pwd_ed = $("#new_pwd_ed").val();

	if (new_pwd == "") {
		Materialize.toast('请输入您的密码', 4000);
		return false;
	}
	if (new_pwd_ed == "") {
		Materialize.toast('请确认您的密码', 4000);
		return false;
	}
	if (new_pwd != new_pwd_ed) {
		Materialize.toast('您两次输入的密码不一致', 4000);
		return false;
	}

	return true;
}
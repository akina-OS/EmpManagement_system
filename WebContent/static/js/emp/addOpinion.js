$(document).ready(function() {

});

/*******************************************************************************
 * 表单验证
 */
function checkIn() {
	var l = $("#textarea1").val().length;
	if (l > 180) {
		Materialize.toast("不能大于180个字", 4000);
		return false;
	}
	if (l == 0) {
		Materialize.toast("请键入您的建议", 4000);
		return false;
	}
	return true;

}

/*******************************************************************************
 * 计数器函数
 * 
 * @returns
 */
function showl() {
	var l = $("#textarea1").val().length;
	$("#showl").html($("#textarea1").val().length);
	if (l > 180) {
		$("#textarea1").addClass("invalid");
		$("#showl").removeClass("teal-text");
		$("#showl").removeClass("text-darken-2");
		$("#showl").addClass("deep-orange-text");
		$("#showl").addClass("text-accent-3");
	} else {
		$("#textarea1").removeClass("invalid");
		$("#showl").removeClass("deep-orange-text");
		$("#showl").removeClass("text-accent-3");
		$("#showl").addClass("teal-text");
		$("#showl").addClass("text-darken-2");
	}

}
$(function() {
	// 初始化
	$('.modal').modal({
		dismissible : false,
		opacity : .6
	});
	// 初始化
	$(".button-collapse").sideNav();
});

/*******************************************************************************
 * 表单提交时执行
 * 
 * @returns
 */
function open_loading() {
	if ($("#emp_name").val() == "") {
		Materialize.toast("请输入员工名字", 3000);
		return false;
	}
	$('#loading').modal('open');
	return true;
}

/*******************************************************************************
 * 
 * @param sourceId
 * @param targetId
 * @param targetId2
 * @returns
 */
function changeImgUrl() {
	preImg("img_file", "image_head_pc");
	preImg("img_file", "image_head_model");
}

/**
 * 将本地图片 显示到浏览器上
 */
function preImg(sourceId, targetId) {
	var url = getFileUrl(sourceId);
	var imgPre = document.getElementById(targetId);
	imgPre.src = url;
}

/**
 * 从 file 域获取 本地图片 url
 */
function getFileUrl(sourceId) {
	var url;
	if (navigator.userAgent.indexOf("MSIE") >= 1) { // IE
		url = document.getElementById(sourceId).value;
	} else if (navigator.userAgent.indexOf("Firefox") > 0) { // Firefox
		url = window.URL
				.createObjectURL(document.getElementById(sourceId).files
						.item(0));
	} else if (navigator.userAgent.indexOf("Chrome") > 0) { // Chrome
		url = window.URL
				.createObjectURL(document.getElementById(sourceId).files
						.item(0));
	}
	return url;
}

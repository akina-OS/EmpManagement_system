$(function() {

	// 初始化
	$(".button-collapse").sideNav();
	// 初始化
	$('.modal').modal({
		dismissible : false,
		opacity : .6
	});
	$('.collapsible').collapsible();
	$('.chips').material_chip();
	getEmpByPage(1);
});

/*******************************************************************************
 * 等待删除的员工
 */
var del_emp_id = -1;

/*******************************************************************************
 * 获取员工数据函数
 * 
 * @param page
 * @returns
 */
function getEmpByPage(page) {
	$('#loading').modal('open');
	// 获取搜索条件
	var query_str = $("#query_str").val();
	$.post('/hnzs_voteSys/adm/getEmpByPpage', {
		pageNum : page,
		query_str : query_str
	}, function(data) {
		console.log(data);
		$("#emp_date").html(builStr(data.dataList));
		$("#page").html(builPageHtml(data.totalPage, page));
		$("#count_num").html("符合条件的员工一共有  "+ data.count  +" 位");
		
		$('.dropdown-button').dropdown({
		      inDuration: 300,
		      outDuration: 225,
		      constrain_width: false, // Does not change width of dropdown to
										// that
										// of the activator
		      hover: false, // Activate on hover
		      gutter: 0, // Spacing from edge
		      belowOrigin: false, // Displays dropdown below the button
		      alignment: 'left' // Displays dropdown with edge aligned to the
								// left
								// of button
		    }
		  );
		
		
		$('#loading').modal('close');
	}, 'json');
}

/*******************************************************************************
 * 构建员工信息结构
 * 
 * @param empDate
 * @returns
 */
function builStr(empDate) {
	var empHtml = '';
	var votedEmp;
	for (var i = 0; i < empDate.length; i++) {
		empHtml += '<tr>';
		empHtml += '<td  id="emp_' + empDate[i].id + '">' + empDate[i].empName
				+ '</td>';
		
		
		empHtml +='<td style="text-align: center;">';
		
		empHtml +='<div  class="row" >';
		empHtml +='<a class="col s7   dropdown-button btn #90caf9 blue lighten-3"  btn  href="#" data-activates="dropdown'+empDate[i].id+'">查看</a>';
		empHtml +='<ul  scoll="no"  id="dropdown'+empDate[i].id+'"  class="dropdown-content   "  >';
		
		
		votedEmp = empDate[i].votedEmpList
		if(votedEmp.length == 0){
			empHtml +='<li disabled style="text-align: center" style="text-align: center"><div    class="chip truncate" style="margin-top: 5%">没有数据</div></li>';  
		}else{
			for(var y = 0;y<votedEmp.length; y++){
				empHtml +='<li disabled style="text-align: center">';
				empHtml +='<div  class="chip  truncate" style="margin-top: 10%">';
				empHtml +='<img src="/hnzsimg/head/'+votedEmp[y].empImg+'" alt="Contact Person">';
				empHtml += votedEmp[y].empName;
				empHtml +='</div> ';
				empHtml +='</li>';
			}
		}
		
		
		empHtml +='</ul>';
		empHtml +='</div>';
		
		empHtml +='</td>';
		
		
		empHtml += '<td>';
		empHtml +='得'+empDate[i].votedNum  +'票';
		empHtml +='</td>';
		
		
		empHtml += '<td>';
		empHtml += '<a  href="/hnzs_voteSys/adm/getUpdateEmp?emp_id='
				+ empDate[i].id + '" class="waves-effect waves-teal btn-flat">';
		empHtml += '<i class="material-icons right">mode_edit</i>';
		empHtml += '修改信息';
		empHtml += '</a>';
		empHtml += '</td>';
		empHtml += '<td>';
		empHtml += '<a onclick="delConfirm(' + empDate[i].id
				+ ')"  class="waves-effect waves-teal btn-flat  ">';
		empHtml += '<i class="material-icons right">delete</i>';
		empHtml += '删除该员工';
		empHtml += '</a>';
		empHtml += '</td>';
		empHtml += '</tr>';
	}
	return empHtml;
}

/*******************************************************************************
 * 构建分页信息网页结构
 * 
 * @param totalPage
 *            总页数
 * @param page
 *            当前页数
 * @returns
 */
function builPageHtml(totalPage, page) {
	var pageHtml = '<ul class="pagination" >';
	pageHtml += '<li class="disabled">';
	pageHtml += '<a href="#!"  onclick="getEmpByPage(1)" >';
	pageHtml += '<i class="material-icons">';
	pageHtml += 'chevron_left';
	pageHtml += '</i>';
	pageHtml += '</a>';
	pageHtml += '</li>'
	for (var i = 1; i <= totalPage; i++) {
		if (i == page) {
			pageHtml += ' <li class="active"><a  onclick="getEmpByPage(' + i
					+ ')" class="#00b0ff light-blue accent-3" href="#!">' + i
					+ '</a></li>';
		} else {
			pageHtml += '<li class="waves-effect"><a onclick="getEmpByPage('
					+ i + ')" href="#!">' + i + '</a></li>';
		}
	}
	pageHtml += '<li class="waves-effect"><a onclick="getEmpByPage('
			+ totalPage
			+ ')"  href="#!"><i class="material-icons">chevron_right</i></a></li>';
	pageHtml += ' </ul>';
	return pageHtml;
}

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
	if ($("#img_file").val() == "") {
		Materialize.toast("请选择员工头像", 3000);
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

/**
 * 删除提醒函数
 * 
 * @param emp_id
 * @returns
 */
function delConfirm(emp_id) {
	$("#confirm_str").html("确定要删除员工  :  " + $("#emp_" + emp_id).html());
	del_emp_id = emp_id;
	$('#confirm').modal('open');
}

/*******************************************************************************
 * 删除员工函数
 * 
 * @param emp_id
 * @returns
 */
function delEmp() {
	$('#confirm').modal('close');　
	if (del_emp_id != -1) {
		$('#loading').modal('open');
		$.post('/hnzs_voteSys/adm/delEmp', {
			emp_id : del_emp_id
		}, function(data) {
			if (data == 1) {
				Materialize.toast("刪除成功", 3000);
			} else {
				Materialize.toast("刪除異常", 3000);
			}
			del_emp_id = -1;

		}, 'json');
	}
	$('#loading').modal('close');
	getEmpByPage(1);
}

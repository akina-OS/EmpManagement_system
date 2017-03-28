/*******************************************************************************
 * 定义全局变量
 */

var emp_vote_list;

$(function() {
	// 初始化
	$('.modal').modal({
		dismissible : false,
		opacity : .6
	});

	getLoginVoted();
	getAllEmp();
});

/*******************************************************************************
 * 获取除了自己以外的所有员工信息
 * 
 * @returns
 */
function getAllEmp() {
	$('#loading').modal('open');
	$.post('/hnzs_voteSys/emp/findAllemp', function(emp_data) {
		// console.log(emp_data);
		$("#mianBody").html(builStr(emp_data));
		$('#loading').modal('close');
	}, 'json');
}

/*******************************************************************************
 * 得到该员工的投票情况
 * 
 * @returns
 */
function getLoginVoted() {
	var logEmpId = $("#loginEmpID").val();
	$.post('/hnzs_voteSys/emp/getEmpVoted', {
		"id" : logEmpId
	}, function(empList) {
		emp_vote_list = empList;
	}, 'json');
}

/*******************************************************************************
 * 构建字符串
 * 
 * @param emp_data
 * @returns
 */
function builStr(emp_data) {
	var empHtml = '';
	for (var i = 0; i < emp_data.length; i++) {
		empHtml += '<div class="col s12 m8 offset-m2 l6 offset-l3">';
		empHtml += '<div class=" card-panel grey lighten-5 z-depth-1  s">';
		empHtml += '<div class="row valign-wrapper">';
		empHtml += '<div class="col s3">';
		empHtml += '<img ';
		empHtml += '  src="' + emp_data[i].empImg
				+ '"  class="circle  responsive-img">';
		empHtml += '</div>';
		empHtml += '<div class="col s3    valign-wrapper">';
		empHtml += '<span class="black-text  valign" style="text-align: center;">';
		empHtml += emp_data[i].empName;
		empHtml += '</span>';
		empHtml += '</div>';
		empHtml += '<div class="col s6">';
		empHtml += '<span class="black-text  ">';
		empHtml += '<p>';
		empHtml += '<input  value="' + emp_data[i].id + '" type="checkbox"';
		for (var y = 0; y < emp_vote_list.length; y++) {
			if (emp_vote_list[y].id == emp_data[i].id) {
				empHtml += '  checked="checked"   ';
			} else {
				empHtml += '';
			}
		}
		empHtml += 'id="emp_' + emp_data[i].id + '" /> ';
		empHtml += '<label for="emp_'
				+ emp_data[i].id
				+ '" class="waves-effect waves-teal btn-flat"onclick="voteClick(\'emp_'
				+ emp_data[i].id + '\')">';
		empHtml += '<a id="vote_num_' + emp_data[i].id + '">投票('
				+ emp_data[i].votedNum + ') </a>';
		empHtml += '<i class="material-icons right ">trending_up</i>';
		empHtml += '</label>';
		empHtml += '</p>';
		empHtml += '</span>';
		empHtml += '</div>';
		empHtml += '</div>';
		empHtml += '</div>';
		empHtml += '</div>';

	}
	return empHtml;
}

/*******************************************************************************
 * 投票事件
 * 
 * @param empID
 * @returns
 */
function voteClick(empID) {
	var empCheck = $('#' + empID).prop('checked');
	if (!empCheck) {
		empVote($('#' + empID).val());
	} else {
		cancelEmpVote($('#' + empID).val());
	}
}

/*******************************************************************************
 * 投票方法请求函数
 * 
 * @returns
 */
function empVote(vote_id) {
	// 投票人id
	var logEmpId = $("#loginEmpID").val();
	// 打开loading
	$('#loading').modal('open');
	$.ajax({
		url : '/hnzs_voteSys/emp/empVote',
		type : 'POST',
		dataType : 'JSON',
		data : {
			emp_id : logEmpId,
			vote_id : vote_id
		},
	}).done(function(date) {
		if (date >= 0) {
			var str = $("#vote_num_" + vote_id).html().replace("投票(", "");
			str = str.replace(")", "");
			var vote = parseInt(str) + 1;
			$("#vote_num_" + vote_id).html("投票(" + vote + ")");
			Materialize.toast('投票成功,您还有' + date + '张选票', 3000);
		} else if (date == -1) {
			// 把复选框取消选择
			$('#emp_' + vote_id).removeAttr("checked");
			Materialize.toast('您已经没有选票了', 3000);
		}
	}).fail(function() {
		Materialize.toast('错误', 3000);
	}).always(function() {
		// 打开loading
		$('#loading').modal('close');
	});

}

/*******************************************************************************
 * 根据用户id和得票id 取消投票函数
 * 
 * @param vote_id
 *            被投票人ID
 * @returns
 */
function cancelEmpVote(vote_id) {
	// 投票人id
	var logEmpId = $("#loginEmpID").val();
	// 打开loading
	$('#loading').modal('open');
	$.ajax({
		url : '/hnzs_voteSys/emp/cEmpVote',
		type : 'POST',
		dataType : 'JSON',
		data : {
			emp_id : logEmpId,
			vote_id : vote_id
		},
	}).done(function(date) {

		var str = $("#vote_num_" + vote_id).html().replace("投票(", "");
		str = str.replace(")", "");
		var vote = parseInt(str) - 1;
		$("#vote_num_" + vote_id).html("投票(" + vote + ")");
		// 把复选框取消选择
		$('#emp_' + vote_id).removeAttr("checked");
		Materialize.toast('您已经取消投票,您还有' + date + '张选票', 3000);
	}).fail(function() {
		Materialize.toast('错误', 3000);
	}).always(function() {
		// 关闭loading
		$('#loading').modal('close');
	});
}
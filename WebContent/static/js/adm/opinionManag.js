$(function() {
	// 初始化
	$(".button-collapse").sideNav();
	$('.modal').modal({
		dismissible : false,
		opacity : .6
	});

	getOpinByPage(1);
});


/**
 * 分页获取意见数据
 * 
 * @param page
 * @returns
 */
function getOpinByPage(page) {
	$('#loading').modal('open');
	$.post('/hnzs_voteSys/adm/getOpinDateByPage', {
		pageNum : page
	}, function(data) {
		 if(data.dataList.length==0){
			 $("#opinionDate").html("暂无数据");
		 }else {
				$("#opinionDate").html(intoHtml(data.dataList));
				$("#page").html(builPageHtml(data.totalPage, page));
				$("#count_num").html("一共有  " + data.count + " 条");
		}
		
		
		$('#loading').modal('close');
	}, 'json');
}

/*******************************************************************************
 * 
 * @param date
 * @returns
 */
function intoHtml(date) {

	var  opinHtml = "";
	
	var  opinionStr = "";
	for (var i = 0; i < date.length; i++) {
		opinHtml += "<tr>";　
		opinHtml += "<td>" + date[i].empName + "</td>";
		if (date[i].opinionStr.length > 14) { 
			opinionStr = date[i].opinionStr.substring(0, 14)+"..."; 
		} else{
			opinionStr = date[i].opinionStr; 
		}
		opinHtml += "<td><a onclick='showOpinion(\""+date[i].opinionStr+"\")'  class='  light-blue accent-4  waves-effect waves-light btn'>" + opinionStr 　+ "</a></td>";

		// date[i].id;
		opinHtml += "<td><a href='/hnzs_voteSys/adm/delOpin?opin_id="+date[i].id+"'>删除</a></td>";
		opinHtml += "</tr>";
	}
	return opinHtml;
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
	pageHtml += '<a href="#!"  onclick="getOpinByPage(1)" >';
	pageHtml += '<i class="material-icons">';
	pageHtml += 'chevron_left';
	pageHtml += '</i>';
	pageHtml += '</a>';
	pageHtml += '</li>'
	for (var i = 1; i <= totalPage; i++) {
		if (i == page) {
			pageHtml += ' <li class="active"><a  onclick="getOpinByPage(' + i
					+ ')" class="#00b0ff light-blue accent-3" href="#!">' + i
					+ '</a></li>';
		} else {
			pageHtml += '<li class="waves-effect"><a onclick="getOpinByPage('
					+ i + ')" href="#!">' + i + '</a></li>';
		}
	}
	pageHtml += '<li class="waves-effect"><a onclick="getOpinByPage('
			+ totalPage
			+ ')"  href="#!"><i class="material-icons">chevron_right</i></a></li>';
	pageHtml += ' </ul>';
	return pageHtml;
}



/*******************************************************************************
 * 查看函数
 * 
 * @returns
 */
function  showOpinion(opinionStr) {
	$("#showedOpin").html(opinionStr);
	$('#showOpinion').modal('open');
}


var emp_date;

$(function() {
	// 初始化
	$(".button-collapse").sideNav();
	$('.modal').modal({
		dismissible : false,
		opacity : .6
	});
	getVoteEmp();
	var date = new Date;
	var year = date.getFullYear();
	// getVoteByYear(year);
	getYear(year);

});

/*******************************************************************************
 * 生成年份选择器
 * 
 * @returns
 */
function getYear(year) {
	var temp = "";
	for (var i = year - 5; i <= year + 5; i++) {
		temp += "<li>";
		temp += "<a  onclick='getVoteByYear(" + i + ")'  href='#!'>" + i
				+ "</a>";
		temp += "</li>";
	}
	$("#dropdown1").html(temp);
}

/*******************************************************************************
 * 获取员工全年的得票情况
 * 
 * @param year
 * @returns
 */
function getVoteByYear(year) {
	$('#loading').modal('open');
	$.post('/hnzs_voteSys/admVote/getVoteByYear', {
		year : year
	}, function(empDate) {
		console.log(empDate);
		$('#loading').modal('close');
		initChartByYear(empDate);
	}, 'json');
}

/*******************************************************************************
 * 获取员工本月得票记录
 * 
 * @param page
 * @returns
 */
function getVoteEmp() {
	$('#loading').modal('open');
	$.post('/hnzs_voteSys/admVote/getVoteEmp', function(empDate) {
		$('#loading').modal('close');
		emp_date = empDate;
		initChart(empDate);
	}, 'json');

}

/*******************************************************************************
 * 判断是否选择
 * 
 * @returns
 */
function onchangedfun() {
	if ($("#changeInput").is(':checked')) {
		initChartAngle(emp_date);
	} else {
		initChart(emp_date);
	}
}

/*******************************************************************************
 * 初始化年得票数 柱状图
 * 
 * @returns
 */
function initChartByYear(empDate) {
	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('main_tu_year'));
	var tulie_date = new Array();
	var shuju_date = new Array();
	for (var i = 0; i < empDate.length; i++) {
		tulie_date[i] = empDate[i].empName;
		shuju_date[i] = empDate[i].votedNum;
	}

	var labelRight = {
		normal : {
			position : 'right'
		}
	};
	option = {
		title : {
			text : '员工年得票情况',
			subtext : '截止本月1日数据'
		},
		tooltip : {
			trigger : 'axis',
			axisPointer : { // 坐标轴指示器，坐标轴触发有效
				type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		grid : {
			top : 80,
			bottom : 30
		},
		xAxis : {
			type : 'value',
			position : 'top',
			splitLine : {
				lineStyle : {
					type : 'dashed'
				}
			},
		},
		yAxis : {
			type : 'category',
			axisLine : {
				show : true
			},
			axisLabel : {
				show : true
			},
			axisTick : {
				show : true
			},
			splitLine : {
				show : true
			},
			data : tulie_date
		},
		itemStyle : {
			normal : {
				color : new echarts.graphic.LinearGradient(0, 0, 0, 1, [ {
					offset : 1,
					color : '#00bfa5'
				} ])
			}
		},
		series : [ {
			name : '得票数',
			type : 'bar',
			stack : '总量',
			label : {
				normal : {
					show : true,
					formatter : '{b}'
				}
			},
			data : shuju_date
		} ]
	};
	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
	window.onresize = myChart.resize;
}

/*******************************************************************************
 * 初始化月得票数 柱状图
 * 
 * @returns
 */
function initChart(empDate) {
	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('main_tu'));
	var tulie_date = new Array();
	var shuju_date = new Array();
	for (var i = 0; i < empDate.length; i++) {
		tulie_date[i] = empDate[i].empName;
		shuju_date[i] = empDate[i].votedNum;
	}

	var labelRight = {
		normal : {
			position : 'right'
		}
	};
	option = {
		title : {
			text : '本月员工得票情况',
			subtext : '本月1日开始情况'
		},
		tooltip : {
			trigger : 'axis',
			axisPointer : { // 坐标轴指示器，坐标轴触发有效
				type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		grid : {
			top : 130,
			bottom : 30
		},
		xAxis : {
			type : 'value',
			position : 'top',
			splitLine : {
				lineStyle : {
					type : 'dashed'
				}
			},
		},
		yAxis : {
			type : 'category',
			axisLine : {
				show : true
			},
			axisLabel : {
				show : true
			},
			axisTick : {
				show : true
			},
			splitLine : {
				show : true
			},
			data : tulie_date
		},
		itemStyle : {
			normal : {
				color : new echarts.graphic.LinearGradient(0, 0, 0, 1, [ {
					offset : 1,
					color : '#00bfa5'
				} ])
			}
		},
		series : [ {
			name : '得票数',
			type : 'bar',
			stack : '总量',
			label : {
				normal : {
					show : true,
					formatter : '{b}'
				}
			},
			data : shuju_date
		} ]
	};
	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
	window.onresize = myChart.resize;
}

/*******************************************************************************
 * 初始化月得票数 南丁格尔图
 * 
 * @returns
 */
function initChartAngle(empDate) {

	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('main_tu'));
	var dete_json = [];
	var row;
	for (var i = 0; i < empDate.length; i++) {
		row = {
			value : empDate[i].votedNum,
			name : empDate[i].empName + ":  " + empDate[i].votedNum + "票"
		}
		dete_json.push(row);
	}
	var option = {
		backgroundColor : '#2c343c',
		// 颜色渐变
		// visualMap : {
		// show : false,
		// min : 0,
		// max : 40,
		// inRange : {
		// colorLightness : [ 0, 6 ]
		// }
		// },
		series : [ {
			name : '访问来源',
			type : 'pie',
			radius : '80%',
			radius : [ 80, 310 ],
			data : dete_json,
			roseType : 'angle',
			label : {
				normal : {
					textStyle : {
						color : 'rgba(255, 255, 255, 0.3)'
					}
				}
			},
			itemStyle : {
				normal : {
					color : '#c23531',
					shadowBlur : 200,
					shadowColor : 'rgba(0, 0, 0, 0.5)'
				}
			}
		} ]
	};
	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
	window.onresize = myChart.resize;
}
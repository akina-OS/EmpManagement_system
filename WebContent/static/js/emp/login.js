
/*******************************************************************************
 * 表单验证
 * 
 * @returns
 */
function checkIn() {
	var empname = $("#emp_name").val();
	var empwd = $("#emp_pwd").val();
	if (empname == "" || empname == null) {
		Materialize.toast('请填写姓名!', 4000);
		return false;
	} else if (empwd == "" || empwd == null) {
		Materialize.toast('请填写密码!', 4000);
		return false;
	}
	return true;
}

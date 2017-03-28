/*******************************************************************************
 * 表单验证
 * 
 * @returns
 */
function checkIn() {
	var admname = $("#adm_name").val();
	var admpwd = $("#adm_pwd").val();
	if (admname == "" || admname == null) {
		Materialize.toast('请填写管理员账号!', 4000);
		return false;
	} else if (admpwd == "" || admpwd == null) {
		Materialize.toast('请填写密码!', 4000);
		return false;
	}
	return true;
}

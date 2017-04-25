window.onload=function(){
	isLogin();
	var username = window.sessionStorage.getItem("username")
	sendQueryInfo(username, 6);
}
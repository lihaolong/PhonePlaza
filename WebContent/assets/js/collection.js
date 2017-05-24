window.onload=function(){
	isLogin();
	var username = window.sessionStorage.getItem("username")
	sendQueryInfo(username, 6);
	alert(username);
	$.ajax({
		url:"/PhonePlaza/control/GetCollectionURLServlet?username="+username,
		type:"get",
		success:function(msg){
			alert(msg);
			updateSearchURL(msg);
		}
	})
}
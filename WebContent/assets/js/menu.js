//判断是否登录
function isLogin(){
	if (window.sessionStorage.getItem("username")) {
		document.getElementById("login").style.display = "none";
		document.getElementById("menu").style.display = "block";
		document.getElementById("username").innerText = window.sessionStorage
				.getItem("username");
	} else {
		document.getElementById("menu").style.display = "none";
		document.getElementById("login").style.display = "block";
	}
}

// 退出当前用户
function exit() {
	window.sessionStorage.clear();
	window.location = "index.html";
}
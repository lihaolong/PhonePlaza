window.onload = function() {
	// alert("clear");
	clearWarning();
}

function clearWarning() {
	document.getElementById("username-warning").innerText = " ";
	document.getElementById("pass-warning").innerText = " ";
	document.getElementById("login-warning").innerText = " ";
}

function loginsure() {
	clearWarning();
	var username = document.getElementById("username").value.toString();
	var password = document.getElementById("password").value.toString();
	var isuser, ispass, issure, issame;
	// alert(username);
	if (username == "") {
		document.getElementById("username-warning").innerText = "用户名为空";
	} else if (username.length < 6) {
		document.getElementById("username-warning").innerText = "用户名小于6个字符";
	} else if (username.length > 20) {
		document.getElementById("username-warning").innerText = "用户名大于20个字符";
	} else {
		isuser = true;
	}
	if (password == "") {
		document.getElementById("pass-warning").innerText = "密码为空";
	} else if (password.length < 6) {
		document.getElementById("pass-warning").innerText = "密码小于6个字符";
	} else if (password.length > 12) {
		document.getElementById("pass-warning").innerText = "用户名大于12个字符";
	} else {
		ispass = true;
	}
	if (isuser && ispass) {
		$.ajax({
			url : "/PhonePlaza/control/DologinServlet",
			type : "post",
			data : {
				"username" : username,
				"password" : password
			},
			success : function(data) {
				alert(data);
				var json = JSON.parse(data);
				if(json.islogin){
					window.sessionStorage.setItem("username", json.username);
					window.open("index.html");
				}else{
					document.getElementById("login-warning").innerText = "用户名或密码错误";
				}
			},
			async:true
		})
	}
}
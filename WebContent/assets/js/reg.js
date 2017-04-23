window.onload = function() {
	// alert("clear");
	clearWarning();
}

function clearWarning() {
	document.getElementById("username-warning").innerText = " ";
	document.getElementById("pass-warning").innerText = " ";
	document.getElementById("sure-warning").innerText = " ";
}

function regsure() {
	clearWarning();
	var username = document.getElementById("username").value.toString();
	var password = document.getElementById("password").value.toString();
	var passsure = document.getElementById("passsure").value.toString();
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
	if (passsure == "") {
		document.getElementById("sure-warning").innerText = "确认密码为空";
	}else if (password != passsure) {
		document.getElementById("sure-warning").innerText = "密码不一致";
	} else {
		issame = true;
	}
	if(isuser&&ispass&&issame){
		//alert("yes");
		/*var xhr = new XMLHttpRequest();
		xhr.open("post", "/PhonePlaza/control/LoginServlet", false);
		xhr.send({"username":username,"password":password});*/
		$.ajax({url:"/PhonePlaza/control/LoginServlet",
			type:"post",
			data:{"username":username,"password":password},
			success:function(){
				//alert("login");
				window.location="login.html";
			}
		})
	}
}
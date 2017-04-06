//ajax传递参数
function sendId(id) {
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.open("GET", "/PhonePlaza/control/PhoneServlet?phoneName=" + id,
			true);
	xmlhttp.send();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

			var msg = xmlhttp.responseText;
			alert(msg);
			window.open("phoneinfo.html");
			window.sessionStorage.setItem("phoneinfo", msg);
		}
	}
}
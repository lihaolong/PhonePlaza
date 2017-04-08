//ajax传递参数
function sendId(id) {
	var xmlhttp = new XMLHttpRequest();;
	xmlhttp.open("GET", "/PhonePlaza/control/PhoneServlet?phoneName=" + id,
			true);
	xmlhttp.send();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

			var msg = xmlhttp.responseText;
			//alert(msg);
			window.sessionStorage.setItem("phoneinfo", msg);
			alert(window.sessionStorage.getItem("phoneinfo"));
			window.open("phoneinfo.html");
		}
	}
}
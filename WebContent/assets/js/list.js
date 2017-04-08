function sendQueryInfo(info1, info2) {
	xmlHttp = new XMLHttpRequest();
	xmlHttp.open("Get", "/PhonePlaza/control/PhoneQueryServlet?info1=" + info1
			+ "&info2=123", true);
	xmlHttp.send();
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			var msg = xmlHttp.responseText;
			alert(msg);
			creatLi(msg);
		}
	}
}

var creatLi = function(phoneName) {
	var json = 
		[ {
			"phonename" : "iphone7",
			"price" : "5888",
			"selltime" : "2016-9-20"
		}, {
			"phonename" : "mate9",
			"price" : "4888",
			"selltime" : "2016-6-20"
		}, {
			"phonename" : "iphone7p",
			"price" : "3888",
			"selltime" : "2016-1-20"
		} ];
	for (var phonename in json) {
		var div = document.getElementById("phone-ul");
		var li1 = document.createElement("li");
		var a1 = document.createElement("a");
		var img1 = document.createElement("img");
		div.appendChild(li1);
		li1.appendChild(a1);
		a1.appendChild(img1);
		img1.setAttribute("src", "assets/img/phoneimg/" + json[phonename].phonename + ".jpg");
		a1.setAttribute("id", phoneName);
		a1.addEventListener("click", (function() {
			return sendId(phoneName);
		}), false);
	}
}
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

var creatLi = function(json) {
	/*
	 * var json = [ { "phonename" : "iphone7", "price" : "5888", "selltime" :
	 * "2016-9-20" }, { "phonename" : "mate9", "price" : "4888", "selltime" :
	 * "2016-6-20" }, { "phonename" : "iphone7p", "price" : "3888", "selltime" :
	 * "2016-1-20" } ];
	 */
	jsonp = JSON.parse(json);
	var ul = document.getElementById("phone-ul");
	var i = 0;
	for (var phonename in jsonp) {
		i++;
		var jsonitem = jsonp[phonename].phonename;
		var li1 = document.createElement("li");
		var img1 = document.createElement("img");
		ul.appendChild(li1);
		li1.appendChild(img1);
		img1.setAttribute("src", "assets/img/phoneimg/" + jsonitem + ".jpg");
		li1.setAttribute("id", jsonitem);
		//alert(jsonitem);
		(function (jsonitem) {
            li1.addEventListener("click", function(){
            	alert(jsonitem);
            	return sendId(jsonitem);
            }, false)	
        })(jsonitem);
	}
}
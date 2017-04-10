window.onload = function() {
	sendQueryInfo("all", 2);
}

function sendQueryInfo(info1, info2) {
	xmlHttp = new XMLHttpRequest();
	xmlHttp.open("Get", "/PhonePlaza/control/PhoneQueryServlet?info1=" + info1
			+ "&info2=" + info2, true);
	xmlHttp.send();
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			var msg = xmlHttp.responseText;
			alert(msg);
			//jsonp = JSON.parse(msg);
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
	while (ul.hasChildNodes()) {
		ul.removeChild(ul.firstChild);
	}
	for ( var phonename in jsonp) {
		var jsonitem = jsonp[phonename].phonename;
		var jsonprice = jsonp[phonename].price;
		var li1 = document.createElement("li");
		var img1 = document.createElement("img");
		ul.appendChild(li1);
		li1.appendChild(img1);
		img1.setAttribute("src", "assets/img/phonelist/" + jsonitem + ".jpg");
		li1.setAttribute("id", jsonitem);
		li1.setAttribute("phonename",jsonitem);
		li1.setAttribute("price", jsonprice);
		!(function(jsonitem) {
			li1.addEventListener("click", function() {
				alert(jsonitem);
				return sendId(jsonitem);
			}, false)
		})(jsonitem);
	}
}
function sortByprice() {
	var ul = document.getElementById("phone-ul");
	alert("clicked");
	alert(ul.childNodes[1].getAttribute("phonename"));
	var arr=[];
	for(var i=0;i<$('#phone-ul').children().length;i++){
		arr[i] = ul.childNodes[i];
	}
    arr.sort(function(li1,li2){
        var n1=parseInt(li1.getAttribute("price"));
        var n2=parseInt(li2.getAttribute("price"));   
        return n1-n2;  
    })
    for(var i=0;i<arr.length;i++){
    	ul.appendChild(arr[i]);
    }
}
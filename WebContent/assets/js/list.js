window.onload = function() {
	sendQueryInfo("all", 2);
	var compborder = document.getElementById("compare-frame");
	compborder.style.border = "none";
}

function sendQueryInfo(info1, info2, info3) {
	xmlHttp = new XMLHttpRequest();
	xmlHttp.open("Get", "/PhonePlaza/control/PhoneQueryServlet?info1=" + info1
			+ "&info2=" + info2 + "&info3=" + info3, true);
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


function creatFrame(phonename) {
	alert(phonename);
	var ul1 = document.getElementById("compare-ul");
	var compborder = document.getElementById("compare-frame");
	compborder.style.border = "1px solid rgba(0,0,0,0.1)";
	if (ul1.childNodes.length < 4 || (!ul1.hasChildNodes())) {
		var pic1 = document.createElement("img");
		pic1.setAttribute("src", "assets/img/phonelist/" + phonename + ".jpg");
		ul1.appendChild(pic1);
		pic1.setAttribute("phonename", phonename);
	}
	alert(ul1.childNodes.length);
if (ul1.childNodes.length == 3) {
	var span = document.createElement("span");
	span.innerHTML = "对比";
	ul1.appendChild(span);
	(function() {
		span.addEventListener("click", function() {
			jumptocomp();
		}, false)
	})();
}
}
function jumptocomp() {
	var ul = document.getElementById("compare-ul");
	alert(ul.childNodes[2].getAttribute("phonename"));
}


var creatLi = function(json) {
	jsonp = JSON.parse(json);
	var ul = document.getElementById("phone-ul");
	while (ul.hasChildNodes()) {
		ul.removeChild(ul.firstChild);
	}
	for ( var phonename in jsonp) {
		var jsonitem = jsonp[phonename].phonename;
		var jsonprice = jsonp[phonename].price;
		var jsonselltime = jsonp[phonename].selltime;
		var li1 = document.createElement("li");
		var img1 = document.createElement("img");
		var p1 = document.createElement("span");
		var p2 = document.createElement("span");
		ul.appendChild(li1);
		li1.appendChild(img1);
		li1.appendChild(p1);
		li1.appendChild(p2);
		p1.innerHTML = jsonp[phonename].phonename;
		p2.style.height = "20px";
		p2.style.color = "blue";
		p2.style.display = "none";
		p2.innerHTML = "加入对比";
		img1.setAttribute("src", "assets/img/phonelist/" + jsonitem + ".jpg");
		li1.setAttribute("id", jsonitem);
		li1.setAttribute("phonename", jsonitem);
		li1.setAttribute("price", jsonprice);
		li1.setAttribute("selltime", jsonselltime);
		!(function(jsonitem) {
			img1.addEventListener("click", function() {
				alert(jsonitem);
				return sendId(jsonitem);
			}, false)
		})(jsonitem);
		!(function(p2) {
			li1.addEventListener("mouseover", function() {
				p2.style.display = "block";
			}, false)
		})(p2);
		!(function(p2) {
			li1.addEventListener("mouseleave", function() {
				p2.style.display = "none";
			}, false)
		})(p2);
		!(function(p2, jsonitem) {
			p2.addEventListener("click", function() {
				alert("span click");
				creatFrame(jsonitem);
			}, false)
		})(p2, jsonitem);
	}
}
function sortByprice() {
	var ul = document.getElementById("phone-ul");
	alert(ul.childNodes[1].getAttribute("price"));
	var arr = [];
	for (var i = 0; i < $('#phone-ul').children().length; i++) {
		arr[i] = ul.childNodes[i];
	}
	arr.sort(function(li1, li2) {
		var n1 = parseInt(li1.getAttribute("price"));
		var n2 = parseInt(li2.getAttribute("price"));
		return n1 - n2;
	})
	for (var i = 0; i < arr.length; i++) {
		ul.appendChild(arr[i]);
	}
}

function sortByselltime() {
	var ul = document.getElementById("phone-ul");
	alert(ul.childNodes[1].getAttribute("selltime"));
	var arr = [];
	for (var i = 0; i < $('#phone-ul').children().length; i++) {
		arr[i] = ul.childNodes[i];
	}
	arr.sort(function(li1, li2) {
		var n1 = Date.parse(li1.getAttribute("selltime"));
		var n2 = Date.parse(li2.getAttribute("selltime"));
		return n1 - n2;
	})
	for (var i = 0; i < arr.length; i++) {
		ul.appendChild(arr[i]);
	}
}
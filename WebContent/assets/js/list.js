//文档加载后向servlet发送获得所有phonename，selltime，price的请求
window.onload = function() {
	sendQueryInfo("all", 2);
	var compborder = document.getElementById("compare-frame");
	compborder.style.border = "none";
	isLogin();
}
//发送获得phonename，selltime，price的请求到servlet
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

//创建侧边手机对比框
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
//跳转到手机对比页
function jumptocomp() {
	var ul = document.getElementById("compare-ul");
	var phonename = ul.childNodes[1].getAttribute("phonename");
	var phonenameother = ul.childNodes[2].getAttribute("phonename");
	xmlHttp = new XMLHttpRequest();
	xmlHttp.open("Get", "/PhonePlaza/control/PhoneServlet?phoneName=" + phonename
			+ "&phoneNameother=" + phonenameother, true);
	xmlHttp.send();
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			var msg = xmlHttp.responseText;
			alert(msg);
			window.sessionStorage.setItem("compareinfo", msg);
			window.open("phonecompare.html");
		}
	}
}

//循环创建手机显示的li和img标签
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
		var p2 = document.createElement("a");
		var p3 = document.createElement("a");
		ul.appendChild(li1);
		li1.appendChild(img1);
		li1.appendChild(p1);
		li1.appendChild(p2);
		li1.appendChild(p3);
		p1.innerHTML = jsonp[phonename].phonename;
		p2.style.height = "20px";
		p2.style.color = "blue";
		p2.style.display = "none";
		p2.innerHTML = "加入对比";
		p3.style.height = "20px";
		p3.style.color = "blue";
		p3.style.display = "none";
		p3.innerHTML = "添加收藏";
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
		!(function(p2,p3) {
			li1.addEventListener("mouseover", function() {
				p2.style.display = "block";
				p3.style.display = "block";
			}, false)
		})(p2,p3);
		!(function(p2,p3) {
			li1.addEventListener("mouseleave", function() {
				p2.style.display = "none";
				p3.style.display = "none";
			}, false)
		})(p2,p3);
		!(function(p2, jsonitem) {
			p2.addEventListener("click", function() {
				creatFrame(jsonitem);
			}, false)
		})(p2, jsonitem);
		!(function(p3, jsonitem) {
			p3.addEventListener("click", function() {
				sendUserPhone(jsonitem);
			}, false)
		})(p3, jsonitem);
	}
}
//发送用户收藏手机信息
function sendUserPhone(phonename){
	var username;
	if(window.sessionStorage.getItem("username")){
		username=window.sessionStorage.getItem("username");
		$.ajax({
			url:"/PhonePlaza/control/UserPhoneServlet",
			data:{"username":username,"phonename":phonename},
			type:"post",
			async:true,
			success:function(data){
				var msg = JSON.parse(data);
				if(msg.isexit){
					alert("此手机已经在您的收藏列表")
				}if(!msg.isexit){
					alert("收藏成功");
				}
			}
		})
	}else{
		alert("请您先登录");
	}
}

//根据price排序
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
//根据selltime排序
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
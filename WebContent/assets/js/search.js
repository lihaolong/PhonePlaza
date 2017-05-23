window.onload = function(){
	var text = window.sessionStorage.getItem("text");
	alert(text);
	document.getElementById("text").innerHTML = text;
	sendsearch(text);
	searchURL(text);
	isLogin();
}


//发送搜索信息到后台
function sendsearch(text){
	$.ajax({
		url:"/PhonePlaza/control/SearchServlet?text="+text,
		type: "get",
		success: function(msg){
			//alert(msg);
			if(msg.length>3)
				creatLi(msg,1);
			else{
				noURL();
			}
		}
	})
}
//查询url
function searchURL(text){
	$.ajax({
		url:"/PhonePlaza/control/GetUrlInfoServlet",
		type: "post",
		data:{"phonename":text},
		success:function(msg){
			alert(msg);
			updateSearchURL(msg);
		}
	})
}

//生成URL标签
function updateSearchURL(json){
	var jsonp = JSON.parse(json);
	var divwrapper = document.getElementById("news-wrapper");
	for(var url in jsonp){
		var div = document.createElement("div");
		div.setAttribute("class", "hotnews01");
		var p = document.createElement("p");
		p.setAttribute("class", "pic01");
		var a = document.createElement("a");
		a.setAttribute("href", jsonp[url].url);
		var img = document.createElement("img");
		img.setAttribute("src", "assets/img/hotnews/zol01.jpg");
		div.appendChild(p);
		p.appendChild(a);
		a.appendChild(img);
		var h3 = document.createElement("h3");
		var a1 = document.createElement("a");
		a1.setAttribute("class", "title01");
		a1.setAttribute("href", jsonp[url].url);
		h3.innerHTML = jsonp[url].title;
		h3.appendChild(a1);
		div.appendChild(h3);
		var p1 = document.createElement("p");
		p1.setAttribute("class", "pra");
		p1.innerHTML = jsonp[url].para;
		div.appendChild(p1);
		var time = document.createElement("span");
		time.setAttribute("class", "time");
		time.innerHTML = jsonp[url].time;
		div.appendChild(time);
		divwrapper.appendChild(div);
	}
}

//未搜索到相关信息
function noURL(){
	var h1 = document.getElementById("aboutphone");
	h1.innerHTML = "未找到有关"+window.sessionStorage.getItem("text")+"的手机信息";
	h1.style.color = "red";
	var h2 = document.getElementById("abouturl");
	h2.innerHTML = "未找到有关"+window.sessionStorage.getItem("text")+"的评测信息";
	h2.style.color = "red";
}
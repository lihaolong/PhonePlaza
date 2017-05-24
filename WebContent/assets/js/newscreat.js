
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
		a.target = "_blank";
		var img = document.createElement("img");
		img.setAttribute("src", "assets/img/hotnews/zol01.jpg");
		div.appendChild(p);
		p.appendChild(a);
		a.appendChild(img);
		var h3 = document.createElement("h3");
		var a1 = document.createElement("a");
		a1.setAttribute("class", "title01");
		a1.href = jsonp[url].url;
		a1.innerHTML = jsonp[url].title;
		a1.target = "_blank";
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

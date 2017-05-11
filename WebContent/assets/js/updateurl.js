//更新热门评测
function updateURL(msg){
	jsonp = JSON.parse(msg);
	var i = 0;
	alert(msg);
	for(var url in jsonp){
		i++;
		var title = document.getElementById("title"+i);
		var time = document.getElementById("time"+i);
		var para = document.getElementById("para"+i);
		title.innerHTML = jsonp[url].title;
		title.href = jsonp[url].url;
		time.innerHTML = jsonp[url].time;
		para.innerHTML = jsonp[url].para;
	}
}
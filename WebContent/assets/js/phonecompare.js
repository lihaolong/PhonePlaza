//页面加载完成后执行
window.onload = function() {
	var compareinfo = window.sessionStorage.getItem("compareinfo");
	alert(compareinfo);
	var jsonp = JSON.parse(compareinfo);
	var i = 0;
	// 循环添加对比信息
	for ( var phonename in jsonp) {
		i++;
		document.getElementById("selltime" + i).innerHTML = jsonp[phonename].selltime;
		document.getElementById("weight" + i).innerHTML = jsonp[phonename].weight;
		document.getElementById("thickness" + i).innerHTML = jsonp[phonename].thickness;
		document.getElementById("ram" + i).innerHTML = jsonp[phonename].ram;
		document.getElementById("rom" + i).innerHTML = jsonp[phonename].rom;
		document.getElementById("price" + i).innerHTML = jsonp[phonename].price;
		document.getElementById("screensize" + i).innerHTML = jsonp[phonename].screensize;
		document.getElementById("screenpx" + i).innerHTML = jsonp[phonename].screenpx;
		document.getElementById("screenmaterial" + i).innerHTML = jsonp[phonename].screenmaterial;
		document.getElementById("cpuname" + i).innerHTML = jsonp[phonename].cpuname;
		document.getElementById("cpubrand" + i).innerHTML = jsonp[phonename].cpubrand;
		document.getElementById("cpuhz" + i).innerHTML = jsonp[phonename].cpuhz;
		document.getElementById("cpucorenum" + i).innerHTML = jsonp[phonename].cpucorenum;
		document.getElementById("gpu" + i).innerHTML = jsonp[phonename].gpu;
		document.getElementById("cpuprocess" + i).innerHTML = jsonp[phonename].cpuprocess;
		document.getElementById("cameraname" + i).innerHTML = jsonp[phonename].cameraname;
		document.getElementById("camerapx" + i).innerHTML = jsonp[phonename].camerapx;
		document.getElementById("sperture" + i).innerHTML = jsonp[phonename].sperture;
		if (jsonp[phonename].supportois == 1) {
			document.getElementById("supportois" + i).innerHTML = "支持";
		} else {
			document.getElementById("supportois" + i).innerHTML = "不支持";
		}
		var phonenameinsrc = jsonp[phonename].phonename;
		var phonepic = document.getElementById("phoneimg" + i);
		phonepic.src = "assets/img/phonelist/" + phonenameinsrc + ".jpg";
		(function(phonenameinsrc) {
			phonepic.addEventListener("click", function() {
				return sendId(phonenameinsrc);
			}, false)
		})(phonenameinsrc);
		document.getElementById("phonenamespan" + i).innerHTML = jsonp[phonename].phonename;
		document.getElementById("pricespan" + i).innerHTML = jsonp[phonename].price;
	}
}
/*
 * jQuery(document).ready(function() { var compareinfo =
 * window.sessionStorage.getItem("compareinfo"); alert(compareinfo);
 * alert("onload"); });
 */
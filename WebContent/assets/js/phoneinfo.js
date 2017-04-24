jQuery(document).ready(function() {
	isLogin();
	var json = window.sessionStorage.getItem("phoneinfo");
	jsonp = JSON.parse(json);
	document.getElementById("selltime").innerHTML = jsonp.selltime;
	document.getElementById("weight").innerHTML = jsonp.weight;
	document.getElementById("thickness").innerHTML = jsonp.thickness;
	document.getElementById("ram").innerHTML = jsonp.ram;
	document.getElementById("rom").innerHTML = jsonp.rom;
	document.getElementById("price").innerHTML = jsonp.price;
	document.getElementById("screensize").innerHTML = jsonp.screensize;
	document.getElementById("screenpx").innerHTML = jsonp.screenpx;
	document.getElementById("screenmaterial").innerHTML = jsonp.screenmaterial;
	document.getElementById("cpuname").innerHTML = jsonp.cpuname;
	document.getElementById("cpubrand").innerHTML = jsonp.cpubrand;
	document.getElementById("cpuhz").innerHTML = jsonp.cpuhz;
	document.getElementById("cpucorenum").innerHTML = jsonp.cpucorenum;
	document.getElementById("gpu").innerHTML = jsonp.gpu;
	document.getElementById("cpuprocess").innerHTML = jsonp.cpuprocess;
	document.getElementById("cameraname").innerHTML = jsonp.cameraname;
	document.getElementById("camerapx").innerHTML = jsonp.camerapx;
	document.getElementById("sperture").innerHTML = jsonp.sperture;
	if(jsonp.supportois==1){
	document.getElementById("supportois").innerHTML = "支持";
	}else{
		document.getElementById("supportois").innerHTML = "不支持";
	}
	var phonenameinsrc = jsonp.phonename;
	var phonepic = document.getElementById("phoneimg");
	phonepic.src = "assets/img/phonelist/"+phonenameinsrc+".jpg";
});
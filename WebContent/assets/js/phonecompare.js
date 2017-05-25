//页面加载完成后执行
window.onload = function() {
	isLogin();
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
		document.getElementById("battery" + i).innerHTML = jsonp[phonename].baterry;
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
		document.getElementById("phonenamespan" + i).innerHTML = jsonp[phonename].phonebrand+jsonp[phonename].phonename;
		document.getElementById("pricespan" + i).innerHTML = jsonp[phonename].price;
	}
	compareresult();
}

function compareresult(){
	//比较发布日期
	var selltime1 = $("#selltime1").text();
	var selltime2 = $("#selltime2").text();
	if((new Date(selltime1.replace(/-/g,"\/"))) > (new Date(selltime2.replace(/-/g,"\/")))){
		$("#selltimecomresult2").text("    ");
		$("#selltimeresult2").text("机型较新");
	}else if((new Date(selltime1.replace(/-/g,"\/"))) < (new Date(selltime2.replace(/-/g,"\/")))){
		$("#selltimecomresult1").text("    ");
		$("#selltimeresult2").text("机型较新");
	}else{
		$("#selltimecomresult1").text("    ");
		$("#selltimecomresult2").text("    ");
	}
	//比较重量
	var weight1 = $("#weight1").text();
	var weight2 = $("#weight2").text();
	if(weight1>weight2){
		$("#weightcomresult1").text("    ");
		$("#weightresult2").text("机身更轻");
	}else if(weight1<weight2){
		$("#weightcomresult2").text("    ");
		$("#weightresult1").text("机身更轻");
	}else{
		$("#weightcomresult1").text("    ");
		$("#weightcomresult2").text("    ");
	}

	var thickness1 = $("#thickness1").text();
	var thickness2 = $("#thickness2").text();
	if(thickness1>thickness2){
		$("#thicknesscomresult1").text("    ");
		$("#thicknessresult2").text("机身更薄");
	}else if(thickness1<thickness2){
		$("#thicknesscomresult2").text("    ");
		$("#thicknessresult1").text("机身更薄");
	}else{
		$("#thicknesscomresult1").text("    ");
		$("#thicknesscomresult2").text("    ");
	}
	
	var battery1 = $("#battery1").text();
	var battery2 = $("#battery2").text();
	if(battery1>battery2){
		$("#batterycomresult2").text("    ");
		$("#batteryresult1").text("电池容量更大");
	}else if(battery1<battery2){
		$("#batterycomresult1").text("    ");
		$("#batteryresult2").text("电池容量更大");
	}else{
		$("#batterycomresult1").text("    ");
		$("#batterycomresult2").text("    ");
	}
	
	var ram1 = $("#ram1").text();
	var ram2 = $("#ram2").text();
	if(ram1>ram2){
		$("#ramcomresult2").text("    ");
		$("#ramresult1").text("运行内存更大");
	}else if(ram1<ram2){
		$("#ramcomresult1").text("    ");
		$("#ramresult2").text("运行内存更大");
	}else{
		$("#ramcomresult1").text("    ");
		$("#ramcomresult2").text("    ");
	}
	
	var rom1 = $("#rom1").text();
	var rom2 = $("#rom2").text();
	if((rom1>rom2||rom1=="128GB")&&rom1!=rom2){
		$("#romcomresult2").text("    ");
		$("#romresult1").text("机身内存更大");
	}else if((ram1<ram2||rom2=="128GB")&&rom1!=rom2){
		$("#romcomresult1").text("    ");
		$("#romresult2").text("机身内存更大");
	}else{
		$("#romcomresult1").text("    ");
		$("#romcomresult2").text("    ");
	}
	
	var price1 = $("#price1").text();
	var price2 = $("#price2").text();
	if(price1>price2){
		$("#pricecomresult1").text("    ");
		$("#priceresult2").text("价格更低");
	}else if(price1<price2){
		$("#pricecomresult2").text("    ");
		$("#priceresult1").text("价格更低");
	}else{
		$("#pricecomresult1").text("    ");
		$("#pricecomresult2").text("    ");
	}
	
	var screensize1 = $("#screensize1").text();
	var screensize2 = $("#screensize2").text();
	if(screensize1>screensize2){
		$("#screensizecomresult2").text("    ");
		$("#screensizeresult1").text("屏幕更大");
	}else if(screensize1<screensize2){
		$("#screensizecomresult1").text("    ");
		$("#screensizeresult2").text("屏幕更大");
	}else{
		$("#screensizecomresult1").text("    ");
		$("#screensizecomresult2").text("    ");
	}
	
	var screenpx1 = $("#screenpx1").text();
	var screenpx2 = $("#screenpx2").text();
	if(screenpx1>screenpx2){
		$("#screenpxcomresult2").text("    ");
		$("#screenpxresult1").text("屏幕像素更高");
	}else if(screenpx1<screenpx2){
		$("#screenpxcomresult1").text("    ");
		$("#screenpxresult2").text("屏幕像素更高");
	}else{
		$("#screenpxcomresult1").text("    ");
		$("#screenpxcomresult2").text("    ");
	}
	
	var cpucorenum1 = $("#cpucorenum1").text();
	var cpucorenum2 = $("#cpucorenum2").text();
	if(parseInt(cpucorenum1)>parseInt(cpucorenum2)){
		$("#cpucorenumcomresult2").text("    ");
		$("#cpucorenumresult1").text("CPU核芯数更多");
	}else if(parseInt(cpucorenum1)<parseInt(cpucorenum2)){
		$("#cpucorenumcomresult1").text("    ");
		$("#cpucorenumresult2").text("CPU核芯数更多");
	}else{
		$("#cpucorenumcomresult1").text("    ");
		$("#cpucorenumcomresult2").text("    ");
	}
	
	var cpuhz1 = $("#cpuhz1").text();
	var cpuhz2 = $("#cpuhz2").text();
	if(cpuhz1>cpuhz2){
		$("#cpuhzcomresult2").text("    ");
		$("#cpuhzresult1").text("CPU频率更高");
	}else if(cpuhz1<cpuhz2){
		$("#cpuhzcomresult1").text("    ");
		$("#cpuhzresult2").text("CPU频率更高");
	}else{
		$("#cpuhzcomresult1").text("    ");
		$("#cpuhzcomresult2").text("    ");
	}
	
	var cpuprocess1 = $("#cpuprocess1").text();
	var cpuprocess2 = $("#cpuprocess2").text();
	if(cpuprocess1>cpuprocess2){
		$("#cpuprocesscomresult1").text("    ");
		$("#cpuprocessresult2").text("CPU制程更先进");
	}else if(cpuprocess1<cpuprocess2){
		$("#cpuprocesscomresult2").text("    ");
		$("#cpuprocessresult1").text("CPU制程更先进");
	}else{
		$("#cpuprocesscomresult1").text("    ");
		$("#cpuprocesscomresult2").text("    ");
	}
	
	var camerapx1 = $("#camerapx1").text();
	var camerapx2 = $("#camerapx2").text();
	if(camerapx1>camerapx2){
		$("#camerapxcomresult2").text("    ");
		$("#camerapxresult1").text("相机像素更高");
	}else if(camerapx1<camerapx2){
		$("#camerapxcomresult1").text("    ");
		$("#camerapxresult2").text("相机像素更高");
	}else{
		$("#camerapxcomresult1").text("    ");
		$("#camerapxcomresult2").text("    ");
	}
	
	var sperture1 = $("#sperture1").text();
	var sperture2 = $("#sperture2").text();
	if(sperture1>sperture2){
		$("#sperturecomresult2").text("    ");
		$("#spertureresult1").text("相机光圈更大");
	}else if(sperture1<sperture2){
		$("#sperturecomresult1").text("    ");
		$("#spertureresult2").text("相机光圈更大");
	}else{
		$("#sperturecomresult1").text("    ");
		$("#sperturecomresult2").text("    ");
	}
	
	var supportois1 = $("#supportois1").text();
	var supportois2 = $("#supportois2").text();
	
	if(supportois1=="不支持"){
		$("#supportoiscomresult1").text("    ");
	}else{
		$("#supportoisresult1").text("相机拍照更稳定");
	}
	if(supportois2=="不支持"){
		$("#supportoiscomresult2").text("    ");
	}else{
		$("#supportoisresult2").text("相机拍照更稳定");
	}
}
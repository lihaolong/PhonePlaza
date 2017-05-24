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

//未搜索到相关信息
function noURL(){
	var h1 = document.getElementById("aboutphone");
	h1.innerHTML = "未找到有关"+window.sessionStorage.getItem("text")+"的手机信息";
	h1.style.color = "red";
	var h2 = document.getElementById("abouturl");
	h2.innerHTML = "未找到有关"+window.sessionStorage.getItem("text")+"的评测信息";
	h2.style.color = "red";
}
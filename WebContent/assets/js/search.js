window.onload = function(){
	var text = window.sessionStorage.getItem("text");
	alert(text);
	document.getElementById("text").innerHTML = text;
	sendsearch(text);
	searchURL(text);
}


//发送搜索信息到后台
function sendsearch(text){
	$.ajax({
		url:"/PhonePlaza/control/SearchServlet?text="+text,
		type: "get",
		success: function(msg){
			alert(msg);
			creatLi(msg,1);
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
		}
	})
}
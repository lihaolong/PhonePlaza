//文档加载后向servlet发送获得所有phonename，selltime，price的请求
window.onload = function() {
	sendQueryInfo("all", 2);
	var compborder = document.getElementById("compare-frame");
	compborder.style.border = "none";
	isLogin();
}

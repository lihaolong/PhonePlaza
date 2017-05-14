/**
 * 点击搜索后跳转
 */
function searchjump(){
	var text = document.getElementById("search").value;
	window.sessionStorage.setItem("text", text);
	window.open("search.html");
}

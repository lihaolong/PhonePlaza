jQuery(document)
		.ready(
				function() {
					$('.slider')
							.each(
									function() {
										var $this = $(this);
										var $group = $this.find('.slide-group');
										var $slides = $this.find('.slide');
										var buttonArray = [];
										var currentIndex = 0;
										var timeout;

										function move(newIndex) {// 向下一张图片移动的方法
											var animateLeft, slideLeft;

											advance();

											if ($group.is(':animated')
													|| currentIndex === newIndex) {// 如果是当前图片，直接返回，什么也不做
												return;
											}

											buttonArray[currentIndex]
													.removeClass('active'); // 将图片下方的小圆点移到下一个
											buttonArray[newIndex]
													.addClass('active');

											if (newIndex > currentIndex) { // 如果下张图片在当前图片的右边，则向左移动
												slideLeft = '100%';
												animateLeft = '-100%';
											} else {// 反之，向相反方向移动
												slideLeft = '-100%';
												animateLeft = '100%';
											}
											$slides.eq(newIndex).css({
												left : slideLeft,
												display : 'block'
											});// 为新索引指向的图片添加css属性，显示该图片及设置左边位置

											$group.animate({
												left : animateLeft
											}, function() {// 设置动画
												$slides.eq(currentIndex).css({
													display : 'none'
												});// 隐藏当前图片
												$slides.eq(newIndex).css({
													left : 0
												});
												$group.css({
													left : 0
												});
												currentIndex = newIndex;
											});
										}
										function advance() {
											clearTimeout(timeout);
											timeout = setTimeout(
													function() {// setTimeout方法在指定毫秒数后调用函数
														if (currentIndex < ($slides.length - 1)) {// 如果当前不是最后一张图片，则向下一张图片移动
															move(currentIndex + 1);
														} else {// 如果当前是最后一张图片，移到第一张图片
															move(0);
														}
													}, 4000);
										}
										$
												.each(
														$slides,
														function(index) { // 对页面上的每一个图片循环，参数是这个图片的索引，程序从这里开始执行，这段意在添加图片下方的小圆点按钮
															var $button = $('<button type="button" class="slide-btn">•</button>');// 新建按钮元素，存在变量$button，即图片下方的小圆点
															if (index === currentIndex) { // 如果索引与变量当前索引的值相同，这个按钮添加active类，在css中可看到active的行为，灰点变黑
																$button
																		.addClass('active');
															}
															$button
																	.on(
																			'click',
																			function() {// 点击按钮时，执行move函数，即将显示该按钮指向的图片
																				move(index);
																			})
																	.appendTo(
																			'.slide-buttons'); // 将按钮添加到图片下方的类为slide-buttons的div区域内
															buttonArray
																	.push($button); // push()方法向数组末尾添加一个元素
														});
										advance();// 循环播放图片
									});
				})
// 点击阅读数增加
function news01() {
	var x = document.getElementById("news01").innerHTML;
	x = parseInt(x) + 1;
	document.getElementById("news01").innerHTML = x;
}
function news02() {
	var x = document.getElementById("news02").innerHTML;
	x = parseInt(x) + 1;
	document.getElementById("news02").innerHTML = x;
}
function news03() {
	var x = document.getElementById("news03").innerHTML;
	x = parseInt(x) + 1;
	document.getElementById("news03").innerHTML = x;
}
function news04() {
	var x = document.getElementById("news04").innerHTML;
	x = parseInt(x) + 1;
	document.getElementById("news04").innerHTML = x;
}

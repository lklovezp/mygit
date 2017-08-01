// JavaScript Document
$(document).ready(function() {

//初始化布局********************************/
//初始化高度，避免出现垂直滚动条
 function initH(){
	 var winH=$(window).height();
	 var h=winH-152;
	 $("#main").css("height",h+"px");
	 $("#l_part").css("height",h+"px");
	 $("#content").css("height",h+"px");
 }
 initH();
 $(window).resize(function(){initH()});

});//ready--end!


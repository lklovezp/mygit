// JavaScript Document
$(document).ready(function() {

//初始化布局********************************/
//初始化高度，避免出现垂直滚动条
 function initH(){
	 var winW=$(window).width();
	 var winH=$(window).height();
	 var h=winH-62;
	 var bcH=$(".breadClumbs").height();
	 $("#main").css("height",h+"px");
	 $("#l_part").css("height",h+"px");
	 $("#content").css("height",h+"px");
	 $(".home_r").css("width",winW-250+"px");
	 $(".home_l,.home_r").height(winH);
	 $("#conIframe").height(h-bcH);
 }
 initH();
 $(window).resize(function(){initH()});

});//ready--end!


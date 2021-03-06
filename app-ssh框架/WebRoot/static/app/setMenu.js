//初始化一级菜单，能显示全，则不显示右移按钮，显示不全，则显示右移按钮
var leftBtn=$("#nav_l");
var rightBtn=$("#nav_r");
var iframeObj=$("#conIframe");
var navC = $("#nav_c");
var navCWidth;
var navCon = $("#nav_con");
var navConUl=navCon.find("ul");
var navLi;
var navLiLength;
var navLiWidth;
var navHTML="";
leftBtn.hide();
function initLev1(menuJSON){
	//先创建DOM
	var lev1=menuJSON[0].lev1;
	var lev1_len=lev1.length;
	for(var i=0;i<lev1_len;i++){
		if(i==0){//首
		   navHTML+='<li class="'+lev1[i].icon+' first" id="'+lev1[i].id+'" ref="'+lev1[i].href+'" onclick="clickInitLev2(this)"><div class="img"></div><div class="txt">'+lev1[i].name+'</div></li>';
		}else if(i==lev1_len-1){//尾    
			navHTML+='<li class="'+lev1[i].icon+' last" id="'+lev1[i].id+'" onclick="clickInitLev2(this)"><div class="img"></div><div class="txt">'+lev1[i].name+'</div></li>';
		}else{//中间的其它
		   navHTML+='<li class="'+lev1[i].icon+'"  id="'+lev1[i].id+'" onclick="clickInitLev2(this)"><div class="img"></div><div class="txt">'+lev1[i].name+'</div></li>';
		}
	}
	navConUl.append(navHTML);

}
///////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////
var start_left=0;
var cur_left=start_left;//刚开始时，cur_left=start_left;
var start_rest;
//var start_rest=navLiWidth - navCWidth;
var cur_rest;
//如果发生resize事件，则一级菜单定位到0,隐藏左移按钮，重新比较navC与navCon，决定右移菜单是否显示
function setPositon(){
	//隐藏左按钮
	leftBtn.hide();
	//定位到0
	navCon.css({"left":"0px"});
	//比较宽度，决定是否显示右移按钮
	navCWidth=navC.width();
	//console.log("navCWidth "+navCWidth);
	navLi = navCon.find("li");
	navLiLength = navLi.length;
	navLiWidth = (navLiLength-1) * 123 + 91 -1 ;//第一个宽度为90，最后一个无右边框
	//console.log("navLiWidth "+navLiWidth);
	if(navLiWidth<=navCWidth){//够显示完，不显示右移按钮
		rightBtn.hide();
	}else{//显示不完，显示右移按钮,并赋cur_left与cur_rest值
	    rightBtn.show();
		start_left=0;
		cur_left=start_left;
		start_rest=navLiWidth - navCWidth;
		cur_rest=start_rest;
	}
}
//////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////

//无论左右，每走一步，记录一下走后的cur_left与rest值，走步走的都是cur_left的值
//走之前，点击右，比较rest值；点击左，比较cur_left的值
var step_width=200;
function rightEvent(){//点击右按钮，left值变小,根据右侧的cur_rest的值来判断是否走到头了
	if(cur_rest>step_width){//够走一步
	    //记录一下cur_left与rest的新值
		cur_left=cur_left-step_width;
		cur_rest=cur_rest-step_width;
		//console.log("cur_left "+cur_left);
		//走动
		navCon.animate({"left": cur_left+"px"});
		//显示左按钮	
	}else{//不够走一步，也就是最后一步，走了这一步之后要解除右按钮的事件，直至点击了左按钮
	    //记录一下cur_left与rest的新值
		cur_left=cur_left-cur_rest;
		cur_rest=0;
		//console.log("右边走至最后一步 cur_left "+ cur_left);
		//走动
		navCon.animate({"left": cur_left+"px"});
		rightBtn.hide();
	}
	leftBtn.show();	
	//console.log("cur_left "+cur_left +" cur_rest "+cur_rest);
}
function leftEvent(){//点击左按钮，left值变大，根据left属性的值与步长的比较来判断，是否走到头了
	//console.log("点击左侧cur_left "+cur_left);
	if(Math.abs(cur_left)>step_width){//够一步
		//console.log("进入够一步");
		//记录一下cur_left与rest的新值
		cur_left=cur_left+step_width;
		cur_rest=cur_rest+step_width;
		//走动
		navCon.animate({"left": cur_left+"px"});
		//显示右按钮
	}else{//不够一步，直接left值走到start_left处
		//console.log("进入不够一步");
		//记录一下cur_left与rest的新值
		cur_left=start_left;
		cur_rest=start_rest;
		navCon.animate({"left": cur_left+"px"});
		leftBtn.hide();
	}
	rightBtn.show();
	//console.log("cur_left "+cur_left +" cur_rest "+cur_rest);
}
rightBtn.bind("click",rightEvent);
leftBtn.bind("click",leftEvent);


///////////////////////////////////////////////////////////////////////
////////////////初始化二/三级菜单及二/三级菜单事件////////////////////////////////
/////////////////////////////////////////////////////////////////////

//先获取一级菜单谁处于.cur状态
var navCur;
var Href;
function initLev2(menuJSON){
	//遍历json找到纯二级或三级菜单的cur的按顺序查找的第一个
	var lev1=menuJSON[0].lev1;
	var lev1_len=lev1.length;
	var curId=null;//查找到的Cur的id
	var lev1Id=null;//找到的cur对应的一级菜单的id
	for(var i=0;i<lev1_len;i++){//遍历找cur
		var lev2=menuJSON[lev1[i].id];
		if(lev2){//第一个首页是没有二级菜单的
		   lev2=lev2.lev2;
		   var lev2_len=lev2.length;
		   for(var j=0;j<lev2_len;j++){//开始遍历二级菜单，要判断一下，此二级菜单是纯二级还是有三级的
			    var thirdId=lev2[j].id;
				//console.log(thirdId);
				var lev3=menuJSON[thirdId];
				if(lev3==""||lev3==undefined){//没有三级菜单,是纯二级的，判断有没有cur
					if(lev2[j].cur){//如果有cur
					  curId=lev2[j].id;
					  lev1Id=lev1[i].id;
					  break;
					}
				}else{//有三级菜单
				    lev3=lev3.lev3;
					var lev3_len=lev3.length;
					for(var k=0;k<lev3_len;k++){
						if(lev3[k].cur){//如果有cur
						   curId=lev3[k].id;
						   lev1Id=lev1[i].id;
						   break;
						}
					}
				}
		   }
		}
		
	}//for
	//for循环结束后，判断有没有找到
	if(curId==null){//没找到，赋给home
	    lev1Id=lev1[0].id; 
	    var Href=$("#"+lev1Id).attr("ref");
	    createHome(Href);
	}else{//找到了，创建二级
	    lpart.show();
		$("#main").addClass("padding");
		lpart.html("");
		var lev2HTML="";
		var cur_lev2=menuJSON[lev1Id].lev2;
		var cur_lev2_len=cur_lev2.length;
		for(var i=0;i<cur_lev2_len;i++){
			var thirdId=cur_lev2[i].id;
			//console.log(thirdId);
			var lev3=menuJSON[thirdId];
			if(lev3==""||lev3==undefined){//没有三级菜单
				if(cur_lev2[i].id==curId){//是，要加cur类
				   lev2HTML+='<p class="onlySecond cur" id="'+cur_lev2[i].id+'" parentId="'+lev1Id+'" ref="'+cur_lev2[i].href +'" onclick="loadURL(this)"><a>'+cur_lev2[i].name+'</a></p>';
				}else{//无需要加cur
				   lev2HTML+='<p class="onlySecond" id="'+cur_lev2[i].id+'" parentId="'+lev1Id+'" ref="'+cur_lev2[i].href +'" onclick="loadURL(this)"><a>'+cur_lev2[i].name+'</a></p>';
				}
			}else{//有三级菜单
				lev2HTML+='<div class="thirdParent open" id="'+cur_lev2[i].id+'" parentId="'+lev1Id+'"  onclick="toogle(this)"><a>'+cur_lev2[i].name+'</a></div>';
				var lev3HTML="";
				lev3HTML+='<div class="third">';
				var lev3_len=lev3.lev3.length;
				//console.log(lev3_len);
				for(var j=0;j<lev3_len;j++){
					if(lev3.lev3[j].id==curId){//是要加cur类
						lev3HTML+='<p class="cur" id="'+lev3.lev3[j].id+'" parentId="'+cur_lev2[i].id+'" ref="'+lev3.lev3[j].href+'"  onclick="loadURL(this)"><a>'+lev3.lev3[j].name+'</a></p>';
					}else{//无需要加cur
						lev3HTML+='<p id="'+lev3.lev3[j].id+'" parentId="'+cur_lev2[i].id+'" ref="'+lev3.lev3[j].href+'"  onclick="loadURL(this)"><a>'+lev3.lev3[j].name+'</a></p>';
					}
				}
				lev3HTML+='</div>';
				lev2HTML+=lev3HTML;
			}
		}
		
		lpart.append(lev2HTML);
		//追加后，改变链接
		var Href=$("#"+curId).attr("ref");
		iframeObj.attr("src",Href);
		var cur=$("#"+curId);
		//初始化折叠
		var curParent=cur.parent("div");
		if(curParent.attr("class")=="third"){//说明cur的是三级菜单
			lpart.find(".third").not(curParent).hide();
			lpart.find(".thirdParent").not(curParent.prev(".thirdParent")).removeClass("open").addClass("close");
		}else{//说明cur是二级菜单
			lpart.find(".third").hide();
			lpart.find(".thirdParent").removeClass("open").addClass("close");
		}
	}
	$("#"+lev1Id).addClass("on");
	//console.log(curId+' ; '+lev1Id);
}
function clickInitLev2(t){
	navConUl.find("li").removeClass("on");
	$(t).addClass("on");
	Href=$(t).attr("ref");
	//console.log(Href);
	if(Href==""||Href==undefined){//点击的不是首页，不带href值
		//左侧第一个（二级菜单或三级菜单的第一个）给cur
		lpart.show();
		$("#main").addClass("padding");
		lpart.html("");
		var lev2HTML="";
		var curId=null;
		var lev1Id=$(t).attr("id");
		var cur_lev2=menuJSON[lev1Id].lev2;
		var cur_lev2_len=cur_lev2.length;
		var flag=true;
		for(var i=0;i<cur_lev2_len;i++){
			var thirdId=cur_lev2[i].id;
			//console.log(thirdId);
			var lev3=menuJSON[thirdId];
			if(lev3==""||lev3==undefined){//没有三级菜单
				if(flag){//第一个要加cur的
				   lev2HTML+='<p class="onlySecond cur" id="'+cur_lev2[i].id+'" parentId="'+lev1Id+'" ref="'+cur_lev2[i].href +'" onclick="loadURL(this)"><a>'+cur_lev2[i].name+'</a></p>';
				   flag=false;//改变flag的值后，就不在走flag=true的分支了
				   curId=cur_lev2[i].id;
				}else{//无需要加cur
				   lev2HTML+='<p class="onlySecond" id="'+cur_lev2[i].id+'" parentId="'+lev1Id+'" ref="'+cur_lev2[i].href +'" onclick="loadURL(this)"><a>'+cur_lev2[i].name+'</a></p>';
				}
			}else{//有三级菜单
				lev2HTML+='<div class="thirdParent open" id="'+cur_lev2[i].id+'" parentId="'+lev1Id+'"  onclick="toogle(this)"><a>'+cur_lev2[i].name+'</a></div>';
				var lev3HTML="";
				lev3HTML+='<div class="third">';
				var lev3_len=lev3.lev3.length;
				//console.log(lev3_len);
				for(var j=0;j<lev3_len;j++){
					if(flag){//是要加cur类
						lev3HTML+='<p class="cur" id="'+lev3.lev3[j].id+'" parentId="'+cur_lev2[i].id+'" ref="'+lev3.lev3[j].href+'"  onclick="loadURL(this)"><a>'+lev3.lev3[j].name+'</a></p>';
						flag=false;
						curId=lev3.lev3[j].id;
					}else{//无需要加cur
						lev3HTML+='<p id="'+lev3.lev3[j].id+'" parentId="'+cur_lev2[i].id+'" ref="'+lev3.lev3[j].href+'"  onclick="loadURL(this)"><a>'+lev3.lev3[j].name+'</a></p>';
					}
				}
				lev3HTML+='</div>';
				lev2HTML+=lev3HTML;
			}
		}
		lpart.append(lev2HTML);
		//追加后，改变链接
		var Href=$("#"+curId).attr("ref");
		iframeObj.attr("src",Href);
		var cur=$("#"+curId);
		//初始化折叠
		var curParent=cur.parent("div");
		//console.log("curId "+curId);
		if(curParent.attr("class")=="third"){//说明cur的是三级菜单
			lpart.find(".third").not(curParent).hide();
			//alert("aa");
			lpart.find(".thirdParent").not(curParent.prev(".thirdParent")).removeClass("open").addClass("close");
		}else{//说明cur是二级菜单
			lpart.find(".third").hide();
			lpart.find(".thirdParent").removeClass("open").addClass("close");
		}
	}else{//点击的是首页，带href值
		createHome(Href);
	}
}
var lpart=$("#l_part");

function createHome(newUrl){
	lpart.hide();
	$("#main").removeClass("padding");
	iframeObj.attr("src",newUrl);
}

//二级菜单和三级菜单的点击事件
function toogle(t){
	var lpart=$("#l_part");
	if($(t).hasClass("open")){
		//关闭所有
		lpart.find(".thirdParent").removeClass("open").addClass("close");
		lpart.find(".third").hide();
		//$(t).removeClass("open").addClass("close");
		//$(t).next(".third").hide();
	}else if($(t).hasClass("close")){
		//关闭所有
		lpart.find(".thirdParent").removeClass("open").addClass("close");
		lpart.find(".third").hide();
		//打开当前
		$(t).removeClass("close").addClass("open");
		$(t).next(".third").show();
	}
}
function loadURL(t){
	var lpart=$("#l_part");
	lpart.find("p").removeClass("cur");
	$(t).addClass("cur");
	//跳转地址
	var newUrl=$(t).attr("ref");
	iframeObj.attr("src",newUrl);
}






























































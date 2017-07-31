function init(obj_1,obj_2,obj_3){
	//定义默认数据
	var ar = ["请选择省份","请选择城市","请选择区县"];
	
	//响应c的change事件
	var cindex;
	//响应p的change事件
	var pindex;
	
	$("#"+obj_2).change(function(){
		cindex = $("#"+obj_2).get(0).selectedIndex;
		//清空h
		$("#"+obj_3).empty();
		//重新给h填充内容
		$("<option>"+ar[2]+"</option>").appendTo($("#"+obj_3));
		var countyhidden = $("#countyhidden").val();
		if (cindex!=0){
			for (j=0;j<mh[pindex-1][cindex-1].length;j++){
				
				if(mh[pindex-1][cindex-1][j]==countyhidden)
				{
					$("<option selected>"+mh[pindex-1][cindex-1][j]+"</option>").appendTo($("#"+obj_3));
				}else
				{
					$("<option>"+mh[pindex-1][cindex-1][j]+"</option>").appendTo($("#"+obj_3));
				}
				
			}
		}
	});
	
	
	$("#"+obj_1).change(function(){
		//获取索引
		pindex = $("#"+obj_1).get(0).selectedIndex;
		//清空c和h
		$("#"+obj_2).empty();
		//重新给c填充内容
		$("<option>"+ar[1]+"</option>").appendTo($("#"+obj_2));
		var cityhidden = $("#cityhidden").val();
			if (pindex!=0){
				for (k=0;k<mc[pindex-1].length;k++){
					if(cityhidden==mc[pindex-1][k])
					{
						$("<option selected>"+mc[pindex-1][k]+"</option>").appendTo($("#"+obj_2));
						$("#"+obj_2).change();
					}else
					{
						$("<option>"+mc[pindex-1][k]+"</option>").appendTo($("#"+obj_2));
					}
					
				}
			}	
		//清空h
	//	$("#"+obj_3).empty();
	//	$("<option>"+ar[2]+"</option>").appendTo($("#"+obj_3));
	});
	
	//初始化
	$("<option value="+ar[0]+">"+ar[0]+"</option>").appendTo($("#"+obj_1));
	$("<option value="+ar[1]+">"+ar[1]+"</option>").appendTo($("#"+obj_2));
	$("<option value="+ar[2]+">"+ar[2]+"</option>").appendTo($("#"+obj_3));
	
	//初始化p
	for (i=0;i<mp.length;i++){
		var province = $("#provincehidden").val();
		if(province==mp[i])
		{
			$("<option value="+mp[i]+" selected>"+mp[i]+"</option>").appendTo($("#"+obj_1));
			$("#"+obj_1).change();
		}else
		{
			$("<option value="+mp[i]+">"+mp[i]+"</option>").appendTo($("#"+obj_1));
		}
		
	}	
	
	
	
	
	
}
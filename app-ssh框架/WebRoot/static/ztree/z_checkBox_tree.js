strSelectFrame="<div id='menuContent' class='menuContent' style='display:none; position: absolute;'><ul id='treeDemo' class='ztree' style=' margin-top:0; width:180px; height: 300px;'></ul></div>";
document.writeln(strSelectFrame);
		
		var mul_setting = {
			check: {
				enable: true,
				chkboxType: {"Y":"", "N":""}
				//chkStyle:"checkBox"
				
			},
			view: {
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeClick: beforeClick,
				onCheck: onCheck
			}
		};

			
		var single_setting = {
			
			view: {
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeClick: singlebeforeClick,
				onClick: singleonClick
			}
		};

		var setting;
		var zNodes ;
		var g_inputId;
		var g_imageId;
		var g_res;
		var g_selectLeafOnly;
		
		function beforeClick(treeId, treeNode) {
			//alert(treeNode.isParent);
			//if(g_selectLeafOnly == true && treeNode.isParent){
			//	return false;	
			//}
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.checkNode(treeNode, !treeNode.checked, null, true);
			return false;
		}
		
		
		function onCheck(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getCheckedNodes(true),
			v = "";
			vn="";
			//----------add by xugh begin
			var objArr = new Array();
			var vi=0; 
			//----------add by xugh end
			for (var i=0, l=nodes.length; i<l; i++) {
				v += nodes[i].name + ",";
				vn += nodes[i].id + ",";
				//----------add by xugh begin
				for(var j=0, le=zNodes.length;j<le;j++){
					if(nodes[i].id == zNodes[j]["id"] ){
						objArr[vi++] = zNodes[j];
					}
				}
				//----------add by xugh end
			}
			if (v.length > 0 ) v = v.substring(0, v.length-1);
			if (vn.length > 0 ) vn = vn.substring(0, vn.length-1);
			
			var cityObj = $("#"+g_inputId);
			cityObj.attr("value", v);
			
			var idObj = $("#"+g_imageId);
			idObj.attr("value", vn);
			
			if(g_res != null){
				g_res(objArr);//----------add by xugh
			}
		}
		
		function singlebeforeClick(treeId, treeNode) {
			//var check = (treeNode && !treeNode.isParent);
			if(g_selectLeafOnly == null){
				alert("isSelectLeafOnly is null");
			}
			if(g_selectLeafOnly==true && treeNode && treeNode.isParent){
				//if (!check) alert("....");
				return false;			
			}else{
				return true;
			}

		}
		
		function singleonClick(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getSelectedNodes(),
			v = "";
			vn="";
			//----------add by xugh begin
			var objArr = new Array();
			var vi=0; 
			//----------add by xugh end
			nodes.sort(function compare(a,b){return a.id-b.id;});
			for (var i=0, l=nodes.length; i<l; i++) {
				v += nodes[i].name + ",";
				vn += nodes[i].id + ",";
				//----------add by xugh begin
				for(var j=0, le=zNodes.length;j<le;j++){
					if(nodes[i].id == zNodes[j]["id"] ){
						objArr[vi++] = zNodes[j];
					}
				}
				//----------add by xugh end
			}
			if (v.length > 0 ) v = v.substring(0, v.length-1);
			if (vn.length > 0 ) vn = vn.substring(0, vn.length-1);
			
			var cityObj = $("#"+g_inputId);
			cityObj.attr("value", v);
			var idObj = $("#"+g_imageId);
			idObj.attr("value", vn);
			
			if(g_res != null){
				g_res(objArr);//----------add by xugh
			}
		}
		
		

		function showMenu(treeType,isDynamic,url,inputId,hiddenId,relatedId, resFun,iconInfo,isSelectLeafOnly,psRelation) {
			
			
			
			if(arguments.length<9){
					alert("Missing parameter!");
					return;
			}
			if(treeType != true && treeType != false){
					alert("treeType type should be true or false!");
					return;
			}
			if(isDynamic != true && isDynamic != false){
					alert("isDynamic type should be true or false!");
					return;
			}else{
				//alert(typeof url);
				if(isDynamic == true && !(typeof url =="string")){
						alert("url type should be string!");
						return;
				}
				if(isDynamic == false && !(url instanceof Array)){
						alert("url type should be array!");
						return;
				}
			}
			if(inputId =="" || !(typeof inputId =="string")){
				alert("inputId can't be empty and must be string!");
				return;
			}
			if(hiddenId =="" || !(typeof hiddenId =="string")){
				alert("hiddenId can't be empty and must be string!");
				return;
			}
			if(relatedId != "" && !(typeof relatedId == "string")){	
				//alert($("#" + relatedId).val());
				alert("relatedId should be type of string");	
			}
			if(resFun !=null && !( resFun instanceof Function )){
				alert("resFun should be Function!");
				return;
			}	
			if(iconInfo !=null && !( iconInfo instanceof Array )){
				alert("iconInfo should be Array!");
				return;
			}
			if(isSelectLeafOnly != null && isSelectLeafOnly != true && isSelectLeafOnly != false){
					alert("isSelectLeafOnly type should be true or false!");
					return;
			}
			if(psRelation != null && !(typeof psRelation =="object")){
					alert("psRelation type should be object!");
					return;
			}
			
			
			
			initTree(treeType,isDynamic,url,inputId,hiddenId,relatedId,resFun,iconInfo,isSelectLeafOnly,psRelation);
			//alert(zNodes.length);
			var cityObj = $("#"+g_inputId);
			var cityOffset = $("#"+g_inputId).offset();
			$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
			
			$("body").bind("mousedown", onBodyDown);
		}
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!(event.target.id == g_imageId || event.target.id == g_inputId || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}
	
		/**
			treeType:
			url: 
			inputId:
			imageId:
			resFun: the method name you want callback,the parameter is a object array
		*/
		function initTree(treeType,isDynamic,url,inputId,imageId,relatedId,resFun,iconInfo,isSelectLeafOnly,psRelation){
			if(treeType == true){
				//alert(mul_setting.check.chkboxType.Y);
				if(psRelation!=null){
					//alert(psRelation.Y);
					y = psRelation.Y;
					n = psRelation.N;
					if(y=="ps"||y=="p"||y=="s"){
						mul_setting.check.chkboxType.Y = y;
					}
					if(n=="ps"||n=="p"||n=="s"){
						mul_setting.check.chkboxType.N = n;
					}
				}
				setting = mul_setting;
			}else{
				setting = single_setting;
			} 
			g_inputId= inputId;
			if(imageId == "" || imageId.length ==0){
				g_imageId = inputId;	
			}else{
				g_imageId = imageId;
			}
			g_res = resFun;
			g_selectLeafOnly = isSelectLeafOnly;
			//$("#"+inputId).after($("#menuContent"));
			var id = "";
			if(relatedId != ""){
				//alert($("#"+relatedId).val());
				id = $("#"+relatedId).val();
			}
			
			if(isDynamic == true){
				$.ajax({
						url:url+id,
						method:"POST", 
						datatype:"json",
						success:function(data){
							zNodes=data;
							if(iconInfo!=null && iconInfo.length !=0 ){
								for(var i=0;i<zNodes.length;i++){
									for(var m=0;m<iconInfo.length;m++){
										if(iconInfo[m].type == zNodes[i].type){
											zNodes[i].icon = iconInfo[m].iconClose;
											zNodes[i].iconOpen = iconInfo[m].iconOpen;
											zNodes[i].iconClose = iconInfo[m].iconClose;
										}
									}
								}							
							}
							if($.fn.zTree.getZTreeObj("treeDemo")){
								$.fn.zTree.destroy("treeDemo");
							}
							$.fn.zTree.init($("#treeDemo"), setting, zNodes);
							if($("#"+g_imageId).val() != ""){
								//alert($("#"+g_imageId).val());
								var arr = new Array();
								arr = $("#"+g_imageId).val().split(",");
								for(var i=0;i<arr.length;i++){
									//alert(arr[i]);
									var node = $.fn.zTree.getZTreeObj("treeDemo").getNodeByParam("id",arr[i],null);
									if(node != null){
										$.fn.zTree.getZTreeObj("treeDemo").checkNode(node,true,false,false); 
										$.fn.zTree.getZTreeObj("treeDemo").selectNode(node,true); 
										$.fn.zTree.getZTreeObj("treeDemo").expandNode(node,true,false,false,false); 									
									}
								}
							}
						}				
					});			
			}else{
			    zNodes = url;	
			    if(iconInfo!=null && iconInfo.length !=0 ){
					for(var i=0;i<zNodes.length;i++){
						for(var m=0;m<iconInfo.length;m++){
							if(iconInfo[m].type == zNodes[i].type){
								zNodes[i].icon = iconInfo[m].iconClose;
								zNodes[i].iconOpen = iconInfo[m].iconOpen;
								zNodes[i].iconClose = iconInfo[m].iconClose;
							}
						}
					}							
				}
				
				if($.fn.zTree.getZTreeObj("treeDemo")){
					$.fn.zTree.destroy("treeDemo");
				}
				$.fn.zTree.init($("#treeDemo"), setting, zNodes);
				if($("#"+g_imageId).val() != ""){
					var arr = new Array();
					arr = $("#"+g_imageId).val().split(",");
					for(var i=0;i<arr.length;i++){
						//alert(arr[i]);
						var node = $.fn.zTree.getZTreeObj("treeDemo").getNodeByParam("id",arr[i],null);
						if(node != null){
							$.fn.zTree.getZTreeObj("treeDemo").checkNode(node,true,false,false); 
							$.fn.zTree.getZTreeObj("treeDemo").selectNode(node,true); 
							$.fn.zTree.getZTreeObj("treeDemo").expandNode(node,true,false,false,false); 
						}
					}
				}
									
			}
			
			
				
				

		}
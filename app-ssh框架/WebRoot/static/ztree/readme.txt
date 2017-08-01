z_checkBox_tree.js 使用说明
				此控件为一个封装自ztree的树形菜单控件
				支持单选菜单和多选菜单，
				支持动态和静态获取数据，
				支持回调，
				支持自定义图片；
				可设置单选菜单是否只能选择叶子节点；
				可设置多选菜单父子节点间的关联关系；
				可设置选择菜单框的宽度和高度；
使用步骤
1，使用前导入jquery,ztree相关js, css;
		列表如下：zTreeStyle.css  demo.css  jquery-1.4.4.min.js jquery.ztree.core-3.4.js  jquery.ztree.excheck-3.4.js
						
2,导入z_checkBox_tree.js

3,在页面js中调用函数 
		function showMenu(treeType,isDynamic,url,inputId,hiddenId,resFun,iconInfo,isSelectLeafOnly，psRelation)
						

函数showMenu(treeType,isDynamic,url,inputId,imageId,resFun,iconInfo,isSelectLeafOnly,psRelation) 说明如下：
	treeType 树类型 ;
					 若为true则为多选菜单，false为单选菜单;
					 是否可为空：不可为空;
	isDynamic 
						是否动态获取数据; 
						若为true，动态获取数据；若为false相反；
						是否可为空：不可为空;
	url			  
					为获取数据的url或者为静态数据。
					若isDynamic为true， url为获取数据的url；若isDynamic为false，url为组织的静态数据；
					数据格式为json格式的数组；格式如下：
					var zNodeArray = [{"id":"1","pId":"0","name":"日常任务","type":"1"},
								  					{"id":"2","pId":"0","name":"现场检查","type":"1"},
								  					{"id":"3","pId":"1","name":"上级来文","type":"2"},
								  					{"id":"4","pId":"1","name":"内部文件","type":"2"},
								  					{"id":"5","pId":"2","name":"污染源","type":"2"},
								  					{"id":"6","pId":"2","name":"建设项目","type":"2"},
								  					{"id":"7","pId":"3","name":"上级来文1","type":"3"},
								  					{"id":"8","pId":"3","name":"上级来文2","type":"3"},
								  					{"id":"9","pId":"4","name":"内部文件1","type":"3"},
								  					{"id":"10","pId":"4","name":"内部文件2","type":"3"}
								  				];
					格式说明：id为节点唯一标识；
										pId为节点父节点标识；
										name为节点在菜单中显示的名称；
										type为自定义图标的参数，当type值和iconInfoArray中type值相同时，显示iconInfoArray中定义的图标；			  				
										nocheck 为节点是否可选，为true时当前节点不可选；
										open 设置节点的展开、折叠状态；为true时节点展开；
										更多请参见zTree API；
										若需要动态获取数据,可参见：TaskTypeAction.java中create_tree()方法; 
					是否可为空：不可为空;
	 inputId 输入框id ;
	 					是否可为空：不可为空;
	 hiddenId 隐藏域id;
	 					是否可为空：不可为空;
	 resFun 
	 				回调函数方法名。此回调函数的参数为一个json格式数组，数组中包含所有当前已选中的节点信息；
	 				如果没有回调，应该设为null；
	 				是否可为空：可以为null;
	 iconInfo 
	 				自定义图片信息；类型为json格式的数组。若不自定义图片，应该设为null;
	 				格式如下：
	 				var iconInfoArray = new Array();
						iconInfoArray[0]={"type":"1","iconClose":"<user:skin value='/scripts/zTree-v3.4/css/zTreeStyle/img/diy/1_open.png'/>","iconOpen":"<user:skin value='/scripts/zTree-v3.4/css/zTreeStyle/img/diy/1_close.png'/>"};
						iconInfoArray[1]={"type":"2","iconClose":"<user:skin value='/scripts/zTree-v3.4/css/zTreeStyle/img/diy/2.png'/>","iconOpen":"<user:skin value='/scripts/zTree-v3.4/css/zTreeStyle/img/diy/3.png'/>"};				
						格式说明：
						type 对应获取数据中的type
						iconClose 菜单关闭时图标路径
						iconOpen 菜单打开时图标路径	
	 					是否可为空：可以为null;
		isSelectLeafOnly
					是否只能选择叶子节点；只针对单选菜单有效；
					若为true只能选择叶子节点，false相反；
					如果要控制多选菜单的节点是否可选，可设置nocheck为true;
					是否可为空：不可为空;
		psRelation
					父子节点关联关系；只对多选菜单有效；
					格式如下：
					var psRelation = {"Y":"ps","N":"s"};
					格式说明：
							Y 设置被勾选时的关联关系
							N	设置取消勾选时的关联关系
								"p" 表示操作会影响父级节点；
								"s" 表示操作会影响子级节点；
								"ps"表示操作会影响父子节点；	
						请注意大小写，不要改变	
						是否可为空：可以为null; 当为null时，父子节点没有关联；
	 boxSize
	 				选择菜单框大小设置
											是否可为空：可以为null;若为null显示默认大小；
											格式如下：
											var boxSize = {"width":"300px","height":"450px"};
											格式说明：
											width:菜单框宽度，可以为空；
											height：菜单框高度，可以为空；					
$package('YiYa.sysRole');
YiYa.sysRole = function(){
	var _box = null;
	var _this = {
		menu: $('#menu-tree'),
		buildTreeData:function(nodes){
			$.each(nodes,function(i,note){
				var id = note.attributes.id;
				var type = note.attributes.type;
				var $id = $("<input type='hidden' name='menuIds' class='c_menus'>");
				if(type == 0){
					$id.attr('name','menuIds');
				}else if(type == 1){
					$id.attr('name','btnIds');
				}
				$id.val(id);
				_box.form.edit.append($id);
			});
		},
		setTreeValue:function(id){
			var node = _this.menu.tree("find",id);
			if(node && node.target){
				//判断是否选择或者半选状态 
				if($(node.target).find(".tree-checkbox0")[0]){
					_this.menu.tree('check',node.target);
					//YiYa.alert('警告','未选中记录.','warning');  
				}
			}
		},
		clearTreeData:function(){
			$(".tree-checkbox1",_this.menu).removeClass("tree-checkbox1").addClass("tree-checkbox0")
			$(".tree-checkbox2",_this.menu).removeClass("tree-checkbox2").addClass("tree-checkbox0");
			$('.c_menus').remove();
		},
		config:{
			event:{
				add:function(){
					_this.clearTreeData();
					_box.handler.add();
				},
				edit:function(){
					_this.clearTreeData();
					_box.handler.edit(function(result){
						var btnIds  = result.data.btnIds;
						var menuIds  = result.data.menuIds;
						$.each(btnIds,function(i,id){
							_this.setTreeValue("btn_"+id);  ///**/
						});
						
						$.each(menuIds,function(i,id){
							_this.setTreeValue("menu_"+id);   //
						});
					});
				},
				save:function(){
					var checknodes = _this.menu.tree('getChecked');	
					var innodes = _this.menu.tree('getChecked','indeterminate');
					_this.buildTreeData(checknodes);
					_this.buildTreeData(innodes);
					_box.handler.save();
				}
			},
  			dataGrid:{
  				title:'Role List',
	   			url:'dataList.do',
				pagination: true,
                pageSize:10,
			    pageList: [10, 20, 30, 40, 50],
	   			columns:[[
						{field:'id',checkbox:true},
						{field:'roleName',title:'RoleName',width:120,sortable:true},
						{field:'state',title:'State',width:80,align:'center',sortable:true,styler:function(value,row,index){
								if(value == 1){
								  return 'color:red;';  
								}
							},
							formatter:function(value,row,index){
								if(value == 0){
									return "可用";
								}
								if(value == 1){
									return "禁用";
								}
							}},
						{field:'createTime',title:'CreateTime',width:150,sortable:true},
						{field:'updateTime',title:'UpdateTime',width:150,sortable:true},
						{field:'descr',title:'Description',width:120,sortable:true}
						
				]]
			}
		},
		init:function(){
			_box = new YDataGrid(_this.config); 
			_box.init();
			
			_this.menu.tree({
				url:'../sysMenu/getMenuTree.do',
				checkbox:true
			});
		}
	}
	return _this;
}();

$(function(){
	YiYa.sysRole.init();
});		
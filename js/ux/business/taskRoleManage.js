$package('YiYa.taskRoleManage');
YiYa.taskRoleManage = function(){
	var _box = null;
	var _this = {
		config:{
			/**
			event:{
				add:function(){
					$('#cgflIds_combobox').combobox('reload');
					_box.handler.add();
				},
				edit:function(){
					$('#cgflIds_combobox').combobox('reload');
					_box.handler.edit();
				}
			},
			*/

  			dataGrid:{
  				title:'工作计划权限列表',
	   			url:'dataList.do',
				nowrap: false,
				pagination: true,
                pageSize:100,
			    pageList: [100, 200, 300, 400, 500],
	   			columns:[[
					{field:'id',checkbox:true},
					{field:'userName',title:'管理者姓名',width:100,sortable:true},
					{field:'roleStrs',title:'可查看工作计划人员列表',width:800,sortable:true},
		
					{field:'state',title:'状态',width:80,align:'center',sortable:true,styler:function(value,row,index){
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
						}}
				]]
			}
		},
		init:function(){
			_box = new YDataGrid(_this.config); 
			_box.init();
		}
	}
	return _this;
}();

$(function(){
	YiYa.taskRoleManage.init();
});		
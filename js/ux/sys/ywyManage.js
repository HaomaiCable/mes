$package('YiYa.ywyManage');
YiYa.ywyManage = function(){
	var _box = null;
	var _this = {
		config:{
		    dataGrid:{
  				title:'业务员列表',
	   			url:'dataList.do',
	   			columns:[[
						{field:'id',checkbox:true},
						{field:'name',title:'业务员姓名',width:100,sortable:true},
						{field:'department',title:'所属部门',width:100,sortable:true},
						{field:'state',title:'可用状态',width:100,align:'center',sortable:true,styler:function(value,row,index){
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
	YiYa.ywyManage.init();
});		
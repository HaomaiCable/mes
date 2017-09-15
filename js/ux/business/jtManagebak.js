$package('YiYa.jtManage');
YiYa.jtManage = function(){
	var _box = null;
	var _this = {
		config:{
		    dataGrid:{
  				title:'机台(设备)列表',
	   			url:'dataList.do',
				pagination: true,
                pageSize:10,
			    pageList: [10, 20, 30, 40, 50],
	   			columns:[[
						{field:'id',checkbox:true},
						{field:'jtmc',title:'机台(设备)名称',width:100,sortable:true},
						{field:'gd',title:'所属工段',width:100,sortable:true},
						{field:'cj',title:'所属车间',width:100,sortable:true},
						//{field:'searchname',title:'定额库设备名称',width:100,sortable:true},
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
	YiYa.jtManage.init();
});		
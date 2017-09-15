$package('YiYa.sbManage');
YiYa.sbManage = function(){
	var _box = null;
	var _this = {
		config:{
		    dataGrid:{
  				title:'设备信息列表',
	   			url:'dataList.do',
				height : 500,  
			    nowrap: true,
				pagination: true,
  			    pageSize:15,
			    pageList: [10, 15, 30, 40, 50],
	   			columns:[[
						{field:'id',checkbox:true},
						{field:'sbmc',title:'设备名称',width:80,sortable:true},
						{field:'deksbmc',title:'定额库设备名称',width:120,sortable:true},
						{field:'sbsl',title:'台数',width:60,align:'right'},
						{field:'bc',title:'工作班次',width:80,align:'right'},
						{field:'cqgs',title:'出勤工时',width:80,align:'right'},
						{field:'yyfh',title:'已有负荷',width:80,align:'right'},
						{field:'bpjhfh',title:'本批计划负荷',width:100,align:'right'},
						{field:'tzxs',title:'负荷修正系数',width:100,align:'right'},
						{field:'jt',title:'所属机台',width:140,sortable:true},
						{field:'state',title:'使用状态',width:80,align:'center',sortable:true,styler:function(value,row,index){
								if(value == 1){
								  return 'color:red;';  
								}
							},
							formatter:function(value,row,index){
								if(value == 0){
									return "在用";
								}
								if(value == 1){
									return "不使用";
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
	YiYa.sbManage.init();
});		
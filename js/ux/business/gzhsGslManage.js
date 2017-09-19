$package('YiYa.gzhsGslManage');
YiYa.gzhsGslManage = function(){
	var _box = null;
	var _this = {
		config:{
		    dataGrid:{
  				title:'机台工时工资率',
	   			url:'dataList.do',
				pagination: true,
                pageSize:10,
			    pageList: [10, 20, 30, 40, 50],
	   			columns:[[
						{field:'id',checkbox:true},
						{field:'gd',title:'工段',width:100,sortable:true},
						{field:'sbmc',title:'机台名称',width:100,sortable:true},
			    	    {field:'jtrs',title:'机台人数',width:100,align:'right' },
			    	    {field:'gsl',title:'工时工资率',width:100,align:'right' },
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
	YiYa.gzhsGslManage.init();
});		
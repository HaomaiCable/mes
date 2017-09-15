$package('YiYa.paraManage');
YiYa.paraManage = function(){
	var _box = null;
	var _this = {
		config:{
		    dataGrid:{
  				title:'系统参数列表',
	   			url:'dataList.do',
				height : 500,  
			    nowrap: true,
				pagination: true,
  			    pageSize:15,
			    pageList: [10, 15, 30, 40, 50],
	   			columns:[[
						{field:'id',checkbox:true},
						{field:'flag',title:'Flag',width:160,sortable:true},
						{field:'para1',title:'Parameter1',width:160,sortable:true},
						{field:'para2',title:'Parameter2',width:160,sortable:true},
						{field:'para3',title:'Parameter3',width:160,sortable:true},
						{field:'descript',title:'Description',width:260,sortable:true},
				        {field:'state',title:'状态',width:60,align:'center',sortable:true,styler:function(value,row,index){
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
	YiYa.paraManage.init();
});		
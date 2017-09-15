$package('YiYa.jhbhManage');
YiYa.jhbhManage = function(){
	var _box = null;
	var _this = {
		config:{
		    dataGrid:{
  				title:'单据顺序号列表',
	   			url:'dataList.do',
				pagination: true,
                pageSize:10,
			    pageList: [10, 20, 30, 40, 50],
	   			columns:[[
						{field:'id',checkbox:true},
						{field:'flag',title:'单据分类',width:120,align:'center',sortable:true,styler:function(value,row,index){
								if(value == 0){
								  return 'color:red;';  
								}
							},
							formatter:function(value,row,index){
								if(value == 1){
									return "销售订单";
								}
								else if(value == 2){
									return "销售订单变更单";
								}
								else if(value == 3){
									return "半成品计划单";
								}
								else if(value == 4){
									return "生产计划单变更单";
								}
                                else if(value == 5){
									return "采购招标公告";
								}
							}},
						{field:'nian',title:'年度',width:100,sortable:true},
						{field:'yue',title:'月度',width:100,sortable:true},
						{field:'jhno',title:'已用最大顺序号',width:100}
	
						
				]],
                toolbar:[
					{id:'btnedit',text:'修改',btnType:'edit'}
				
				]
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
	YiYa.jhbhManage.init();
});		
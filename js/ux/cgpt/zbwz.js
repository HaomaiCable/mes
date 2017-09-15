$package('YiYa.zbwz');
YiYa.zbwz = function(){
	var _box = null;
	var _this = {
		config:{
  			dataGrid:{
  				title:'招标物资明细',
	   			url:'dataList.do',
				pagination: true,
                pageSize:10,
			    pageList: [10, 20, 30, 40, 50],
	   			columns:[[
					{field:'id',checkbox:true},
					{field:'name',title:'招标物资名称',width:80,sortable:true},
					{field:'descr',title:'说明',width:260,sortable:true},//,sorter:function(a,b){return (a>b?1:-1);}
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
	YiYa.zbwz.init();
});		
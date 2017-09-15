$package('YiYa.siteType');
YiYa.siteType = function(){
	var _box = null;
	var _this = {
		config:{
  			dataGrid:{
  				title:'SiteType List',
	   			url:'dataList.do',
				pagination: true,
                pageSize:10,
			    pageList: [10, 20, 30, 40, 50],
	   			columns:[[
					{field:'id',checkbox:true},
					{field:'name',title:'Name',width:120,sortable:true},
					{field:'code',title:'Code',width:220,sortable:true,sorter:function(a,b){return (a>b?1:-1);}},
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
					{field:'rank',title:'Rank',align:'right',width:80,sortable:true},
					{field:'descr',title:'Desc',width:250,sortable:true}
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
	YiYa.siteType.init();
});		
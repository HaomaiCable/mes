$package('YiYa.gysdaManage');
YiYa.gysdaManage = function(){
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
  				title:'供应商档案列表',
	   			url:'dataList.do',
				pagination: true,
                pageSize:10,
			    pageList: [10, 20, 30, 40, 50],
	   			columns:[[
					{field:'id',checkbox:true},
					{field:'name',title:'采购商名称',width:160,sortable:true},
					{field:'addr',title:'地址',width:100,sortable:true},
					{field:'fr',title:'法人',width:60,sortable:true},
					{field:'khh',title:'开户银行',width:100,sortable:true},
					{field:'zh',title:'账号',width:80,sortable:true},
					{field:'sh',title:'税号',width:80,sortable:true},
					{field:'lxr',title:'联系人',width:60,sortable:true},
					{field:'phone',title:'手机',width:100,sortable:true},
					{field:'tel',title:'电话',width:100,sortable:true},
					{field:'fax',title:'传真',width:100,sortable:true},
					{field:'email',title:'邮箱',width:100,sortable:true},
					{field:'cgfls',title:'供应物资分类',width:160,sortable:true},
					//{field:'zbwzs',title:'供应物资明细',width:160,sortable:true},
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
	YiYa.gysdaManage.init();
});		
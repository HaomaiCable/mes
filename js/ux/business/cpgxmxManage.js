$package('YiYa.cpgxmxManage');

YiYa.cpgxmxManage = function(){
	var _box = null;
	var _this = {
		config:{
 		    dataGrid:{
  				title:'产品及工序明细',
	   			url:'dataList.do',
				height: 365,  
				nowrap:true,
				pagination: true,
  			    pageSize:10,
			    pageList: [10, 20, 30, 40, 50],
             	columns:[[
					{field:'id',checkbox:true},
                    {field:'cplb',title:'产品类别',width:80,sortable:true},
			    	{field:'cpxh',title:'产品型号',width:100,sortable:true},
			    	{field:'cpgg',title:'产品规格',width:100,sortable:true},
			    	{field:'cpdy',title:'电压等级',width:60,sortable:true},
			    	{field:'cpdw',title:'单位',width:40},
   	                {field:'xsjg',title:'线芯结构',width:120},
			    	{field:'zxs',title:'主线芯数',width:60,align:'right' },
			    	{field:'zzl',title:'主线单位长度重量',width:100,align:'right' },
			    	{field:'lxs',title:'零线芯数',width:60,align:'right' },
			    	{field:'lzl',title:'零线单位长度重量',width:100,align:'right' },
			    	{field:'cpgs',title:'终端工时',width:80,align:'right' },
	                {field:'gxDetails',title:'工序明细',width:80,align:'center',formatter:function(value,row,index){
						if (row.subCountGx!=0)
						{
						    var html ="<a href='#' onclick='toGxmxList("+row.id+")'>工序明细("+row.subCountGx+")</a>";
						    return html;
						}
					}}
				]],
				toolbar:[
					   {
						id:'btntmp',
						text:'',
						disabled: true,
					    handler:function(){
						}
					}
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
	YiYa.cpgxmxManage.init();
});		
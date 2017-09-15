$package('YiYa.gsdeManage');

YiYa.gsdeManage = function(){
	var _box = null;
	var _this = {
		config:{
 		    dataGrid:{
  				title:'机台工时定额列表',
	   			url:'dataList.do',
				height: 500,  
				nowrap:true,
				pagination: true,
  			    pageSize:15,
			    pageList: [15, 20, 30, 40, 50],
             	columns:[[
					{field:'id',checkbox:true},
                    {field:'sbmcdek',title:'设备名称',width:100,sortable:true},
			    	{field:'gxxh',title:'型号',width:100,sortable:true},
			    	{field:'gxgg',title:'规格',width:100,sortable:true},
			    	{field:'gxdy',title:'电压',width:80,sortable:true},
					{field:'gxlb',title:'类别',width:50},
   	                {field:'dgrs',title:'定岗人数',width:60,align:'right'},
			    	{field:'fthpgs',title:'分摊换盘时间',width:100,align:'right' },
			    	{field:'fxprl',title:'放线盘容量',width:80,align:'right' },
			    	{field:'gdzbgs',title:'固定准备时间',width:80,align:'right' },
			    	{field:'zbgs',title:'准备时间',width:60,align:'right' },
			    	{field:'qygs',title:'牵引工时',width:60,align:'right' },
			    	{field:'sdgs',title:'实动工时',width:80,align:'right' },
				    {field:'fzgs',title:'辅助工时',width:60,align:'right' },
		    	    {field:'hsxpgs',title:'换收线盘工时',width:100,align:'right' }
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
	YiYa.gsdeManage.init();
});		
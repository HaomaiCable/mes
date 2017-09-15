$package('YiYa.xsddRkhbManage');

YiYa.xsddRkhbManage = function(){
 
	var _box = null;
	var _this = {
 
		config:{
			action:{
 				save:'saveRkhb.do',
				getId:'getRkhb.do'
			},
   	
 			dataGrid:{
  		title:'销售订单列表',
	   			url:'dataListLink.do',
				height : 500,  
			    nowrap: false,
				pagination: true,
                pageSize:100,
			    pageList: [100, 200, 300, 400, 500],
				singleSelect:true,	
                frozenColumns:[[
						{field:'id',checkbox:true},
					{field:'xdrq',title:'下达日期',width:80,sortable:true,formatter:function formatterdate(value,row,index) {
                        if (value != null) { 
						    var date = new Date(value);
                            return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                             + date.getDate();
                         }}
                     },	
					{field:'jhbh',title:'计划编号',width:80,sortable:true},
					{field:'row',title:'行号',width:40},
			    	{field:'xh',title:'产品型号',width:120,sortable:true},
			    	{field:'gg',title:'产品规格',width:80,sortable:true},
			    	{field:'dy',title:'电压等级',width:60,sortable:true},
			    	{field:'gy',title:'工艺',width:40},
			    	{field:'dw',title:'单位',width:40},
			    	{field:'ph',title:'批号',width:140},
			    	{field:'sl',title:'计划数量',width:60,align:'right' },
		        ]],
	   			columns:[[

			        {field:'jhrq',title:'计划交货期',width:70,sortable:true,formatter:function formatterdate(value,row,index) {
                        if (value != null) {
                        var date = new Date(value);
                        return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                         + date.getDate();
                         }}
                     },
			    	{field:'jsyq',title:'技术要求',width:160},
					{field:'state',title:'状态',width:40,align:'center',sortable:true,styler:function(value,row,index){
							if(value != 1){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
							
							if(value == 2){
								return "暂停";
							}
                           if(value == 3){
								return "作废";
							}
						}},
                    {field:'maxWgrq',title:'最后完工日期',width:80,sortable:true,formatter:function formatterdate(value,row,index) {
                         if (value != null) {
                             var date = new Date(value);
                             return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                              + date.getDate();
                          }}
                     },
			         {field:'wgzs',title:'完工主手',width:60},
			         {field:'sumWgsl',title:'合计完工数量',width:80,align:'right' },
			         {field:'sumWgslds',title:'完工段数明细',width:120},

					 {field:'qbRk',title:'全部入库',width:70,align:'center',sortable:true,styler:function(value,row,index){
							if(value != 0){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
							
							if(value == 1){
								return "已全部入库";
							}
                         
					 }},
			         {field:'sumRksl',title:'合计入库数量',width:80},
			         {field:'sumRkslds',title:'入库段数明细',width:120,align:'right' },
				     {field:'createBy',title:'订单员',width:80,sortable:true}

				]],
				toolbar:[
					{id:'btnedit',text:'入库确认',btnType:'edit'}
				
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
	YiYa.xsddRkhbManage.init();
});		
$package('YiYa.xsddChangeCheck');

YiYa.xsddChangeCheck = function(){
	var _box = null;
	var _this = {
 
		config:{
			action:{
 				save:'saveDdbgCheck.do',
				getId:'getDdbgCheck.do'
			},
   	
 			dataGrid:{
  				title:'销售订单变更单列表',
	   			url:'dataListChange.do',
				height : 500,  
			    nowrap: false,
				pagination: true,
  			    pageSize:100,
			    pageList: [100, 200, 300, 400, 500],
   			    columns:[[
					{field:'id',checkbox:true},
					{field:'bh',title:'变更单编号',width:80,sortable:true},	
					{field:'row',title:'行号',width:30},
					{field:'jhbh',title:'销售订单编号',width:90,sortable:true},	
					{field:'jhbhrow',title:'行号',width:30},

			    	{field:'field',title:'变更项目',width:80,sortable:true},
			    	{field:'oldContent',title:'变更前内容',width:180,
	                     formatter:function(value,row,index){
	                         if (row.field=='状态')
	                         {
							      if(value == 1){
								      return '正常';
							       } 
								   else if (value==2)
								   {
									   return '暂停';
								   }
								   else if (value==3)
								   {
									   return '作废';
								   }
							}
	                        else{
								return value;
							}							
   
					}},
					{field:'newContent',title:'变更后内容',width:160,
                        formatter:function(value,row,index){
	                         if (row.field=='状态')
	                         {
							      if(value == 1){
								      return '正常';
							       } 
								   else if (value==2)
								   {
									   return '暂停';
								   }
								   else if (value==3)
								   {
									   return '作废';
								   }
							}
	                        else{
								return value;
							}
   
					}},		            
					{field:'checked',title:'变更审核',width:60,align:'center',sortable:true,styler:function(value,row,index){
							if(value == 1){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
	
							if(value == 1){
								return "已审核";
							}
   
					}},				
		            {field:'accept',title:'变更确认',width:60,align:'center',sortable:true,styler:function(value,row,index){
							if(value == 1){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
	
							if(value == 1){
								return "已确认";
							}
   
					}},									    
					{field:'createBy',title:'订单员',width:60,sortable:true},
					{field:'createTime',title:'变更时间',width:150,sortable:true},
		            {field:'checkBy',title:'审核人',width:60,sortable:true},
					{field:'checkTime',title:'审核时间',width:150,sortable:true},	
				    {field:'acceptBy',title:'确认人',width:60,sortable:true},
					{field:'acceptTime',title:'确认时间',width:150,sortable:true}
				]],
				toolbar:[
					{id:'btnedit',text:'变更单审核',btnType:'edit'}
				
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
	YiYa.xsddChangeCheck.init();
});		
$package('YiYa.jtjhChangeBrow');

YiYa.jtjhChangeBrow = function(){
	var _box = null;
	var _this = {
 
		config:{
			action:{
 				save:'',
				getId:'getDdbgAccept.do'
			},
   	
 			dataGrid:{
  				title:'机台计划变更单列表',
	   			url:'dataListChange.do',
				height : 500,  
			    nowrap: false,
				pagination: true,
  			    pageSize:100,
			    pageList: [100, 200, 300, 400, 500],
	   			columns:[[
					{field:'id',checkbox:true},
					{field:'bh',title:'变更单编号',width:100,sortable:true},	
					{field:'row',title:'行号',width:40},
					{field:'gd',title:'工段',width:80,sortable:true},
				    {field:'sbmc',title:'机台',width:100,sortable:true},	
					{field:'jhbh',title:'机台计划编号',width:120,sortable:true},	
					{field:'jhbhrow',title:'行号',width:40},

			    	{field:'field',title:'变更项目',width:100,sortable:true},
			    	{field:'oldContent',title:'变更前内容',width:200,
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
					{field:'newContent',title:'变更后内容',width:200,
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
					{field:'createBy',title:'计划员',width:80,sortable:true},
					{field:'createTime',title:'变更时间',width:150,sortable:true},
	
				    {field:'acceptBy',title:'确认人',width:80,sortable:true},
					{field:'acceptTime',title:'确认时间',width:150,sortable:true}
				]],
				toolbar:[
					{id:'btnedit',text:'查看变更明细',btnType:'edit'},
                    {  
                        text : "导出变更明细",  
                        iconCls : "icon-undo",  
                        handler : function() {  
 					        toExcel();
						}
                    } , {  
                        text : "导出变更后计划",  
                        iconCls : "icon-undo",  
                        handler : function() {  
 					        toExcelJh();
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
	YiYa.jtjhChangeBrow.init();
});		
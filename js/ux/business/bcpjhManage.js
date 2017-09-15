$package('YiYa.bcpjhManage');

YiYa.bcpjhManage = function(){
 
	var _box = null;
	var _this = {
 
		config:{
	
   	
 			dataGrid:{
  				title:'半成品计划列表',
				url:'dataList.do',
				height : 540,  
                pagination:true,
			    nowrap: true,
  			    pageSize:16,
			    pageList: [10, 16, 30, 40, 50],
				singleSelect:true,	
                frozenColumns:[[
				    {field:'id',checkbox:true},
					{field:'xdrq',title:'下达日期',width:70,sortable:true,formatter:function formatterdate(value,row,index) {
                        if (value != null) { 
						    var date = new Date(value);
                            return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                             + date.getDate();
                         }}
                     },			
					{field:'jhbh',title:'计划编号',width:90,sortable:true},
					{field:'row',title:'序号',width:30},
			    	{field:'sbmc',title:'机台',width:60,sortable:true},
			    	{field:'iszl',title:'主零',width:30,sortable:true},
			    	{field:'gxxh',title:'型号',width:80,sortable:true},
			    	{field:'gxgg',title:'规格',width:80,sortable:true},
			    	{field:'gxdy',title:'电压',width:40,sortable:true},
			    	{field:'gxgy',title:'工艺',width:30},
			    	{field:'gxdw',title:'单位',width:30},
			    	{field:'jhsl',title:'计划数量',width:60,align:'right' }
		        ]],
	   			columns:[[
	
				    {field:'qbWg',title:'全部完工',width:70,align:'center',sortable:true,styler:function(value,row,index){
							if(value != 0){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
							
							if(value == 1){
								return "已全部完工";
							}
                         
					 }},
					 {field:'sumWgsl',title:'合计完工数量',width:80,align:'right' },					 
					 {field:'wwgsl',title:'未完工数量',width:80,align:'right' },
			         {field:'sumWgslds',title:'完工段数明细',width:120},
			         {field:'jhrq',title:'计划日期',width:80,sortable:true,formatter:function formatterdate(value,row,index) {
                        if (value != null) {
                        var date = new Date(value);
                        return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                         + date.getDate();
                         }}
                     },
                     {field:'maxWgrq',title:'最后完工日期',width:80,sortable:true,formatter:function formatterdate(value,row,index) {
                         if (value != null) {
                             var date = new Date(value);
                             return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                              + date.getDate();
                          }}
                     },
			    	 {field:'gxjsyq',title:'技术要求',width:120},
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
 

				     {field:'createBy',title:'计划员',width:80,sortable:true},
				     {field:'createTime',title:'日期',width:150,sortable:true}

				]],
			    toolbar:[
					{id:'btnadd',text:'增加',btnType:'add'},
					{id:'btnedit',text:'修改',btnType:'edit'}
					//{id:'btndelete',text:'删除',btnType:'remove'}
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
	YiYa.bcpjhManage.init();
});		
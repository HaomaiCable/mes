$package('YiYa.jtjhWghbManage');

YiYa.jtjhWghbManage = function(){
 
	var _box = null;
	var _this = {
 
		config:{
			action:{
 				save:'saveWghb.do',
				getId:'getWghb.do'
			},
   	
 			dataGrid:{
  				title:'机台计划列表',
				height : 500,  
			    nowrap: true,
			    pagination:true,
                pageSize:100,
			    pageList: [100, 200, 300, 400, 500],				
				singleSelect:true,	
                frozenColumns:[[
					{field:'id',checkbox:true},
				    {field:'cqts',title:'超期天数',width:60,align:'center',sortable:true,styler:function(value,row,index){
				        if(value >0 ){
						  return 'color:red;';  
					    }
					},
					formatter:function(value,row,index){
						
					        if (row.state==2)
						    {
							    return '暂停';
						    }
						    else if (row.state==3)
						    {  
							    return '作废';
						    }
						    else{
						        if(value == -9999){
							        return '未到期';
						        }
                                else if(value == 0){
							        return '按期';
						        }
						        else{
							        return '('+value+')天';
						        }
						    }

					}},
					{field:'xdrq',title:'下达日期',width:80,sortable:true,formatter:function formatterdate(value,row,index) {
                        if (value != null) { 
						    var date = new Date(value);
                            return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                             + date.getDate();
                         }}
                     },			
					{field:'jhbh',title:'计划编号',width:120,sortable:true},
					{field:'row',title:'序号',width:30},
			    	{field:'sbmc',title:'机台',width:80,sortable:true},
			    	{field:'iszl',title:'主零',width:30,sortable:true},
			    	{field:'gxxh',title:'型号',width:100,sortable:true},
			    	{field:'gxgg',title:'规格',width:80,sortable:true},
			    	{field:'gxdy',title:'电压',width:60,sortable:true},
		    	    {field:'gxlb',title:'类别',width:50,sortable:true},
			    	{field:'gxgy',title:'工艺',width:30},
			    	{field:'gxdw',title:'单位',width:30},
			    	{field:'jhsl_xs',title:'产品数量',width:65,align:'right' },
			    	{field:'jhsl_o',title:'机台数量',width:65,align:'right' }
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
			    	 {field:'gxph',title:'批号',width:120},
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
 
				     {field:'gxph',title:'批号',width:100,sortable:true},
				     {field:'createBy',title:'计划员',width:80,sortable:true},
				     {field:'createTime',title:'日期',width:150,sortable:true}

				]],
				toolbar:[
					{id:'btnedit',text:'完工汇报',btnType:'edit'}
				
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
	YiYa.jtjhWghbManage.init();
});		
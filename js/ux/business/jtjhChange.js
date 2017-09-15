$package('YiYa.jtjhChange');

YiYa.jtjhChange = function(){
 
	var _box = null;
	var _this = {
 
		config:{

   	
 			dataGrid:{
  				title:'机台计划列表',
	   			url:'dataList.do',
				height : 500,  
			    nowrap: false,
				pagination: true,
  			    pageSize:16,
			    pageList: [10, 16, 30, 40, 50],
   			    columns:[[
					{field:'id',checkbox:true},
	                {field:'bgDetails',title:'变更明细',width:80,align:'center',formatter:function(value,row,index){
						if (row.subCountBg!=0)
						{
						    var html ="<a href='#' onclick='toBgList("+row.id+")'>变更明细("+row.subCountBg+")</a>";
						    return html;
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
					{field:'gd',title:'工段',width:80,sortable:true},
			    	{field:'sbmc',title:'机台',width:80,sortable:true},
			    	{field:'iszl',title:'主零',width:30,sortable:true},
			    	{field:'gxxh',title:'型号',width:100,sortable:true},
			    	{field:'gxgg',title:'规格',width:80,sortable:true},
			    	{field:'gxdy',title:'电压',width:60,sortable:true},
			    	{field:'gxgy',title:'工艺',width:30},
			    	{field:'gxdw',title:'单位',width:30},
			    	{field:'gxlb',title:'类别',width:40},
			    	{field:'jhsl_xs',title:'产品数量',width:65,align:'right' },
			    	{field:'jhsl_o',title:'机台数量',width:65,align:'right' },		
			        {field:'jhrq',title:'计划日期',width:80,sortable:true,formatter:function formatterdate(value,row,index) {
                        if (value != null) {
                        var date = new Date(value);
                        return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                         + date.getDate();
                         }}
                     },
   
			    	 {field:'gxjsyq',title:'技术要求',width:120},
			    	 {field:'gxph',title:'批号',width:140},
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
 

				     {field:'createBy',title:'计划员',width:80,sortable:true}


				]],
				toolbar:[
					{id:'btnedit',text:'机台计划变更',btnType:'edit'}
				
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
	YiYa.jtjhChange.init();
});		
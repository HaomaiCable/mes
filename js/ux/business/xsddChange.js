$package('YiYa.xsddChange');

YiYa.xsddChange = function(){
 
	var _box = null;
	var _this = {
 
		config:{
			action:{
 				save:'',//'saveDdbg.do',
				getId:'getDdbg.do'
			},
   	
 			dataGrid:{
  				title:'销售订单列表',
	   			url:'dataList.do',
				height : 500,  
			    nowrap: false,
				pagination: true,
  			    pageSize:100,
			    pageList: [100, 200, 300, 400, 500],
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
					{field:'jhbh',title:'计划编号',width:80,sortable:true},
					{field:'row',title:'行号',width:40},
					{field:'ywy',title:'业务员',width:60,sortable:true},
			    	{field:'xh',title:'产品型号',width:150,sortable:true},
			    	{field:'gg',title:'产品规格',width:120,sortable:true},
			    	{field:'dy',title:'电压等级',width:60,sortable:true},
			    	{field:'gy',title:'工艺',width:40},
			    	{field:'dw',title:'单位',width:40},
			    	{field:'ph',title:'批号',width:140},
			    	{field:'sl',title:'计划数量',width:60,align:'right' },
			        {field:'jhrq_kh',title:'需求交货期',width:70,sortable:true,formatter:function formatterdate(value,row,index) {
                        if (value != null) {
                        var date = new Date(value);
                        return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                         + date.getDate();
                         }}
                     },
			        {field:'jhrq',title:'计划交货期',width:70,sortable:true,formatter:function formatterdate(value,row,index) {
                        if (value != null) {
                        var date = new Date(value);
                        return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                         + date.getDate();
                         }}
                     },
			    	{field:'jsyq',title:'技术要求',width:260},
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
				    {field:'xdjt',title:'下达机台',width:60,align:'center',sortable:true,styler:function(value,row,index){
							if(value == 1){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
	
							if(value == 1){
								return "下达机台";
							}
   
						}},
				    {field:'createBy',title:'订单员',width:80,sortable:true}

				]],
				toolbar:[
					{id:'btnedit',text:'订单变更',btnType:'edit'}
				
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
	YiYa.xsddChange.init();
});		
$package('YiYa.jtjhManage');

YiYa.jtjhManage = function(){
	var _box = null;
	var _this = {

		config:{
			action:{
  				save:'', //新增&修改 保存Action  
  				getId:'',//编辑获取的Action
  				delele:''//删除数据的Action
  			},
 	
 			dataGrid:{
  				title:'销售订单列表',
	   			url:'../xsddManage/dataList.do',
				height : 315,  
			    nowrap: true,
				pagination:true,
				pagination: true,
  			    pageSize:8,
			    pageList: [8, 20, 30, 40, 50],
				singleSelect:true,	
   	   			columns:[[
					{field:'id',checkbox:true},
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
                    {field:'jhDetails',title:'计划明细',width:80,align:'center',formatter:function(value,row,index){
						if (row.subCountJtjh!=0)
						{
						    var html ="<a href='#' onclick='toJtjhList("+row.id+")'>机台计划("+row.subCountJtjh+")</a>";
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
					{field:'jhbh',title:'计划编号',width:90,sortable:true},
					{field:'row',title:'行号',width:40},

			    	{field:'xh',title:'产品型号',width:150,sortable:true},
			    	{field:'gg',title:'产品规格',width:120,sortable:true},
			    	{field:'dy',title:'电压等级',width:60,sortable:true},
			    	{field:'gy',title:'工艺',width:40},
			    	{field:'dw',title:'单位',width:40},
	
			    	{field:'sl',title:'计划数量',width:60,align:'right' },

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

				    {field:'createBy',title:'订单员',width:80,sortable:true}

				]],
		        toolbar:[
					{id:'btnedit',text:'下达机台',btnType:'edit'}
				
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
	YiYa.jtjhManage.init();
});		
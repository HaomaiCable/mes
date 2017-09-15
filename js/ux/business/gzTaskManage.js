$package('YiYa.gzTaskManage');

YiYa.gzTaskManage = function(){
	var _box = null;
	var _this = {

		config:{
			action:{
  				save:'', //新增&修改 保存Action  
  				getId:'',//编辑获取的Action
  				delele:''//删除数据的Action
  			},
 	
 			dataGrid:{
  				title:'工作计划列表',
	   			url:'dataList.do',
				height : 500,  
			    nowrap: false,
				pagination:true,
				pagination: true,
  			    pageSize:100,
			    pageList: [100, 200, 300, 400, 500],
				singleSelect:true,	
                frozenColumns:[[
					{field:'id',checkbox:true},

					{field:'rq',title:'下达日期',width:70,sortable:true,formatter:function formatterdate(value,row,index) {
                        if (value != null) { 
						    var date = new Date(value);
                            return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                             + date.getDate();
                         }}
                     },
					{field:'tcr',title:'任务提出',width:80,sortable:true},
					{field:'ly',title:'任务来源',width:80,sortable:true},
				    {field:'createBy',title:'任务承担人',width:60,sortable:true},
			    	{field:'cyry',title:'参与人员',width:120,sortable:true},
			    	{field:'rwName',title:'任务名称',width:160,sortable:true}
				]],
	   			columns:[[
	
			    	{field:'rwContent',title:'任务描述',width:160,sortable:true},
			    	{field:'rwResult',title:'任务结果',width:120,sortable:true},
					{field:'wcrq_yq',title:'要求完成日期',width:80,sortable:true,formatter:function formatterdate(value,row,index) {
                        if (value != null) { 
						    var date = new Date(value);
                            return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                             + date.getDate();
                         }}
                     },
					{field:'wcrq',title:'实际完成日期',width:80,sortable:true,formatter:function formatterdate(value,row,index) {
                        if (value != null) { 
						    var date = new Date(value);
                            return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                             + date.getDate();
                         }}
                     },
			    	{field:'wcResult',title:'完成结果说明',width:250,sortable:true},
					{field:'report',title:'汇报',width:50,align:'center',sortable:true,styler:function(value,row,index){
							if(value != 0){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
							
							if(value == 1){
								return "已汇报";
							}
                 
						}},
					{field:'reportTime',title:'汇报时间',width:150,sortable:true},

					{field:'accept',title:'确认',width:50,align:'center',sortable:true,styler:function(value,row,index){
							if(value != 0){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
							
							if(value == 1){
								return "已确认";
							}
                 
						}},

				    {field:'acceptBy',title:'确认人',width:60,sortable:true},
					{field:'acceptTime',title:'确认时间',width:150,sortable:true},
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


				]],
		        toolbar:[
					{id:'btnadd',text:'新建',btnType:'add'},
				    {id:'btndelete',text:'删除',btnType:'remove'},
				    {id:'btnedit',text:'汇报',btnType:'edit'},

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
	YiYa.gzTaskManage.init();
});		
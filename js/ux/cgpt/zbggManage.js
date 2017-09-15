$package('YiYa.zbggManage');

YiYa.zbggManage = function(){
	var _box = null;
	var _this = {
	
		config:{
			action:{
  				save:'', //新增&修改 保存Action  
  				getId:'',//编辑获取的Action
  				delele:''//删除数据的Action
  			},
 	
 			dataGrid:{
  				title:'采购招标公告列表',
	   			url:'dataList.do',
				height : 500,  
			    nowrap: false,
				pagination: true,
  			    pageSize:100,
			    pageList: [100, 200, 300, 400, 500],
				singleSelect:true,	
	   			columns:[[
					{field:'id',checkbox:true},
				    {field:'kb',title:'开标',width:50,align:'center',sortable:true,styler:function(value,row,index){
							if(value == 1){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
	
							if(value == 1){
								return "已开标";
							}
							else if(value == 2){
								return "有投标";
							}
   
					}},
				    {field:'yxrq',title:'投标截止日期',width:140,sortable:true},
					{field:'ggbh',title:'公告编号',width:100,sortable:true},
					{field:'fbrq',title:'发布日期',width:80,sortable:true,formatter:function formatterdate(value,row,index) {
                        if (value != null) { 
						    var date = new Date(value);
                            return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                             + date.getDate();
                         }}
                     },

					{field:'row',title:'行号',width:40},
					{field:'wlfl',title:'采购分类',width:120,sortable:true},
			    	{field:'wlmc',title:'物料名称',width:120,sortable:true},
			    	{field:'wlgg',title:'物料规格',width:120,sortable:true},
			    	{field:'wldw',title:'单位',width:40},
			    	{field:'wlsl',title:'采购数量',width:80,align:'right' },
			        {field:'jhrq_xq',title:'需求交货期',width:70,sortable:true,formatter:function formatterdate(value,row,index) {
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
				    {field:'createBy',title:'采购员',width:80,sortable:true}

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
	YiYa.zbggManage.init();
});		
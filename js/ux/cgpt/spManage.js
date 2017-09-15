$package('YiYa.spManage');

YiYa.spManage = function(){
	var _box = null;
	var _this = {
	
		config:{
			action:{
  				save:'', //新增&修改 保存Action  
  				getId:'',//编辑获取的Action
  				delele:''//删除数据的Action
  			},
 	
 			dataGrid:{
  				title:'已选定供应商的招标公告列表',
	   			url:'dataListDist.do',
				height : 315,  
			    nowrap: true,
			    pagination:true,
  			    pageSize:8,
			    pageList: [8, 20, 30, 40, 50],
				singleSelect:true,	
	            columns:[[
					{field:'id',checkbox:true},
				    {field:'kb',title:'确认供应商',width:80,align:'center',sortable:true,styler:function(value,row,index){
							if(value == 1){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
	
							if(value == 1){
								return "已确认供应商";
							}
						  
					}},
                    {field:'countZbwxs',title:'投标明细',width:80,align:'center',formatter:function(value,row,index){
						if (row.subCountTb!=0)
						{
							var html ="<a href='#' onclick='toTbList("+row.ggbh+")'>投标明细("+row.subCountTb+")</a>";
						    return html;
						}
					}},
					{field:'ggbh',title:'公告编号',width:100,sortable:true},
					{field:'fbrq',title:'发布日期',width:80,sortable:true,formatter:function formatterdate(value,row,index) {
                        if (value != null) { 
						    var date = new Date(value);
                            return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                             + date.getDate();
                         }}
                     },

					{field:'wlfl',title:'采购分类',width:120,sortable:true},
				    {field:'yxrq',title:'投标截止日期',width:140,sortable:true},
				    {field:'qdBy',title:'中标确认',width:70,sortable:true},
					{field:'qdTime',title:'确认时间',width:130,sortable:true},
				    {field:'spBy',title:'审批人',width:70,sortable:true},
					{field:'spTime',title:'审批时间',width:130,sortable:true},
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
					{id:'btnedit',text:'审批中标供应商',btnType:'edit'}
				
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
	YiYa.spManage.init();
});	
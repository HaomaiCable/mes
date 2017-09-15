$package('YiYa.tbManage');

YiYa.tbManage = function(){
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
	   			url:'dataListDist.do',
				height : 315,  
			    nowrap: true,
			    pagination:true,
  			    pageSize:8,
			    pageList: [8, 20, 30, 40, 50],
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
                    {field:'countZbwxs',title:'投标明细',width:80,align:'center',formatter:function(value,row,index){
						if (row.subCountTb!=0)
						{
						    //var ggbh="'"+row.ggbh+"'";
							//var obj = new Object(); 
			                //obj.ggbh = row.ggbh;
							//alert(ggbh);
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
					{id:'btnedit',text:'开标',btnType:'edit'}
				
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
	YiYa.tbManage.init();
});	
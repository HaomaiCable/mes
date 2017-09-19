$package('YiYa.gzhsJtkqManage');
YiYa.gzhsJtkqManage = function(){
	var _box = null;
	var _this = {
	    plkqAction:'getPlkq.do',
		editPlkqForm:function(){
			return $("#plkqForm");
		},
		editPlkqWin:function(){
			return $("#edit-plkq-win");
		},
		config:{
		    dataGrid:{
  				title:'机台考勤列表',
	   			url:'dataList.do?gdid=1',  //拔丝工段
				height :520,  
				pagination: true,
                pageSize:100,
			    pageList: [100, 200, 300, 400, 500],
	   			columns:[[
					{field:'id',checkbox:true},
					{field:'gd',title:'工段',width:80,sortable:true},
					{field:'sbmc',title:'机台名称',width:80,sortable:true},
					{field:'ygxm',title:'员工姓名',width:80,sortable:true},
					{field:'dexs',title:'定额系数',width:80,align:'right' },
					{field:'kqrq',title:'考勤日期',width:80,sortable:true,formatter:function formatterdate(value,row,index) {
                        if (value != null) { 
						    var date = new Date(value);
                            return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                             + date.getDate();
                         }}
                    },
					{field:'bc',title:'班次',width:60,align:'center',sortable:true,styler:function(value,row,index){
						if(value == 2){
						  return 'color:red;';  
						}
					},
					formatter:function(value,row,index){
						if(value == 2){
					    return "夜班";
						}
					}},
					{field:'cqgs',title:'出勤工时',width:80,align:'right' },
					{field:'lrBy',title:'录入',width:80,sortable:true},
					{field:'lrTime',title:'时间',width:150,sortable:true}
				]],
		        toolbar:[
					{id:'btnadd',text:'增加',btnType:'add'},
					{id:'btnedit',text:'修改',btnType:'edit'},
					{id:'btndelete',text:'删除',btnType:'remove'},
					{
						id:'btnedit',text:'批量考勤',btnType:'editPlkq',iconCls:'icon-edit',handler:function(){
						_this.editPlkqForm().resetForm();
						_this.editPlkqWin().window('open'); 
					}},
                    {  
                       text : "导出Excel",  
                       iconCls : "icon-undo",  
                       handler : function() {  
 				 	      toExcel();
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
	YiYa.gzhsJtkqManage.init();
});		
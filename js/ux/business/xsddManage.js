$package('YiYa.xsddManage');

YiYa.xsddManage = function(){
	var _box = null;
	var _this = {
	    chooseFileWin:function(){
			return $("#import-excel-win");
		},
	    updateBhAction:'updateBh.do',
		editBhForm:function(){
			return $("#bhForm");
		},
		editBhWin:function(){
			return $("#edit-bh-win");
		},
		saveBh:function(){
			if(_this.editBhForm().form('validate')){
				_this.editBhForm().attr('action',_this.updateBhAction);
				YiYa.saveForm(_this.editBhForm(),function(data){
					_this.editBhWin().dialog('close');
					if(data.success){  
       				    var param = $("#searchForm").serializeObject();
			            $('#data-list').datagrid('reload',param);
                        $.messager.alert("提示", data.msg);   
                    }  
					else
					{
	                     $.messager.alert("提示",data.msg);  	
					}

				});
			 }
		},
		initForm:function(){
			//修改订单编号
			_this.editBhWin().find("#btn-bh-submit").click(function(){
				_this.saveBh();
			});
			_this.editBhWin().find("#btn-bh-close").click(function(){	
				$.messager.confirm('提示','你要放弃对订单编号的修改吗?',function(r){  
				    if (r){  
				     	_this.editBhWin().dialog('close');
				    }  
				});
			});
		},
		config:{
			action:{
  				save:'', //新增&修改 保存Action  
  				getId:'',//编辑获取的Action
  				delele:''//删除数据的Action
  			},
 	
 			dataGrid:{
  				title:'销售订单列表',
	   			url:'dataList.do',
				height : 500,  
			    nowrap: false,
				pagination: true,
  			    pageSize:100,
			    pageList: [100, 200, 300, 400, 500],
				singleSelect:true,	
	   			columns:[[
					{field:'id',checkbox:true},
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
			    	{field:'sl',title:'计划数量',width:60,align:'right' },
			    	{field:'jsyq',title:'技术要求',width:260},
		    	    {field:'ph',title:'批号',width:140},
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
					{id:'btnadd',text:'增加',btnType:'add'},
					{id:'btnedit',text:'修改',btnType:'edit'},
					//{id:'btndelete',text:'删除',btnType:'remove'},
			        {
						id:'btnfromExcel',
						text:'从Excel导入',
						disabled: false,
						iconCls:'icon-redo',
						handler:function(){
							_this.chooseFileWin().window('open'); 
						}
					},
					{
						id:'btnedit',text:'修改订单编号',btnType:'editBh',iconCls:'icon-edit',handler:function(){
						var selected = _box.utils.getCheckedRows();
						if ( _box.utils.checkSelectOne(selected)){
							_this.editBhForm().resetForm();
							_this.editBhForm().find("input[name='jhbh']").val(selected[0].jhbh);
							_this.editBhForm().find("input[name='id']").val(selected[0].id);
							_this.editBhWin().window('open'); 
						}
					}},
				]
			}
		},
		init:function(){
			_this.initForm();
			_box = new YDataGrid(_this.config); 
			_box.init();
		
		}
	}
	return _this;
}();

$(function(){
	YiYa.xsddManage.init();
});		
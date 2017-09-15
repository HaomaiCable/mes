$package('YiYa.cljzManage');

YiYa.cljzManage = function(){
	var _box = null;
	var _this = {
	    getCljzAction:'getCljzForCpId.do',
		editCljzForm:function(){
			return $("#cljzForm");
		},
		editCljzWin:function(){
			return $("#edit-cljz-win");
		},	
			
		getCljz:function(id){
	       var data ={};
		   var idKey = 'id'; //主键名称
 		   data[idKey] = id;
           YiYa.getById(_this.getCljzAction,data,function(result){
			    //_this.editCljzForm.form('load',result.json);
				_this.editCljzWin().dialog('open'); 
				 $("#dg-clcpcljz").datagrid('loadData', result.json);	
		
			});     
		},


		config:{
 		    dataGrid:{
  				title:'产品及材料净重明细',
	   			url:'dataList.do',
				height: 365,  
				nowrap:true,
				pagination: true,
  			    pageSize:10,
			    pageList: [10, 20, 30, 40, 50],
             	columns:[[
					{field:'id',checkbox:true},
			    	{field:'cpxh',title:'产品型号',width:160,sortable:true},
			    	{field:'cpgg',title:'产品规格',width:160,sortable:true},
			    	{field:'cpdy',title:'电压等级',width:160,sortable:true},
	                {field:'jzDetails',title:'材料净重明细',width:100,align:'center',formatter:function(value,row,index){
						if (row.subCountJz!=0)
						{
						    var html ="<a href='#' onclick='toCljzList("+row.id+")'>材料净重明细("+row.subCountJz+")</a>";
						    return html;
						}
					}}
				]],
		        toolbar:[
					{id:'btnadd',text:'增加',btnType:'add'},
					{id:'btnedit',text:'修改',btnType:'edit'},
					{id:'btndelete',text:'删除',btnType:'remove'},
			    	{
						id:'btnedit',text:'修改材料净重',btnType:'editCljz',iconCls:'icon-edit',handler:function(){
						var selected = _box.utils.getCheckedRows();
						if ( _box.utils.checkSelectOne(selected)){
							_this.editCljzForm().resetForm();
							//_this.editCljzForm().find("input[name='jhbh']").val(selected[0].jhbh);
							_this.editCljzForm().find("input[name='cpid']").val(selected[0].id);
							_this.editCljzForm().find("input[name='cpxh']").val(selected[0].cpxh);
							_this.editCljzForm().find("input[name='cpgg']").val(selected[0].cpgg);
							_this.editCljzForm().find("input[name='cpdy']").val(selected[0].cpdy);
							_this.getCljz(selected[0].id);
						}
					}},
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
	YiYa.cljzManage.init();
});		
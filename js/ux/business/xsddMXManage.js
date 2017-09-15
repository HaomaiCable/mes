$package('YiYa.xsddMxManage');

YiYa.xsddMxManage = function(){
	//Grid DataList
    //var _this = {
	    var Grid = $('#data-listmx');
	    dataGrid:{
 		    title:'销售订单明细列表',
		    url:'dataList.do',
		    columns:[[
		    	{field:'id',checkbox:true},
			    {field:'xdrq',title:'下达日期',width:120,sortable:true},
			    {field:'jhbh',title:'计划编号',width:120,sortable:true},
			    {field:'ywy',title:'业务员',width:120,sortable:true},
			    {field:'jhrq_kh',title:'客户交货期',width:120,sortable:true},
			    {field:'createBy',title:'订单员',width:100,sortable:true},
			    {field:'createTime',title:'录入时间',width:120,sortable:true},
			    {field:'childItems',title:'订单明细查看',width:120,align:'center',formatter:function(value,row,index){
				    var html ="<a href='#' onclick='YiYa.xsddManage.toList("+row.id+")'>订单明细管理("+row.subCount+")</a>";
				    return html;
			    }}
		    ]],
		    toolbar:[
			    {id:'btnadd',text:'Add',btnType:'add'},
			    {id:'btnedit',text:'Edit',btnType:'edit'},
			    {id:'btndelete',text:'Remove',btnType:'remove'},
			    {
				    id:'btnback',
				    text:'back',
				    disabled: true,
				    iconCls:'icon-back',
				    handler:function(){
					    _this.toList();
				    }
			    }
		    ]
	    },
	    //初始化表格
	    var initGrid = function(){
	    	var dataconfig = {
	    		title: dataGrid.title || 'Data List',
		    	iconCls: dataGrid.iconCls || 'icon-save',
			    height: dataGrid.height || 265,
			    nowrap: true,
			    autoRowHeight: false,
			    striped: true,
			    collapsible:true,
			    remoteSort: false,
			    pagination:true,
			    rownumbers:true,
			    singleSelect:false,
			    checkOnSelect:false,
			    selectOnCheck:false,
			    url: dataGrid.url,
			    method: dataGrid.method || 'post',
			    loadMsg: dataGrid.loadMsg || 'Loading in ...',
			    idField: dataGrid.idField,
			    columns: dataGrid.columns,
			    //toolbar: getToolbar(),
			    onLoadSuccess: dataGrid.onLoadSuccess || function(){
				    Grid.datagrid('unselectAll');
				    Grid.datagrid('uncheckAll');
			    },
			    onSelect:function(rowIndex, rowData){
				    //选择一行
				    var rows = Grid.datagrid('getRows');
				    $.each(rows,function(i){
				    	if(i != rowIndex){
					    	Grid.datagrid('uncheckRow',i);
					    	Grid.datagrid('unselectRow',i);
				    	}
				    })
				    Grid.datagrid('checkRow',rowIndex);
			    }
		    };
	    	Grid.datagrid(dataconfig);
	    }

        //this 返回属性
	    this.grid = Grid;

		
	    //初始化方法
	    this.init = function(){
		    initGrid();
	    }
	}
	//调用初始化
	return this;
};

$(function(){
	YiYa.xsddMxManage.init();
});		
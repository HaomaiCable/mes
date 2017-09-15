<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <%@include file="/view/resource.jsp" %>
   	<script type="text/javascript">
		$(function(){
			$('#tt').edatagrid({
			});
		});
		$(function(){
			$('#data-listmx').edatagrid({
			});
		});
	</script>
  </head>
<body>
<div class="warp easyui-panel"  data-options="border:false">
  	 <!-- Search panel start -->
 	 <div class="easyui-panel ui-search-panel" title="查询窗口" data-options="striped: true,collapsible:true,iconCls:'icon-search'">   
 	 <form id="searchForm">
 	 	<input class="hidden" id='search_parentId' name="parentId">
 	 	<p class="ui-fields">
            <label class="ui-label">计划号:</label> 
            <input name="jhbh" class="easyui-box ui-text" style="width:100px;">
  	 	    <label class="ui-label">业务员:</label><input name="ywy" class="easyui-box ui-text" style="width:100px;">
            <label class="ui-label">订单员: </label><input name="createBy" class="easyui-box ui-text" style="width:100px;">
        </p>
        <a id="btn-search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
      </form>  
     </div> 
     <!--  Search panel end -->
     <!-- DataList  -->
     <form id="listForm" method="post" >
     <table id="data-list"></table>
 	  <!-- Edit Win&From -->
     <div id="edit-win" class="easyui-dialog" title="销售订单下达" data-options="closed:true,iconCls:'icon-edit',modal:true"  style="width:1000px;height:560px;">  
     	<form id="editForm" class="ui-form" method="post"> 
     	  <!-- 隐藏文本框 -->
     	  <input class="hidden" name="id">
     	  <input class="hidden" name="btid">
    	  <div class="easyui-panel" border='false' style="height:450;">  
	        <div class="easyui-layout" data-options="fit:true">  
	          <div data-options="region:'north',split:true" style="height:90px;padding:10px">  
	             <div class="ftitle">信息</div>    
		            <div class="fitem">  
					   <label>下达时间:</label>  
					   <input class="easyui-datebox" name="xdrq" label="计划日期:"   data-options="formatter:myformatter,parser:myparser" style="width:100%;">
		               <label>计划单号:</label>  
                       <input class="easyui-validatebox" type="text" name="jhbh" style="width:100px;" data-options="required:true">
		               <label>业务员:</label>  
		               <input type="text" name="ywy"></input>
					    <label>客户要求交货期:</label>  
					   <input class="easyui-datebox" name="jhrq_kh"  labelPosition="left"  data-options="formatter:myformatter,parser:myparser" style="width:100%;">
		            </div> 
	            </div>
	          
			    <div data-options="region:'center'">  
	              <!-- 	<div id="toolbar">  
				        <a href="javascript:void(0)" id='addLine_btn' class="easyui-linkbutton" iconCls="icon-add" plain="true" >Add</a>  
				        <a href="javascript:void(0)" id='addDefLine_btn'class="easyui-linkbutton" iconCls="icon-add" plain="true" >AddDefault</a>
				        <a href="javascript:void(0)" id='delAllLine_btn'class="easyui-linkbutton" iconCls="icon-remove" plain="true" >Delete All</a>  
				    </div>  
				    
					<table id="btn-tb" style="width:100%">
				    	<thead>
				    	<tr>
				    		<th width="25%">产品型号</th>
				    		<th width="25%">产品规格</th>
				    		<th width="35%">电压等级</th>
				    		<th width="10%">操作</th>
				    	</tr>
				    	</thead>
				    	<tbody>
				    	</tbody>
				    </table> -->
	            <table id="tt" style="height:auto"
			           url="${msUrl}/xsddManage/dataListBlank.do"
			           singleSelect="true"
					   pagination="true"
					   rownumbers="true"
					  	iconCls="icon-edit"
					   >
		         <thead>
			      <tr>
				    <th field="jhbh" width="80" >计划编号</th>
				    <th field="xh" width="150" editor="{type:'validatebox',options:{required:true}}">产品型号</th>
				    <th field="gg" width="120" editor="{type:'validatebox',options:{required:true}}">产品规格</th>					
					<th field="dy" width="80" editor="text">电压等级</th>
				    <th field="gy" width="40" editor="text">工艺</th>
			  	    <th field="dw" width="40" editor="text">单位</th>
			  	    <th field="ph" width="80" editor="text">批号</th>
				    <th field="sl" width="80" align="right" editor="{type:'numberbox',options:{precision:2}}">计划数量</th>
				    <th field="jhrq_kh" width="100"  editor="datebox">客户要求交货期</th>
				    <th field="jsyq" width="250" editor="text">技术要求</th>
			     </tr>
		       </thead>

	           </table>
	
		         <div style="margin-bottom:10px">
		              <a href="#" onclick="javascript:$('#tt').edatagrid('addRow')"  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">增加行</a>
		              <a href="#" onclick="javascript:$('#tt').edatagrid('saveRow')" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" >保存行</a>
		              <a href="#" onclick="javascript:$('#tt').edatagrid('cancelRow')" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true">作废行</a>
		              <a href="#" onclick="javascript:$('#tt').edatagrid('destroyRow')" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除行</a>
	              </div>
			</div> 	
				    <!-- <table id="dg" class="easyui-datagrid" title="销售订单明细" style="width:960px;height:auto"
			           data-options="
				       iconCls: 'icon-edit',
				       singleSelect: true,
				       toolbar: '#tb',
				       url: 'datagrid_data1.json',
				       method: 'get',
				       onClickCell: onClickCell,
				       onEndEdit: onEndEdit">
		               <thead>
			               <tr>
				              <th data-options="field:'jhbh',width:80">计划编号</th>
				              <th data-options="field:'xh',width:150,
						      formatter:function(value,row){
							       return row.productname;
						      },
						      editor:{
							       type:'combobox',
							       options:{
								     valueField:'productid',
								     textField:'productname',
								     method:'get',
								     url:'products.json',
								     required:true
							   }
						     }">产品型号</th>
				            <th data-options="field:'gg',width:120,editor:'textbox'">产品规格</th>
				            <th data-options="field:'dy',width:80,editor:'textbox'">电压等级</th>
				            <th data-options="field:'gy',width:40,editor:'textbox'">工艺</th>
				            <th data-options="field:'dw',width:40,editor:'textbox'">单位</th>
						    <th data-options="field:'ph',width:80,editor:'textbox'">批号</th>
				            <th data-options="field:'sl',width:60,align:'right',editor:'numberbox'">计划数量</th>
				            <th data-options="field:'jhrq_kh',width:100,editor:'datebox'">客户要求交货期</th>
				            <th data-options="field:'jsyq',width:250,editor:'textbox'">技术要求</th>
				            <!--<th data-options="field:'status',width:60,align:'center',editor:{type:'checkbox',options:{on:'P',off:''}}">Status</th> 
			            </tr>
		            </thead>
	             </table>  -->
	

		  <!--<div id="tb" style="height:auto">
		            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">Append</a>
	                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">Remove</a>
		            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">Accept</a>
		            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">Reject</a>
		            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="getChanges()">GetChanges</a>
	               </div>	-->
		
	      </div>
	  </div>
	</div>
  </form>
  </div>
</div>

  <script type="text/javascript" src="${msUrl}/js/commons/YDataGrid.js"></script>
  <script type="text/javascript" src="${msUrl}/js/ux/business/xsddManage.js"></script> 
  <!--<script type="text/javascript" src="${msUrl}/js/ux/business/xsddMxManage.js"></script>  -->
<script type="text/javascript">
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
</script>

<script type="text/javascript">
		function myformatter(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
		}
		function myparser(s){
			if (!s) return new Date();
			var ss = (s.split('-'));
			var y = parseInt(ss[0],10);
			var m = parseInt(ss[1],10);
			var d = parseInt(ss[2],10);
			if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
				return new Date(y,m-1,d);
			} else {
				return new Date();
			}
		}
</script>
<script type="text/javascript">
		var editIndex = undefined;
		function endEditing(){
			if (editIndex == undefined){return true}
			if ($('#dg').datagrid('validateRow', editIndex)){
				$('#dg').datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
		}
		function onClickCell(index, field){
			if (editIndex != index){
				if (endEditing()){
					$('#dg').datagrid('selectRow', index)
							.datagrid('beginEdit', index);
					var ed = $('#dg').datagrid('getEditor', {index:index,field:field});
					if (ed){
						($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
					}
					editIndex = index;
				} else {
					setTimeout(function(){
						$('#dg').datagrid('selectRow', editIndex);
					},0);
				}
			}
		}
		function onEndEdit(index, row){
			var ed = $(this).datagrid('getEditor', {
				index: index,
				field: 'productid'
			});
			row.productname = $(ed.target).combobox('getText');
		}
		function append(){
			if (endEditing()){
				$('#dg').datagrid('appendRow',{status:'P'});
				editIndex = $('#dg').datagrid('getRows').length-1;
				$('#dg').datagrid('selectRow', editIndex)
						.datagrid('beginEdit', editIndex);
			}
		}
		function removeit(){
			if (editIndex == undefined){return}
			$('#dg').datagrid('cancelEdit', editIndex)
					.datagrid('deleteRow', editIndex);
			editIndex = undefined;
		}
		function accept(){
			if (endEditing()){
				$('#dg').datagrid('acceptChanges');
			}
		}
		function reject(){
			$('#dg').datagrid('rejectChanges');
			editIndex = undefined;
		}
		function getChanges(){
			var rows = $('#dg').datagrid('getChanges');
			alert(rows.length+' rows are changed!');
		}
</script>
</body>
</html>

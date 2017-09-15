<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <%@include file="/view/resource.jsp" %>
   	<!--<script type="text/javascript">
		$(function(){
			$('#tt').edatagrid({
			});
		});
		$(function(){
			$('#data-listmx').edatagrid({
			});
		});
	</script>   -->
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
  	 	    <label class="ui-label">产品型号:</label><input name="xh" class="easyui-box ui-text" style="width:100px;">
            <label class="ui-label">产品规格: </label><input name="gg" class="easyui-box ui-text" style="width:100px;">
            <label class="ui-label">交货日期: </label><input name="jhrq" class="easyui-datebox" style="width:100px;">
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
					   <input class="easyui-datebox"  name="xdrq"   style="width:100%;">
		               <label>计划单号:</label>  
                       <input class="easyui-validatebox" type="text" name="jhbh" style="width:100px;" data-options="required:true">
		               <label>业务员:</label>  
		               <input type="text" name="ywy"></input>
					    <label>客户要求交货期:</label>  
					   <input class="easyui-datebox" name="jhrq_kh"    style="width:100%;">
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
	          <!--  <table id="tt" style="height:auto"
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
			-->
				   <!--
				   <table id="dg" class="easyui-datagrid" title="销售订单明细" style="width:960px;height:auto"
			          data-options="
				       iconCls: 'icon-edit',
				       singleSelect: true,

					   url: 'dataListBlank.do',
				       method: 'get',
				       onClickCell: onClickCell,
				       onEndEdit: onEndEdit">
		         <thead>
			      <tr>
				    <th field="jhbh" width="80" >计划编号</th>
				    <th field="xh" width="150" editor="validatebox">产品型号</th>
				    <th field="gg" width="120" editor="validatebox">产品规格</th>					
					<th field="dy" width="80" editor="textbox">电压等级</th>
				    <th field="gy" width="40" editor="textbox">工艺</th>
			  	    <th field="dw" width="40" editor="textbox">单位</th>
			  	    <th field="ph" width="80" editor="textbox">批号</th>
				    <th field="sl" width="80" align="right" editor="numberbox">计划数量</th>
				    <th field="jhrq_kh" width="100"  editor="datebox">客户要求交货期</th>
				    <th field="jsyq" width="250" editor="textbox">技术要求</th>
			     </tr>
		       </thead> 
	             </table>  


		  <div  id="ft" style="padding:2px 5px;" >
		            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">Append</a>
	                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">Remove</a>
		            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">Accept</a>
		            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">Reject</a>
		            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="getChanges()">GetChanges</a>
	     </div>	-->
	     <table id="dg">
	         <thead>
	            <tr>
		        <th field="jhbh" width="80" >计划编号</th>
      	        <th field="xh" width="150" editor="validatebox">产品型号</th>
		        <th field="gg" width="120" editor="validatebox">产品规格</th>					
			    <th field="dy" width="80" editor="text">电压等级</th>
		        <th field="gy" width="40" editor="text">工艺</th>
	  	        <th field="dw" width="40" editor="text">单位</th>
	  	        <th field="ph" width="80" editor="text">批号</th>
		        <th field="sl" width="80" align="right" editor="numberbox">计划数量</th>
		        <th field="jhrq_kh" width="100"  editor="datebox" >客户要求交货期</th>
		        <th field="jsyq" width="250" editor="text">技术要求</th>
		        </tr>
		     </thead> 
	     </table>	
         <!--<div  id="toobar" style="padding:2px 5px;" >
	         <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">增加行</a>
		     <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="edit()">编辑行</a>
	         <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除行</a>
		     <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">结束编辑</a>
		     <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="submit()">保存提交</a>
	     </div> -->
	  </div>
	</div>
  </form>
  </div>
</div>

  <script type="text/javascript" src="${msUrl}/js/commons/json.js"></script>
  <script type="text/javascript" src="${msUrl}/js/commons/YDataGrid.js"></script>
  <script type="text/javascript" src="${msUrl}/js/ux/business/xsddManage.js"></script> 
  <!--<script type="text/javascript" src="${msUrl}/js/ux/business/xsddMxManage.js"></script>  -->
<script type="text/javascript">  
     $(function() {  
        var $dg = $("#dg");  
        $dg.datagrid({ 
            url : '',  
            // width : 700,  
            //height : 250,  
			singleSelect:true,
			pagination:true,
		    rownumbers:true,
			ft:'#toolbar',
          /** columns : [ [ {  
			    field : "jhbh", 
				title : "计划编号", 
				width : 80 
			}, { 
                field : 'xh',  
                title : '产品型号',  
                width : 150,  
                editor : "validatebox"  
            }, {  
                field : 'gg',  
                title : '产品规格',  
                width : 120,  
                editor : "validatebox"  
            }, {  
                field : 'dy',  
                title : '电压等级',  
                width : 80,  
                editor : "text"  
            }, {  
                field : 'gy',  
                title : '工艺',  
                width : 40,  
                editor : "text"  
            }, {  
               field : 'dw',  
                title : '单位',  
                width : 40,  
                editor : "text"  
            }, {  
                field : 'ph',  
                title : '批号',  
                width : 80,  
                editor : "text"  
            }, {  
                field : 'sl',  
                title : '计划数量',  
                width : 80,  
                align : 'right',  
                editor : "numberbox"  
            }, {  
                field : 'jhrq_kh',  
                title : '要求交货期',  
                width : 80,  
                editor : "datebox" ,
				formatter:function formatterdate(value,row,index) {
                        if (value != null) {
                        var date = new Date(value);
                        return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                         + date.getDate();
                         }}
                }, {  
                field : 'jsyq',  
                title : '技术要求',  
                width : 250,  
                editor : "text"
            } ] ], */
	
  
            toolbar : [ {  
                text : "添加",  
                iconCls : "icon-add",  
                handler : function() {  
                    $dg.datagrid('appendRow', {});  
                    var rows = $dg.datagrid('getRows');  
                    $dg.datagrid('beginEdit', rows.length - 1);  
                }  
            }, {  
                text : "编辑",  
                iconCls : "icon-edit",  
                handler : function() {  
                    var row = $dg.datagrid('getSelected');  
                    if (row) {  
                        var rowIndex = $dg.datagrid('getRowIndex', row);  
                        $dg.datagrid('beginEdit', rowIndex);  
                    }  
                }  
            }, {  
                text : "删除",  
                iconCls : "icon-remove",  
                handler : function() {  
                    var row = $dg.datagrid('getSelected');  
                    if (row) {  
                        var rowIndex = $dg.datagrid('getRowIndex', row);  
                        $dg.datagrid('deleteRow', rowIndex);  
                    }  
                }  
            }, {  
                text : "结束编辑",  
                iconCls : "icon-save",  
                handler :endEdit  
            }, {  
                text : "保存提交",  
                iconCls : "icon-save",  
                handler : function() {  
                    endEdit();  
                    if ($dg.datagrid('getChanges').length) {  
                        var inserted = $dg.datagrid('getChanges', "inserted");  
                        var deleted = $dg.datagrid('getChanges', "deleted");  
                        var updated = $dg.datagrid('getChanges', "updated");  
                          
                        var effectRow = new Object();  
                        if (inserted.length) {  
                            effectRow["inserted"] = JSON.stringify(inserted);  
                        }  
                        if (deleted.length) {  
                            effectRow["deleted"] = JSON.stringify(deleted);  
                        }  
                        if (updated.length) {  
                            effectRow["updated"] = JSON.stringify(updated);  
                        }  
  
                        $.post("${msUrl}/xsddManage/add.do", effectRow, function(rsp) {  
                            if(rsp.status){  
                                $.messager.alert("提示", "提交成功！");  
                                $dg.datagrid('acceptChanges');  
                            }  
                        }, "JSON").error(function() {  
		                        // $.messager.alert("提示", "提交失败！");  
                        });  
                    }  
                }  
            } ]  
        });  
         function endEdit(){  
            var rows = $dg.datagrid('getRows');  
            for ( var i = 0; i < rows.length; i++) {  
                $dg.datagrid('endEdit', i);  
            }  
       }        

   });
  
	/**function append(){
        $dg.datagrid('appendRow', {});  
        var rows = $dg.datagrid('getRows');  
        $dg.datagrid('beginEdit', rows.length - 1);  
	}
	function edit(){
         var row = $dg.datagrid('getSelected');  
         if (row) {  
                var rowIndex = $dg.datagrid('getRowIndex', row);  
                $dg.datagrid('beginEdit', rowIndex); 
		}
	}
	function removeit{
        var row = $dg.datagrid('getSelected');  
        if (row) {  
             var rowIndex = $dg.datagrid('getRowIndex', row);  
             $dg.datagrid('deleteRow', rowIndex);  
        }  
     } 
	function accept(){
        endEdit();
     }  
	function submit(){
        endEdit();  
        if ($dg.datagrid('getChanges').length) {  
            var inserted = $dg.datagrid('getChanges', "inserted");  
            var deleted = $dg.datagrid('getChanges', "deleted");  
            var updated = $dg.datagrid('getChanges', "updated");  
            var effectRow = new Object();  
            if (inserted.length) {  
                effectRow["inserted"] = JSON.stringify(inserted);  
            }  
            if (deleted.length) {  
                effectRow["deleted"] = JSON.stringify(deleted);  
            }  
            if (updated.length) {  
                effectRow["updated"] = JSON.stringify(updated);  
            }  
  
            $.post("${msUrl}/xsddManage/add.do", effectRow, function(rsp) {  
                if(rsp.status){  
                     $.messager.alert("提示", "提交成功！");  
                     $dg.datagrid('acceptChanges');  
                }  
            }, "JSON").error(function() {  
		             $.messager.alert("提示", "提交失败！");  
            });  
        }  
   }  */
</script>  

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


<script type="text/javascript">
/*
    json.js
    2011-08-30

    Public Domain

    No warranty expressed or implied. Use at your own risk.

    This file has been superceded by http://www.JSON.org/json2.js

    See http://www.JSON.org/js.html

    This code should be minified before deployment.
    See http://javascript.crockford.com/jsmin.html

    USE YOUR OWN COPY. IT IS EXTREMELY UNWISE TO LOAD CODE FROM SERVERS YOU DO
    NOT CONTROL.

    This file adds these methods to JavaScript:

        object.toJSONString(whitelist)
            This method produce a JSON text from a JavaScript value.
            It must not contain any cyclical references. Illegal values
            will be excluded.

            The default conversion for dates is to an ISO string. You can
            add a toJSONString method to any date object to get a different
            representation.

            The object and array methods can take an optional whitelist
            argument. A whitelist is an array of strings. If it is provided,
            keys in objects not found in the whitelist are excluded.

        string.parseJSON(filter)
            This method parses a JSON text to produce an object or
            array. It can throw a SyntaxError exception.

            The optional filter parameter is a function which can filter and
            transform the results. It receives each of the keys and values, and
            its return value is used instead of the original value. If it
            returns what it received, then structure is not modified. If it
            returns undefined then the member is deleted.

            Example:

            // Parse the text. If a key contains the string 'date' then
            // convert the value to a date.

            myData = text.parseJSON(function (key, value) {
                return key.indexOf('date') >= 0 ? new Date(value) : value;
            });

    This file will break programs with improper for..in loops. See
    http://yuiblog.com/blog/2006/09/26/for-in-intrigue/

    This file creates a global JSON object containing two methods: stringify
    and parse.

        JSON.stringify(value, replacer, space)
            value       any JavaScript value, usually an object or array.

            replacer    an optional parameter that determines how object
                        values are stringified for objects. It can be a
                        function or an array of strings.

            space       an optional parameter that specifies the indentation
                        of nested structures. If it is omitted, the text will
                        be packed without extra whitespace. If it is a number,
                        it will specify the number of spaces to indent at each
                        level. If it is a string (such as '\t' or '&nbsp;'),
                        it contains the characters used to indent at each level.

            This method produces a JSON text from a JavaScript value.

            When an object value is found, if the object contains a toJSON
            method, its toJSON method will be called and the result will be
            stringified. A toJSON method does not serialize: it returns the
            value represented by the name/value pair that should be serialized,
            or undefined if nothing should be serialized. The toJSON method
            will be passed the key associated with the value, and this will be
            bound to the object holding the key.

            For example, this would serialize Dates as ISO strings.

                Date.prototype.toJSON = function (key) {
                    function f(n) {
                        // Format integers to have at least two digits.
                        return n < 10 ? '0' + n : n;
                    }

                    return this.getUTCFullYear()   + '-' +
                         f(this.getUTCMonth() + 1) + '-' +
                         f(this.getUTCDate())      + 'T' +
                         f(this.getUTCHours())     + ':' +
                         f(this.getUTCMinutes())   + ':' +
                         f(this.getUTCSeconds())   + 'Z';
                };

            You can provide an optional replacer method. It will be passed the
            key and value of each member, with this bound to the containing
            object. The value that is returned from your method will be
            serialized. If your method returns undefined, then the member will
            be excluded from the serialization.

            If the replacer parameter is an array of strings, then it will be
            used to select the members to be serialized. It filters the results
            such that only members with keys listed in the replacer array are
            stringified.

            Values that do not have JSON representations, such as undefined or
            functions, will not be serialized. Such values in objects will be
            dropped; in arrays they will be replaced with null. You can use
            a replacer function to replace those with JSON values.
            JSON.stringify(undefined) returns undefined.

            The optional space parameter produces a stringification of the
            value that is filled with line breaks and indentation to make it
            easier to read.

            If the space parameter is a non-empty string, then that string will
            be used for indentation. If the space parameter is a number, then
            the indentation will be that many spaces.

            Example:

            text = JSON.stringify(['e', {pluribus: 'unum'}]);
            // text is '["e",{"pluribus":"unum"}]'


            text = JSON.stringify(['e', {pluribus: 'unum'}], null, '\t');
            // text is '[\n\t"e",\n\t{\n\t\t"pluribus": "unum"\n\t}\n]'

            text = JSON.stringify([new Date()], function (key, value) {
                return this[key] instanceof Date ?
                    'Date(' + this[key] + ')' : value;
            });
            // text is '["Date(---current time---)"]'


        JSON.parse(text, reviver)
            This method parses a JSON text to produce an object or array.
            It can throw a SyntaxError exception.

            The optional reviver parameter is a function that can filter and
            transform the results. It receives each of the keys and values,
            and its return value is used instead of the original value.
            If it returns what it received, then the structure is not modified.
            If it returns undefined then the member is deleted.

            Example:

            // Parse the text. Values that look like ISO date strings will
            // be converted to Date objects.

            myData = JSON.parse(text, function (key, value) {
                var a;
                if (typeof value === 'string') {
                    a =
/^(\d{4})-(\d{2})-(\d{2})T(\d{2}):(\d{2}):(\d{2}(?:\.\d*)?)Z$/.exec(value);
                    if (a) {
                        return new Date(Date.UTC(+a[1], +a[2] - 1, +a[3], +a[4],
                            +a[5], +a[6]));
                    }
                }
                return value;
            });

            myData = JSON.parse('["Date(09/09/2001)"]', function (key, value) {
                var d;
                if (typeof value === 'string' &&
                        value.slice(0, 5) === 'Date(' &&
                        value.slice(-1) === ')') {
                    d = new Date(value.slice(5, -1));
                    if (d) {
                        return d;
                    }
                }
                return value;
            });


    This is a reference implementation. You are free to copy, modify, or
    redistribute.
*/

/*jslint evil: true, regexp: true, unparam: true */

/*members "", "\b", "\t", "\n", "\f", "\r", "\"", JSON, "\\", apply,
    call, charCodeAt, getUTCDate, getUTCFullYear, getUTCHours,
    getUTCMinutes, getUTCMonth, getUTCSeconds, hasOwnProperty, join,
    lastIndex, length, parse, parseJSON, prototype, push, replace, slice,
    stringify, test, toJSON, toJSONString, toString, valueOf
*/


// Create a JSON object only if one does not already exist. We create the
// methods in a closure to avoid creating global variables.

var JSON;
if (!JSON) {
    JSON = {};
}

(function () {
    'use strict';

    function f(n) {
        // Format integers to have at least two digits.
        return n < 10 ? '0' + n : n;
    }

    if (typeof Date.prototype.toJSON !== 'function') {

        Date.prototype.toJSON = function (key) {

            return isFinite(this.valueOf()) ?
                this.getUTCFullYear()     + '-' +
                f(this.getUTCMonth() + 1) + '-' +
                f(this.getUTCDate())      + 'T' +
                f(this.getUTCHours())     + ':' +
                f(this.getUTCMinutes())   + ':' +
                f(this.getUTCSeconds())   + 'Z' : null;
        };

        String.prototype.toJSON      =
            Number.prototype.toJSON  =
            Boolean.prototype.toJSON = function (key) {
                return this.valueOf();
            };
    }

    var cx = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
        escapable = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,
        gap,
        indent,
        meta = {    // table of character substitutions
            '\b': '\\b',
            '\t': '\\t',
            '\n': '\\n',
            '\f': '\\f',
            '\r': '\\r',
            '"' : '\\"',
            '\\': '\\\\'
        },
        rep;


    function quote(string) {

// If the string contains no control characters, no quote characters, and no
// backslash characters, then we can safely slap some quotes around it.
// Otherwise we must also replace the offending characters with safe escape
// sequences.

        escapable.lastIndex = 0;
        return escapable.test(string) ? '"' + string.replace(escapable, function (a) {
            var c = meta[a];
            return typeof c === 'string' ? c :
                '\\u' + ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
        }) + '"' : '"' + string + '"';
    }


    function str(key, holder) {

// Produce a string from holder[key].

        var i,          // The loop counter.
            k,          // The member key.
            v,          // The member value.
            length,
            mind = gap,
            partial,
            value = holder[key];

// If the value has a toJSON method, call it to obtain a replacement value.

        if (value && typeof value === 'object' &&
                typeof value.toJSON === 'function') {
            value = value.toJSON(key);
        }

// If we were called with a replacer function, then call the replacer to
// obtain a replacement value.

        if (typeof rep === 'function') {
            value = rep.call(holder, key, value);
        }

// What happens next depends on the value's type.

        switch (typeof value) {
        case 'string':
            return quote(value);

        case 'number':

// JSON numbers must be finite. Encode non-finite numbers as null.

            return isFinite(value) ? String(value) : 'null';

        case 'boolean':
        case 'null':

// If the value is a boolean or null, convert it to a string. Note:
// typeof null does not produce 'null'. The case is included here in
// the remote chance that this gets fixed someday.

            return String(value);

// If the type is 'object', we might be dealing with an object or an array or
// null.

        case 'object':

// Due to a specification blunder in ECMAScript, typeof null is 'object',
// so watch out for that case.

            if (!value) {
                return 'null';
            }

// Make an array to hold the partial results of stringifying this object value.

            gap += indent;
            partial = [];

// Is the value an array?

            if (Object.prototype.toString.apply(value) === '[object Array]') {

// The value is an array. Stringify every element. Use null as a placeholder
// for non-JSON values.

                length = value.length;
                for (i = 0; i < length; i += 1) {
                    partial[i] = str(i, value) || 'null';
                }

// Join all of the elements together, separated with commas, and wrap them in
// brackets.

                v = partial.length === 0 ? '[]' : gap ?
                    '[\n' + gap + partial.join(',\n' + gap) + '\n' + mind + ']' :
                    '[' + partial.join(',') + ']';
                gap = mind;
                return v;
            }

// If the replacer is an array, use it to select the members to be stringified.

            if (rep && typeof rep === 'object') {
                length = rep.length;
                for (i = 0; i < length; i += 1) {
                    k = rep[i];
                    if (typeof k === 'string') {
                        v = str(k, value);
                        if (v) {
                            partial.push(quote(k) + (gap ? ': ' : ':') + v);
                        }
                    }
                }
            } else {

// Otherwise, iterate through all of the keys in the object.

                for (k in value) {
                    if (Object.prototype.hasOwnProperty.call(value, k)) {
                        v = str(k, value);
                        if (v) {
                            partial.push(quote(k) + (gap ? ': ' : ':') + v);
                        }
                    }
                }
            }

// Join all of the member texts together, separated with commas,
// and wrap them in braces.

            v = partial.length === 0 ? '{}' : gap ?
                '{\n' + gap + partial.join(',\n' + gap) + '\n' + mind + '}' :
                '{' + partial.join(',') + '}';
            gap = mind;
            return v;
        }
    }

// If the JSON object does not yet have a stringify method, give it one.

    if (typeof JSON.stringify !== 'function') {
        JSON.stringify = function (value, replacer, space) {

// The stringify method takes a value and an optional replacer, and an optional
// space parameter, and returns a JSON text. The replacer can be a function
// that can replace values, or an array of strings that will select the keys.
// A default replacer method can be provided. Use of the space parameter can
// produce text that is more easily readable.

            var i;
            gap = '';
            indent = '';

// If the space parameter is a number, make an indent string containing that
// many spaces.

            if (typeof space === 'number') {
                for (i = 0; i < space; i += 1) {
                    indent += ' ';
                }

// If the space parameter is a string, it will be used as the indent string.

            } else if (typeof space === 'string') {
                indent = space;
            }

// If there is a replacer, it must be a function or an array.
// Otherwise, throw an error.

            rep = replacer;
            if (replacer && typeof replacer !== 'function' &&
                    (typeof replacer !== 'object' ||
                    typeof replacer.length !== 'number')) {
                throw new Error('JSON.stringify');
            }

// Make a fake root object containing our value under the key of ''.
// Return the result of stringifying the value.

            return str('', {'': value});
        };
    }


// If the JSON object does not yet have a parse method, give it one.

    if (typeof JSON.parse !== 'function') {
        JSON.parse = function (text, reviver) {

// The parse method takes a text and an optional reviver function, and returns
// a JavaScript value if the text is a valid JSON text.

            var j;

            function walk(holder, key) {

// The walk method is used to recursively walk the resulting structure so
// that modifications can be made.

                var k, v, value = holder[key];
                if (value && typeof value === 'object') {
                    for (k in value) {
                        if (Object.prototype.hasOwnProperty.call(value, k)) {
                            v = walk(value, k);
                            if (v !== undefined) {
                                value[k] = v;
                            } else {
                                delete value[k];
                            }
                        }
                    }
                }
                return reviver.call(holder, key, value);
            }


// Parsing happens in four stages. In the first stage, we replace certain
// Unicode characters with escape sequences. JavaScript handles many characters
// incorrectly, either silently deleting them, or treating them as line endings.

            text = String(text);
            cx.lastIndex = 0;
            if (cx.test(text)) {
                text = text.replace(cx, function (a) {
                    return '\\u' +
                        ('0000' + a.charCodeAt(0).toString(16)).slice(-4);
                });
            }

// In the second stage, we run the text against regular expressions that look
// for non-JSON patterns. We are especially concerned with '()' and 'new'
// because they can cause invocation, and '=' because it can cause mutation.
// But just to be safe, we want to reject all unexpected forms.

// We split the second stage into 4 regexp operations in order to work around
// crippling inefficiencies in IE's and Safari's regexp engines. First we
// replace the JSON backslash pairs with '@' (a non-JSON character). Second, we
// replace all simple value tokens with ']' characters. Third, we delete all
// open brackets that follow a colon or comma or that begin the text. Finally,
// we look to see that the remaining characters are only whitespace or ']' or
// ',' or ':' or '{' or '}'. If that is so, then the text is safe for eval.

            if (/^[\],:{}\s]*$/
                    .test(text.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, '@')
                        .replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, ']')
                        .replace(/(?:^|:|,)(?:\s*\[)+/g, ''))) {

// In the third stage we use the eval function to compile the text into a
// JavaScript structure. The '{' operator is subject to a syntactic ambiguity
// in JavaScript: it can begin a block or an object literal. We wrap the text
// in parens to eliminate the ambiguity.

                j = eval('(' + text + ')');

// In the optional fourth stage, we recursively walk the new structure, passing
// each name/value pair to a reviver function for possible transformation.

                return typeof reviver === 'function' ?
                    walk({'': j}, '') : j;
            }

// If the text is not JSON parseable, then a SyntaxError is thrown.

            throw new SyntaxError('JSON.parse');
        };
    }

// Augment the basic prototypes if they have not already been augmented.
// These forms are obsolete. It is recommended that JSON.stringify and
// JSON.parse be used instead.


    if (!Object.prototype.toJSONString) {
        Object.prototype.toJSONString = function (filter) {
            return JSON.stringify(this, filter);
        };
        Object.prototype.parseJSON = function (filter) {
            return JSON.parse(this, filter);
        };

}());

</script>
</body>
</html>

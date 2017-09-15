<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
       <%@include file="/view/resource.jsp" %>
  </head>
<body>
<div class="warp easyui-panel"  data-options="border:false">
  	 <!-- Search panel start -->
 	 <div class="easyui-panel ui-search-panel" title="查询窗口" data-options="striped: true,collapsible:true,iconCls:'icon-search'">   
 	     <form id="searchForm">
 	 	    <input class="hidden" id='search_parentId' name="parentId">
 	 	    <p class="ui-fields">
				<label class="ui-label">下达日期: </label><input name="fromxdrq" class="easyui-datebox" style="width:100px;">
				<label class="ui-label">至: </label><input name="toxdrq" class="easyui-datebox" style="width:100px;">	 	
                <label class="ui-label">计划号:</label> 

                <input name="jhbh" class="easyui-box ui-text" style="width:80px;">
      
    
               <label>订单员:</label>  
		            <input class="easyui-combobox"  name="createBy" style="width:60px;"   
				    data-options="
			        url:'getUniDdy.do',
					multiple:false,
			        valueField:'text',
			        textField:'text'">
  	 	       
                <label>产品型号:</label>  
		            <input class="easyui-combobox"  name="xh"  
				    data-options="
	                url:'getUniXh.do',
				    method:'get',
					
			        multiple:false,
			        valueField:'text',
			        textField:'text'">

                <label>产品规格:</label>  
		            <input  class="easyui-combobox"  name="gg"  
				    data-options="
	                url:'getUniGg.do',
			        method:'get',
				    
			        multiple:false,
			        valueField:'text',
			        textField:'text'">
               <!-- <label class="ui-label">计划号:</label> 
                <input name="jhbh" class="easyui-box ui-text" style="width:100px;">
  	            <label class="ui-label">订单员: </label><input name="createBy" class="easyui-box ui-text" style="width:100px;">
  	 	        <label class="ui-label">产品型号:</label><input name="xh" class="easyui-box ui-text" style="width:100px;">
                <label class="ui-label">产品规格: </label><input name="gg" class="easyui-box ui-text" style="width:100px;">-->
                <!--<label class="ui-label">交货日期: </label><input name="jhrq" class="easyui-datebox" style="width:100px;"
				    data-options="formatter:function(value,row,index) {
                        if (value != null) {
                           var date = new Date(value);
                           return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                           + date.getDate();
                        }
				 }">-->
            </p>
            <a id="btn-search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
        </form>  
     </div> 
     <!--  Search panel end -->
     <!-- DataList  -->
     <form id="listForm" method="post" >
         <table id="data-list" >
		 </table>
 	 </form>	
	  <!-- Edit Win&From -->

	 <div id="edit-win-dlgbuttons" class="dialog-button" >
       
	     <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" 
		   onclick=" cancelquit();">放弃修改</a>
     </div>
     <div id="edit-win" class="easyui-dialog"  buttons="#edit-win-dlgbuttons" 
	     title="入库汇报" 
	     data-options="closed:true,iconCls:'icon-edit',modal:true"  
		 style="width:1000px;height:560px;">  
     	 <form id="editForm" class="ui-form" method="post"> 
     	      <!-- 隐藏文本框 -->
     	      <input class="hidden" name="id">
			  <input class="hidden" name="parentId" id='edit_parentId'>
    	      <div class="easyui-panel" border='false' style="height:450;">  
	              <div class="easyui-layout" data-options="fit:true">  
	                  <div data-options="region:'north',split:true" style="height:100px;padding:2px">  
	                     <div class="ftitle">信息</div>    
		                 <div class="fitem">  
						    <input class="hidden" name="ddid" >
		                    <label>计划单号:</label>  
                            <input  type="text" name="jhbh" style="width:80;" readonly="readonly">					        
		                    <label>行号:</label>  
                            <input type="text" name="row" style="width:20;" readonly="readonly">		
							<label>交货期:</label>  
					        <input class="text"  name="jhrq"   style="width:80;"  readonly="readonly">
						    <label>技术要求:</label>  
					        <input type="datebox"  name="jsyq"   style="width:150;"  readonly="readonly">
						    <label>状态:</label>  
					        <input type="text"  name="state"   style="width:40;"  readonly="readonly"
							    data-options="styler:function(value,row,index){
							        if(value != 1){
							            return 'color:red;';  
							        }
						        },
						        formatter:function(value,row,index){
							
							       if(value == 2){
								       return '暂停';
							       }
                                  if(value == 3){
								    return '作废';
							       }
						        }">
					    </div>
						<div class="fitem">  
			                <label>产品型号:</label>  
                            <input  type="text" name="xh" style="width:120px;" readonly="readonly">	
			                <label>产品规格:</label>  
                            <input  type="text" name="gg" style="width:120px;" readonly="readonly">	
			                <label>电压等级:</label>  
                            <input  type="text" name="dy" style="width:60px;" readonly="readonly">	
			                <label>工艺:</label>  
                            <input  type="text" name="gy" style="width:30px;" readonly="readonly">	
			                <label>计划数量:</label>  
                            <input  type="numberbox" name="sl" style="width:60px;" readonly="readonly">	
						
		                </div> 
	                </div>
	          
                    <div data-options="region:'center'">  
                         <table id="dg-xsddrkhb" >
                            <thead>
                            <tr>
   	                        <input class="hidden" name="id">
	                        <th data-options="field:'rkrq', width:100,editor:'datebox', required:true,
                                  formatter:function(value,row,index) {
                                      if (value != null) {
                                          var date = new Date(value);
                                          return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                                          + date.getDate();
                                       }
								   }
								  
							">入库日期</th>
	                         <th field="rksl" width="80" align="right" 
							      data-options="editor:{type:'numberbox',options:{precision:2}}, required:true"
							 >入库数量</th>
  
   	                        <th field="rksm" width="200" editor="text">入库说明</th>
						
						   
							<th data-options="field:'rk',width:80,
						       editor:{
							      type:'combobox',
							      options:{
								     valueField:'id',
								     textField:'text',
								     url:'${msUrl}/selectdata/rk.json'
	                              }
								 }
						     ">全部入库完毕</th>   
   	
   		                  	
  		                    <th field="lrBy" width="70" >录入</th>
	                        <th field="lrTime" width="150" >时间</th>
	                        </tr>
	                        </thead> 
                        </table>	
                    </div>
               </div>
		   </div>
       </form>
   </div>
 
</div>

  <script type="text/javascript" src="${msUrl}/js/commons/YDataGrid.js"></script>
  <script type="text/javascript" src="${msUrl}/js/ux/business/xsddRkReport.js"></script> 
 
<script type="text/javascript"> 
      function cancelquit(){ 
       $.messager.confirm('提示','你确认要放弃以上修改吗',function(r){  
	       if (r){  
		       //endEdit(); 
               $('#edit-win').dialog('close');
		       $('#edit-win').resetForm();

		       $("#dg-xsddwghb").datagrid('reload');
		       $("#dg-xsddwghb").datagrid('loadData', { total: 0, rows: [] }); 

			   //$('#data-list').datagrid('load');
		       var param = $("#searchForm").serializeObject();
		       $('#data-list').datagrid('reload',param);
           }
	   })
    }

 	$(function() {  
        var $dg = $("#data-list");  
        $dg.datagrid({ 
            height : 500,  
			pageSize:16,
			pageList: [10, 16, 30, 40, 50],
		    onHeaderContextMenu: function(e, field){
		        e.preventDefault();
			    if (!cmenu){
			  	    createColumnMenu();
			    }
			    cmenu.menu('show', {
			        left:e.pageX,
			        top:e.pageY
			    });
		    }
 	    });
    });
    var cmenu = null;
    function createColumnMenu(){
		cmenu = $('<div/>').appendTo('body');
		cmenu.menu({
			onClick: function(item){
				if (item.iconCls == 'icon-ok'){
					$('#data-list').datagrid('hideColumn', item.name);
					cmenu.menu('setIcon', {
						target: item.target,
						iconCls: 'icon-empty'
					});
				 } else {
					 $('#data-list').datagrid('showColumn', item.name);
					 cmenu.menu('setIcon', {
						target: item.target,
						iconCls: 'icon-ok'
				   });
				}
			}
		});
		var fields = $('#data-list').datagrid('getColumnFields');
		for(var i=0; i<fields.length; i++){
			var field = fields[i];
			var col = $('#data-list').datagrid('getColumnOption', field);
			cmenu.menu('appendItem', {
				text: col.title,
				name: field,
				iconCls: 'icon-ok'
			});
		}
	}

  
	$(function() {  
        var $dg = $("#dg-xsddrkhb");  

        $dg.datagrid({ 
         
			nowrap: false,
			singleSelect:true,
			//pagination:true,
		    rownumbers:true,
			//pageSize:16,
			//pageList: [10, 16, 30, 40, 50],
			onClickRow: onClickRow,
            toolbar : [ {  
                text : "添加行",  
                iconCls : "icon-add",  
                handler : function() {  
                    $dg.datagrid('appendRow', {});  
                    var rows = $dg.datagrid('getRows');  
                    $dg.datagrid('beginEdit', rows.length - 1);  
                }  
            }, {  
                text : "编辑行",  
                iconCls : "icon-edit",  
                handler : function() {  

                    var row = $dg.datagrid('getSelected');  
                    if (row) {  
                        var rowIndex = $dg.datagrid('getRowIndex', row);  
                        $dg.datagrid('beginEdit', rowIndex);  
                    }  
                }  
            }, {  
                text : "删除行",  
                iconCls : "icon-remove",  
                handler : function() {  
                    var row = $dg.datagrid('getSelected');  
                    if (row) {  
                        var rowIndex = $dg.datagrid('getRowIndex', row);  
                        $dg.datagrid('deleteRow', rowIndex);  
                    }  
                }  
            },'-', {  
                text : "接受编辑",  
                iconCls : "icon-ok",  
                handler :endEdit  
            }, {  
                text : "保存提交",  
                iconCls : "icon-save",  
                handler : function() {  
 					submit();
                 }  
            } ]  
        });  
        function endEdit(){  
            var rows = $dg.datagrid('getRows');  
            for ( var i = 0; i < rows.length; i++) {  
                $dg.datagrid('endEdit', i);  
            }  
        } 
        function onClickRow(){

	        var row = $dg.datagrid('getSelected');  
                if (row) {  
                    var rowIndex = $dg.datagrid('getRowIndex', row);  
                    $dg.datagrid('beginEdit', rowIndex);  
                }  
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
				//var ywy = $("input[name='ywy' type='text']").val(); 

	 			var ddid=$("#editForm input:input[name='ddid']").val();  //销售订单ID
	            effectRow["ddid"] =ddid;
	

                $.post("${msUrl}/xsddManage/saveRkhb.do", effectRow,function(rsp) {  
                   if(rsp.success){  
  					    var param = $("#searchForm").serializeObject();
			            $('#data-list').datagrid('reload',param);
                        $.messager.alert("提示", rsp.msg);   
                    }  
					else
					{
	                     $.messager.alert("提示",rsp.msg);  	
					}

               }, "JSON");
			   $dg.datagrid('acceptChanges');  
			   $('#edit-win').dialog('close');
			 
			   $('#edit-win').resetForm();
		
			   $("#dg-xsddwghb").datagrid('reload');
			   $dg.datagrid('loadData', { total: 0, rows: [] }); 
               var param = $("#searchForm").serializeObject();
			   $('#data-list').datagrid('reload',param);
			

            }  
        }

	
   });
 
  function initdate(){
     $("#searchForm input:input[name='fromxdrq']").val("${requestScope.fromdate}");
     $("#searchForm input:input[name='toxdrq']").val("${requestScope.todate}");
 
  }
  document.body.onload=initdate(); 
</script>  



</body>
</html>

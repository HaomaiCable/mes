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

    
            <label>产品型号:</label>  
		    <input class="easyui-combobox"  name="cpxh"  
				    data-options="
	                url:'getUniXh.do',
				    method:'get',
					multiple:false,
			        valueField:'text',
			        textField:'text'">
            <label>产品规格:</label>  
		    <input class="easyui-combobox"  name="cpgg"  
				    data-options="
	                url:'getUniGg.do',
				    method:'get',
					multiple:false,
			        valueField:'text',
			        textField:'text'">
            <label>电压:</label>  
		    <input class="easyui-combobox"  name="cpdy"  
				    data-options="
	                url:'getUniDy.do',
				    method:'get',
					multiple:false,
			        valueField:'text',
			        textField:'text'">
     
        </p>
        <a id="btn-search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
     </form>  
     </div> 
     <!--  Search panel end -->
     <!-- DataList  -->
     <form id="listForm" method="post" >
     <table id="data-list"></table>

	 <form id="listcjlzForm" method="post" >
     <table id="data-cljzlist" class="easyui-datagrid" 
         data-options="
		     url:'dataListForCpId.do?id='+0,
			 height:140,
			 nowrap:true,
			 striped: true,
			 collapsible:true,
			 remoteSort: false,
		     method:  'post',
		     rownumbers:true,
			 singleSelect:true
		">
        <thead>
        <tr>
        <th data-options="field:'clmc',width:160,sortable:true">材料名称</th>
        <th data-options="field:'cljz',width:120">材料净重</th>
 
	    </tr>
		</thead>
	</table>  
    </form>
     <!-- Edit Form -->
     <div id="edit-win" class="easyui-dialog" title="Edit" data-options="closed:true,iconCls:'icon-save',modal:true" style="width:400px;height:410px;">  
     	 <form id="editForm" class="ui-form" method="post">  
     		 <input class="hidden" type="text" name="id">
     	     <div class="ui-edit">
	             <div class="ftitle">产品信息</div>    
	             <div class="fitem">  
	                <label>产品型号:</label>  
	                <input class="easyui-validatebox" type="text" name="cpxh" data-options="required:true,validType:'length[1,60]'">
	             </div>
		         <div class="fitem">  
	                <label>产品规格:</label>  
	                <input class="easyui-validatebox" type="text" name="cpgg" data-options="required:true,validType:'length[1,60]'">
	             </div>
		         <div class="fitem">  
	                <label>电    压:</label>  
	                <input class="easyui-validatebox" type="text" name="cpdy" data-options="required:true,validType:'length[1,60]'">
	             </div>
	       </div>	               
       </form>
   </div> 
	  <!-- Edit Cljz Win&From -->

	 <div id="edit-cljz-win-dlgbuttons" class="dialog-button" >
       
	     <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" 
		   onclick=" cancelquit();">放弃修改</a>
     </div>
     <div id="edit-cljz-win" class="easyui-dialog"  buttons="#edit-cljz-win-dlgbuttons" 
	     title="完工数量汇报" 
	     data-options="closed:true,iconCls:'icon-edit',modal:true"  
		 style="width:1000px;height:460px;">  
     	 <form id="cljzForm" class="ui-form" method="post"> 
     	      <!-- 隐藏文本框 -->
     	      <input class="hidden" name="id">
    	      <div class="easyui-panel" border='false' style="height:350;">  
	              <div class="easyui-layout" data-options="fit:true">  
	                  <div data-options="region:'north',split:true" style="height:80px;padding:2px">  
	                     <div class="ftitle">产品信息</div>    
		                 <div class="fitem">  
						    <input class="hidden" name="cpid">
		                    <label>产品型号:</label>  
                            <input  type="text" name="cpxh" style="width:100;" readonly="readonly">					        
		                    <label>产品规格:</label>  
                            <input type="text" name="cpgg" style="width:100;" readonly="readonly">		
							<label>电    压:</label>  
					        <input class="text"  name="cpdy"   style="width:100;"  readonly="readonly">
					    </div>
	                </div>
	          
                    <div data-options="region:'center'">  
                         <table id="dg-clcpcljz" >
                            <thead>
                            <tr>
   	                        <input class="hidden" name="id">
  
	                        <th data-options="field:'clmc',width:150,
						       editor:{
							      type:'combobox',
							      options:{
								      url:'getUniCl.do',
				                      method:'get',
					                  multiple:false,
			                          valueField:'text',
			                          textField:'text'
	                              }
							  }
						     ">材料名称</th>   							
	                        <th field="cljz" width="120" align="right" 
							      data-options="editor:{type:'numberbox',options:{precision:2}}, required:true"
							 >材料净重</th>
  
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
  <script type="text/javascript" src="${msUrl}/js/ux/business/cljzManage.js"></script> 


<script type="text/javascript">

function toCljzList(id){


	 $.post("dataListForCpId.do?id="+id,function(rsp) {  
	
                    if(rsp.success){  
       
					    $('#data-cljzlist').datagrid('loadData',rsp.cljzmx);
                        //$.messager.alert("提示", rsp.msg);   
                    }  
					else
					{
	                     $.messager.alert("提示",rsp.msg);  	
					}

               }, "JSON");

  } 

       function cancelquit(){ 
       $.messager.confirm('提示','你确认要放弃以上修改吗',function(r){  
	       if (r){  
		       //endEdit(); 
               $('#edit-cljz-win').dialog('close');
		       $('#edit-cljz-win').resetForm();

		       $("#dg-clcpcljz").datagrid('reload');
		       $("#dg-clcpcljz").datagrid('loadData', { total: 0, rows: [] }); 

		
           }
	   })
 

    }

 	$(function() {  
        var $dg = $("#data-list");  
        $dg.datagrid({ 
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
        var $dg = $("#dg-clcpcljz");  

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
				    $.messager.confirm('提示','你确认要删除该记录吗',function(r){  
	                   if (r){  
                           var row = $dg.datagrid('getSelected');  
                           if (row) {  
                               var rowIndex = $dg.datagrid('getRowIndex', row);  
                               $dg.datagrid('deleteRow', rowIndex);  		  
                           }
                       }  
                    })  
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

	 			var cpid=$("#edit-cljz-win input:input[name='cpid']").val();  //产品ID
	            effectRow["cpid"] =cpid;
	

                $.post("saveCljz.do", effectRow,function(rsp) {  
                   if(rsp.success){  
  	
                        $.messager.alert("提示", rsp.msg);   
                    }  
					else
					{
	                     $.messager.alert("提示",rsp.msg);  	
					}
               }, "JSON");
			   $dg.datagrid('acceptChanges');  
			   $("#dg-clcpcljz").datagrid('reload');
			   $dg.datagrid('loadData', { total: 0, rows: [] }); 

			   $('#edit-cljz-win').dialog('close');
			   $('#edit-cljz-win').resetForm();

	           var param = $("#searchForm").serializeObject();
			   $('#data-list').datagrid('reload',param);		
   
            }  
        }
	
   });

</script>
</body>
</html>

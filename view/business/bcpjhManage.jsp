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

                <input name="jhbh" class="easyui-box ui-text" >
      
 
               <label>机台:</label>  
		            <input class="easyui-combobox"  name="sbmc"    
				    data-options="
			        url:'../sbManage/getSbList.do',
					multiple:false,
			        valueField:'sbmc',
			        textField:'sbmc'">
  	 	       
                <label>型号:</label>  
		            <input class="easyui-combobox"  name="gxxh"  
				    data-options="
	                url:'getUniXh.do',
				    method:'get',
					
			        multiple:false,
			        valueField:'text',
			        textField:'text'">

                <label>规格:</label>  
		            <input  class="easyui-combobox"  name="gxgg"  
				    data-options="
	                url:'getUniGg.do',
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
         <table id="data-list" > 
		 </table>
 	 </form>	
	  <!-- Edit Win&From -->

	 <div id="edit-win-dlgbuttons" class="dialog-button" >
       
	     <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" 
		   onclick=" cancelquit();">放弃修改</a>
     </div>
     <div id="edit-win" class="easyui-dialog"  buttons="#edit-win-dlgbuttons" 
	     title="半成品计划编辑" 
	     data-options="closed:true,iconCls:'icon-edit',modal:true"  
		 style="width:1100px;height:560px;">  
     	 <form id="editForm" class="ui-form" method="post"> 
     	      <!-- 隐藏文本框 -->
     	      <input class="hidden" name="id">
			  <input class="hidden" name="parentId" id='edit_parentId'>
    	      <div class="easyui-panel" border='false' style="height:450;">  
	              <div class="easyui-layout" data-options="fit:true">  
	                  <div data-options="region:'north',split:true" style="height:100px;padding:2px">  
	                     <div class="ftitle">信息</div>    
		    		     <div class="fitem">  
					        <label>下达时间:</label>  
					        <input class="easyui-datebox"  name="xdrq"   style="width:100%;">
		                    <label>半成品计划编号:</label>  
                            <input class="easyui-validatebox" type="text" name="jhbh" readonly="readonly" style="width:100px;" >
					        <label>完工货期:</label>  
				            <input class="easyui-datebox" name="jhrq"  style="width:100%;" >
												   
		                </div> 
	                </div>
	          
                    <div data-options="region:'center'">  
                      <table id="dg-bcpjhappend" >
                            <thead>
                            <tr>
   	                        <input class="hidden" name="id" >
                            <th data-options="field:'xdrq', width:70,disabled:true,
                                  formatter:function(value,row,index) {
                                      if (value != null) {
                                          var date = new Date(value);
                                          return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                                          + date.getDate();
                                       }
								   }
								  
							 ">下达日期</th>
  	                        <th field="jhbh" width="100" >计划编号</th>
	                        <th field="row" width="30" align="right">序号</th>
  						    <th data-options="field:'sbmc',width:90,
						         editor:{
							      type:'combobox',
							      options:{
								     valueField:'sbmc',
								     textField:'sbmc',
								     url:'../sbManage/getSbList.do',
									 method:'get'
	                              }
							    }
						    ">工序设备</th>  
 						    <th data-options="field:'iszl',width:40,
						       editor:{
							      type:'combobox',
							      options:{
								     valueField:'id',
								     textField:'text',
								     url:'${msUrl}/selectdata/iszl.json'
							       }
								}
						    ">主零</th>      	                        
							<th field="gxxh" width="120" editor="validatebox">型号</th>
	                        <th field="gxgg" width="80" editor="validatebox">规格</th>					
  						    <th data-options="field:'gxdy',width:80,
						       editor:{
							      type:'combobox',
							      options:{
								     valueField:'id',
								     textField:'text',
								     url:'${msUrl}/selectdata/dy.json'
							       }
								}
						    ">电压</th>   
  						    <th data-options="field:'gxgy',width:50,
						       editor:{
							      type:'combobox',
							      options:{
								     valueField:'id',
								     textField:'text',
								     url:'${msUrl}/selectdata/gy.json'
	                              }
								 }
						     ">工艺</th>   
							 <th data-options="field:'gxdw',width:50,
						        editor:{
							      type:'combobox',
							      options:{
								     valueField:'id',
								     textField:'text',
								     url:'${msUrl}/selectdata/dw.json',
								     required:true
							       }
								 }
						     ">单位</th>
  	                         <th field="gxlb" width="40" editor="text" >类别</th>
	                         <th field="jhsl" width="80" align="right" 
							      data-options="editor:{type:'numberbox',options:{precision:2}}"
							 >计划数量</th>
	                         <th data-options="field:'jhrq', width:100,editor:'datebox'">完工日期</th>
	                         <th data-options="field:'gxjsyq', width:180,editor:'text',nowrap: false">技术要求</th>
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
  <script type="text/javascript" src="${msUrl}/js/ux/business/bcpjhManage.js"></script> 
 
<script type="text/javascript"> 
      function cancelquit(){ 
       $.messager.confirm('提示','你确认要放弃以上修改吗',function(r){  
	       if (r){  
		       //endEdit(); 
               $('#edit-win').dialog('close');
		       $('#edit-win').resetForm();

		       $("#dg-bcpjhappend").datagrid('reload');
		       $("#dg-bcpjhappend").datagrid('loadData', { total: 0, rows: [] }); 

			   //$('#data-list').datagrid('load');
		       var param = $("#searchForm").serializeObject();
		       $('#data-list').datagrid('reload',param);
           }
	   })
 

    }

 	$(function() {  
        var $dg = $("#data-list");  
        $dg.datagrid({ 
            //height : 510,  
			//pageSize:15,
			//pageList: [10, 15, 30, 40, 50],
			//url:'dataListLink.do?gd='+'高压工段'
		    onHeaderContextMenu: function(e, field){
		        e.preventDefault();
			    if (!cmenu){
			  	    createColumnMenu();
			    }
			    cmenu.menu('show', {
			        left:e.pageX,
			        top:e.pageY-100
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
        var $dg = $("#dg-bcpjhappend");  

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
		 	    var xdrq=$("#editForm input:input[name='xdrq']").val(); 
	            effectRow["xdrq"] =xdrq;
				var jhbh=$("#editForm input:input[name='jhbh']").val();  
	            effectRow["jhbh"] =jhbh;
			
				var jhrq=$("#editForm input:input[name='jhrq']").val();  
	            effectRow["jhrq"] = jhrq;
	

                $.post("save.do", effectRow,function(rsp) {  
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
		
			   $("#dg-bcpjhappend").datagrid('reload');
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
   //处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外  
    function banBackSpace(e)
	{   var ev = e || window.event;
       //获取event对象   
       var obj = ev.target || ev.srcElement;
       //获取事件源   
       var t = obj.type || obj.getAttribute('type');//获取事件源类型   
       //获取作为判断条件的事件类型   
       var vReadOnly = obj.getAttribute('readonly');   
       var vEnabled = obj.getAttribute('enabled');   
       //处理null值情况   
       vReadOnly = (vReadOnly == null) ? false : vReadOnly; 
       vEnabled = (vEnabled == null) ? true : vEnabled;  
       //当敲Backspace键时，事件源类型为密码或单行、多行文本的，  
       //并且readonly属性为true或enabled属性为false的，则退格键失效   
       var flag1=(ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea") && (vReadOnly==true || vEnabled!=true))?true:false;   
       //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效   
       var flag2=(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea")   ?true:false;  
      //判断   
      if(flag2)
	  {   return false;   }  
      if(flag1)
      {   return false;   }   
    }   
    //禁止后退键 作用于Firefox、Opera   
    document.onkeypress=banBackSpace;   
    //禁止后退键 作用于IE、Chrome   
    document.onkeydown=banBackSpace;   
</script>  



</body>
</html>

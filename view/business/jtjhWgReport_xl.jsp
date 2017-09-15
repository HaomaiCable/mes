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

                <input name="jhbh" class="easyui-box ui-text" style="width:100px;">
      
 
               <label>机台:</label>  
		            <input class="easyui-combobox"  name="sbmc" style="width:100px;"   
				    data-options="
			        url:'../sbManage/getSbListForGd.do?gdid=2',
					multiple:false,
			        valueField:'sbmc',
			        textField:'sbmc'">
              <label>完工状态:</label>  
		            <input class="easyui-combobox"  name="wgflag" style="width:60px;"    
                    data-options="
			        method:'get',
	                valueField:'text',
		            textField:'text',
				    url:'${msUrl}/selectdata/wgflag.json' ">
  	 	       
                <label>型号:</label>  
		            <input class="easyui-combobox"  name="gxxh" style="width:100px;" 
				    data-options="
	                url:'getUniXh.do',
				    method:'get',
					
			        multiple:false,
			        valueField:'text',
			        textField:'text'">

                <label>规格:</label>  
		            <input  class="easyui-combobox"  name="gxgg"  style="width:100px;"
				    data-options="
	                url:'getUniGg.do',
			        method:'get',
				    
			        multiple:false,
			        valueField:'text',
			        textField:'text'">
				<label class="ui-label">完工日期: </label><input name="fromjhrq" class="easyui-datebox" style="width:100px;">
				<label class="ui-label">至: </label><input name="tojhrq" class="easyui-datebox" style="width:100px;">	 
    
            </p>
            <a id="btn-search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
        </form>  
     </div> 
     <!--  Search panel end -->
     <!-- DataList  -->
     <form id="listForm" method="post" >
         <table id="data-list" 	 
	     data-options="url:'dataListLinkForGd.do?gdid=2'" >	
		 </table>
 	 </form>	
	  <!-- Edit Win&From -->

	 <div id="edit-win-dlgbuttons" class="dialog-button" >
       
	     <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" 
		   onclick=" cancelquit();">放弃修改</a>
     </div>
     <div id="edit-win" class="easyui-dialog"  buttons="#edit-win-dlgbuttons" 
	     title="机台完工数量汇报" 
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
						    <input class="hidden" name="jhid" >
		                    <label>计划单号:</label>  
                            <input  type="text" name="jhbh" style="width:100;" readonly="readonly">					        
		                    <label>行号:</label>  
                            <input type="text" name="row" style="width:20;" readonly="readonly">		
							<label>交货期:</label>  
					        <input class="text"  name="jhrq"   style="width:80;"  readonly="readonly">
						    <label>技术要求:</label>  
					        <input type="datebox"  name="gxjsyq"   style="width:150;"  readonly="readonly">
                            <label>状态:</label>   
                            <input  class="easyui-combobox" id="state" name="state"   style="width:60px;"    
                                  data-options="
			                      method:'get',
	                              valueField:'id',
						          textField:'text',
								  disabled:true,
								  url:'${msUrl}/selectdata/state.json' ">

					    </div>
						<div class="fitem">  
			                <label>型号:</label>  
                            <input  type="text" name="gxxh" style="width:120px;" readonly="readonly">	
			                <label>规格:</label>  
                            <input  type="text" name="gxgg" style="width:120px;" readonly="readonly">	
			                <label>电压:</label>  
                            <input  type="text" name="gxdy" style="width:60px;" readonly="readonly">	
			                <label>工艺:</label>  
                            <input  type="text" name="gxgy" style="width:30px;" readonly="readonly">	
			                <label>计划数量:</label>  
                            <input  type="numberbox" name="jhsl" style="width:60px;" readonly="readonly">	
						
		                </div> 
	                </div>
	          
                    <div data-options="region:'center'">  
                         <table id="dg-jtjhwghb" >
                            <thead>
                            <tr>
   	                        <input class="hidden" name="id">
	                        <th data-options="field:'wgrq', width:120,editor:'datebox', required:true
								  
							">完工日期</th>
		                    <th data-options="field:'bc',width:50,
						       editor:{
							      type:'combobox',
							      options:{
								     valueField:'id',
								     textField:'text',
								     url:'${msUrl}/selectdata/bc.json'
	                              }
								 }
						     ">班次</th> 
	                         <th field="wgsl" width="100" align="right" 
							      data-options="editor:{type:'numberbox',options:{precision:2}}, required:true"
							 >完工数量</th>
  
   	                        <th field="wgsm" width="180" editor="text">文字备注信息</th>
		                    <th data-options="field:'wg',width:80,
						       editor:{
							      type:'combobox',
							      options:{
								     valueField:'id',
								     textField:'text',
								     url:'${msUrl}/selectdata/rk.json'
	                              }
								 }
						     ">全部完工</th>   			
  		                    <th field="lrBy" width="80" >录入</th>
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
  <script type="text/javascript" src="${msUrl}/js/ux/business/jtjhWgReport.js"></script> 
 
<script type="text/javascript"> 
      function cancelquit(){ 
       $.messager.confirm('提示','你确认要放弃以上修改吗',function(r){  
	       if (r){  
		       //endEdit(); 
               $('#edit-win').dialog('close');
		       $('#edit-win').resetForm();

		       $("#dg-jtjhwghb").datagrid('reload');
		       $("#dg-jtjhwghb").datagrid('loadData', { total: 0, rows: [] }); 

			   //$('#data-list').datagrid('load');
		       var param = $("#searchForm").serializeObject();
		       $('#data-list').datagrid('reload',param);
           }
	   })
 

    }

 	$(function() {  
        var $dg = $("#data-list");  
        $dg.datagrid({ 
            //height : 500,  
			//pageSize:16,
			//pageList: [10, 16, 30, 40, 50],
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
        var $dg = $("#dg-jtjhwghb");  

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

                    var today = new Date();
                    var date= new Date(today);
                    date.setDate(today.getDate()-1);
                    var year = date.getFullYear();
                    var month = date.getMonth() + 1;
				    if (month<10)
				    {
					    month='0'+month;
				    }
                    var strDate = date.getDate();
				    if (strDate<10)
				    {
					    strDate='0'+strDate;
				    }
                    var myDate=year+"-"+month+"-"+strDate;
                    $dg.datagrid('appendRow', {wgrq:myDate});  
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
				//var ywy = $("input[name='ywy' type='text']").val(); 

	 			var jhid=$("#editForm input:input[name='jhid']").val();  //机台计划ID
	            effectRow["jhid"] =jhid;
	

                $.post("saveWghb.do", effectRow,function(rsp) {  
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
		
			   $("#dg-jtjhwghb").datagrid('reload');
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

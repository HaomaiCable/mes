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

				<label class="ui-label">发布日期: </label><input name="fromfbrq" class="easyui-datebox" style="width:100px;">
				<label class="ui-label">至: </label><input name="tofbrq" class="easyui-datebox" style="width:100px;">	 	
                <label class="ui-label">公告编号:</label> 
                <input name="ggbh" class="easyui-box ui-text" style="width:100px;">
      
                <label>采购分类:</label>  
		            <input class="easyui-combobox"  name="wlfl"  style="width:100px;"
				    data-options="
			        url:'../cgflManage/cgflListJson.do',
			        method:'get',
					multiple:false,
			        valueField:'name',
			        textField:'name'">

  	 	       
                <!--<label>物料名称:</label>  
		            <input class="easyui-combobox"  name="wlmc"   style="width:100px;"
				    data-options="
	                url:'../zbwzManage/zbwzListJson.do',
				    method:'get',
			        multiple:false,
			        valueField:'name',
			        textField:'name'">-->
                <label>物料名称:</label>  
		         <input   name="wlmc"  class="easyui-box ui-text" style="width:100px;" >
                <label>产品规格:</label>  
		         <input   name="wlgg"  class="easyui-box ui-text" style="width:100px;" >
				<label class="ui-label">有效期: </label><input name="fromyxrq" class="easyui-datetimebox" style="width:140px;">
				<label class="ui-label">至: </label><input name="toyxrq" class="easyui-datetimebox" style="width:140px;">	 	
	
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
	     title="采购公告发布" 
	     data-options="closed:true,iconCls:'icon-edit',modal:true"  
		 style="width:1100px;height:560px;">  
     	 <form id="editForm" class="ui-form" method="post"> 
     	      <!-- 隐藏文本框 -->
     	      <input class="hidden" name="id">
			  <input class="hidden" name="ggbh">
 			  <input class="hidden" name="parentId" id='edit_parentId'>
    	      <div class="easyui-panel" border='false' style="height:450;">  
	              <div class="easyui-layout" data-options="fit:true">  
	                  <div data-options="region:'north',split:true" style="height:90px;padding:10px">  
	                     <div class="ftitle">信息</div>    
		                 <div class="fitem">  
					        <label>发布时间:</label>  
					        <input class="easyui-datebox"  name="fbrq"   style="width:100%;">
		                    <!--<label>公告编号:</label>  
                            <input class="easyui-validatebox" type="text" name="ggbh" readonly="readonly" style="width:100px;" >-->
                            <label>采购分类:</label>  
		                        <input class="easyui-combobox"  name="wlfl"  style="width:100px;"
				                   data-options="
			                           url:'../cgflManage/cgflListJson.do',
			                           method:'get',
					                   multiple:false,
									  
			                           valueField:'name',
			                           textField:'name'">
					        <label>公告有效期:</label>  
				            <input class="easyui-datetimebox" name="yxrq"  style="width:140%;" >
					        <label>要求交货期:</label>  
				            <input class="easyui-datebox" name="jhrq_xq"  style="width:100%;" >
												   
		                </div> 
	                </div>
	          
                    <div data-options="region:'center'">  
                         <table id="dg-zbggappend" >
                            <thead>
                            <tr>
   	                        <input class="hidden" name="id">
  	                        <th field="ggbh" width="100" >公告编号</th>
	                        <th field="row" width="30" >行号</th>
							<th data-options="field:'wlfl',width:100,
						       editor:{
							      type:'combobox',
							      options:{
								     valueField:'name',
								     textField:'name',
							         url:'../cgflManage/cgflListJson.do',
									  disabled:true
	
							       }
								}
						    ">采购分类</th> 
						    <!--<th data-options="field:'wlmc',width:100,
						       editor:{
							      type:'combobox',
							      options:{
								     valueField:'name',
								     textField:'name',
								     url:'../zbwzManage/zbwzListJson.do'
							       }
								}
						    ">物料名称</th>   -->
                             <th field="wlmc" width="120" editor="validatebox" >物料名称</th>								
	                         <th field="wlgg" width="120" editor="validatebox">物料规格</th>					
 	                         <th field="wldw" width="50" editor="validatebox">单位</th>	 	
	                         <th field="wlsl" width="80" align="right" 
							      data-options="editor:{type:'numberbox',options:{precision:2}}"
							 >采购数量</th>
	                         <th data-options="field:'jhrq_xq', width:100,editor:'datebox'
								  
							 ">要求交货期</th>
	                         <th data-options="field:'jsyq', width:500,editor:'text',nowrap: false">技术要求</th>
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
  <script type="text/javascript" src="${msUrl}/js/ux/cgpt/zbggManage.js"></script> 
  
 <script type="text/javascript">   

   function initdate(){
     $("#searchForm input:input[name='fromfbrq']").val("${requestScope.fromdate}");
     $("#searchForm input:input[name='tofbrq']").val("${requestScope.todate}");

  }

 
   function cancelquit(){ 
       $.messager.confirm('提示','你确认要放弃以上修改吗',function(r){  
	       if (r){  
		       //endEdit(); 
               $('#edit-win').dialog('close');
		       $('#edit-win').resetForm();

		       $("#editForm input:input[name='fbrq']").val("");
		       $("#editForm input:input[name='ggbh']").val("");
   		       $("#editForm input:input[name='yxrq']").val("");
   		       $("#editForm input:input[name='wlfl']").val("");
		       $("#editForm input:input[name='jhrq_xq']").val("");
		       $("#dg-zbggappend").datagrid('reload');
		       $("#dg-zbggappend").datagrid('loadData', { total: 0, rows: [] }); 

			   //$('#data-list').datagrid('load');
		       var param = $("#searchForm").serializeObject();
		       $('#data-list').datagrid('reload',param);
           }
	   })
    }
 

  
   $(function() {  

        var $dg = $("#dg-zbggappend");  
        $dg.datagrid({ 
			nowrap: false,
			singleSelect:true,
		    rownumbers:true,
			onClickRow: onClickRow,
			ft:'#toolbar',
  
  
            toolbar : [ {  
                text : "添加行",  
                iconCls : "icon-add",  
                handler : function() {  
                    $dg.datagrid('appendRow', {});  
                    var rows = $dg.datagrid('getRows');  
                    $dg.datagrid('beginEdit', rows.length - 1);  
                }  
            },  {  
                text : "插入行",  
                iconCls : "icon-add",  
                handler : function(){  
                    var target = $dg.datagrid('getSelected');  
                    var parentIndex =  $dg.datagrid('getRowIndex', target);   
                    //保存父行数据，用于新增数据。  
                    $dg.datagrid('endEdit', parentIndex);  
                    $dg.datagrid('updateRow',{index: parentIndex,row:{}});  
                   //获取父行数据，进行新增操作。  
                    var newIndex = parentIndex+1;  
                    $dg.datagrid('selectRow',parentIndex);  
                    var rowParent =$dg.datagrid('getSelected');  
                    var newRow = jQuery.extend(true, {}, rowParent);  
                    $dg.datagrid('insertRow',{  
                        index:newIndex
							,row:newRow  
                    });  
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

	 			var fbrq=$("#editForm input:input[name='fbrq']").val();  
	            effectRow["fbrq"] =fbrq;
				var ggbh=$("#editForm input:input[name='ggbh']").val();  
	            effectRow["ggbh"] =ggbh;
				var wlfl=$("#editForm input:input[name='wlfl']").val();  
	            effectRow["wlfl"] =wlfl;
				var yxrq=$("#editForm input:input[name='yxrq']").val(); 
	            effectRow["yxrq"] =yxrq;
				var jhrq_xq=$("#editForm input:input[name='jhrq_xq']").val();  
	            effectRow["jhrq_xq"] = jhrq_xq;

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
			 

			   $("#editForm input:input[name='fbrq']").val("");
			   $("#editForm input:input[name='ggbh']").val("");
			   $("#editForm input:input[name='wlfl']").val("");
   			   $("#editForm input:input[name='yxrq']").val("");
			   $("#editForm input:input[name='jhrq_xq']").val("");
  
			   //$("#dg-zbggappend").datagrid('reload');
			   $dg.datagrid('loadData', { total: 0, rows: [] }); 

               var param = $("#searchForm").serializeObject();
			   $('#data-list').datagrid('reload',param);
		
            }  
        }
	
   });

  document.body.onload=initdate();
  var param =$('#searchForm').serializeObject();
  $('#data-list').datagrid('reload',param);

</script>  	
</body>
</html>

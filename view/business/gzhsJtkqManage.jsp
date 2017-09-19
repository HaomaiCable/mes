<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
       <%@include file="/view/resource.jsp" %>
	  <!-- <script type="text/javascript" src="${msUrl}/js/commons/ajaxfileupload.js"></script> -->
  </head>
<body>
<div class="warp easyui-panel"  data-options="border:false">
  	 <!-- Search panel start -->
 	 <div class="easyui-panel ui-search-panel" title="查询窗口" data-options="striped: true,collapsible:true,iconCls:'icon-search'">   
 	     <form id="searchForm">
 	 	      <input class="hidden" id='search_parentId' name="parentId">
 	 	      <p class="ui-fields">
			      <label class="ui-label">考勤日期: </label><input name="fromkqrq" class="easyui-datebox" style="width:100px;">
			      <label class="ui-label">至: </label><input name="tokqrq" class="easyui-datebox" style="width:100px;">	 	

                   <!-- <label>工段:</label>  
		            <input class="easyui-combobox"  name="gd" style="width:100px;"   
				    data-options="
			        url:'${msUrl}/selectdata/gd.json',
					method:'get',
					multiple:false,
			        valueField:'id',
			        textField:'text'">   -->   
                 <label>班次:</label>  
		            <input class="easyui-combobox"  name="bc" style="width:100px;"   
				    data-options="
			        url:'${msUrl}/selectdata/bc.json',
					method:'get',
					multiple:false,
			        valueField:'id',
			        textField:'text'">       
                  <label>机台:</label>  
		            <input class="easyui-combobox"  name="sbmc" style="width:100px;"   
				    data-options="
			        url:'../sbManage/getSbListForGd.do?gdid=1',
					multiple:false,
			        valueField:'sbmc',
			        textField:'sbmc'">
                 <label>员工:</label>  
		            <input  class="easyui-combobox"  name="wgzs"  style="width:100px;" 
				    data-options="
	                url:'../ygManage/getYgListForGd.do?gdid=1',
			        method:'get',
			        multiple:false,
			        valueField:'xm',
			        textField:'xm'">
              </p>
              <a id="btn-search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
         </form>  
     </div> 
     <!--  Search panel end -->
     <!-- DataList  -->
     <form id="listForm" method="post" >
         <table id="data-list" 
		     data-options="pageSize:16,
 			               height:500" >
		
		 </table>
 	 </form>	
     <!-- Edit Form 单个-->
     <div id="edit-win" class="easyui-dialog" title="编辑" data-options="closed:true,iconCls:'icon-save',modal:true" style="width:350px;height:360px;">  
     	 <form id="editForm" class="ui-form" method="post">  
     		 <input class="hidden" type="text" name="id">
     	     <div class="ui-edit">
	             <div class="ftitle">机台考勤</div>    
	             <div class="fitem">  
                     <label>工段:</label>  
		                <input class="easyui-combobox"  name="gd" style="width:100px;"   
				           data-options="
			               url:'${msUrl}/selectdata/gd.json',
					       method:'get',
					       multiple:false,
			               valueField:'id',
			               textField:'text'">      
	             </div>
				 <div class="fitem">  
				     <label>机台:</label>  
                     <input class="easyui-combobox"  name="sbmc"  
					      data-options="
				            url:'../sbManage/getSbListForGd.do?gdid=1',
				            method:'get',
				            valueField:'sbmc',
				            textField:'sbmc'">
	             </div>  
				 <div class="fitem">  
				     <label>员工:</label>  
                     <input class="easyui-combobox"  name="ygxm"  
					      data-options="
				            url:'../ygManage/getYgListForGd.do?gdid=1',
				            method:'get',
				            valueField:'xm',
				            textField:'xm'">
	             </div>  
				 <div class="fitem">  	
                     <label>定额系数:</label>  
	                     <input class="easyui-validatebox" type="numberbox" name="dexs" data-options="required:true,options:{precision:1}">	
	             </div>  
				 <div class="fitem">  	
                     <label>考勤日期:</label>  
	                     <input class="easyui-datebox"  name="kqrq" data-options="required:true">	
	             </div>  
				 <div class="fitem">  
                     <label>班次:</label>  
		                <input class="easyui-combobox"  name="bc" style="width:100px;"   
				           data-options="
			               url:'${msUrl}/selectdata/bc.json',
					       method:'get',
					       multiple:false,
			               valueField:'id',
			               textField:'text'">   
	             </div>  						   
				 <div class="fitem">  	
                     <label>出勤工时:</label>  
	                     <input class="easyui-validatebox" type="numberbox" name="cqgs" data-options="required:true,options:{precision:1}">	
	             </div>  
	         </div>	               
         </form>
     </div> 

	 <!-- Edit Win&From 批量考勤-->
	 <div id="edit-plkq-win-dlgbuttons" class="dialog-button" >
	     <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" 
		   onclick="cancelquit();">放弃修改</a>
     </div>
     <div id="edit-plkq-win" class="easyui-dialog"  buttons="#edit-plkq-win-dlgbuttons" 
	     title="机台批量考勤" 
	     data-options="closed:true,iconCls:'icon-edit',modal:true"  
		 style="width:1000px;height:560px;">  
     	 <form id="plkqForm" class="ui-form" method="post"> 
     	      <!-- 隐藏文本框 -->
     	      <input class="hidden" name="id">
 			  <input class="hidden" name="parentId" id='edit_parentId'>
    	      <div class="easyui-panel" border='false' style="height:450;">  
	              <div class="easyui-layout" data-options="fit:true">  
	                  <div data-options="region:'north',split:true" style="height:50px;padding:10px">  
	                       <div class="ftitle">信息</div>    
		                   <div class="fitem">  
					          <label>考勤日期:</label>  
					          <input class="easyui-datebox"  name="kqrq"   style="width:100%;">
		                      <label>班次:</label>  
		                      <input class="easyui-combobox"  name="bc"  
							     data-options="
				    	            url:'${msUrl}/selectdata/bc.json',
					                method:'get',
					                multiple:false,
			                        valueField:'id',
			                        textField:'text'">
		                   </div> 
	                  </div>
                      <div data-options="region:'center'">  
                         <table id="dg-jtkqappend" >
                             <thead>
                             <tr>
   	                         <input class="hidden" name="id">
  	                         <th field="gd" width="80" >工段</th>
  						     <th data-options="field:'sbmc',width:100,
						       editor:{
							      type:'combobox',
							      options:{
								     valueField:'sbmc',
					                 textField:'sbmc',
									 method:'get',
								     url:'../sbManage/getSbListForGd.do?gdid=1'
							       }
								}
						     ">机台</th>   
  						     <th data-options="field:'ygxm',width:100,
						       editor:{
							      type:'combobox',
							      options:{
								     valueField:'xm',
					                 textField:'xm',
									 method:'get',
								     url:'../ygManage/getYgListForGd.do?gdid=1'
							       }
								}
						     ">员工</th>   
	                         <th field="dexs" width="80" align="right" 
							      data-options="editor:{type:'numberbox',options:{precision:1}}"
							 >定额系数</th>
	                         <th data-options="field:'kqrq', width:100,editor:'datebox'
								  
							 ">考勤日期</th>
	                         <th field="cqgs" width="100" align="right" 
							      data-options="editor:{type:'numberbox',options:{precision:1}}"
							 >出勤工时</th>
   	                         <!--<th field="bz" width="150" editor="text">备注信息</th>-->
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
<script type="text/javascript" src="${msUrl}/js/ux/business/gzhsJtkqManage.js"></script>
<!-- <script type="text/javascript" src="${msUrl}/js/commons/json.js"></script> -->
<!--<script type="text/javascript" src="${msUrl}/js/ux/business/xsddMxManage.js"></script>  -->
<script type="text/javascript">   
   function initdate(){
     $("#searchForm input:input[name='fromkqrq']").val("${requestScope.fromdate}");
     $("#searchForm input:input[name='tokqrq']").val("${requestScope.todate}");
     // $("#searchForm").find("#btn-search").click();
     //var param =$('#searchForm').serializeObject();
     // $('#data-list').datagrid('reload',param);
	 //$('#data-list').datagrid('load')
  }

   function downExcel(fileName){
     window.location.href ="../downLoadManage/downLoad.do?fileName="+fileName; 
   }


	function toExcel(){
         var rows = $('#data-list').datagrid('getRows');
         var effectRow = new Object();  
         effectRow["selected"] =JSON.stringify(rows);  
  
         $.post("exportExcel.do",effectRow ,function(rsp) {  			
           if(rsp.success){  
			   downExcel(rsp.fileName);
            }  
		    else{
	           $.messager.alert("提示",rsp.msg);  	
		    }
		 });
	}
   function cancelquit(){ 
       $.messager.confirm('提示','你确认要放弃以上修改吗',function(r){  
	       if (r){  
		       //endEdit(); 
               $('#edit-plkq-win').dialog('close');
		       $('#edit-plkq-win').resetForm();

		       $("#plkqForm input:input[name='kqrq']").val("");
		       $("#plkqForm input:input[name='bc']").val("");

		       $("#dg-jtkqappend").datagrid('reload');
		       $("#dg-jtkqappend").datagrid('loadData', { total: 0, rows: [] }); 

		       var param = $("#searchForm").serializeObject();
		       $('#data-list').datagrid('reload',param);
           }
	   })
    }
  
   $(function() {  
        var $dg = $("#dg-jtkqappend");  
        $dg.datagrid({ 
            url : "getPlkq.do?gdid=1",  
            // width : 700,  
            //height :365,  
			nowrap: false,
			singleSelect:true,
			//pagination:true,
		    rownumbers:true,
			//pageSize:16,
			//pageList: [10, 16, 30, 40, 50],
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
             }, {  
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
             },{  
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

	 			var kqrq=$("#plkqForm input:input[name='kqrq']").val();  //考勤日期
	            effectRow["kqrq"] =kqrq;
				var bc=$("#plkqForm input:input[name='bc']").val();  //计划编号,第5个
	            effectRow["bc"] =bc;

                $.post("savePlkq.do", effectRow,function(rsp) {  
	
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
			   $('#edit-plkq-win').dialog('close');
			   $("#plkqForm input:input[name='kqrq']").val("");
			   $("#plkqForm input:input[name='bc']").val("");
			   $dg.datagrid('loadData', { total: 0, rows: [] }); 
               var param = $("#searchForm").serializeObject();
			   $('#data-list').datagrid('reload',param);
		
            }  
        }
	
   });

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
    var param =$('#searchForm').serializeObject();
    $('#data-list').datagrid('reload',param);
</script>  	
</body>
</html>

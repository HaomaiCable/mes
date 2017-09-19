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
				<tr>
				<label class="ui-label">下达日期: </label><input name="fromxdrq" class="easyui-datebox" style="width:100px;">
				<label class="ui-label">至: </label><input name="toxdrq" class="easyui-datebox" style="width:100px;">	 	
				</tr> 
                <label class="ui-label">下达机台:</label> 
                <input name="xdjt" class="easyui-box ui-text" style="width:40px;">
                <label class="ui-label">订单状态:</label> 
                <input name="state" class="easyui-box ui-text" style="width:40px;">   	
				<label class="ui-label">计划号:</label> 
                <input name="jhbh" class="easyui-box ui-text" style="width:80px;">
  
 	 	       
                <label>产品型号:</label>  
		            <input class="easyui-combobox"  name="xh"  
				    data-options="
	                url:'../xsddManage/getUniXh.do',
				    method:'get',
					
			        multiple:false,
			        valueField:'text',
			        textField:'text'">

                <label>产品规格:</label>  
		            <input  class="easyui-combobox"  name="gg"  
				    data-options="
	                url:'../xsddManage/getUniGg.do',
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
         <table id="data-list">
		 </table>
 	 </form>	
	 <form id="listjtjhForm" method="post" >
     <table id="data-jtjhlist" class="easyui-datagrid" 
         data-options="
		     url:'dataListForDdId.do?id='+0,
			 height:185,
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
        <th data-options="field:'xdrq',width:70,sortable:true">下达日期</th>
        <th data-options="field:'jhbh',width:100,sortable:true">计划编号</th>
		<th data-options="field:'row',width:30,sortable:true">序号</th>
        <th data-options="field:'sbmc',width:80,sortable:true">工序设备</th>
        <th data-options="field:'iszl',width:40">主零</th>
        <th data-options="field:'gxxh',width:120">型号</th>
        <th data-options="field:'gxgg',width:120">规格</th>
        <th data-options="field:'gxdy',width:60">电压</th>
		<th data-options="field:'gxgy',width:30">工艺</th>
		<th data-options="field:'gxdw',width:30">单位</th>
        <th data-options="field:'gxlb',width:40">类别</th>
        <th data-options="field:'jhsl_xs',width:65">计划数量</th>
        <th data-options="field:'jhrq',width:80">完工日期</th>
        <th data-options="field:'gxjsyq',width:120">技术要求</th>
        <th data-options="field:'gxph',width:140">批号</th>
		<th data-options="field:'state', width:40,align:'center',
            styler:function(value,row,index){
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
			}"
		>状态</th>
        <th field="createBy" width="70" >制单人</th>
	    <th field="createTime" width="150" >时间</th>

	    </tr>
		</thead>
	</table>  
    </form>
	 <!-- Edit Win&From -->

	 <div id="edit-win-dlgbuttons" class="dialog-button" >
       
	     <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" 
		   onclick=" cancelquit();">返回</a>
     </div>
     <div id="edit-win" class="easyui-dialog"  buttons="#edit-win-dlgbuttons" 
	     title="机台计划下达" 
	     data-options="closed:true,iconCls:'icon-edit',modal:true"  
		 style="width:1200px;height:560px;">  
     	 <form id="editForm" class="ui-form" method="post"> 
     	      <!-- 隐藏文本框 -->
     	      <input class="hidden" name="id">
 			  <input class="hidden" name="parentId" id='edit_parentId'>
    	      <div class="easyui-panel" border='false' style="height:450;">  
	              <div class="easyui-layout" data-options="fit:true">  
	                  <div data-options="region:'north',split:true" style="height:90px;padding:10px">  
	                     <div class="ftitle">信息</div>    
		                 <div class="fitem">  
					       <!-- <label>销售订单下达时间:</label>  
					        <input class="easyui-datebox"  name="xsddxdrq"   style="width:100px;"  disabled="true" >-->
		                    <label>销售订单号:</label>  
                            <input type="text"  name="xsddjhbh"  tyle="width:80px;" readonly="readonly">
                            <label>行号:</label>  
                            <input type="text" name="xsddrow"  tyle="width:40px;" readonly="readonly">
                            <label>计划数量:</label>  
                            <input type="text"  name="xsddsl"  tyle="width:60px;" readonly="readonly">
		         	        <label>交货期:</label>  
				            <input class="easyui-datebox" name="xsddjhrq"  style="width:100px;" disabled="true"  >
												   
		                </div> 
	                </div>
	          
                    <div data-options="region:'center'">  
                         <table id="dg-jtjhappend" >
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

	                         <th field="row" width="30" align="right" 
							      data-options="editor:{type:'numberbox',options:{precision:0}}"
							 >序号</th>
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
	                        <th field="gxgg" width="120" editor="validatebox">规格</th>		
	                        <!--<th field="gxgg_o" width="100" editor="validatebox">机台规格</th>	-->							
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
								     url:'${msUrl}/selectdata/dwnocheck.json',
								     required:true
							       }
								 }
						     ">单位</th>
  	                         <th field="gxlb" width="40" editor="text" >类别</th>
	                         <th field="jhsl_xs" width="80"  editor="text" >计划数量</th>
	                         <th field="jhsl_o" width="80" align="right" 
							      data-options="editor:{type:'numberbox',options:{precision:2}}"
							 >机台数量</th>
	                         <th data-options="field:'jhrq', width:100,editor:'datebox'">完工日期</th>
	                         <th data-options="field:'gxjsyq', width:180,editor:'text',nowrap: false">技术要求</th>
	                         <th data-options="field:'gxph', width:120,editor:'text',nowrap: false">批号</th>
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
  <script type="text/javascript" src="${msUrl}/js/ux/business/jtjhManage.js"></script> 
  <!-- <script type="text/javascript" src="${msUrl}/js/commons/json.js"></script> -->
  <!--<script type="text/javascript" src="${msUrl}/js/ux/business/xsddMxManage.js"></script>  -->
 <script type="text/javascript">   
    $('#data-list').datagrid({   
       rowStyler:function(index,row){   
           if (row.state==2){   
               return  'background-color:pink;color:blue;font-weight:bold;'; //'color:blue;';
					   
            } 
	        else if (row.state==3)
		    {
			   return 'background-color:yellow;color:red;font-weight:bold;'; //'color:red;'
				   
		    }
        }   
   });
 function toJtjhList(id){

	 $.post("${msUrl}/jtjhManage/dataListForDdId.do?id="+id,function(rsp) {  
	
                    if(rsp.success){  
       
					    $('#data-jtjhlist').datagrid('loadData',rsp.jtjh);
                        //$.messager.alert("提示", rsp.msg);   
                    }  
					else
					{
	                     $.messager.alert("提示",rsp.msg);  	
					}

               }, "JSON");

  } 
  
   function cancelquit(){ 
       //$.messager.confirm('提示','你确认要放弃以上修改吗',function(r){  
	   //    if (r){  
		       //endEdit(); 
               $('#edit-win').dialog('close');
		       $('#edit-win').resetForm();

		       $("#editForm input:input[name='xsddxdrq']").val("");
		       $("#editForm input:input[name='xsddjhbh']").val("");
   		       $("#editForm input:input[name='xsddrow']").val("");
		       $("#editForm input:input[name='xsddjhrq']").val("");
		       $("#dg-jtjhappend").datagrid('reload');
		       $("#dg-jtjhappend").datagrid('loadData', { total: 0, rows: [] }); 

			   //$('#data-list').datagrid('load');
		       var param = $("#searchForm").serializeObject();
		       $('#data-list').datagrid('reload',param);
           //}
	   //})
    }
 

  
   $(function() {  

		//alert json;
        var $dg = $("#dg-jtjhappend");  
		//$dg.datagrid('reload');
        $dg.datagrid({ 
            //url : "xsddListByJhbh.do?jhbh=201706280002",  
            // width : 700,  
            height :355,  
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
                text : "重新测算完工日期",  
                iconCls : "icon-redo",  
                handler :function() {  
 					recount();
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
			

	 			//var xdrq=$("#editForm input:input[name='xdrq']").val();  //计划下达时间,第4个
	            //effectRow["xdrq"] =xdrq;
				
	            //effectRow["jhbh"] =jhbh;
				//var ywy=$("#editForm input:input[name='ywy']").val();  //业务员,第6个
	            //effectRow["ywy"] =ywy;
				var ddid=$("#editForm input:input[name='id']").val(); 
	            effectRow["ddid"] = ddid;
                var xsddjhbh=$("#editForm input:input[name='xsddjhbh']").val(); 
				var xsddrow=$("#editForm input:input[name='xsddrow']").val(); 
				if (xsddrow<10)
				{
					effectRow["xsddjhbh"] = xsddjhbh+"-"+"0"+xsddrow;
				}
				else{
					 effectRow["xsddjhbh"] = xsddjhbh+"-"+xsddrow;
				}
                //effectRow["xsddjhbh"] = xsddjhbh+"-"+xsddrow<10?("0"+xsddrow):xsddrow;				
                $.post("${msUrl}/jtjhManage/save.do", effectRow,function(rsp) {  
	
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
			 
			   //$('#edit-win').resetForm();
			   /** $(':input','#edit-win')  
                  .not(':button, :submit, :reset, :hidden')  
                  .val('')  
                  .removeAttr('checked')  
                  .removeAttr('selected');  
				*/
			   $("#editForm input:input[name='xdrq']").val("");
			   $("#editForm input:input[name='jhbh']").val("");
   			   $("#editForm input:input[name='row']").val("");
			   $("#editForm input:input[name='jhrq']").val("");
  
			   //$("#dg-jtjhappend").datagrid('reload');
			   $dg.datagrid('loadData', { total: 0, rows: [] }); 

               var param = $("#searchForm").serializeObject();
			   $('#data-list').datagrid('reload',param);
		
            }  
        }
        function recount(){    
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
			

	 			//var xdrq=$("#editForm input:input[name='xdrq']").val();  //计划下达时间,第4个
	            //effectRow["xdrq"] =xdrq;
				
	            //effectRow["jhbh"] =jhbh;
				//var ywy=$("#editForm input:input[name='ywy']").val();  //业务员,第6个
	            //effectRow["ywy"] =ywy;
				var ddid=$("#editForm input:input[name='id']").val(); 
	            effectRow["ddid"] = ddid;
                var xsddjhbh=$("#editForm input:input[name='xsddjhbh']").val(); 
				var xsddrow=$("#editForm input:input[name='xsddrow']").val(); 
				if (xsddrow<10)
				{
					effectRow["xsddjhbh"] = xsddjhbh+"-"+"0"+xsddrow;
				}
				else{
					 effectRow["xsddjhbh"] = xsddjhbh+"-"+xsddrow;
				}
                //effectRow["xsddjhbh"] = xsddjhbh+"-"+xsddrow<10?("0"+xsddrow):xsddrow;				
                $.post("${msUrl}/jtjhManage/saveRecount.do", effectRow,function(rsp) {  
	
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
			   $("#editForm input:input[name='xdrq']").val("");
			   $("#editForm input:input[name='jhbh']").val("");
   			   $("#editForm input:input[name='row']").val("");
			   $("#editForm input:input[name='jhrq']").val("");
  
			   //$("#dg-jtjhappend").datagrid('reload');
			   $dg.datagrid('loadData', { total: 0, rows: [] }); 

               var param = $("#searchForm").serializeObject();
			   $('#data-list').datagrid('reload',param);
		
            }  
        }	
   });

  function initdate(){
     $("#searchForm input:input[name='fromxdrq']").val("${requestScope.fromdate}");
     $("#searchForm input:input[name='toxdrq']").val("${requestScope.todate}");
    // $("#searchForm").find("#btn-search").click();
     //var param =$('#searchForm').serializeObject();
    // $('#data-list').datagrid('reload',param);
	 //$('#data-list').datagrid('load')
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
  var param =$('#searchForm').serializeObject();
  $('#data-list').datagrid('reload',param);
</script>  	
</body>
</html>

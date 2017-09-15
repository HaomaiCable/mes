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
            <input class="hidden" id='id' name="id">
            <p class="ui-fields">
               <label>任务承担人:</label>  
		            <input class="easyui-combobox"  name="createBy"  
				    data-options="
	                url:'getUniCdr.do',
				    method:'get',
			        multiple:false,
			        valueField:'text',
			        textField:'text'">
              <label>完成:</label>  
		            <input class="easyui-combobox"  name="report" style="width:60px;"    
                    data-options="
			        method:'get',
	                valueField:'id',
		            textField:'text',
				    url:'${msUrl}/selectdata/ywc.json' ">	
              <label>确认:</label>  
		            <input class="easyui-combobox"  name="accept" style="width:60px;"    
                    data-options="
			        method:'get',
	                valueField:'id',
		            textField:'text',
				    url:'${msUrl}/selectdata/yqr.json' ">	
				<label class="ui-label">计划日期: </label><input name="fromrq" class="easyui-datebox" style="width:100px;">
				<label class="ui-label">至: </label><input name="torq" class="easyui-datebox" style="width:100px;">	 	
                <label class="ui-label">参与人:</label> 
                <input name="cyry" class="easyui-box ui-text" style="width:100px;">
 
				<label class="ui-label">要求完成日期: </label><input name="fromwcrq_yq" class="easyui-datebox" style="width:100px;">
				<label class="ui-label">至: </label><input name="towcrq_yq" class="easyui-datebox" style="width:100px;">	 	
				<label class="ui-label">实际完成日期: </label><input name="fromwcrq" class="easyui-datebox" style="width:100px;">
				<label class="ui-label">至: </label><input name="towcrq" class="easyui-datebox" style="width:100px;">	 	

    
            </p>
            <a id="btn-search" class="easyui-linkbutton" iconCls="icon-search"  onclick="refreshgrid();")>查询</a>
        </form>  
     </div> 
     <!--  Search panel end -->
     <!-- DataList  -->
     <form id="listForm" method="post" >
         <table id="data-list">	 
	    
		 </table>
 	 </form>	
</div>

<script type="text/javascript"> 
   function refreshgrid(){ 
       var param = $("#searchForm").serializeObject();
	  $('#data-list').datagrid('reload',param);
    }
 
	$(function() {  

        var $dg = $("#data-list");  
        $dg.datagrid({ 
 				title:'工作计划列表',
	   			url:'dataListForBrow.do',
				height : 500,  
			    nowrap: false,
				//pagination:true,
  			    //pageSize:100,
			    //pageList: [100, 200, 300, 400, 500],
				singleSelect:true,	
                frozenColumns:[[
					{field:'id',checkbox:true},

					{field:'rq',title:'下达日期',width:70,sortable:true,formatter:function formatterdate(value,row,index) {
                        if (value != null) { 
						    var date = new Date(value);
                            return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                             + date.getDate();
                         }}
                     },
					{field:'tcr',title:'任务提出',width:80,sortable:true},
					{field:'ly',title:'任务来源',width:80,sortable:true},
				    {field:'createBy',title:'任务承担人',width:60,sortable:true},
			    	{field:'cyry',title:'参与人员',width:120,sortable:true},
			    	{field:'rwName',title:'任务名称',width:160,sortable:true}
				]],
	   			columns:[[
	
			    	{field:'rwContent',title:'任务描述',width:160,sortable:true},
			    	{field:'rwResult',title:'任务结果',width:120,sortable:true},
					{field:'wcrq_yq',title:'要求完成日期',width:80,sortable:true,formatter:function formatterdate(value,row,index) {
                        if (value != null) { 
						    var date = new Date(value);
                            return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                             + date.getDate();
                         }}
                     },
					{field:'wcrq',title:'实际完成日期',width:80,sortable:true,formatter:function formatterdate(value,row,index) {
                        if (value != null) { 
						    var date = new Date(value);
                            return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                             + date.getDate();
                         }}
                     },
			    	{field:'wcResult',title:'完成结果说明',width:250,sortable:true},
					{field:'report',title:'汇报',width:50,align:'center',sortable:true,styler:function(value,row,index){
							if(value != 0){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
							
							if(value == 1){
								return "已汇报";
							}
                 
						}},
					{field:'reportTime',title:'汇报时间',width:150,sortable:true},

					{field:'accept',title:'确认',width:50,align:'center',sortable:true,styler:function(value,row,index){
							if(value != 0){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
							
							if(value == 1){
								return "已确认";
							}
                 
						}},

				    {field:'acceptBy',title:'确认人',width:60,sortable:true},
					{field:'acceptTime',title:'确认时间',width:150,sortable:true},
					{field:'state',title:'状态',width:40,align:'center',sortable:true,styler:function(value,row,index){
							if(value != 1){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
							
							if(value == 2){
								return "暂停";
							}
                           if(value == 3){
								return "作废";
							}
						}},


				]],            
	         toolbar : [ 
					
				{id:'btnedit',text:'确认',btnType:'edit' ,
					iconCls : "icon-edit", 
					handler : function() {  
 					    toConfirm();
                    } 
				} ,'-',{  
                text : "导出Excel",  
                iconCls : "icon-undo",  
                handler : function() {  
 					toExcel();
					//downExcel('( 系统管理员)_销售订单明细表_(2017-08-04 17_26_05).xls');
                }   
             } ]  
        }); 
   });	 
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

	function toConfirm(){    
                        
            var effectRow = new Object();  
			var selected =Utils.getCheckedRows();
	
			if (Utils.checkSelectOne(selected)){
				var id=selected[0].id;  //ID
	            effectRow["id"] =id;
				//		alert(id);
                $.post("saveConfirm.do", effectRow,function(rsp) {  
	                //$.messager.alert("提示", rsp.success);  
                    if(rsp.success){  
                        $.messager.alert("提示", "确认成功！");    
                    }  
				    else
				    {
				        $.messager.alert("提示", rsp.msg);  //提交失败！
				    }

                 }, "JSON");
			}

			 $('#data-list').datagrid('load');	
			 var param = $("#searchForm").serializeObject();
			 $('#data-list').datagrid('reload',param);
  	    }
		var Grid = $('#data-list');
		//Grid 工具类
		var Utils = {
			getCheckedRows : function(){
				return Grid.datagrid('getChecked');			
			},
			checkSelect : function(rows){//检查grid是否有勾选的行, 有返回 true,没有返回true
				var records =  rows;
				if(records && records.length > 0){
					return true;
				}
				YiYa.alert('警告','未选中记录.','warning');  
				return false;
				
			},
			checkSelectOne : function(rows){//检查grid是否只勾选了一行,是返回 true,否返回true
				var records = rows;
				if(!Utils.checkSelect(records)){
					return false;
				}
				if(records.length == 1){
					return true;
				}
				YiYa.alert('警告','只能选择一行记录.','warning');  
				return false;
			}
		}
		
   
  function initdate(){
     $("#searchForm input:input[name='fromrq']").val("${requestScope.fromdate}");
     $("#searchForm input:input[name='torq']").val("${requestScope.todate}");
 
  }
  document.body.onload=initdate();
 </script>  


</body>
</html>

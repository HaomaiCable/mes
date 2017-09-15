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
                <!--<label class="ui-label">计划号:</label> 
                <input name="jhbh" class="easyui-box ui-text" style="width:100px;">
  	 	        <label class="ui-label">业务员:</label><input name="ywy" class="easyui-box ui-text" style="width:100px;">
                <label class="ui-label">订单员: </label><input name="createBy" class="easyui-box ui-text" style="width:100px;">
  	 	        <label class="ui-label">产品型号:</label><input name="xh" class="easyui-box ui-text" style="width:100px;">
                <label class="ui-label">产品规格: </label><input name="gg" class="easyui-box ui-text" style="width:100px;">-->
                <!--<label class="ui-label">交货日期: </label><input name="jhrq" class="easyui-datebox" style="width:100px;">-->
            </p>
            <a id="btn-search" class="easyui-linkbutton" iconCls="icon-search"  onclick="refreshgrid();")>查询</a>
        </form>  
     </div> 
     <!--  Search panel end -->
     <!-- DataList  -->
     <form id="listForm" method="post" >
         <table id="data-list" >
             <thead>
             <tr>
   	         <input class="hidden" name="id">
			 <th field="xdrq" width="80" >下达日期</th>
  	         <th field="jhbh" width="80" >计划编号</th>
	         <th field="row" width="30" >行号</th>
   	         <th field="xh" width="150">产品型号</th>
	         <th field="gg" width="120">产品规格</th>					
  			 <th field="dy" width="80" >电压等级</th>   
			 <th field="gy" width="50" >工艺</th>  
			 <th field="dw" width="50" >单位</th>  	
			 <th field="ph" width="130" >批号</th>  			 
			 <th field="sl" width="80" align="right" >计划数量</th>  			  
             <th field="jhrq_kh" width="100" >客户要求交货期</th>
             <th data-options="field:'jhrq', width:100,editor:'datebox'
			  ">计划交货期</th>
	         <th field="jsyq" width="250">技术要求</th>
			 <th data-options="field:'state',width:40,align:'center',styler:function(value,row,index){
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
						}
              ">状态</th>
			  <th data-options="field:'xdjt',width:60,align:'center',styler:function(value,row,index){
							if(value == 1){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
	
							if(value == 1){
								return '下达机台';
							}
   
						}
                ">下达机台</th>
				<th field="createBy" width="100">订单员</th>
	         </tr>
	         </thead> 
		 </table>
 	 </form>	
</div>

<script type="text/javascript"> 
   function refreshgrid(){ 
       var param = $("#searchForm").serializeObject();
	  $('#data-list').datagrid('reload',param);
    }

  
	$(function() {  

		//alert json;
        var $dg = $("#data-list");  
		//$dg.datagrid('reload');
        $dg.datagrid({ 
            url : "dataList.do",  
            // width : 700,  
            height : 500,  
           			
			//loadData:'$(json}', 
			nowrap: false,
			singleSelect:true,
			pagination:true,
		    rownumbers:true,
		    pageSize:100,
			pageList: [100, 200, 300, 400, 500],
			onClickRow: onClickRow,
			ft:'#toolbar',
  
            toolbar : [  {  
                text : "接受编辑",  
                iconCls : "icon-ok",  
                handler :endEdit  
            },'-', {  
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
			
                $.post("${msUrl}/xsddManage/saveJhq.do", effectRow,function(rsp) {  
	                // $.messager.alert("提示", rsp);  
                    if(rsp.status){  
                        $.messager.alert("提示", "提交成功！");  
      
                    }  
					else
					{
					    $.messager.alert("提示", "修改成功");  //提交失败！
					}

               }, "JSON");
			   $dg.datagrid('acceptChanges');  
			   //$('#data-list').datagrid('load');	
			   //var param = $("#searchForm").serializeObject();
			   //$('#data-list').datagrid('reload',param);

	
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

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
				<label class="ui-label">完工日期: </label><input name="fromwgrq" class="easyui-datebox" style="width:100px;">
				<label class="ui-label">至: </label><input name="towgrq" class="easyui-datebox" style="width:100px;">	 
                <label class="ui-label">计划号:</label> 

                <input name="jhbh" class="easyui-box ui-text" style="width:100px;">
                <label>核算:</label>  
		            <input class="easyui-combobox"  name="yhs" style="width:60px;"    
                    data-options="
			        method:'get',
	                valueField:'id',
		            textField:'text',
				    url:'${msUrl}/selectdata/yhs.json' ">    
 
               <label>机台:</label>  
		            <input class="easyui-combobox"  name="sbmc" style="width:100px;"   
				    data-options="
			        url:'../sbManage/getSbListForGd.do?gdid=1',
					multiple:false,
			        valueField:'sbmc',
			        textField:'sbmc'">
              <label>班次:</label>  
		            <input class="easyui-combobox"  name="bc" style="width:60px;"    
                    data-options="
			        method:'get',
	                valueField:'text',
		            textField:'text',
				    url:'${msUrl}/selectdata/bc.json' ">  	 	       
                <!--<label>型号:</label>  
		            <input class="easyui-combobox"  name="gxxh_o" style="width:100px;" 
				    data-options="
	                url:'getUniXh.do',
				    method:'get',
					
			        multiple:false,
			        valueField:'text',
			        textField:'text'">

                <label>规格:</label>  
		            <input  class="easyui-combobox"  name="gxgg_o"  style="width:100px;"
				    data-options="
	                url:'getUniGg.do',
			        method:'get',
				    
			        multiple:false,
			        valueField:'text',
			        textField:'text'">-->
    
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

		//alert json;
        var $dg = $("#data-list");  
		//$dg.datagrid('reload');
        $dg.datagrid({ 
			title:'拔丝工段工时核算',
            url : 'dataListLinkForGd.do?gdid=1',  //1=='拔丝工段'

			//url : 'dataListLinkNoPage.do',  
            // width : 700,  
            height : 540,  

			//loadData:'$(json}', 
			nowrap: true,
			striped: true,
			collapsible:true,
			remoteSort: false,
			singleSelect:true,
		    rownumbers:true,
			pagination:true,
            pageSize:500,
			pageList:[500, 600, 700, 800, 1000],
            method:  'post',
            onClickRow: onClickRow,
			ft:'#toolbar',
            frozenColumns:[[
					{field:'id',checkbox:true},
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
			    	{field:'wgrq',title:'完工日期',width:70,sortable:true },
				    {field:'bc',title:'班次',width:40,align:'center',sortable:true,styler:function(value,row,index){
							if(value != 1){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
							
							if(value == 2){
								return "夜班";
							}
       
					}},
					{field:'jhbh',title:'计划编号',width:95,sortable:true},
					//{field:'row',title:'序号',width:30},
			    	//{field:'gd',title:'工段',width:60,sortable:true},
			    	{field:'sbmc',title:'机台',width:60,sortable:true},
			    	//{field:'sbmcdek',title:'定额库设备',width:80,sortable:true},
			    	{field:'iszl',title:'主零',width:30,sortable:true},
			    	{field:'gxxh',title:'型号',width:70,sortable:true},
			    	//{field:'gxxh_o',title:'型号',width:80,sortable:true},
			    	{field:'gxgg',title:'规格',width:70,sortable:true},
			    	//{field:'gxgg_o',title:'规格',width:80,sortable:true},
			    	{field:'gxdy',title:'电压',width:40,sortable:true},
			    	//{field:'gxdy_o',title:'电压',width:50,sortable:true},
		    	    {field:'gxlb',title:'类别',width:40,sortable:true},
			    	{field:'gxgy',title:'工艺',width:30},
			    	//{field:'gxph',title:'批号',width:120},
			    	{field:'gxdw',title:'单位',width:30},
			    	//{field:'jhsl_o',title:'计划数量',width:60,align:'right' }
			    	{field:'wgsl',title:'完工数量',width:60,align:'right' }

		    ]],

  			columns:[[

			    	{field:'wgsm',title:'完工说明',width:80 },
                    {field:'gs',title:'工时',width:60,align:'right' },
					{field:'gsl',title:'工时率',width:60,align:'right' },
					{field:'gsgz',title:'工时工资',width:60,align:'right' },
			        {field:'yhs',title:'核算',width:40,align:'center',sortable:true,styler:function(value,row,index){
							if(value == 1){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
							
							if(value == 1){
								return "已核算";
							}
             
					}},
			        {field:'yfp',title:'分配',width:40,align:'center',sortable:true,styler:function(value,row,index){
							if(value == 1){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
							
							if(value == 1){
								return "已分配";
							}
             
					}},
				    {field:'hsBy',title:'统计员',width:60,sortable:true},
				    {field:'hsTime',title:'日期',width:150,sortable:true}

			]],  
            toolbar : [ {  
                text : "导出Excel",  
                iconCls : "icon-undo",  
                handler : function() {  
 					toExcel();
                }   
             }]  
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
			
                $.post("save.do", effectRow,function(rsp) {  
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

   function downExcel(fileName){
     window.location.href ="../downLoadManage/downLoad.do?fileName="+fileName; 
   }
	function hsgs(){
            var rows = $('#data-list').datagrid('getRows');
            var effectRow = new Object();  
            effectRow["selected"] =JSON.stringify(rows);  
  
            $.post("saveHsgs.do",effectRow ,function(rsp) {  			
               if(rsp.success){  
			       $.messager.alert("提示",rsp.msg);;
                }  
		        else{
	               $.messager.alert("提示",rsp.msg);  	
		         }
		     });
	}

	function toExcel(){
         var rows = $('#data-list').datagrid('getRows');
         var effectRow = new Object();  
         effectRow["selected"] =JSON.stringify(rows);  
  
         $.post("exportExcelGd.do",effectRow ,function(rsp) {  			
           if(rsp.success){  
			   downExcel(rsp.fileName);
            }  
		    else{
	           $.messager.alert("提示",rsp.msg);  	
		 }
		 });
	}
  function initdate(){
     $("#searchForm input:input[name='fromwgrq']").val("${requestScope.fromdate}");
     $("#searchForm input:input[name='towgrq']").val("${requestScope.todate}");
 
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

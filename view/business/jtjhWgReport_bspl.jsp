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
			        url:'../sbManage/getSbListForGd.do?gdid=1',
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
			title:'拔丝工段批量完工汇报',
            url : 'dataListLinkForGd.do?gdid=1',  //1=='拔丝工段'

			//url : 'dataListLinkNoPage.do',  
            // width : 700,  
            height : 500,  

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
				    {field:'cqts',title:'超期天数',width:60,align:'center',sortable:true,styler:function(value,row,index){
				        if(value >0 ){
						  return 'color:red;';  
					    }
					},
					formatter:function(value,row,index){
						
						if(value == -9999){
							return "未到期";
						}
                        else if(value == 0){
							return "按期";
						}
						else{
							return "("+value+")天";
						}
					}},
					{field:'xdrq',title:'下达日期',width:70,sortable:true,formatter:function formatterdate(value,row,index) {
                        if (value != null) { 
						    var date = new Date(value);
                            return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                             + date.getDate();
                         }}
                     },			
					{field:'jhbh',title:'计划编号',width:90,sortable:true},
					{field:'row',title:'序号',width:30},
			    	{field:'sbmc',title:'机台',width:60,sortable:true},
			    	{field:'iszl',title:'主零',width:30,sortable:true},
			    	{field:'gxxh',title:'型号',width:80,sortable:true},
			    	{field:'gxgg',title:'规格',width:80,sortable:true},
			    	{field:'gxdy',title:'电压',width:50,sortable:true},
		    	    {field:'gxlb',title:'类别',width:40,sortable:true},
			    	{field:'gxgy',title:'工艺',width:30},
			    	{field:'gxdw',title:'单位',width:30},
			    	{field:'jhsl_xs',title:'产品数量',width:60,align:'right' },
			    	{field:'jhsl_o',title:'机台数量',width:60,align:'right' }
		    ]],

  			columns:[[
			         {field:'jhrq',title:'计划日期',width:80,sortable:true,formatter:function formatterdate(value,row,index) {
                        if (value != null) {
                        var date = new Date(value);
                        return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                         + date.getDate();
                         }}
                     },
			    	{field:'wgrq',title:'完工日期',width:90,editor:'datebox' },
			    	{field:'bc',title:'班次',width:50, 
						 editor:{
							  type:'combobox',
							  options:{
							   valueField:'id',
							    textField:'text',
							     url:'${msUrl}/selectdata/bc.json'
	                           }
						  } 
					  },
			    	{field:'wgsl',title:'完工数量',width:60,align:'right',editor:{type:'numberbox',options:{precision:2}} },
			    	{field:'wgsm',title:'文字备注信息',width:100,editor:'text' },
			    	{field:'wg',title:'全部完工',width:80, 
						 editor:{
							  type:'combobox',
							  options:{
							   valueField:'id',
							    textField:'text',
							     url:'${msUrl}/selectdata/rk.json'
	                           }
						  } 
					  },
  
				    {field:'qbWg',title:'全部完工',width:70,align:'center',sortable:true,styler:function(value,row,index){
							if(value != 0){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
							
							if(value == 1){
								return "已全部完工";
							}
                         
					 }},
					 {field:'sumWgsl',title:'合计完工数量',width:80,align:'right' },
					 {field:'wwgsl',title:'未完工数量',width:80,align:'right' },
			         {field:'sumWgslds',title:'完工段数明细',width:120},

                     {field:'maxWgrq',title:'最后完工日期',width:80,sortable:true,formatter:function formatterdate(value,row,index) {
                         if (value != null) {
                             var date = new Date(value);
                             return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                              + date.getDate();
                          }}
                     },
			    	 {field:'gxjsyq',title:'技术要求',width:120},
			    	 {field:'gxph',title:'批号',width:120},
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
 

				     {field:'createBy',title:'计划员',width:80,sortable:true},
				     {field:'createTime',title:'日期',width:150,sortable:true}

			]],  
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
            } ,'-',	{  
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
	       var row = $dg.datagrid('getSelected');  
           if (row) {  
                var rowIndex = $dg.datagrid('getRowIndex', row);  
  				$dg.datagrid('beginEdit', rowIndex);        
			     var ed=$dg.datagrid('getEditor',{
					  index:rowIndex,
					  field:'wgrq'
				  });
				  //alert(ed);
				  if (ed)
				  {
				  
				      $(ed.target).datebox('setValue',myDate);
				  }
				  // ($(ed.target).data('textbox')?$(ed.target).textbox('textbox'):$(ed.target)).focus();

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
			
                $.post("saveWghbpl.do", effectRow,function(rsp) {  
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

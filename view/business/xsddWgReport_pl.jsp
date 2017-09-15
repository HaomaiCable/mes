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
                <label>完工状态:</label>  
		            <input class="easyui-combobox"  name="qbRk" style="width:60px;"    
                    data-options="
			        method:'get',
	                valueField:'id',
		            textField:'text',
				    url:'${msUrl}/selectdata/wgflag.json' ">  
  	 	       
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
			title:'电线完工批量汇报',
	   		url:'dataListLinkNoPage.do', 
            // width : 700,  
            height : 500,  

			//loadData:'$(json}', 
			nowrap: true,
			singleSelect:true,
		    rownumbers:true,
  		    pagination:true,
            pageSize:1000,
			pageList:[1000, 1500, 2000, 2500, 3000],
			onClickRow: onClickRow,
            frozenColumns:[[
					{field:'id',checkbox:true},
					{field:'xdrq',title:'下达日期',width:80,sortable:true,formatter:function formatterdate(value,row,index) {
                        if (value != null) { 
						    var date = new Date(value);
                            return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                             + date.getDate();
                         }}
                     },			
					{field:'jhbh',title:'计划编号',width:80,sortable:true},
					{field:'row',title:'行号',width:40},
			    	{field:'xh',title:'产品型号',width:120,sortable:true},
			    	{field:'gg',title:'产品规格',width:80,sortable:true},
			    	{field:'dy',title:'电压等级',width:60,sortable:true},
			    	{field:'gy',title:'工艺',width:40},
			    	{field:'dw',title:'单位',width:40},
			    	{field:'ph',title:'批号',width:140},
			    	{field:'sl',title:'计划数量',width:60,align:'right' }
		    ]],

	   		columns:[[

			        {field:'jhrq',title:'计划交货期',width:70,sortable:true,formatter:function formatterdate(value,row,index) {
                        if (value != null) {
                        var date = new Date(value);
                        return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                         + date.getDate();
                         }}
                     },
			    	{field:'wgrq',title:'完工日期',width:90,editor:'datebox' },
			    	{field:'wgsl',title:'完工数量',width:60,align:'right',editor:{type:'numberbox',options:{precision:2}} },
			    	{field:'czg',title:'完工主手',width:80, 
						 editor:{
							  type:'combobox',
							  options:{
							      valueField:'xm',
							      textField:'xm',
							      url:'../ygManage/getYgList.do',
								   method:'get'

	                           }
						  } 
					  },
			    	{field:'xpgg',title:'线盘规格',width:60, 
						 editor:{
							  type:'combobox',
							  options:{
							      valueField:'id',
							      textField:'text',
							      url:'${msUrl}/selectdata/ml.json'

	                           }
						  } 
					  },
			    	{field:'cmsl',title:'长米',width:60,align:'right',editor:{type:'numberbox',options:{precision:2}} },

			    	{field:'wgslss',title:'文字备注信息',width:100,editor:'text' },

			    	{field:'jsyq',title:'技术要求',width:160},
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
                    {field:'maxWgrq',title:'最后完工日期',width:80,sortable:true,formatter:function formatterdate(value,row,index) {
                         if (value != null) {
                             var date = new Date(value);
                             return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                              + date.getDate();
                          }}
                     },
			         {field:'sumWgsl',title:'合计完工数量',width:80,align:'right' },
			         {field:'sumWgslds',title:'完工段数明细',width:120},
			         {field:'sumXpgg',title:'使用木轮明细',width:120},
			         {field:'sumCmsl',title:'合计长米数量',width:80,align:'right' },
				     {field:'createBy',title:'订单员',width:80,sortable:true}

			]],
            toolbar : [ 
		        {  
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
           var today = new Date();
           var date= new Date(today);
           date.setDate(today.getDate());
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
  function initdate(){
     $("#searchForm input:input[name='fromxdrq']").val("${requestScope.fromdate}");
     $("#searchForm input:input[name='toxdrq']").val("${requestScope.todate}");
 
  }
  document.body.onload=initdate();
 </script>  


</body>
</html>

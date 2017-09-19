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

                <label>业务员:</label>  
		            <input class="easyui-combobox"  name="ywy"  style="width:60px;"
				    data-options="
			        url:'../ywyManage/getYwyList.do',
			        method:'get',
					multiple:false,
			        valueField:'name',
			        textField:'name'">
               <!--<label>订单员:</label>  
		            <input class="easyui-combobox"  name="createBy" style="width:60px;"   
				    data-options="
			        url:'getUniDdy.do',
					multiple:false,
			        valueField:'text',
			        textField:'text'">-->
  	 	       
               <label>产品型号:</label>  
		            <input class="easyui-combobox"  name="xh"  id="cxxh" 
				    data-options="
	                url:'getUniXh.do',
				    method:'get',
					multiple:false,
			        valueField:'text',
			        textField:'text'
			 
				 ">
                <!--<label>产品型号:</label>  
		            <input type="text"    name="xh"  id="cxxh" onchange="upperCase()"  
				   >-->

                <label>产品规格:</label>  
		            <input  class="easyui-combobox"  name="gg"  
				    data-options="
	                url:'getUniGg.do',
			        method:'get',
				    
			        multiple:false,
			        valueField:'text',
			        textField:'text'">
                <label>完工状态:</label>  
		            <input class="easyui-combobox"  name="qbRk" style="width:60px;"    
                    data-options="
			        method:'get',
	                valueField:'id',
		            textField:'text',
				    url:'${msUrl}/selectdata/wgflag.json' ">  
				<label class="ui-label">交货日期: </label><input name="fromjhrq" class="easyui-datebox" style="width:100px;">
				<label class="ui-label">至: </label><input name="tojhrq" class="easyui-datebox" style="width:100px;">	 
				<label class="ui-label">最后完工日期: </label><input name="frommaxWgrq_Search" class="easyui-datebox" style="width:100px;">
				<label class="ui-label">至: </label><input name="tomaxWgrq_Search" class="easyui-datebox" style="width:100px;">	 
               <!-- <label class="ui-label">计划号:</label> 
                <input name="jhbh" class="easyui-box ui-text" style="width:100px;">
  	 	        <label class="ui-label">业务员:</label><input name="ywy" class="easyui-box ui-text" style="width:100px;">
                <label class="ui-label">订单员: </label><input name="createBy" class="easyui-box ui-text" style="width:100px;">
  	 	        <label class="ui-label">产品型号:</label><input name="xh" class="easyui-box ui-text" style="width:100px;">
                <label class="ui-label">产品规格: </label><input name="gg" class="easyui-box ui-text" style="width:100px;">-->
                <!--<label class="ui-label">交货日期: </label><input name="jhrq" class="easyui-datebox" style="width:100px;"> -->
            </p>
            <a id="btn-search" class="easyui-linkbutton" iconCls="icon-search" onclick="refreshgrid();">查询</a>
        </form>  
     </div> 
     <!--  Search panel end -->
     <!-- DataList  -->
     <form id="listForm" method="post" >
         <table id="data-list"></table>
 	 </form>	
	  <!-- ddbg View Win&From -->
	 <div id="viewddbg-win-dlgbuttons" class="dialog-button" >
       
	     <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo'" 
		   onclick="closeBgList();">返回</a>
     </div>
     <div id="viewddbg-win" class="easyui-dialog"  buttons="#viewddbg-win-dlgbuttons"  
	     title="销售订单变更单" 
	     data-options="closed:true,iconCls:'icon-search',modal:true,
		 onResize:function(){
		 $(this).dialog('center');}"  
		 style="width:750px;height:450px;">  
  	     <form id="viewddbgForm" class="ui-form" method="post"> 
     	      <!-- 隐藏文本框 -->
     	      <input class="hidden" name="id">
 			  <div class="easyui-panel" border='false' style="height:355;">  
	               <div class="easyui-layout" data-options="fit:true">  
                       <div data-options="region:'center'">  
                           <table id="ddbg-list"> </table>
                       </div>
                   </div>
		      </div>
        </form>
    </div>
	<!-- Wghb View Win&From -->
	 <div id="viewwghb-win-dlgbuttons" class="dialog-button" >
       
	     <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo'" 
		   onclick="closeWgList();">返回</a>
     </div>
     <div id="viewwghb-win" class="easyui-dialog"  buttons="#viewwghb-win-dlgbuttons" 
	     title="产品完工汇报单" 
	     data-options="closed:true,iconCls:'icon-search',modal:true"  
		 style="width:750px;height:450px;">  
  	     <form id="viewwghbForm" class="ui-form" method="post"> 
     	      <!-- 隐藏文本框 -->
     	      <input class="hidden" name="id">
 			  <input class="hidden" name="parentId" id='edit_parentId'>
    	      <div class="easyui-panel" border='false' style="height:355;">  
	               <div class="easyui-layout" data-options="fit:true">  
                       <div data-options="region:'center'">  
                           <table id="wghb-list"> </table>
                       </div>
                   </div>
		      </div>
        </form>
    </div>
	<!-- Rkhb View Win&From -->
	 <div id="viewrkhb-win-dlgbuttons" class="dialog-button" >
       
	     <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo'" 
		   onclick="closeRkList();">返回</a>
     </div>
     <div id="viewrkhb-win" class="easyui-dialog"  buttons="#viewrkhb-win-dlgbuttons" 
	     title="产品入库汇报单" 
	     data-options="closed:true,iconCls:'icon-search',modal:true"  
		 style="width:750px;height:450px;">  
  	     <form id="viewrkhbForm" class="ui-form" method="post"> 
     	      <!-- 隐藏文本框 -->
     	      <input class="hidden" name="id">
 			  <input class="hidden" name="parentId" id='edit_parentId'>
    	      <div class="easyui-panel" border='false' style="height:355;">  
	               <div class="easyui-layout" data-options="fit:true">  
                       <div data-options="region:'center'">  
                           <table id="rkhb-list"> </table>
                       </div>
                   </div>
		      </div>
        </form>
    </div>
</div>
<script type="text/javascript"> 
	$(function() {  
        var $dg = $("#data-list");  
        $dg.datagrid({ 
			title:'销售订单列表',
            iconCls: 'icon-search',
			url : "dataListLinkNoPage.do", //dataListLinkNoPage.do
			height : 500,  
			//autoRowHeight: true,
			nowrap:true,
			
			striped: true,
			collapsible:true,
			remoteSort: false,
			pagination:true,
		    pageSize:500,
			pageList: [500, 1000, 1500, 2000, 2500],
            method:  'post',
			rownumbers:true,
			singleSelect:true,	
	
			//ft:'#toolbar',
		    onHeaderContextMenu: function(e, field){
			        e.preventDefault();
			        if (!cmenu){
			    	    createColumnMenu();
			        }
			        cmenu.menu('show', {
				        left:e.pageX,
				        top:-e.pageY
			        });
		    },
            frozenColumns:[[
				   {field:'cqts',title:'超期天数',width:60,align:'center',sortable:true,styler:function(value,row,index){
				        if(value >0 ){
						  return 'color:red;';  
					    }
					},
					formatter:function(value,row,index){
						
						if (row.state==2)
						{
							return "暂停";
						}
						else if (row.state==3)
						{
							return "作废";
						}
						else{
						    if(value == -9999){
							    return "未到期";
						    }
                            else if(value == 0){
							    return "按期";
						    }
						    else{
							    return "("+value+")天";
						    }
						}
					}},
				{field:'xdrq',title:'下达日期',width:70,sortable:true,formatter:function formatterdate(value,row,index) {
                    if (value != null) { 
					    var date = new Date(value);
                        return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                         + date.getDate();
                     }}
                 },

				{field:'jhbh',title:'计划编号',width:80,sortable:true},
				{field:'row',title:'行号',width:30},
				{field:'ywy',title:'业务员',width:50,sortable:true},
			   	{field:'xh',title:'产品型号',width:80,sortable:true},
			   	{field:'gg',title:'产品规格',width:80,sortable:true},
			   	{field:'dy',title:'电压等级',width:50,sortable:true},
			   	{field:'gy',title:'工艺',width:30},
			   	{field:'dw',title:'单位',width:30},
			   	{field:'sl',title:'计划数量',width:60,align:'right' },
			    {field:'jsyq',title:'技术要求',width:140}
		    ]],
	        columns:[[

		      	{field:'ph',title:'批号',width:120},
			    {field:'jhrq_kh',title:'需求交货期',width:70,sortable:true,formatter:function formatterdate(value,row,index) {
                    if (value != null) {
                    var date = new Date(value);
                    return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                     + date.getDate();
                     }}
                 },
			     {field:'jhrq',title:'计划交货期',width:70,sortable:true,formatter:function formatterdate(value,row,index) {
                    if (value != null) {
                        var date = new Date(value);
                        return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                         + date.getDate();
                     }}
                 },
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
	            {field:'bgDetails',title:'变更明细',width:80,align:'center',formatter:function(value,row,index){
						if (row.subCountBg!=0)
						{
						
						    var html ="<a href='#' onclick='toBgList("+row.id+")'>变更明细("+row.subCountBg+")</a>";
						    return html;
						}
					}},
				{field:'xdjt',title:'下达机台',width:60,align:'center',sortable:true,styler:function(value,row,index){
					if(value == 1){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
	
							if(value == 1){
								return "下达机台";
							}
   
						}},
                {field:'ywyy',title:'延误原因',width:120},
			    {field:'ywjt',title:'延误机台',width:70},
                {field:'ecjhq',title:'二次交货期',width:80,sortable:true,formatter:function formatterdate(value,row,index) {
                    if (value != null) {
                        var date = new Date(value);
                        return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                         + date.getDate();
                     }}
                 },
                {field:'maxWgrq',title:'最后完工日期',width:80,formatter:function formatterdate(value,row,index) {
                    if (value != null) {
                        var date = new Date(value);
                        return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                         + date.getDate();
                     }}
                 },
			    {field:'sumWgsl',title:'合计完工数量',width:80,align:'right'},
			    {field:'sumWgslds',title:'完工段数明细',width:120 ,formatter:function(value,row,index){
						if (row.subCountWg!=0)
						{
						
						     var html ="<a href='#' onclick='toWgList("+row.id+")'>"+row.sumWgslds+"</a>";
						     return html;
						}
					}},
			    {field:'sumWgslss',title:'实数段数明细',width:120 ,formatter:function(value,row,index){
						if (row.subCountWg!=0)
						{
						
						     var html ="<a href='#' onclick='toWgList("+row.id+")'>"+row.sumWgslss+"</a>";
						     return html;
						}
					}},
			    {field:'sumXpgg',title:'使用木轮明细',width:120},
			    {field:'sumCmsl',title:'合计长米数量',width:80,align:'right' },
  			    {field:'sumRksl',title:'合计入库数量',width:80 ,align:'right' },  
				{field:'sumRkslds',title:'入库段数明细',width:120 ,formatter:function(value,row,index){
					if (row.subCountRk!=0)
					{
					     var html ="<a href='#' onclick='toRkList("+row.id+")'>"+row.sumRkslds+"</a>";
					    return html;
					}
				}},	
				{field:'qbRk',title:'全部入库',width:70,align:'center',styler:function(value,row,index){
				     if(value != 0){
						  return 'color:red;';  
						}
					},
					formatter:function(value,row,index){
							
						if(value == 1){
							return "已全部入库";
						}
                         
				 }},

			     {field:'createBy',title:'订单员',width:80,sortable:true}
			
			]],
            toolbar : [ {  
                text : "导出Excel",  
                iconCls : "icon-undo",  
                handler : function() {  
 					toExcel();
					//downExcel('( 系统管理员)_销售订单明细表_(2017-08-04 17_26_05).xls');
                }   					
   
             } ]  
 	    });
        $('#data-list').datagrid({   
            rowStyler:function(index,row){   
               if (row.state==2){   
                   return  'background-color:pink;color:blue;font-weight:bold;'; //'color:blue;';
                } 
			    else if (row.state==3)
			    {
				   return 'background-color:yellow;color:red;font-weight:bold;'; //'color:red;'
			    }
				else
				{}
		    }
         });
    });
    $('#cxxh').combobox({  
        onChange:function(newValue,oldValue){  
           // alert(oldValue.toUpperCase());  
		   var upperNewValue= newValue.toUpperCase();
		   //alert(upperNewValue);
		   $("#searchForm input:input[name='xh']").val(upperNewValue);
		  //return upperNewValue;
        }  
    }); 
    function toRkList(id){
		$("#viewrkhb-win").dialog('open');
		$(function() {  
			var $dg = $("#rkhb-list");  
			$dg.datagrid({ 
				//title:'销售订单变更单',
				//iconCls: 'icon-search',
				url : "dataListRkhbForDdid.do?id="+id, 
				width:700,
				height : 335,  
				autoRowHeight:false,
				nowrap:true,
				striped: true,
				collapsible:true,
				remoteSort: false,
				//pagination:true,
				// pageSize:10,
				//pageList: [10, 20, 30, 40, 50],
				method:  'post',
				rownumbers:true,
				singleSelect:true,	
				//ft:'#toolbar',
				columns:[[
					//{field:'id',checkbox:true},
					{field:'wgrq',title:'完工日期',width:80,sortable:true,
						formatter:function formatterdate(value,row,index) {
							if (value != null) { 
								var date = new Date(value);
								return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'+ date.getDate();
							}
						}
					},
					{field:'wgsl',title:'完工数量',width:80,align:'right' },
					{field:'wgslss',title:'完工数量--实数',width:100},	
					{field:'czg',title:'完工机台主手',width:80,sortable:true},
					{field:'xpgg',title:'线盘规格',width:80,sortable:true},	
					{field:'cmsl',title:'长米割去数量',width:80},
					{field:'rk',title:'入库确认',width:70,align:'center',sortable:true,
						styler:function(value,row,index){
						   if(value != 0){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
							
							if(value == 1){
								return "已入库";
							}
						 
						}
					},
					{field:'rksm',title:'入库说明',width:180},	
					{field:'rklrBy',title:'录入',width:80,sortable:true},
					{field:'rklrTime',title:'时间',width:150,sortable:true},
				]]
			});
		});
	} 
   function closeRkList(){
	   $("#viewrkhb-win").dialog('close');
   } 
 
   function toWgList(id){
		 $("#viewwghb-win").dialog('open');
		 $(function() {  
			var $dg = $("#wghb-list");  
			$dg.datagrid({ 
				//title:'销售订单变更单',
				//iconCls: 'icon-search',
				url : "dataListWghbForDdid.do?id="+id, 
				width:700,
				height : 335,  
				autoRowHeight:false,
				nowrap:true,
				striped: true,
				collapsible:true,
				remoteSort: false,
				//pagination:true,
				// pageSize:10,
				//pageList: [10, 20, 30, 40, 50],
				method:  'post',
				rownumbers:true,
				singleSelect:true,	
				//ft:'#toolbar',
				columns:[[
					//{field:'id',checkbox:true},
					{field:'wgrq',title:'完工日期',width:100,sortable:true,formatter:function formatterdate(value,row,index) {
						if (value != null) { 
							var date = new Date(value);
							return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
							 + date.getDate();
						 }}
					 },
					{field:'wgsl',title:'完工数量',width:80,align:'right' },
					{field:'wgslss',title:'完工数量--实数',width:150},	
					{field:'czg',title:'完工机台主手',width:100,sortable:true},
					{field:'xpgg',title:'线盘规格',width:80,sortable:true},	
					{field:'cmsl',title:'长米割去数量',width:100},

					{field:'lrBy',title:'录入',width:80,sortable:true},
					{field:'lrTime',title:'时间',width:150,sortable:true},

				]]
			});
	   });
   } 
   function closeWgList(){
	   $("#viewwghb-win").dialog('close');
   } 
   function toBgList(id){
	    $("#viewddbg-win").dialog('open');
	   	$(function() {  
            var $dg = $("#ddbg-list");  
            $dg.datagrid({ 
			    //title:'销售订单变更单',
                //iconCls: 'icon-search',
			    url : "dataListChangeForDdid.do?id="+id, 
			    width:700,
			    height : 335,  
			    autoRowHeight:false,
			    nowrap:true,
			    striped: true,
			    collapsible:true,
			    remoteSort: false,
			    //pagination:true,
		        // pageSize:10,
			    //pageList: [10, 20, 30, 40, 50],
                method:  'post',
			    rownumbers:true,
			    singleSelect:true,	
	   			columns:[[
					{field:'id',checkbox:true},
					{field:'bh',title:'变更单编号',width:80,sortable:true},	
					{field:'row',title:'行号',width:30},
					{field:'jhbh',title:'销售订单编号',width:90,sortable:true},	
					{field:'jhbhrow',title:'行号',width:30},

			    	{field:'field',title:'变更项目',width:80,sortable:true},
			    	{field:'oldContent',title:'变更前内容',width:180,
	                     formatter:function(value,row,index){
	                         if (row.field=='状态')
	                         {
							      if(value == 1){
								      return '正常';
							       } 
								   else if (value==2)
								   {
									   return '暂停';
								   }
								   else if (value==3)
								   {
									   return '作废';
								   }
							}
	                        else{
								return value;
							}
   
					}},
					{field:'newContent',title:'变更后内容',width:160,
                        formatter:function(value,row,index){
	                         if (row.field=='状态')
	                         {
							      if(value == 1){
								      return '正常';
							       } 
								   else if (value==2)
								   {
									   return '暂停';
								   }
								   else if (value==3)
								   {
									   return '作废';
								   }
							}
	                        else{
								return value;
							}
   
   
					}},
		            {field:'checked',title:'变更审核',width:60,align:'center',sortable:true,styler:function(value,row,index){
							if(value == 1){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
	
							if(value == 1){
								return "已审核";
							}
   
					}},				
		            {field:'accept',title:'变更确认',width:60,align:'center',sortable:true,styler:function(value,row,index){
							if(value == 1){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
	
							if(value == 1){
								return "已确认";
							}
   
					}},									    
					{field:'createBy',title:'订单员',width:60,sortable:true},
					{field:'createTime',title:'变更时间',width:150,sortable:true},
		            {field:'checkBy',title:'审核人',width:60,sortable:true},
					{field:'checkTime',title:'审核时间',width:150,sortable:true},	
				    {field:'acceptBy',title:'确认人',width:60,sortable:true},
					{field:'acceptTime',title:'确认时间',width:150,sortable:true}
				]]
 	        });
		});
   } 
   function closeBgList(){
	   $("#viewddbg-win").dialog('close');
   } 
   function refreshgrid(){ 
         var param = $("#searchForm").serializeObject();
	     $('#data-list').datagrid('reload',param);
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
				 } 
				 else {
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
   function initdate(){
     $("#searchForm input:input[name='fromxdrq']").val("${requestScope.fromdate}");
     $("#searchForm input:input[name='toxdrq']").val("${requestScope.todate}");
   }
 document.body.onload=initdate();
 var param =$('#searchForm').serializeObject();
 $('#data-list').datagrid('reload',param);
</script>  
</body>
</html>

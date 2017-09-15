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

            </p>
            <a id="btn-search" class="easyui-linkbutton" iconCls="icon-search" onclick="refreshgrid();">查询</a>
        </form>  
     </div> 
     <!--  Search panel end -->
     <!-- DataList  -->
     <form id="listForm" method="post" >
         <table id="data-list"></table>
 	 </form>	
	 <form id="listzbmxForm" method="post" >
     <table id="data-zbmxlist"></table>  
    </form>

</div>
<script type="text/javascript"> 
   
   function refreshgrid(){ 
       var param = $("#searchForm").serializeObject();
	  $('#data-list').datagrid('reload',param);
    }

  function toTbList(ggbh){

	 $.post("dataListForGgbh.do?ggbh="+ggbh,function(rsp) {  
	
                    if(rsp.success){  
       
					    $('#data-zbmxlist').datagrid('loadData',rsp.tbxx);
                        //$.messager.alert("提示", rsp.tbxx);   
                    }  
					else
					{
	                     $.messager.alert("提示",rsp.msg);  	
					}

               }, "JSON");

  } 


  $(function() {  
        var $dg = $("#data-list");  
        $dg.datagrid({ 
 				title:'已审批的中标供应商列表',
	   			url:'dataListDist.do',
				height : 315,  
			    nowrap: true,
			    pagination:true,
  			    pageSize:8,
			    pageList: [8, 20, 30, 40, 50],
				singleSelect:true,	
	            columns:[[
					{field:'id',checkbox:true},
				    {field:'sp',title:'审批',width:80,align:'center',sortable:true,styler:function(value,row,index){
							if(value == 1){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
	
							if(value == 1){
								return "已审批";
							}
						  
					}},
                    {field:'countZbwxs',title:'投标明细',width:80,align:'center',formatter:function(value,row,index){
						if (row.subCountTb!=0)
						{

							var html ="<a href='#' onclick='toTbList("+row.ggbh+")'>投标明细("+row.subCountTb+")</a>";
						    return html;
						}
					}},
					{field:'ggbh',title:'公告编号',width:100,sortable:true},
					{field:'fbrq',title:'发布日期',width:80,sortable:true,formatter:function formatterdate(value,row,index) {
                        if (value != null) { 
						    var date = new Date(value);
                            return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                             + date.getDate();
                         }}
                     },

					{field:'wlfl',title:'采购分类',width:120,sortable:true},
				    {field:'yxrq',title:'投标截止日期',width:140,sortable:true},
				    {field:'qdBy',title:'中标确认',width:70,sortable:true},
					{field:'qdTime',title:'确认时间',width:130,sortable:true},
				    {field:'spBy',title:'审批人',width:70,sortable:true},
					{field:'spTime',title:'审批时间',width:130,sortable:true},
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
			
				    {field:'createBy',title:'采购员',width:80,sortable:true}

				]]
	 	    });
            var $dgmx = $("#data-zbmxlist");  
            $dgmx.datagrid({ 
			     url : "dataListForGgbh.do?ggbh="+'0', 
			     height:185,
			     nowrap:true,
			     striped: true,
			     collapsible:true,
			     remoteSort: false,
		         method:  'post',
		         rownumbers:true,
			     singleSelect:true,
		         columns:[[
					{field:'zb',title:'中标',width:40,align:'center',sortable:true,styler:function(value,row,index){
				         if(value != 0){
						      return 'color:red;';  
						    }
					    },
				     	formatter:function(value,row,index){
							
						    if(value == 1){
							    return "中标";
						    }
                         
				     }},
                    {field:'gysStr',title:'供应商名称',width:120,sortable:true},
					{field:'row',title:'序号',width:40},
			    	{field:'wlmc',title:'物料名称',width:80,sortable:true},
			    	{field:'wlgg',title:'物料规格',width:120,sortable:true},
			    	{field:'wldw',title:'单位',width:40},
			    	{field:'wlsl',title:'采购数量',width:80,align:'right' },
			        {field:'jhrq_xq',title:'需求交货期',width:70,sortable:true,formatter:function     formatterdate(value,row,index) {
                        if (value != null) {
                        var date = new Date(value);
                        return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                         + date.getDate();
                         }}
                     },
			    	{field:'jsyq',title:'技术要求',width:160},
			    	{field:'tbsl',title:'投标数量',width:80,align:'right' ,styler:function(value,row,index){
							if(row.kb != 1){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
							
							if(row.kb !=1){
								return "******";
							}
                           else {
								return value;
							}
					}},
			    	{field:'dj',title:'含税单价',width:80,align:'right' ,styler:function(value,row,index){
							if(row.kb != 1){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
							
							if(row.kb !=1){
								return "******";
							}
                           else {
								return value;
							}
					}},
			    	{field:'je',title:'价税合计',width:80,align:'right',styler:function(value,row,index){
							if(row.kb != 1){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
							
							if(row.kb !=1){
								return "******";
							}
                           else {
								return value;
							}
					}},
		            {field:'jhrq',title:'交货日期',width:70,sortable:true,styler:function(value,row,index){
							if(row.kb != 1){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
							
							if(row.kb !=1){
								return "******";
							}
                           else {
								return value;
							}
					}},
			    	{field:'tbsm',title:'投标说明信息',width:260,styler:function(value,row,index){
							if(row.kb != 1){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
							
							if(row.kb !=1){
								return "******";
							}
                           else {
								return value;
							}
					}},
				     {field:'createBy',title:'投标人',width:80,sortable:true},
				     {field:'createTime',title:'日期',width:150,sortable:true}

				]]

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

   function initdate(){
     $("#searchForm input:input[name='fromfbrq']").val("${requestScope.fromdate}");
     $("#searchForm input:input[name='tofbrq']").val("${requestScope.todate}");
   }
  document.body.onload=initdate();

 </script>  


</body>
</html>

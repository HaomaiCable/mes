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

   function toMxList(ggbh){
		//var ggbh=obj.ggbh;
		//alert(ggbh);
	   	$(function() {  
            var $dg1 = $("#data-zbmxlist");  
            $dg1.datagrid({ 
			     url : "dataListForGgbh.do?ggbh="+ggbh, 
			     height:185,
			     nowrap:true,
			     striped: true,
			     collapsible:true,
			     remoteSort: false,
		         method:  'post',
		         rownumbers:true,
			     singleSelect:true,
		         columns:[[
					{field:'row',title:'序号',width:40},
			    	{field:'wlmc',title:'物料名称',width:120,sortable:true},
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
			    	{field:'jsyq',title:'技术要求',width:260}
			    ]]
 	       });
       });
    } 


  $(function() {  
        var $dg = $("#data-list");  
        $dg.datagrid({ 
  			title:'采购招标公告列表',
	   		url:'dataListDist.do',
            iconCls: 'icon-search',
			height : 315,  
			nowrap: false,
			agination:true,
			pagination:true,
  			pageSize:8,
			pageList: [8, 20, 30, 40, 50],
            method:  'post',
			rownumbers:true,
			singleSelect:true,	
		    columns:[[
					{field:'id',checkbox:true},
				    {field:'kb',title:'开标',width:50,align:'center',sortable:true,styler:function(value,row,index){
							if(value == 1){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
	
							if(value == 1){
								return "已开标";
							}
							else if(value == 2){
								return "有投标";
							}			
   
					}},
                    {field:'countZbwxs',title:'物资明细',width:80,align:'center',formatter:function(value,row,index){
						if (row.subCountMx!=0)
						{
						    //var ggbh="'"+row.ggbh+"'";
							//var obj = new Object(); 
			                //obj.ggbh = row.ggbh;
							//alert(ggbh);
							var html ="<a href='#' onclick='toMxList("+row.ggbh+")'>物资明细("+row.subCountMx+")</a>";
							//var html ="<a href='#' onclick='toWgList("+row.id+")'>"+row.sumWgslds+"</a>";
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
			     url : "dataListForGgbh.do?ggbh="+'ZB', 
			     height:185,
			     nowrap:true,
			     striped: true,
			     collapsible:true,
			     remoteSort: false,
		         method:  'post',
		         rownumbers:true,
			     singleSelect:true,
		         columns:[[

					{field:'row',title:'序号',width:40},
			    	{field:'wlmc',title:'物料名称',width:120,sortable:true},
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
			    	{field:'jsyq',title:'技术要求',width:260}
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

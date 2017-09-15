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
	 <form id="listtbxxForm" method="post" >
         <table id="data-tbxxlist" ></table>  
     </form>
</div>
  <script type="text/javascript" src="${msUrl}/js/commons/YDataGrid.js"></script>
  <script type="text/javascript" src="${msUrl}/js/ux/cgpt/tbManage.js"></script> 

 <script type="text/javascript"> 
 
    function refreshgrid(){ 
       var param = $("#searchForm").serializeObject();
	  $('#data-list').datagrid('reload',param);
    }


 function toTbList(ggbh){

	 $.post("dataListForGgbh.do?ggbh="+ggbh,function(rsp) {  
	
                    if(rsp.success){  
       
					    $('#data-tbxxlist').datagrid('loadData',rsp.tbxx);
                        //$.messager.alert("提示", rsp.msg);   
                    }  
					else
					{
	                     $.messager.alert("提示",rsp.msg);  	
					}

               }, "JSON");

  } 
  
  
   $(function() {  

            var $dgmx = $("#data-tbxxlist");  
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
  var param =$('#searchForm').serializeObject();
  $('#data-list').datagrid('reload',param);

</script>  	
</body>
</html>

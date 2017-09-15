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

            <label>设备名称:</label>  
		    <input class="easyui-combobox"  name="sbmcdek"  
				    data-options="
	                url:'getUniSbmc.do',
				    method:'get',
					multiple:false,
			        valueField:'text',
			        textField:'text'">
            <label>型号:</label>  
		    <input class="easyui-combobox"  name="gxxh"  
				    data-options="
	                url:'getUniXh.do',
				    method:'get',
					multiple:false,
			        valueField:'text',
			        textField:'text'">
            <label>规格:</label>  
		    <input class="easyui-combobox"  name="gxgg"  
				    data-options="
	                url:'getUniGg.do',
				    method:'get',
					multiple:false,
			        valueField:'text',
			        textField:'text'">
            <label>电压:</label>  
		    <input class="easyui-combobox"  name="gxdy"  
				    data-options="
	                url:'getUniDy.do',
				    method:'get',
					multiple:false,
			        valueField:'text',
			        textField:'text'">
            <label>类别:</label>  
		    <input class="easyui-combobox"  name="gxlb"  
				    data-options="
	                url:'getUniLb.do',
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
     <table id="data-list"></table>

</div>

  <script type="text/javascript" src="${msUrl}/js/commons/YDataGrid.js"></script>
  <script type="text/javascript" src="${msUrl}/js/ux/business/gsdeManage.js"></script> 
  <!--<script type="text/javascript" src="${msUrl}/js/ux/business/xsddMxManage.js"></script>  -->

<script type="text/javascript">


</script>
</body>
</html>

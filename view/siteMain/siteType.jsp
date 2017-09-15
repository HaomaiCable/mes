<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <%@include file="/view/resource.jsp" %>
  </head>
  <body>
<div class="warp easyui-panel" data-options="border:false">

	 <!-- Search panel start -->
 	 <div class="easyui-panel ui-search-panel" title="Search box" data-options="striped: true,collapsible:true,iconCls:'icon-search'">  
 	 <form id="searchForm">
 	 	<p class="ui-fields">
            <label class="ui-label">Name:</label>
            <input name="name" class="easyui-box ui-text" style="width:100px;">
        </p>
        <a href="#" id="btn-search" class="easyui-linkbutton" iconCls="icon-search">Search</a>
      </form>  
     </div> 
     <!--  Search panel end -->
     
     <!-- Data List -->
     <form id="listForm" method="post">
     <table id="data-list"></table>
     </form>
     
     <!-- Edit Win&Form -->
     <div id="edit-win" class="easyui-dialog" title="Basic window" data-options="closed:true,iconCls:'icon-save',modal:true" style="width:400px;height:300px;">  
     	<form id="editForm" class="ui-form" method="post">  
     		 <input class="hidden" name="id">
     		  <div class="ui-edit">
		     	   <div class="ftitle">SiteType Information</div>    
		           <div class="fitem">  
		               <label>Name:</label>  
		               <input class="easyui-validatebox" type="text" name="name" data-options="required:true">
		           </div>  
		            <div class="fitem">  
		               <label>Code:</label>  
		               <input class="easyui-validatebox" type="text" name="code" data-options="required:true">
		           </div> 
		           <div class="fitem">  
		               <label>Rank:</label>  
		               <input class="easyui-numberbox" type="text" value="0" name="rank" data-options="required:true,min:0,max:999">
		           </div> 
		           <div class="fitem">  
		               <label>State:</label>  
		               <select class="easyui-combobox" name="state" data-options="required:true">
	                    	<option value="0" selected="selected">可用</option>
	                    	<option value="1">禁用</option>
                    	</select>
		           </div> 
		           <div class="fitem">  
		               <label>Description:</label>  
		               <textarea class="easyui-validatebox" data-options="length:[0,100]" name="descr"></textarea>
		           </div> 
	         </div>
     	</form>
  	 </div> 
</div>  	 
<!-- Js Start -->
<script type="text/javascript" src="${msUrl}/js/commons/YDataGrid.js"></script>
<script type="text/javascript" src="${msUrl}/js/ux/siteMain/siteType.js"></script>
  </body>
</html>

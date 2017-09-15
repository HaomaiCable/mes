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
 	 	   <label class="ui-label">Account:</label> 
 	 	   <input name="email" class="easyui-box ui-text" style="width:100px;">
           <label class="ui-label"> Name:</label> 
           <input name="nickName" class="easyui-box ui-text" style="width:100px;">
        </p>
        <a href="#" id="btn-search" class="easyui-linkbutton" iconCls="icon-search">Search</a>
      </form>  
     </div> 
     <!--  Search panel end -->
     
     <!-- DataList  -->
     <form id="listForm" method="post">
     <table id="data-list"></table>
     </form>

     <!-- Edit Win&Form -->
     <div id="edit-win" class="easyui-dialog" title="Edit" data-options="closed:true,iconCls:'icon-save',modal:true" style="width:400px;height:300px;">  
     	<form id="editForm" class="ui-form" method="post">  
     		 <input class="hidden" type="text" name="id">
     		  <div class="ui-edit">
		     	   <div class="ftitle">Information</div>    
		           <div class="fitem">  
		               <label>Email:</label>  
		               <input class="easyui-validatebox" type="text" readonly="readonly" name="email" data-options="required:true"></input>
		           </div>  
		            <div class="fitem">  
		               <label>Roles:</label>  
		               <select class="easyui-combobox" id="roleIds" name="roleIds"  
                    		   data-options="width:135"></select>
		           </div> 
	         </div>
     	</form>
  	 </div> 

  	
</div>

<script type="text/javascript" src="${msUrl}/js/commons/YDataGrid.js"></script>
<script type="text/javascript" src="${msUrl}/js/ux/sys/sysUserRole.js"></script>
</body>
</html>

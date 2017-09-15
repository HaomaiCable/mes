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
 	 	    <label class="ui-label">Account:</label><input name="email" class="easyui-box ui-text" style="width:100px;">
            <label class="ui-label">Name: </label><input name="nickName" class="easyui-box ui-text" style="width:100px;">
        </p>
        <a href="#" id="btn-search" class="easyui-linkbutton" iconCls="icon-search">Search</a>
      </form>  
     </div> 
     <!--  Search panel end -->
     
     <!-- DataList  -->
     <form id="listForm" method="post">
     <table id="data-list"></table>
     </form>

     <!-- Edit Form -->
     <div id="edit-win" class="easyui-dialog" title="Edit" data-options="closed:true,iconCls:'icon-save',modal:true" style="width:400px;height:300px;">  
     	<form id="editForm" class="ui-form" method="post">  
     		 <input class="hidden" type="text" name="id">
     		 <input class="hidden" name="deleted">
     		 <div class="ui-edit">
	     	   <div class="ftitle">User Information</div>    
	           <div class="fitem">  
	               <label>Account:</label>  
	               <input class="easyui-validatebox" type="text" name="email" data-options="required:true">
	           </div>  
	            
	           <div class="fitem">  
	               <label>Name:</label>  
	               <input class="easyui-validatebox" type="text" name="nickName" data-options="required:true,validType:'length[1,10]'">
	           </div>
	            <div class="fitem">  
	               <label>State:</label>  
	               <select class="easyui-combobox" name="state" data-options="required:true">
                    	<option value="0" selected="selected">可用</option>
                    	<option value="1">禁用</option>
                   	</select>
	           </div> 
	         </div>
     	</form>
  	 </div> 
  	 
  	 <!-- Edit Password Form -->
     <div id="edit-pwd-win" class="easyui-dialog" buttons="#editPwdbtn" title="Edit PassWord" data-options="closed:true,iconCls:'icon-save',modal:true" style="width:400px;height:300px;">
     	<form id="pwdForm" class="ui-form" method="post">  
     		 <input class="hidden" name="id">
     		 <div class="ui-edit">
	     	   <div class="ftitle">User Information</div>    
	           <div class="fitem">  
	               <label>Account:</label>  
	               <input class="easyui-validatebox" type="text" readonly="readonly" name="email" data-options="required:true">
	           </div>  
	           <div class="fitem">  
	              <label>Old PassWord:</label>  
	              <input id="oldPwd" name="oldPwd" type="password" class="easyui-validatebox"/>
	           </div>
	            <div class="fitem">  
	               <label>New PassWord:</label>  
	               <input id="newPwd" name="newPwd" type="password" class="easyui-validatebox" data-options="required:true" />
	           </div> 
	           <div class="fitem">  
	               <label>Re PassWord:</label>  
	              <input id="rpwd" name="rpwd" type="password" class="easyui-validatebox"   required="required" validType="equals['#newPwd']" />
	           </div> 
	         </div>
     	</form>
     	 <div id="editPwdbtn" class="dialog-button">  
            <a href="javascript:void(0)" class="easyui-linkbutton" id="btn-pwd-submit">提交</a>  
            <a href="javascript:void(0)" class="easyui-linkbutton" id="btn-pwd-close">放弃</a>  
        </div>
  	 </div> 
  	
</div>

<script type="text/javascript" src="${msUrl}/js/commons/YDataGrid.js"></script>
<script type="text/javascript" src="${msUrl}/js/ux/sys/sysUser.js"></script>
  </body>
</html>

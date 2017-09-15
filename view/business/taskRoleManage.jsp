<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <%@include file="/view/resource.jsp" %>
  </head>
  <body>
<div class="warp easyui-panel" data-options="border:false">

	 <!-- Search panel start -->
 	 <div class="easyui-panel ui-search-panel" title="查询窗口" data-options="striped: true,collapsible:true,iconCls:'icon-search'">  
 	 <form id="searchForm">
 	 	<p class="ui-fields">
            <label class="ui-label">计划管理者姓名:</label>
            <input name="userName" class="easyui-box ui-text" style="width:120px;">


        </p>
        <a href="#" id="btn-search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
      </form>  
     </div> 
     <!--  Search panel end -->
     
     <!-- Data List -->
     <form id="listForm" method="post">
     <table id="data-list"></table>
     </form>
     
     <!-- Edit Win&Form -->
     <div id="edit-win" class="easyui-dialog" title="供应商档案" data-options="closed:true,iconCls:'icon-save',modal:true" style="width:660px;height:300px;">  
     	<form id="editForm" class="ui-form" method="post">  
     		 <input class="hidden" name="id">
     		  <div class="ui-edit">
		     	   <div class="ftitle">工作计划权限信息</div>   
   	               <div class="fitem">  

                        <label>管理者姓名:</label>  
                         <input id='roleIds_combobox' class="easyui-combobox" 
							name="userId" style="width:100px;"
							data-options="
								url:'../sysUser/sysUserListJson.do',
								valueField:'id',
								textField:'nickName',
								multiple:false,
								">	
	               </div>
	               <div class="fitem">  								
                       <label>被管理者姓名:</label>  
                       <input id='roleIds_combobox' class="easyui-combobox" 
							name="roleIds" style="width:500px;"
							data-options="
								url:'../sysUser/sysUserListJson.do',
								valueField:'id',
								textField:'nickName',
								multiple:true,
								">				   

		           </div> 

	         </div>
     	</form>
  	 </div> 
</div>  	 
<!-- Js Start -->
<script type="text/javascript" src="${msUrl}/js/commons/YDataGrid.js"></script>
<script type="text/javascript" src="${msUrl}/js/ux/business/taskRoleManage.js"></script>
  </body>
</html>

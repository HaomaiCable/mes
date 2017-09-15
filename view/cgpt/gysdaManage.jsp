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
            <label class="ui-label">供应商名称:</label>
            <input name="name" class="easyui-box ui-text" style="width:120px;">

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
     <div id="edit-win" class="easyui-dialog" title="供应商档案" data-options="closed:true,iconCls:'icon-save',modal:true" style="width:700px;height:320px;">  
     	<form id="editForm" class="ui-form" method="post">  
     		 <input class="hidden" name="id">
     		  <div class="ui-edit">
		     	   <div class="ftitle">供应商档案信息</div>    
		           <div class="fitem">  
		               <label>供应商名称:</label>  
		               <input class="easyui-validatebox" type="text" name="name" style="width:200px;" data-options="required:true">
		               <label>地址:</label>  
		               <input class="easyui-validatebox" type="text" name="addr" style="width:200px;">
		           </div>  
		           <div class="fitem">  
		               <label>法定代表人:</label>  
		               <input class="easyui-validatebox" type="text" name="fr" style="width:200px;" >
		               <label>开户银行:</label>  
		               <input class="easyui-validatebox" type="text" name="khh" style="width:200px;">
		           </div> 
		           <div class="fitem">  
		               <label>账户:</label>  
		               <input class="easyui-validatebox" type="text" name="zh" style="width:200px;" >
		               <label>税号:</label>  
		               <input class="easyui-validatebox" type="text" name="sh" style="width:200px;">
		           </div> 
		           <div class="fitem">  
		               <label>联系人:</label>  
		               <input class="easyui-validatebox" type="text" name="lxr" style="width:200px;">
		               <label>手机:</label>  
		               <input class="easyui-validatebox" type="text" name="phone" style="width:200px;" data-options="required:true">
		           </div> 
		           <div class="fitem">  
		               <label>电话:</label>  
		               <input class="easyui-validatebox" type="text" name="tel" style="width:200px;" >
		               <label>传真:</label>  
		               <input class="easyui-validatebox" type="text" name="fax" style="width:200px;">
		           </div> 
		           <div class="fitem">  
		               <label>Email:</label>  
		               <input class="easyui-validatebox" type="text" name="email" style="width:200px;" data-options="required:true">
            
		
		           </div> 
                   <div class="fitem">  
                       <label>供应物资分类:</label>  
                       <input id='cgflIds_combobox' class="easyui-combobox" 
							name="cgflIds" style="width:200px;"
							data-options="
								url:'../cgflManage/cgflListJson.do',
								valueField:'id',
								textField:'name',
								multiple:true,
								panelHeight:'auto'">
                      <!-- <label>供应物资明细:</label>  
                       <input id='zbwzIds_combobox' class="easyui-combobox" 
							name="zbwzIds" style="width:200px;"
							data-options="
								url:'../zbwzManage/zbwzListJson.do',
								valueField:'id',
								textField:'name',
								multiple:true,
								panelHeight:'auto'">
		
		           </div> 
                   <div class="fitem">  -->
		               <label>状态:</label>  
		               <select class="easyui-combobox" name="state" data-options="required:true">
	                    	<option value="0" selected="selected">可用</option>
	                    	<option value="1">禁用</option>
                    	</select>
		           </div> 

	         </div>
     	</form>
  	 </div> 
</div>  	 
<!-- Js Start -->
<script type="text/javascript" src="${msUrl}/js/commons/YDataGrid.js"></script>
<script type="text/javascript" src="${msUrl}/js/ux/cgpt/gysdaManage.js"></script>
  </body>
</html>

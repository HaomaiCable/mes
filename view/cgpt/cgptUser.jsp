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
 	 	    <label class="ui-label">账号:</label><input name="email" class="easyui-box ui-text" style="width:100px;">
            <label class="ui-label">姓名: </label><input name="nickName" class="easyui-box ui-text" style="width:100px;">
        </p>
        <a href="#" id="btn-search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
      </form>  
     </div> 
     <!--  Search panel end -->
     
     <!-- DataList  -->
     <form id="listForm" method="post">
     <table id="data-list"></table>
     </form>

     <!-- Edit Form -->
     <div id="edit-win" class="easyui-dialog" title="编辑" data-options="closed:true,iconCls:'icon-save',modal:true" style="width:400px;height:300px;">  
     	<form id="editForm" class="ui-form" method="post">  
     		 <input class="hidden" type="text" name="id">
     		 <input class="hidden" name="deleted">
     		 <div class="ui-edit">
	     	   <div class="ftitle">供应商用户信息</div>    
	           <div class="fitem">  
	               <label>账号:</label>  
	               <input class="easyui-validatebox" type="text" name="email" data-options="required:true">
	           </div>  
	            
	           <div class="fitem">  
	               <label>姓名:</label>  
	               <input class="easyui-validatebox" type="text" name="nickName" data-options="required:true,validType:'length[1,10]'">
	           </div>
			   <div class="fitem">  
                   <label>所属供应商:</label>  
		               <input class="easyui-combobox"  name="gysId"  style="width:100px;"
				           data-options="
			                   url:'../gysdaManage/gysListJson.do',
			                   method:'get',
					           multiple:false,
			                   valueField:'id',
			                   textField:'name'">
	           </div>
	           <div class="fitem">  
	               <label>状态:</label>  
	               <select class="easyui-combobox" name="state" data-options="required:true">
                    	<option value="0" selected="selected">可用</option>
                    	<option value="1">禁用</option>
                   	</select>
	           </div> 
	         </div>
     	</form>
  	 </div> 
  	 
  	 <!-- Edit Password Form -->
     <div id="edit-pwd-win" class="easyui-dialog" buttons="#editPwdbtn" title="修改密码" data-options="closed:true,iconCls:'icon-save',modal:true" style="width:400px;height:300px;">
     	<form id="pwdForm" class="ui-form" method="post">  
     		 <input class="hidden" name="id">
     		 <div class="ui-edit">
	     	   <div class="ftitle">供应商用户信息</div>    
	           <div class="fitem">  
	               <label>账户:</label>  
	               <input class="easyui-validatebox" type="text" readonly="readonly" name="email" data-options="required:true">
	           </div>  
	           <div class="fitem">  
	              <label>原密码:</label>  
	              <input id="oldPwd" name="oldPwd" type="password" class="easyui-validatebox"/>
	           </div>
	            <div class="fitem">  
	               <label>新密码:</label>  
	               <input id="newPwd" name="newPwd" type="password" class="easyui-validatebox" data-options="required:true" />
	           </div> 
	           <div class="fitem">  
	               <label>确认密码:</label>  
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
<script type="text/javascript" src="${msUrl}/js/ux/cgpt/cgptUser.js"></script>
  </body>
</html>

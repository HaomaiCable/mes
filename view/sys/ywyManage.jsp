<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <%@include file="/view/resource.jsp" %>
  </head>
	<body>
<div class="warp easyui-panel" data-options="border:false">
	<!-- Search panel start -->
 	 <div class="easyui-panel ui-search-panel" title="查找窗口" data-options="striped: true,collapsible:true,iconCls:'icon-search'">  
 	 <form id="searchForm">
 	 	<p class="ui-fields">
            <label class="ui-label">业务员姓名:</label> 
            <input name="name" class="easyui-box ui-text" style="width:100px;">
        </p>  
        <a href="#" id="btn-search" class="easyui-linkbutton" iconCls="icon-search">查找</a>
      </form>  
     </div> 
     <!--  Search panel end -->
     
     <!-- DataList  -->
     <form id="listForm" method="post">
     <table id="data-list"></table>
	 </form> 
     <!-- Edit Form -->
     <div id="edit-win" class="easyui-dialog" title="Edit" data-options="closed:true,iconCls:'icon-save',modal:true" style="width:400px;height:410px;">  
     	<form id="editForm" class="ui-form" method="post">  
     		 <input class="hidden" type="text" name="id">
     	 <div class="ui-edit">
	       <div class="ftitle">业务员信息</div>    
	            <div class="fitem">  
	               <label>姓名:</label>  
	               <input class="easyui-validatebox" type="text" name="name" data-options="required:true,validType:'length[1,10]'">
	            </div>
				<div class="fitem">  
				   <label>部门:</label>  
	               <input class="easyui-validatebox" type="text" name="department" >
	            </div>  
	            <div class="fitem">  
	               <label>状态:</label>  
	               <select class="easyui-combobox" name="state" data-options="required:true">
                    	<option value="0" selected="selected">可用</option>
                    	<option value="1">禁用</option>
                   	</select>
	            </div>  
	       </div>	               
	   </div>
  </form>
</div> 
  
</div>

<script type="text/javascript" src="${msUrl}/js/commons/YDataGrid.js"></script>
<script type="text/javascript" src="${msUrl}/js/ux/sys/ywyManage.js"></script>
  </body>
</html>

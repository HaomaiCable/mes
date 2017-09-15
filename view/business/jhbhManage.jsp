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
                <label class="ui-label">年度:</label> 
                <input name="nian" class="easyui-box ui-text" style="width:100px;">
                <label class="ui-label">月度:</label> 
                <input name="yue" class="easyui-box ui-text" style="width:100px;">
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
     <div id="edit-win" class="easyui-dialog" title="Edit" data-options="closed:true,iconCls:'icon-save',modal:true" style="width:400px;height:250px;">  
     	 <form id="editForm" class="ui-form" method="post">  
     		 <input class="hidden" type="text" name="id">
     	     <div class="ui-edit">
	             <div class="ftitle">单据顺序号信息</div>    
	             <div class="fitem">  
	                <label>单据分类:</label>  
	                <select class="easyui-combobox" name="flag" data-options="required:true" disabled="true" >
                     	<option value="1" selected="selected">销售订单</option>
                     	<option value="2">销售订单变更单</option>
                     	<option value="3" selected="selected">半成品计划单</option>
                     	<option value="4">生产计划单变更单</option>
		            </select>
	             </div>  
	             <div class="fitem">  
	                 <label>年度</label>  
	                 <input class="easyui-validatebox" type="text" name="nian" readonly="readonly" >
	             </div>
                 <div class="fitem">  
	                 <label>月度</label>  
	                 <input class="easyui-validatebox" type="text" name="yue" readonly="readonly" >
	             </div>
                <div class="fitem">  
	                 <label>已用最大顺序号</label>  
	                 <input class="easyui-validatebox" type="text" name="jhno" data-options="required:true,validType:'length[1,10]'" >
	             </div>
			
	         </div>	               
         </form>
     </div> 
</div>

<script type="text/javascript" src="${msUrl}/js/commons/YDataGrid.js"></script>
<script type="text/javascript" src="${msUrl}/js/ux/business/jhbhManage.js"></script>
  </body>
</html>

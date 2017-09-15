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
            <label class="ui-label">设备名称:</label> 
            <input name="sbmc" class="easyui-box ui-text" style="width:100px;">
            <label class="ui-label">定额库设备名称:</label> 
            <input name="deksbmc" class="easyui-box ui-text" style="width:100px;">
            <label class="ui-label">所属机台:</label> 
            <input name="jt" class="easyui-box ui-text" style="width:100px;">
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
	             <div class="ftitle">设备信息</div>    
	             <div class="fitem">  
	                <label>设备名称:</label>  
	                <input class="easyui-validatebox" type="text" name="sbmc" data-options="required:true,validType:'length[1,30]'">
	             </div>
	             <div class="fitem">  
	                <label>定额库设备名称:</label>  
	                <input class="easyui-validatebox" type="text" name="deksbmc" data-options="required:true,validType:'length[1,30]'">
	             </div>
	             <div class="fitem">  
	                <label>设备台数:</label>  
	                <input class="easyui-validatebox"  name="sbsl" data-options="required:true,editor:{type:'numberbox',options:{precision:1}}">
	             </div>
	             <div class="fitem">  
	                <label>工作班次:</label>  
	                <input class="easyui-validatebox"  name="bc" data-options="required:true,editor:{type:'numberbox',options:{precision:1}}">
	             </div>
	             <div class="fitem">  
	                <label>出勤工时:</label>  
	                <input class="easyui-validatebox"  name="cqgs" data-options="required:true,editor:{type:'numberbox',options:{precision:1}}">
	             </div>
	             <div class="fitem">  
	                <label>负荷修正系统:</label>  
	                <input class="easyui-validatebox"  name="tzxs" data-options="editor:{type:'numberbox',options:{precision:1}}">
	             </div>
				 <div class="fitem">  
				     <label>所属机台:</label>  
                     <input class="easyui-combobox"  name="jt"  
					      data-options="
				            url:'../jtManage/getJtList.do',
				            method:'get',
				            valueField:'jtmc',
				            textField:'jtmc'">
	             </div>  
	
	             <div class="fitem">  
	                 <label>状态:</label>  
	                 <select class="easyui-combobox" name="state" data-options="required:true">
                    	<option value="0" selected="selected">可用</option>
                    	<option value="1">不可用</option>
                     </select>
	            </div>  
	       </div>	               
       </form>
   </div> 
</div>

<script type="text/javascript" src="${msUrl}/js/commons/YDataGrid.js"></script>
<script type="text/javascript" src="${msUrl}/js/ux/business/sbManage.js"></script>
  </body>
</html>

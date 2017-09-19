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
              <label>工段:</label>  
		            <input class="easyui-combobox"  name="gd" style="width:100px;"   
				    data-options="
			        url:'${msUrl}/selectdata/gd.json',
					method:'get',
					multiple:false,
			        valueField:'id',
			        textField:'text'">      
 
               <label>机台:</label>  
		            <input class="easyui-combobox"  name="sbmc" style="width:100px;"   
				    data-options="
			        url:'../sbManage/getSbList.do',
					multiple:false,
			        valueField:'sbmc',
			        textField:'sbmc'">
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
     <div id="edit-win" class="easyui-dialog" title="Edit" data-options="closed:true,iconCls:'icon-save',modal:true" style="width:300px;height:310px;">  
     	 <form id="editForm" class="ui-form" method="post">  
     		 <input class="hidden" type="text" name="id">
     	     <div class="ui-edit">
	             <div class="ftitle">机台工时工资率</div>    
	             <div class="fitem">  
                     <label>工段:</label>  
		                <input class="easyui-combobox"  name="gd" style="width:100px;"   
				           data-options="
			               url:'${msUrl}/selectdata/gd.json',
					       method:'get',
					       multiple:false,
			               valueField:'id',
			               textField:'text'">      
	             </div>
				 <div class="fitem">  
				     <label>机台名称:</label>  
                     <input class="easyui-combobox"  name="sbmc"  
					      data-options="
				            url:'../sbManage/getSbList.do',
				            method:'get',
				            valueField:'sbmc',
				            textField:'sbmc'">
	             </div>  
				 <div class="fitem">  	
                     <label>人数(不分输入0):</label>  
	                     <input class="easyui-validatebox" type="numberbox" name="jtrs" data-options="required:true,options:{precision:0}">	
	             </div>  
				 <div class="fitem">  	
                     <label>工时工资率:</label>  
	                     <input class="easyui-validatebox" type="numberbox" name="gsl" data-options="required:true,options:{precision:2}">	
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
 </div>

<script type="text/javascript" src="${msUrl}/js/commons/YDataGrid.js"></script>
<script type="text/javascript" src="${msUrl}/js/ux/business/gzhsGslManage.js"></script>
  </body>
</html>

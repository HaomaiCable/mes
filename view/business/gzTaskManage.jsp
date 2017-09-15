<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
       <%@include file="/view/resource.jsp" %>
	  <!-- <script type="text/javascript" src="${msUrl}/js/commons/ajaxfileupload.js"></script> -->
  </head>
<body>
<div class="warp easyui-panel"  data-options="border:false">
  	 <!-- Search panel start -->
 	 <div class="easyui-panel ui-search-panel" title="查询窗口" data-options="striped: true,collapsible:true,iconCls:'icon-search'">   
 	     <form id="searchForm">
 	 	    <input class="hidden" id='search_parentId' name="parentId">
 	 	    <p class="ui-fields">
						
				<label class="ui-label">计划日期: </label><input name="fromrq" class="easyui-datebox" style="width:100px;">
				<label class="ui-label">至: </label><input name="torq" class="easyui-datebox" style="width:100px;">	 	
              <label>完成:</label>  
		            <input class="easyui-combobox"  name="report" style="width:60px;"    
                    data-options="
			        method:'get',
	                valueField:'id',
		            textField:'text',
				    url:'${msUrl}/selectdata/ywc.json' ">	
              <label>确认:</label>  
		            <input class="easyui-combobox"  name="accept" style="width:60px;"    
                    data-options="
			        method:'get',
	                valueField:'id',
		            textField:'text',
				    url:'${msUrl}/selectdata/yqr.json' ">	  
                <label class="ui-label">参与人:</label> 
                <input name="cyry" class="easyui-box ui-text" style="width:100px;">
 
				<label class="ui-label">要求完成日期: </label><input name="fromwcrq_yq" class="easyui-datebox" style="width:100px;">
				<label class="ui-label">至: </label><input name="towcrq_yq" class="easyui-datebox" style="width:100px;">	 	
				<label class="ui-label">实际完成日期: </label><input name="fromwcrq" class="easyui-datebox" style="width:100px;">
				<label class="ui-label">至: </label><input name="towcrq" class="easyui-datebox" style="width:100px;">	 	

            </p>
            <a id="btn-search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
        </form>  
     </div> 
     <!--  Search panel end -->
     <!-- DataList  -->
     <form id="listForm" method="post" >
         <table id="data-list">
		 </table>
 	 </form>	

     <!-- Edit Form -->
     <div id="edit-win" class="easyui-dialog" title="编辑" data-options="closed:true,iconCls:'icon-save',modal:true" style="width:700px;height:540px;">  
     	 <form id="editForm" class="ui-form" method="post">  
     		 <input class="hidden" type="text" name="id">
     	     <div class="ui-edit">
	             <div class="ftitle">工作计划信息</div>    
	             <div class="fitem">  
	                <label>任务提出部门:</label>  
	                <input class="easyui-validatebox" type="text" name="tcr"  style="width:200px;" data-options="required:true,validType:'length[1,10]'">
	                <label>任务来源:</label>  
	                <input class="easyui-validatebox" type="text" name="ly" style="width:200px;" data-options="required:true,validType:'length[1,30]'">

	             </div>
				 <div class="fitem"> 
				     <label>提出时间:</label>  
	                 <input class="easyui-datebox"  name="rq" style="width:200px;" > 
	                <label>参与人员:</label>  
	                <input class="easyui-validatebox" type="text" name="cyry" style="width:200px;" data-options="required:true,validType:'length[1,100]'">				     
    
	             </div>  
			     <div class="fitem">  
		               <label>任务名称:</label>  
	                   <textarea id="rwName" name="rwName" cols="82"
	                       rows="4"></textarea>
				 </div>
			     <div class="fitem">  
		               <label>任务描述:</label>  
	                   <textarea id="rwContent" name="rwContent" cols="82"
	                       rows="4"></textarea>
				 </div>
			     <div class="fitem">  
		               <label>任务结果:</label>  
	                   <textarea id="rwResult" name="rwResult" cols="82"
	                       rows="4"></textarea>
				 </div>
	             <div class="fitem">  
					 <label>要求完成时间:</label>  
	                 <input class="easyui-datebox"  name="wcrq_yq" style="width:200px;" > 
	            </div>  
	             <div class="fitem">  
					 <label>-----------------------</label>  
	            </div>  
	             <div class="fitem">  
					 <label>实际完成时间:</label>  
	                 <input class="easyui-datebox"  name="wcrq" style="width:200px;" > 
	            </div>  
			     <div class="fitem">  
		               <label>完成结果说明:</label>  
	                   <textarea id="wcResult" name="wcResult" cols="82"
	                       rows="4"></textarea>
				 </div>
	       </div>	               
       </form>
   </div> 

</div>

  <script type="text/javascript" src="${msUrl}/js/commons/YDataGrid.js"></script>
  <script type="text/javascript" src="${msUrl}/js/ux/business/gzTaskManage.js"></script> 
  <!-- <script type="text/javascript" src="${msUrl}/js/commons/json.js"></script> -->
  <!--<script type="text/javascript" src="${msUrl}/js/ux/business/xsddMxManage.js"></script>  -->
 <script type="text/javascript">   


  function initdate(){
     $("#searchForm input:input[name='fromrq']").val("${requestScope.fromdate}");
     $("#searchForm input:input[name='torq']").val("${requestScope.todate}");
    // $("#searchForm").find("#btn-search").click();
     //var param =$('#searchForm').serializeObject();
    // $('#data-list').datagrid('reload',param);
	 //$('#data-list').datagrid('load')
  }
  document.body.onload=initdate();
  var param =$('#searchForm').serializeObject();
  $('#data-list').datagrid('reload',param);

</script>  	
</body>
</html>

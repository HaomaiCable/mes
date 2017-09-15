<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
       <%@include file="/view/resource.jsp" %>
  </head>
<body>
<div class="warp easyui-panel"  data-options="border:false">
  	 <!-- Search panel start -->
 	 <div class="easyui-panel ui-search-panel" title="查询窗口" data-options="striped: true,collapsible:true,iconCls:'icon-search'">   
 	     <form id="searchForm">
 	 	    <input class="hidden" id='search_parentId' name="parentId">
 	 	    <p class="ui-fields">
				<label class="ui-label">变更日期: </label><input name="frombgrq" class="easyui-datebox" style="width:100px;">
				<label class="ui-label">至: </label><input name="tobgrq" class="easyui-datebox" style="width:100px;">	 		
                <label class="ui-label">变更单编号:</label> 
                <input name="bh" class="easyui-box ui-text" style="width:80px;">
                <label class="ui-label">机台计划编号:</label> 
                <input name="jhbh" class="easyui-box ui-text" style="width:80px;"> 
                <label>工段:</label>  
		            <input class="easyui-combobox"  name="gd" style="width:80px;"    
                    data-options="
			        method:'get',
	                valueField:'id',
		            textField:'text',
				    url:'${msUrl}/selectdata/gd.json' ">  				
                <label>机台:</label>  
		            <input class="easyui-combobox"  name="sbmc"    
				    data-options="
			        url:'../sbManage/getSbList.do',
					multiple:false,
			        valueField:'sbmc',
			        textField:'sbmc'">
                <label>确认:</label>  
		            <input class="easyui-combobox"  name="accept" style="width:60px;"    
                    data-options="
			        method:'get',
	                valueField:'id',
		            textField:'text',
				    url:'${msUrl}/selectdata/yqr.json' ">  
                <label>变更项目:</label>  
		        <input class="easyui-box ui-text"  name="field" style="width:80px;">
  
            </p>
            <a id="btn-search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
        </form>  
     </div> 
     <!--  Search panel end -->
     <!-- DataList  -->
     <form id="listForm" method="post" >
         <table id="data-list" >
		 </table>
 	 </form>	
	  <!-- Edit Win&From -->

	  <!-- Edit Win&From -->
     <div id="edit-win-dlgbuttons" class="dialog-button" >
  
	    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo'" 
		   onclick="cancelquit();">返回</a>
     </div>
     <div id="edit-win" class="easyui-dialog"   buttons="#edit-win-dlgbuttons"  
	     title="机台计划变更单查看" 
	     data-options="closed:true,iconCls:'icon-edit',modal:true"  
		 style="width:760px;height:650px;">  

     	 <form id="editForm" class="ui-form" method="post"> 
     	      <!-- 隐藏文本框 -->
     	      <input class="hidden" name="id">
 			  <!--<input class="hidden" name="parentId" id='edit_parentId'> -->
    	      <div class="easyui-panel" border='false' style="height:590;">  
	              <div class="easyui-layout" data-options="fit:true">  
	                   <div data-options="region:'north',split:true" style="height:90px;padding:10px">  

                            <div class="ftitle">机台计划单信息</div>    
		                    <div class="fitem">  
					            <label>变更单编号:</label>  
					            <input type="text" name="bh"   style="width:100px;" readonly="readonly">
								<label>计划单号:</label>  
                                <input type="text" name="jhbh" style="width:120px;" readonly="readonly">


		                    </div> 
					   </div>
					   <div data-options="region:'center'">  
					         <div class="fitem">  
					             <label>设备名称:</label>  
					             <input  type="text" name="sbmc"   style="width:160px;" readonly="readonly">
					             <label>---->:</label>  
					             <input  type="text" name="sbmcnew"   style="width:160px;" readonly="readonly">
							 </div>	
					         <div class="fitem">  
					             <label>主零:</label>  
					             <input  type="text" name="iszl"   style="width:160px;" readonly="readonly">
					             <label>---->:</label>  
					             <input  type="text" name="iszlnew"   style="width:160px;" readonly="readonly">
							 </div>								 
						     <div class="fitem">  
					             <label>型号:</label>  
					             <input  type="text" name="gxxh"   style="width:160px;" readonly="readonly">
					             <label>---->:</label>  
					             <input  type="text" name="gxxhnew"   style="width:160px;" readonly="readonly">
							 </div>
					         <div class="fitem">  
					             <label>规格:</label>  
					             <input  type="text" name="gxgg"   style="width:160px;" readonly="readonly">
					             <label>---->:</label>  
					             <input  type="text" name="gxggnew"   style="width:160px;" readonly="readonly">
							 </div>
					         <div class="fitem">  
					             <label>电    压:</label>  
					             <input  type="text" name="gxdy"   style="width:160px;" readonly="readonly">
					             <label>---->:</label> 
                                 <input  class="easyui-combobox"  name="gxdynew"   style="width:160px;" 
                                      data-options="
					                      method:'get',
	                                      valueField:'id',
								          textField:'text',
										  disabled:true,
								          url:'${msUrl}/selectdata/dy.json'">
		
							 </div>
					         <div class="fitem">  
					             <label>工艺类型:</label>  
					             <input  type="text" name="gxgy"   style="width:160px;" readonly="readonly">
					             <label>---->:</label>  
					             <input  class="easyui-combobox"  name="gxgynew"   style="width:160px;" 
                                      data-options="
					                      method:'get',
	                                      valueField:'id',
								          textField:'text',
										  disabled:true,
								          url:'${msUrl}/selectdata/gy.json'" >
							 </div>
					         <div class="fitem">  
					             <label>单    位:</label>  
					             <input  type="text" name="gxdw"   style="width:160px;" readonly="readonly">
                                 <label>---->:</label>  
				                 <input  class="easyui-combobox"  name="gxdwnew"   style="width:160px;"  
                                      data-options="
					                      method:'get',
	                                      valueField:'id',
								          textField:'text',
                                          disabled:true,
								          url:'${msUrl}/selectdata/dwnocheck.json'">

							 </div>    
					         <div class="fitem">  
					             <label>类    别:</label>  
					             <input  type="text" name="gxlb"   style="width:160px;" readonly="readonly">
                                 <label>---->:</label>  
                                 <input  type="text" name="gxlbnew"   style="width:160px;" readonly="readonly">

							 </div> 
					         <div class="fitem">  
					             <label>产品数量:</label>  
					             <input  type="text" name="jhsl_xs"   style="width:160px;" readonly="readonly" >
					             <label>---->:</label>  
					             <input  type="text" name="jhsl_xsnew"   style="width:160px;" readonly="readonly" data-options="editor:{type:'numberbox',options:{precision:1}}">
							 </div>
					         <div class="fitem">  
					             <label>机台数量:</label>  
					             <input  type="text" name="jhsl_o"   style="width:160px;" readonly="readonly" >
					             <label>---->:</label>  
					             <input  type="text" name="jhsl_onew"   style="width:160px;" readonly="readonly" data-options="editor:{type:'numberbox',options:{precision:1}}">
							 </div>		
					         <div class="fitem">  
					             <label>完工日期:</label>  
					             <input  type="text" name="jhrq"  style="width:160px;" readonly="readonly">
					             <label>---->:</label>  
					             <input   class="easyui-datebox"  name="jhrqnew"  style="width:160px;" disabled="true">
							 </div>
					         <div class="fitem">  
					             <label>技术要求:</label>  

                                 <textarea id="gxjsyq" name="gxjsyq" cols="28"
	                                 rows="7" readonly="readonly"></textarea>
					             <label>---->:</label>  
	                             <textarea id="gxjsyqnew" name="gxjsyqnew" cols="28"
	                                 rows="7" readonly="readonly"></textarea>
							 </div>   
				            <div class="fitem">  
					             <label>批    号:</label>  
					             <input  type="text" name="gxph"   style="width:160px;" readonly="readonly">
					             <label>---->:</label>  
					             <input  type="text" name="gxphnew"   style="width:160px;" readonly="readonly" >
							 </div>
					         <div class="fitem">  
					             <label>状    态:</label>  

                                 <input  class="easyui-combobox" id="state" name="state"   style="width:160px;"    
                                      data-options="
					                      method:'get',
	                                      valueField:'id',
								          textField:'text',
										  disabled:true,
										  url:'${msUrl}/selectdata/state.json' ">
					             <label>---->:</label>  
                                 <input  class="easyui-combobox"  name="statenew"   style="width:160px;" 
                                      data-options="
					                      method:'get',
	                                      valueField:'id',
								          textField:'text',
										  disabled:true,
								          url:'${msUrl}/selectdata/state.json' ">
							 </div>  					
					         <div class="fitem">  
					             <label>确认人:</label>  
					             <input  type="text" name="acceptBy"   style="width:200px;color:red;" readonly="readonly" >
					             <label>确认时间:</label>  
					             <input  type="text" name="acceptTime"   style="width:200px;color:red;" readonly="readonly">
							 </div>							 
                    </div>
                 </div>
               </div>
		   </div>
       </form>
   </div>
</div>

  <script type="text/javascript" src="${msUrl}/js/commons/YDataGrid.js"></script>
  <script type="text/javascript" src="${msUrl}/js/ux/business/jtjhChangeBrow.js"></script> 
 
<script type="text/javascript"> 
    function cancelquit(){ 
 
       $('#edit-win').dialog('close');
       $('#edit-win').resetForm();
     
    }
    function downExcel(fileName){
     window.location.href ="../downLoadManage/downLoad.do?fileName="+fileName; 
    }


	function toExcel(){
         var rows = $('#data-list').datagrid('getRows');
         var effectRow = new Object();  
         effectRow["selected"] =JSON.stringify(rows);  
  
         $.post("exportExcelBgd.do",effectRow ,function(rsp) {  			
           if(rsp.success){  
			   downExcel(rsp.fileName);
            }  
		    else{
	           $.messager.alert("提示",rsp.msg);  	
		 }
		 });
	 }
	function toExcelJh(){
         var rows = $('#data-list').datagrid('getRows');
         var effectRow = new Object();  
         effectRow["selected"] =JSON.stringify(rows);  
  
         $.post("exportExcelBgjh.do",effectRow ,function(rsp) {  			
           if(rsp.success){  
			   downExcel(rsp.fileName);
            }  
		    else{
	           $.messager.alert("提示",rsp.msg);  	
		 }
		 });
	 }
 	 
</script>  



</body>
</html>

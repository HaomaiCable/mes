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
	
                <label class="ui-label">变更单编号:</label> 
                <input name="bh" class="easyui-box ui-text" style="width:80px;">
                <label class="ui-label">销售订单编号:</label> 
                <input name="jhbh" class="easyui-box ui-text" style="width:80px;">      
                <label>审核:</label>  
		            <input class="easyui-combobox"  name="checked" style="width:60px;"    
                    data-options="
			        method:'get',
	                valueField:'id',
		            textField:'text',
				    url:'${msUrl}/selectdata/ysh.json' ">        
                <label>确认:</label>  
		            <input class="easyui-combobox"  name="accept" style="width:60px;"    
                    data-options="
			        method:'get',
	                valueField:'id',
		            textField:'text',
				    url:'${msUrl}/selectdata/yqr.json' ">  

		        <input class="easyui-box ui-text"  name="acceptBy" style="width:80px;">
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
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" 
		   onclick="savechange();">确认变更</a>      
	    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" 
		   onclick="cancelquit();">放弃</a>
     </div>
     <div id="edit-win" class="easyui-dialog"   buttons="#edit-win-dlgbuttons"  
	     title="销售订单变更确认" 
	     data-options="closed:true,iconCls:'icon-edit',modal:true"  
		 style="width:760px;height:580px;">  

     	 <form id="editForm" class="ui-form" method="post"> 
     	      <!-- 隐藏文本框 -->
     	      <input class="hidden" name="id">
 			  <!--<input class="hidden" name="parentId" id='edit_parentId'> -->
    	      <div class="easyui-panel" border='false' style="height:480;">  
	              <div class="easyui-layout" data-options="fit:true">  
	                   <div data-options="region:'north',split:true" style="height:90px;padding:10px">  
	                        <div class="ftitle">销售订单信息</div>    
		                    <div class="fitem">  
					            <label>变更单编号:</label>  
					            <input type="text" name="bh"   style="width:100px;" readonly="readonly">
								<label>计划单号:</label>  
                                <input type="text" name="jhbh" style="width:100px;" readonly="readonly">
	                            <label>行号:</label>  
                                <input  type="text" name="row" style="width:40px;" readonly="readonly">	

		                    </div> 
 
					   </div>
					   <div data-options="region:'center'">  
					     <div class="fitem">  
					             <label>业务员:</label>  
					             <input  type="text" name="ywy"   style="width:160px;" readonly="readonly">
					             <label>---->:</label>  
					             <input  class="easyui-combobox" name="ywynew"   style="width:160px;" 
			                         data-options="
			                             url:'../ywyManage/getYwyList.do',
			                             method:'get',
										 disabled:true,
					                     multiple:false,
			                             valueField:'name',
			                             textField:'name'">
							 </div>
						     <div class="fitem">  
					             <label>批    号:</label>  
					             <input  type="text" name="ph"   style="width:160px;" readonly="readonly">
					             <label>---->:</label>  
					             <input  type="text" name="phnew"   style="width:160px;"  readonly="readonly">
							 </div>
						     <div class="fitem">  
					             <label>产品型号:</label>  
					             <input  type="text" name="xh"   style="width:160px;" readonly="readonly">
					             <label>---->:</label>  
					             <input  type="text" name="xhnew"   style="width:160px;"  readonly="readonly">
							 </div>
					         <div class="fitem">  
					             <label>产品规格:</label>  
					             <input  type="text" name="gg"   style="width:160px;" readonly="readonly">
					             <label>---->:</label>  
					             <input  type="text" name="ggnew"   style="width:160px;" readonly="readonly" >
							 </div>
					         <div class="fitem">  
					             <label>电    压:</label>  
					             <input  type="text" name="dy"   style="width:160px;" readonly="readonly">
					             <label>---->:</label> 
                                 <input  class="easyui-combobox"  name="dynew"   style="width:160px;" 
                                      data-options="
					                      method:'get',
	                                      valueField:'id',
								          textField:'text',
										  disabled:true,
								          url:'${msUrl}/selectdata/dy.json'">
		
							 </div>
					         <div class="fitem">  
					             <label>工艺类型:</label>  
					             <input  type="text" name="gy"   style="width:160px;" readonly="readonly">
					             <label>---->:</label>  
					             <input  class="easyui-combobox"  name="gynew"   style="width:160px;" 
                                      data-options="
					                      method:'get',
	                                      valueField:'id',
								          textField:'text',
										  disabled:true,
								          url:'${msUrl}/selectdata/gy.json'">
							 </div>
					         <div class="fitem">  
					             <label>单    位:</label>  
					             <input  type="text" name="dw"   style="width:160px;" readonly="readonly">
                                 <label>---->:</label>  
				                 <input  class="easyui-combobox"  name="dwnew"   style="width:160px;" 
                                      data-options="
					                      method:'get',
	                                      valueField:'id',
								          textField:'text',
										  disabled:true,
								          url:'${msUrl}/selectdata/dwnocheck.json'">

							 </div>    
					         <div class="fitem">  
					             <label>数    量:</label>  
					             <input  type="text" name="sl"   style="width:160px;" readonly="readonly" >
					             <label>---->:</label>  
					             <input  type="text" name="slnew"   style="width:160px;" readonly="readonly" data-options="editor:{type:'numberbox',options:{precision:1}}">
							 </div>
					         <div class="fitem">  
					             <label>交货日期:</label>  
					             <input  type="text" name="jhrq"  style="width:160px;" readonly="readonly" >
					             <label>---->:</label>  
					             <input   class="easyui-datebox"  name="jhrqnew"  style="width:160px;" disabled="true" >
							 </div>
					         <div class="fitem">  
					             <label>技术要求:</label>  
                                 <textarea id="jsyq" name="jsyq" cols="28"
	                                 rows="7" readonly="readonly"></textarea>
					             <label>---->:</label>  
	                             <textarea id="jsyqnew" name="jsyqnew" cols="28"
	                                 rows="7" readonly="readonly"></textarea>
							 </div>   
					         <div class="fitem">  
					             <label>状    态:</label>  
					             <!--<input  type="text"  name="state"   style="width:160px;" readonly="readonly">  -->
                                 <input  class="easyui-combobox" id="state" name="state"   style="width:160px;"    
                                      data-options="
					                      method:'get',
	                                      valueField:'id',
								          textField:'text',
										  disabled:true,
										  url:'${msUrl}/selectdata/state.json'">
					             <label>---->:</label>  
                                 <input  class="easyui-combobox"  name="statenew"   style="width:160px;" 
                                      data-options="
					                      method:'get',
	                                      valueField:'id',
								          textField:'text',
										  disabled:true,
								          url:'${msUrl}/selectdata/state.json'">
							 </div>  	
				         <div class="fitem">  
					             <label>审核人:</label>  
					             <input  type="text" name="checkBy"   style="width:200px;color:red;" readonly="readonly" >
					             <label>审核时间:</label>  
					             <input  type="text" name="checkTime"   style="width:200px;color:red;" readonly="readonly">
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
  <script type="text/javascript" src="${msUrl}/js/ux/business/xsddChangeAccept.js"></script> 
 
<script type="text/javascript"> 
    function cancelquit(){ 
       $.messager.confirm('提示','你确认要放弃【变更单确认】吗',function(r){  
	       if (r){  
	
               $('#edit-win').dialog('close');
		       $('#edit-win').resetForm();
	  
           }
	   })
    }
	function savechange(){
		var effectRow = new Object();  
	    var bh=$("#editForm input:input[name='bh']").val(); 
	    effectRow["bh"] =bh;

        $.post("${msUrl}/xsddManage/saveDdbgAccept.do", effectRow,function(rsp) {  
	
                    if(rsp.success){  
       
					    //var param = $("#searchForm").serializeObject();
			            //$('#data-list').datagrid('reload',param);
                        $.messager.alert("提示", rsp.msg);   
                    }  
					else
					{
	                     $.messager.alert("提示",rsp.msg);  	
					}

               }, "JSON");

			   $('#edit-win').dialog('close');
			   $('#edit-win').resetForm();

			   var param =$('#searchForm').serializeObject();
               $('#data-list').datagrid('reload',param);
	}

 	 
</script>  



</body>
</html>

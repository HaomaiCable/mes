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

				<label class="ui-label">下达日期: </label><input name="fromxdrq" class="easyui-datebox" style="width:100px;">
				<label class="ui-label">至: </label><input name="toxdrq" class="easyui-datebox" style="width:100px;">	 	
                 <label class="ui-label">计划号:</label> 

                <input name="jhbh" class="easyui-box ui-text" >
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
  	 	       
                <label>型号:</label>  
		            <input class="easyui-combobox"  name="gxxh"  
				    data-options="
	                url:'getUniXh.do',
				    method:'get',
					
			        multiple:false,
			        valueField:'text',
			        textField:'text'">

                <label>规格:</label>  
		            <input  class="easyui-combobox"  name="gxgg"  
				    data-options="
	                url:'getUniGg.do',
			        method:'get',
				    
			        multiple:false,
			        valueField:'text',
			        textField:'text'">
            </p>
            <a id="btn-search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
        </form>  
     </div> 
     <!--  Search panel end -->
     <!-- DataList  -->
     <form id="listForm" method="post" >
         <table id="data-list"></table>
		 
 	 </form>	
	  <!-- jhbg View Win&From -->
	 <div id="viewjhbg-win-dlgbuttons" class="dialog-button" >
       
	     <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo'" 
		   onclick="closeBgList();">返回</a>
     </div>
     <div id="viewjhbg-win" class="easyui-dialog"  buttons="#viewjhbg-win-dlgbuttons"  
	     title="生产计划变更单" 
	     data-options="closed:true,iconCls:'icon-search',modal:true,
		 onResize:function(){
		 $(this).dialog('center');}"  
		 style="width:750px;height:450px;">  
  	     <form id="viewjhbgForm" class="ui-form" method="post"> 
     	      <!-- 隐藏文本框 -->
     	      <input class="hidden" name="id">
 			  <div class="easyui-panel" border='false' style="height:355;">  
	               <div class="easyui-layout" data-options="fit:true">  
                       <div data-options="region:'center'">  
                           <table id="jhbg-list"> </table>
                       </div>
                   </div>
		      </div>
        </form>
    </div>
	  <!-- Edit Win&From -->
     <div id="edit-win-dlgbuttons" class="dialog-button" >
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" 
		   onclick="savechange();">保存</a>      
	    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" 
		   onclick="cancelquit();">放弃</a>
     </div>
     <div id="edit-win" class="easyui-dialog"   buttons="#edit-win-dlgbuttons"  
	     title="机台计划变更" 
	     data-options="closed:true,iconCls:'icon-edit',modal:true"  
		 style="width:760px;height:620px;">  

     	 <form id="editForm" class="ui-form" method="post"> 
     	      <!-- 隐藏文本框 -->
     	      <input class="hidden" name="id">
 			  <!--<input class="hidden" name="parentId" id='edit_parentId'> -->
    	      <div class="easyui-panel" border='false' style="height:540;">  
	              <div class="easyui-layout" data-options="fit:true">  
	                   <div data-options="region:'north',split:true" style="height:90px;padding:10px">  
	                        <div class="ftitle">机台计划信息</div>    
		                    <div class="fitem">  
					            <label>下达时间:</label>  
					            <input  type="text" name="xdrq"   style="width:100px;" readonly="readonly">
								<label>计划单号:</label>  
                                <input type="text" name="jhbh" style="width:100px;" readonly="readonly">
	                            <label>序号:</label>  
                                <input  type="text" name="row" style="width:40px;" readonly="readonly">	

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
					             <input  type="text" name="gxxhnew"   style="width:160px;" readonly="readonly" >
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
					             <input  type="text" name="jhrq"  style="width:160px;" readonly="readonly" >
					             <label>---->:</label>  
					             <input   class="easyui-datebox"  name="jhrqnew"  style="width:160px;" disabled="true">
							 </div>
					         <div class="fitem">  
					             <label>技术要求:</label>  

                                 <textarea id="gxjsyq" name="gxjsyq" cols="28"
	                                 rows="7" readonly="readonly"></textarea>
					             <label>---->:</label>  
	                             <textarea id="gxjsyqnew" name="gxjsyqnew" cols="28"
	                                 rows="7"></textarea>
							 </div>   
				            <div class="fitem">  
					             <label>批    号:</label>  
					             <input  type="text" name="gxph"   style="width:160px;" readonly="readonly">
					             <label>---->:</label>  
					             <input  type="text" name="gxphnew"   style="width:160px;" >
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
								          url:'${msUrl}/selectdata/state.json' ">
							 </div>  							 
                         </div>
                     </div>
                 </div>
             </form>
         </div>
</div>

  <script type="text/javascript" src="${msUrl}/js/commons/YDataGrid.js"></script>
  <script type="text/javascript" src="${msUrl}/js/ux/business/jtjhChange_pl.js"></script> 

 <script type="text/javascript">   
 	    //$('#state').combobox('disable');
	   // $("#state").combobox({disabled:true})
 function toBgList(id){
	    $("#viewjhbg-win").dialog('open');
	   	$(function() {  
        var $dg = $("#jhbg-list");  
        $dg.datagrid({ 
			title:'机台计划变更单',
            iconCls: 'icon-search',
			url : "dataListChangeForDdId.do?id="+id, 
			width:700,
			height : 335,  
			autoRowHeight:false,
			nowrap:true,
			striped: true,
			collapsible:true,
			remoteSort: false,
			//pagination:true,
		    // pageSize:10,
			//pageList: [10, 20, 30, 40, 50],
            method:  'post',
			rownumbers:true,
			singleSelect:true,	
			//ft:'#toolbar',
		    columns:[[
					//{field:'id',checkbox:true},
					{field:'bh',title:'变更单编号',width:80,sortable:true},	
					{field:'row',title:'行号',width:40},
					{field:'jhbh',title:'机台计划编号',width:100,sortable:true},	
					{field:'jhbhrow',title:'行号',width:40},

			    	{field:'field',title:'变更项目',width:80,sortable:true},
			    	{field:'oldContent',title:'变更前内容',width:180,
	                     formatter:function(value,row,index){
	                         if (row.field=='状态')
	                         {
							      if(value == 1){
								      return '正常';
							       } 
								   else if (value==2)
								   {
									   return '暂停';
								   }
								   else if (value==3)
								   {
									   return '作废';
								   }
							}
   	                        else{
								return value;
							}
   
					}},
					{field:'newContent',title:'变更后内容',width:160,
                        formatter:function(value,row,index){
	                         if (row.field=='状态')
	                         {
							      if(value == 1){
								      return '正常';
							       } 
								   else if (value==2)
								   {
									   return '暂停';
								   }
								   else if (value==3)
								   {
									   return '作废';
								   }
							}
	                        else{
								return value;
							}
   
   
					}},		            		            {field:'accept',title:'变更确认',width:60,align:'center',sortable:true,styler:function(value,row,index){
							if(value == 1){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
	
							if(value == 1){
								return "已确认";
							}
   
					}},									    
					{field:'createBy',title:'订单员',width:80,sortable:true},
					{field:'createTime',title:'变更时间',width:150,sortable:true},
	
				    {field:'acceptBy',title:'确认人',width:80,sortable:true},
					{field:'acceptTime',title:'确认时间',width:150,sortable:true}
				]]
 	    });
    });
   } 
   function closeBgList(){
	   $("#viewjhbg-win").dialog('close');
   } 
    function cancelquit(){ 
        $.messager.confirm('提示','你确认要放弃以上修改吗',function(r){  
	         if (r){  
	
                $('#edit-win').dialog('close');
		        $('#edit-win').resetForm();
	
           }
	   })
    }
	function savechange(){
		var effectRow = new Object();  
	    var id=$("#editForm input:input[name='id']").val(); 
	    effectRow["id"] =id;
	    var jhbh=$("#editForm input:input[name='jhbh']").val();  
	    effectRow["jhbh"] =jhbh;
	    var row=$("#editForm input:input[name='row']").val(); 
	    effectRow["row"] =row;
	    var sbmc=$("#editForm input:input[name='sbmc']").val();  
	    effectRow["sbmc"] = sbmc;
	    var sbmcnew=$("#editForm input:input[name='sbmcnew']").val();  
	    effectRow["sbmcnew"] = sbmcnew;
	    var iszl=$("#editForm input:input[name='iszl']").val();  
	    effectRow["iszl"] = iszl;	
	    var iszlnew=$("#editForm input:input[name='iszlnew']").val();  
	    effectRow["iszlnew"] = iszlnew;		
	    var gxxh=$("#editForm input:input[name='gxxh']").val();  
	    effectRow["gxxh"] = gxxh;
	    var gxxhnew=$("#editForm input:input[name='gxxhnew']").val();  
	    effectRow["gxxhnew"] = gxxhnew;
	    var gxgg=$("#editForm input:input[name='gxgg']").val();  
	    effectRow["gxgg"] = gxgg;
	    var gxggnew=$("#editForm input:input[name='gxggnew']").val();  
	    effectRow["gxggnew"] = gxggnew;
	    var gxdy=$("#editForm input:input[name='gxdy']").val();  
	    effectRow["gxdy"] = gxdy;
	    var gxdynew=$("#editForm input:input[name='gxdynew']").val();  
	    effectRow["gxdynew"] = gxdynew;
	    var gxgy=$("#editForm input:input[name='gxgy']").val();  
	    effectRow["gxgy"] = gxgy;
	    var gxgynew=$("#editForm input:input[name='gxgynew']").val();  
	    effectRow["gxgynew"] = gxgynew;
	    var gxdw=$("#editForm input:input[name='gxdw']").val();  
	    effectRow["gxdw"] = gxdw;
	    var gxdwnew=$("#editForm input:input[name='gxdwnew']").val();  
	    effectRow["gxdwnew"] = gxdwnew;
	    var gxlb=$("#editForm input:input[name='gxlb']").val();  
	    effectRow["gxlb"] = gxlb;
	    var gxlbnew=$("#editForm input:input[name='gxlbnew']").val();  
	    effectRow["gxlbnew"] = gxlbnew;
	    var gxjsyq=$("#editForm input:input[name='gxjsyq']").val();  
	    effectRow["gxjsyq"] = gxjsyq;
	    var gxjsyqnew=$("#editForm input:input[name='gxjsyqnew']").val();  
	    effectRow["gxjsyqnew"] = gxjsyqnew;
	    var gxph=$("#editForm input:input[name='gxph']").val();  
	    effectRow["gxph"] = gxph;
	    var gxphnew=$("#editForm input:input[name='gxphnew']").val();  
	    effectRow["gxphnew"] = gxphnew;
	    var jhsl_xs=$("#editForm input:input[name='jhsl_xs']").val();  
	    effectRow["jhsl_xs"] = jhsl_xs;
	    var jhsl_xsnew=$("#editForm input:input[name='jhsl_xsnew']").val();  
	    effectRow["jhsl_xsnew"] = jhsl_xsnew;
	    var jhsl_o=$("#editForm input:input[name='jhsl_o']").val();  
	    effectRow["jhsl_o"] = jhsl_o;
	    var jhsl_onew=$("#editForm input:input[name='jhsl_onew']").val();  
	    effectRow["jhsl_onew"] = jhsl_onew;
	    var jhrq=$("#editForm input:input[name='jhrq']").val();  
	    effectRow["jhrq"] = jhrq;
	    var jhrqnew=$("#editForm input:input[name='jhrqnew']").val();  
	    effectRow["jhrqnew"] = jhrqnew;
	    var state=$("#editForm input:input[name='state']").val();  
	    effectRow["state"] = state;
	    var statenew=$("#editForm input:input[name='statenew']").val();  
	    effectRow["statenew"] = statenew;

	    var gxjsyq=$("#gxjsyq").val();  
	    effectRow["gxjsyq"] = gxjsyq;
	    var gxjsyqnew=$("#gxjsyqnew").val();;  
	    effectRow["gxjsyqnew"] = gxjsyqnew;

        $.post("savePlBg.do", effectRow,function(rsp) {  
	
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
  function initdate(){
     $("#searchForm input:input[name='fromxdrq']").val("${requestScope.fromdate}");
     $("#searchForm input:input[name='toxdrq']").val("${requestScope.todate}");

    // $("#searchForm").find("#btn-search").click();
     //var param =$('#searchForm').serializeObject();
    // $('#data-list').datagrid('reload',param);
	 //$('#data-list').datagrid('load')
  }

  document.body.onload=initdate();
  //var param =$('#searchForm').serializeObject();
  //$('#data-list').datagrid('reload',param);

</script>  	
</body>
</html>

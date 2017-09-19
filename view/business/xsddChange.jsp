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
				<tr>
				<label class="ui-label">下达日期: </label><input name="fromxdrq" class="easyui-datebox" style="width:100px;">
				<label class="ui-label">至: </label><input name="toxdrq" class="easyui-datebox" style="width:100px;">	 	
                <label class="ui-label">计划号:</label> 
				</tr>
                <input name="jhbh" class="easyui-box ui-text" style="width:80px;">
      
                <label>业务员:</label>  
		            <input class="easyui-combobox"  name="ywy"  style="width:60px;"
				    data-options="
			        url:'../ywyManage/getYwyList.do',
			        method:'get',
					multiple:false,
			        valueField:'name',
			        textField:'name'">
               <label>订单员:</label>  
		            <input class="easyui-combobox"  name="createBy" style="width:60px;"   
				    data-options="
			        url:'getUniDdy.do',
					multiple:false,
			        valueField:'text',
			        textField:'text'">
  	 	       
                <label>产品型号:</label>  
		            <input class="easyui-combobox"  name="xh"  
				    data-options="
	                url:'getUniXh.do',
				    method:'get',
			        multiple:false,
			        valueField:'text',
			        textField:'text'">

                <label>产品规格:</label>  
		            <input  class="easyui-combobox"  name="gg"  
				    data-options="
	                url:'getUniGg.do',
			        method:'get',
			        multiple:false,
			        valueField:'text',
			        textField:'text'">
                <!--<label class="ui-label">交货日期: </label><input name="jhrq" class="easyui-datebox" style="width:100px;"
				    data-options="formatter:function(value,row,index) {
                        if (value != null) {
                           var date = new Date(value);
                           return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                           + date.getDate();
                        }
				 }">-->
            </p>
            <a id="btn-search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
        </form>  
     </div> 
     <!--  Search panel end -->
     <!-- DataList  -->
     <form id="listForm" method="post" >
         <table id="data-list"></table>
 	 </form>	
	  <!-- Edit Win&From -->
     <div id="edit-win-dlgbuttons" class="dialog-button" >
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" 
		   onclick="savechange();">保存</a>      
	    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" 
		   onclick="cancelquit();">放弃</a>
     </div>
     <div id="edit-win" class="easyui-dialog"   buttons="#edit-win-dlgbuttons"  
	     title="销售订单变更" 
	     data-options="closed:true,iconCls:'icon-edit',modal:true"  
		 style="width:660px;height:560px;">  

     	 <form id="editForm" class="ui-form" method="post"> 
     	      <!-- 隐藏文本框 -->
     	      <input class="hidden" name="id">
 			  <!--<input class="hidden" name="parentId" id='edit_parentId'> -->
    	      <div class="easyui-panel" border='false' style="height:450;">  
	              <div class="easyui-layout" data-options="fit:true">  
	                   <div data-options="region:'north',split:true" style="height:90px;padding:10px">  
	                        <div class="ftitle">销售订单信息</div>    
		                    <div class="fitem">  
					            <label>下达时间:</label>  
					            <input  type="text" name="xdrq"   style="width:100px;" readonly="readonly">
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
					                     multiple:false,
			                             valueField:'name',
			                             textField:'name'">
							 </div>
						     <div class="fitem">  
					             <label>批    号:</label>  
					             <input  type="text" name="ph"   style="width:160px;" readonly="readonly">
					             <label>---->:</label>  
					             <input  type="text" name="phnew"   style="width:160px;" >
							 </div>
						     <div class="fitem">  
					             <label>产品型号:</label>  
					             <input  type="text" name="xh"   style="width:160px;" readonly="readonly">
					             <label>---->:</label>  
					             <input  type="text" name="xhnew"   style="width:160px;" >
							 </div>
					         <div class="fitem">  
					             <label>产品规格:</label>  
					             <input  type="text" name="gg"   style="width:160px;" readonly="readonly">
					             <label>---->:</label>  
					             <input  type="text" name="ggnew"   style="width:160px;" >
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
								          url:'${msUrl}/selectdata/gy.json'" >
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
								          url:'${msUrl}/selectdata/dwnocheck.json'">

							 </div>    
					         <div class="fitem">  
					             <label>数    量:</label>  
					             <input  type="text" name="sl"   style="width:160px;" readonly="readonly" >
					             <label>---->:</label>  
					             <input  type="text" name="slnew"   style="width:160px;" data-options="editor:{type:'numberbox',options:{precision:1}}">
							 </div>
					         <div class="fitem">  
					             <label>交货日期:</label>  
					             <input  type="text" name="jhrq"  style="width:160px;" readonly="readonly" >
					             <label>---->:</label>  
					             <input   class="easyui-datebox"  name="jhrqnew"  style="width:160px;" >
							 </div>
					         <div class="fitem">  
					             <label>技术要求:</label>  
					             <!--<input  type="textarea" name="jsyq"   style="width:100%;" readonly="readonly">-->
                                 <textarea id="jsyq" name="jsyq" cols="28"
	                                 rows="7" readonly="readonly"></textarea>
					             <label>---->:</label>  
	                             <textarea id="jsyqnew" name="jsyqnew" cols="28"
	                                 rows="7"></textarea>
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
	  <!-- ddbg View Win&From -->
	 <div id="viewddbg-win-dlgbuttons" class="dialog-button" >
       
	     <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo'" 
		   onclick="closeBgList();">返回</a>
     </div>
     <div id="viewddbg-win" class="easyui-dialog"  buttons="#viewddbg-win-dlgbuttons"  
	     title="销售订单变更单" 
	     data-options="closed:true,iconCls:'icon-search',modal:true,
		 onResize:function(){
		 $(this).dialog('center');}"  
		 style="width:750px;height:450px;">  
  	     <form id="viewddbgForm" class="ui-form" method="post"> 
     	      <!-- 隐藏文本框 -->
     	      <input class="hidden" name="id">
 			  <div class="easyui-panel" border='false' style="height:355;">  
	               <div class="easyui-layout" data-options="fit:true">  
                       <div data-options="region:'center'">  
                           <table id="ddbg-list"> </table>
                       </div>
                   </div>
		      </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="${msUrl}/js/commons/YDataGrid.js"></script>
<script type="text/javascript" src="${msUrl}/js/ux/business/xsddChange.js"></script> 
<script type="text/javascript">   
    $('#data-list').datagrid({   
       rowStyler:function(index,row){   
           if (row.state==2){   
               return  'background-color:pink;color:blue;font-weight:bold;'; //'color:blue;';
					   
            } 
	        else if (row.state==3)
		    {
			   return 'background-color:yellow;color:red;font-weight:bold;'; //'color:red;'
				   
		    }
        }   
   });

 	    //$('#state').combobox('disable');
	   // $("#state").combobox({disabled:true})
    function cancelquit(){ 
       $.messager.confirm('提示','你确认要放弃以上修改吗',function(r){  
	       if (r){  
	
               $('#edit-win').dialog('close');
		       $('#edit-win').resetForm();
		       //$("#editForm input:input[name='xhnew']").val('');
		       // $("#editForm input:input[name='ggnew']").val('');
   		       //$("#editForm input:input[name='dynew']").val('');
		       // $("#editForm input:input[name='gynew']").val('');
		       //$("#editForm input:input[name='dwnew']").val('');
		       // $("#editForm input:input[name='slnew']").val('');
   		       // $("#editForm input:input[name='jhrqnew']").val('');
		       //$("#editForm input:input[name='statenew']").val('');
		       //$("#jsyq").val();
			   //document.getElementById('jsyqnew').Value = '';
			  //document.getElementById('dynew').Value='';
		  
           }
	   })
    }
  function toBgList(id){
	    $("#viewddbg-win").dialog('open');
	   	$(function() {  
        var $dg = $("#ddbg-list");  
        $dg.datagrid({ 
			title:'销售订单变更单',
            iconCls: 'icon-search',
			url : "dataListChangeForDdid.do?id="+id, 
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
					{field:'jhbh',title:'销售订单编号',width:80,sortable:true},	
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
   
					}},
		            {field:'accept',title:'变更确认',width:60,align:'center',sortable:true,styler:function(value,row,index){
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
	   $("#viewddbg-win").dialog('close');
   } 
	function savechange(){
		var effectRow = new Object();  
	    var id=$("#editForm input:input[name='id']").val(); 
	    effectRow["id"] =id;
	    var jhbh=$("#editForm input:input[name='jhbh']").val();  
	    effectRow["jhbh"] =jhbh;
	    var row=$("#editForm input:input[name='row']").val(); 
	    effectRow["row"] =row;

	    var ywy=$("#editForm input:input[name='ywy']").val();  
	    effectRow["ywy"] = ywy;
	    var ywynew=$("#editForm input:input[name='ywynew']").val();  
	    effectRow["ywynew"] = ywynew;	
	    var ph=$("#editForm input:input[name='ph']").val();  
	    effectRow["ph"] = ph;
	    var phnew=$("#editForm input:input[name='phnew']").val();  
	    effectRow["phnew"] = phnew;	
	    var xh=$("#editForm input:input[name='xh']").val();  
	    effectRow["xh"] = xh;
	    var xhnew=$("#editForm input:input[name='xhnew']").val();  
	    effectRow["xhnew"] = xhnew;
	    var gg=$("#editForm input:input[name='gg']").val();  
	    effectRow["gg"] = gg;
	    var ggnew=$("#editForm input:input[name='ggnew']").val();  
	    effectRow["ggnew"] = ggnew;
	    var dy=$("#editForm input:input[name='dy']").val();  
	    effectRow["dy"] = dy;
	    var dynew=$("#editForm input:input[name='dynew']").val();  
	    effectRow["dynew"] = dynew;
	    var gy=$("#editForm input:input[name='gy']").val();  
	    effectRow["gy"] = gy;
	    var gynew=$("#editForm input:input[name='gynew']").val();  
	    effectRow["gynew"] = gynew;
	    var dw=$("#editForm input:input[name='dw']").val();  
	    effectRow["dw"] = dw;
	    var dwnew=$("#editForm input:input[name='dwnew']").val();  
	    effectRow["dwnew"] = dwnew;
	    var sl=$("#editForm input:input[name='sl']").val();  
	    effectRow["sl"] = sl;
	    var slnew=$("#editForm input:input[name='slnew']").val();  
	    effectRow["slnew"] = slnew;
	    var jhrq=$("#editForm input:input[name='jhrq']").val();  
	    effectRow["jhrq"] = jhrq;
	    var jhrqnew=$("#editForm input:input[name='jhrqnew']").val();  
	    effectRow["jhrqnew"] = jhrqnew;
	    var state=$("#editForm input:input[name='state']").val();  
	    effectRow["state"] = state;
	    var statenew=$("#editForm input:input[name='statenew']").val();  
	    effectRow["statenew"] = statenew;

	    var jsyq=$("#jsyq").val();  
	    effectRow["jsyq"] = jsyq;
	    var jsyqnew=$("#jsyqnew").val();;  
	    effectRow["jsyqnew"] = jsyqnew;

        $.post("${msUrl}/xsddManage/saveDdbg.do", effectRow,function(rsp) {  
	
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
   //处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外  
    function banBackSpace(e)
	{   var ev = e || window.event;
       //获取event对象   
       var obj = ev.target || ev.srcElement;
       //获取事件源   
       var t = obj.type || obj.getAttribute('type');//获取事件源类型   
       //获取作为判断条件的事件类型   
       var vReadOnly = obj.getAttribute('readonly');   
       var vEnabled = obj.getAttribute('enabled');   
       //处理null值情况   
       vReadOnly = (vReadOnly == null) ? false : vReadOnly; 
       vEnabled = (vEnabled == null) ? true : vEnabled;  
       //当敲Backspace键时，事件源类型为密码或单行、多行文本的，  
       //并且readonly属性为true或enabled属性为false的，则退格键失效   
       var flag1=(ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea") && (vReadOnly==true || vEnabled!=true))?true:false;   
       //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效   
       var flag2=(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea")   ?true:false;  
      //判断   
      if(flag2)
	  {   return false;   }  
      if(flag1)
      {   return false;   }   
    }   
    //禁止后退键 作用于Firefox、Opera   
    document.onkeypress=banBackSpace;   
    //禁止后退键 作用于IE、Chrome   
    document.onkeydown=banBackSpace;   
    var param =$('#searchForm').serializeObject();
    $('#data-list').datagrid('reload',param);


</script>  	
</body>
</html>

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
				<label class="ui-label">发布日期: </label><input name="fromfbrq" class="easyui-datebox" style="width:100px;">
				<label class="ui-label">至: </label><input name="tofbrq" class="easyui-datebox" style="width:100px;">	 	
                <label class="ui-label">公告编号:</label> 
                <input name="ggbh" class="easyui-box ui-text" style="width:100px;">
      
                <label>采购分类:</label>  
		            <input class="easyui-combobox"  name="wlfl"  style="width:100px;"
				    data-options="
			        url:'../cgflManage/cgflListJson.do',
			        method:'get',
					multiple:false,
			        valueField:'name',
			        textField:'name'">
	 	       
              <!--<label>物料名称:</label>  
		            <input class="easyui-combobox"  name="wlmc"   style="width:100px;"
				    data-options="
	                url:'../zbwzManage/zbwzListJson.do',
				    method:'get',
			        multiple:false,
			        valueField:'name',
			        textField:'name'">-->
                <label>物料名称:</label>  
		         <input   name="wlmc"  class="easyui-box ui-text" style="width:100px;" >

                <label>产品规格:</label>  
		         <input   name="wlgg"  class="easyui-box ui-text" style="width:100px;" >
				<label class="ui-label">有效期: </label><input name="fromyxrq" class="easyui-datetimebox" style="width:140px;">
				<label class="ui-label">至: </label><input name="toyxrq" class="easyui-datetimebox" style="width:140px;">	 

            </p>
            <a id="btn-search" class="easyui-linkbutton" iconCls="icon-search" onclick="refreshgrid();">查询</a>
        </form>  
     </div> 
     <!--  Search panel end -->
     <!-- DataList  -->
     <form id="listForm" method="post" >
         <table id="data-list"></table>
 	 </form>	
	 <form id="listtbxxForm" method="post" >
         <table id="data-tbxxlist" ></table>  
     </form>
	 <!-- Edit Win&From -->

	 <div id="edit-win-dlgbuttons" class="dialog-button" >
       
	     <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo'" 
		   onclick=" cancelquit();">返回</a>
     </div>
     <div id="edit-win" class="easyui-dialog"  buttons="#edit-win-dlgbuttons" 
	     title="投标信息填写" 
	     data-options="closed:true,iconCls:'icon-edit',modal:true"  
		 style="width:1200px;height:560px;">  
     	 <form id="editForm" class="ui-form" method="post"> 
     	      <!-- 隐藏文本框 -->
     	      <input class="hidden" name="id">
 			  <input class="hidden" name="parentId" id='edit_parentId'>
    	      <div class="easyui-panel" border='false' style="height:450;">  
	              <div class="easyui-layout" data-options="fit:true">  
	                  <div data-options="region:'north',split:true" style="height:90px;padding:10px">  
	                     <div class="ftitle">信息</div>    
		                 <div class="fitem">  
					        <label>公告编号:</label>  
					        <input class="easyui-datebox"  name="ggbh"   style="width:100%;" readonly="readonly">

                            <label>采购分类:</label>  
		                        <input class="easyui-combobox"  name="wlfl"  style="width:100px;"
				                   data-options="
			                           url:'../cgflManage/cgflListJson.do',
			                           method:'get',
					                   multiple:false,
									   disabled:true,
			                           valueField:'name',
			                           textField:'name'">
					        <label>发布日期:</label>  
				            <input class="easyui-datebox" name="fbrq"  style="width:100%;" readonly="readonly">
					        <label>要求交货期:</label>  
				            <input class="easyui-datebox" name="jhrq_xq"  style="width:100%;" readonly="readonly">
												   
		                </div> 
	                </div>
	          
                    <div data-options="region:'center'">  
                         <table id="dg-zbconfirm" >
                            <thead>
   	                        <input class="hidden" name="id" >
                            <input class="hidden" name="ggbh" >
   	                        <th field="row" width="30" >序号</th>
   	                        <th data-options="field:'gysId', width:150,
							    editor:{
							      type:'combobox',
							      options:{
								     method:'post',
								     valueField:'id',
								     textField:'name',
									 disabled:true,
								     url:'../gysdaManage/gysListJson.do'
							       }
								}
							">供应商名称</th>
						    <!--<th data-options="field:'wlmc',width:100,
						       editor:{
							      type:'combobox',
							      options:{
								     valueField:'name',
								     textField:'name',
									 disabled:true,
								     url:'../zbwzManage/zbwzListJson.do'
							       }
								}
						    ">物料名称</th> -->	
                             <th field="wlmc" width="120" >物料名称</th>							
	                         <th field="wlgg" width="120" >物料规格</th>					
 	                         <th field="wldw" width="50" >单位</th>	 	
	                         <th field="wlsl" width="80" align="right" 							      
							 >采购数量</th>
	                         <th  data-options="field:'jhrq_xq', width:100">要求交货期</th>
	                         <th data-options="field:'jsyq', width:200,nowrap: false">技术要求</th>
 						     <th data-options="field:'zb',width:60,
						       editor:{
							      type:'combobox',
							      options:{
								     method:'get',
								     valueField:'id',
								     textField:'text',
								     url:'${msUrl}/selectdata/zb.json'
							       }
								}
						    ">中标</th>   
	                         <th field="tbsl" width="80" align="right" 
							      data-options="editor:{type:'numberbox',options:{precision:2}}"
							 >投标数量</th>
	                         <th field="dj" width="80" align="right" 
							      data-options="editor:{type:'numberbox',options:{precision:2}}"
							 >含税单价</th>
	                         <th data-options="field:'jhrq', width:100,editor:'datebox'
			  
							 ">要求交货期</th>
	                         <th data-options="field:'tbsm', width:300,editor:'text',nowrap: false">投标说明信息</th>

	                         </thead> 
                        </table>	
                    </div>
               </div>
		   </div>
       </form>
   </div>
</div>
  <script type="text/javascript" src="${msUrl}/js/commons/YDataGrid.js"></script>
  <script type="text/javascript" src="${msUrl}/js/ux/cgpt/zbManage.js"></script> 

 <script type="text/javascript"> 
 
    function refreshgrid(){ 
       var param = $("#searchForm").serializeObject();
	  $('#data-list').datagrid('reload',param);
    }


 function toTbList(ggbh){

	 $.post("dataListForGgbh.do?ggbh="+ggbh,function(rsp) {  
	
                    if(rsp.success){  
       
					    $('#data-tbxxlist').datagrid('loadData',rsp.tbxx);
                        //$.messager.alert("提示", rsp.msg);   
                    }  
					else
					{
	                     $.messager.alert("提示",rsp.msg);  	
					}

               }, "JSON");

  } 
  function cancelquit(){ 
       //$.messager.confirm('提示','你确认要放弃以上修改吗',function(r){  
	   //    if (r){  
		       //endEdit(); 
               $('#edit-win').dialog('close');
		       $('#edit-win').resetForm();

	           $("#editForm input:input[name='fbrq']").val("");
		       $("#editForm input:input[name='ggbh']").val("");
   		       $("#editForm input:input[name='yxrq']").val("");
   		       $("#editForm input:input[name='wlfl']").val("");
		       $("#editForm input:input[name='jhrq_xq']").val("");

		       $("#dg-zbconfirm").datagrid('reload');
		       $("#dg-zbconfirm").datagrid('loadData', { total: 0, rows: [] }); 

			   //$('#data-list').datagrid('load');
		       var param = $("#searchForm").serializeObject();
		       $('#data-list').datagrid('reload',param);
           //}
	   //})
    }  
  
   $(function() {  

            var $dgmx = $("#data-tbxxlist");  
            $dgmx.datagrid({ 
			     url : "dataListForGgbh.do?ggbh="+'0', 
			     height:185,
			     nowrap:true,
			     striped: true,
			     collapsible:true,
			     remoteSort: false,
		         method:  'post',
		         rownumbers:true,
			     singleSelect:true,
		         columns:[[
					{field:'zb',title:'中标',width:40,align:'center',sortable:true,styler:function(value,row,index){
				         if(value != 0){
						      return 'color:red;';  
						    }
					    },
				     	formatter:function(value,row,index){
							
						    if(value == 1){
							    return "中标";
						    }
                         
				     }},
                    {field:'gysStr',title:'供应商名称',width:120,sortable:true},
					{field:'row',title:'序号',width:40},
			    	{field:'wlmc',title:'物料名称',width:80,sortable:true},
			    	{field:'wlgg',title:'物料规格',width:120,sortable:true},
			    	{field:'wldw',title:'单位',width:40},
			    	{field:'wlsl',title:'采购数量',width:80,align:'right' },
			        {field:'jhrq_xq',title:'需求交货期',width:70,sortable:true,formatter:function     formatterdate(value,row,index) {
                        if (value != null) {
                        var date = new Date(value);
                        return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                         + date.getDate();
                         }}
                     },
			    	{field:'jsyq',title:'技术要求',width:160},
			    	{field:'tbsl',title:'投标数量',width:80,align:'right' ,styler:function(value,row,index){
							if(row.kb != 1){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
							
							if(row.kb !=1){
								return "******";
							}
                           else {
								return value;
							}
					}},
			    	{field:'dj',title:'含税单价',width:80,align:'right' ,styler:function(value,row,index){
							if(row.kb != 1){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
							
							if(row.kb !=1){
								return "******";
							}
                           else {
								return value;
							}
					}},
			    	{field:'je',title:'价税合计',width:80,align:'right',styler:function(value,row,index){
							if(row.kb != 1){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
							
							if(row.kb !=1){
								return "******";
							}
                           else {
								return value;
							}
					}},
		            {field:'jhrq',title:'交货日期',width:70,sortable:true,styler:function(value,row,index){
							if(row.kb != 1){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
							
							if(row.kb !=1){
								return "******";
							}
                           else {
								return value;
							}
					}},
			    	{field:'tbsm',title:'投标说明信息',width:260,styler:function(value,row,index){
							if(row.kb != 1){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
							
							if(row.kb !=1){
								return "******";
							}
                           else {
								return value;
							}
					}},
				     {field:'createBy',title:'投标人',width:80,sortable:true},
				     {field:'createTime',title:'日期',width:150,sortable:true}

				]]
            });
		//alert json;
        var $dg = $("#dg-zbconfirm");  
		//$dg.datagrid('reload');
        $dg.datagrid({ 
            //url : "xsddListByJhbh.do?jhbh=201706280002",  
            // width : 700,  
            height :355,  
			nowrap: false,
			singleSelect:true,
			//pagination:true,
		    rownumbers:true,
			//pageSize:16,
			//pageList: [10, 16, 30, 40, 50],
			onClickRow: onClickRow,
			ft:'#toolbar',
  
  
            toolbar : [   
                {  
                    text : "接受编辑",  
                    iconCls : "icon-ok",  
                    handler :endEdit  
                }, {  
                    text : "保存提交",  
                    iconCls : "icon-save",  
                    handler : function() {  
 					    submit();
                     }  
             } ]  
        });  

        function endEdit(){  
            var rows = $dg.datagrid('getRows');  
            for ( var i = 0; i < rows.length; i++) {  
                $dg.datagrid('endEdit', i);  
            }  
        } 

        function onClickRow(){

	        var row = $dg.datagrid('getSelected');  
                if (row) {  
                    var rowIndex = $dg.datagrid('getRowIndex', row);  
                    $dg.datagrid('beginEdit', rowIndex);  
                }  
		}

        function submit(){    
		    endEdit();  
			if ($dg.datagrid('getChanges').length) {  
                var inserted = $dg.datagrid('getChanges', "inserted");  
                var deleted = $dg.datagrid('getChanges', "deleted");  
                var updated = $dg.datagrid('getChanges', "updated");  
                          
                var effectRow = new Object();  
                if (inserted.length) {  
                    effectRow["inserted"] = JSON.stringify(inserted);  
                }  
                if (deleted.length) {  
                    effectRow["deleted"] = JSON.stringify(deleted);  
                }  
                if (updated.length) {  
                    effectRow["updated"] = JSON.stringify(updated);  
                }  
 			    var fbrq=$("#editForm input:input[name='fbrq']").val();  
	            effectRow["fbrq"] =fbrq;
				var ggbh=$("#editForm input:input[name='ggbh']").val();  
	            effectRow["ggbh"] =ggbh;
				var wlfl=$("#editForm input:input[name='wlfl']").val();  
	            effectRow["wlfl"] =wlfl;
				var yxrq=$("#editForm input:input[name='yxrq']").val(); 
	            effectRow["yxrq"] =yxrq;
				var jhrq_xq=$("#editForm input:input[name='jhrq_xq']").val();  
	            effectRow["jhrq_xq"] = jhrq_xq;

                $.post("save.do", effectRow,function(rsp) {  
	
                    if(rsp.success){  
   
					    var param = $("#searchForm").serializeObject();
			            $('#data-list').datagrid('reload',param);
                        $.messager.alert("提示", rsp.msg);   
                    }  
					else
					{
	                     $.messager.alert("提示",rsp.msg);  	
					}

               }, "JSON");

			   $dg.datagrid('acceptChanges');  
			   $('#edit-win').dialog('close');
			 

			   $("#editForm input:input[name='fbrq']").val("");
			   $("#editForm input:input[name='ggbh']").val("");
			   $("#editForm input:input[name='wlfl']").val("");
   			   $("#editForm input:input[name='yxrq']").val("");
			   $("#editForm input:input[name='jhrq_xq']").val("");
  
			   //$("#dg-zbggappend").datagrid('reload');
			   $dg.datagrid('loadData', { total: 0, rows: [] }); 

               var param = $("#searchForm").serializeObject();
			   $('#data-list').datagrid('reload',param);
            }  
        }
	
   });

    var cmenu = null;
    function createColumnMenu(){
		cmenu = $('<div/>').appendTo('body');
		cmenu.menu({
			onClick: function(item){
				if (item.iconCls == 'icon-ok'){
					$('#data-list').datagrid('hideColumn', item.name);
					cmenu.menu('setIcon', {
						target: item.target,
						iconCls: 'icon-empty'
					});
				 } else {
					 $('#data-list').datagrid('showColumn', item.name);
					 cmenu.menu('setIcon', {
						target: item.target,
						iconCls: 'icon-ok'
				   });
				}
			}
		});
		var fields = $('#data-list').datagrid('getColumnFields');
		for(var i=0; i<fields.length; i++){
			var field = fields[i];
			var col = $('#data-list').datagrid('getColumnOption', field);
			cmenu.menu('appendItem', {
				text: col.title,
				name: field,
				iconCls: 'icon-ok'
		    });
		}
	}


  function initdate(){
     $("#searchForm input:input[name='fromfbrq']").val("${requestScope.fromdate}");
     $("#searchForm input:input[name='tofbrq']").val("${requestScope.todate}");
  }
  document.body.onload=initdate();
  var param =$('#searchForm').serializeObject();
  $('#data-list').datagrid('reload',param);

</script>  	
</body>
</html>

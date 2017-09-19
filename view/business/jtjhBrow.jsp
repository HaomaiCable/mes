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
                <label class="ui-label" >计划号:</label> 

                <input name="jhbh" class="easyui-box ui-text" style="width:100px;" >
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
  	 	       
                <label>型号:</label>  
		            <input class="easyui-combobox"  name="gxxh"   style="width:100px;"
				    data-options="
	                url:'getUniXh.do',
				    method:'get',
					
			        multiple:false,
			        valueField:'text',
			        textField:'text'">

                <label>规格:</label>  
		            <input  class="easyui-combobox"  name="gxgg" style="width:100px;" 
				    data-options="
	                url:'getUniGg.do',
			        method:'get',
				    
			        multiple:false,
			        valueField:'text',
			        textField:'text'">
				<label class="ui-label">完工日期: </label><input name="fromjhrq" class="easyui-datebox" style="width:100px;">
				<label class="ui-label">至: </label><input name="tojhrq" class="easyui-datebox" style="width:100px;">
                <label>完工状态:</label>  
		            <input class="easyui-combobox"  name="wgflag" style="width:60px;"    
                    data-options="
			        method:'get',
	                valueField:'text',
		            textField:'text',
				    url:'${msUrl}/selectdata/wgflag.json' ">		
				<label class="ui-label">创建时间: </label><input name="fromcreateTime" class="easyui-datetimebox" style="width:140px;" >
				<label class="ui-label">至: </label><input name="tocreateTime" class="easyui-datetimebox" style="width:140px;" >	 				

            </p>
            <a id="btn-search" class="easyui-linkbutton" iconCls="icon-search" onclick="refreshgrid();">查询</a>
        </form>  
     </div> 
     <!--  Search panel end -->
     <!-- DataList  -->
     <form id="listForm" method="post" >
         <table id="data-list"> </table>
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
	<!-- Wghb View Win&From -->
	 <div id="viewwghb-win-dlgbuttons" class="dialog-button" >
       
	     <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo'" 
		   onclick="closeWgList();">返回</a>
     </div>
     <div id="viewwghb-win" class="easyui-dialog"  buttons="#viewwghb-win-dlgbuttons" 
	     title="机台完工汇报单" 
	     data-options="closed:true,iconCls:'icon-search',modal:true"  
		 style="width:750px;height:450px;">  
  	     <form id="viewwghbForm" class="ui-form" method="post"> 
     	      <!-- 隐藏文本框 -->
     	      <input class="hidden" name="id">
 			  <input class="hidden" name="parentId" id='edit_parentId'>
    	      <div class="easyui-panel" border='false' style="height:355;">  
	               <div class="easyui-layout" data-options="fit:true">  
                       <div data-options="region:'center'">  
                           <table id="wghb-list"> </table>
                       </div>
                   </div>
		      </div>
        </form>
    </div>
</div>

<script type="text/javascript"> 
	$(function() {  
        var $dg = $("#data-list");  

        $dg.datagrid({ 
			title:'机台计划列表',
            iconCls: 'icon-search',
			url : "dataListLinkNoPage.do", 
			height : 500,  
			//autoRowHeight: true,
			nowrap:true,
			
			striped: true,
			collapsible:true,
			remoteSort: false,
			pagination:true,
		    pageSize:500,
			pageList: [500, 1000, 1650, 2000, 3000],
            method:  'post',
			rownumbers:true,
			singleSelect:true,	
			//ft:'#toolbar',
		    onHeaderContextMenu: function(e, field){
			        e.preventDefault();
			        if (!cmenu){
			    	    createColumnMenu();
			        }
			        cmenu.menu('show', {
				        left:e.pageX,
				        top:-e.pageY
			        });
		    },
            frozenColumns:[[
					{field:'id',checkbox:true},
				    {field:'cqts',title:'超期天数',width:60,align:'center',sortable:true,styler:function(value,row,index){
				        if(value >0 ){
						  return 'color:red;';  
					    }
					},
					formatter:function(value,row,index){
						
						if (row.state==2)
						{
							return "暂停";
						}
						else if (row.state==3)
						{
							return "作废";
						}
						else{
						    if(value == -9999){
							    return "未到期";
						    }
                            else if(value == 0){
							    return "按期";
						    }
						    else{
							    return "("+value+")天";
						    }
						}
					}},
					{field:'xdrq',title:'下达日期',width:70,sortable:true,formatter:function formatterdate(value,row,index) {
                        if (value != null) { 
						    var date = new Date(value);
                            return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                             + date.getDate();
                         }}
                     },			
					{field:'jhbh',title:'计划编号',width:90,sortable:true},
					{field:'row',title:'序号',width:30},
			    	{field:'gd',title:'工段',width:60,sortable:true},
			    	{field:'sbmc',title:'机台',width:60,sortable:true},
			    	{field:'iszl',title:'主零',width:30,sortable:true},
			    	{field:'gxxh',title:'型号',width:80,sortable:true},
			    	{field:'gxgg',title:'规格',width:80,sortable:true},
			    	{field:'gxdy',title:'电压',width:50,sortable:true},
		    	    {field:'gxlb',title:'类别',width:40,sortable:true},
			    	{field:'gxgy',title:'工艺',width:30},
			    	{field:'gxdw',title:'单位',width:30},
			    	{field:'jhsl_xs',title:'产品数量',width:60,align:'right' },
			    	{field:'jhsl_o',title:'机台数量',width:60,align:'right' }
		    ]],
			columns:[[

				    {field:'qbWg',title:'全部完工',width:70,align:'center',styler:function(value,row,index){
							if(value != 0){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
							
							if(value == 1){
								return "已全部完工";
							}
                         
					 }},
					 {field:'sumWgsl',title:'合计完工数量',width:80,align:'right' },
					 {field:'wwgsl',title:'未完工数量',width:80,align:'right' },
			         {field:'sumWgslds',title:'完工段数明细',width:120 ,formatter:function(value,row,index){
						if (row.subCountWg!=0)
						{
						
						     var html ="<a href='#' onclick='toWgList("+row.id+")'>"+row.sumWgslds+"</a>";
						     return html;
						}
					  }},	
					  {field:'jhrq',title:'计划日期',width:80,sortable:true,formatter:function formatterdate(value,row,index) {
                          if (value != null) {
                          var date = new Date(value);
                            return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                             + date.getDate();
                          }}
                     },
                     {field:'maxWgrq',title:'最后完工日期',width:80,formatter:function formatterdate(value,row,index) {
                         if (value != null) {
                             var date = new Date(value);
                             return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                              + date.getDate();
                          }}
                     },
			    	 {field:'gxjsyq',title:'技术要求',width:120},
			    	 {field:'gxph',title:'批号',width:120},
					 {field:'state',title:'状态',width:40,align:'center',sortable:true,styler:function(value,row,index){
							if(value != 1){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
							
							if(value == 2){
								return "暂停";
							}
                           if(value == 3){
								return "作废";
							}
					}},
                    {field:'bgDetails',title:'变更明细',width:80,align:'center',formatter:function(value,row,index){
						if (row.subCountBg!=0)
						{
						
						    var html ="<a href='#' onclick='toBgList("+row.id+")'>变更明细("+row.subCountBg+")</a>";
						    return html;
						}
					}},

				     {field:'createBy',title:'计划员',width:80,sortable:true},
				     {field:'createTime',title:'日期',width:150,sortable:true}

			]],
            toolbar : [ {  
                text : "机台打印导出Excel",  
                iconCls : "icon-undo",  
                handler : function() {  
 					toExcel();
					//downExcel('( 系统管理员)_销售订单明细表_(2017-08-04 17_26_05).xls');
                }   
                } ,'-',{  
                text : "汇报计划导出Excel",  
                iconCls : "icon-undo",  
                handler : function() {  
 					toExcelHb();
					//downExcel('( 系统管理员)_销售订单明细表_(2017-08-04 17_26_05).xls');
                }   
            }]  

	 	});
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

    });
   function toWgList(id){
      $("#viewwghb-win").dialog('open');
	  $(function(){  
          var $dg = $("#wghb-list");  
          $dg.datagrid({ 
		      //title:'销售订单变更单',
			  //iconCls: 'icon-search',
			  url: "dataListWghbForJhId.do?id="+id, 
			  width:700,
		  	  height : 335,  
			  autoRowHeight:false,
			  nowrap:true,
			  striped: true,
		   	  collapsible:true,
			  remoteSort: false,
			  //pagination:true,
			  //pageSize:500,
			  //pageList: [500, 1000, 1500, 2000, 2500],
			  method:  'post',
			  rownumbers:true,
			  singleSelect:true,	
			  //ft:'#toolbar',
		  	  columns:[[
					//{field:'id',checkbox:true},
					{field:'wgrq',title:'完工日期',width:100,sortable:true,
						formatter:function formatterdate(value,row,index) {
						    if (value != null) { 
							    var date = new Date(value);
							    return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
							     + date.getDate();
						    }
						}
					},
					{field:'bc',title:'班次',width:40,align:'center',sortable:true,
						styler:function(value,row,index){
						     if(value != 1){
							    return 'color:red;';  
							  }
						},
						formatter:function(value,row,index){
							
							if(value == 2){
								return "夜班";
							}
						 
					    }
					},
					{field:'wgsl',title:'完工数量',width:80,align:'right' },
					{field:'wgsm',title:'文字性备注说明',width:150},	
					{field:'wg',title:'全部完工',width:70,align:'center',sortable:true,
						styler:function(value,row,index){
						    if(value != 0){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
							
							if(value == 1){
								return "已全部完工";
							}
					    }
					},
					{field:'lrBy',title:'录入',width:80,sortable:true},
					{field:'lrTime',title:'时间',width:150,sortable:true}
			  ]]
 	        });
        });

   } 
   function closeWgList(){
	   $("#viewwghb-win").dialog('close');
   } 

   function toBgList(id){
	    $("#viewjhbg-win").dialog('open');
	   	$(function() {  
            var $dg = $("#jhbg-list");  
            $dg.datagrid({ 
		  	    //title:'销售订单变更单',
                //iconCls: 'icon-search',
			    url : "../jtjhChangeManage/dataListChangeForDdId.do?id="+id, 
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

					{field:'jhbh',title:'机台计划编号',width:80,sortable:true},	
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
	   $("#viewjhbg-win").dialog('close');
   } 
   function refreshgrid(){ 
        var param = $("#searchForm").serializeObject();
	    $('#data-list').datagrid('reload',param);
    }


   function downExcel(fileName){
     window.location.href ="../downLoadManage/downLoad.do?fileName="+fileName; 
   }


	function toExcel(){
         var rows = $('#data-list').datagrid('getRows');
         var effectRow = new Object();  
         effectRow["selected"] =JSON.stringify(rows);  
  
         $.post("exportExcel.do",effectRow ,function(rsp) {  			
           if(rsp.success){  
			   downExcel(rsp.fileName);
            }  
		    else{
	           $.messager.alert("提示",rsp.msg);  	
		    }
		 });
	}
	function toExcelHb(){
         var rows = $('#data-list').datagrid('getRows');
         var effectRow = new Object();  
         effectRow["selected"] =JSON.stringify(rows);  
  
         $.post("exportExcelGd.do",effectRow ,function(rsp) {  			
           if(rsp.success){  
			   downExcel(rsp.fileName);
            }  
		    else{
	           $.messager.alert("提示",rsp.msg);  	
		    }
		});
	}

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
				 } 
				 else {
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
     $("#searchForm input:input[name='fromxdrq']").val("${requestScope.fromdate}");
     $("#searchForm input:input[name='toxdrq']").val("${requestScope.todate}");
	 //var param =$('#searchForm').serializeObject();
     //$('#data-list').datagrid('reload',param);
	 //$('#fromcreateTime').datetimebox({showSeconds:false});
     //$('#tocreateTime').datetimebox({showSeconds:false});
	 //datetimebox({showSeconds:$(this).is(':checked')});
  }
  document.body.onload=initdate();		
  //处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外  
  function banBackSpace(e)
  {   
	   var ev = e || window.event;
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
	   {  
		   return false;   
	   }  
       if(flag1)
       {   
		   return false;  
	    }   
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

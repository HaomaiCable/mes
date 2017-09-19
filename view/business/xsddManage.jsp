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
         <table id="data-list" 
		     data-options="pageSize:16,
 			               height:500" >
		
		 </table>
 	 </form>	
	 <!-- Edit Win&From -->
	 <div id="edit-win-dlgbuttons" class="dialog-button" >
	     <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" 
		   onclick=" cancelquit();">放弃修改</a>
     </div>
     <div id="edit-win" class="easyui-dialog"  buttons="#edit-win-dlgbuttons" 
	     title="销售订单下达" 
	     data-options="closed:true,iconCls:'icon-edit',modal:true"  
		 style="width:1000px;height:560px;">  
     	 <form id="editForm" class="ui-form" method="post"> 
     	      <!-- 隐藏文本框 -->
     	      <input class="hidden" name="id">
 			  <input class="hidden" name="parentId" id='edit_parentId'>
    	      <div class="easyui-panel" border='false' style="height:450;">  
	              <div class="easyui-layout" data-options="fit:true">  
	                  <div data-options="region:'north',split:true" style="height:90px;padding:10px">  
	                       <div class="ftitle">信息</div>    
		                   <div class="fitem">  
					          <label>下达时间:</label>  
					          <input class="easyui-datebox"  name="xdrq"   style="width:100%;">
		                      <label>计划单号:</label>  
                              <input class="easyui-validatebox" type="text" name="jhbh" readonly="readonly" style="width:100px;" >
		                      <label>业务员:</label>  
		                      <input class="easyui-combobox"  name="ywy"  
							    data-options="
				    	            url:'../ywyManage/getYwyList.do',
					                method:'get',
					                valueField:'name',
					                textField:'name'">
					          <label>客户要求交货期:</label>  
				              <input class="easyui-datebox" name="jhrq_kh"  style="width:100%;" >
		                   </div> 
	                  </div>
                      <div data-options="region:'center'">  
                         <table id="dg-xsddappend" >
                             <thead>
                             <tr>
   	                         <input class="hidden" name="id">
  	                         <th field="jhbh" width="80" >计划编号</th>
	                         <th field="row" width="30" >行号</th>
  						     <th data-options="field:'ywy',width:80,
						       editor:{
							      type:'combobox',
							      options:{
								     valueField:'name',
					                 textField:'name',
									 method:'get',
								     url:'../ywyManage/getYwyList.do'
							       }
								}
						     ">业务员</th>   
   	                         <th field="xh" width="150" editor="validatebox">产品型号</th>
	                         <th field="gg" width="120" editor="validatebox">产品规格</th>					
  						     <th data-options="field:'dy',width:80,
						       editor:{
							      type:'combobox',
							      options:{
								     valueField:'id',
								     textField:'text',
								     url:'${msUrl}/selectdata/dy.json'
							       }
								}
						     ">电压等级</th>   
  						     <th data-options="field:'gy',width:50,
						       editor:{
							      type:'combobox',
							      options:{
								     valueField:'id',
								     textField:'text',
								     url:'${msUrl}/selectdata/gy.json'
	                              }
								 }
						     ">工艺</th>   
							 <th data-options="field:'dw',width:50,
						        editor:{
							      type:'combobox',
							      options:{
								     valueField:'id',
								     textField:'text',
								     url:'${msUrl}/selectdata/dw.json',
								     required:true
							       }
								 }
						     ">单位</th>
  	                         <th field="ph" width="120" editor="text" >批号</th>
	                         <th field="sl" width="80" align="right" 
							      data-options="editor:{type:'numberbox',options:{precision:1}}"
							 >计划数量</th>
	                         <th data-options="field:'jhrq_kh', width:100,editor:'datebox'
								  
							 ">客户要求交货期</th>
	                         <th data-options="field:'jsyq', width:500,editor:'text',nowrap: false">技术要求</th>
	                         </tr>
	                         </thead> 
                         </table>	
                      </div>
                 </div>
			  </div>
		  </form>
      </div>

     <!-- import Excel  Form -->
     <div id="import-excel-win"  class="easyui-dialog" buttons="#chooseFilebtn" title="选择导入的Excel文件" data-options="closed:true,iconCls:'icon-search',modal:true" style="width:438px;height:190px;"> 
       <form id="importFileForm"  method="post"  action="proUpload.do"  class="ui-form" enctype="multipart/form-data"   target="hideframe">
           <table width="400" border="0" cellspacing="1" cellpadding="10">
               <tr>
	           <td height="25">文件标题：</td>
	           <td><input id="title" name="title" type="text" /></td>
               </tr>
               <tr>
	           <td height="25">浏览文件：</td>
	           <td><input id="file" name="file" type="file"  accept=".xls,.xlsx"/></td>
               </tr>
               <tr>
	           <td colspan="2" align="center">
	           <input type="submit" value="上传" ) />
	           <input type="reset" value="重设" />
	           </td>
               </tr> 
           </table>
       </form>
     </div>
     <iframe name="hideframe" style="display:none"></iframe>
     <!-- Edit 销售订单编号 Form -->
     <div id="edit-bh-win" class="easyui-dialog" buttons="#editBhbtn" title="修改销售订单编号" data-options="closed:true,iconCls:'icon-save',modal:true" style="width:400px;height:200px;">
     	 <form id="bhForm" class="ui-form" method="post">  
     		 <input class="hidden" name="id">
     		 <div class="ui-edit">
	     	      <div class="ftitle">订单编号信息</div>    
	              <div class="fitem">  
	                  <label>原订单编号:</label>  
	                  <input class="easyui-validatebox" type="text" readonly="readonly" name="jhbh"  data-options="required:true">
	              </div>  
	              <div class="fitem">  
	                  <label>新订单编号:</label>  
	                  <input id="newJhbh" name="newJhbh" type="text" class="easyui-validatebox" data-options="required:true" />
	              </div> 
	              <!--<div class="fitem">  
	               <label>Re PassWord:</label>  
	              <input id="rpwd" name="rpwd" type="password" class="easyui-validatebox"   required="required" validType="equals['#newPwd']" /> 
	             </div> -->
	          </div>
     	 </form>
     	 <div id="editBhbtn" class="dialog-button">  
              <a href="javascript:void(0)" class="easyui-linkbutton" id="btn-bh-submit">提交</a>  
              <a href="javascript:void(0)" class="easyui-linkbutton" id="btn-bh-close">放弃</a>  
         </div>
  	 </div>    
         <!--<div id="chooseFilebtn" class="dialog-button">  
         <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"  id="btn-importfile-submit">导入</a>  
         <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"  id="btn-importfile-close">放弃</a>  
          </div> -->
</div>
<script type="text/javascript" src="${msUrl}/js/commons/YDataGrid.js"></script>
<script type="text/javascript" src="${msUrl}/js/ux/business/xsddManage.js"></script>
<!-- <script type="text/javascript" src="${msUrl}/js/commons/json.js"></script> -->
<!--<script type="text/javascript" src="${msUrl}/js/ux/business/xsddMxManage.js"></script>  -->
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

   function initdate(){
     $("#searchForm input:input[name='fromxdrq']").val("${requestScope.fromdate}");
     $("#searchForm input:input[name='toxdrq']").val("${requestScope.todate}");
     // $("#searchForm").find("#btn-search").click();
     //var param =$('#searchForm').serializeObject();
     // $('#data-list').datagrid('reload',param);
	 //$('#data-list').datagrid('load')
  }

  function callback(msg)
        {
    	    alert(msg); 
	        // 隐藏文件上传的对话框
	        $('#import-excel-win').dialog('close');
	        // 清空title、file两个表单域。
	        $('#title,#file').val('');
	        $('#hideframe').attr('src' , '');
	        var param = $("#searchForm").serializeObject();
	        $('#data-list').datagrid('reload',param);
        } 
  function downExcel(fileName){
	       	    alert(fileName); 
     //window.location.href ="../exportExcel/downLoad.do?fileName="+fileName; 
   }
   function cancelquit(){ 
       $.messager.confirm('提示','你确认要放弃以上修改吗',function(r){  
	       if (r){  
		       //endEdit(); 
               $('#edit-win').dialog('close');
		       $('#edit-win').resetForm();

		       $("#editForm input:input[name='xdrq']").val("");
		       $("#editForm input:input[name='jhbh']").val("");
   		       $("#editForm input:input[name='ywy']").val("");
		       $("#editForm input:input[name='jhrq_kh']").val("");
		       $("#dg-xsddappend").datagrid('reload');
		       $("#dg-xsddappend").datagrid('loadData', { total: 0, rows: [] }); 

			   //$('#data-list').datagrid('load');
		       var param = $("#searchForm").serializeObject();
		       $('#data-list').datagrid('reload',param);
           }
	   })
    }
 

  
   $(function() {  

		//alert json;
        var $dg = $("#dg-xsddappend");  
		//$dg.datagrid('reload');
        $dg.datagrid({ 
            //url : "xsddListByJhbh.do?jhbh=201706280002",  
            // width : 700,  
            //height :365,  
			nowrap: false,
			singleSelect:true,
			//pagination:true,
		    rownumbers:true,
			//pageSize:16,
			//pageList: [10, 16, 30, 40, 50],
			onClickRow: onClickRow,
			ft:'#toolbar',
  
  
            toolbar : [ {  
                text : "添加行",  
                iconCls : "icon-add",  
                handler : function() {  
                    $dg.datagrid('appendRow', {});  
                    var rows = $dg.datagrid('getRows');  
                    $dg.datagrid('beginEdit', rows.length - 1);  
                }  
            },  {  
                text : "编辑行",  
                iconCls : "icon-edit",  
                handler : function() {  

                    var row = $dg.datagrid('getSelected');  
                    if (row) {  
                        var rowIndex = $dg.datagrid('getRowIndex', row);  
                        $dg.datagrid('beginEdit', rowIndex);  
                    }  
                }  
            }, {  
                text : "删除行",  
                iconCls : "icon-remove",  
                handler : function() {  
				    $.messager.confirm('提示','你确认要删除该记录吗',function(r){  
	                   if (r){  
                           var row = $dg.datagrid('getSelected');  
                           if (row) {  
                               var rowIndex = $dg.datagrid('getRowIndex', row);  
                               $dg.datagrid('deleteRow', rowIndex);  		  
                           }
                       }  
                    })  
				}
				
            },'-', {  
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
				//var ywy = $("input[name='ywy' type='text']").val(); 

	 			var xdrq=$("#editForm input:input[name='xdrq']").val();  //计划下达时间,第4个
	            effectRow["xdrq"] =xdrq;
				var jhbh=$("#editForm input:input[name='jhbh']").val();  //计划编号,第5个
	            effectRow["jhbh"] =jhbh;
				var ywy=$("#editForm input:input[name='ywy']").val();  //业务员,第6个
	            effectRow["ywy"] =ywy;
				var jhrq_kh=$("#editForm input:input[name='jhrq_kh']").val();  //业务员,第6个
	            effectRow["jhrq_kh"] = jhrq_kh;

                $.post("${msUrl}/xsddManage/save.do", effectRow,function(rsp) {  
	
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
			 
			   //$('#edit-win').resetForm();
			   /** $(':input','#edit-win')  
                  .not(':button, :submit, :reset, :hidden')  
                  .val('')  
                  .removeAttr('checked')  
                  .removeAttr('selected');  
				*/
			   $("#editForm input:input[name='xdrq']").val("");
			   $("#editForm input:input[name='jhbh']").val("");
   			   $("#editForm input:input[name='ywy']").val("");
			   $("#editForm input:input[name='jhrq_kh']").val("");
  
			   //$("#dg-xsddappend").datagrid('reload');
			   $dg.datagrid('loadData', { total: 0, rows: [] }); 

               var param = $("#searchForm").serializeObject();
			   $('#data-list').datagrid('reload',param);
		
            }  
        }
	
   });

   document.body.onload=initdate();

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

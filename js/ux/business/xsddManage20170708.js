$package('YiYa.xsddManage');

YiYa.xsddManage = function(){
	var _box = null;
	var _this = {
    // 上传文件的回调函数
    
		impInfo:function(){ //未使用
    	    //YiYa.alert(_this.getPath(document.getElementById('uploadfile')));
    		//YiYa.alert("1===" + $('#uploadfile').val());
			var filename=$('#uploadfile').val();
    		//YiYa.alert("2===" +filename);
    		if($('#uploadfile').val() == "")
			{
				 YiYa.alert("请选择Excel文件!");
				return;
			}
 /**
			$('#loading').ajaxStart(function(){
				$(this).show();
			})//开始上传文件时显示一个图片
			.ajaxComplete(function(){
				$(this).hide();
			});
			*/
			/**
			$.ajaxFileUpload({
				url:'importExcel.do?filePath='+filename,//用于文件上传的服务器端请求地址
				secureuri:true,//是否启用安全提交，一般设置为false
				fileElementId:'uploadfile',//文件上传控件的id
				dataType:'text',//服务器返回的数据类型
				success: function (data,status){
					if(data == 'success'){
						$('#import-excel-win').window('close');
						YiYa.alert("导入成功!");
					}else if(data == 'error'){
						YiYa.alert("文件上传过程中出错!请重试!");
					}
				},
				error: function (data,status,e){
					YiYa.alert("服务中断或连接超时导致通信失败！");
					YiYa.alert(e);
					$('#import-excel-win').window('close');
				}
			}
			)*/
			//var jhrq_kh=$("#editForm input:input[name='jhrq_kh']").val();  //业务员,第6个
			//var effect = new Object(); 
	        //effect["filePath"] = filename;

            //    $.post("proUpload.do", $("importFileFormf"),unction(rsp) {  
	        //         $.messager.alert("提示", rsp.status);  
            //       if(rsp.status){  
            //            $.messager.alert("提示", "导入成功！");  
            //
            //        }  
			//		else
			//		{
			//		    $.messager.alert("提示", "导入成功2");  //提交失败！
			//		}

            //   }, "JSON")
      
			//return false;
    	//},

        $.ajax({
                cache: true,
                type: "POST",
                url:aproUpload.do,
                data:$('#importFileFormf').serialize(),// 你的formid
                async: false,
                error: function(request) {
                    //alert("Connection error");
					YiYa.alert('警告','导入失败，请重试','warning')
                },
                success: function(data) {
                   YiYa.alert('提示',"导入成功！");
                }
            });
         },
    	
    	getPath:function(obj){  //未使用
    		if(obj){
    			if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
            	    obj.select();
            	    return document.selection.createRange().text;
    		    }else if (window.navigator.userAgent.indexOf("Firefox") >= 1) {
    			     if (obj.files) {
                	    return obj.files.item(0).getAsDataURL();
            	    }
            	    return obj.value;
    		    }
    		    return obj.value;
    	    }
        },

		//导入文件  未使用
        importFileClick:function ()
        {
            //获取上传文件控件内容
            var file = document.getElementById('fileImport').files[0];
            //判断控件中是否存在文件内容，如果不存在，弹出提示信息，阻止进一步操作
            if (file == null) { YiYa.alert('错误，请选择文件'); return; }
            //获取文件名称
            var fileName = file.name;
            //获取文件类型名称
            var file_typename = fileName.substring(fileName.lastIndexOf('.'), fileName.length);
            //这里限定上传文件文件类型必须为.xlsx，如果文件类型不符，提示错误信息
            if (file_typename == '.xlsx' || file_typename == '.xls'  )
            {
                //计算文件大小
                var fileSize = 0;
                //如果文件大小大于1024字节X1024字节，则显示文件大小单位为MB，否则为KB
                if (file.size > 1024 * 1024) {
　　　　　    　　　　　fileSize = Math.round(file.size * 100 / (1024 * 1024)) / 100;

　    　　　　　　　　if (fileSize > 10) {
                        YiYa.alert('错误，文件超过10MB，禁止上传！'); return;
                    }
　    　　　　　　　　fileSize = fileSize.toString() + 'MB';
                }
                else {
                    fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';
                }
                //将文件名和文件大小显示在前端label文本中
                document.getElementById('fileName').innerHTML = "<span style='color:Blue'>文件名: " + file.name + ',大小: ' + fileSize + "</span>";
                //获取form数据
                var formData = new FormData($("#importFileForm")[0]);
                //调用apicontroller后台action方法，将form数据传递给后台处理。contentType必须设置为false,否则chrome和firefox不兼容
                $.ajax({
                    url: "importExcel.do",
                    type: 'POST',
                    data: formData,
                    async: false,
                    cache: false,
                    contentType: false,
                    processData: false,
                    success: function (returnInfo) {
                        //上传成功后将控件内容清空，并显示上传成功信息
                        document.getElementById('fileImport').value = null;
                        document.getElementById('uploadInfo').innerHTML = "<span style='color:Red'>" + returnInfo + "</span>";
                    },
                    error: function (returnInfo) {
                        //上传失败时显示上传失败信息
                        document.getElementById('uploadInfo').innerHTML = "<span style='color:Red'>" + returnInfo + "</span>";
                    }
                });
            }
           else {
                YiYa.alert("文件类型错误");
                //将错误信息显示在前端label文本中
                document.getElementById('fileName').innerHTML = "<span style='color:Red'>错误提示:上传文件应该是.xls(x)后缀而不应该是" + file_typename + ",请重新选择文件</span>"
            }
        },

		chooseFileWin:function(){
			return $("#import-excel-win");
		},
		initForm:function(){
			
			_this.chooseFileWin().find("#btn-importfile-submit").click(function(){
				_this.impInfo();
				document.getElementById('uploadfile').value = null;
                //document.getElementById('fileName').innerHTML = "";
               // document.getElementById('uploadInfo').innerHTML = "";
		     
				 _this.chooseFileWin().dialog('close');
				 //$('#data-list').datagrid('load');	
				 var param = $("#searchForm").serializeObject();
		         $('#data-list').datagrid('reload',param);
			});
			_this.chooseFileWin().find("#btn-importfile-close").click(function(){	
			    document.getElementById('uploadfile').value = null;
                //document.getElementById('fileName').innerHTML = "";
               // document.getElementById('uploadInfo').innerHTML = "";
		     	_this.chooseFileWin().dialog('close');
				
			});
		},
		config:{
			action:{
  				save:'', //新增&修改 保存Action  
  				getId:'',//编辑获取的Action
  				delele:''//删除数据的Action
  			},
 	
 			dataGrid:{
  				title:'销售订单列表',
	   			url:'dataList.do',
				height : 500,  
			    nowrap: false,
  			    pageSize:15,
			    pageList: [10, 15, 30, 40, 50],
	   			columns:[[
					{field:'id',checkbox:true},
					{field:'xdrq',title:'下达日期',width:80,sortable:true,formatter:function formatterdate(value,row,index) {
                        if (value != null) { 
						    var date = new Date(value);
                            return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                             + date.getDate();
                         }}
                     },
					{field:'jhbh',title:'计划编号',width:80,sortable:true},
					{field:'row',title:'行号',width:40},
					{field:'ywy',title:'业务员',width:60,sortable:true},
			    	{field:'xh',title:'产品型号',width:150,sortable:true},
			    	{field:'gg',title:'产品规格',width:120,sortable:true},
			    	{field:'dy',title:'电压等级',width:60,sortable:true},
			    	{field:'gy',title:'工艺',width:40},
			    	{field:'dw',title:'单位',width:40},
			    	{field:'ph',title:'批号',width:110},
			    	{field:'sl',title:'计划数量',width:60,align:'right' },
			        {field:'jhrq_kh',title:'需求交货期',width:70,sortable:true,formatter:function formatterdate(value,row,index) {
                        if (value != null) {
                        var date = new Date(value);
                        return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                         + date.getDate();
                         }}
                     },
			        {field:'jhrq',title:'计划交货期',width:70,sortable:true,formatter:function formatterdate(value,row,index) {
                        if (value != null) {
                        var date = new Date(value);
                        return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
                         + date.getDate();
                         }}
                     },
			    	{field:'jsyq',title:'技术要求',width:260},
					{field:'state',title:'状态',width:40,align:'center',sortable:true,styler:function(value,row,index){
							if(value != 0){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
							
							if(value == 1){
								return "暂停";
							}
                           if(value == 2){
								return "作废";
							}
						}},
				    {field:'xdjt',title:'下达机台',width:60,align:'center',sortable:true,styler:function(value,row,index){
							if(value == 1){
							  return 'color:red;';  
							}
						},
						formatter:function(value,row,index){
	
							if(value == 1){
								return "下达机台";
							}
   
						}},
				    {field:'createBy',title:'订单员',width:80,sortable:true},

				]],
				toolbar:[
					{id:'btnadd',text:'增加',btnType:'add'},
					{id:'btnedit',text:'修改',btnType:'edit'},
					{id:'btndelete',text:'删除',btnType:'remove'},
			        {
						id:'btnfromExcel',
						text:'从Excel导入',
						disabled: false,
						iconCls:'icon-redo',
						handler:function(){
							_this.chooseFileWin().window('open'); 
						}
					}
				]
			}
		},
		init:function(){
		    _this.initForm();
			_box = new YDataGrid(_this.config); 
			_box.init();
		
		}
	}
	return _this;
}();

$(function(){
	YiYa.xsddManage.init();
});		
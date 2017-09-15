$package('YiYa.jhqConfirm');

YiYa.jhqConfirm = function(){
	var _box = null;
	var _this = {
  		config:{
			action:{
 				save:'saveJhq.do'
			},
   	
 			dataGrid:{
  				title:'销售订单列表',
	   			url:'dataList.do',
				height : 500,  
			    nowrap: false,
				pagination: true,
  			    pageSize:100,
			    pageList: [100, 200, 300, 400, 500],
        		singleSelect:true,
			    pagination:true,
		        rownumbers:true
			}
      
		},
		init:function(){
			_box = new YDataGrid(_this.config); 
			_box.init();
		
		}
	}
	return _this;
}();

$(function(){
	YiYa.jhqConfirm.init();
});		
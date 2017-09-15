var map = null;
function BDMAP(){
	var data1=[
			{"id":"1","name":"北京","lng":116.384,"lat":39.925,"pic":"http://img1.qunarzz.com/tuan/team/2012/1030/13515851290000.jpg_165x110x100_8172c1d8.jpg"},
			{"id":"2","name":"上海","lng":121.483,"lat":31.229,"pic":"http://hiphotos.baidu.com/baidu/pic/item/d058ccbf6c81800af1617f40b13533fa838b47fc.jpg"},
			{"id":"3","name":"杭州市","lng":120.152449,"lat":30.2673,"pic":"http://hiphotos.baidu.com/baidu/pic/item/d058ccbf6c81800af1617f40b13533fa838b47fc.jpg"},
			{"id":"4","name":"新乡市","lng":113.975,"lat":35.050,"pic":"http://hiphotos.baidu.com/baidu/pic/item/d058ccbf6c81800af1617f40b13533fa838b47fc.jpg"},
			{"id":"5","name":"长沙市","lng":113.018,"lat":28.128,"pic":"http://hiphotos.baidu.com/baidu/pic/item/d058ccbf6c81800af1617f40b13533fa838b47fc.jpg"},
			{"id":"6","name":"深圳市","lng":113.979464,"lat":22.540099,"pic":"http://hiphotos.baidu.com/baidu/pic/item/d058ccbf6c81800af1617f40b13533fa838b47fc.jpg"}
		];
	var data2=[
			{"id": "1", "name":"阿拉善盟","lng":104.850498,"lat":39.801568,"pic":"1.jpg"},
			{"id": "2", "name":"阿拉善盟","lng":100.876681,"lat":40.254163,"pic":"2.jpg"},
			{"id": "3", "name":"赤峰市","lng":118.685265,"lat":44.454626,"pic":"3.jpg"},
			{"id": "4", "name":"甘孜藏族自治州","lng":99.625665,"lat":31.086319,"pic":"4.jpg"},
			{"id": "5", "name":"海西蒙古族藏族自治州","lng":97.859525,"lat":37.376066,"pic":"5.jpg"},
			{"id": "6", "name":"林芝地区","lng":93.370584,"lat":30.259366,"pic":"6.jpg"},
			{"id": "7", "name":"普洱市","lng":101.391806,"lat":23.839887,"pic":"3.jpg"}
		];
	var data3=[
			{"id": "1", "name":"阿拉善盟","lng":104.850498,"lat":39.801568,"pic":"1.jpg"},
			{"id": "2", "name":"阿拉善盟","lng":100.876681,"lat":40.254163,"pic":"2.jpg"},
			{"id": "3", "name":"赤峰市","lng":118.685265,"lat":44.454626,"pic":"3.jpg"},
			{"id": "4", "name":"甘孜藏族自治州","lng":99.625665,"lat":31.086319,"pic":"4.jpg"},
			{"id": "5", "name":"海西蒙古族藏族自治州","lng":97.859525,"lat":37.376066,"pic":"5.jpg"},
			{"id": "6", "name":"林芝地区","lng":93.370584,"lat":30.259366,"pic":"6.jpg"},
			{"id": "7", "name":"普洱市","lng":101.391806,"lat":23.839887,"pic":"3.jpg"},
			{"id": 1, "name":"定西市","lng":104.114606,"lat":34.990021,"pic":"1.jpg"},
			{"id": 1, "name":"甘孜藏族自治州","lng":101.171038,"lat":31.086319,"pic":"1.jpg"},
			{"id": 1, "name":"楚雄彝族自治州","lng":101.244627,"lat":24.413989,"pic":"1.jpg"},
			{"id": 1, "name":"巴音郭楞蒙古自治州","lng":88.219341,"lat":40.366838,"pic":"1.jpg"},
			{"id": 1, "name":"那曲地区","lng":86.747557,"lat":33.338431,"pic":"1.jpg"},
			{"id": 1, "name":"阿拉善盟","lng":102.86359,"lat":41.538469,"pic":"1.jpg"},
			{"id": 1, "name":"普洱市","lng":101.02386,"lat":23.534902,"pic":"1.jpg"},
			{"id": 1, "name":"遵义市","lng":107.867654,"lat":27.867304,"pic":"1.jpg"},
			{"id": 1, "name":"南充市","lng":106.837406,"lat":31.339322,"pic":"1.jpg"},
			{"id": 1, "name":"西安市","lng":109.339438,"lat":34.015235,"pic":"1.jpg"},
			{"id": 1, "name":"驻马店市","lng":113.239665,"lat":32.842846,"pic":"1.jpg"},
			{"id": 1, "name":"潍坊市","lng":118.979622,"lat":36.370963,"pic":"1.jpg"},
			{"id": 1, "name":"巴彦淖尔市","lng":108.456368,"lat":40.871531,"pic":"1.jpg"},
			{"id": 1, "name":"鄂尔多斯市","lng":108.2356,"lat":39.460137,"pic":"1.jpg"}
		];
	
	
	var _this ={
		initMap:function(){
			map = new BMap.Map("container");          // 创建地图实例
			var point = new BMap.Point(116.404, 39.915);  // 创建点坐标
			map.centerAndZoom(point, 5);                 // 初始化地图，设置中心点坐标和地图级别
			map.enableScrollWheelZoom();
			map.addEventListener('click',_this.click);
			map.addEventListener('zoomend',_this.zoomend);
		},
		click:function(data){ //点击获取 城市的名称，Point
			new BMap.Geocoder().getLocation(data.point,function(c){
				var name =c.addressComponents.city;
				var html="{\"id\": 1, \"name\":\""+name+"\",\"lng\":"+data.point.lng+",\"lat\":"+data.point.lat+",\"pic\":\"1.jpg\"},";
				$('#info').append(html);
			});
		},
		zoomend:function(type, target){ //缩放结束事件
			var zoom = map.getZoom();
			$('#zoom').html("ZOOM:"+zoom);
			switch(zoom){
				case  5: 
					_this.load(data1);
					//_this.loadUrl('data.json');
					break;
				case  6: 
					_this.load(data2);
					//_this.loadUrl('data1.json');
					break;
				case  7: 
					_this.load(data3);
					_//this.loadUrl('data2.json');
					break;
			}
		},
		addOverlay:function(overlay){
			 map.addOverlay(overlay);
		},
		clearOverlays:function(){
			//alert('清除覆盖物');
			map.clearOverlays();
		},
		createOverlay:function(item){
			var myPoint = new BMap.Point(item.lng, item.lat);
			var overlay = new ComplexCustomOverlay(myPoint,item.name,item.pic);
			_this.addOverlay(overlay);
			
		},
		loadUrl:function(url){
			var _url= url || 'data.json';
			$.getJSON(_url,function(data){
					load(data);
			});
		},
		load:function(data){
			_this.clearOverlays();
			for(var i= 0;i< data.length;i++){
				var item = data[i];
				_this.createOverlay(item);
			}	
		},
		init:function(){
			this.initMap();
			this.load(data1);
			$("#btn_clear").click(_this.clearOverlays);
		}
	};
	return _this;
}

$(function (){
	BDMAP().init();
});

function ComplexCustomOverlay(point, text, pic){
  this._point = point;
  this._text = text;
  this._pic = pic;
}

ComplexCustomOverlay.prototype = new BMap.Overlay();
ComplexCustomOverlay.prototype.initialize = function(map){
	  var div = $('<div>');
      this._map = map;
	  this._div = div[0];
	  div.css('zIndex',BMap.Overlay.getZIndex(this._point.lat));
	  div.css({'position':'absolute',
			   'background':'#EE5D5B',
			   'border':'2px solid #BC3B3A',
			   'padding':'2px',
			   'color':'white',
			   'whiteSpace':'nowrap',
			   'font':'10px',
			   'width':'50px',
			   'height':'50px',
			   'overflow':'hidden',
			   'lineHeight':'18px'});
	  //div.html(this._text);
		 
	  var img = $('<img>');
	  img.css({'width':'50px','height':'50px'});
	  img.attr('src',this._pic);
	  img.appendTo(div);

	  map.getPanes().labelPane.appendChild(div[0]);
	  return div[0];
}

ComplexCustomOverlay.prototype.draw = function(){
  var map = this._map;
  var pixel = map.pointToOverlayPixel(this._point);
  this._div.style.left = pixel.x - 50 + "px";
  this._div.style.top  = pixel.y - 50 + "px";
}


/**
$(function ready(){
	for(var i= 0;i< data.length;i++){
		var item = data[i];
		//alert(item.name);
		//var marker = destMarker(item);
		//addMarker(marker);
		 destMarker(item);
	}
});

*/

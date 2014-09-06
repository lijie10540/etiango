<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
body, html,#allmap,#panorama {width: 100%;height: 100%;overflow: hidden;margin:1px;}
</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=FOnz9xPGxpo4aD2Bl17ABzTW"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
<link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css" />
<title>一天一天|相约七夕</title>
</head>
<body>
<div id="allmap" style="display:block"></div>
<div id="panorama" style="display:none"></div>
<div id="panelWrap" style="width:0px;position:absolute;top:0px;right:0px;height:100%;overflow:auto;-webkit-transition: all 0.5s ease-in-out;transition: all 0.5s ease-in-out;">
        <div style="width:20px;height:200px;margin:-100px 0 0 -10px;color:#999;position:absolute;opacity:0.5;top:50%;left:50%;">此处用于展示结果面板</div>
        <div id="panel" style="position:absolute;"></div>
</div>
</body>
<script type="text/javascript">
var mePoint = new BMap.Point(114.427575,30.461718);
var youPoint = new BMap.Point(121.298755,31.186668);
var map = new BMap.Map('allmap',{mapType: BMAP_HYBRID_MAP});
map.centerAndZoom(youPoint, 3);
map.addControl(new BMap.NavigationControl());               // 添加平移缩放控件
map.addControl(new BMap.ScaleControl());                    // 添加比例尺控件
map.addControl(new BMap.OverviewMapControl());              //添加缩略地图控件
map.enableScrollWheelZoom();                            //启用滚轮放大缩小
map.disable3DBuilding();


var panorama = new BMap.Panorama('panorama'); 
panorama.setPosition(youPoint); //根据经纬度坐标展示全景图
panorama.setPov({heading: 100, pitch:6});
  
var content = '<div style="margin:0;line-height:20px;padding:2px;">' +
'<img src="http://pcsv0.map.bdimg.com//pr/?qt=poiprv&uid=f44902cb2493b302a11de91b&width=323&height=101&quality=80&fovx=250" alt="" style="float:left;zoom:1;overflow:hidden;width:100px;height:100px;margin-right:3px;"/>' +
'地址：青浦区蟠龙路200号   <br/>电话：(021)69768000<br/>' +
'</div>';

//创建检索信息窗口对象
var searchInfoWindow = null;
searchInfoWindow = new BMapLib.SearchInfoWindow(map, content, {
title  : "上海国家会计学院",      //标题
width  : 250,             //宽度
height : 90,              //高度
panel  : "panel",         //检索结果面板
enableAutoPan : true,     //自动平移
searchTypes   :[
BMAPLIB_TAB_SEARCH,   //周边检索
BMAPLIB_TAB_TO_HERE,  //到这里去
BMAPLIB_TAB_FROM_HERE //从这里出发
]
});
var marker = new BMap.Marker(youPoint); //创建marker对象
marker.enableDragging(); //marker可拖拽
marker.addEventListener("click", function(e){
searchInfoWindow.open(marker);
});
map.addOverlay(marker); //在地图中添加marker
setTimeout(function(){map.setZoom(6)},3000);
setTimeout(function(){map.setZoom(8)},5000);
setTimeout(function(){map.setZoom(10)},7000);
setTimeout(function(){map.setZoom(14)},9000);
setTimeout(function(){map.setZoom(19);
                      searchInfoWindow.open(marker);
                      setTimeout(function(){
                    	  if(map.getZoom()==19){
                        	  document.getElementById("allmap").style.display="none"; 
                              document.getElementById("panorama").style.display="block"; 
                             } 
                          },5000); 
                      },12000);

//searchInfoWindow.open(marker); //在marker上打开检索信息串口
</script>
</html>

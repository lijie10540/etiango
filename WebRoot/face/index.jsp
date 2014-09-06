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
<base href="<%=basePath%>">

<title>一天一天·首页</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<link rel="shortcut icon" href="../yitianyitian.ico" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="../css/yitian.css">
<link rel="stylesheet" type="text/css" href="../css/gallery.css">
<link rel="stylesheet" type="text/css" href="../css/styles.css">
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>

<body>
	<div class="borderWrap">
		<section class="homeContent">
		<%--<video src="video/myVideo.mp4" id="video" width="1350" controls="controls"></video>--%>
		<div style="width: 100%;height:110px;" align="center"><a href="#" class=""><img alt="yitianyitian" src="img/list-view.png" style="margin-top: 50px" class="listview"></a></div>
		<div class="bannerbox">
            <div id="focus">
		        <ul>
		            <li><a href="http://www.lanrentuku.com/" target="_blank">
		                <img src="img/bn1.jpg" alt="" class="imgView"/></a></li>
		            <li><a href="http://www.lanrentuku.com/" target="_blank">
		                <img src="img/bn2.jpg" alt="" class="imgView"/></a></li>
		            <li><a href="http://www.lanrentuku.com/" target="_blank">
		                <img src="img/bn3.jpg" alt="" class="imgView"/></a></li>
		            <li><a href="http://www.lanrentuku.com/" target="_blank">
		                <img src="img/bn4.jpg" alt="" class="imgView"/></a></li>
		        </ul>
            </div>
        </div>
		<div style="width: 100%;height:120px;display:none;" align="center"><a href="#" class=""><img alt="yitianyitian" src="img/plus.png" style="margin-top: 10px" class="plus"></a></div>   
		</section>

		<footer id="pageFooter">
			<div class="footerColumn">
				<h2>Contact</h2>
				<p>
					<a href="#">475470758@qq.com</a>
				</p>
				<p>
					<a href="#">13545052440</a>
				</p>
			</div>
			<div class="footerColumn">
				<h2>Let's be social</h2>
				<p>
					<a href="#">Weixin</a>
				</p>
				<p>
					<a href="#">QQ</a>
				</p>
			</div>
			<div class="footerColumn">
				<h2>Suggest</h2>
				<p>
					<a href="#">Thank you</a>
				</p>
				<p>
					<a href="#">Make me</a>
				</p>
			</div>
		</footer>
		<footer id="copyright">
			<p class="copyright_en">Copyright © 2014 JayLee All Rights Reserved</p>
			<p class="copyright_cn"><span>黎杰·版权所有·鄂ICP备14011367号</span><img width="13px" height="15px" src="img/copy_rignt_24.png"/></p>
		</footer>
	</div>
</body>
<script type="text/javascript">
var _bannerBox=$(".bannerbox");//幻灯片盒子
var _loginForm=$("#loginForm");//登陆表单对象
var _maskDiv=$(".mask");//遮罩层
	 $(".listview").hover(function(){
   	  var el=$(this);
         el.attr("src","<%=request.getContextPath()%>/img/list-view-hover.png");
         },
         function(){
       	  var el=$(this);
             el.attr("src","<%=request.getContextPath()%>/img/list-view.png");
             });
      $(".listview").click(function(){
    	  var el=$(this);
    	  el.unbind("hover");
          el.attr("src","<%=request.getContextPath()%>/img/list-view-active.png");
          showLoginForm();
          });
      $(".plus").hover(function(){
	   	  var el=$(this);
	         el.attr("src","<%=request.getContextPath()%>/img/plus-hover.png");
	         },
	         function(){
	       	  var el=$(this);
	             el.attr("src","<%=request.getContextPath()%>/img/plus.png");
	             });
      $(".plus").click(function(){
    	  showLoginForm();
          });
      //遮罩点击退出
      $(".mask").click(function(){
    	  _loginForm.hide(); 
    	  _maskDiv.fadeOut();
    	  _bannerBox.fadeIn();
          });
      //弹出登陆界面
      function showLoginForm(){
    	  _bannerBox.hide();
    	  _maskDiv.fadeIn();
    	  var windowHeight=$(window).height();
          var windowWidth=$(window).width();
          var objHeight=_loginForm.height();
          var objWidth=_loginForm.width();
          var positon_top=(windowHeight-objHeight)/2;
          var position_left=(windowWidth-objWidth)/2;
          _loginForm.css({top:positon_top,left:position_left}); 
          _loginForm.fadeIn("slow");
      }
      //  表单提交
      function doLogin(){
    	  document.forms[0].submit();
          _bannerBox.show();
          _maskDiv.hide();
    	  _loginForm.hide();
      }


    //倒计时
      function setTimer(){
      	var i = 5; 
      	var intervalid; 
      	intervalid = setInterval("fun()", 1000); 
      	function fun() { 
      	if (i == 1) { 
      	window.location.href = "<%=basePath%>main/login.do"; 
      	clearInterval(intervalid); 
      	} 
      	document.getElementById("timer").innerHTML = i; 
      	i--; 
      	} 	
      }
</script>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>一天一天</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="一天一天 ">
    <link rel="shortcut icon" href="img/favicon.ico">
    
    <!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="css/yitian.css">
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.css">
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<script src="js/transition.js"></script>
<script src="js/tooltip.js"></script>
<script src="js/modal.js"></script>
<script src="js/carousel.js"></script>
</head>

<body role="document" style="background-image: url(img/bg.jpg);background-position:100px -190px;font-family: 'arial','微软雅黑','Microsoft YaHei'">
<!-- Fixed navbar -->
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation" id ="mainNav">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">OneDay</a>
        </div>
        <div class="navbar-collapse collapse" id="navbarmenu">
          <ul class="nav navbar-nav">
            <li><a href="#">首页</a></li>
            <li><a href="<%=request.getContextPath()%>/main/login.do">地图</a></li>
            <li><a href="#aboutUs" id="togglemodal">关于我们</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">黎杰 <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li role="presentation"><a role="menuitem" tabindex="-1" href="#"><span class="glyphicon glyphicon-user"></span> 我的中心</a></li>
                <li role="presentation"><a role="menuitem" tabindex="-1" href="#"><span class="glyphicon glyphicon-pencil"></span> 账号修改</a></li>
                <li role="presentation"><a role="menuitem" tabindex="-1" href="#"><span class="glyphicon glyphicon-cog"></span> 设置中心</a></li>
                <li role="presentation" class="divider"></li>
                <li role="presentation" class="dropdown-header"> 我的收藏</li>
                <li role="presentation"><a role="menuitem" tabindex="-1" href="#"><span class="glyphicon glyphicon-music"></span> 音乐</a></li>
                <li role="presentation"><a role="menuitem" tabindex="-1" href="#"><span class="glyphicon glyphicon-film"></span> 视频</a></li>
              </ul>
            </li>
          </ul>
          <div class="btn-group" style="margin-top:8px;float:right">
			  <button type="button" class="btn btn-primary">登陆</button>
			  <button type="button" class="btn btn-primary">注册</button>
	      </div>
        </div><!--/.nav-collapse -->
      </div>
    </div>

    <div class="jumbotron" style="background-image: url(img/1.jpg);background-repeat: repeat-x;margin-top:52px;max-height: 330px">
	  <div class="container">
	   <h2 style="color:white;">Hello, Dear WangTing!</h2>
		  <p style="color:white;">嗨，小婷婷，七夕快乐！<img src="img/love.jpg" width="50px" height="50px"/></p>
		  <blockquote>
			  <p style="color:white;"><small style="color:white;">年轻人，愿你在尘世获得幸福，有情人终成眷属！</small></p>
		  </blockquote>
		  <p><a class="btn btn-primary btn-lg" role="button" id="next">我说...</a><a class="btn btn-primary btn-lg" role="button" id="stopmusic" style="margin-left:5px;display:none">停止音乐</a></p>
	  </div>
    </div>
    <!-- 
    <div class="container" style="margin-top:55px;">
    <div class="panel panel-default" id ="word">
	  <div class="panel-body" id = "word0">
	      	    <p style="text-align: center;"><img width="60px" height="60px" src="img/L.jpg" class="imgView"><img src="img/aixin.png" style="margin-left:10px;margin-right:10px"> <img width="60px" height="60px" src="img/T.jpg" class="imgView"></p>
	  </div>
    </div>
    </div>
    -->
		<div class="container" style="margin-top: 52px">
			<div class="row">
				<div class="col-md-12">
					<div style="width: 100%;min-height: 500px">
					<p style="text-align: center;position:absolute;top:260px;left:550px"><a href="#"><img src="img/aixin.jpg" style="margin-left:10px;margin-right:10px"></a></p>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="aboutUs" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">联系我们</h4>
      </div>
      <div class="modal-body">
        <div class="control-group">
          <label class="control-label">联系电话:</label>
          <label class="control-label">13545052440</label>
        </div>
       <div class="control-group">
          <label class="control-label">公司地址:</label>
          <label class="control-label">湖北武汉市光谷大道金融港B4 12层</label>
        </div>
      </div>
       <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary">确定</button>
      </div>
    </div>
  </div>
</div>     
    <div style="display:none"><audio id ="media" src="video/xuehui.mp3" id="video" width="1350" controls="controls"></audio></div>
    <footer id="copyright">
			<p class="copyright_en">Copyright © 2014 JayLee All Rights Reserved</p>
			<p class="copyright_cn"><span>黎杰·版权所有·鄂ICP备14011367号</span><img width="13px" height="15px" src="img/copy_rignt_24.png"/></p>
    </footer>
 <script src="face/home.js"></script>
</html>

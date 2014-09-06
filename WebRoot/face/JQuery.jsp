<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'JQuery.jsp' starting page</title>
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
   <style type="text/css">
   div{
      margin:3px;
      width:40px; 
      height:40px;
      position:absolute; 
      left:0px; 
      top:40px; 
      background:green; 
      color:white;
   }
  div.newcolor { background:blue; }
   </style>
  </head>
  
  <body>
   <div>Dear Jay!</div>
  </body>
  <script type="text/javascript">
  $(document.body).toggle(function(){
	  $('div').text('18062567515');
	 $('div').queue(function(){
		 $(this).addClass('newcolor');
	 });
  },function(){
	  alert('SB!,WANG YUJIE');
  });
  
  </script>
</html>

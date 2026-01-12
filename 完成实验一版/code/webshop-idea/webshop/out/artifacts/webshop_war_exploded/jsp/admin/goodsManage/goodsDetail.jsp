<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();  
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
	<meta charset="UTF-8">
	<title>商品详情页</title>
	<link rel="stylesheet" href="bs/css/bootstrap.css">
	<style type="text/css">
		body{
			margin:0;
			padding:0;
			background:#eee;
		}
		 
		.container .row{
			line-height: 30px;
			htight:30px;
		}
		
	</style>
</head>
<body>
	<h2 class="text-center">商品详情</h2>
	<div class="container">
		<div class="row">
			<div class="col-md-2 text-right">商品id</div>
			<div class="col-md-10">${goodsInfo.goodsId}</div>
		</div>
		<div class="row">
			<div class="col-md-2 text-right">商品名称</div>
			<div class="col-md-10">${goodsInfo.goodsName}</div>
		</div>
		<div class="row">
			<div class="col-md-2 text-right">商品分类</div>
			<div class="col-md-10">${goodsInfo.catalog.catalogName}</div>
		</div>
		<div class="row">
			<div class="col-md-2 text-right">商品产地</div>
			<div class="col-md-10">${goodsInfo.origin}</div>
		</div>
		<div class="row">
			<div class="col-md-2 text-right">供应商</div>
			<div class="col-md-10">${goodsInfo.supplier}</div>
		</div>
		<div class="row">
			<div class="col-md-2 text-right">商品价格</div>
			<div class="col-md-10">￥${goodsInfo.price}</div>
		</div>
		<div class="row">
			<div class="col-md-2 text-right">上架日期</div>
			<div class="col-md-10">${goodsInfo.addTime}</div>
		</div>
		
		<div class="row">
			<div class="col-md-2 text-right">商品简介</div>
			<div class="col-md-10">${goodsInfo.description}</div>
		</div>
		<div>
			<img class="col-md-6 col-md-offset-2" alt="" src="${goodsInfo.upLoadImg.imgSrc}">
		</div>
		
	</div>
</body>
</html>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();  
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	pageContext.setAttribute("basePath", basePath);
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${basePath}">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>商城</title>
	<link rel="stylesheet" href="bs/css/bootstrap.css">
	<script type="text/javascript" src="bs/js/jquery.min.js"></script>
	<script type="text/javascript" src="bs/js/bootstrap.js"></script> 
	<script type="text/javascript" src="js/goods/landing.js"></script>
	<link href="css/goods/head_footer.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="js/goods/addcart.js"></script>
	<style type="text/css">
		.wrapper .pro_info{
			border-bottom: 1px #ccc solid;
			line-height: 34px;
			margin-top:20px;
		}
		.wrapper .pro_info tr td:first-child{
			font-weight: bold;
			
		}
		.wrapper .pro_info i{
			color:red;
			font-size:22px;
		}
		.wrapper .buy_pro{
			margin-top:20px;
			
		}
		.wrapper .pro_desc{
			margin:10px;
		}
		
		.wrapper .pro_desc h3{
			border-bottom: 1px #ccc solid;
			padding:10px;
		}
		.wrapper .pro_desc div{
			text-indent: 2em;
			line-height: 2em;
		}
		
	</style>
</head>
<body>

	<div class="container-fullid">
		<%@include file="header.jsp" %>
		<div class="wrapper">
			<!-- main start -->
			<div class="main container">
				<div class="sub-nav">
					<ol class="breadcrumb">
  						<li><a href="jsp/front/index.jsp">主页</a></li>
						<li><a href="#">${goodsInfo.catalog.catalogName}</a></li>
						<li class="active">${goodsInfo.goodsName}</li>
					</ol>
				</div>
				<div class="row">
					<div class="col-md-5">
						<img class="img-responsive" src="${goodsInfo.upLoadImg.imgSrc}" />
					</div>
					<div class="col-md-7">
						<table class="pro_info">
								<tr>
									<td colspan="2"><h2>${goodsInfo.goodsName}</h2></td>
								</tr>
								<tr>
									<td>价格：</td>
									<td><span style='color: red;font-size: 20px'>￥${goodsInfo.price}</span></td>
								</tr>
								<tr>
									<td>商品编号：</td>
									<td>${goodsInfo.goodsId}</td>
								</tr>
								<tr>
									<td>商品分类：</td>
									<td>${goodsInfo.catalog.catalogName}</td>
								</tr>
								<tr>
									<td>产地：</td>
									<td>${goodsInfo.origin}</td>
								</tr>
								<tr>
									<td>供应商：</td>
									<td>${goodsInfo.supplier}</td>
								</tr>
								<tr>
									<td>上架日期：</td>
									<td>${goodsInfo.addTime}</td>
								</tr>
								<tr>
									<td>服务：</td>
									<td>由商城 发货,并提供售后服务,预计三天内送达，我们会全力加快为您服务，谢谢！</td>
								</tr>
							</table>
						<p class="buy_pro">
								<button type="button" class="btn btn-danger" onclick="addToCart(${goodsInfo.goodsId})" data-toggle="modal" data-target=".bs-example-modal-sm">加入购物车</button>
						</p>
						<p></p>
					</div>
					
				</div>
				<div class="row pro_desc">
					<h3>商品简介</h3>
					<div>${goodsInfo.description}</div>
				</div>
				
			</div>
			
			
		</div>
								
		<%@include file="footer.jsp" %>
	</div>
<!--弹窗盒子start -->
<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
  	<div class="modal-dialog modal-sm">
    	<div class="modal-content">
    		<div class="modal-body" style="color:green;font-size:24px;">
			  <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>&nbsp商品已成功加入购物车！
			</div>
    			
      		<div class="modal-footer">
      			<a href="javascript:void(0)" type="button" class="btn btn-default" data-dismiss="modal">返回继续购物</a>
		        <a href="jsp/front/cart.jsp" type="button" class="btn btn-success">立即去结算</a>
		    </div>
    	</div>
  	</div>
</div>
<!--弹窗盒子end -->
</body>
</html>
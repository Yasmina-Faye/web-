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
    <link href="css/goods/head_footer.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/goods/getCatalog.js"></script>
<%--    <script type="text/javascript" src="js/goods/index.js"></script>--%>
    <script type="text/javascript" src="js/goods/landing.js"></script>
    <link rel="stylesheet" href="css/goods/index.css" />
    <script type="text/javascript" src="js/goods/addcart.js"></script>
   <script>
       $.ajax({
           url: "ShopIndex?count=8",
           dataType: "json",
           async: true,
           data: {},
           type: "POST",
           success: function (data) {
               datalist(data);
           }

       })

       function datalist(data) {
           //新增加的书
           if (data.newGoodss != null) {
               $.each(data.newGoodss, function (i, n) {
                   var tag = "<li class='col-md-3'><div class='list'>" +
                       "<a href='goodsdetail?goodsId=" + n.goodsId + "'><img class='img-responsive' src='" + n.upLoadImg.imgSrc + "'/></a>" +
                       "<div class='proinfo'><h2><a class='text-center' href='goodsdetail?goodsId=" + n.goodsId + "'>" + n.goodsName + "</a></h2>" +
                       "<p><span style='color: #ff0000;font-size: 20px'>" + n.price + "</span><a class='btn btn-danger btn-xs' href='javascript:void(0)' onclick='addToCart(" + n.goodsId + ")' " +
                       "data-toggle='modal' data-target='.bs-example-modal-sm'>加入购物车</a></p></div></div></li>";

                   $("#newGoodss ul").append(tag);

               })
           }


       }

   </script>
    <style>
        .img-responsive {
            width: 300px; /* 设置固定的宽度 */
            height: 200px; /* 设置固定的高度 */
            object-fit: contain; /* 保持图片的纵横比，同时确保图片完全显示在容器内 */
        }

    </style>
</head>
<body>

<div class="container-fullid">
    <%@include file="header.jsp" %>
    <div class="wrapper">
        <!-- banner start -->
        <div class="banner">
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="3"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="4"></li>
                </ol>
            </div>
        </div>
        <!-- main start -->
        <div class="main container">
            <div class="row">
                <div class="col-md-2 main-left">
                    <h3>商品分类</h3>

                    <ul id="catalog-list">
                        <li><a href="GoodsList">全部商品</a></li>

                    </ul>
                </div>
                <div class="col-md-10 main-right">
                    <div class="pro col-md-12">
                        <h3>新品上架</h3>
                        <div id="newGoodss" class="pro-list">
                            <ul >

                            </ul>
                        </div>
                    </div>
                </div>

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
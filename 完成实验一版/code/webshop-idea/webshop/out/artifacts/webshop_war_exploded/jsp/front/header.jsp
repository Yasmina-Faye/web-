<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="head">
			<div class="top">
				<div class="container">

					<a href="jsp/admin/login.jsp">后台管理系统</a>
					<div class="pull-right">
						<c:choose>
							<c:when test="${empty landing}">
								<div class="top-right">
									您好，请
									<a href="jsp/front/reg.jsp?type=login">登录</a>
									<a href="jsp/front/reg.jsp?type=reg">注册</a>
								</div>
							</c:when>
							<c:otherwise>
								<div class="btn-group adminName top-right">
									<a href="javascript:void(0)">
									    ${landing.name} <span class="caret"></span>
									</a>
									<ul class="dropdown-menu dropdown-menu-right">
									    <li><a href="OrderServlet?action=list" >我的订单</a></li>
									    <li><a href="UserServlet?action=mySelf">我的资料</a></li>
									    <li><a style="border-top:1px #ccc solid" href="UserServlet?action=off" onClick="return confirm('确定要退出登陆了么？')">退 出 登 录</a></li>
									</ul>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
			<div class="mid container">
				<div class="row">
					<a class="logo col-md-5"  title="网上商城" style="padding-top:25px">
						<span>网上商城</span>
					</a>
					<div class="search col-md-4">
						<div class="input-group" style="position: relative;">
							<form action="GoodsList" method="get">
		     	 				<input style="float: left;width: 180px;" type="text" class="form-control" id="searchInput" name="seachname" placeholder="输入要搜索的商品名称" autocomplete="off">
		       					&nbsp;&nbsp;&nbsp;
		       					<input style="float: left;width: 55px;" class="btn btn-default" type="submit" value="搜索"/>
							</form>
							<div id="autocomplete-list" style="position: absolute; top: 38px; left: 0; width: 180px; background: white; border: 1px solid #ddd; display: none; z-index: 1000; max-height: 300px; overflow-y: auto;"></div>
   						</div>
					</div>
					<div class="shopcart col-md-2 col-md-offset-1">
						<a id="cart" href="jsp/front/cart.jsp">
							<span class="badge num">
								<c:choose>
									<c:when test="${!empty shopCart}">
										${shopCart.getTotQuan()}
									</c:when>
									<c:otherwise>
										0
									</c:otherwise>
								</c:choose>
							</span>
							<span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
							<span>购物车</span>
							<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
						</a>
					</div>
				</div>
				<div class="navbar">
					<ul class="nav navbar-nav">
				        <li><a href="jsp/front/index.jsp">首 页 <span class="sr-only">(current)</span></a></li>
						<li><a href="GoodsList">分 类</a></li>
						<li><a href="jsp/front/newGoods.jsp">新 品</a></li>
						<li><a href="jsp/front/recommendGoods.jsp">推 荐</a></li>
			      	</ul>
				</div>
			</div>
		</div>
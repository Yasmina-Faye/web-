$.ajax({
    url: "ShopIndex",
    dataType: "json",
    async: true,
    data: {},
    type: "POST",
    success: function (data) {
        datalist(data);
    }

})

function datalist(data) {


    //推荐书籍
    if (data.recGoodss != null) {
        $.each(data.recGoodss, function (i, n) {
            var tag = "<li class='col-md-3'><div class='list'>" +
                "<a href='goodsdetail?goodsId=" + n.goodsId + "'><img class='img-responsive' src='" + n.upLoadImg.imgSrc + "'/></a>" +
                "<div class='proinfo'><h2><a class='text-center' href='goodsdetail?goodsId=" + n.goodsId + "'>" + n.goodsName + "</a></h2>" +
                "<p><span style='color: red;font-size: 20px'>" + n.price + "</span><a class='btn btn-danger btn-xs' href='javascript:void(0)' onclick='addToCart(" + n.goodsId + ")' " +
                "data-toggle='modal' data-target='.bs-example-modal-sm'>加入购物车</a></p></div></div></li>";

            $("#recGoodss ul").append(tag);
        })
    }

    //新增加的书
    if (data.newGoodss != null) {
        $.each(data.newGoodss, function (i, n) {
            var tag = "<li class='col-md-3'><div class='list'>" +
                "<a href='goodsdetail?goodsId=" + n.goodsId + "'><img class='img-responsive' src='" + n.upLoadImg.imgSrc + "'/></a>" +
                "<div class='proinfo'><h2><a class='text-center' href='goodsdetail?goodsId=" + n.goodsId + "'>" + n.goodsName + "</a></h2>" +
                "<p><span style='color: red;font-size: 20px'>" + n.price + "</span><a class='btn btn-danger btn-xs' href='javascript:void(0)' onclick='addToCart(" + n.goodsId + ")' " +
                "data-toggle='modal' data-target='.bs-example-modal-sm'>加入购物车</a></p></div></div></li>";

            $("#newGoodss ul").append(tag);

        })
    }


}

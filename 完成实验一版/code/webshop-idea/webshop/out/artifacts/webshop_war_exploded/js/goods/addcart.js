function addToCart(goodsId){
	$.ajax({
		url:"CartServlet?action=add",
		dataType:"json",
		async:true,
		data:{"goodsId":goodsId},
		type:"POST",
		success:function(data){
			$("#cart .num").html(data);
		}
			
	})
}




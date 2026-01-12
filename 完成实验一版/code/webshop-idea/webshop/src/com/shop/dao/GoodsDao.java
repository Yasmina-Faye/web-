package com.shop.dao;

import java.util.List;

import com.shop.bean.Goods;
import com.shop.bean.PageBean;

public interface GoodsDao {


	List<Goods> goodsList(PageBean pageBean, Integer catalogId, String goodsname);







	// 增加商品
	boolean insertGoods(Goods goods);

	// 根据商品id查找商品信息
	Goods findGoodsById(int goodsId);

	// 根据商品名称查找商品是否存在
	boolean findGoodsByGoodsName(String goodsName);

	// 更新商品信息
	boolean updateGoods(Goods goods);

	// 根据id删除商品
	boolean deleteGoodsById(int goodsId);

	// 根据id串查询图片id串
	String findimgIdByIds(String ids);

	// 批量删除商品
	boolean batchDeleteGoodsByIds(String ids);

	// 随机获取指定数量书
	List<Goods> getRandGoodsListLimitNum(int num);

	// 获取指定数量新添加的商品
	List<Goods> getNewAddGoodssLimitNum(int num);

	long goodsReadCount(String goodsname, Integer catalogId);

	// 根据关键词搜索商品（用于自动补全）
	List<Goods> searchGoodsByKeyword(String keyword);

}

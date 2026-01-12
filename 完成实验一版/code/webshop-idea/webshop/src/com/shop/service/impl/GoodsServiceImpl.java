package com.shop.service.impl;

import com.shop.dao.GoodsDao;
import com.shop.service.GoodsService;
import com.shop.bean.Goods;
import com.shop.bean.PageBean;

import com.shop.dao.impl.GoodsDaoImpl;

import java.util.List;


public class GoodsServiceImpl implements GoodsService {

    private GoodsDao goodsDao=new GoodsDaoImpl();




    @Override
    public List<Goods> goodsList(PageBean pageBean, Integer catalogId, String goodsname) {
        return goodsDao.goodsList(pageBean, catalogId, goodsname);
    }



    // 按分类获取商品数量
    public long goodsReadCount(String goodsname, Integer catalogId){
        return goodsDao.goodsReadCount(goodsname,catalogId);
    }


    // 增加商品
    public boolean goodsAdd(Goods goods){
        return goodsDao.insertGoods(goods);
    }

    // 根据商品id查找商品信息
    public Goods findGoodsById(int goodsId){
        return goodsDao.findGoodsById(goodsId);
    }

    // 根据商品名称查找商品是否存在
    public boolean findGoodsByGoodsName(String goodsName){
        return goodsDao.findGoodsByGoodsName(goodsName);
    }

    // 更新商品信息
    public boolean goodsUpdate(Goods goods){
        return goodsDao.updateGoods(goods);
    }

    // 根据id删除商品
    public boolean goodsDelById(int goodsId){
        return goodsDao.deleteGoodsById(goodsId);
    }

    // 根据id串查询图片id串
    public String findimgIdByIds(String ids){
        return goodsDao.findimgIdByIds(ids);
    }

    // 批量删除商品
    public boolean goodsBatDelById(String ids){
        return goodsDao.batchDeleteGoodsByIds(ids);
    }

    // 随机获取指定数量书
    public List<Goods> goodsList(int num){
        return goodsDao.getRandGoodsListLimitNum(num);
    }

    // 获取指定数量新添加的商品
    public List<Goods> newGoodss(int num){
        return goodsDao.getNewAddGoodssLimitNum(num);
    }

    // 根据关键词搜索商品（用于自动补全）
    @Override
    public List<Goods> searchGoodsByKeyword(String keyword) {
        return goodsDao.searchGoodsByKeyword(keyword);
    }
}

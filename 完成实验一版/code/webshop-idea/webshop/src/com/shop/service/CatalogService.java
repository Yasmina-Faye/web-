package com.shop.service;

import com.shop.bean.Catalog;
import com.shop.bean.PageBean;

import java.util.List;

/**
 * 商品分类dao层
 *
 *
 */
public interface CatalogService {
	//获取商品分类信息
	List<Catalog> getCatalog();
	//获取商品分类信息（分页）
	List<Catalog> catalogList(PageBean pb);
	//统计总数
	long catalogReadCount();
	//分类删除
	boolean catalogDel(int catalogId);
	//分类批量删除
	boolean catalogBatDelById(String ids);
	//查找分类名称
	boolean findCatalogByCatalogName(String catalogName);
	//增加分类
	boolean catalogAdd(String catalogName);
}

package com.shop.service.impl;

import com.shop.dao.CatalogDao;
import com.shop.dao.impl.CatalogDaoImpl;
import com.shop.bean.Catalog;
import com.shop.bean.PageBean;
import com.shop.service.CatalogService;

import java.util.List;

/**
 * 商品分类dao层
 *
 *
 */
public class CatalogServiceImpl implements CatalogService {

	private CatalogDao catalogDao=new CatalogDaoImpl();
	//获取商品分类信息
	public List<Catalog> getCatalog(){
		return catalogDao.getCatalog();
	}
	//获取商品分类信息（分页）
	public List<Catalog> catalogList(PageBean pb){
		return catalogDao.catalogList(pb);
	}
	//统计总数
	public long catalogReadCount(){
		return catalogDao.catalogReadCount();
	}
	//分类删除
	public boolean catalogDel(int catalogId){
		return catalogDao.catalogDel(catalogId);
	}
	//分类批量删除
	public boolean catalogBatDelById(String ids){
		return catalogDao.catalogBatDelById(ids);
	}
	//查找分类名称
	public boolean findCatalogByCatalogName(String catalogName){
		return catalogDao.findCatalogByCatalogName(catalogName);
	}
	//增加分类
	public boolean catalogAdd(String catalogName){
		return catalogDao.catalogAdd(catalogName);
	}
}

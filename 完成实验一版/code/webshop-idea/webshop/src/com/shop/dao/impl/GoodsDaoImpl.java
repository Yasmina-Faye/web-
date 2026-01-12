package com.shop.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.shop.bean.Goods;
import com.shop.bean.PageBean;
import com.shop.dao.GoodsDao;
import com.shop.util.DateUtil;
import com.shop.util.DbUtil;

public class GoodsDaoImpl implements GoodsDao {




	@Override
	public boolean insertGoods(Goods goods) {
		String sql = "insert into s_goods(goodsName,catalogId,origin,supplier,price,description,imgId,addTime) values(?,?,?,?,?,?,?,?)";

		int i = DbUtil.excuteUpdate(sql, goods.getGoodsName(), goods.getCatalog().getCatalogId(), goods.getOrigin(),
				goods.getSupplier(), goods.getPrice(), goods.getDescription(), goods.getUpLoadImg().getImgId(),
				DateUtil.getTimestamp());

		return i > 0 ? true : false;
	}

	@Override
	public Goods findGoodsById(int goodsId) {
		String sql = "SELECT\n" +
				"  `s_catalog`.`catalogName`,\n" +
				"  `s_goods`.`goodsId`,\n" +
				"  `s_goods`.`catalogId`,\n" +
				"  `s_goods`.`goodsName`,\n" +
				"  `s_goods`.`price`,\n" +
				"  `s_goods`.`description`,\n" +
				"  `s_goods`.`imgId`,\n" +
				"  `s_uploadimg`.`imgName`,\n" +
				"  `s_uploadimg`.`imgSrc`,\n" +
				"  `s_uploadimg`.`imgType`,\n" +
				"  `s_goods`.`origin`,\n" +
				"  `s_goods`.`supplier`,\n" +
				"  `s_goods`.`addTime`\n" +
				"FROM\n" +
				"  ((`s_goods`\n" +
				"    JOIN `s_catalog` ON ((`s_goods`.`catalogId` = `s_catalog`.`catalogId`)))\n" +
				"    JOIN `s_uploadimg` ON ((`s_goods`.`imgId` = `s_uploadimg`.`imgId`))) where goodsId=?";
		Goods goods = null;
		List<Map<String, Object>> list = DbUtil.executeQuery(sql, goodsId);
		if (list.size() > 0) {
			goods = new Goods(list.get(0));
		}
		return goods;
	}

	/**
	 * 
	 */
	@Override
	public boolean findGoodsByGoodsName(String goodsName) {
		String sql = "select * from s_goods where goodsName=?";
		List<Map<String, Object>> list = DbUtil.executeQuery(sql, goodsName);
		return list.size() > 0 ? true : false;
	}

	/**
	 * 更新商品信息
	 */
	@Override
	public boolean updateGoods(Goods goods) {
		String sql = "update s_goods set catalogId=?,origin=?,supplier=?,price=?,description=? where goodsId=?";
		int i = DbUtil.excuteUpdate(sql, goods.getCatalogId(), goods.getOrigin(), goods.getSupplier(), goods.getPrice(),
				goods.getDescription(), goods.getGoodsId());
		return i > 0 ? true : false;
	}

	/**
	 * 商品删除
	 */
	@Override
	public boolean deleteGoodsById(int goodsId) {
		String sql = "SET FOREIGN_KEY_CHECKS = 0;delete from s_goods where goodsId=?";
		int i = DbUtil.excuteUpdate(sql, goodsId);
		return i > 0 ? true : false;
	}

	/**
	 * 批量查询
	 */
	@Override
	public String findimgIdByIds(String ids) {
		String imgIds = "";
		String sql = "select imgId from s_goods where goodsId in(" + ids + ")";
		List<Map<String, Object>> list = DbUtil.executeQuery(sql);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (i != list.size() - 1) {
					imgIds += list.get(i).get("imgId") + ",";
				} else {
					imgIds += list.get(i).get("imgId");
				}
			}
		}
		return imgIds;
	}

	// 批量删除
	@Override
	public boolean batchDeleteGoodsByIds(String ids) {
		String sql = "delete from s_goods where goodsId in(" + ids + ")";
		int i = DbUtil.excuteUpdate(sql);
		return i > 0 ? true : false;
	}

	// 随机查询一定数量的书
	@Override
	public List<Goods> getRandGoodsListLimitNum(int num) {
		List<Goods> list = new ArrayList<>();
		String sql = "SELECT\n" +
				"  `s_catalog`.`catalogName`,\n" +
				"  `s_goods`.`goodsId`,\n" +
				"  `s_goods`.`catalogId`,\n" +
				"  `s_goods`.`goodsName`,\n" +
				"  `s_goods`.`price`,\n" +
				"  `s_goods`.`description`,\n" +
				"  `s_goods`.`imgId`,\n" +
				"  `s_uploadimg`.`imgName`,\n" +
				"  `s_uploadimg`.`imgSrc`,\n" +
				"  `s_uploadimg`.`imgType`,\n" +
				"  `s_goods`.`origin`,\n" +
				"  `s_goods`.`supplier`,\n" +
				"  `s_goods`.`addTime`\n" +
				"FROM\n" +
				"  ((`s_goods`\n" +
				"    JOIN `s_catalog` ON ((`s_goods`.`catalogId` = `s_catalog`.`catalogId`)))\n" +
				"    JOIN `s_uploadimg` ON ((`s_goods`.`imgId` = `s_uploadimg`.`imgId`))) order by rand() LIMIT ?";
		List<Map<String, Object>> lm = DbUtil.executeQuery(sql, num);
		// 把查询的goods结果由List<Map<String, Object>>转换为List<Goods>
		if (lm.size() > 0) {
			for (Map<String, Object> map : lm) {
				Goods goods = new Goods(map);
				list.add(goods);
			}
		}
		return list;
	}

	/**
	 * 查询指定数量新书
	 */
	@Override
	public List<Goods> getNewAddGoodssLimitNum(int num) {
		List<Goods> list = new ArrayList<>();
		String sql = "SELECT\n" +
				"  `s_catalog`.`catalogName`,\n" +
				"  `s_goods`.`goodsId`,\n" +
				"  `s_goods`.`catalogId`,\n" +
				"  `s_goods`.`goodsName`,\n" +
				"  `s_goods`.`price`,\n" +
				"  `s_goods`.`description`,\n" +
				"  `s_goods`.`imgId`,\n" +
				"  `s_uploadimg`.`imgName`,\n" +
				"  `s_uploadimg`.`imgSrc`,\n" +
				"  `s_uploadimg`.`imgType`,\n" +
				"  `s_goods`.`origin`,\n" +
				"  `s_goods`.`supplier`,\n" +
				"  `s_goods`.`addTime`\n" +
				"FROM\n" +
				"  ((`s_goods`\n" +
				"    JOIN `s_catalog` ON ((`s_goods`.`catalogId` = `s_catalog`.`catalogId`)))\n" +
				"    JOIN `s_uploadimg` ON ((`s_goods`.`imgId` = `s_uploadimg`.`imgId`))) ORDER BY addTime desc limit 0,?";
		List<Map<String, Object>> lm = DbUtil.executeQuery(sql, num);
		// 把查询的goods结果由List<Map<String, Object>>转换为List<Goods>
		if (lm.size() > 0) {
			for (Map<String, Object> map : lm) {
				Goods goods = new Goods(map);
				list.add(goods);
			}
		}
		return list;
	}


	@Override
	public List<Goods> goodsList(PageBean pageBean, Integer catalogId, String goodsname) {
		List<Goods> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT\n" +
				"  `s_catalog`.`catalogName`,\n" +
				"  `s_goods`.`goodsId`,\n" +
				"  `s_goods`.`catalogId`,\n" +
				"  `s_goods`.`goodsName`,\n" +
				"  `s_goods`.`price`,\n" +
				"  `s_goods`.`description`,\n" +
				"  `s_goods`.`imgId`,\n" +
				"  `s_uploadimg`.`imgName`,\n" +
				"  `s_uploadimg`.`imgSrc`,\n" +
				"  `s_uploadimg`.`imgType`,\n" +
				"  `s_goods`.`origin`,\n" +
				"  `s_goods`.`supplier`,\n" +
				"  `s_goods`.`addTime`\n" +
				"FROM\n" +
				"  ((`s_goods`\n" +
				"    JOIN `s_catalog` ON ((`s_goods`.`catalogId` = `s_catalog`.`catalogId`)))\n" +
				"    JOIN `s_uploadimg` ON ((`s_goods`.`imgId` = `s_uploadimg`.`imgId`))) where 1=1 ");

		if (catalogId != null) {
			sql.append("and `s_goods`.catalogId=?");
		} else if (goodsname != null) {
			sql.append("and goodsname like '%").append(goodsname).append("%'");
		}

		sql.append(" limit ?,?");
		List<Map<String, Object>> lm=null;
		if (catalogId!=null){
			// 查询的分页结果集
			lm = DbUtil.executeQuery(sql.toString(),
					catalogId,
					(pageBean.getCurPage() - 1) * pageBean.getMaxSize(), pageBean.getMaxSize());
		}else {
			// 查询的分页结果集
			lm = DbUtil.executeQuery(sql.toString(),
					(pageBean.getCurPage() - 1) * pageBean.getMaxSize(), pageBean.getMaxSize());
		}


		// 把查询的goods结果由List<Map<String, Object>>转换为List<Goods>
		if (lm.size() > 0) {
			for (Map<String, Object> map : lm) {
				Goods goods = new Goods(map);
				list.add(goods);
			}
		}
		return list;
	}



	@Override
	public long goodsReadCount(String goodsname, Integer catalogId) {
		StringBuilder sql = new StringBuilder("select count(*) as count from s_goods where 1=1");
		if (goodsname != null && !goodsname.isEmpty()) {
			sql.append(" and goodsName like '%").append(goodsname).append("%'");
		}
		if (catalogId != null) {
			sql.append(" and catalogId=?");
		}
		List<Map<String, Object>> lm;
		if (catalogId != null) {
			lm = DbUtil.executeQuery(sql.toString(), catalogId);
		} else {
			lm = DbUtil.executeQuery(sql.toString());
		}
		return lm.size() > 0 ? (long) lm.get(0).get("count") : 0;
	}

	@Override
	public List<Goods> searchGoodsByKeyword(String keyword) {
		List<Goods> list = new ArrayList<>();
		String sql = "SELECT\n" +
				"  `s_catalog`.`catalogName`,\n" +
				"  `s_goods`.`goodsId`,\n" +
				"  `s_goods`.`catalogId`,\n" +
				"  `s_goods`.`goodsName`,\n" +
				"  `s_goods`.`price`,\n" +
				"  `s_goods`.`description`,\n" +
				"  `s_goods`.`imgId`,\n" +
				"  `s_uploadimg`.`imgName`,\n" +
				"  `s_uploadimg`.`imgSrc`,\n" +
				"  `s_uploadimg`.`imgType`,\n" +
				"  `s_goods`.`origin`,\n" +
				"  `s_goods`.`supplier`,\n" +
				"  `s_goods`.`addTime`\n" +
				"FROM\n" +
				"  ((`s_goods`\n" +
				"    JOIN `s_catalog` ON ((`s_goods`.`catalogId` = `s_catalog`.`catalogId`)))\n" +
				"    JOIN `s_uploadimg` ON ((`s_goods`.`imgId` = `s_uploadimg`.`imgId`)))\n" +
				"WHERE `s_goods`.`goodsName` LIKE ? LIMIT 10";
		
		List<Map<String, Object>> lm = DbUtil.executeQuery(sql, "%" + keyword + "%");
		
		if (lm.size() > 0) {
			for (Map<String, Object> map : lm) {
				Goods goods = new Goods(map);
				list.add(goods);
			}
		}
		return list;
	}

}

package com.shop.service;

import com.shop.bean.UpLoadImg;

import java.util.List;

/**
 * 商品图片上传dao层
 *
 *
 */
public interface UpLoadImgService {
	
	//图片增加
	boolean addImg(UpLoadImg upLoadImg);
	//根据商品图片名称获取id（名称用的uuid唯一识别码）
	Integer findIdByImgName(String imgName);
	//更新图片信息
	boolean updateImg(UpLoadImg upImg);
	//通过id删除图片
	boolean delById(int imgId);
	//通过imgIds查询图片信息
	List<UpLoadImg> findImgByIds(String imgIds);
	//批量删除
	boolean batDelById(String imgIds);
}

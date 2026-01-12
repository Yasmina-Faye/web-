package com.shop.service.impl;

import com.shop.bean.UpLoadImg;
import com.shop.dao.UpLoadImgDao;
import com.shop.dao.impl.UpLoadImgDaoImpl;
import com.shop.service.UpLoadImgService;

import java.util.List;

/**
 * 商品图片上传Service层
 */
public class UpLoadImgServiceImpl implements UpLoadImgService {

	private UpLoadImgDao upLoadImgDao=new UpLoadImgDaoImpl();
	
	//图片增加
	public boolean addImg(UpLoadImg upLoadImg){
		return upLoadImgDao.imgAdd(upLoadImg);
	}
	//根据商品图片名称获取id
	public Integer findIdByImgName(String imgName){
		return upLoadImgDao.findIdByImgName(imgName);
	}
	//更新图片信息
	public boolean updateImg(UpLoadImg upImg){
		return upLoadImgDao.imgUpdate(upImg);
	}
	//通过id删除图片
	public boolean delById(int imgId){
		return upLoadImgDao.imgDelById(imgId);
	}
	//通过imgIds查询图片信息
	public List<UpLoadImg> findImgByIds(String imgIds){
		return upLoadImgDao.findImgByIds(imgIds);
	}
	//批量删除
	public boolean batDelById(String imgIds){
		return upLoadImgDao.imgBatDelById(imgIds);
	}
}

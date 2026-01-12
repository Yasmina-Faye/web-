package com.shop.servlet.admin;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.shop.bean.Goods;
import com.shop.bean.Catalog;
import com.shop.bean.PageBean;
import com.shop.bean.UpLoadImg;
import com.shop.service.GoodsService;
import com.shop.service.CatalogService;
import com.shop.service.UpLoadImgService;
import com.shop.service.impl.GoodsServiceImpl;
import com.shop.service.impl.CatalogServiceImpl;
import com.shop.service.impl.UpLoadImgServiceImpl;
import com.shop.util.RanUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import net.sf.json.JSONObject;


@WebServlet("/GoodsManageServlet")
public class GoodsManageServlet extends HttpServlet {

	private GoodsService goodsService=new GoodsServiceImpl();
	private CatalogService catalogService=new CatalogServiceImpl();
	private UpLoadImgService imgService=new UpLoadImgServiceImpl();


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("list".equals(action)) {
			goodsList(request, response);
		} else if ("detail".equals(action)) {
			goodsDetail(request, response);
		} else if ("addReq".equals(action)) {
			goodsAddReq(request, response);
		} else if ("add".equals(action)) {
			goodsAdd(request, response);
		} else if ("edit".equals(action)) {
			goodsEdit(request, response);
		} else if ("update".equals(action)) {
			goodsUpdate(request,response);
		} else if ("find".equals(action)) {
			goodsFind(request, response);
		} else if ("updateImg".equals(action)) {
			updateImg(request,response);
		} else if ("del".equals(action)) {
			goodsDel(request,response);
		} else if ("batDel".equals(action)) {
			goodsBatDel(request,response);
		} else if ("seach".equals(action)) {
			seachGoods(request,response);
		}


	}
	private void seachGoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int curPage = 1;
		String page = request.getParameter("page");
		if (page != null) {
			curPage = Integer.parseInt(page);
		}
		int maxSize = Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));
		String goodsname = request.getParameter("goodsname");
	
		PageBean pb = null;
		if(goodsname != null && goodsname != "") {
			pb = new PageBean(curPage, maxSize, goodsService.goodsReadCount(goodsname,null));
			request.setAttribute("goodsList", goodsService.goodsList(pb,null,goodsname));
		}else {
			pb = new PageBean(curPage, maxSize, goodsService.goodsReadCount(null,null));
			request.setAttribute("goodsList", goodsService.goodsList(pb,null,null));
		}
		
		request.setAttribute("pageBean", pb);
		request.getRequestDispatcher("jsp/admin/goodsManage/goodsList.jsp").forward(request, response);
	}

	//商品批量删除
	private void goodsBatDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ids=request.getParameter("ids");


		File contextPath=new File(request.getServletContext().getRealPath("/"));
		String imgIds=goodsService.findimgIdByIds(ids);//批量查询图片的id并组成一组字符串
		
		List<UpLoadImg> list = imgService.findImgByIds(imgIds);
		if(goodsService.goodsBatDelById(ids)) {
			request.setAttribute("goodsMessage", "商品已批量删除");
			if(imgService.batDelById(imgIds)) {
				for(UpLoadImg uli:list) {
					//批量删除本地文件
					File f=new File(contextPath,uli.getImgSrc());
					if(f.exists()) {
						f.delete();
					}
				}
			}
		}else {
			request.setAttribute("goodsMessage", "商品批量删除失败");
		}
		//用户删除成功失败都跳转到用户列表页面
		goodsList(request, response);//通过servlet中listUser跳到用户列表
	}

	//商品删除
	private void goodsDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		File contextPath=new File(request.getServletContext().getRealPath("/"));


		Goods goods=goodsService.findGoodsById(id);
		//这里先删除数据库商品信息，再删除商品图片及本地硬盘图片信息
		if(goodsService.goodsDelById(id)) {
			request.setAttribute("goodsMessage", "商品已删除");
			if(imgService.delById(goods.getImgId())) {
				//删除本地文件
				File f=new File(contextPath,goods.getUpLoadImg().getImgSrc());
				if(f.exists()) {
					f.delete();
				}
			}
		}else {
			request.setAttribute("goodsMessage", "商品删除失败");
		}
		
		//用户删除成功失败都跳转到用户列表页面
		goodsList(request, response);//通过servlet中listUser跳到用户列表
		
	}

	//商品更新
	private void goodsUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Goods goods=new Goods();
		goods.setGoodsId(Integer.parseInt(request.getParameter("goodsId")));
		goods.setCatalogId(Integer.parseInt(request.getParameter("catalog")));
		goods.setOrigin(request.getParameter("origin"));
		goods.setSupplier(request.getParameter("supplier"));
		goods.setPrice(Double.parseDouble(request.getParameter("price")));
		goods.setDescription(request.getParameter("description"));
		
		if(goodsService.goodsUpdate(goods)) {
			request.setAttribute("goodsMessage", "修改成功");
			goodsList(request, response);
		}else {
			request.setAttribute("goodsMessage", "图片失败");
			request.setAttribute("goodsInfo", goodsService.findGoodsById(goods.getGoodsId()));
			request.getRequestDispatcher("jsp/admin/goodsManage/goodsEdit.jsp").forward(request, response);
		}
	}

	// 更新商品图片
	private void updateImg(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int goodsId = Integer.parseInt(request.getParameter("id"));
		boolean flag = false;
		String imgSrc = null;
		OutputStream outputStream = null;
		InputStream inputStream = null;
		String imgName = null;
		String contentType = null;



		
		File contextPath=new File(request.getServletContext().getRealPath("/"));
		File dirPath = new File( contextPath,"images/front/goodsimg/");
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}

		DiskFileItemFactory dfif = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(dfif);
		List<FileItem> parseRequest = null;
		try {
			parseRequest = servletFileUpload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		Iterator<FileItem> iterator = parseRequest.iterator();
		while (iterator.hasNext()) {
			FileItem fileItem = iterator.next();
			if (!fileItem.isFormField()) {

				inputStream = fileItem.getInputStream();
				contentType = fileItem.getContentType();
				if ("image/jpeg".equals(contentType)) {
					imgName = RanUtil.getUUID() + ".jpg";
					flag = true;
				}
				if ("image/png".equals(contentType)) {
					imgName = RanUtil.getUUID() + ".png";
					flag = true;
				}

			}

		}
		if (flag) {
			imgSrc = "images/front/goodsimg/" + imgName;
			outputStream = new FileOutputStream(new File(contextPath,imgSrc));
			IOUtils.copy(inputStream, outputStream);
			outputStream.close();
			inputStream.close();
			//根据商品id去查询图片信息
			Goods goods = goodsService.findGoodsById(goodsId);
			UpLoadImg upImg = goods.getUpLoadImg();
			// 删除旧图片文件如果存在
			File oldImg = new File(contextPath,goods.getUpLoadImg().getImgSrc());
			if (oldImg.exists()) {
				oldImg.delete();
			}
			upImg.setImgName(imgName);
			upImg.setImgSrc(imgSrc);
			upImg.setImgType(contentType);
			
			
			if (imgService.updateImg(upImg)) {
				request.setAttribute("goodsMessage", "图片修改成功");
			} else {
				request.setAttribute("goodsMessage", "图片修改失败");
			}
		} else {
			request.setAttribute("goodsMessage", "图片修改失败");
		}
		goodsEdit(request,response);
	}

	// 获取商品分类信息
	private void goodsAddReq(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("catalog", catalogService.getCatalog());
		request.getRequestDispatcher("jsp/admin/goodsManage/goodsAdd.jsp").forward(request, response);

	}

	// 商品增加
	private void goodsAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag = false;
		
		Map<String, String> map = new HashMap<>();
		InputStream inputStream = null;
		OutputStream outputStream = null;
		File dirPath = new File(request.getServletContext().getRealPath("/") + "images/front/goodsimg/");
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}

		DiskFileItemFactory dfif = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(dfif);
		// 解决乱码
		servletFileUpload.setHeaderEncoding("ISO8859_1");

		List<FileItem> parseRequest = null;
		try {
			parseRequest = servletFileUpload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		Iterator<FileItem> iterator = parseRequest.iterator();

		while (iterator.hasNext()) {
			FileItem fileItem = iterator.next();
			// 判断是否是表单的普通字段true为普通表单字段，false为上传文件内容
			if (fileItem.isFormField()) {
				String name = new String(fileItem.getFieldName().getBytes("ISO8859_1"), "utf-8");
				String value = new String(fileItem.getString().getBytes("ISO8859_1"), "utf-8");
				map.put(name, value);
			} else {
				String imgName = null;

				String contentType = fileItem.getContentType();

				if ("image/jpeg".equals(contentType)) {
					imgName = RanUtil.getUUID() + ".jpg";
					flag = true;
				}
				if ("image/png".equals(contentType)) {
					imgName = RanUtil.getUUID() + ".png";
					flag = true;
				}
				if (flag) {
					inputStream = fileItem.getInputStream();
					File file = new File(dirPath, imgName);
					outputStream = new FileOutputStream(file);
					// 保存img信息到map集合中，后面传入对象使用
					map.put("imgName", imgName);
					map.put("imgSrc", "images/front/goodsimg/" + imgName);
					map.put("imgType", contentType);
				}

			}
		}
		// 如果上传的内容小于3个必填项或者图片没有或类型不正确返回
		if (map.size() < 3 || !flag) {
			request.setAttribute("goodsMessage", "商品添加失败");
			goodsAddReq(request, response);
		} else {
			// 验证通过才可以保存图片流到本地
			IOUtils.copy(inputStream, outputStream);
			outputStream.close();
			inputStream.close();

			// 把map集合中存储的表单数据提取出来转换为goods对象
			// 这里要求商品增加的字段要和数据库字段一致，不然map集合转对象会出错
			Goods goods = new Goods();
			goods.setGoodsName(map.get("goodsName"));
			goods.setPrice(Double.parseDouble(map.get("price")));
			goods.setDescription(map.get("desc"));
			goods.setOrigin(map.get("origin"));
			goods.setSupplier(map.get("supplier"));
			// 商品分类信息
			Catalog catalog = goods.getCatalog();
			catalog.setCatalogId(Integer.parseInt(map.get("catalog")));
			// 图片信息
			UpLoadImg upLoadImg = goods.getUpLoadImg();
			upLoadImg.setImgName(map.get("imgName"));
			upLoadImg.setImgSrc(map.get("imgSrc"));
			upLoadImg.setImgType(map.get("imgType"));

			// 增加商品先增加商品图片,商品图片增加成功了在添加商品信息

			if (imgService.addImg(goods.getUpLoadImg())) {
				// 获取商品图片添加后的id
				Integer imgId = imgService.findIdByImgName(upLoadImg.getImgName());
				upLoadImg.setImgId(imgId);


				if (goodsService.goodsAdd(goods)) {
					request.setAttribute("goodsMessage", "商品添加成功");
					goodsList(request, response);
				} else {
					request.setAttribute("goodsMessage", "商品添加失败");
					goodsAddReq(request, response);
				}
			} else {
				// 图片添加失败就判定商品添加失败
				request.setAttribute("goodsMessage", "商品添加失败");
				goodsAddReq(request, response);
			}

		}
	}

	// 获取商品列表
	private void goodsList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int curPage = 1;
		String page = request.getParameter("page");
		if (page != null) {
			curPage = Integer.parseInt(page);
		}
		int maxSize = Integer.parseInt(request.getServletContext().getInitParameter("maxPageSize"));
		PageBean pb = new PageBean(curPage, maxSize, goodsService.goodsReadCount(null,null));
		
		request.setAttribute("pageBean", pb);
		request.setAttribute("goodsList", goodsService.goodsList(pb,null,null));
		request.getRequestDispatcher("jsp/admin/goodsManage/goodsList.jsp").forward(request, response);
	}

	// 商品详情页
	private void goodsDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");

		request.setAttribute("goodsInfo", goodsService.findGoodsById(Integer.parseInt(id)));
		request.getRequestDispatcher("jsp/admin/goodsManage/goodsDetail.jsp").forward(request, response);

	}

	// 接收商品修改请求
	private void goodsEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int goodsId = Integer.parseInt(request.getParameter("id"));


		request.setAttribute("catalog", catalogService.getCatalog());//获取商品分类信息
		request.setAttribute("goodsInfo", goodsService.findGoodsById(goodsId));//获取商品信息byId
		request.getRequestDispatcher("jsp/admin/goodsManage/goodsEdit.jsp").forward(request, response);
	}

	// ajax查找商品是否存在
	private void goodsFind(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String goodsName = request.getParameter("param");

		JSONObject json = new JSONObject();
		if (goodsService.findGoodsByGoodsName(goodsName)) {
			json.put("info", "该商品已存在");
			json.put("status", "n");
		} else {
			json.put("info", "输入正确");
			json.put("status", "y");
		}
		response.getWriter().write(json.toString());

	}

}

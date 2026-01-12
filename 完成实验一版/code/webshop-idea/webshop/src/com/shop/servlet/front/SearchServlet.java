package com.shop.servlet.front;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.bean.Goods;
import com.shop.service.GoodsService;
import com.shop.service.impl.GoodsServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GoodsService goodsService = new GoodsServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("autocomplete".equals(action)) {
            autocomplete(request, response);
        }
    }

    private void autocomplete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String keyword = request.getParameter("keyword");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        
        JSONArray jsonArray = new JSONArray();
        
        if (keyword != null && !keyword.trim().isEmpty()) {
            List<Goods> goodsList = goodsService.searchGoodsByKeyword(keyword.trim());
            
            for (Goods goods : goodsList) {
                JSONObject json = new JSONObject();
                json.put("goodsId", goods.getGoodsId());
                json.put("goodsName", goods.getGoodsName());
                jsonArray.add(json);
            }
        }
        
        pw.print(jsonArray.toString());
        pw.flush();
    }
}

package com.shop.servlet.front;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.shop.bean.User;
import com.shop.dao.impl.AdminDaoImpl;
import com.shop.dao.impl.UserDaoImpl;

import com.shop.service.UserService;
import com.shop.service.impl.UserServiceImpl;
import net.sf.json.JSONObject;


@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String LOGIN_PATH = "jsp/front/reg.jsp?type=login";
    private static final String REG_PATH = "jsp/front/reg.jsp?type=reg";
    private static final String INDEX_PATH = "jsp/front/index.jsp";
    private static final String UPDATE_MYSELF = "jsp/front/updateMySelf.jsp";
    private static final String LANDING = "landing";                //前台用户session标识

    private UserService userService = new UserServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action.equals("login")) {
            login(request, response);
        } else if (action.equals("off")) {
            offlogin(request, response);
        } else if (action.equals("ajlogin")) {
            ajlogin(request, response);
        } else if (action.equals("reg")) {
            reg(request, response);
        } else if (action.equals("landstatus")) {
            landstatus(request, response);
        } else if (action.equals("update")) {
            update(request, response);
        } else if (action.equals("mySelf")) {
            mySelf(request, response);
        } else if (action.equals("checkUsername")) {
            checkUsername(request, response);
        }
    }


    //判断登陆状态
    private void landstatus(HttpServletRequest request, HttpServletResponse response) throws IOException {

        User user = (User) request.getSession().getAttribute(LANDING);

        PrintWriter pw = response.getWriter();
        JSONObject json = new JSONObject();

        if (user != null) {
            json.put("status", "y");
        } else {
            json.put("status", "n");
        }
        pw.print(json.toString());
        pw.flush();

    }

    //ajax登陆
    private void ajlogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");
        User user = new User(userName, passWord);
        PrintWriter pw = response.getWriter();
        JSONObject json = new JSONObject();

        User user2 = userService.userLogin(user);

        if (user2 != null) {
            if ("y".equals(user2.getEnabled())) {
                request.getSession().setAttribute(LANDING, user2);
                json.put("status", "y");
            } else {
                json.put("info", "该用户已被禁用，请联系管理员");
            }
        } else {
            json.put("info", "用户名或密码错误，请重新登陆！");

        }
        pw.print(json.toString());
    }

    private void reg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User(
                request.getParameter("userName"),
                request.getParameter("passWord"),
                request.getParameter("name"),
                request.getParameter("sex"),
                Integer.parseInt(request.getParameter("age")),
                request.getParameter("tell"),
                request.getParameter("address"));
        user.setEnabled("y");//默认添加的用户启用
        //添加之前判断用户名是否在库中存在
        if (new AdminDaoImpl().findUser(user.getUserName())) {
            request.setAttribute("infoList", "用户添加失败！用户名已存在");
            request.getRequestDispatcher(REG_PATH).forward(request, response);
        } else {
            //执行dao层添加操作
            if (userService.addUser(user)) {
                request.setAttribute("infoList", "注册成功！请登陆！");
                request.getRequestDispatcher(LOGIN_PATH).forward(request, response);
            } else {
                request.setAttribute("userMessage", "用户添加失败！");
                request.getRequestDispatcher(REG_PATH).forward(request, response);
            }
        }

    }

    private void offlogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute(LANDING);
        if (user != null) {
            request.getSession().removeAttribute(LANDING);
            request.getSession().setAttribute("shopCart", null);

        }
        response.sendRedirect(INDEX_PATH);

    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");
        User user = new User(userName, passWord);

        User user2 = userService.userLogin(user);
        if (user2 != null) {
            if ("y".equals(user2.getEnabled())) {
                request.getSession().setAttribute(LANDING, user2);
                response.sendRedirect(INDEX_PATH);

            } else {
                request.setAttribute("infoList", "该用户已被禁用，请联系管理员");
                request.getRequestDispatcher(LOGIN_PATH).forward(request, response);
            }
        } else {
            request.setAttribute("infoList", "用户名或密码错误，请重新登陆！");
            request.getRequestDispatcher(LOGIN_PATH).forward(request, response);
        }

    }

    //判断登陆状态
    private void mySelf(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        User user = (User) request.getSession().getAttribute(LANDING);
        request.setAttribute("userInfo", user);
        request.getRequestDispatcher(UPDATE_MYSELF).forward(request, response);

    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int userId = Integer.parseInt(request.getParameter("userId"));
        User user = new User(
                userId,
                request.getParameter("passWord"),
                request.getParameter("name"),
                request.getParameter("sex"),
                Integer.parseInt(request.getParameter("age")),
                request.getParameter("tell"),
                request.getParameter("address"),"y");
        String userName = request.getParameter("userName");
        //执行dao层添加操作
        if (userService.updateUser(user)) {
            user.setUserName(userName);
            request.getSession().setAttribute(LANDING, user);
            request.setAttribute("infoList", "修改成功！");
            request.getRequestDispatcher("/UserServlet?action=mySelf").forward(request, response);
        } else {
            request.setAttribute("userMessage", "修改失败！");
            request.getRequestDispatcher("/UserServlet?action=mySelf").forward(request, response);

        }

    }

    private void checkUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("userName");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        JSONObject json = new JSONObject();

        if (userName == null || userName.trim().isEmpty()) {
            json.put("status", "n");
            json.put("info", "用户名不能为空");
        } else {
            boolean exists = new UserDaoImpl().findUser(userName);
            if (exists) {
                json.put("status", "n");
                json.put("info", "用户名已存在");
            } else {
                json.put("status", "y");
                json.put("info", "");
            }
        }
        pw.print(json.toString());
        pw.flush();
    }

}

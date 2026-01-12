package com.shop.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.shop.bean.PageBean;
import com.shop.bean.User;
import com.shop.dao.UserDao;
import com.shop.util.DbUtil;

public class UserDaoImpl implements UserDao {

    @Override
    public long goodsReadCount(String username) {
        long count = 0;
        StringBuilder sql = new StringBuilder("select count(*) as count from s_user");
        if (username != null && !username.isEmpty()) {
            sql.append(" where userName like '%").append(username).append("%'");
        }
        List<Map<String, Object>> lm = DbUtil.executeQuery(sql.toString());
        if (lm.size() > 0) {
            count = (long) lm.get(0).get("count");
        }
        return count;
    }

    @Override
    public List<User> userList(PageBean pageBean, String username) {
        List<User> lu = new ArrayList<>();
        String sql = "select * from s_user where 1=1";

        if (username != null && !username.isEmpty()) {
            sql += " and userName like ?";
        }

        sql += " limit ?,?";

        List<Map<String, Object>> lm = null;
        if (username != null && !username.isEmpty()) {
        // 查询的分页结果集
            lm = DbUtil.executeQuery(sql.toString(),username,
                    (pageBean.getCurPage() - 1) * pageBean.getMaxSize(), pageBean.getMaxSize());
        } else {
            // 查询的分页结果集
            lm = DbUtil.executeQuery(sql.toString(),
                    (pageBean.getCurPage() - 1) * pageBean.getMaxSize(), pageBean.getMaxSize());
        }

        if (lm.size() > 0) {
            for (Map<String, Object> map : lm) {
                User u = new User(map);
                lu.add(u);
            }
        }


        return lu;
    }


    @Override
    public boolean findUser(String userName) {
        String sql = "select * from s_user where userName=?";
        List<Map<String, Object>> list = DbUtil.executeQuery(sql, userName);
        return list.size() > 0 ? true : false;
    }

    /**
     * @param user 要增加的用户对象
     * @return 返回一个boolean true用户增加成功 false用户增加失败
     */
    @Override
    public boolean userAdd(User user) {
        String sql = "insert into s_user(userName,userPassWord,name,sex,age,tell,address,enabled) values(?,?,?,?,?,?,?,?)";

        int i = DbUtil.excuteUpdate(sql, user.getUserName(), user.getUserPassWord(), user.getName(), user.getSex(), user.getAge()
                , user.getTell(), user.getAddress(), user.getEnabled());

        return i > 0 ? true : false;

    }

    /**
     * @param id 根据id查找一个用户信息
     * @return 返回一个list用户信息集合
     */
    //查找指定id用户信息
    @Override
    public User findUser(Integer id) {
        String sql = "select * from s_user where userId=?";
        User u = null;
        List<Map<String, Object>> list = DbUtil.executeQuery(sql, id);
        if (list.size() > 0) {
            u = new User(list.get(0));
        }
        return u;
    }

    /**
     * @return boolean
     */
    @Override
    public boolean userUpdate(User user) {
        String sql = "update s_user set userPassWord=?,name=?,sex=?,age=?,tell=?,address=?,enabled=? where userId =?";
        int i = DbUtil.excuteUpdate(sql, user.getUserPassWord(), user.getName(), user.getSex(), user.getAge()
                , user.getTell(), user.getAddress(), user.getEnabled(), user.getUserId());

        return i > 0 ? true : false;
    }

    /**
     * @param id 要删除的用户id
     * @return 返回boolean true删除用户成功，false删除用户失败
     */
    @Override
    public boolean delUser(int id) {
        String sql = "delete from s_user where userId=?";
        int i = DbUtil.excuteUpdate(sql, id);
        return i > 0 ? true : false;
    }

    /**
     * @param ids 要批量删除id组的字符串
     */
    @Override
    public boolean batDelUser(String ids) {
        String sql = "delete from s_user where userId in (" + ids + ")";
        int i = DbUtil.excuteUpdate(sql);
        return i > 0 ? true : false;
    }

    @Override
    public User userLogin(User user) {
        User user1 = null;
        String sql = "select * from s_user where userName=? and userPassWord=?";
        List<Map<String, Object>> list = DbUtil.executeQuery(sql, user.getUserName(), user.getUserPassWord());
        if (list.size() > 0) {
            Map<String, Object> map = list.get(0);
            user1 = new User(map);

        }
        return user1;
    }

}

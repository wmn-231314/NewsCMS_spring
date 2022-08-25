package com.ning.service;

import com.ning.entity.User;
//import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService {
    /**
     * 用id查询用户信息
     * @param id;
     * @return User
     */
    User getUserById(int id) throws Exception;

    /**
     * 用账号查询用户信息
     * @param account;
     * @return User
     */
    User getUserByAccount(String account) throws Exception;

    /**
     * 获取用户列表
     */
    List<User> getAllUser() throws Exception;

    /**
     * 通过权限获取用户列表
     * @param permission;
     * @return List
     */
    List<User> getUserListByPermission(String permission) throws Exception;

    /**
     * 获取禁言用户列表
     * @return List
     */
    List<User> getBannedUserList(int banned) throws Exception;

    /**
     * 更新用户信息
     * @param user;
     * @return int
     */
    int updateUser(User user) throws Exception;

    int updateUserBan(int uid,int banned) throws Exception;

    /**
     * 以列表的形式更新用户信息
     * @param userList;
     * @return int
     */
    int updateUserByList(List<User> userList) throws Exception;

    /**
     * 通过账号删除用户
     * @param account;
     * @return int
     */
    int deleteUserByAccount(String account) throws Exception;

    /**
     * 通过id删除用户
     * @param id;
     * @return int
     */
    int deleteUserById(int id) throws Exception;

    /**
     * 通过列表删除用户
     * @param userList;
     * @return int
     */
    int deleteUserByList(List<User> userList) throws Exception;

    /**
     * 登录账号
     * @param account,password;
     * @return User
     */
    User login(String account,String password) throws Exception;

    /**
     * 注册账号
     * @param user;
     * @return User
     */
    User register(User user) throws Exception;
}

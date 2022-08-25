package com.ning.news.mapper;

import com.ning.news.BaseTest;
import com.ning.news.config.Config;
import com.ning.news.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest extends BaseTest {
    @Autowired
    private UserMapper userMapper;

    @AfterEach
    void tearDown() {
    }

    @Test
    void addUser() {
        User user = new User("1234@qq.com","123","张三连");
        user.setPermission(Config.NORMAL_USER);
        int res = userMapper.addUser(user);
        System.out.println(res);
        user = userMapper.getUserByAccount("1234@qq.com");
        System.out.println("addUser"+user);
    }

    @Test
    void getUserByAccount() {
        String account = "admin";
        User user = userMapper.getUserByAccount(account);
        System.out.println("getUserByAccount"+user);
    }

    @Test
    void getUserById() {
        int id = 1;
        User user = userMapper.getUserById(id);
        System.out.println("getUserById"+user);
    }

    @Test
    void getUserListByPermission() {
        String permission = "US000";
        List<User> userList = userMapper.getUserListByPermission(permission);
        System.out.println("getUserListByPermission");
        for(User user:userList){
            System.out.println(user);
        }
    }

    @Test
    void getAllUser() {
        List<User> allUser = userMapper.getAllUser();
        System.out.println("getAll");
        for(User user:allUser){
            System.out.println(user);
        }
    }

    @Test
    void getBannedUserList() {
        int banned = Config.BANNED_CODE;
        List<User> bannedUser = userMapper.getBannedUserList(banned);
        System.out.println("getBanned");
        for(User user:bannedUser){
            System.out.println(user);
        }
    }

    @Test
    void updateUser() {
        User user = userMapper.getUserById(2);
        user.setName(user.getName()+"p");
        System.out.println(user);
        int res = userMapper.updateUser(user);
        user = userMapper.getUserById(2);
        System.out.println("update"+res);
        System.out.println(user);
    }

    @Test
    void updateUserByList() {
        List<User> userList = userMapper.getAllUser();
        for(int i = 0; i < 2; i++){
            User user = userList.get(i);
            user.setName(user.getName()+"upList");
        }
        for(User user:userList){
            System.out.println(user);
        }
        int res = userMapper.updateUserByList(userList);
        userList = userMapper.getAllUser();
        System.out.println("update"+res);
        for(User user:userList){
            System.out.println(user);
        }
    }

    @Test
    void deleteUserByAccount() {
        String account = "1@qq.com";
        int res = userMapper.deleteUserByAccount(account);
        List<User> userList = userMapper.getAllUser();
        System.out.println("deleteAccount"+res);
        for(User user:userList){
            System.out.println(user);
        }
    }

    @Test
    void deleteUserById() {
        int id = 34;
        int res = userMapper.deleteUserById(id);
        System.out.println("deleteId"+res);
        System.out.println(userMapper.getUserById(34));
    }

    @Test
    void deleteUserByList() {
        List<User> userList = userMapper.getAllUser();
        int len = userList.size();
        for(int i = 0; i < len-2; i++){
            userList.remove(0);
        }
        for(User user:userList){
            System.out.println(user);
        }
        int res = userMapper.deleteUserByList(userList);
        System.out.println("delete list"+res);
        userList = userMapper.getAllUser();
        for(User user:userList){
            System.out.println(user);
        }
    }
}
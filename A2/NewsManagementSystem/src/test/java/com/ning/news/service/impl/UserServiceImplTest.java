package com.ning.news.service.impl;

import com.ning.news.BaseTest;
import com.ning.news.config.Config;
import com.ning.news.entity.User;
import com.ning.news.exception.AccountException;
import com.ning.news.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImplTest extends BaseTest {
    @Autowired
    UserService userService;

    @Test
    public void getUserById() {
        int id = 2;
        User user = null;
        try {
            user = userService.getUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(user);
    }

    @Test
    public void getUserByAccount() {
        String account = "2@qq.com";
        try {
            User user = userService.getUserByAccount(account);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAllUser() {
        try {
            List<User> userList = userService.getAllUser();
            System.out.println("all user");
            for(User user:userList){
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUserListByPermission() {
        String permission = Config.PRE_TEXT+Config.SENIOR_EDITOR;
        try {
            List<User> userList = userService.getUserListByPermission(permission);
            System.out.println("get permission");
            for(User user:userList){
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getBannedUserList() {
        try {
            int banned = Config.FREE_CODE;
            List<User> userList = userService.getBannedUserList(banned);
            System.out.println("get permission");
            for(User user:userList){
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void updateUser() {
        try {
            User user = userService.getUserById(2);
            user.setName(user.getName()+"up");
            int res = userService.updateUser(user);
            System.out.println("update"+res+userService.getUserById(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateUserByList() {
        try {
            List<User> userList = userService.getUserListByPermission(Config.ADMIN);
            for(User user:userList){
                System.out.println(user);
                user.setName(user.getName()+"lst");
            }
            userService.updateUserByList(userList);
            System.out.println("update");
            getAllUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteUserByAccount() {
        String account = "3@qq.com";
        try {
            int res = userService.deleteUserByAccount(account);
            System.out.println("delete"+res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteUserById() {
        int id = 1;
        try {
            int res = userService.deleteUserById(id);
            System.out.println("delete"+res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteUserByList() {
        List<User> userList = null;
        try {
            userList = userService.getAllUser();
            int len = userList.size();
            for(int i = 0; i < len-2; i++){
                userList.remove(0);
            }
            int res = userService.deleteUserByList(userList);
            System.out.println("delete list"+res);
            getAllUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void login() {
        String account = "1@qq.com",password = "1";
        User user = null;
        try {
            user = userService.login(account,password);
            System.out.println("login"+user);
            user = userService.login(" "," ");
            System.out.println("login"+user);
        } catch (AccountException e) {
            System.out.println(e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void register() {
        User user = new User("2@qq.com","123","Nick");
        try {
            user = userService.register(user);
            System.out.println(user);
            getAllUser();
            user = userService.register(user);
        } catch (AccountException e){
            System.out.println(e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
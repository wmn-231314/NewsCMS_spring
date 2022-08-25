package com.ning.news.mapper;

import com.ning.news.entity.User;

import java.util.List;

public interface UserMapper {
    int addUser(User user);

    User getUserByAccount(String account);
    User getUserById(int id);
    List<User> getUserListByPermission(String permission);
    List<User> getAllUser();
    List<User> getBannedUserList(int banned);

    int updateUser(User user);
    int updateUserByList(List<User> userList);

    int deleteUserByAccount(String account);
    int deleteUserById(int id);
    int deleteUserByList(List<User> userList);
}

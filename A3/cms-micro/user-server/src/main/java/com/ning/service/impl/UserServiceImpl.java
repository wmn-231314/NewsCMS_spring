package com.ning.service.impl;

import com.ning.exception.AccountException;
import com.ning.service.client.CommentClient;
import com.ning.service.client.NewsClient;
import com.ning.utils.Config;
import com.ning.entity.User;
//import com.ning.exception.AccountException;
//import com.ning.mapper.CommentMapper;
//import com.ning.mapper.NewsMapper;
import com.ning.mapper.UserMapper;
import com.ning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private CommentClient commentClient;

    @Resource
    private NewsClient newsClient;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    @Override
    public User getUserByAccount(String account) {
        return userMapper.getUserByAccount(account);
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public List<User> getUserListByPermission(String permission) {
        return userMapper.getUserListByPermission(permission);
    }

    @Override
    public List<User> getBannedUserList(int banned) throws Exception {
        return userMapper.getBannedUserList(banned);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int updateUserBan(int uid, int banned) throws Exception {
        User user = new User();
        user.setId(uid);
        user.setBanned(banned);
        return userMapper.updateUser(user);
    }

    @Override
    public int updateUserByList(List<User> userList) {
        return userMapper.updateUserByList(userList);
    }

    @Override
    public int deleteUserByAccount(String account) {
        return userMapper.deleteUserByAccount(account);
    }

    @Override
    public int deleteUserById(int id) {
        newsClient.deleteNewsByProviderId(id);
        commentClient.deleteByUserId(id);
        return userMapper.deleteUserById(id);
    }

    @Override
    public int deleteUserByList(List<User> userList) {
        return userMapper.deleteUserByList(userList);
    }

    @Override
    public User login(String account, String password) throws Exception{
        User user = userMapper.getUserByAccount(account);
        if(user == null){
            System.out.println(account);
            throw new AccountException("用户名不存在");
        }else if(user.getPassword().equals(password)){
            return user;
        }else{
            throw new AccountException("用户名或密码错误");
        }
    }

    @Override
    public User register(User user) throws Exception{
        user.setRegisterTime(new Date());
        user.setBanned(Config.FREE_CODE);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User testSame = userMapper.getUserByAccount(user.getAccount());
        if(testSame != null){
            throw new AccountException("用户名已存在");
        }else{
            userMapper.addUser(user);
            user = userMapper.getUserByAccount(user.getAccount());
            return user;
        }
    }
//
//    @Override
//    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
//        User user = userMapper.getUserByAccount(account);
//        if(user == null){
//            throw new UsernameNotFoundException(account + "not found");
//        }
//        return UserDetailsImpl.build(user);
//    }
}

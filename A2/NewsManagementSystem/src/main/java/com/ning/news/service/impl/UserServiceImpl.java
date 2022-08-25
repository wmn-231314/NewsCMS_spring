package com.ning.news.service.impl;

import com.ning.news.config.Config;
import com.ning.news.entity.User;
import com.ning.news.mapper.CommentMapper;
import com.ning.news.mapper.NewsMapper;
import com.ning.news.mapper.UserMapper;
import com.ning.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ning.news.exception.AccountException;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        newsMapper.deleteNewsByProviderId(id);
        commentMapper.deleteCommentByUser(id);
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User testSame = userMapper.getUserByAccount(user.getAccount());
        if(testSame != null){
            throw new AccountException("用户名已存在");
        }else{
            userMapper.addUser(user);
            user = userMapper.getUserByAccount(user.getAccount());
            return user;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        User user = userMapper.getUserByAccount(account);
        if(user == null){
            throw new UsernameNotFoundException(account + "not found");
        }
        return UserDetailsImpl.build(user);
    }
}

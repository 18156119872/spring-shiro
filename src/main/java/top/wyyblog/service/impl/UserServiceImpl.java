package top.wyyblog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wyyblog.dao.UserMapper;
import top.wyyblog.entity.User;
import top.wyyblog.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;


    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }
}

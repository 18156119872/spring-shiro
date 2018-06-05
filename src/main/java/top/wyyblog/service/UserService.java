package top.wyyblog.service;

import top.wyyblog.entity.User;

public interface UserService {

    User getUserByUsername(String username);
}

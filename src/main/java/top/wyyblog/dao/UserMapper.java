package top.wyyblog.dao;

import top.wyyblog.entity.User;

public interface UserMapper {

    User getUserByUsername(String username);
}

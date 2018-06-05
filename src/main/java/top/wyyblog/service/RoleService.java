package top.wyyblog.service;

import java.util.List;

public interface RoleService {

    List<String> getAllRoleSn();


    List<String> getRoleSnByUserId(Long userId);
}

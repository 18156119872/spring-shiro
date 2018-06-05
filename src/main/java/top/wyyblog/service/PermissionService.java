package top.wyyblog.service;

import top.wyyblog.entity.Permission;

import java.util.List;

public interface PermissionService {

    void save(Permission permission);


    List<String> getPermissionResourceByUserId(Long userId);

    List<String> getAllPermissionResource();
}

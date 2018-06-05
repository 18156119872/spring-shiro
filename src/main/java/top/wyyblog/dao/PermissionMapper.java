package top.wyyblog.dao;

import top.wyyblog.entity.Permission;

import java.util.List;

public interface PermissionMapper {

    /**
     * 保存权限对象
     * @param permission
     */
    void save(Permission permission);

    /**
     * 获取员工的权限表达式
     * @param userId
     * @return
     */
    List<String> getPermissionResourceByUserId(Long userId);


    /**
     * 获取所有的权限
     * @return
     */
    List<String> getAllPermissionResource();
}

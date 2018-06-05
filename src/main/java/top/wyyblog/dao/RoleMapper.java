package top.wyyblog.dao;

import java.util.List;

public interface RoleMapper {

    /**
     * 查询所有的角色
     * @return
     */
    List<String> getAllRoleSn();

    /**
     * 查询用户id获取角色表达式
     * @param userId
     * @return
     */
    List<String> getRoleSnByUserId(Long userId);
}

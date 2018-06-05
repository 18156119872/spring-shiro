package top.wyyblog.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wyyblog.dao.RoleMapper;
import top.wyyblog.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<String> getAllRoleSn() {
        return roleMapper.getAllRoleSn();
    }

    @Override
    public List<String> getRoleSnByUserId(Long userId) {
        return roleMapper.getRoleSnByUserId(userId);
    }
}

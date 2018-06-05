package top.wyyblog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wyyblog.dao.PermissionMapper;
import top.wyyblog.entity.Permission;
import top.wyyblog.service.PermissionService;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public void save(Permission permission) {
        permissionMapper.save(permission);
    }

    @Override
    public List<String> getPermissionResourceByUserId(Long userId) {
        return permissionMapper.getPermissionResourceByUserId(userId);
    }

    @Override
    public List<String> getAllPermissionResource() {
        return permissionMapper.getAllPermissionResource();
    }
}

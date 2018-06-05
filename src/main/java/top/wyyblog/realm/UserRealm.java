package top.wyyblog.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import top.wyyblog.dao.UserMapper;
import top.wyyblog.entity.Role;
import top.wyyblog.entity.User;
import top.wyyblog.service.PermissionService;
import top.wyyblog.service.RoleService;
import top.wyyblog.service.UserService;
import top.wyyblog.service.impl.UserServiceImpl;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class UserRealm extends AuthorizingRealm{


    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;


    @Override
    public String getName() {
        return "UserRealm";
    }

    //授权操作
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        User user = (User)principalCollection.getPrimaryPrincipal();
        List<String> permissions = new ArrayList<>();

        List<String> roles = new ArrayList<>();
        //超级管理员
        if("admin".equals(user.getUsername())){
            //拥有所有权限
            permissions.add("*:*");
            //拥有所有角色
            roles = roleService.getAllRoleSn();
        }else{
            //根据用户id查询所具有的角色
            roles = roleService.getRoleSnByUserId(Long.valueOf(user.getId()));
            //据用户id查询所具有的权限
            permissions = permissionService.getPermissionResourceByUserId(Long.valueOf(user.getId()));

//            System.out.println("角色："+roles+"，权限："+permissions);
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);
        info.addRoles(roles);
        return info;
    }

    //认证操作
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //从token中获取登录用户名，查询数据库并返回信息
        String username = (String) authenticationToken.getPrincipal();
        User user = userService.getUserByUsername(username);
        System.out.println(user);
        if(user == null){
            return null;
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        return info;
    }


    //清楚缓存
    public void clearCached(){
        PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principalCollection);
    }
}

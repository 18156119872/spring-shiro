package top.wyyblog.controller;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import top.wyyblog.entity.Permission;
import top.wyyblog.realm.PermissionName;
import top.wyyblog.service.PermissionService;

import java.util.Collection;
import java.util.List;
import java.util.Map;


@Controller
public class PermissionController {


    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/reload")
    public String reload() throws Exception{
        //加载系统中所有的权限表达式到数据库

        //从数据库中查询出所有的权限表达式并对比去重，如果已经存在不添加
        List<String> list = permissionService.getAllPermissionResource();


        //1.获取controller中所有带有@RequestMapping标签的方法
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        Collection<HandlerMethod> methods = handlerMethods.values();
        for (HandlerMethod method:methods) {
            //2.遍历所有的方法，判断当前的方法是否有@RequestPermissions权限控制标签
            RequiresPermissions annotation = method.getMethodAnnotation(RequiresPermissions.class);
            if(annotation != null){
                //权限表达式
                String resource = annotation.value()[0];
                //去重
                if(list.contains(resource)){
                    continue;
                }
                //3.如果有，解析得到的权限表达式，封装成Permission对象保存到Permission表中
                Permission mypermission = new Permission();
                mypermission.setResource(resource);
                //设置权限名称
                mypermission.setName(method.getMethodAnnotation(PermissionName.class).value());
                //保存到数据库
                permissionService.save(mypermission);
            }
        }
        return "main";
    }

}

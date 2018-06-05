package top.wyyblog.controller;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import top.wyyblog.realm.PermissionName;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @RequestMapping("")
    @RequiresPermissions("employee:list")
    @PermissionName("员工列表")
    public String index(){
        System.out.println("执行了员工列表");
        return "employee";
    }

    @RequestMapping("/save")
    @RequiresPermissions("employee:save")
    @PermissionName("员工保存")
    public String save() throws Exception{
        System.out.println("执行了员工save");
        return "employee";
    }

    @RequestMapping("/edit")
    @RequiresPermissions("employee:edit")
    @PermissionName("员工编辑")
    public String edit() throws Exception{
        System.out.println("执行了员工edit");
        return "employee";
    }

    @RequestMapping("/delete")
    @RequiresPermissions("employee:delete")
    @PermissionName("员工删除")
    public String delete() throws Exception{
        System.out.println("执行了员工delete");
        return "employee";
    }
}

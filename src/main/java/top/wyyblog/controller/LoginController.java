package top.wyyblog.controller;


import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController{
    @RequestMapping("/login")
    public String login(Model model, HttpServletRequest request){
        //如果登录失败从request中获取认证异常信息，ShiroLoginFailure就是Shiro异常类的全限定名
        System.out.println("进入Controller");
        String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
        System.out.println(exceptionClassName);
        if(exceptionClassName != null){
            if(UnknownAccountException.class.getName().equals(exceptionClassName)){
                //最终会抛给异常处理器
                model.addAttribute("errorMsg","账号不存在");
                System.out.println("账号不存在");
            } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)){
                model.addAttribute("errorMsg","用户名/密码错误");
                System.out.println("用户名/密码错误");
            } else {
                //未知登录错误
                model.addAttribute("errorMsg","其他异常信息");
                System.out.println("其他异常信息");
            }
        }
        //此方法不处理登陆成功（认证成功），shiro认证成功会跳转到上一个请求的路径
        //登录失败返回login界面
        return "forward:/login.jsp";
    }
}

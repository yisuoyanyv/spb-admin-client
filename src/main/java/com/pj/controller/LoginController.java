package com.pj.controller;

import cn.dev33.satoken.annotation.*;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.pj.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录测试
 * @author kong
 *
 */
@RestController
@RequestMapping("/acc/")
public class LoginController {

    @Autowired
    BaseServiceImpl baseService;

    // 测试登录  ---- http://localhost:8081/acc/doLogin?name=zhang&pwd=123456
    @SaCheckLogin
    @RequestMapping("doLogin")
    public SaResult doLogin(String name, String pwd) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if("zhang".equals(name) && "123456".equals(pwd)) {
            StpUtil.login(10001);
            return SaResult.ok("登录成功");
        }
        return SaResult.error("登录失败");
    }

    // 查询登录状态  ---- http://localhost:8081/acc/isLogin
    @RequestMapping("isLogin")
    public SaResult isLogin() {
        return SaResult.ok("是否登录：" + StpUtil.isLogin());
    }

    // 查询 Token 信息  ---- http://localhost:8081/acc/tokenInfo
    @RequestMapping("tokenInfo")
    public SaResult tokenInfo() {
        return SaResult.data(StpUtil.getTokenInfo());
    }

    // 测试注销  ---- http://localhost:8081/acc/logout
    @RequestMapping("logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }


    // 登录认证：只有登录之后才能进入该方法
    @SaCheckLogin
    @RequestMapping("info")
    public String info() {
        return "查询用户信息";
    }

    // 角色认证：必须具有指定角色才能进入该方法
    @SaCheckRole("super-admin")
    @RequestMapping("addWithRole")
    public String addWithRole() {
        return "用户增加";
    }

    // 权限认证：必须具有指定权限才能进入该方法
    @SaCheckPermission("user-add")
    @RequestMapping("addWithPermission")
    public String addWithPermission() {
        return "用户增加";
    }


    // 权限认证：必须具有指定权限才能进入该方法
    @RequestMapping("addWithNoPermission")
    public String addWithNoPermission() {
        return baseService.addTest();
    }



    // 二级认证：必须二级认证之后才能进入该方法
    @SaCheckSafe()
    @RequestMapping("addWithCheckSafe")
    public String addWithCheckSafe() {
        return "用户增加";
    }

    // Http Basic 认证：只有通过 Basic 认证后才能进入该方法
    @SaCheckBasic(account = "sa:123456")
    @RequestMapping("addWithCheckBasic")
    public String addWithCheckBasic() {
        return "用户增加";
    }


}

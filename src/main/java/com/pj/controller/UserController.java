package com.pj.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangjinglong
 * @date 2022-01-05-10:09 PM
 */
@RestController
@RequestMapping("/user/")
public class UserController {

    // 测试登录，浏览器访问： http://localhost:8081/user/doLogin?username=zhang&password=123456
    @RequestMapping("doLogin")
    public String doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            return "登录成功";
        }
        return "登录失败";
    }

    // 查询登录状态，浏览器访问： http://localhost:8080/user/isLogin
    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

    // 查询登录状态，浏览器访问： http://localhost:8080/user/loginInfo
    @RequestMapping("loginInfo")
    public String loginInfo() {
        // 获取当前会话账号id, 如果未登录，则返回默认值 （`defaultValue`可以为任意类型）
        String sessionId = StpUtil.getLoginId("未登录");

        // 获取指定token对应的账号id，如果未登录，则返回 null
        //        StpUtil.getLoginIdByToken(String tokenValue);

        // 获取当前`StpLogic`的token名称
        String tokenName = StpUtil.getTokenName();

        // 获取当前会话的token值
        String tokenValue = StpUtil.getTokenValue();

        // 获取当前会话的token信息参数
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        String loginType = tokenInfo.getLoginType();

//        return "当前会话ID：" + sessionId;
        return "tokenName：" + sessionId+" \n tokenValue:"+tokenValue
        +" \n loginType:"+loginType;
    }

}

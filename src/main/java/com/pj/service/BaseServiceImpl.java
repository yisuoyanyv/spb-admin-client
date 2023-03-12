package com.pj.service;

import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.stereotype.Service;

/**
 * @author zhangjinglong
 * @date 2022-01-06-10:25 PM
 */

@Service
public class BaseServiceImpl {
    @SaCheckPermission("user-xxx") //这样加注解不起作用
    public String addTest(){
        return "注解加在非RequestMapping方法上";
    }
}

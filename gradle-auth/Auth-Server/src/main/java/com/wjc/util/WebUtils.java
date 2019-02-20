package com.wjc.util;

import com.wjc.entity.sys.SysUser;
import org.apache.shiro.SecurityUtils;

/**
 * @author: wangjc
 * 2019/1/4
 */
public class WebUtils {
    public static SysUser getCurrentUser(){
        if (SecurityUtils.getSubject().isAuthenticated()) {
           return (SysUser)SecurityUtils.getSubject().getPrincipal();
        }
        return null;
    }
}

package com.wjc.vo.sys;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.Objects;

/**
 * created by wangjc at 2019/12/27 16:14<br>
 *     用于存储角色或权限的值
 */
public class AuthVo implements Serializable {
    private String name;//显示名
    private String val;//值

    public AuthVo() {
    }

    public AuthVo(String val) {
        this.val = val;
    }

    public AuthVo(String name, String val) {
        this.name = name;
        this.val = val;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthVo authVo = (AuthVo) o;
        return Objects.equals(val, authVo.val);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

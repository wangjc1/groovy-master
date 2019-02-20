package com.wjc.util;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * created by wangjc at 2019/12/27 10:15<br>
 */
public class PageUtils {

    /**
     * 获取分页参数
     *
     * @param json
     * @return
     */
    public static Page getPageParam(JSONObject json) {
        int current = json.getIntValue("current");
        int size = json.getIntValue("size");
        if (current == 0) current = 1;
        if (size == 0) size = 10;
        return new Page(current, size);
    }

    public static Page getPageParam(int current,int size) {
        if (current == 0) current = 1;
        if (size == 0) size = 10;
        return new Page(current, size);
    }

}

package com.ts.api.service.impl;

import com.ts.api.service.IDemoService;

/**
 * 服务测试
 * @author: Owen Jia
 * @time: 2018/11/26 15:09
 */
public class DemoServiceImpl implements IDemoService {

    @Override
    public void justForTest(int code) {
        System.out.println("i am a test code of api. code=" + code);
    }

}

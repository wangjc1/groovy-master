package com.ts.util;

import com.ts.api.service.IDemoService;
import com.ts.api.service.impl.DemoServiceImpl;
import org.junit.Test;

/**
 * @author: Owen Jia
 * @time: 2018/11/26 17:54
 */
public class MainTest {

    @Test
    public void test1(){
        IDemoService demoService = new DemoServiceImpl();
        demoService.justForTest(1010);
    }
}

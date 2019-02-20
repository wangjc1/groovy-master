package com.ts.api.service.impl;

import com.ts.api.service.ICodeService;
import org.junit.Test;

/**
 * @author: Owen Jia
 * @time: 2018/11/26 15:23
 */
public class DemoServiceImplTest {

    @Test
    public void test1(){
        DemoServiceImpl demoService = new DemoServiceImpl();
        demoService.justForTest(1010);

        ICodeService codeService = new CodeServiceImpl();
        codeService.sendPhoneCode("15800001111");
    }
}

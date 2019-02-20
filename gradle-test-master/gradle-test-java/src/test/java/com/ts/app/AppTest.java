package com.ts.app;

import org.junit.Test;

/**
 * @author: Owen Jia
 * @time: 2018/11/23 16:17
 */
public class AppTest {

    @Test
    public void test1(){
        App app = new App();
        String tmp = app.getGreeting();
        System.out.println("Greeting result is " +tmp);
    }

}

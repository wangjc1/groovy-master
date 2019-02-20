package com.ts.main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Demo
 * @author: Owen Jia
 * @time: 2018/11/27 17:03
 */
@RestController
@RequestMapping(value = "demo")
public class DemoController {

    @GetMapping(value = "say1")
    @ResponseBody
    public Object sayHello(){
        class T{
            String aa;
            String bb;

            public T(String aa, String bb) {
                this.aa = aa;
                this.bb = bb;
            }

            public String getAa() {
                return aa;
            }

            public void setAa(String aa) {
                this.aa = aa;
            }

            public String getBb() {
                return bb;
            }

            public void setBb(String bb) {
                this.bb = bb;
            }
        }

        T t = new T("aa","bb");
        return t;
    }
}

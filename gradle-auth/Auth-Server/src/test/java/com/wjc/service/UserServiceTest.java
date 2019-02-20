package com.wjc.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.wjc.service.sys.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void queryOfpage(){
        sysUserService.queryUserIncludeRoles(new Page(1,10),"wang");
    }

}

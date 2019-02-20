package com.wjc.service.sys.impl;

import com.wjc.dao.sys.SysUserMapper;
import com.wjc.entity.sys.SysUser;
import com.wjc.service.sys.SysUserService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public boolean insert(SysUser entity) {
        super.insert(entity);
        return true;
    }


    @Override
    public Page<SysUser> queryUserIncludeRoles(Page page, String nick) {
        return page.setRecords(baseMapper.selectUserIncludeRoles(page,nick));
    }

}

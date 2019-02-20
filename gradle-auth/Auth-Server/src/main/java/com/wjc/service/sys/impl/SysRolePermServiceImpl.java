package com.wjc.service.sys.impl;

import com.wjc.dao.sys.SysRolePermMapper;
import com.wjc.entity.sys.SysRolePerm;
import com.wjc.service.sys.SysRolePermService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SysRolePermServiceImpl extends ServiceImpl<SysRolePermMapper, SysRolePerm> implements SysRolePermService {
}

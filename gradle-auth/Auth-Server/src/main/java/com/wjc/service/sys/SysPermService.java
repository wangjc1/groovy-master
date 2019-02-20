package com.wjc.service.sys;

import com.wjc.entity.sys.SysPerm;
import com.wjc.vo.sys.AuthVo;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Set;

public interface SysPermService extends IService<SysPerm> {

    Set<AuthVo> getPermsByUserId(Long userId);

    List<SysPerm> getPermsByRoleId(Long roleId);

    void saveOrUpdate(List<SysPerm> perms);



}

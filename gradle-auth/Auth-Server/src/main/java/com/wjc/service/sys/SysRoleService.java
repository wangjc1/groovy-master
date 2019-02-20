package com.wjc.service.sys;

import com.wjc.entity.sys.SysRole;
import com.wjc.vo.sys.AuthVo;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Set;

public interface SysRoleService extends IService<SysRole> {

    Set<AuthVo> getRolesByUserId(Long userId);

    List<Long> getRoleIdsByUserId(Long userId);

    boolean checkRidsContainRval(List<Long> rids, String rval);

    boolean checkUidContainRval(Long uid, String rval);

}

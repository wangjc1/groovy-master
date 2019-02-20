package com.wjc.dao.sys;

import com.wjc.entity.sys.SysPerm;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysPermMapper extends BaseMapper<SysPerm> {

    List<SysPerm> getPermsByUserId(@Param("userId") Long userId);

    List<SysPerm> getPermsByRoleId(@Param("roleId") Long roleId);

    void saveOrUpdate(@Param("perms") List<SysPerm> perms);



}

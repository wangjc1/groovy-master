package com.wjc.dao.sys;

import com.wjc.entity.sys.SysRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> getRolesByUserId(@Param("userId") Long userId);

    List<Long> getRoleIdsByUserId(@Param("userId") Long userId);

    Boolean checkRidsContainRval(@Param("rids")List<Long> rids,@Param("rval")String rval);

    Boolean checkUidContainRval(@Param("uid")Long uid,@Param("rval")String rval);

}

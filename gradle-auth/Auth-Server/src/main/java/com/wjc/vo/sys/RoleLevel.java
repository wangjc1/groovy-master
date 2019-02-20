package com.wjc.vo.sys;


import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

/**
 * 角色级别
 * @author: wangjc
 * 2019/1/2
 */
public enum RoleLevel implements IEnum {
  System("系统级",0), Platform("平台级",1), Company("企业级",2);

  private String name;
  private int level;

  RoleLevel(String name, int level) {
    this.name = name;
    this.level = level;
  }

  public String getName() {
    return name;
  }

  public int getLevel() {
    return level;
  }

  @Override
  public Serializable getValue() {
    return level;
  }
}

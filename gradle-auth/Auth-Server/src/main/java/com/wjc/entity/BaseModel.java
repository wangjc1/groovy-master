package com.wjc.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author: wangjc
 * 2019/1/2
 */
@Data
public class BaseModel<E extends BaseModel> extends Model<E> {
  /**
   * 主键
   */
  @TableId(value = "id", type = IdType.AUTO)
  protected Long id;

  /**
   * ts_insert
   */
  protected Timestamp tsInsert = new Timestamp(System.currentTimeMillis());
  /**
   * dr
   */
  protected Boolean dr;
  /**
   * ts_update
   */
  protected Timestamp tsUpdate;

  @Override
  protected Serializable pkVal() {
    return id;
  }
}

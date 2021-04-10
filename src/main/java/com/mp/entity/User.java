package com.mp.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @program: plus
 * @description
 * @author: xiangyuyi
 * @create: 2021-03-29 17:05
 **/
@Data
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    //@TableField(strategy = FieldStrategy.DEFAULT)
    protected String name;

    private String email;

    private Integer age;

    private Long managerId;

    private LocalDateTime createTime;
}

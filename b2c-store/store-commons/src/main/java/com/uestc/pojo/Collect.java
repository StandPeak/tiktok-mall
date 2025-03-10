package com.uestc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author llf
 * @school uestc
 */
@Data
@TableName("collect")
public class Collect implements Serializable {
    public static final Long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @JsonProperty("user_id")
    @TableField("user_id")
    private Integer userId;

    @TableField("product_id")
    @JsonProperty("product_id")
    private Integer productId;

    @TableField("collect_time")
    @JsonProperty("collect_time")
    private Long collectTime;
}

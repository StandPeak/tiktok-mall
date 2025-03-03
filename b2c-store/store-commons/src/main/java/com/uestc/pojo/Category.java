package com.uestc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author llf
 * @school uestc
 */
@TableName("category")
@Data
public class Category implements Serializable {

    @JsonProperty("category_id")
    @TableId(type = IdType.AUTO)
    private Integer categoryId;
    @JsonProperty("category_name")
    private String categoryName;
}

package com.uestc.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author llf
 * @school uestc
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("role")
public class Role {

    @TableId(value = "role_id", type = IdType.AUTO)
    private String roleId;

    @TableField(value = "role_name")
    private String roleName;

    @TableField(value = "role_user_name")
    private String userName;
}

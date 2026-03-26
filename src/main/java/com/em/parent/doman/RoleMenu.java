package com.em.parent.doman;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@TableName("role_menu")
@Data
public class RoleMenu {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String roleId;
    private String menuId;
    private LocalDateTime createdAt;
}
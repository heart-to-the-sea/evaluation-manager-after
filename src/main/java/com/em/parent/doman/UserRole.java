package com.em.parent.doman;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@TableName("user_role")
@Data
public class UserRole {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;
    private Long roleId;
    private LocalDateTime createdAt;
}

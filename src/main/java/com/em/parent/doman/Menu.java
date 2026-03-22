package com.em.parent.doman;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@TableName("menu")
@Data
public class Menu {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private Long parentId;
    private String name;
    private String path;
    private String type;
    private String icon;
    private Integer sort;
    private String permission;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

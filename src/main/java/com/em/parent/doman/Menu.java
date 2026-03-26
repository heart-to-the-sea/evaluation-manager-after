package com.em.parent.doman;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("menu")
@Data
public class Menu {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String parentId;

    private String key;

    private String label;

    private String i18nKey;

    private String routeKey;

    private String routePath;
}
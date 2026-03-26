package com.em.parent.doman.vo;

import lombok.Data;

import java.util.List;

@Data
public class MenuVo {
    private String id;
    private String parentId;
    private String key;
    private String label;
    private String i18nKey;
    private String routeKey;
    private String routePath;
    private List<MenuVo> children;
}
package com.em.parent.doman.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MenuVo {
    private Long id;
    private Long parentId;
    private String name;
    private String path;
    private String type;
    private String icon;
    private Integer sort;
    private String permission;
    private Integer status;
    private String statusLabel;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<MenuVo> children;
}

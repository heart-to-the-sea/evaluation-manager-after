package com.em.parent.doman.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RoleVo {
    private String id;
    private String name;
    private String code;
    private String description;
    private Integer status;
    private String statusLabel;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<MenuVo> menus;
}
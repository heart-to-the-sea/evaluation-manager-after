package com.em.parent.doman.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DictVo {
    private Long id;
    private String name;
    private String code;
    private String description;
    private Integer status;
    private String statusLabel;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

package com.em.parent.doman.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DictValuesVo {
    private Long id;
    private Long dictId;
    private String dictKey;
    private String label;
    private String value;
    private Integer sort;
    private Integer status;
    private String statusLabel;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

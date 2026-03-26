package com.em.parent.doman.vo;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
public class DictVo {
    private String id;
    private String name;
    private String code;
    private String description;
    private Integer status;
    private String statusLabel;
    private String dictTemplateStr;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
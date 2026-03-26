package com.em.parent.doman.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DictValuesBo extends PageBo {
    private Long id;
    private Long dictId;
    private String dictKey;
    private String label;
    private String value;
    private Integer sort;
    private Integer status;
}
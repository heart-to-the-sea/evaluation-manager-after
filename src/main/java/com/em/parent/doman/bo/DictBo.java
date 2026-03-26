package com.em.parent.doman.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DictBo extends PageBo {
    private String id;
    private String name;
    private String code;
    private String description;
    private Integer status;
    private String dictTemplateStr;
}
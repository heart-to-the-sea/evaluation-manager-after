package com.em.parent.doman.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DictValuesBo extends PageBo {
    private Long dictId;
    private String label;
    private String value;
}

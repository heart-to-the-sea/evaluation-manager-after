package com.em.parent.doman.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleBo extends PageBo {
    private String name;
    private String code;
}

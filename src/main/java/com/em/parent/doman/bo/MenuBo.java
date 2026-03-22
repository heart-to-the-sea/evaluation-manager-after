package com.em.parent.doman.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MenuBo extends PageBo {
    private String name;
    private String path;
    private String type;
}

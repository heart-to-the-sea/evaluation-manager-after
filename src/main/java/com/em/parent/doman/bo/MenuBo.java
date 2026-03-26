package com.em.parent.doman.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MenuBo extends PageBo {
    private String id;
    private String parentId;
    private String key;
    private String label;
    private String i18nKey;
    private String routeKey;
    private String routePath;
}
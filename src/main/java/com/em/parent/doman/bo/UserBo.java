package com.em.parent.doman.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserBo extends PageBo {
    private String id;
    private String account;
    private String username;
    private String password;
}
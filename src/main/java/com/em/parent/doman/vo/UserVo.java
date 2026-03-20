package com.em.parent.doman.vo;

import lombok.Data;

@Data
public class UserVo {
    private Long id;
    private String account;
    private String password;
    private String username;
    private String disableFlag;
    private String delFlag;
}

package com.em.parent.doman.vo;

import com.em.parent.doman.User;
import lombok.Data;

@Data
public class UserVo {
    private String id;
    private String account;
    private String password;
    private String username;
    private String disableFlag;
    private String delFlag;
    private String delFlagLabel;
}
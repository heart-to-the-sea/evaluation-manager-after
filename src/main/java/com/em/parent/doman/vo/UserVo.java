package com.em.parent.doman.vo;

import com.em.parent.doman.User;
import lombok.Data;
//import org.dromara.core.trans.anno.Trans;
//import org.dromara.core.trans.constant.TransType;

@Data
public class UserVo {
    private Long id;
    private String account;
    private String password;
    private String username;
    private String disableFlag;
    private String delFlag;
    private String delFlagLabel;
}

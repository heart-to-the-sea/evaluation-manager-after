package com.em.parent.service;

import com.em.parent.common.R;
import com.em.parent.doman.bo.UserBo;
import com.em.parent.doman.vo.AuthVo;

import java.util.Map;

public interface AuthService {
    R<AuthVo> login(UserBo userBo);
    R<Void> logout();
    R<AuthVo> refresh();
    R<Map<String,Object>> getAuthInfo();
}

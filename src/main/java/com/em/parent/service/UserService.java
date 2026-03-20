package com.em.parent.service;

import com.em.parent.common.R;
import com.em.parent.doman.vo.UserVo;

import java.util.List;

public interface UserService {
    R<List<UserVo>> pageList();
}

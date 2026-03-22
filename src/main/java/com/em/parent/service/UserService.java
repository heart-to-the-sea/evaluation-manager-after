package com.em.parent.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.em.parent.common.R;
import com.em.parent.doman.vo.UserVo;

import java.util.List;

public interface UserService {
    R<Page<UserVo>> pageList();
}

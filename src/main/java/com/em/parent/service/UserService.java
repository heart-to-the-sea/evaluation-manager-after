package com.em.parent.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.em.parent.common.R;
import com.em.parent.doman.bo.UserBo;
import com.em.parent.doman.vo.UserVo;

public interface UserService {
    R<Page<UserVo>> pageList(UserBo bo);
    R<UserVo> getById(Long id);
    R<Void> add(UserVo userVo);
    R<Void> update(UserVo userVo);
    R<Void> delete(Long id);
}

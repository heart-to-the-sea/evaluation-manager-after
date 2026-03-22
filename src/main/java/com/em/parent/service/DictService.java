package com.em.parent.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.em.parent.common.R;
import com.em.parent.doman.bo.DictBo;
import com.em.parent.doman.vo.DictVo;

public interface DictService {
    R<Page<DictVo>> pageList(DictBo bo);
    R<Void> add(DictVo dictVo);
    R<Void> update(DictVo dictVo);
    R<Void> delete(Long id);
}

package com.em.parent.service;

import com.em.parent.common.R;
import com.em.parent.doman.vo.DictVo;

public interface DictService {
    R<?> pageList();
    R<Void> add(DictVo dictVo);
    R<Void> update(DictVo dictVo);
    R<Void> delete(Long id);
}

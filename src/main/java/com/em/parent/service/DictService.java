package com.em.parent.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.em.parent.common.R;
import com.em.parent.doman.bo.DictBo;
import com.em.parent.doman.vo.DictVo;

public interface DictService {
    R<Page<DictVo>> pageList(DictBo bo);
    R<Void> add(DictBo bo);
    R<Void> update(DictBo bo);
    R<Void> delete(String id);
}
package com.em.parent.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.em.parent.common.R;
import com.em.parent.doman.bo.DictValuesBo;
import com.em.parent.doman.vo.DictValuesVo;

import java.util.List;

public interface DictValuesService {
    R<Page<DictValuesVo>> pageList(DictValuesBo bo);
    R<List<DictValuesVo>> listByDictCode(String dictCode);
    R<Void> add(DictValuesBo bo);
    R<Void> update(DictValuesBo bo);
    R<Void> delete(Long id);
}
package com.em.parent.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.em.parent.common.R;
import com.em.parent.doman.vo.DictValuesVo;

import java.util.List;

public interface DictValuesService {
    R<Page<DictValuesVo>> pageList(Long dictId);
    R<List<DictValuesVo>> listByDictCode(String dictCode);
    R<Void> add(DictValuesVo dictValuesVo);
    R<Void> update(DictValuesVo dictValuesVo);
    R<Void> delete(Long id);
}

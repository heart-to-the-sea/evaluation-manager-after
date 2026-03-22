package com.em.parent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.em.parent.common.R;
import com.em.parent.doman.Dict;
import com.em.parent.doman.DictValues;
import com.em.parent.doman.vo.DictValuesVo;
import com.em.parent.mapper.DictMapper;
import com.em.parent.mapper.DictValuesMapper;
import com.em.parent.service.DictValuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictValuesServiceImpl implements DictValuesService {
    @Autowired
    DictValuesMapper dictValuesMapper;
    @Autowired
    DictMapper dictMapper;

    @Override
    public R<Page<DictValuesVo>> pageList(Long dictId) {
        Page<DictValues> page = new Page<>();
        QueryWrapper<DictValues> wrapper = new QueryWrapper<>();
        if (dictId != null) {
            wrapper.eq("dict_id", dictId);
        }
        return R.ok(dictValuesMapper.selectVoPage(page, wrapper));
    }

    @Override
    public R<List<DictValuesVo>> listByDictCode(String dictCode) {
        Dict dict = dictMapper.selectOne(new QueryWrapper<Dict>().eq("code", dictCode));
        if (dict == null) {
            return R.ok(List.of());
        }
        List<DictValuesVo> list = dictValuesMapper.selectVoList(new QueryWrapper<DictValues>().eq("dict_id", dict.getId()));
        return R.ok(list);
    }

    @Override
    public R<Void> add(DictValuesVo dictValuesVo) {
        DictValues dictValues = new DictValues();
        dictValues.setDictId(dictValuesVo.getDictId());
        dictValues.setLabel(dictValuesVo.getLabel());
        dictValues.setValue(dictValuesVo.getValue());
        dictValues.setSort(dictValuesVo.getSort());
        dictValues.setStatus(dictValuesVo.getStatus());
        dictValuesMapper.insert(dictValues);
        return R.ok();
    }

    @Override
    public R<Void> update(DictValuesVo dictValuesVo) {
        DictValues dictValues = new DictValues();
        dictValues.setId(dictValuesVo.getId());
        dictValues.setDictId(dictValuesVo.getDictId());
        dictValues.setLabel(dictValuesVo.getLabel());
        dictValues.setValue(dictValuesVo.getValue());
        dictValues.setSort(dictValuesVo.getSort());
        dictValues.setStatus(dictValuesVo.getStatus());
        dictValuesMapper.updateById(dictValues);
        return R.ok();
    }

    @Override
    public R<Void> delete(Long id) {
        dictValuesMapper.deleteById(id);
        return R.ok();
    }
}

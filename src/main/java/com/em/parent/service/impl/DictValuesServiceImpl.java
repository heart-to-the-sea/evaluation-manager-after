package com.em.parent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.em.parent.common.R;
import com.em.parent.doman.Dict;
import com.em.parent.doman.DictValues;
import com.em.parent.doman.bo.DictValuesBo;
import com.em.parent.doman.vo.DictValuesVo;
import com.em.parent.mapper.DictMapper;
import com.em.parent.mapper.DictValuesMapper;
import com.em.parent.service.DictValuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class DictValuesServiceImpl implements DictValuesService {
    @Autowired
    DictValuesMapper dictValuesMapper;
    @Autowired
    DictMapper dictMapper;

    @Override
    public R<Page<DictValuesVo>> pageList(DictValuesBo bo) {
        Page<DictValues> page = new Page<>(bo.getPageNum(), bo.getPageSize());
        LambdaQueryWrapper<DictValues> wrapper = new LambdaQueryWrapper<>();
        if (bo.getDictId() != null) {
            wrapper.eq(DictValues::getDictId, bo.getDictId());
        }
        if (StringUtils.hasText(bo.getLabel())) {
            wrapper.like(DictValues::getLabel, bo.getLabel());
        }
        if (StringUtils.hasText(bo.getValue())) {
            wrapper.like(DictValues::getValue, bo.getValue());
        }
        wrapper.orderByDesc(DictValues::getId);
        return R.ok(dictValuesMapper.selectVoPage(page, wrapper));
    }

    @Override
    public R<List<DictValuesVo>> listByDictCode(String dictCode) {
        Dict dict = dictMapper.selectOne(new LambdaQueryWrapper<Dict>().eq(Dict::getCode, dictCode));
        if (dict == null) {
            return R.ok(List.of());
        }
        List<DictValuesVo> list = dictValuesMapper.selectVoList(
                new LambdaQueryWrapper<DictValues>().eq(DictValues::getDictId, dict.getId()));
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

package com.em.parent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.em.parent.common.R;
import com.em.parent.doman.Dict;
import com.em.parent.doman.DictValues;
import com.em.parent.doman.bo.DictBo;
import com.em.parent.doman.vo.DictVo;
import com.em.parent.mapper.DictMapper;
import com.em.parent.mapper.DictValuesMapper;
import com.em.parent.service.DictService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class DictServiceImpl implements DictService {
    @Autowired
    DictMapper dictMapper;
    @Autowired
    DictValuesMapper dictValuesMapper;

    @Override
    public R<Page<DictVo>> pageList(DictBo bo) {
        Page<Dict> page = new Page<>(bo.getPageNum(), bo.getPageSize());
        LambdaQueryWrapper<Dict> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(bo.getName())) {
            wrapper.like(Dict::getName, bo.getName());
        }
        if (StringUtils.hasText(bo.getCode())) {
            wrapper.like(Dict::getCode, bo.getCode());
        }
        wrapper.orderByDesc(Dict::getId);
        return R.ok(dictMapper.selectVoPage(page, wrapper));
    }

    @Override
    public R<Void> add(DictBo bo) {
        Dict dict = new Dict();
        BeanUtils.copyProperties(bo, dict);
        dictMapper.insert(dict);

        if (StringUtils.hasText(bo.getDictTemplateStr())) {
            List<DictValues> valuesList = parseDictTemplate(String.valueOf(dict.getId()), bo.getDictTemplateStr());
            if (!valuesList.isEmpty()) {
                for (DictValues dv : valuesList) {
                    dictValuesMapper.insert(dv);
                }
            }
        }
        return R.ok();
    }

    @Override
    public R<Void> update(DictBo bo) {
        Dict dict = new Dict();
        BeanUtils.copyProperties(bo, dict);
        dictMapper.updateById(dict);
        return R.ok();
    }

    @Override
    public R<Void> delete(String id) {
        dictMapper.deleteById(id);
        return R.ok();
    }

    private List<DictValues> parseDictTemplate(String dictId, String templateStr) {
        List<DictValues> list = new ArrayList<>();
        if (templateStr == null || templateStr.trim().isEmpty()) {
            return list;
        }
        String[] lines = templateStr.trim().split("\n");
        int sort = 0;
        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] parts = line.split("\\s+", 2);
            DictValues dv = new DictValues();
            dv.setDictId(dictId);
            dv.setDictKey(String.valueOf(sort));
            dv.setLabel(parts[0]);
            dv.setValue(parts.length > 1 ? parts[1] : parts[0]);
            dv.setSort(sort);
            dv.setStatus(1);
            list.add(dv);
            sort++;
        }
        return list;
    }
}
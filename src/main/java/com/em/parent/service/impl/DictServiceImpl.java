package com.em.parent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.em.parent.common.R;
import com.em.parent.doman.Dict;
import com.em.parent.doman.bo.DictBo;
import com.em.parent.doman.vo.DictVo;
import com.em.parent.mapper.DictMapper;
import com.em.parent.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class DictServiceImpl implements DictService {
    @Autowired
    DictMapper dictMapper;

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
    public R<Void> add(DictVo dictVo) {
        Dict dict = new Dict();
        dict.setName(dictVo.getName());
        dict.setCode(dictVo.getCode());
        dict.setDescription(dictVo.getDescription());
        dict.setStatus(dictVo.getStatus());
        dictMapper.insert(dict);
        return R.ok();
    }

    @Override
    public R<Void> update(DictVo dictVo) {
        Dict dict = new Dict();
        dict.setId(dictVo.getId());
        dict.setName(dictVo.getName());
        dict.setCode(dictVo.getCode());
        dict.setDescription(dictVo.getDescription());
        dict.setStatus(dictVo.getStatus());
        dictMapper.updateById(dict);
        return R.ok();
    }

    @Override
    public R<Void> delete(Long id) {
        dictMapper.deleteById(id);
        return R.ok();
    }
}

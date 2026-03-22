package com.em.parent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.em.parent.common.R;
import com.em.parent.doman.Dict;
import com.em.parent.doman.vo.DictVo;
import com.em.parent.mapper.DictMapper;
import com.em.parent.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictServiceImpl implements DictService {
    @Autowired
    DictMapper dictMapper;

    @Override
    public R<?> pageList() {
        Page<Dict> page = new Page<>();
        Page<DictVo> data = dictMapper.selectVoPage(page, new QueryWrapper<>());
        return R.ok(data);
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

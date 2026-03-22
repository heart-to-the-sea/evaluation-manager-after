package com.em.parent.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.em.parent.common.R;
import com.em.parent.doman.bo.DictValuesBo;
import com.em.parent.doman.vo.DictValuesVo;
import com.em.parent.service.DictValuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dict/values")
public class DictValuesController {

    @Autowired
    DictValuesService dictValuesService;

    @GetMapping("list")
    public R<Page<DictValuesVo>> list(DictValuesBo bo) {
        return dictValuesService.pageList(bo);
    }

    @GetMapping("listByCode")
    public R<List<DictValuesVo>> listByCode(@RequestParam String dictCode) {
        return dictValuesService.listByDictCode(dictCode);
    }

    @PostMapping("add")
    public R<Void> add(@RequestBody DictValuesVo dictValuesVo) {
        return dictValuesService.add(dictValuesVo);
    }

    @PutMapping("update")
    public R<Void> update(@RequestBody DictValuesVo dictValuesVo) {
        return dictValuesService.update(dictValuesVo);
    }

    @DeleteMapping("delete")
    public R<Void> delete(@RequestParam Long id) {
        return dictValuesService.delete(id);
    }
}

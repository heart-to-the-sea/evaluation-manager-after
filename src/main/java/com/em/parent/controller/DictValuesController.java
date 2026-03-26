package com.em.parent.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.em.parent.common.R;
import com.em.parent.doman.bo.DictValuesBo;
import com.em.parent.doman.vo.DictValuesVo;
import com.em.parent.service.DictValuesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dict/values")
@Tag(name = "字典值管理")
public class DictValuesController {

    @Autowired
    DictValuesService dictValuesService;

    @GetMapping("list")
    @Operation(summary = "字典值列表")
    public R<Page<DictValuesVo>> list(DictValuesBo bo) {
        return dictValuesService.pageList(bo);
    }

    @GetMapping("listByCode")
    @Operation(summary = "根据字典代码获取字典值")
    public R<List<DictValuesVo>> listByCode(@Parameter(description = "字典代码") @RequestParam String dictCode) {
        return dictValuesService.listByDictCode(dictCode);
    }

    @PostMapping("add")
    @Operation(summary = "新增字典值")
    public R<Void> add(@RequestBody DictValuesBo bo) {
        return dictValuesService.add(bo);
    }

    @PutMapping("update")
    @Operation(summary = "更新字典值")
    public R<Void> update(@RequestBody DictValuesBo bo) {
        return dictValuesService.update(bo);
    }

    @DeleteMapping("delete")
    @Operation(summary = "删除字典值")
    public R<Void> delete(@Parameter(description = "字典值ID") @RequestParam Long id) {
        return dictValuesService.delete(id);
    }
}
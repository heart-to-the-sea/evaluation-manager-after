package com.em.parent.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.em.parent.common.R;
import com.em.parent.doman.bo.DictBo;
import com.em.parent.doman.vo.DictVo;
import com.em.parent.service.DictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("dict")
@Tag(name = "字典管理")
public class DictController {

    @Autowired
    DictService dictService;

    @GetMapping("list")
    @Operation(summary = "字典列表")
    public R<Page<DictVo>> list(DictBo bo) {
        return dictService.pageList(bo);
    }

    @PostMapping("add")
    @Operation(summary = "新增字典")
    public R<Void> add(@RequestBody DictBo bo) {
        return dictService.add(bo);
    }

    @PutMapping("update")
    @Operation(summary = "更新字典")
    public R<Void> update(@RequestBody DictBo bo) {
        return dictService.update(bo);
    }

    @DeleteMapping("delete")
    @Operation(summary = "删除字典")
    public R<Void> delete(@Parameter(description = "字典ID") @RequestParam String id) {
        return dictService.delete(id);
    }
}
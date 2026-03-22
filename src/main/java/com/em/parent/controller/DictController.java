package com.em.parent.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.em.parent.common.R;
import com.em.parent.doman.bo.DictBo;
import com.em.parent.doman.vo.DictVo;
import com.em.parent.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("dict")
public class DictController {

    @Autowired
    DictService dictService;

    @GetMapping("list")
    public R<Page<DictVo>> list(DictBo bo) {
        return dictService.pageList(bo);
    }

    @PostMapping("add")
    public R<Void> add(@RequestBody DictVo dictVo) {
        return dictService.add(dictVo);
    }

    @PutMapping("update")
    public R<Void> update(@RequestBody DictVo dictVo) {
        return dictService.update(dictVo);
    }

    @DeleteMapping("delete")
    public R<Void> delete(@RequestParam Long id) {
        return dictService.delete(id);
    }
}

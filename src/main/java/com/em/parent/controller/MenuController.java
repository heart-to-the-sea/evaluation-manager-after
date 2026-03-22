package com.em.parent.controller;

import com.em.parent.common.R;
import com.em.parent.doman.vo.MenuVo;
import com.em.parent.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping("treeList")
    public R<List<MenuVo>> treeList() {
        return menuService.treeList();
    }

    @GetMapping("getById")
    public R<MenuVo> getById(@RequestParam Long id) {
        return menuService.getById(id);
    }

    @PostMapping("add")
    public R<Void> add(@RequestBody MenuVo menuVo) {
        return menuService.add(menuVo);
    }

    @PutMapping("update")
    public R<Void> update(@RequestBody MenuVo menuVo) {
        return menuService.update(menuVo);
    }

    @DeleteMapping("delete")
    public R<Void> delete(@RequestParam Long id) {
        return menuService.delete(id);
    }
}

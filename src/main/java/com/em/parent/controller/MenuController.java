package com.em.parent.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.em.parent.common.R;
import com.em.parent.doman.bo.MenuBo;
import com.em.parent.doman.vo.MenuVo;
import com.em.parent.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("menu")
@Tag(name = "菜单管理")
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping("list")
    @Operation(summary = "菜单列表")
    public R<Page<MenuVo>> list(MenuBo bo) {
        return menuService.pageList(bo);
    }

    @GetMapping("treeList")
    @Operation(summary = "菜单树形列表")
    public R<List<MenuVo>> treeList() {
        return menuService.treeList();
    }

    @GetMapping("getById")
    @Operation(summary = "根据ID获取菜单")
    public R<MenuVo> getById(@Parameter(description = "菜单ID") @RequestParam String id) {
        return menuService.getById(id);
    }

    @PostMapping("add")
    @Operation(summary = "新增菜单")
    public R<Void> add(@RequestBody MenuBo bo) {
        return menuService.add(bo);
    }

    @PutMapping("update")
    @Operation(summary = "更新菜单")
    public R<Void> update(@RequestBody MenuBo bo) {
        return menuService.update(bo);
    }

    @DeleteMapping("delete")
    @Operation(summary = "删除菜单")
    public R<Void> delete(@Parameter(description = "菜单ID") @RequestParam String id) {
        return menuService.delete(id);
    }
}
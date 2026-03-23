package com.em.parent.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.em.parent.common.R;
import com.em.parent.doman.bo.RoleBo;
import com.em.parent.doman.vo.RoleVo;
import com.em.parent.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
@Tag(name = "角色管理")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("list")
    @Operation(summary = "角色列表")
    public R<Page<RoleVo>> list(RoleBo bo) {
        return roleService.pageList(bo);
    }

    @GetMapping("getById")
    @Operation(summary = "根据ID获取角色")
    public R<RoleVo> getById(@Parameter(description = "角色ID") @RequestParam Long id) {
        return roleService.getById(id);
    }

    @PostMapping("add")
    @Operation(summary = "新增角色")
    public R<Void> add(@RequestBody RoleVo roleVo) {
        return roleService.add(roleVo);
    }

    @PutMapping("update")
    @Operation(summary = "更新角色")
    public R<Void> update(@RequestBody RoleVo roleVo) {
        return roleService.update(roleVo);
    }

    @DeleteMapping("delete")
    @Operation(summary = "删除角色")
    public R<Void> delete(@Parameter(description = "角色ID") @RequestParam Long id) {
        return roleService.delete(id);
    }

    @PostMapping("assignMenus")
    @Operation(summary = "分配菜单")
    public R<Void> assignMenus(@Parameter(description = "角色ID") @RequestParam Long roleId, @RequestBody List<Long> menuIds) {
        return roleService.assignMenus(roleId, menuIds);
    }

    @GetMapping("getMenuIds")
    @Operation(summary = "获取角色菜单ID列表")
    public R<List<Long>> getMenuIds(@Parameter(description = "角色ID") @RequestParam Long roleId) {
        return roleService.getMenuIdsByRoleId(roleId);
    }
}

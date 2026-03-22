package com.em.parent.controller;

import com.em.parent.common.R;
import com.em.parent.doman.vo.RoleVo;
import com.em.parent.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("list")
    public R<List<RoleVo>> list() {
        return roleService.listAll();
    }

    @GetMapping("getById")
    public R<RoleVo> getById(@RequestParam Long id) {
        return roleService.getById(id);
    }

    @PostMapping("add")
    public R<Void> add(@RequestBody RoleVo roleVo) {
        return roleService.add(roleVo);
    }

    @PutMapping("update")
    public R<Void> update(@RequestBody RoleVo roleVo) {
        return roleService.update(roleVo);
    }

    @DeleteMapping("delete")
    public R<Void> delete(@RequestParam Long id) {
        return roleService.delete(id);
    }

    @PostMapping("assignMenus")
    public R<Void> assignMenus(@RequestParam Long roleId, @RequestBody List<Long> menuIds) {
        return roleService.assignMenus(roleId, menuIds);
    }

    @GetMapping("getMenuIds")
    public R<List<Long>> getMenuIds(@RequestParam Long roleId) {
        return roleService.getMenuIdsByRoleId(roleId);
    }
}

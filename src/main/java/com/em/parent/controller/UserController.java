package com.em.parent.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.em.parent.common.R;
import com.em.parent.doman.bo.UserBo;
import com.em.parent.doman.vo.UserVo;
import com.em.parent.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@Tag(name = "用户管理")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("list")
    @Operation(summary = "用户列表")
    public R<Page<UserVo>> list(UserBo bo) {
        return userService.pageList(bo);
    }

    @GetMapping("getById")
    @Operation(summary = "根据ID获取用户")
    public R<UserVo> getById(@Parameter(description = "用户ID") @RequestParam Long id) {
        return userService.getById(id);
    }

    @PostMapping("add")
    @Operation(summary = "新增用户")
    public R<Void> add(@RequestBody UserVo userVo) {
        return userService.add(userVo);
    }

    @PutMapping("update")
    @Operation(summary = "更新用户")
    public R<Void> update(@RequestBody UserVo userVo) {
        return userService.update(userVo);
    }

    @DeleteMapping("delete")
    @Operation(summary = "删除用户")
    public R<Void> delete(@Parameter(description = "用户ID") @RequestParam Long id) {
        return userService.delete(id);
    }
}

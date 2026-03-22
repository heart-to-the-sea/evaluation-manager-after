package com.em.parent.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.em.parent.common.R;
import com.em.parent.doman.bo.UserBo;
import com.em.parent.doman.vo.UserVo;
import com.em.parent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("list")
    public R<Page<UserVo>> list(UserBo bo) {
        return userService.pageList(bo);
    }

    @GetMapping("getById")
    public R<UserVo> getById(@RequestParam Long id) {
        return userService.getById(id);
    }

    @PostMapping("add")
    public R<Void> add(@RequestBody UserVo userVo) {
        return userService.add(userVo);
    }

    @PutMapping("update")
    public R<Void> update(@RequestBody UserVo userVo) {
        return userService.update(userVo);
    }

    @DeleteMapping("delete")
    public R<Void> delete(@RequestParam Long id) {
        return userService.delete(id);
    }
}

package com.em.parent.controller;

import com.em.parent.common.R;
import com.em.parent.doman.bo.UserBo;
import com.em.parent.doman.vo.AuthVo;
import com.em.parent.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "认证管理")
public class AuthController {

    private final AuthService authService;
    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public R<AuthVo> login(@RequestBody UserBo user){
        return authService.login(user);
    }
    @GetMapping("/getUserInfo")
    @Operation(summary = "获取用户权限")
    public R<?> getAuthInfo(){
        return authService.getAuthInfo();
    }

    @GetMapping("getConstantRoutes")
    @Operation(summary = "获取通用菜单")
    public R<?> getConstantRoutes() {
        return authService.getConstantRoutes();
    }

    @GetMapping("getUserRoutes")
    @Operation(summary = "获取权限菜单")
    public R<?>
    getUserRoutes() {
        return authService.getUserRoutes();
    }
}

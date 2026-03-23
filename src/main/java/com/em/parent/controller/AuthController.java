package com.em.parent.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.em.parent.common.R;
import com.em.parent.doman.User;
import com.em.parent.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "认证管理")
public class AuthController {
    private final UserMapper userMapper;
    
    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public R<Void> login(@RequestBody User user){
        if(Objects.isNull(user)) {
            return R.fail().setMessage("登陆失败");
        }
        User userOne = userMapper.selectOne(
                new LambdaQueryWrapper<User>()
                        .eq(User::getAccount,user.getAccount())
                        .or().eq(User::getUsername, user.getUsername())
                        .eq(User::getPassword,user.getPassword())
        );

        if(Objects.isNull(userOne)) {
            return R.fail().setMessage("登陆失败");
        }

        StpUtil.login(userOne.getId());
        return R.ok().setMessage("登陆成功");
    }
}

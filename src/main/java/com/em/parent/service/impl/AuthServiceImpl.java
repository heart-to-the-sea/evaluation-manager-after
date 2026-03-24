package com.em.parent.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.em.parent.common.R;
import com.em.parent.doman.User;
import com.em.parent.doman.bo.UserBo;
import com.em.parent.doman.vo.AuthVo;
import com.em.parent.mapper.UserMapper;
import com.em.parent.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserMapper userMapper;
    @Override
    public R<AuthVo> login(UserBo userBo) {

        AuthVo authVo = new AuthVo();
        if(Objects.isNull(userBo)) {
            return R.fail().setMessage("登陆失败");
        }
        User userOne = userMapper.selectOne(
                new LambdaQueryWrapper<User>()
                        .eq(User::getAccount,userBo.getAccount())
                        .or().eq(User::getUsername, userBo.getUsername())
                        .eq(User::getPassword,userBo.getPassword())
        );

        if(Objects.isNull(userOne)) {
            return R.fail().setMessage("登陆失败");
        }

        StpUtil.login(userOne.getId());
        authVo.setToken(StpUtil.getTokenValue());
        authVo.setRefreshToken(StpUtil.createLoginSession(userOne.getId()));
        return R.ok(authVo).setMessage("登陆成功");
    }

    @Override
    public R<Void> logout() {
        StpUtil.logout();
        return R.ok();
    }

    @Override
    public R<AuthVo> refresh() {
        return null;
    }
    public R<Map<String,Object>> getAuthInfo(){
        Map<String,Object> map = new HashMap<>();
        map.put("buttons",null);
        map.put("rules",null);
        map.put("userId", "1");
        map.put("username", "amdin");
        return R.ok(map);
    }
}

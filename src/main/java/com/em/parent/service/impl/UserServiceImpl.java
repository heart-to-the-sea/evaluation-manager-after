package com.em.parent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.em.parent.common.R;
import com.em.parent.doman.User;
import com.em.parent.doman.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.em.parent.mapper.UserMapper;
import com.em.parent.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public R<Page<UserVo>> pageList() {
        Page<User> page = new Page<>();
        return R.ok(userMapper.selectVoPage(page, new QueryWrapper<>()));
    }
}

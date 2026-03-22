package com.em.parent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.em.parent.common.R;
import com.em.parent.doman.User;
import com.em.parent.doman.bo.UserBo;
import com.em.parent.doman.vo.UserVo;
import com.em.parent.mapper.UserMapper;
import com.em.parent.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public R<Page<UserVo>> pageList(UserBo bo) {
        Page<User> page = new Page<>(bo.getPageNum(), bo.getPageSize());

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();

        wrapper.like(StringUtils.hasText(bo.getAccount()), User::getAccount, bo.getAccount());
        wrapper.like(StringUtils.hasText(bo.getUsername()), User::getUsername, bo.getUsername());
        wrapper.orderByDesc(User::getId);

        return R.ok(userMapper.selectVoPage(page, wrapper));
    }

    @Override
    public R<UserVo> getById(Long id) {
        return R.ok(userMapper.selectVo(new LambdaQueryWrapper<User>().eq(User::getId, id)));
    }

    @Override
    public R<Void> add(UserVo userVo) {
        User user = new User();
        BeanUtils.copyProperties(userVo, user);
        userMapper.insert(user);
        return R.ok();
    }

    @Override
    public R<Void> update(UserVo userVo) {
        User user = new User();
        BeanUtils.copyProperties(userVo, user);
        userMapper.updateById(user);
        return R.ok();
    }

    @Override
    public R<Void> delete(Long id) {
        userMapper.deleteById(id);
        return R.ok();
    }
}

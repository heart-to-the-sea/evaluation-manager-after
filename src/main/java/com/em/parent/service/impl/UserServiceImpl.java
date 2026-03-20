package com.em.parent.service;

import com.em.parent.common.R;
import com.em.parent.doman.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.em.parent.mapper.UserMapper;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public R<List<UserVo>> pageList() {
        return R.ok(userMapper.selectVoList());
    }
}

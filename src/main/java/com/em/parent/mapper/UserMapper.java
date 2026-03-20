package com.em.parent.mapper;

import com.em.parent.doman.User;
import com.em.parent.doman.vo.UserVo;
import com.em.parent.utils.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapperPlus<User, UserVo> {
}

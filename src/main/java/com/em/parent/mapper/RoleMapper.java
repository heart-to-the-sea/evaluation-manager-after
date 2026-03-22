package com.em.parent.mapper;

import com.em.parent.doman.Role;
import com.em.parent.doman.vo.RoleVo;
import com.em.parent.utils.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper extends BaseMapperPlus<Role, RoleVo> {
}

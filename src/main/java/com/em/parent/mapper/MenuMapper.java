package com.em.parent.mapper;

import com.em.parent.doman.Menu;
import com.em.parent.doman.vo.MenuVo;
import com.em.parent.utils.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MenuMapper extends BaseMapperPlus<Menu, MenuVo> {
}

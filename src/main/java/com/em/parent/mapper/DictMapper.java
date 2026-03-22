package com.em.parent.mapper;

import com.em.parent.doman.Dict;
import com.em.parent.doman.vo.DictVo;
import com.em.parent.utils.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictMapper extends BaseMapperPlus<Dict, DictVo> {
}

package com.em.parent.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ConvertUtils {
    public static <Entity,VO> VO toVo(Entity entity, Class<VO> voClass) {
        if(entity == null) {
            return null;
        }
        try {
            VO vo = voClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(entity,vo);
            if(vo instanceof VoConvertible) {
                ((VoConvertible<Entity>) vo).init(entity);
            }
            return vo;
        } catch (Exception e) {
            throw new RuntimeException("Entity 转VO失败",e);
        }
    }
    public static <Entity,VO> List<VO> toVoList(List<Entity> entityList, Class<VO> voClass) {
        if(entityList == null || entityList.isEmpty()) {
            return List.of();
        }
        return entityList.stream().map(entity-> toVo(entity,voClass)).collect(Collectors.toList());
    }
    public interface VoConvertible<Entity> {
        void init(Entity entity);
    }
}

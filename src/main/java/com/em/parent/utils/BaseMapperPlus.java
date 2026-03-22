package com.em.parent.utils;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.bean.BeanUtil;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.stream.Collectors;

@NoRepositoryBean
public interface BaseMapperPlus<T, V> extends BaseMapper<T> {

    @SuppressWarnings("unchecked")
    default Class<V> getReturnClass() {
        return (Class<V>) ReflectionKit.getSuperClassGenericType(this.getClass(), BaseMapperPlus.class, 1);
    }

    default Page<V> selectVoPage(Page<T> page, Wrapper<T> queryWrapper) {
        Page<T> entityPage = this.selectPage(page, queryWrapper);
        Class<V> VClass = getReturnClass();

        Page<V> VPage = new Page<>(entityPage.getCurrent(), entityPage.getSize(), entityPage.getTotal());

        List<V> VList = entityPage.getRecords().stream()
                .map(entity -> BeanUtil.toBean(entity, VClass))
                .collect(Collectors.toList());

        VPage.setRecords(VList);
        return VPage;
    }

    default V selectVo(Wrapper<T> queryWrapper) {
        T entity = this.selectOne(queryWrapper);
        if (entity == null) return null;
        return BeanUtil.toBean(entity, getReturnClass());
    }
    default List<V> selectVoList() {
        List<T> entityList = this.selectList(new QueryWrapper<>());
        if(null == entityList || entityList.isEmpty()) {
            return List.of();
        }
        Class<V> VClass = getReturnClass();
        return entityList.stream()
                .map(entity -> BeanUtil.toBean(entity, VClass))
                .collect(Collectors.toList());
    }
    default List<V> selectVoList(Wrapper<T> queryWrapper) {
        List<T> entityList = this.selectList(queryWrapper);
        if(null == entityList || entityList.isEmpty()) {
            return List.of();
        }
        Class<V> VClass = getReturnClass();
        return entityList.stream()
                .map(entity -> BeanUtil.toBean(entity, VClass))
                .collect(Collectors.toList());
    }
}

package com.em.parent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.em.parent.common.R;
import com.em.parent.doman.Menu;
import com.em.parent.doman.vo.MenuVo;
import com.em.parent.mapper.MenuMapper;
import com.em.parent.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuMapper menuMapper;

    @Override
    public R<List<MenuVo>> treeList() {
        List<MenuVo> allMenus = menuMapper.selectVoList(new QueryWrapper<>());
        List<MenuVo> tree = buildTree(allMenus);
        return R.ok(tree);
    }

    @Override
    public R<MenuVo> getById(Long id) {
        MenuVo menuVo = menuMapper.selectVo(new QueryWrapper<Menu>().eq("id", id));
        return R.ok(menuVo);
    }

    @Override
    public R<Void> add(MenuVo menuVo) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuVo, menu);
        menuMapper.insert(menu);
        return R.ok();
    }

    @Override
    public R<Void> update(MenuVo menuVo) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuVo, menu);
        menuMapper.updateById(menu);
        return R.ok();
    }

    @Override
    public R<Void> delete(Long id) {
        menuMapper.deleteById(id);
        return R.ok();
    }

    private List<MenuVo> buildTree(List<MenuVo> menus) {
        Map<Long, List<MenuVo>> groupByParent = menus.stream()
                .collect(Collectors.groupingBy(MenuVo::getParentId));
        
        List<MenuVo> roots = groupByParent.getOrDefault(0L, new ArrayList<>());
        roots.forEach(root -> buildChildren(root, groupByParent));
        return roots;
    }

    private void buildChildren(MenuVo parent, Map<Long, List<MenuVo>> groupByParent) {
        List<MenuVo> children = groupByParent.getOrDefault(parent.getId(), new ArrayList<>());
        parent.setChildren(children);
        children.forEach(child -> buildChildren(child, groupByParent));
    }
}

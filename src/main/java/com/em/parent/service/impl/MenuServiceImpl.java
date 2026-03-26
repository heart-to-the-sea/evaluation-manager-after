package com.em.parent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.em.parent.common.R;
import com.em.parent.doman.Menu;
import com.em.parent.doman.bo.MenuBo;
import com.em.parent.doman.vo.MenuVo;
import com.em.parent.mapper.MenuMapper;
import com.em.parent.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuMapper menuMapper;

    @Override
    public R<Page<MenuVo>> pageList(MenuBo bo) {
        Page<Menu> page = new Page<>(bo.getPageNum(), bo.getPageSize());
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(bo.getLabel())) {
            wrapper.like(Menu::getLabel, bo.getLabel());
        }
        if (StringUtils.hasText(bo.getRoutePath())) {
            wrapper.like(Menu::getRoutePath, bo.getRoutePath());
        }
        return R.ok(menuMapper.selectVoPage(page, wrapper));
    }

    @Override
    public R<List<MenuVo>> treeList() {
        List<MenuVo> allMenus = menuMapper.selectVoList(new LambdaQueryWrapper<>());
        List<MenuVo> tree = buildTree(allMenus);
        return R.ok(tree);
    }

    @Override
    public R<MenuVo> getById(String id) {
        return R.ok(menuMapper.selectVo(new LambdaQueryWrapper<Menu>().eq(Menu::getId, id)));
    }

    @Override
    public R<Void> add(MenuBo bo) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(bo, menu);
        menuMapper.insert(menu);
        return R.ok();
    }

    @Override
    public R<Void> update(MenuBo bo) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(bo, menu);
        menuMapper.updateById(menu);
        return R.ok();
    }

    @Override
    public R<Void> delete(String id) {
        menuMapper.deleteById(id);
        return R.ok();
    }

    private List<MenuVo> buildTree(List<MenuVo> menus) {
        Map<String, List<MenuVo>> groupByParent = menus.stream()
                .collect(Collectors.groupingBy(MenuVo::getParentId));
        List<MenuVo> roots = groupByParent.getOrDefault("0", new ArrayList<>());
        roots.forEach(root -> buildChildren(root, groupByParent));
        return roots;
    }

    private void buildChildren(MenuVo parent, Map<String, List<MenuVo>> groupByParent) {
        List<MenuVo> children = groupByParent.getOrDefault(parent.getId(), new ArrayList<>());
        parent.setChildren(children);
        children.forEach(child -> buildChildren(child, groupByParent));
    }
}
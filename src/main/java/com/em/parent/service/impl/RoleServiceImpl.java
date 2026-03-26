package com.em.parent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.em.parent.common.R;
import com.em.parent.doman.Menu;
import com.em.parent.doman.Role;
import com.em.parent.doman.RoleMenu;
import com.em.parent.doman.bo.RoleBo;
import com.em.parent.doman.vo.MenuVo;
import com.em.parent.doman.vo.RoleVo;
import com.em.parent.mapper.MenuMapper;
import com.em.parent.mapper.RoleMapper;
import com.em.parent.mapper.RoleMenuMapper;
import com.em.parent.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    RoleMenuMapper roleMenuMapper;
    @Autowired
    MenuMapper menuMapper;

    @Override
    public R<Page<RoleVo>> pageList(RoleBo bo) {
        Page<Role> page = new Page<>(bo.getPageNum(), bo.getPageSize());
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(bo.getName())) {
            wrapper.like(Role::getName, bo.getName());
        }
        if (StringUtils.hasText(bo.getCode())) {
            wrapper.like(Role::getCode, bo.getCode());
        }
        wrapper.orderByDesc(Role::getId);
        return R.ok(roleMapper.selectVoPage(page, wrapper));
    }

    @Override
    public R<RoleVo> getById(String id) {
        RoleVo roleVo = roleMapper.selectVo(new QueryWrapper<Role>().eq("id", id));
        if (roleVo != null) {
            roleVo.setMenus(getMenusByRoleId(id));
        }
        return R.ok(roleVo);
    }

    private List<MenuVo> getMenusByRoleId(String roleId) {
        List<RoleMenu> roleMenus = roleMenuMapper.selectList(new QueryWrapper<RoleMenu>().eq("role_id", roleId));
        if (roleMenus.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> menuIds = roleMenus.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        List<MenuVo> allMenus = menuMapper.selectVoList(new QueryWrapper<Menu>().in("id", menuIds));
        return buildTree(allMenus);
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

    @Override
    public R<Void> add(RoleBo bo) {
        Role role = new Role();
        BeanUtils.copyProperties(bo, role);
        roleMapper.insert(role);
        return R.ok();
    }

    @Override
    public R<Void> update(RoleBo bo) {
        Role role = new Role();
        BeanUtils.copyProperties(bo, role);
        roleMapper.updateById(role);
        return R.ok();
    }

    @Override
    public R<Void> delete(String id) {
        roleMapper.deleteById(id);
        return R.ok();
    }

    @Override
    @Transactional
    public R<Void> assignMenus(String roleId, List<String> menuIds) {
        roleMenuMapper.delete(new QueryWrapper<RoleMenu>().eq("role_id", roleId));
        for (String menuId : menuIds) {
            RoleMenu rm = new RoleMenu();
            rm.setRoleId(roleId);
            rm.setMenuId(menuId);
            roleMenuMapper.insert(rm);
        }
        return R.ok();
    }

    @Override
    public R<List<String>> getMenuIdsByRoleId(String roleId) {
        List<RoleMenu> roleMenus = roleMenuMapper.selectList(
                new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId, roleId));
        List<String> menuIds = new ArrayList<>();
        for (RoleMenu rm : roleMenus) {
            menuIds.add(rm.getMenuId());
        }
        return R.ok(menuIds);
    }
}
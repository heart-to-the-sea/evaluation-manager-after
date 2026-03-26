package com.em.parent.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.em.parent.common.R;
import com.em.parent.doman.bo.RoleBo;
import com.em.parent.doman.vo.RoleVo;

import java.util.List;

public interface RoleService {
    R<Page<RoleVo>> pageList(RoleBo bo);
    R<RoleVo> getById(String id);
    R<Void> add(RoleBo bo);
    R<Void> update(RoleBo bo);
    R<Void> delete(String id);
    R<Void> assignMenus(String roleId, List<String> menuIds);
    R<List<String>> getMenuIdsByRoleId(String roleId);
}
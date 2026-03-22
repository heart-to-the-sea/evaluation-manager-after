package com.em.parent.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.em.parent.common.R;
import com.em.parent.doman.bo.RoleBo;
import com.em.parent.doman.vo.RoleVo;

import java.util.List;

public interface RoleService {
    R<Page<RoleVo>> pageList(RoleBo bo);
    R<RoleVo> getById(Long id);
    R<Void> add(RoleVo roleVo);
    R<Void> update(RoleVo roleVo);
    R<Void> delete(Long id);
    R<Void> assignMenus(Long roleId, List<Long> menuIds);
    R<List<Long>> getMenuIdsByRoleId(Long roleId);
}

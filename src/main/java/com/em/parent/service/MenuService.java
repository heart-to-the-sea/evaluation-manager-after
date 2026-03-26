package com.em.parent.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.em.parent.common.R;
import com.em.parent.doman.bo.MenuBo;
import com.em.parent.doman.vo.MenuVo;

import java.util.List;

public interface MenuService {
    R<Page<MenuVo>> pageList(MenuBo bo);
    R<List<MenuVo>> treeList();
    R<MenuVo> getById(String id);
    R<Void> add(MenuBo bo);
    R<Void> update(MenuBo bo);
    R<Void> delete(String id);
}
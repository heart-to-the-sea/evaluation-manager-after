package com.em.parent.service;

import com.em.parent.common.R;
import com.em.parent.doman.vo.MenuVo;

import java.util.List;

public interface MenuService {
    R<List<MenuVo>> treeList();
    R<MenuVo> getById(Long id);
    R<Void> add(MenuVo menuVo);
    R<Void> update(MenuVo menuVo);
    R<Void> delete(Long id);
}

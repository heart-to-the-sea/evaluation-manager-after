package com.em.parent.diver;

import com.fhs.core.trans.vo.VO;
import com.fhs.trans.service.impl.SimpleTransService;

import java.io.Serializable;
import java.util.List;

public class EasyQueryTransDiver implements SimpleTransService.SimpleTransDiver {

    @Override
    public List<? extends VO> findByIds(List<? extends Serializable> list, Class<? extends VO> aClass, String s) {
        return null;
    }

    @Override
    public VO findById(Serializable serializable, Class<? extends VO> aClass, String s) {
        return null;
    }
}

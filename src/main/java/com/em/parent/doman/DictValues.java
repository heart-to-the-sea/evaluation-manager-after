package com.em.parent.doman;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@TableName("dict_values")
@Data
public class DictValues {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private Long dictId;
    private String dictKey;
    private String label;
    private String value;
    private Integer sort;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

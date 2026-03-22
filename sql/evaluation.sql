-- 考核管理数据库设计
-- 包含：考核模板表、阶段表、考核内容表、小阶段表、员工考核表

-- ----------------------------
-- 1. 考核模板表 (evaluation_template)
-- ----------------------------
DROP TABLE IF EXISTS evaluation_template;
CREATE TABLE evaluation_template (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    name VARCHAR(200) NOT NULL COMMENT '考核模板名称',
    type VARCHAR(50) COMMENT '考核类型（项目/日常/专项）',
    description TEXT COMMENT '考核说明',
    status TINYINT DEFAULT 1 COMMENT '状态：1-启用 0-停用',
    created_by BIGINT COMMENT '创建人ID',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='考核模板表';

-- ----------------------------
-- 2. 模板阶段表 (template_stage)
-- ----------------------------
DROP TABLE IF EXISTS template_stage;
CREATE TABLE template_stage (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    template_id BIGINT NOT NULL COMMENT '模板ID',
    name VARCHAR(100) NOT NULL COMMENT '阶段名称',
    code VARCHAR(50) COMMENT '阶段编码',
    sort INT DEFAULT 0 COMMENT '排序',
    weight DECIMAL(5,2) DEFAULT 0 COMMENT '权重（%）',
    description TEXT COMMENT '阶段说明',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (template_id) REFERENCES evaluation_template(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='模板阶段表';

-- ----------------------------
-- 3. 模板考核内容表 (template_content)
-- ----------------------------
DROP TABLE IF EXISTS template_content;
CREATE TABLE template_content (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    stage_id BIGINT NOT NULL COMMENT '阶段ID',
    name VARCHAR(200) NOT NULL COMMENT '考核内容名称',
    description TEXT COMMENT '考核内容说明',
    weight DECIMAL(5,2) DEFAULT 0 COMMENT '权重（%）',
    sort INT DEFAULT 0 COMMENT '排序',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (stage_id) REFERENCES template_stage(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='模板考核内容表';

-- ----------------------------
-- 4. 模板小阶段表 (template_substage)
-- ----------------------------
DROP TABLE IF EXISTS template_substage;
CREATE TABLE template_substage (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    content_id BIGINT NOT NULL COMMENT '考核内容ID',
    name VARCHAR(200) NOT NULL COMMENT '小阶段名称',
    description TEXT COMMENT '小阶段说明',
    max_score DECIMAL(5,2) DEFAULT 100 COMMENT '最高分',
    sort INT DEFAULT 0 COMMENT '排序',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (content_id) REFERENCES template_content(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='模板小阶段表';

-- ----------------------------
-- 5. 员工考核表 (employee_evaluation)
-- ----------------------------
DROP TABLE IF EXISTS employee_evaluation;
CREATE TABLE employee_evaluation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    template_id BIGINT NOT NULL COMMENT '模板ID',
    employee_id BIGINT NOT NULL COMMENT '员工ID',
    employee_name VARCHAR(100) COMMENT '员工名称',
    period_start DATE COMMENT '考核周期开始',
    period_end DATE COMMENT '考核周期结束',
    status TINYINT DEFAULT 1 COMMENT '状态：1-进行中 0-已完成',
    total_score DECIMAL(5,2) COMMENT '总分',
    grade VARCHAR(20) COMMENT '考核等级',
    evaluator_id BIGINT COMMENT '考核人ID',
    evaluator_name VARCHAR(100) COMMENT '考核人名称',
    remark TEXT COMMENT '备注',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (template_id) REFERENCES evaluation_template(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工考核表';

-- ----------------------------
-- 6. 员工考核阶段得分表 (employee_stage_score)
-- ----------------------------
DROP TABLE IF EXISTS employee_stage_score;
CREATE TABLE employee_stage_score (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    employee_eval_id BIGINT NOT NULL COMMENT '员工考核ID',
    template_stage_id BIGINT NOT NULL COMMENT '模板阶段ID',
    stage_name VARCHAR(100) COMMENT '阶段名称',
    weight DECIMAL(5,2) COMMENT '权重',
    score DECIMAL(5,2) COMMENT '阶段得分',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_eval_id) REFERENCES employee_evaluation(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工考核阶段得分表';

-- ----------------------------
-- 7. 员工考核内容得分表 (employee_content_score)
-- ----------------------------
DROP TABLE IF EXISTS employee_content_score;
CREATE TABLE employee_content_score (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    employee_eval_id BIGINT NOT NULL COMMENT '员工考核ID',
    template_content_id BIGINT NOT NULL COMMENT '模板内容ID',
    content_name VARCHAR(200) COMMENT '考核内容名称',
    weight DECIMAL(5,2) COMMENT '权重',
    score DECIMAL(5,2) COMMENT '内容得分',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_eval_id) REFERENCES employee_evaluation(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工考核内容得分表';

-- ----------------------------
-- 8. 员工考核小阶段得分表 (employee_substage_score)
-- ----------------------------
DROP TABLE IF EXISTS employee_substage_score;
CREATE TABLE employee_substage_score (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    employee_eval_id BIGINT NOT NULL COMMENT '员工考核ID',
    template_substage_id BIGINT NOT NULL COMMENT '模板小阶段ID',
    substage_name VARCHAR(200) COMMENT '小阶段名称',
    max_score DECIMAL(5,2) COMMENT '最高分',
    actual_score DECIMAL(5,2) COMMENT '实际得分',
    result VARCHAR(50) COMMENT '考核结果',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_eval_id) REFERENCES employee_evaluation(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工考核小阶段得分表';

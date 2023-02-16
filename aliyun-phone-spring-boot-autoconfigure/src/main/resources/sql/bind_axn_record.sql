CREATE TABLE `bind_axn_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(100) DEFAULT '' COMMENT '请求状态码',
  `message` varchar(100) DEFAULT '' COMMENT '状态码的描述',
  `request_id` varchar(100) DEFAULT '' COMMENT '请求ID',
  `extension` varchar(50) DEFAULT '' COMMENT '分机号(axn不涉及分机号码，请忽略)',
  `secret_no` varchar(50) DEFAULT '' COMMENT '隐私号码，即X号码',
  `subs_id` varchar(100) DEFAULT '' COMMENT '订购关系ID',
  `pool_key` varchar(100) DEFAULT '' COMMENT '号码池key',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_subs_id` (`subs_id`) USING BTREE,
  KEY `idx_pool_key` (`pool_key`) USING BTREE,
  KEY `idx_secret_no` (`secret_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='BindAxn绑定记录表';
CREATE TABLE `ring_public_url` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `call_id` varchar(100) DEFAULT '' COMMENT '标识每一通唯一通话行为的callId',
  `call_time` datetime DEFAULT NULL COMMENT '话单回执中返回的callTime字段吗，呼叫时间',
  `ring_public_url` varchar(255) DEFAULT '' COMMENT '响铃放音的录音下载URL。',
  `phone_public_url` varchar(255) DEFAULT '' COMMENT '通话录音的下载URL。',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_call_id` (`call_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='获取响铃放音的下载链接';
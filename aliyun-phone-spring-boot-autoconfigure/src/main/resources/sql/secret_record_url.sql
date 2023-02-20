CREATE TABLE `secret_record_url` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `call_id` varchar(100) DEFAULT '' COMMENT '标识每一通唯一通话行为的callId',
  `call_time` datetime DEFAULT NULL COMMENT '话单回执中返回的callTime字段吗，呼叫时间',
  `download_url` varchar(255) DEFAULT NULL COMMENT '返回的URL链接，URL链接访问有效时间为2小时内，录音存储周期是14天。',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_call_id` (`call_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='获取录音文件下载地址';
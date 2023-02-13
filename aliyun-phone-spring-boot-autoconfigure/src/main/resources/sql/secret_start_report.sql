CREATE TABLE `secret_start_report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pool_key` varchar(50) DEFAULT '' COMMENT '号码池key',
  `sub_id` bigint(20) DEFAULT '0' COMMENT '绑定关系ID',
  `call_id` varchar(100) NOT NULL DEFAULT '' COMMENT '唯一标识一通通话记录的ID',
  `phone_no` varchar(50) DEFAULT '' COMMENT 'A号码',
  `secret_no` varchar(50) DEFAULT '' COMMENT '隐私号码X',
  `peer_no` varchar(50) DEFAULT '' COMMENT 'B或者N号码',
  `called_display_no` varchar(50) DEFAULT '' COMMENT '被叫显号',
  `call_type` int(2) DEFAULT '6' COMMENT '呼叫类型 0：主叫（phone_no打给peer_no）1：被叫（peer_no打给phone_no）2：短信发送 3：短信接收4：呼叫拦截5：短信收发拦截',
  `call_time` datetime DEFAULT NULL COMMENT '主叫拨打时间',
  `out_id` varchar(50) DEFAULT '' COMMENT '外部业务ID',
  `control_msg` varchar(50) DEFAULT 'NO_SUBS_EXIST' COMMENT '无绑定关系时返回NO_SUBS_EXIST',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_call_id` (`call_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='呼叫发起时话单回执消息SecretStartReport';
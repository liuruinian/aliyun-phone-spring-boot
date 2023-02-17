CREATE TABLE `bind_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `request_id` varchar(100) DEFAULT '' COMMENT '请求ID',
  `code` varchar(100) DEFAULT '' COMMENT '请求状态码',
  `message` varchar(100) DEFAULT '' COMMENT '请求状态码的描述',
  `pool_key` varchar(100) DEFAULT '' COMMENT '号码池key',
  `subs_id` varchar(100) DEFAULT '' COMMENT '订购关系ID',
  `status` bigint(1) DEFAULT '1' COMMENT '绑定状态 1生效 0失效',
  `extension` varchar(100) DEFAULT '' COMMENT '分机号',
  `phone_no_a` varchar(50) DEFAULT '' COMMENT 'phoneNoA',
  `phone_no_b` varchar(50) DEFAULT '' COMMENT 'phoneNoB',
  `phone_no_x` varchar(50) DEFAULT '' COMMENT 'phoneNoX',
  `group_id` bigint(20) DEFAULT NULL COMMENT '组ID',
  `gmt_create` datetime DEFAULT NULL COMMENT '绑定关系创建时间',
  `expire_date` datetime DEFAULT NULL COMMENT '绑定关系失效时间',
  `need_record` varchar(5) DEFAULT '' COMMENT '是否对该绑定关系产生的所有通话，进行通话录音 false：不录音 true：录音',
  `call_restrict` varchar(100) DEFAULT '' COMMENT '单通呼叫限制的状态。如果没有设置单通呼叫限制，则不返回该参数 CONTROL_AX_DISABLE：A号码无法呼叫X号码。CONTROL_BX_DISABLE：B号码无法呼叫X号码。',
  `asr_status` varchar(5) DEFAULT '' COMMENT 'ASR状态 false：关闭 true：开启',
  `asr_model_id` varchar(100) DEFAULT '' COMMENT 'ASR模型ID',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_subs_id` (`subs_id`) USING BTREE,
  KEY `idx_phone_no_x` (`phone_no_x`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='绑定详情表';
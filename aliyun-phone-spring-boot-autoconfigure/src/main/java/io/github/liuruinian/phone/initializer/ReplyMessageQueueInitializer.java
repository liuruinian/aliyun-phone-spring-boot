package io.github.liuruinian.phone.initializer;

import com.alicom.mns.tools.DefaultAlicomMessagePuller;
import io.github.liuruinian.phone.constants.MessageType;
import io.github.liuruinian.phone.properties.AliPhoneProperties;
import io.github.liuruinian.phone.reply.SecretEndReportListener;
import io.github.liuruinian.phone.reply.SecretStartReportListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import javax.annotation.PostConstruct;

/**
 * @author liuruinian
 * @since 2023-02-13
 */
@Slf4j
public class ReplyMessageQueueInitializer {

    private final AliPhoneProperties properties;

    private final DefaultAlicomMessagePuller defaultAlicomMessagePuller;

    private final SecretStartReportListener secretStartReportListener;

    private final SecretEndReportListener secretEndReportListener;

    public ReplyMessageQueueInitializer(AliPhoneProperties properties,
                                        DefaultAlicomMessagePuller defaultAlicomMessagePuller,
                                        SecretStartReportListener secretStartReportListener,
                                        SecretEndReportListener secretEndReportListener) {
        this.properties = properties;
        this.defaultAlicomMessagePuller = defaultAlicomMessagePuller;
        this.secretStartReportListener = secretStartReportListener;
        this.secretEndReportListener = secretEndReportListener;
    }

    @PostConstruct
    public void initReplyMessageQueue() {
        final String accessKeyId = properties.getAcs().getAccessKeyId();
        final String accessKeySecret = properties.getAcs().getAccessKeySecret();
        final String messageType = properties.getMns().getMessageType();

        // init secret start report listener
        if (properties.getMns().getEnableSecretStartReport() &&
                StringUtils.hasLength(properties.getMns().getSecretStartReportQueueName()) &&
                messageType.contains(MessageType.SECRET_START_REPORT)) {

            String queueName = properties.getMns().getSecretStartReportQueueName();
            try {
                log.info("[ReplyMessageQueueInitializer] - initializing the secret start report listener ......");
                defaultAlicomMessagePuller.startReceiveMsg(accessKeyId, accessKeySecret, MessageType.SECRET_START_REPORT, queueName, secretStartReportListener);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }

        // init secret end report listener
        if (properties.getMns().getEnableSecretEndReport() &&
                StringUtils.hasLength(properties.getMns().getSecretEndReportQueueName()) &&
                messageType.contains(MessageType.SECRET_END_REPORT)) {

            String queueName = properties.getMns().getSecretEndReportQueueName();
            try {
                log.info("[ReplyMessageQueueInitializer] - initializing the secret end report listener ......");
                defaultAlicomMessagePuller.startReceiveMsg(accessKeyId, accessKeySecret, MessageType.SECRET_END_REPORT, queueName, secretEndReportListener);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}

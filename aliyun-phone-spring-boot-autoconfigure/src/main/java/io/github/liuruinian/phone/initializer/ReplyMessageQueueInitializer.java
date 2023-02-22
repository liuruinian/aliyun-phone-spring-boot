package io.github.liuruinian.phone.initializer;

import com.alicom.mns.tools.DefaultAlicomMessagePuller;
import io.github.liuruinian.phone.constants.MessageType;
import io.github.liuruinian.phone.mnsreply.*;
import io.github.liuruinian.phone.properties.AliPhoneProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;
import javax.annotation.PostConstruct;

/**
 * @author liuruinian
 * @since 2023-02-13
 */
@Slf4j
public class ReplyMessageQueueInitializer implements ApplicationContextAware {

    private final AliPhoneProperties properties;

    private ApplicationContext applicationContext;

    private final SecretStartReportListener secretStartReportListener;

    private final SecretEndReportListener secretEndReportListener;

    private final SecretRecordingCompletionListener secretRecordingListener;

    private final SecretExceptionPhoneReportListener secretExceptionPhoneReportListener;

    private final SecretAsrReportListener secretAsrReportListener;

    public ReplyMessageQueueInitializer(AliPhoneProperties properties,
                                        SecretStartReportListener secretStartReportListener,
                                        SecretEndReportListener secretEndReportListener,
                                        SecretRecordingCompletionListener secretRecordingListener,
                                        SecretExceptionPhoneReportListener secretExceptionPhoneReportListener,
                                        SecretAsrReportListener secretAsrReportListener) {
        this.properties = properties;
        this.secretStartReportListener = secretStartReportListener;
        this.secretEndReportListener = secretEndReportListener;
        this.secretRecordingListener = secretRecordingListener;
        this.secretExceptionPhoneReportListener = secretExceptionPhoneReportListener;
        this.secretAsrReportListener = secretAsrReportListener;
    }

    public DefaultAlicomMessagePuller getDefaultAlicomMessagePuller() {
        return applicationContext.getBean(DefaultAlicomMessagePuller.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
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
                getDefaultAlicomMessagePuller().startReceiveMsg(accessKeyId, accessKeySecret, MessageType.SECRET_START_REPORT, queueName, secretStartReportListener);
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
                getDefaultAlicomMessagePuller().startReceiveMsg(accessKeyId, accessKeySecret, MessageType.SECRET_END_REPORT, queueName, secretEndReportListener);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }

        // init secret recording listener
        if (properties.getMns().getEnableRecordingCompletionEvent() &&
                StringUtils.hasLength(properties.getMns().getSecretRecordingCompletionQueueName()) &&
                messageType.contains(MessageType.SECRET_RECORDING_COMPLETION)) {
            String queueName = properties.getMns().getSecretRecordingCompletionQueueName();
            try {
                log.info("[ReplyMessageQueueInitializer] - initializing the secret recording listener ......");
                getDefaultAlicomMessagePuller().startReceiveMsg(accessKeyId, accessKeySecret, MessageType.SECRET_RECORDING_COMPLETION, queueName, secretRecordingListener);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }

        // init secret exception phone report listener
        if (properties.getMns().getEnableSecretExceptionPhoneReport() &&
                StringUtils.hasLength(properties.getMns().getSecretExceptionPhoneReportQueueName()) &&
                messageType.contains(MessageType.SECRET_EXCEPTION_PHONE_REPORT)) {
            String queueName = properties.getMns().getSecretExceptionPhoneReportQueueName();
            try {
                log.info("[ReplyMessageQueueInitializer] - initializing the secret exception phone report listener ......");
                getDefaultAlicomMessagePuller().startReceiveMsg(accessKeyId, accessKeySecret, MessageType.SECRET_EXCEPTION_PHONE_REPORT, queueName, secretExceptionPhoneReportListener);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }

        // init secret asr report listener
        if (properties.getMns().getEnableSecretAsrReport() &&
                StringUtils.hasLength(properties.getMns().getSecretAsrReportQueueName()) &&
                messageType.contains(MessageType.SECRET_ASR_REPORT)) {
            String queueName = properties.getMns().getSecretAsrReportQueueName();
            try {
                log.info("[ReplyMessageQueueInitializer] - initializing the secret asr report listener ......");
                getDefaultAlicomMessagePuller().startReceiveMsg(accessKeyId, accessKeySecret, MessageType.SECRET_ASR_REPORT, queueName, secretAsrReportListener);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}

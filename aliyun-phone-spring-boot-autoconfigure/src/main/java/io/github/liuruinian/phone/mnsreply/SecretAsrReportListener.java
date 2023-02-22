package io.github.liuruinian.phone.mnsreply;

import com.alicom.mns.tools.MessageListener;
import com.aliyun.mns.model.Message;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author liuruinian
 * @since 2023-02-22
 */
public class SecretAsrReportListener implements MessageListener, ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public boolean dealMessage(Message message) {
        return false;
    }
}

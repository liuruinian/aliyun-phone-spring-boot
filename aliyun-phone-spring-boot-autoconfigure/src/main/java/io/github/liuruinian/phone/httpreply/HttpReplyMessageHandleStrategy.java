package io.github.liuruinian.phone.httpreply;

import io.github.liuruinian.phone.httpreply.strategy.*;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import java.lang.reflect.Constructor;

/**
 * @author liuruinian
 * @since 2023-02-24
 */
public class HttpReplyMessageHandleStrategy {
    // ~ static fields
    public static final String MODE_SECRET_START_REPORT = "MODE_SECRET_START_REPORT";
    public static final String MODE_SECRET_ASR_REPORT = "MODE_SECRET_ASR_REPORT";
    public static final String MODE_SECRET_RECORDING = "MODE_SECRET_RECORDING";
    public static final String MODE_SECRET_END_REPORT = "MODE_SECRET_END_REPORT";
    public static final String MODE_SMART_LOGISTICS_REPORT = "MODE_SMART_LOGISTICS_REPORT";
    public static final String MODE_SECRET_RING_REPORT = "MODE_SECRET_RING_REPORT";
    public static final String MODE_SECRET_PICKUP_REPORT = "MODE_SECRET_PICKUP_REPORT";
    public static final String MODE_NUMBER_MANAGEMENT_REPORT = "MODE_NUMBER_MANAGEMENT_REPORT";
    public static final String SYSTEM_PROPERTY = "aliphone.protection.strategy";

    private static HttpReplyMessageHandler strategy;
    private static String strategyName = System.getProperty(SYSTEM_PROPERTY);

    private static int initializeCount = 0;

    static {
        initialize();
    }

    // ~ Methods
    private static void initialize() {
        if (!StringUtils.hasText(strategyName)) {
            // Set default
            strategyName = MODE_SECRET_START_REPORT;
        }

        if (MODE_SECRET_START_REPORT.equals(strategyName)) {
            strategy = new SecretStartReportReplyMessageStrategy();
        }
        else if (MODE_SECRET_END_REPORT.equals(strategyName)) {
            strategy = new SecretEndReportReplyMessageStrategy();
        }
        else if (MODE_SECRET_RECORDING.equals(strategyName)) {
            strategy = new SecretRecordingReplyMessageStrategy();
        }
        else if (MODE_SECRET_ASR_REPORT.equals(strategyName)) {
            strategy = new SecretAsrReportReplyMessageStrategy();
        }
        else if (MODE_SMART_LOGISTICS_REPORT.equals(strategyName)) {
            strategy = new SmartLogisticsReportReplyMessageStrategy();
        }
        else if (MODE_SECRET_RING_REPORT.equals(strategyName)) {
            strategy = new SecretRingReportReplyMessageStrategy();
        }
        else if (MODE_SECRET_PICKUP_REPORT.equals(strategyName)) {
            strategy = new SecretPickUpReportReplyMessageStrategy();
        }
        else if (MODE_NUMBER_MANAGEMENT_REPORT.equals(strategyName)) {
            strategy = new NumberManagementReportReplyMessageStrategy();
        }
        else {
            // Try to load a custom strategy
            try {
                Class<?> clazz = Class.forName(strategyName);
                Constructor<?> customStrategy = clazz.getConstructor();
                strategy = (HttpReplyMessageHandler) customStrategy.newInstance();
            }
            catch (Exception ex) {
                ReflectionUtils.handleReflectionException(ex);
            }
        }

        initializeCount++;
    }

    public static void setStrategyName(String strategyName) {
        HttpReplyMessageHandleStrategy.strategyName = strategyName;
        initialize();
    }

    public static HttpReplyMessageHandler getStrategy() {
        return strategy;
    }

    @Override
    public String toString() {
        return "HttpReplyMessageHandleStrategy - [strategy = '" + strategyName + "'; initializeCount = " + initializeCount + "]";
    }
}

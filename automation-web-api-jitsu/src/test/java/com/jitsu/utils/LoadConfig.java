package com.jitsu.utils;
import net.thucydides.core.ThucydidesSystemProperty;

import java.util.concurrent.TimeUnit;

public class LoadConfig {
    private static String BASE_URL = null;


    public static String getURL() {
        return DataUtils.getValueConf(String.valueOf(ThucydidesSystemProperty.WEBDRIVER_BASE_URL));
    }

    public static long getWaitTimeoutInSecond() {
        long millisecond = Long.parseLong(DataUtils.getValueConf(String.valueOf(ThucydidesSystemProperty.WEBDRIVER_WAIT_FOR_TIMEOUT)));
        return TimeUnit.MILLISECONDS.toSeconds(millisecond);
    }

}



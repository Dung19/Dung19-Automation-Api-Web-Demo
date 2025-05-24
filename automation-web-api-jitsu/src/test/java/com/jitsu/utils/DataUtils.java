package com.jitsu.utils;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.guice.Injectors;
import net.thucydides.core.util.EnvironmentVariables;

public class DataUtils {

    public static String getValueConf(String config) {
        EnvironmentVariables environmentVariables = Injectors.getInjector().getInstance(EnvironmentVariables.class);
        return EnvironmentSpecificConfiguration.from(environmentVariables).getProperty(config);
    }
}

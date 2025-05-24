package com.jitsu.web.pages;


import com.jitsu.web.common.BasePage;
import com.jitsu.utils.LoadConfig;


import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;


public class WeatherPage extends BasePage {
    private final String INPUT_SEARCH = "//*[@id='desktop-menu']//input[@placeholder='Weather in your city']";
    private final String NAME_DISPLAY = "//*[@class='current-container mobile-padding']//h2";
    private final String DATE_DISPLAY = "//*[@class='current-container mobile-padding']//span";
    private final String TEMP_DISPLAY = "//*[@class='current-temp']//span";
    private final String HOURLY_FORECAST = "//*[@class='mobile-padding']";


    public void openWebAppWeather() {
        open();
        System.out.println("URL: " + LoadConfig.getURL());
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        waitForAllLoadingCompleted();
        waitForVisibilityOfElement(INPUT_SEARCH, TIMEOUT_IN_SECONDS);

    }

    public void searchNameCity(String nameCity) {
        waitTypeAndEnter(INPUT_SEARCH, nameCity);

    }
    public void clickOnItemLocal(String nameCity) {
        waitForVisibilityOfElement("//a[contains(text(),'" + nameCity + "')]", TIMEOUT_IN_SECONDS);
        waitAbit(500);
        clickOnElement("//a[contains(text(),'" + nameCity + "')]");

    }

    public void verifyCityNameDisplaySuccess(String nameCity) {
        waitAbit(500);
        waitForVisibilityOfElement(HOURLY_FORECAST, TIMEOUT_IN_SECONDS);
        assertThat(getText(NAME_DISPLAY)).isEqualTo(nameCity);
    }


    public void verifyCurrentDateDisplaySuccess(String timezone) {
        ZonedDateTime laTime = ZonedDateTime.now(ZoneId.of(timezone));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, hh:mma");
        assertThat(getText(DATE_DISPLAY).toLowerCase()).isEqualTo(laTime.format(formatter).toLowerCase());
    }

    public void verifyTemperatureDisplaySuccess() {
        boolean containsDigit = getText(TEMP_DISPLAY).matches(".*\\d.*");
        assertThat(containsDigit).isEqualTo(true);
    }
}


//
//    public void userInputUserNameAndPass(String username, String password) {
////        waitForAllLoadingCompleted();
//        waitAndType(INPUT_USER, username);
//        waitAndType(INPUT_PASS, password);
//    }
//    public void clickBtnLogin() {
//        clickOnElement(BTN_LOGIN);
//    }


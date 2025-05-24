package com.jitsu.web.steps;

import com.jitsu.web.pages.WeatherPage;
import net.thucydides.core.annotations.Step;

public class WeatherSteps {
    WeatherPage weatherPage;



    @Step
    public void openWebAppWeather() {
        weatherPage.openWebAppWeather();
    }
    @Step
    public void searchNameCity(String nameCity) {
        weatherPage.searchNameCity(nameCity);
    }
    @Step
    public void clickOnItemLocal(String nameCity) {
        weatherPage.clickOnItemLocal(nameCity);
    }
    @Step
    public void verifyCityNameDisplaySuccess(String nameCity) {
        weatherPage.verifyCityNameDisplaySuccess(nameCity);
    }
    @Step
    public void verifyCurrentDateDisplaySuccess(String timezone) {
        weatherPage.verifyCurrentDateDisplaySuccess(timezone);
    }
    @Step
    public void verifyTemperatureDisplaySuccess() {
        weatherPage.verifyTemperatureDisplaySuccess();
    }


//    @Step
//    public void userLoginUserNameAndPassWord(String username,String pass) {
//        weatherPage.userInputUserNameAndPass(username,pass);
//        weatherPage.clickBtnLogin();
//    }

}

package com.jitsu.web.defs;


import com.jitsu.web.steps.WeatherSteps;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class WeatherDefs {
    @Steps
    WeatherSteps weatherSteps;

    @When("^user Search the city:\"([^\"]*)\"$")
    public void searchNameCity(String nameCity) {
        weatherSteps.searchNameCity(nameCity);
    }
    @When("^user click on city name:\"([^\"]*)\"$")
    public void clickOnItemLocal(String nameCity) {
        weatherSteps.clickOnItemLocal(nameCity);
    }

    @When("^user open openweathermap$")
    public void openWebAppWeather() {
        weatherSteps.openWebAppWeather();
    }
    @When("^user verify city name display success with city name:\"([^\"]*)\"$")
    public void verifyCityNameDisplaySuccess(String city) {
        weatherSteps.verifyCityNameDisplaySuccess(city);
    }
    @When("^user verify current date display success with time zone:\"([^\"]*)\"$")
    public void verifyCurrentDateDisplaySuccess(String timezone) {
        weatherSteps.verifyCurrentDateDisplaySuccess(timezone);
    }
    @When("^user verify temperature display success$")
    public void verifyTemperatureDisplaySuccess() {
        weatherSteps.verifyTemperatureDisplaySuccess();
    }





//    @When("^user login to lazada with username \"([^\"]*)\" and password \"([^\"]*)\"$")
//    public void userLoginUserNameAndPassWord(String username,String pass) {
//        weatherSteps.userLoginUserNameAndPassWord(username,pass);
//    }
}

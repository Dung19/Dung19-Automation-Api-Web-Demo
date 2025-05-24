//package com.jitsu.web.pages;
//
//import com.jitsu.web.common.BasePage;
//import com.jitsu.utils.LoadConfig;
//import org.openqa.selenium.WebDriver;
//
//public class OpenweathermapPage extends BasePage {
//    WebDriver driver;
//    private final String XPATH_CHANGE_LANGUAGE = "//*[@id='kc-current-locale-link'] | //button[contains(@class, 'dropdown-toggle language-selection__button')]";
//    private final String CSS_USERNAME = "input#username";
//    private final String CSS_PASSWORD = "input#password";
//    private final String CSS_WELCOME_TITLE = "h2#kc-page-title";
//    private final String CSS_T24_USERNAME = "input#signOnName";
//    private final String CSS_T24_PASSWORD = "input#password";
//    private final String CSS_T24_BTN_LOGIN = "input[class='sign_in']";
//    private final String CSS_ALERT_LOGIN_FAIL = "div[class*='alert-body']";
//    private final String CSS_HEADER = "h2[id='kc-page-title']";
//    private final String CSS_LABEL_USERNAME = "label[for='username']";
//    private final String CSS_LABEL_PASSWORD = "label[for='password']";
//    private final String XPATH_ALERT_LOGIN = "//label[text()='%s']";
//
//
//
//    public String getTextLabelUsername(){
//        return getText(CSS_LABEL_USERNAME);
//    }
//
//    public String getTextLabelPassword(){
//        return getText(CSS_LABEL_PASSWORD);
//    }
//
//    public String getTextHeader(){
//        return getText(CSS_HEADER);
//    }
//
//
//    public void openWebApp() {
//        waitAbit(1000);
//        open();
//        Logging.info("URL: " + LoadConfig.getURL());
////        waitForAllLoadingCompleted();
////        waitForVisibilityOfElement(CSS_USERNAME);
////        waitForVisibilityOfElement(CSS_PASSWORD);
//        getDriver().manage().window().maximize();
//    }
//
//
//    public void inputPassword(String password) {
//        waitAndType(CSS_PASSWORD, password);
//    }
//
//
//
//    public void clickBtnLogin() {
//        String cssSelector = "input[name=login]";
//        clickOnElement(cssSelector);
//    }
//
//    public void clickBtnLoginT24() {
//        clickOnElement(CSS_T24_BTN_LOGIN);
//        waitAbit(2000);
//    }
//
//    public String getTextWrongLoginInformation(String mesWrongLoginInformation){
//        return getText( String.format(XPATH_ALERT_LOGIN, mesWrongLoginInformation));
//    }
//
//}

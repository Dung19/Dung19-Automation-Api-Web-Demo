package com.jitsu.web.common;


import com.jitsu.utils.LoadConfig;
import io.appium.java_client.functions.ExpectedCondition;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BasePage extends PageObject {
    public static final long TIMEOUT_IN_SECONDS = LoadConfig.getWaitTimeoutInSecond();

    // <!---------------------------------------------------- BEGIN COMMON ------------------------------------------------------------>
    public <T> T executeJS(String command) {
        JavascriptExecutor jsExec = (JavascriptExecutor) getDriver();
        return (T) jsExec.executeScript(command);
    }

    public void highlightElement(WebElementFacade element) {
        executeJS("arguments[0].style.border='2px solid red'", element);
    }

    public <T> T executeJS(String script, WebElementFacade element) {
        return (T) ((JavascriptExecutor) getDriver()).executeScript(script, element);
    }

    public void waitForAllLoadingCompleted() {
        waitUntilJQueryRequestCompleted(TIMEOUT_IN_SECONDS);
        waitForJQueryLoadingCompleted(TIMEOUT_IN_SECONDS);
        waitUntilHTMLReady(TIMEOUT_IN_SECONDS);
    }

    public void waitUntilJQueryRequestCompleted(long timeoutInSeconds) {
        new FluentWait<>(getDriver()).withTimeout(Duration.of(timeoutInSeconds, ChronoUnit.SECONDS))
                .withMessage("**** INFO **** JQUERY STILL LOADING FOR OVER" + timeoutInSeconds + "SECONDS.")
                .pollingEvery(Duration.of(300, ChronoUnit.MILLIS)).until((ExpectedCondition<Boolean>) driver -> {
                    try {
                        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
                        return (Boolean) jsExec.executeScript("return jQuery.active === 0");
                    } catch (Exception e) {
                        return true;
                    }
                });
    }

    public void waitForJQueryLoadingCompleted(long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeoutInSeconds);
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            try {
                return ((Long) ((JavascriptExecutor) getDriver()).executeScript("return $.active") == 0);
            } catch (Exception e) {
                // no jQuery present
                return true;
            }
        };
        wait.until(jQueryLoad);
    }

    public void waitUntilHTMLReady(long timeoutInSeconds) {
        new FluentWait<>(getDriver()).withTimeout(Duration.of(timeoutInSeconds, ChronoUnit.SECONDS))
                .pollingEvery(Duration.of(300, ChronoUnit.MILLIS)).until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver d) {
                        try {
                            JavascriptExecutor jsExec = (JavascriptExecutor) d;
                            return jsExec.executeScript("return document.readyState").toString().equals("complete");
                        } catch (Exception e) {
                            return true;
                        }
                    }
                });
    }

    public boolean isElementExist(String xPath) {
        boolean isExist = false;
        List<WebElementFacade> e = findAllElements(xPath);
        if (e.size() != 0) {
            highlightElement(e.get(0));
            isExist = true;
        }
        return isExist;
    }

    public void scrollElementIntoView(WebElementFacade element) {
        executeJS("arguments[0].scrollIntoView(false);", element);
    }

    public void quitDriver() {
        getDriver().manage().deleteAllCookies();
        getDriver().quit();
    }

    public void clearCache() {
        getDriver().manage().deleteAllCookies();
    }

    public void waitUntilElementReady(WebElementFacade element) {
        scrollElementIntoView(element);
        element.waitUntilClickable();
    }

    public By isXpathOrCssSelector(String value) {
        if (value.contains("//")) return By.xpath(value);
        else return By.cssSelector(value);
    }



    public WebElementFacade getElement(String xpathOrCss) {
        return find(isXpathOrCssSelector(xpathOrCss));
    }

    public List<WebElementFacade> findAllElements(String xpathOrCss) {
        return findAll(xpathOrCss);
    }


    public void clickOnElement(String xpathOrCss) {
        WebElementFacade element = getElement(xpathOrCss);
        element.click();
    }


    public String getText(String xpathOrCss) {
        WebElementFacade element = getElement(xpathOrCss);
        scrollElementIntoView(element);
        highlightElement(element);
        return element.getText();
    }

    public void switchToBrowserTabByIndex(int index) {
        String siteHandle = ((String) getAllBrowserTab().toArray()[index]);
        getDriver().switchTo().window(siteHandle);

    }

    public Set<String> getAllBrowserTab() {
        return getDriver().getWindowHandles();
    }

    public void waitForVisibilityOfElement(String xpathOrCss, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(isXpathOrCssSelector(xpathOrCss)));
    }


    public void waitForInvisibilityOfElement(String xpathOrCss, long timeOutInSecond) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSecond);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(isXpathOrCssSelector(xpathOrCss)));
    }


    public void waitForPresenceOfElement(String xpathOrCss, long timeOutInSecond) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSecond);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(isXpathOrCssSelector(xpathOrCss)));
    }

    public void waitAbit(int millisecond) {
        waitABit(millisecond);
    }

    public void waitTypeAndEnter(String xpathOrCss, String value) {
        WebElementFacade element = getElement(xpathOrCss);
        waitUntilElementReady(element);
        highlightElement(element);
        element.clear();
        element.typeAndEnter(value);
    }
}

package com.qaworks.test.framework.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by matthewtully on 10/11/2015.
 */
public class WaitHelper {

    private static WaitHelper instance = null;

    private WaitHelper() {

    }

    public static WaitHelper getInstance() {
        if (instance == null) {
            instance = new WaitHelper();
        }
        return instance;
    }

    /**
     *
     */
    public WebElement waitForElement(WebDriver driver, String elementId, String locatorType) throws Exception {

        WebElement element = null;
        By locator = null;

        //TODO configuration
        int timeOutSeconds = 5;

        WebDriverWait wait = new WebDriverWait(driver, timeOutSeconds);

        // locator type
        if (locatorType.equalsIgnoreCase("id")) {
            locator = By.id(elementId);
        }
        if (locatorType.equalsIgnoreCase("linkText")) {
            locator = By.linkText(elementId);
        }
        if (locatorType.equalsIgnoreCase("cssSelector")) {
            locator = By.cssSelector(elementId);
        }
        //TODO add remaming locator types

        try {
            element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            String pageTitle = driver.getTitle();
            String currentURL = driver.getCurrentUrl();
            String message = "Element not found on page title: " + pageTitle + " and page url: " + currentURL;

            throw new Exception(message);
        }

        return element;

    }
}

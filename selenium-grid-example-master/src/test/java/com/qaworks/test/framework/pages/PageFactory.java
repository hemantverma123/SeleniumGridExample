package com.qaworks.test.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

/**
 * Created by matthewtully on 09/11/2015.
 */
public class PageFactory {

    private static WebDriver driver = null;

    /**
     *
     */
    public static HomePage getHomePage() throws Exception {
        HomePage homePage = new HomePage(getDriver());

        return homePage;
    }

    /**
     *
     */
    public static ContactPage getContactPage() throws Exception {
        ContactPage contactPage = new ContactPage(getDriver());

        return contactPage;
    }


    public static WebDriver getFireFoxDriver() {
        driver = new FirefoxDriver();

        return driver;
    }

    public static WebDriver getChromeDriver() {
        //System.setProperty("webdriver.chrome.driver", "//Users//matttully//dev//drivers//chromedriver.exe");
    	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
        driver = new ChromeDriver();

        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriver getRemoteWebDriver() throws Exception {

        driver = new RemoteWebDriver(
                new URL("http://localhost:4441/wd/hub"),
                DesiredCapabilities.firefox());

        return driver;

    }

    public static void tearDown() {
        if (driver != null) {
            try {
                driver.manage().deleteAllCookies();
            } catch (Exception e) {
                //TODO logging
                System.out.println("Exception: Unable to delete all coookies!");

            }
            try {
                driver.quit();
            } catch (Exception e) {
                //TODO logging
                System.out.println("Exception: Unable execute driver.quit");
            }
        }
    }


}

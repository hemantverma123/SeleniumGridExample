package com.qaworks.test.framework.pages;

import com.qaworks.test.framework.helpers.WaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by matthewtully on 09/11/2015.
 */
public class BasePage {

    protected WebDriver driver;

    protected static final String FILE_EXTENSION = ".properties";

    protected Properties pageProperties = new Properties();

    /**
     *
     */
    public BasePage(WebDriver driver, String pageName) throws Exception {

        this.driver = driver;
        String fileName = null;
        fileName = pageName + FILE_EXTENSION;
        loadPageProperties(fileName);

    }

    /**
     *
     */
    public WebElement getElement(String elementName) throws Exception {

        WebElement webElement = null;
        String elementType = this.getProperty(elementName + "Type");

        //TODO get page attribute for element name
        webElement = WaitHelper.getInstance().waitForElement(driver, this.getProperty(elementName), elementType);

        return webElement;

    }

    /**
     *
     */
    public void clickElement(String elementName) throws Exception {

        WebElement webElement = getElement(elementName);
        webElement.click();

    }

    /**
     *
     */
    public void setInputFieldWithText(String elementName, String value) throws Exception {

        WebElement webElement = getElement(elementName);
        webElement.clear();
        webElement.sendKeys(value);

    }

    /**
     *
     */
    public void loadPageProperties(String fileName) throws Exception {

        InputStream inStream;
        String basePath = "src/test/java/com/qaworks/test/framework/pages/";

        try {
            String fileNameAndPath = basePath + fileName;
            inStream = new FileInputStream(fileNameAndPath);
            pageProperties.load(inStream);
        } catch (IOException e) {
            throw new Exception("Cannot load page properties for page: " + fileName + e.getMessage());
        }
    }

    /**
     *
     */
    public String getProperty(String key) throws Exception {

        try {
            String value = pageProperties.getProperty(key);
            return value;
        } catch (NullPointerException e) {
            throw new Exception("Page property not found for: " + key + " in properties file");
        }

    }

    public String getElementText(String elementName) throws Exception {

        WebElement webElement = getElement(elementName);
        return webElement.getText();

    }
}

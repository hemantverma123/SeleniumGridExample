package com.qaworks.test.cucumber.hooks;

import com.qaworks.test.framework.config.Configuration;
import cucumber.api.java.After;
import cucumber.api.java.Before;
//import org.junit.Before;
import org.openqa.selenium.WebDriver;
import com.qaworks.test.framework.pages.PageFactory;

/**
 * Created by matthewtully on 09/11/2015.
 */
public class CucumberHooks {

    @Before
    public void beforeScenario() throws Exception {

        Configuration config = Configuration.getConfiguration();

        String browser = config.getBrowserType();

        WebDriver driver = null;

        if (config.getIsGridRun()) {

            driver = PageFactory.getRemoteWebDriver();

        } else {

            if (browser.equals("chrome")) {
                driver = PageFactory.getChromeDriver();

            } else if (browser.equals("firefox")) {
                driver = PageFactory.getFireFoxDriver();

            } else if (browser.equals("internetexplorer")) {
                //driver = PageFactory.getInternetExploerDriver();

            } else {
                throw new Exception();
            }
            //maximise window
            driver.manage().window().maximize();
        }
    }

    @After
    public void tearDown() throws Exception {

        PageFactory.tearDown();

    }

}

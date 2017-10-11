package com.qaworks.test;

import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-html-report"},
        features = {"src/test/resources/com/qaworks/test/contact/"}
)
public class TestRunner {
}
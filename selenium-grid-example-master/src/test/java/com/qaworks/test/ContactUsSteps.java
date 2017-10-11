package com.qaworks.test;

import com.qaworks.test.framework.pages.ContactPage;
import com.qaworks.test.framework.pages.HomePage;
import com.qaworks.test.framework.pages.PageFactory;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class ContactUsSteps {

    private Map<String, String> emailDetails;

    @Given("^I am on the QAWorks Staging Site$")
    public void getQAWorksStagingSite() throws Throwable {

        HomePage homePage = PageFactory.getHomePage();
        homePage.navigate();
    }

    @Then("^I should be able to contact QAWorks with the following information$")
    public void submitEmailMessage(Map<String, String> emailDetails) throws Throwable {

        // open contacts page.
        HomePage homePage = PageFactory.getHomePage();
        homePage.clickElement("ContactLink");

        // submit message
        this.emailDetails = emailDetails;
        ContactPage contactPage = PageFactory.getContactPage();
        contactPage.setInputFieldWithText("nameField", emailDetails.get("name"));
        contactPage.setInputFieldWithText("emailField", emailDetails.get("email"));
        contactPage.setInputFieldWithText("messageField", emailDetails.get("message"));
        contactPage.clickElement("sendButton");

    }

    @Then("^I should be able to view the contact telephone number (.*)$")
    public void verifyContactTelephoneNumber(String telephoneNumber) throws Throwable {

        // open contacts page.
        HomePage homePage = PageFactory.getHomePage();
        homePage.clickElement("ContactLink");

        // get telephone number
        ContactPage contactPage = PageFactory.getContactPage();
        String displayedTelephoneNumber = contactPage.getTelephoneNumber();

        // compare with expected value
        assertTrue("Contact telephone number not displayed correctly. Expected = " + telephoneNumber + " Actual = " + displayedTelephoneNumber, telephoneNumber.equals(displayedTelephoneNumber));

    }
}
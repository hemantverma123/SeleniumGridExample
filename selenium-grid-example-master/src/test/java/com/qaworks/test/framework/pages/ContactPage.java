package com.qaworks.test.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by matthewtully on 09/11/2015.
 */
public class ContactPage extends BasePage {

    public ContactPage(WebDriver driver) throws Exception {
        super(driver, "ContactPage");

    }

    public String getTelephoneNumber() throws Exception {

        Map<String, String> addressDetails = getAddressDetails();

        return addressDetails.get("TelNumber");

    }

    private Map<String, String> getAddressDetails() throws Exception {

        Map<String, String> addressDetails = new HashMap<String, String>();
        String[] addressDetailsString = getElementText("addressDetails").split("\\n");

        addressDetails.put("AddrLine1", addressDetailsString[1]);
        addressDetails.put("AddrLine2", addressDetailsString[2]);
        addressDetails.put("Town", addressDetailsString[3]);
        addressDetails.put("PostCode", addressDetailsString[4]);
        addressDetails.put("TelNumber", addressDetailsString[6]);

        return addressDetails;

    }

}

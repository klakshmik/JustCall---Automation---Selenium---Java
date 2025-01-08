package org.justcall.phonenumbers;

import io.qameta.allure.Description;
import org.justcall.base.BaseTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IncomingCallsTest extends BaseTest {
    @Test(priority = 1)
    @Description("This test verifies that unanswered incoming calls are forwarded to the correct number.")
    public void forwardUnansweredCallTest() {
        IncomingCalls incomingCalls = PageFactory.initElements(driver, IncomingCalls.class);
        incomingCalls.forwardUnansweredCallsToNumber(testRunPropertyReader.getPropertyMethod("reusableURL"));
        Assert.assertEquals(incomingCalls.getSuccessMsgText(),"Changes Saved");
    }

    @Test(priority = 2)
    @Description("This test verifies that the IVR message is correctly set up for incoming calls.")
    public void ivrSetUpTest() {
        IncomingCalls incomingCalls = PageFactory.initElements(driver, IncomingCalls.class);
        incomingCalls.setupIVR(testRunPropertyReader.getPropertyMethod("reusableURL"));
        Assert.assertEquals(incomingCalls.getSuccessMessageText(),"IVR message updated successfully");
    }

}

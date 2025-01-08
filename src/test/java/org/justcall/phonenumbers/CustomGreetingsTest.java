package org.justcall.phonenumbers;

import io.qameta.allure.Description;
import org.justcall.base.BaseTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CustomGreetingsTest extends BaseTest {

    @Test(priority = 1)
    @Description("This test verifies that the outbound call is initiated with the correct welcome message.")
    public void startOutboundCallWithMsgTest() {
        CustomGreetings customGreetings = PageFactory.initElements(driver, CustomGreetings.class);
        customGreetings.startOutboundCallWithMsg(testRunPropertyReader.getPropertyMethod("reusableURL"));
        Assert.assertEquals(customGreetings.getSuccessMsgText(),"Welcome message updated successfully");
    }

    @Test(priority = 2)
    @Description("This test verifies that the queue callback message is set correctly.")
    public void setQueueCallbackMsgTest() {
        CustomGreetings customGreetings = PageFactory.initElements(driver, CustomGreetings.class);
        customGreetings.setQueueCallbackMessageText(testRunPropertyReader.getPropertyMethod("reusableURL"));
        Assert.assertEquals(customGreetings.getSuccessMessageText(),"Queue Callback message updated successfully");
    }
}

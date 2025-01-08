package org.justcall.settings;

import io.qameta.allure.Description;
import org.justcall.base.BaseTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DialerSettingsTest extends BaseTest {
    @Test
    @Description("This test updates the wrap-up time setting and verifies that the update was successful.")
    public void setWrapUpTimeTest() {
        DialerSettings dialerSettings = PageFactory.initElements(driver, DialerSettings.class);
        dialerSettings.setWrapUpTime();
        Assert.assertEquals(dialerSettings.successText(),"Wrapup time updated successfully");
    }
}

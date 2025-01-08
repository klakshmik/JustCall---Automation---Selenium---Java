package org.justcall.phonenumbers;

import io.qameta.allure.Description;
import org.justcall.base.BaseTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BusinessHoursTest extends BaseTest {
    @Test
    @Description("This test verifies that calls are forwarded to voicemail after business hours are updated successfully.")
    public void forwardCallAfterBusinessHoursToVoiceMailTest() {

        BusinessHours businessHours = PageFactory.initElements(driver, BusinessHours.class);
        businessHours.forwardCallAfterBusinessHoursToVoicemail(this.testRunPropertyReader.getPropertyMethod("reusableURL"));
        Assert.assertEquals(businessHours.getSuccessText(),"Working hours updated");
    }

}

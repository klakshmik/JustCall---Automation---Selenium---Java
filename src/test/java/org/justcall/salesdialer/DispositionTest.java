package org.justcall.salesdialer;

import io.qameta.allure.Description;
import org.justcall.base.BaseTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DispositionTest extends BaseTest {
    @Test(priority = 1)
    @Description("This test creates a new disposition group and verifies that the group was created successfully")
    public void createDispositionGroupTest() {
        Disposition disposition = PageFactory.initElements(driver, Disposition.class);
        disposition.createCallDispositionGroup();
        Assert.assertEquals(disposition.getSuccessMsgText(),"Group created successfully!");
    }
}

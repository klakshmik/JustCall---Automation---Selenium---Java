package org.justcall.salesdialer;

import io.qameta.allure.Description;
import org.justcall.base.BaseTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SalesDialerTest extends BaseTest {

    @Test
    @Description("This test sets up the reattempt rules and verifies that the rules were updated successfully.")
    public void setupReattemptRulesTest() {
        SalesDialer salesDialer = PageFactory.initElements(driver, SalesDialer.class);
        salesDialer.setupReattemptRules();
        Assert.assertEquals(salesDialer.getSuccessMsg(),"Rules updated successfully");
    }


}

package org.justcall.dialer;

import io.qameta.allure.Description;
import org.justcall.base.BaseTest;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TransferOutboundCallTest extends BaseTest {

    @Test
    @Description("This test verifies the successful transfer of an outbound call. It ensures that the call transfer is initiated and the processing status is updated to 'Processing..'.")
    public void transferOutboundCallTest() {
        TransferOutboundCall transferOutboundCall = PageFactory.initElements(driver, TransferOutboundCall.class);
        transferOutboundCall.performOutboundCallTransfer();
        Assert.assertEquals(transferOutboundCall.getProcessingStatusText(),"Processing..");

    }
}

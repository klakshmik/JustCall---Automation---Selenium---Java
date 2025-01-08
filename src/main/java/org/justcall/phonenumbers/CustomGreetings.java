package org.justcall.phonenumbers;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.justcall.base.WebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Slf4j
public class CustomGreetings extends WebPage {

    @FindBy(css = "span[id='welcomeDisplayMsg'] + a")
    protected WebElement addMsgButton;

    @FindBy(css = "span[id='welcomeDisplayMsg'] + a + a")
    protected WebElement editMsgButton;

    @FindBy(css = "textarea[placeholder='Welcome Message']")
    protected WebElement welcomeMsgTextArea;

    @FindBy(css = "div[id='hs'] > div:nth-child(3) > a:first-child")
    protected WebElement saveMsgButton;

    @FindBy(css = "a[class='gritter-close'] + p")
    protected WebElement successMsgToast;

    @FindBy(css = "#sidebar_show_default > ul > li:nth-child(5) > a > span:nth-child(2)")
    protected WebElement phoneNumbersSidebar;

    @FindBy(css = "div[class='tocify'] > ul > li > a")
    protected List<WebElement> settingsOptionsList;

    @FindBy(css = "span[id='queueCallbackDisplayMsg'] + a")
    protected WebElement addQueueMsgButton;

    @FindBy(css = "span[id='queueCallbackDisplayMsg'] + a + a")
    protected WebElement editQueueMsgButton;

    @FindBy(css = "div[id='showCustomQueueCallbackRow'] > div > form > div:nth-child(4) > div:first-child > div:nth-child(2) > textarea")
    protected WebElement queueMsgTextArea;

    @FindBy(css = "div[id='showCustomQueueCallbackRow'] > div > form > div:nth-child(4) > div:nth-child(3) > a:first-child")
    protected WebElement saveChangesButton;

    @FindBy(css = "a[class='gritter-close'] + p")
    protected WebElement successMessageToast;

    public CustomGreetings(WebDriver driver) {
        super(driver);
    }

    @Step("Start outbound call and configure welcome message")
    public void startOutboundCallWithMsg(String reusableURL) {
        waitForElementToBeClickable(phoneNumbersSidebar);
        phoneNumbersSidebar.click();
        driver.get(reusableURL);
        if (addMsgButton.isDisplayed()) {
            addMsgButton.click();
        } else {
            editMsgButton.click();
        }
        welcomeMsgTextArea.clear();
        welcomeMsgTextArea.sendKeys("Hello..");
        saveMsgButton.click();

    }
    @Step("Get success message after saving welcome message")
    public String getSuccessMsgText() {
        return successMsgToast.getText();
    }

    @Step("Set or update queue callback message")
    public void setQueueCallbackMessageText(String reusableURL) {
        driver.get(reusableURL);
        selectSettingOptionFromList("Custom Greetings");
        if (addQueueMsgButton.isDisplayed()) {
            addQueueMsgButton.click();
        } else {
            editQueueMsgButton.click();
        }
        queueMsgTextArea.sendKeys("Thanks for placing the callback request. We will reach out to you in some time. GoodBye.");
        saveChangesButton.click();

    }
    @Step("Select a setting option from the settings list")
    public void selectSettingOptionFromList(String option) {
        for (WebElement element : settingsOptionsList) {
            if (option.equalsIgnoreCase(element.getText())) {
                element.click();
                break;
            }
        }
    }
    @Step("Get success message after saving queue callback message")
    public String getSuccessMessageText() {
        return successMessageToast.getText();
    }

}

package org.justcall.phonenumbers;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.justcall.base.WebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Set;

@Slf4j
public class IncomingCalls extends WebPage {

    @FindBy(css = "div[class='tocify'] > ul > li > a")
    protected List<WebElement> settingsOptionsList;

    @FindBy(css = "div[id='forwardcall_bck'] > label")
    protected WebElement forwardCallRadio;

    @FindBy(css = "select[id='forward_unanswered_select']")
    protected WebElement unansweredCallsDropdown;

    @FindBy(css = "button[id='save_fwd_mynumber']")
    protected WebElement saveChangesButton;

    @FindBy(css = "a[class='gritter-close'] + p")
    protected WebElement successMsgToast;

    @FindBy(css = "#showdetails > div.col-md-10 > div > div > div > div:nth-child(2) > div > div > div > form > div > label")
    protected List<WebElement> incomingCallsSettings;

    @FindBy(css = "span[class='dv-notification-list-learn-more'] > a")
    protected WebElement installNowButton;

    @FindBy(css = "#modal-confirm_mlivr > div > div > div.modal-footer > button.btn.btn-primary")
    protected WebElement updateInstallButton;

    @FindBy(css = "div[class='ivr-div'] > a")
    protected WebElement createIvrButton;

    @FindBy(css = "input[id='ivr-name']")
    protected WebElement ivrNameInputField;

    @FindBy(css = "span[id='playoptionDisplayMsg'] + a")
    protected WebElement addGreetingMsgButton;

    @FindBy(css = "span[id='playoptionDisplayMsg'] + a + a")
    protected WebElement editGreetingMsgButton;

    @FindBy(css = "input[id='ttpo']")
    protected WebElement greetingTextRadioButton;

    @FindBy(css = "div[id='hs'] > div:first-child > div:nth-child(2) > textarea")
    protected WebElement greetingTextArea;

    @FindBy(css = "div[id='hs'] > div:nth-child(3) > a:first-child")
    protected WebElement saveGreetingMsgButton;

    @FindBy(css = "span[id='nooptionDisplayMsg'] + a")
    protected WebElement addNoOptionMsgButton;

    @FindBy(css = "span[id='nooptionDisplayMsg'] + a + a")
    protected WebElement editNoOptionMsgButton;

    @FindBy(css = "div[id='vmhs'] > div:first-child > div:nth-child(2) > textarea")
    protected WebElement noOptionMsgTextArea;

    @FindBy(css = "div[id='vmhs'] > div:nth-child(2) > a:first-child")
    protected WebElement saveNoOptionMsgButton;

    @FindBy(css = "input[id='ttno']")
    protected WebElement noOptionTextRadioButton;

    @FindBy(css = "span[id='wrong-optionDisplayMsg'] + a")
    protected WebElement addWrongOptionMsgButton;

    @FindBy(css = "span[id='wrong-optionDisplayMsg'] + a + a")
    protected WebElement editWrongOptionMsgButton;

    @FindBy(css = "input[id='ttwo']")
    protected WebElement wrongOptionMsgTextRadioButton;

    @FindBy(css = "div[id='evmhs'] > div:first-child > div:nth-child(2) > textarea")
    protected WebElement wrongOptionTextArea;

    @FindBy(css = "div[id='evmhs'] > div:nth-child(2) > a:first-child")
    protected WebElement saveWrongOptionMsgButton;

    @FindBy(css = "div[class='digit-col column'] > select")
    protected WebElement digitSelectDropdown;

    @FindBy(css = "div[class='custom-col column'] > textarea")
    protected WebElement customMsgTextArea;

    @FindBy(css = "input[class='form-control outline-input forward_unanswered_voicemail']")
    protected WebElement emailInputField;

    @FindBy(css = "div[class='ivr-add-option-div section'] > button:nth-child(2)")
    protected WebElement saveChangeButton;

    @FindBy(css = "a[class='gritter-close'] + p")
    protected WebElement successMessgeToast;

    public IncomingCalls(WebDriver driver) {
        super(driver);
    }

    @Step("Forward unanswered calls to a specific number")
    public void forwardUnansweredCallsToNumber(String reusableURL) {

        driver.get(reusableURL);
        selectSettingOptionFromList("incoming calls");
        if (!forwardCallRadio.isSelected()) {
            forwardCallRadio.click();
        }
        Select select = new Select(unansweredCallsDropdown);
        select.selectByIndex(1);
        pause(2);
        saveChangesButton.click();
    }

    @Step("Select a setting option from the list")
    public void selectSettingOptionFromList(String option) {
        for (WebElement element : settingsOptionsList) {
            if (option.equalsIgnoreCase(element.getText())) {
                element.click();
                break;
            }
        }
    }

    @Step("Get success message after saving changes")
    public String getSuccessMsgText() {
        return successMsgToast.getText();
    }

    @Step("Set up IVR with greeting messages and options")
    public void setupIVR(String reusableURL) {
        driver.get(reusableURL);
        clickInstallButton();
        clickCreateIVRButton();
        handleIvrNameInputField();
        handleOptionFields();
        handleNoOptionMsgTextArea();
        handleWrongOptionMsgButton();
        handleSaveChangeButton();

    }

    private void handleSaveChangeButton() {
        Select digitSelect = new Select(digitSelectDropdown);
        digitSelect.selectByIndex(1);
        customMsgTextArea.sendKeys("Forward call to sales");
        emailInputField.sendKeys("lakshmi29kumari@gmail.com");
        saveChangeButton.click();
    }

    private void handleWrongOptionMsgButton() {
        if (addWrongOptionMsgButton.isDisplayed()) {
            waitForElementToBeClickable(addWrongOptionMsgButton);
            addWrongOptionMsgButton.click();
        } else {
            waitForElementToBeClickable(editWrongOptionMsgButton);
            editWrongOptionMsgButton.click();
        }
        if (!wrongOptionMsgTextRadioButton.isSelected()) {
            wrongOptionMsgTextRadioButton.click();
        }
        wrongOptionTextArea.sendKeys("Oops, that is not a valid option. Let's repeat your choices.");
        waitForElementToBeClickable(saveWrongOptionMsgButton);
        saveWrongOptionMsgButton.click();
    }

    private void handleNoOptionMsgTextArea() {
        noOptionMsgTextArea.click();
        noOptionMsgTextArea.sendKeys("Oops, it looks like you did not select an option.");
        waitForElementToBeClickable(saveNoOptionMsgButton);
        saveNoOptionMsgButton.click();
    }

    private void handleOptionFields() {
        if (!greetingTextRadioButton.isSelected()) {
            waitForElementToBeClickable(greetingTextRadioButton);
            greetingTextRadioButton.click();
        } else {
            greetingTextArea.sendKeys("Press 1 for sales,Press 2 for support.");
            waitForElementToBeClickable(saveGreetingMsgButton);
            saveGreetingMsgButton.click();
        }

        if (addNoOptionMsgButton.isDisplayed()) {
            waitForElementToBeClickable(addNoOptionMsgButton);
            addNoOptionMsgButton.click();
        } else {
            waitForElementToBeClickable(editNoOptionMsgButton);
            editNoOptionMsgButton.click();
        }
        if (!noOptionTextRadioButton.isSelected()) {
            waitForElementToBeClickable(noOptionTextRadioButton);
            noOptionTextRadioButton.click();
        }
    }

    private void handleIvrNameInputField() {
        waitForElementToBeVisible(ivrNameInputField);
        ivrNameInputField.sendKeys("Sales IVR");
        if (addGreetingMsgButton.isDisplayed()) {
            waitForElementToBeClickable(addGreetingMsgButton);
            addGreetingMsgButton.click();
        } else {
            waitForElementToBeClickable(editGreetingMsgButton);
            editGreetingMsgButton.click();
        }
    }

    private void clickCreateIVRButton() {
        Set<String> parentWindows = driver.getWindowHandles();
        createIvrButton.click();

        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!parentWindows.contains(handle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }

    private void clickInstallButton() {
        selectSettingOptionFromList("incoming calls");
        selectOptionFromIncomingCallsSettings(1);
        installNowButton.click();
        updateInstallButton.click();
    }

    @Step("Select an option from incoming calls settings")
    public void selectOptionFromIncomingCallsSettings(int index) {
        WebElement element = incomingCallsSettings.get(index);
        if (!element.isSelected()) {
            element.click();
        } else {
            log.info("Incoming call option element is selected");
        }
    }

    @Step("Get success message text after configuring incoming calls")
    public String getSuccessMessageText() {
        return successMsgToast.getText();
    }

}

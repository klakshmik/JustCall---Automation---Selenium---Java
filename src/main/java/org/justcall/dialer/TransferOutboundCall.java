package org.justcall.dialer;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.justcall.base.WebPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.Set;
import java.util.List;

public class TransferOutboundCall extends WebPage {
    @FindBy(css = "span[class='btn btn-primary fixmsgmargin']")
    protected WebElement dialerButton;

    @FindBy(css = "div[class='row bottom-tab'] > div > div")
    protected List<WebElement> bottomNavigationButtons;

    @FindBy(css = "div[class='row'] > ul:nth-child(3) > li")
    protected List<WebElement> contactList;

    @FindBy(css = "div[class='row'] > ul:nth-child(3) > li:first-child > div:first-child > button:first-child")
    protected WebElement contactPhoneIcon;

    @FindBy(css = "button[id='call-btn']")
    protected WebElement startCallButton;

    @FindBy(css = "button[onclick='transfer_screen();'] > div")
    protected WebElement transferCallButton;

    @FindBy(css = "input[id='transfer_search']")
    protected WebElement searchTransferInputField;

    @FindBy(css = "div[id='transferscreen'] > ul:nth-child(4) > li:first-child > span:first-child")
    protected WebElement teamMemberSelection;

    @FindBy(css = "span[id='btn_transfer']")
    protected WebElement confirmTransferButton;

    @FindBy(css = "textarea[id='draggable_textarea_transfer']")
    protected WebElement transferNotesTextArea;

    @FindBy(css = "#transfer_with_notes_div > div > div:nth-child(2) > div > div:nth-child(3)")
    protected List<WebElement> transferOptionsWithNotes;

    @FindBy(xpath = "//span[text()='Processing..']")
    protected WebElement processingStatusText;

    public TransferOutboundCall(WebDriver driver) {
        super(driver);
    }



    @Step("Initiate outbound call and transfer to a team member")
    public void performOutboundCallTransfer() {
        preStep();

        clickBottomNavTab();
        selectContactByIndex(0);
        startCallButton.click();
        pause(2);

        performAction();

    }

    @Step("Search for team member and perform call transfer")
    private void performAction() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", transferCallButton);
        searchTransferInputField.click();
        searchTransferInputField.sendKeys("then");
        searchTransferInputField.sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("div[id='transferscreen'] > ul:nth-child(4) > li:first-child > span:first-child")).click();
        pause(DELAY_TEST_TIME);
        jsExecutor.executeScript("arguments[0].click();", confirmTransferButton);
        pause(DELAY_TEST_TIME);
        transferNotesTextArea.sendKeys("Hey there");
        pause(DELAY_TEST_TIME);
        selectTransferWithNotesByIndex(0);
    }


    @Step("Prepare the environment for the outbound call transfer")
    private void preStep() {
        String parentWindowHandle = driver.getWindowHandle();
        dialerButton.click();

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(parentWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }

    @Step("Navigate to the contacts section")
    private void clickBottomNavTab() {
        String tabName = "contacts";
        for (WebElement button : bottomNavigationButtons) {
            String text = button.getText();
            if (text.equalsIgnoreCase(tabName)) {
                button.click();
                break;
            }
        }
    }

    @Step("Select contact and start call")
    private void selectContactByIndex(int index) {
        WebElement element = contactList.get(index);
        action.moveToElement(element).build().perform();
        waitForElementToBeVisible(contactPhoneIcon).click();
    }

    @Step("Select transfer option with notes")
    private void selectTransferWithNotesByIndex(int index) {
        WebElement element = transferOptionsWithNotes.get(index);
        element.click();
    }


    @Step("Get processing status text")
    public String getProcessingStatusText() {
        return processingStatusText.getText();
    }
}

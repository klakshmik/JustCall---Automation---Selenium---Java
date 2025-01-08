package org.justcall.phonenumbers;

import io.qameta.allure.Step;
import org.justcall.base.WebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class BusinessHours extends WebPage {
    @FindBy(css = "#sidebar_show_default > ul > li.active > a > span:nth-child(2)")
    protected WebElement phoneNumbersSidebar;

    @FindBy(css = "div[class='tocify'] > ul > li > a")
    protected List<WebElement> settingsOptions;

    @FindBy(css = "select[id='select_afteroffice']")
    protected WebElement selectAfterHours;

    @FindBy(css = "input[id='voicemail_email_input']")
    protected WebElement emailInputField;

    @FindBy(css = "button[id='workinghourmodalbutton']")
    protected WebElement updateBusinessHoursButton;

    @FindBy(css = "a[class='gritter-close'] + p")
    protected WebElement successMsgToast;


    public BusinessHours(WebDriver driver) {
        super(driver);
    }


    @Step("Configure call forwarding after business hours to voicemail")
    public void forwardCallAfterBusinessHoursToVoicemail(String reusableURL) {
        pause(DELAY_TEST_TIME);
        phoneNumbersSidebar.click();
        driver.get(reusableURL);
        selectSettingOptionFromList("Business Hours");
        Select select = new Select(selectAfterHours);
        select.selectByIndex(0);
        emailInputField.sendKeys("lakshmi29kumari@gmail.com");
        updateBusinessHoursButton.click();

    }


    @Step("Select a setting option from the settings menu")
    public void selectSettingOptionFromList(String option) {
        for (WebElement element : settingsOptions) {
            if (option.equalsIgnoreCase(element.getText())) {
                element.click();
                break;
            }
        }
    }


    @Step("Retrieve success message after settings update")
    public String getSuccessText() {
        return successMsgToast.getText();
    }
}

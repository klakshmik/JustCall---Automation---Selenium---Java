package org.justcall.salesdialer;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.justcall.base.WebPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Slf4j
public class SalesDialer extends WebPage {

    List<Integer> optionList = Arrays.asList(0, 1, 2, 3, 4);

    @FindBy(css = "ul[class='sidebar-elements'] > li > a > span + span")
    protected List<WebElement> leftSidebarElements;

    @FindBy(css = "ul[id='campaigns-navigation'] > li > a > i + span")
    protected List<WebElement> salesDialerSidebarMenu;

    @FindBy(css = "ul[class='nav nav-tabs'] > li > a")
    protected List<WebElement> tabContainer;

    @FindBy(css = "button[class='btn-addRule'] > i + span")
    protected WebElement addRuleButton;

    @FindBy(css = "i[class='mdi mdi-format-list-numbers']")
    protected WebElement hamburgerButton;

    @FindBy(css = "div[id='prior-call-body'] > div")
    protected List<WebElement> conditions;

    @FindBy(css = "div[id='prior-dispo-body'] > div")
    protected List<WebElement> dispositions;

    @FindBy(css = "button[id='save-dispobutton-fields']")
    protected WebElement saveButton;

    @FindBy(css = "div[class='tab-content'] > div:nth-child(4) > div:nth-child(6) > div > input")
    protected List<WebElement> dialingPriorityRadioButtons;

    @FindBy(css = "button[class='save-button']")
    protected WebElement rulesSaveButton;

    @FindBy(css = "div[class='gritter-item'] > div > a + p")
    protected WebElement successMsg;


    public SalesDialer(WebDriver driver) {
        super(driver);
    }

    JavascriptExecutor js = (JavascriptExecutor) driver;

    @Step("Setup reattempt rules for sales dialer")
    public void setupReattemptRules() {
        String parentWindow = driver.getWindowHandle();
        selectLeftSidebarElement("Sales Dialer");
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(parentWindow)) {
                driver.switchTo().window(handle);
            }
        }

        selectOptionFromSalesDialerSidebarMenu("Settings");
        pause(DELAY_TEST_TIME);
        selectTabFromContainer("Reattempt Rules");
        addRuleButton.click();
        hamburgerButton.click();
        selectConditions(1);
        selectDispositions(optionList);
        saveButton.click();
        pause(DELAY_TEST_TIME);
        selectDialingPriorityButtons(1);
        rulesSaveButton.click();

    }

    @Step("Select an element from the left sidebar")
    public void selectLeftSidebarElement(String option) {
        for (WebElement sidebarElement : leftSidebarElements) {
            if (option.equalsIgnoreCase(sidebarElement.getText())) {
                sidebarElement.click();
                break;
            }
        }
    }

    @Step("Select an option from the Sales Dialer sidebar menu")
    public void selectOptionFromSalesDialerSidebarMenu(String option) {
        for (WebElement element : salesDialerSidebarMenu) {
            if (option.equalsIgnoreCase(element.getText())) {
                js.executeScript("arguments[0].click();", element);
                break;
            }
        }
    }

    @Step("Select a tab from the container")
    public void selectTabFromContainer(String option) {
        for (WebElement element : tabContainer) {
            if (option.equalsIgnoreCase(element.getText())) {
                element.click();
                break;
            }
        }
    }

    @Step("Select a condition")
    public void selectConditions(int index) {
        WebElement element = conditions.get(index);
        element.click();

    }

    @Step("Select dispositions")
    public void selectDispositions(List<Integer> optionList) {
        for (int index : optionList) {
            waitForElementToBeClickable(dispositions.get(index));
            dispositions.get(index).click();
        }

    }

    @Step("Select dialing priority")
    public void selectDialingPriorityButtons(int index) {
        WebElement element = dialingPriorityRadioButtons.get(index);
        element.click();

    }

    @Step("Get success message")
    public String getSuccessMsg() {
        return successMsg.getText();
    }

}



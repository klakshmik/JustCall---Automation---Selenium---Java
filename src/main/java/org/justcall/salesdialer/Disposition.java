package org.justcall.salesdialer;

import io.qameta.allure.Step;
import org.justcall.base.WebPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.Set;

public class Disposition extends WebPage {

    @FindBy(css = "ul[class='sidebar-elements'] > li > a > span + span")
    protected List<WebElement> leftSidebarElements;

    @FindBy(css = "ul[id='campaigns-navigation'] > li > a > i + span")
    protected List<WebElement> salesDialerSidebarMenu;

    @FindBy(css = "ul[class='nav nav-tabs'] > li > a")
    protected List<WebElement> tabContainer;

    @FindBy(css = "tbody[id='field_disposition_list'] > tr:first-child > td:nth-child(2) > input")
    protected WebElement dispositionName;

    @FindBy(css = "button[id='btn-addgroup']")
    protected WebElement createDispositionGroupButton;

    @FindBy(css = "div[class='group-wrapper open'] > h3 > input")
    protected WebElement newGroupTextField;

    @FindBy(css = "div[class='group-wrapper open'] > p")
    protected WebElement dragDispositionsArea;

    @FindBy(css = "div[class='gritter-content gritter-without-image'] > p")
    protected WebElement successMsg;

    public Disposition(WebDriver driver) {
        super(driver);
    }

    JavascriptExecutor js = (JavascriptExecutor) driver;

    @Step("Create a new call disposition group")
    public void createCallDispositionGroup() {
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
        selectTabFromContainer("Disposition");
        createDispositionGroupButton.click();
        newGroupTextField.sendKeys(getFaker().name().title());
        dispositionName.click();
        action.dragAndDrop(dispositionName,dragDispositionsArea).build().perform();

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

    @Step("Get the success message after creating a disposition group")
    public String getSuccessMsgText() {
        return successMsg.getText();
    }
}

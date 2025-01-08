package org.justcall.teams;

import io.qameta.allure.Step;
import org.justcall.base.WebPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Teams extends WebPage {

    @FindBy(css = "ul[class='sidebar-elements'] > li > a > span + span")
    protected List<WebElement> leftSidebarElements;

    @FindBy(css = "ul[class='sub-menu'] > li > a > span")
    protected List<WebElement> teamsSubMenu;

    @FindBy(css = "div[class='justify-end items-center flex'] > div:nth-child(2) > div > a > button > span")
    protected WebElement addMemberButton;

    @FindBy(css = "input[placeholder='email@example.com']")
    protected WebElement emailInputField;

    @FindBy(css = "button[data-testid='next']")
    protected WebElement nextButton;

    @FindBy(css = "button[data-testid='next']")
    protected WebElement inviteButton;

    @FindBy(css = "div[class='Toastify__toast-icon Toastify--animate-icon Toastify__zoom-enter'] + div")
    protected WebElement toastMsg;


    public Teams(WebDriver driver) {
        super(driver);
    }

    @Step("Add a new member to the team")
    public void addMemberToTeams() {
        selectLeftSidebarElement("Teams");
        pause(DELAY_TEST_TIME);
        selectFromTeamsSubMenu("Team Members");
        pause(DELAY_TEST_TIME);
        driver.switchTo().frame(0);
        pause(DELAY_TEST_TIME);
        addMemberButton.click();
        emailInputField.sendKeys("bsandra7715@gmail.com");
        emailInputField.sendKeys(Keys.ENTER);
        pause(DELAY_TEST_TIME);
        nextButton.click();
        pause(DELAY_TEST_TIME);
        nextButton.click();
        pause(DELAY_TEST_TIME);
        inviteButton.click();
        pause(DELAY_TEST_TIME);
        driver.switchTo().defaultContent();

    }

    @Step("Select left sidebar element")
    public void selectLeftSidebarElement(String option) {
        for (WebElement sidebarElement : leftSidebarElements) {
            if (option.equalsIgnoreCase(sidebarElement.getText())) {
                sidebarElement.click();
                break;
            }
        }
    }

    @Step("Select option from Teams sub-menu")
    public void selectFromTeamsSubMenu(String menu) {
        for (WebElement option : teamsSubMenu) {
            if (menu.equalsIgnoreCase(option.getText())) {
                option.click();
                break;
            }
        }
    }

    @Step("Get success message")
    public String getSuccessMsgText() {
        return toastMsg.getText();
    }

}

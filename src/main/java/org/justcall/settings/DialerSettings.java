package org.justcall.settings;

import io.qameta.allure.Step;
import org.justcall.base.WebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class DialerSettings extends WebPage {

    @FindBy(css = "img[id='accountimage']")
    protected WebElement imgIcon;

    @FindBy(css = "ul[role='menu'] > li > a")
    protected List<WebElement> roleMenu;

    @FindBy(css = "div[class='tocify'] > ul > li > a")
    protected List<WebElement> settingsList;

    @FindBy(css = "select[id='wrapup_select']")
    protected WebElement wrapUpSelect;

    @FindBy(css = "button[id='saveWrapupButton']")
    protected WebElement saveButton;

    @FindBy(css = "a[class='gritter-close'] + p")
    protected WebElement successMsgToast;



    public DialerSettings(WebDriver driver) {
        super(driver);
    }

    @Step("Set wrap-up time")
    public void setWrapUpTime() {
        imgIcon.click();
        selectFromRoleMenu("Settings");
        selectFromSettingsList("Wrap-up Time Settings");
        Select select = new Select(wrapUpSelect);
        select.selectByIndex(6);
        saveButton.click();
    }

    @Step("Select from role menu")
    public void selectFromRoleMenu(String option) {
        for (WebElement singleMenu : roleMenu) {
            if (option.equalsIgnoreCase(singleMenu.getText())){
                singleMenu.click();
                break;
            }
        }
    }

    @Step("Select from settings list")
    public void selectFromSettingsList(String option) {
        for (WebElement setting : settingsList) {
            if (option.equalsIgnoreCase(setting.getText())){
                setting.click();
                break;
            }
        }
    }

    @Step("Get success message")
    public String successText() {
        return successMsgToast.getText();
    }
}

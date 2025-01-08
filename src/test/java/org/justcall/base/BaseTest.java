package org.justcall.base;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.justcall.utilities.MiscUtil;
import org.justcall.utilities.TestRunPropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

@Slf4j
public class BaseTest {
    
    protected static RemoteWebDriver driver;
    protected static TestRunPropertyReader testRunPropertyReader;

    @BeforeSuite
    public void setupClass() {

        log.info("Started setupClass");

        testRunPropertyReader = new TestRunPropertyReader("src/test/resources/testrun.properties");

        String testPlatform = getTestPlatform();

        switch (testPlatform) {
            case "LAMBDA":
                driver = LambdaPlatformHandler.setupLambdaTestEnv();
                break;

            case "LOCAL":
            default:
                driver = LocalEnvHandler.localSetUp(testRunPropertyReader);
        }

        doLogin();
    }

    @AfterMethod
    public void navigateBack() {
        log.info("In navigateBack() during AfterMethod");
        driver.get("https://app.justcall.io/app/get-started");
    }

    @AfterTest
    public void afterTest(){
        log.info("In AfterTest()");
        driver.quit();
    }

    private void doLogin() {
        MiscUtil.defaultPause();
        driver.get(testRunPropertyReader.getPropertyMethod("url"));
        WebElement loginIcon = driver.findElement(By.cssSelector("a[class='new_menu_login login-link']"));
        loginIcon.click();
        WebElement usernameField = driver.findElement(By.cssSelector("input[id='form-email_id']"));
        usernameField.sendKeys(testRunPropertyReader.getPropertyMethod("userName"));
        WebElement nextButton = driver.findElement(By.cssSelector("button[id='login-btn']"));
        nextButton.click();
        WebElement passwordField = driver.findElement(By.cssSelector("input[id='form-password']"));
        MiscUtil.defaultPause();
        passwordField.sendKeys(testRunPropertyReader.getPropertyMethod("password"));
        MiscUtil.defaultPause();
        WebElement loginButton = driver.findElement(By.cssSelector("button[class='btn btn-color btn-green ']"));
        loginButton.click();
    }

    private static String getTestPlatform() {
        String testPlatform = System.getProperty("testPlatform");
        log.info("testPlatform: {}", testPlatform);

        if (StringUtils.isEmpty(testPlatform))
            testPlatform = "";

        return testPlatform;
    }
}


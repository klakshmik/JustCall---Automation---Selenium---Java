package org.justcall.base;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.justcall.utilities.TestRunPropertyReader;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class LambdaPlatformHandler {

    static Set<String> browserProps = new HashSet<>(Arrays.asList("username", "accessKey", "build", "project", "selenium_version", "browserName"));

    public static RemoteWebDriver setupLambdaTestEnv() {
        TestRunPropertyReader lambdaProps = new TestRunPropertyReader("src/test/resources/lambda.properties");
        ChromeOptions browserOptions = getLambdaBrowserOptions(lambdaProps);
        URL url = null;
        RemoteWebDriver driver = null;
        try {
            url = new URL(lambdaProps.getPropertyMethod("url"));
            driver = new RemoteWebDriver(url, browserOptions);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
        } catch (MalformedURLException e) {
            System.out.println("wrong url");
        }
        return driver;
    }

    private static ChromeOptions getLambdaBrowserOptions(TestRunPropertyReader lambdaProps) {
        ChromeOptions browserOptions = new ChromeOptions();

        setOS(browserOptions);
        setBrowserVersion(browserOptions);

        HashMap<String, Object> ltOptions = getLtOptions(lambdaProps);

        browserOptions.setCapability("LT:Options", ltOptions);
        browserOptions.setCapability("browserName", lambdaProps.getPropertyMethod("browserName"));
        return browserOptions;
    }

    private static HashMap<String, Object> getLtOptions(TestRunPropertyReader lambdaProps) {
        HashMap<String, Object> ltOptions = new HashMap<>();

        for (String key : browserProps) {
            ltOptions.put(key, lambdaProps.getPropertyMethod(key));
        }
        ltOptions.put("video", true);
        ltOptions.put("w3c", true);
        return ltOptions;
    }

    private static void setOS(ChromeOptions browserOptions) {
        String os = System.getProperty("os");
        log.info("os: {}.", os);
        if (StringUtils.isBlank(os)) {
            os = "Windows 10";
        }
        browserOptions.setPlatformName(os);
    }

    private static void setBrowserVersion(ChromeOptions browserOptions) {
        String browserVersion = System.getProperty("browserVersion");
        log.info("browserVersion:{}", browserVersion);
        if (StringUtils.isBlank(browserVersion)) {
            browserVersion = "latest";
        }
        browserOptions.setBrowserVersion(browserVersion);
    }

}
